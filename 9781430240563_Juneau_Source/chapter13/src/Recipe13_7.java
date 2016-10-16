import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.geometry.Text2D;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.Random;
import java.util.TimerTask;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 9/28/11
 * Time: 8:16 PM
 * Space Game
 */
public class Recipe13_7 {
    private static final int NUMBER_OF_ASTEROIDS = 500;
    long score =0;
    Random random = new Random();

    Text2D scoreText;
    private SimpleUniverse universe;
    Alpha advanceAlpha = new Alpha(1,100000);   // this advances the spaceship



    public static void main(String[] args) {
        Recipe13_7 recipe = new Recipe13_7();
        recipe.start();
    }

    private void start() {
        JFrame frame = new JFrame("Space Game");

        GraphicsConfiguration configuration = SimpleUniverse.getPreferredConfiguration();
        final Canvas3D canvas = new Canvas3D(configuration);
        frame.setContentPane(new JPanel() {
            {
                setLayout(new BorderLayout());
                add(canvas);
            }
        });

        // Set universe Viewing Plate where we want it..
        universe = new SimpleUniverse(canvas);
        universe.getViewingPlatform().setNominalViewingTransform();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        View view = canvas.getView();
        view.setBackClipDistance(300f);

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
        // Attach the view to the "spaceship"
        attachView(spaceshipGroup);

        // Background Suns
        createBackgroundSuns(transformGroup);

        root.compile();
        return root;
    }


    private void gameOver() {
        Text2D gameOverText = new Text2D("GAME OVER",new Color3f(Color.white),"Helvetica",25,Font.ITALIC );

        Transform3D textTransform = new Transform3D();
        textTransform.setTranslation(new Vector3f(-.5f,0f,0f));
        TransformGroup gameOverTransformGroup = new TransformGroup(textTransform);
        gameOverTransformGroup.addChild(gameOverText);
        BranchGroup gameOverBranchGroup = new BranchGroup();
        gameOverBranchGroup.addChild(gameOverTransformGroup);
        universe.getViewingPlatform().addChild(gameOverBranchGroup);
        advanceAlpha.pause();
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
        child.setName("ASTEROID");
        //Wrap around a branchgroup
        BranchGroup asteroidGroup = new BranchGroup();
        asteroidGroup.setCapability(BranchGroup.ALLOW_DETACH);
        asteroidGroup.addChild(child);

        // 1st transform group, rotates
        TransformGroup rotateGroup = new TransformGroup();
        rotateGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        rotateGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        rotateGroup.setCapability(TransformGroup.ALLOW_COLLISION_BOUNDS_READ);
        rotateGroup.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
        rotateGroup.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
        rotateGroup.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
        rotateGroup.setCapability(BranchGroup.ALLOW_DETACH);
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


        CollisionBehavior collisionBehavior = new CollisionBehavior(spaceship);
        collisionBehavior.setSchedulingBounds(new BoundingSphere());
        userMovementGroup.addChild(collisionBehavior);


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

        PositionInterpolator positionInterpolator = new PositionInterpolator(advanceAlpha, positionGroup, axisOfTransform, -30f, 1000f);
        positionInterpolator.setSchedulingBounds(new BoundingSphere(new Point3d(), 1000f));
        positionGroup.addChild(positionInterpolator);


//        // Make it respond to the Arrow Keys
        TransformGroup userTransformGroup = new TransformGroup();
        userTransformGroup.addChild(positionGroup);
        userTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        userTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        userTransformGroup.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);

        KeyboardMoveBehavior keyboardMoveBehavior= new KeyboardMoveBehavior(userMovementGroup, transformGroup, spaceship);
        keyboardMoveBehavior.setSchedulingBounds(new BoundingSphere(new Point3d(), 1000f));
        userTransformGroup.addChild(keyboardMoveBehavior);
        transformGroup.addChild(userTransformGroup);

