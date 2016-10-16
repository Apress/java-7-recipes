import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.View;
import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 9/29/11
 * Time: 5:46 PM
 * Java3D Hello World (or cube) example
 */
public class Recipe13_2 {
    public static void main(String[] args) {
        Recipe13_2 recipe = new Recipe13_2();
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
        SimpleUniverse universe = new SimpleUniverse(canvas);
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
        branchGroup.addChild(new ColorCube(.3f));
        branchGroup.compile();
        return branchGroup;
    }
}
