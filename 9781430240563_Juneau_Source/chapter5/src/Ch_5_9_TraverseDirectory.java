import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 8/14/11
 * Time: 11:51 AM
 * Traverse File Example
 */
public class Ch_5_9_TraverseDirectory {

    private void start() {
        FileVisitor<Path> myFileVisitor = new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("Visited File: "+file.toString());
                return FileVisitResult.CONTINUE;
            }
        };

        FileSystem fileSystem = FileSystems.getDefault();
        Path directory= fileSystem.getPath(".");
        try {
            Files.walkFileTree(directory, myFileVisitor);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main (String args[]) {
        Ch_5_9_TraverseDirectory traverseDirectory = new Ch_5_9_TraverseDirectory();
        traverseDirectory.start();
    }




}
