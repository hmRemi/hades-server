package net.hadesbot.server;

import net.hadesbot.server.cli.CommandLineParser;
import net.hadesbot.server.cli.Configuration;

/**
 * The server class responsible for handling server operations.
 *
 * @author Hades Development
 * @project Hades
 * @date 5/27/2023
 */

public class Server {

    public Server(final String[] args) {
        /**
         * Constructs a new instance of the Server class.
         */
        final Configuration configuration = new Configuration();
        configuration.set("address", "");
        configuration.set("port", 1337);
        configuration.set("password", "");

        CommandLineParser commandLineParser = new CommandLineParser(configuration, args);
        commandLineParser.parseArguments();
    }

    /**
     * Starts the server.
     */
    public void start() {

    }
}
