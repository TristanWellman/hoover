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

}