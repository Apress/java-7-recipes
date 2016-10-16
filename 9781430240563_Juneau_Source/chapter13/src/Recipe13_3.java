import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 9/29/11
 * Time: 6:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class Recipe13_3 {
    private SimpleUniverse universe ;

    public static void main(String[] args) {
        Recipe13_3 recipe = new Recipe13_3();
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

        TransformGroup asteroid = createAsteroid(-0.5f, -4f);
        branchGroup.addChild(asteroid);


        branchGroup.compile();
        return branchGroup;
    }


    private TransformGroup createAsteroid( double x, double z) {
        // Leaf, the Cube
        ColorCube child = new ColorCube(0.3f);

        Transform3D rotationTransform = new Transform3D();
        rotationTransform.rotY(Math.PI/8);
        TransformGroup rotationGroup = new TransformGroup(rotationTransform);
        rotationGroup.addChild(child);


        // 2nd Transform Group, translates.
        Transform3D locationTransform = new Transform3D();
        locationTransform.setTranslation(new Vector3d(x, 0d, z));
        TransformGroup translateGroup = new TransformGroup(locationTransform);
        translateGroup.setTransform(locationTransform);
        translateGroup.addChild(rotationGroup);



        return translateGroup;
    }



}
