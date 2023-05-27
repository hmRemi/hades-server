package net.hadesbot.server.launch;

import net.hadesbot.server.Server;

/**
 * The main class responsible for launching the Hades server.
 *
 * @author Hades Development
 * @project Hades
 * @date 5/27/2023
 */

public class Launch {

    public static void main(final String[] args) {
        // Set the file encoding to UTF-8
        System.setProperty("file.encoding", "UTF-8");

        // Start the server
        new Server(args).start();
    }
}
