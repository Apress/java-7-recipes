import java.io.IOException;
import java.nio.file.*;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 8/13/11
 * Time: 8:53 AM
 * Copy a file
 */
public class Ch_5_6_CopyFileExample {

    public static void main (String[] args) {
        Ch_5_6_CopyFileExample exampleCh56 = new Ch_5_6_CopyFileExample();
        exampleCh56.copyFile();
    }

    private void copyFile() {
        FileSystem fileSystem = FileSystems.getDefault();
        Path sourcePath = fileSystem.getPath("file.log");
        Path targetPath = fileSystem.getPath("file2.log");
        System.out.println("Copy from "+sourcePath.toAbsolutePath().toString()+" to "+targetPath.toAbsolutePath().toString());
        try {
            Files.move(sourcePath, targetPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
