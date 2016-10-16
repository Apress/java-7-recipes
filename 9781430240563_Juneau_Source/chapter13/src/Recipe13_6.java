import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Enumeration;
import java.util.Random;
import java.util.TimerTask;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 9/29/11
 * Time: 6:33 PM
 * To change this template use File | Settings | File Templates.
 */

public class Recipe13_6 {
    private SimpleUniverse universe ;
    private static final int NUMBER_OF_ASTEROIDS = 500;
    Random random = new Random();

    public static void main(String[] args) {
        Recipe13_6 recipe = new Recipe13_6();
        recipe.start();
    }

    private void start() {
        JFrame frame = new JFrame("Space Game");
        GraphicsConfiguration configuration = SimpleUniverse.getPreferredConfiguration();
        final Canvas3D canvas = new Canvas3D(configuration);
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add (canvas);
        frame.setContentPane(contentPane);
        // Set universe Viewing Plate where we want it..
        universe = new SimpleUniverse(canvas);
        universe.getViewingPlatform().setNominalViewingTransform();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        View view = canvas.getView();
        view.setBackClipDistance(300f);
        Transform3D viewTransform3D = new Transform3D();
        viewTransform3D.lookAt(new Point3d(0,20,40),new Point3d(), new Vector3d(0,1,0));
        viewTransform3D.invert();
        universe.getViewingPlatform().getViewPlatformTransform().setTransform(viewTransform3D);

        // Our Scene
        BranchGroup scene = createScene();
        universe.addBranchGraph(scene);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setVisible(true);


    }

    private BranchGroup createScene() {
        BranchGroup root = new BranchGroup();
        root.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);

        TransformGroup transformGroup = new TransformGroup();       // top transform group
        transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        root.addChild(transformGroup);
        // Create Asteroids
        createAsteroids(transformGroup);
        // Create Hero Spaceship
        TransformGroup spaceshipGroup = createSpaceship(transformGroup);

        root.compile();
        return root;
    }


    private void createAsteroids(Group group) {
        for (int i= 0;i < NUMBER_OF_ASTEROIDS;i++) {
            double x = random.nextDouble() * 50 -25;
            double z = - 1000+  (random.nextDouble() * 1000);
            group.addChild(createAsteroid(x, z));
        }

    }

    private TransformGroup createAsteroid( double x, double z) {
        // Leaf, the Cube
        ColorCube child = new ColorCube(0.3f);
        //Wrap around a branchgroup
        BranchGroup asteroidGroup = new BranchGroup();
        asteroidGroup.addChild(child);

        // 1st transform group, rotates
        TransformGroup rotateGroup = new TransformGroup();
        rotateGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        rotateGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        rotateGroup.addChild(asteroidGroup);

        Alpha scaleAlpha = new Alpha(-1,random.nextInt(16000));
        RotationInterpolator rotationInterpolator = new RotationInterpolator(scaleAlpha, rotateGroup);
        rotationInterpolator.setSchedulingBounds(new BoundingSphere(new Point3d(),1000f));      // rotate when we're 1000f
        rotationInterpolator.setTransformAxis(new Transform3D());
        rotateGroup.addChild(rotationInterpolator);



        // 2nd Transform Group, translates.
        TransformGroup translateGroup = new TransformGroup();
        Transform3D locationTransform = new Transform3D();
        locationTransform.setTranslation(new Vector3d(x,0d,z));
        translateGroup.setTransform(locationTransform);
        translateGroup.addChild(rotateGroup);
        translateGroup.setCapability(BranchGroup.ALLOW_DETACH);

        return translateGroup;
    }

    private TransformGroup createSpaceship(TransformGroup transformGroup) {
        // Spaceship
        Appearance ap = new Appearance();
        ap.setColoringAttributes(new ColoringAttributes(new Color3f(Color.BLUE),ColoringAttributes.SHADE_GOURAUD));

        Cone spaceship = new Cone(.5f, 2f, ap);

        // User "Displacement" group, used for moving the spaceship because of the keyboard
        TransformGroup userMovementGroup = new TransformGroup();
        userMovementGroup.addChild(spaceship);
        userMovementGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        userMovementGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        userMovementGroup.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
        userMovementGroup.setCapability(TransformGroup.ALLOW_CHILDREN_READ);


        Transform3D spaceship3D = new Transform3D();
        spaceship3D.rotX(-Math.PI/2);               // Face the right way. Make it look towards the -Z field
        TransformGroup spaceshipGroup = new TransformGroup(spaceship3D);
        spaceshipGroup.addChild(userMovementGroup);


        TransformGroup positionGroup = new TransformGroup();
        positionGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        positionGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        positionGroup.addChild(spaceshipGroup);


//        // Make it "advance"
        Transform3D axisOfTransform = new Transform3D();
        axisOfTransform.rotY(Math.PI / 2); // make it so that it's advancing "forward"

//        // Make it respond to the Arrow Keys
        TransformGroup userTransformGroup = new TransformGroup();
        userTransformGroup.addChild(positionGroup);
        userTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        userTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        userTransformGroup.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);

        KeyboardMoveBehavior keyboardMoveBehavior= new KeyboardMoveBehavior(userMovementGroup, transformGroup);
        keyboardMoveBehavior.setSchedulingBounds(new BoundingSphere(new Point3d(), 1000f));
        userTransformGroup.addChild(keyboardMoveBehavior);
        transformGroup.addChild(userTransformGroup);

        return userMovementGroup;

    }

    class KeyboardMoveBehavior extends Behavior {
        TransformGroup spaceshipGroup;
        private float currentAxis =0;
        private float currentDepth = 0;
        volatile java.util.Timer timer = null;
        private BranchGroup laserBranchGroup;


        KeyboardMoveBehavior(TransformGroup spaceshipGroup, Group transformGroup) {
            this.spaceshipGroup = spaceshipGroup;
            laserBranchGroup = new BranchGroup();
            laserBranchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
            laserBranchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
            laserBranchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
            laserBranchGroup.setCapability(BranchGroup.ALLOW_DETACH);

            transformGroup.addChild(laserBranchGroup);
        }

        @Override
        public void initialize() {
            this.wakeupOn(new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED));
            currentAxis = 0f;
        }

        @Override
        public void processStimulus(Enumeration criteria) {
            while (criteria.hasMoreElements()) {
                Object element = criteria.nextElement();
                if (element instanceof WakeupOnAWTEvent) {
                    WakeupOnAWTEvent event = (WakeupOnAWTEvent) element;
                    for (AWTEvent awtEvent : event.getAWTEvent()) {
                        if (awtEvent instanceof KeyEvent) {
                            KeyEvent keyEvent = (KeyEvent) awtEvent;
                            if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
                                moveLeft();
                            } else if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
                                moveRight();
                            } else if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
                                moveForward();
                            } else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
                                moveBackwards();
                            }
                        }
                    }
                }
            }
            this.wakeupOn(new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED));
        }

        private void moveBackwards() {
            move(0,-0.5f);
        }

        private void moveForward() {
            move(0,0.5f);

        }

        private void moveRight() {
            move(.5f,0);
        }

        private void moveLeft() {
            move(-0.5f,0);
        }

        private void move(float sideways, float forward) {
            currentAxis += sideways;
            currentDepth += forward;
            if (currentAxis > 20f) currentAxis = 20;
            if (currentAxis < -20f) currentAxis = -20f;
            Transform3D location = new Transform3D();
            location.setTranslation(new Vector3f(currentAxis, currentDepth, 0f));
            spaceshipGroup.setTransform(location);
        }
    }



}
