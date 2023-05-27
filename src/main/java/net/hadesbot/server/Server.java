package net.hadesbot.server;

import net.hadesbot.server.cli.CommandLineParser;
import net.hadesbot.server.cli.Configuration;
import net.hadesbot.server.socket.SocketServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The server class responsible for handling server operations.
 *
 * @author Hades Development
 * @project Hades
 * @date 5/27/2023
 */

public class Server {

    private final Configuration configuration;

    public Server(final String[] args) {
        /**
         * Constructs a new instance of the Server class.
         */
        this.configuration = new Configuration();
        this.configuration.set("address", "");
        this.configuration.set("port", 1337);
        this.configuration.set("password", "");

        CommandLineParser commandLineParser = new CommandLineParser(this.configuration, args);
        commandLineParser.parseArguments();
    }

    /**
     * Starts the server.
     */
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(Integer.parseInt(String.valueOf(this.configuration.get("port"))))) {
            int port = serverSocket.getLocalPort();
            System.out.println("Started server on port " + port + "!");

            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Socket socket = serverSocket.accept();
                    new SocketServer(this, socket);
                } catch (IOException exception) {
                    System.err.println("Connection error (IO Exception - A): " + exception.getMessage() + " | " + exception.getCause().toString());
                }
            }
        } catch (IOException exception) {
            System.err.println("Error occurred while starting the server:");
            exception.printStackTrace();
        } catch (NumberFormatException exception) {
            System.err.println("Invalid port configuration specified!");
            exception.printStackTrace();
        }
    }

}
