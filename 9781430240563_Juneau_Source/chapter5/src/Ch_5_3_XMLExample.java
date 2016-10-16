import java.awt.*;
import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 8/18/11
 * Time: 9:35 PM
 * Encoder XML Example
 */
public class Ch_5_3_XMLExample {
    public static void main(String[] args) {
        Ch_5_3_XMLExample example = new Ch_5_3_XMLExample();
        example.start();
    }

    private void start() {
        ProgramSettings settings = new ProgramSettings(new Point(10,10),new Dimension(300,200), Color.blue, "The title of the application" );
        try {
            FileSystem fileSystem = FileSystems.getDefault();
            FileOutputStream fos = new FileOutputStream("settings.xml");
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.setExceptionListener(new ExceptionListener() {
                @Override
                public void exceptionThrown(Exception e) {
                    System.out.println("Exception! :"+e.toString());
                }
            });
            encoder.writeObject(settings);
            encoder.close();
            fos.close();



            FileInputStream fis = new FileInputStream("settings.xml");
            XMLDecoder decoder = new XMLDecoder(fis);
            ProgramSettings decodedSettings = (ProgramSettings) decoder.readObject();
            System.out.println("Is same? "+settings.equals(decodedSettings));
            decoder.close();
            fis.close();

            Path file= fileSystem.getPath("settings.xml");
            List<String> xmlLines = Files.readAllLines(file, Charset.defaultCharset());

            for (String line : xmlLines) {
                System.out.println(line);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }






    }
}
