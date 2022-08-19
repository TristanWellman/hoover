package sockets

import java.net.ServerSocket
import java.net.Socket

import sockets.*
import java.io.*
import java.util.*

class server_side {

    public val getserver = getserver();
    public val serverarr: Array<String> = getserver.server_and_port();

    fun server_main() {
        while(true) {

            val PORT = serverarr[1];

            val port: Int = PORT.toInt();

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

    fun server_logins() {
        while(true) {

            val msg: String = server_recievestring();
            File("logins.hver").appendText(msg);

            //Runtime.getRuntime().exec("./");

        }
    }

    fun server_sendstring(string: String) {

        val PORT = serverarr[3];
        val port: Int = PORT.toInt();

        val socket: ServerSocket = ServerSocket(port);
        val server: Socket = socket.accept();

        val dos: DataOutputStream = DataOutputStream(server.getOutputStream());
        dos.writeUTF(string);
    }

    fun server_recievestring(): String {

        val PORT = serverarr[1];
        val port: Int = PORT.toInt();

        val socket: ServerSocket = ServerSocket(port);
        val server: Socket = socket.accept();

        val output: DataInputStream = DataInputStream(server.getInputStream())
        val msg: String = output.readUTF();

        return msg;

    }

}