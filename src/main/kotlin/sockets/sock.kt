package sockets

import java.io.*
import java.net.Socket;

class sock {

    fun socket_sendfile(server: String, port: String, file: File) {

        val PORT: Int = port.toInt();

        val socket: Socket = Socket(server, PORT);
        val input: InputStream = FileInputStream(file);
        val output: OutputStream = DataOutputStream(socket.getOutputStream());

        val buff: ByteArray = ByteArray(2048);
        val bytes: Int = input.read(buff, 0, buff.size);
        output.write(buff, 0, bytes);

        input.close();
        output.close();
        socket.close();

    }

    fun socket_sendstring(server: String, port: String, input: String) {

        println("$port" + "\n" + "$server");

        val PORT = port.toInt();


        val socket: Socket = Socket(server, PORT);

        val dos: DataOutputStream = DataOutputStream(socket.getOutputStream());
        dos.writeUTF(input);

    }

    fun socket_recievestring(server: String, port: String) {

        val PORT = port.toInt();

        val socket: Socket = Socket(server, PORT);
        val output: DataInputStream = DataInputStream(socket.getInputStream());

        val msg: String = output.readUTF();

    }

}