        return userMovementGroup;

    }

    class KeyboardMoveBehavior extends Behavior {
        TransformGroup spaceshipGroup;
        private float currentAxis =0;
        Alpha laserAlpha = null;
        volatile java.util.Timer timer = null;
        private BranchGroup laserBranchGroup;
        private Cone spaceship;


        KeyboardMoveBehavior(TransformGroup spaceshipGroup, Group transformGroup, Cone spaceship) {
            this.spaceshipGroup = spaceshipGroup;
            this.spaceship = spaceship;
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
                            }
                        }
                    }
                }
            }
            this.wakeupOn(new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED));
        }

        private void moveRight() {
            move(.5f);
        }

        private void moveLeft() {
            move(-0.5f);
        }

        private void move(float v) {
            currentAxis += v;
            if (currentAxis > 20f) currentAxis = 20;
            if (currentAxis < -20f) currentAxis = -20f;
            Transform3D location = new Transform3D();
            location.setTranslation(new Vector3f(currentAxis, 0f, 0f));
            spaceshipGroup.setTransform(location);
        }
    }

    class CollisionBehavior extends Behavior {
        Group spaceshipGroup;
        private WakeupOr combinedCriteria;

        CollisionBehavior(Group spaceshipGroup) {
            this.spaceshipGroup = spaceshipGroup;
        }

        @Override
        public void initialize() {
            WakeupCriterion[] criteria = new WakeupCriterion[3];
            criteria[0] = new WakeupOnCollisionEntry(spaceshipGroup);
            criteria[1] = new WakeupOnCollisionExit(spaceshipGroup);
            criteria[2] = new WakeupOnCollisionMovement(spaceshipGroup);
            combinedCriteria = new WakeupOr(criteria);
            wakeupOn(combinedCriteria);
        }

        @Override
        public void processStimulus(Enumeration criteria) {
            // only when there's a collision.
            while (criteria.hasMoreElements()) {
                Object entry = criteria.nextElement();
                if (entry instanceof WakeupOnCollisionEntry) {
                    WakeupOnCollisionEntry wakeupOnCollisionEntry = (WakeupOnCollisionEntry) entry;
                    if ("ASTEROID".equals(wakeupOnCollisionEntry.getTriggeringPath().getObject().getName())) {
                        gameOver();
                    }
                }
            }
            wakeupOn(combinedCriteria);
        }
    }

    private void attachView(Group attachableGroup) {
        ViewingPlatform viewingPlatform = universe.getViewingPlatform();
        viewingPlatform.detach();
        Transform3D viewTransform = new Transform3D();
        viewTransform.lookAt(new Point3d(0f,-40f,-40f),new Point3d(),new Vector3d(0f,1f,0f) );
        viewTransform.invert();
        TransformGroup viewGroup = new TransformGroup(viewTransform);
        viewGroup.addChild(viewingPlatform);
        attachableGroup.addChild(viewGroup);

    }

    private void  createBackgroundSuns(TransformGroup transformGroup) {
        for (int i =0;i < 20;i++) {
            float x = (random.nextFloat() * 10 -5)*5;
            float  z = (-100 + (random.nextFloat() * 100))*10;
            float  y = (random.nextFloat()) * 30;
            float size = random.nextFloat() * 10f+5;

            BranchGroup sunGroup = new BranchGroup();

            Appearance appearance = new Appearance();
            Material material = new Material();
            appearance.setMaterial(material);
            appearance.setColoringAttributes(new ColoringAttributes(new Color3f(Color.yellow), ColoringAttributes.SHADE_GOURAUD));
            Sphere sun = new Sphere(size, Sphere.GENERATE_NORMALS, appearance);
            sunGroup.addChild(sun);

            DirectionalLight directionalLight = new DirectionalLight();
            directionalLight.setDirection(0,1,-1);
            directionalLight.setColor(new Color3f(new Color(random.nextInt())));
            directionalLight.setInfluencingBounds(new BoundingSphere(new Point3d(),50f));
            sunGroup.addChild(directionalLight);

            // Move
            Transform3D sunTransform3D = new Transform3D();
            sunTransform3D.setTranslation(new Vector3f(x,10+y,z));
            TransformGroup sunTransformGroup = new TransformGroup(sunTransform3D);
            sunTransformGroup.addChild(sunGroup);

            transformGroup.addChild(sunTransformGroup);

        }
    }

}
