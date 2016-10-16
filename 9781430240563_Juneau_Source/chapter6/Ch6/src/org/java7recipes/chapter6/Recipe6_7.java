package org.java7recipes.chapter6;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 8/31/11
 * Time: 8:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Recipe6_7 {
    public static void main (String[] args) {
        Recipe6_7 recipe = new Recipe6_7();
        recipe.start();
    }

    Object chatServer = null;

    private void start() {
        try {
            sendChat("Hello, how are you?");
        } catch (ConnectionUnavailableException e) {
            System.out.println("Got a connection unavailable Exception!");
        }

        disconnectChatServer(chatServer);
    }

    private void disconnectChatServer(Object chatServer) {
        if (chatServer == null) throw new IllegalChatServerException("Chat server is empty");
    }

    private void sendChat(String chatMessage) throws ConnectionUnavailableException {
        if (chatServer == null) throw new ConnectionUnavailableException("Can't find the chat server");
    }


    class ConnectionUnavailableException extends Exception {
        ConnectionUnavailableException(String message) {
            super(message);
        }
    }

    class IllegalChatServerException extends RuntimeException {
        IllegalChatServerException(String message) {
            super(message);
        }
    }
}
