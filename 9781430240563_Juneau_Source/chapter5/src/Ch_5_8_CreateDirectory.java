import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.*;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 8/14/11
 * Time: 10:13 AM
 * Create Directory
 */
public class Ch_5_8_CreateDirectory {

    private void start() {
        FileSystem fileSystem = FileSystems.getDefault();
        Path directory= fileSystem.getPath("./newDirectoryWPermissions");
        try {
            Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxr-x---");
            FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);
            Files.createDirectory(directory, attr);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String [] args) {
        Ch_5_8_CreateDirectory createDirectory = new Ch_5_8_CreateDirectory();
        createDirectory.start();

    }

}
