package sockets

import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.ServerSocket
import java.net.Socket

import sockets.sock

class server_side {

    public val port: Int = 8080

    fun server_main() {
        while(true) {

            var serversocket: ServerSocket = ServerSocket(port);

            val socket: Socket = serversocket.accept();
            val input: InputStream = DataInputStream(socket.getInputStream());
            val output: OutputStream = FileOutputStream("data.log");

            val buff: ByteArray = ByteArray(2048);

            val bytes: Int = input.read(buff, 0, buff.size);
            output.write(buff, 0, bytes);

            output.close();
            input.close();
            socket.close();
        }
    }

}