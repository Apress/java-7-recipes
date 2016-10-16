import java.awt.*;
import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 8/17/11
 * Time: 10:11 PM
 * Serialization Example
 */
public class Ch_5_1_SerializeExample {
    public static void main(String[] args) {
        Ch_5_1_SerializeExample example = new Ch_5_1_SerializeExample();
        example.start();
    }

    private void start() {
        ProgramSettings settings = new ProgramSettings(new Point(10,10),new Dimension(300,200), Color.blue, "The title of the application" );
        saveSettings(settings,"settings.bin");
        ProgramSettings loadedSettings = loadSettings("settings.bin");
        System.out.println("Are settings are equal? :"+loadedSettings.equals(settings));


    }

    private void saveSettings(ProgramSettings settings, String filename) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(settings);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ProgramSettings loadSettings(String filename) {
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (ProgramSettings) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

}
