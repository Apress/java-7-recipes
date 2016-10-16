import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.Channels;
import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 8/11/11
 * Time: 9:48 PM
 * Example of Async Channels
 */
public class Ch_5_4_AsyncChannel {
    private AsynchronousSocketChannel clientWorker;

    InetSocketAddress hostAddress;

    public Ch_5_4_AsyncChannel() {
    }

    private void start() throws IOException, ExecutionException, TimeoutException, InterruptedException {
        hostAddress = new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 2583);

        Thread serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                serverStart();
            }
        });

        serverThread.start();


        Thread clientThread = new Thread(new Runnable() {
            @Override
            public void run() {
                clientStart();
            }
        });
        clientThread.start();

    }

    private void clientStart() {
        try {
            AsynchronousSocketChannel clientSocketChannel = AsynchronousSocketChannel.open();
            Future<Void> connectFuture = clientSocketChannel.connect(hostAddress);
            connectFuture.get();            // Wait until connection is done.
            OutputStream os = Channels.newOutputStream(clientSocketChannel);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            for (int i = 0; i < 5; i++) {
                oos.writeObject("Look at me " + i);
                Thread.sleep(1000);
            }
            oos.writeObject("EOF");
            oos.close();
            clientSocketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    private void serverStart() {
        try {
            AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open().bind(hostAddress);
            Future<AsynchronousSocketChannel> serverFuture  = serverSocketChannel.accept();
            final AsynchronousSocketChannel clientSocket = serverFuture.get();
            System.out.println("Connected!");
            if ((clientSocket != null) && (clientSocket.isOpen())) {
                InputStream connectionInputStream = Channels.newInputStream(clientSocket);
                ObjectInputStream ois = null;
                ois = new ObjectInputStream(connectionInputStream);
                while (true) {
                    Object object = ois.readObject();
                    if (object.equals("EOF")) {
                        clientSocket.close();
                        break;
                    }
                    System.out.println("Received :" + object);
                }
                ois.close();
                connectionInputStream.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//        try {
//            AsynchronousSocketChannel clientConnection = serverFuture.get();
//            InputStream connectionInputStream = Channels.newInputStream(clientConnection);
//            ObjectInputStream ois = new ObjectInputStream(connectionInputStream);
//            while (true) {
//                Object object = ois.readObject();
//                if (object.equals("EOF")) return;
//                System.out.println("Received :"+object);
//            }
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args) throws IOException, ExecutionException, TimeoutException, InterruptedException {
        Ch_5_4_AsyncChannel example = new Ch_5_4_AsyncChannel();
        example.start();
    }


}
