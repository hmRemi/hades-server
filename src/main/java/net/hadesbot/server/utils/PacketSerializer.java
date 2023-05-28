package net.hadesbot.server.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.LinkedList;

/**
 * PacketSerializer is a utility class to write/read certain
 * data types to/from byte arrays.
 *
 * @author Hades Development
 * @project Hades
 * @date 5/28/2023
 */

public class PacketSerializer {

    public LinkedList<Byte> data = new LinkedList<>();
    public int pointer = 0;
    public PacketSerializer(){

    }

    public void serialize(){
        int length = data.size();
        /* add length to data, will be used for encryption later on */
        writeInteger(data.size()); // write the size at the end
        for(int i = 0; i < 4; i++){ // move the 4 bytes representing the size to the start
            data.addFirst(data.removeLast());
        }

    }

    public PacketSerializer(byte[] data){
        for(byte b : data)
            this.data.add(b);
    }

    public byte[] toBytes(Byte[] bytes){
        byte[] output = new byte[bytes.length];
        for(int i = 0; i < output.length; i++)
            output[i] = bytes[i];
        return output;
    }
    public void writeInteger(int value){
        for (byte b : ByteBuffer.allocate(4).putInt(value).order(ByteOrder.BIG_ENDIAN).array()) {
            data.add(b);
        }
    }
    public int readInt(int index){
        byte[] arr = toBytes(data.toArray(new Byte[0]));
        return ByteBuffer.wrap(arr).getInt(index);
    }

    public int readInt(){
        int val = readInt(pointer);
        pointer += 4;
        return val;
    }
    public void writeShort(short value){
        for (byte b : ByteBuffer.allocate(2).putShort(value).order(ByteOrder.BIG_ENDIAN).array()) {
            data.add(b);
        }
    }
    public short readShort(int index){
        byte[] arr = toBytes(data.toArray(new Byte[0]));
        return ByteBuffer.wrap(arr).getShort(index);
    }
    public short readShort(){
        short val = readShort(pointer);
        pointer += 2;
        return val;
    }
    public void writeChar(char value){
        for (byte b : ByteBuffer.allocate(2).putChar(value).order(ByteOrder.BIG_ENDIAN).array()) {
            data.add(b);
        }
    }

    public void writeString(String str){
        writeInteger(str.length());
        for(char c : str.toCharArray()){
            writeChar(c);
        }
    }

    public char readChar(int index){
        byte[] arr = toBytes(data.toArray(new Byte[0]));
        return ByteBuffer.wrap(arr).getChar(index);
    }

    public char readChar(){
        char val = readChar(pointer);
        pointer += 2;
        return val;
    }

    public byte readByte(int index){
        return data.get(index);
    }

    public byte readByte(){
        return readByte(pointer++);
    }

    public String readString(){
        int length = readInt();
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < length; i++){
            output.append(readChar());
        }
        return output.toString();
    }

}