package org.java7recipes.chapter23.recipe23_4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * Recipe 23-4
 * 
 * Server for broadcasting multicast messages.
 * 
 * @author juneau
 */
public class MulticastServer extends Thread {

    protected DatagramSocket socket = null;
    protected ByteBuffer message = null;

    public MulticastServer() {
    }

    public static void main(String[] args) {

        MulticastServer server = new MulticastServer();
        server.start();

    }

    public void run() {

        try {

            // send the response to the client at "address" and "port"
            InetAddress address = InetAddress.getByName("226.18.84.25");
            int port = 5239;

            DatagramChannel server = DatagramChannel.open().bind(null);
            System.out.println("Sending datagram packet to group " + address + " on port " + port);
            ByteBuffer message = ByteBuffer.wrap("Hello to all listeners".getBytes());
            server.send(message, new InetSocketAddress(address, port));

            server.disconnect();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
