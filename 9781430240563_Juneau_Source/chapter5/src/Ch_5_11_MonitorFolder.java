import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 8/15/11
 * Time: 10:47 PM
 * Monitor File/Floder
 */
public class Ch_5_11_MonitorFolder {
    private void start() {
        try {
            System.out.println("Watch Event, press q<Enter> to exit");
            FileSystem fileSystem = FileSystems.getDefault();
            WatchService service = fileSystem.newWatchService();
            Path path = fileSystem.getPath(".");
            System.out.println("Watching :"+path.toAbsolutePath());
            path.register(service, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
            boolean shouldContinue = true;
            while(shouldContinue) {
                WatchKey key = service.poll(250, TimeUnit.MILLISECONDS);

                // Code to stop the program
                while (System.in.available() > 0) {
                    int readChar = System.in.read();
                    if ((readChar == 'q') || (readChar == 'Q')) {
                        shouldContinue = false;
                        break;
                    }
                }
                if (key == null) continue;
                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.kind() == StandardWatchEventKinds.OVERFLOW) continue;
                    WatchEvent<Path> ev = (WatchEvent<Path>)event;
                    Path filename = ev.context();
                    System.out.println("Event detected :"+filename.toString()+" "+ev.kind());
                }
                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main (String[] args) {
        Ch_5_11_MonitorFolder monitorFolder = new Ch_5_11_MonitorFolder();
        monitorFolder.start();

    }

}
