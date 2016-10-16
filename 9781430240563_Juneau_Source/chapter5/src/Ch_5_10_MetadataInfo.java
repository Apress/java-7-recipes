import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.DosFileAttributeView;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 8/14/11
 * Time: 3:11 PM
 * Metadata Example
 */
public class Ch_5_10_MetadataInfo {

    private void start() {
        Path path = FileSystems.getDefault().getPath("./file2.log");
        try {
            // General file attributes, supported by all Java systems
            System.out.println("File Size:"+Files.size(path));
            System.out.println("Is Directory:"+Files.isDirectory(path));
            System.out.println("Is Regular File:"+Files.isRegularFile(path));
            System.out.println("Is Symbolic Link:"+Files.isSymbolicLink(path));
            System.out.println("Is Hidden:"+Files.isHidden(path));
            System.out.println("Last Modified Time:"+Files.getLastModifiedTime(path));
            System.out.println("Owner:"+Files.getOwner(path));

            // Specific attribute views.
            DosFileAttributeView view = Files.getFileAttributeView(path, DosFileAttributeView.class);
            System.out.println("\nDOS File Attributes\n------------------------------------\n");
            System.out.println("Archive  :"+view.readAttributes().isArchive());
            System.out.println("Hidden   :"+view.readAttributes().isHidden());
            System.out.println("Read-only:"+view.readAttributes().isReadOnly());
            System.out.println("System   :"+view.readAttributes().isSystem());

            view.setHidden(false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Ch_5_10_MetadataInfo info = new Ch_5_10_MetadataInfo();
        info.start();
    }

}
