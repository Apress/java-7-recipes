import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import java.awt.*;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 9/29/11
 * Time: 6:33 PM
 * To change this template use File | Settings | File Templates.
 */

public class Recipe13_4 {
    private SimpleUniverse universe ;
    private static final int NUMBER_OF_ASTEROIDS = 500;
    Random random = new Random();

    public static void main(String[] args) {
        Recipe13_4 recipe = new Recipe13_4();
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

        // Our Scene
        BranchGroup scene = createScene();
        universe.addBranchGraph(scene);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setVisible(true);


    }

    private BranchGroup createScene() {

        BranchGroup branchGroup = new BranchGroup();

        createAsteroids(branchGroup);


        branchGroup.compile();
        return branchGroup;
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


}
