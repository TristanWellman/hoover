package sockets

import java.net.ServerSocket
import java.net.Socket

import sockets.sock
import java.io.*
import java.util.*

class server_side {

    fun server_main() {
        while(true) {

            val scan: BufferedReader = BufferedReader(FileReader("user.config"));
            var server_port: String = "";

            while(true) {
                val line: String = scan.readLine() ?: break;

                if(line.contains("SERVER_PORT=")) {
                    server_port = line;
                }
            }

            val st: StringTokenizer = StringTokenizer(server_port);
            val str1: String = st.nextToken("~");
            val PORT: String = st.nextToken("~");
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

            val scan: BufferedReader = BufferedReader(FileReader("user.config"));
            var server_port: String = "";

            while(true) {
                val line: String = scan.readLine() ?: break;

                if(line.contains("SERVER_LOGIN_PORT=")) {
                    server_port = line;
                }
            }

            val st: StringTokenizer = StringTokenizer(server_port);
            val str1: String = st.nextToken("~");
            val PORT: String = st.nextToken("~");
            val port: Int = PORT.toInt();


            var serversocket: ServerSocket = ServerSocket(port);

            val socket: Socket = serversocket.accept();
            val input: InputStream = DataInputStream(socket.getInputStream());
            val output: OutputStream = FileOutputStream("login.log");

            val buff: ByteArray = ByteArray(2048);

            val bytes: Int = input.read(buff, 0, buff.size);
            output.write(buff, 0, bytes);

            output.close();
            input.close();
            socket.close();
        }
    }

}