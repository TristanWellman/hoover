package sockets

import log.hoover_log
import java.net.ServerSocket
import java.net.Socket

import sockets.*
import java.io.*
import java.util.*

class server_side {

    public val getserver = getserver();
    public val serverarr: Array<String> = getserver.server_and_port();
    public val log = hoover_log();

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

    fun server_signups() {

    }

    fun server_logins() {

        while(true) {

            val msg: String = server_recievestring();

            if(msg.contains("LOGIN~")) {

                File("logins.log").appendText(msg + "\n");
                val os = System.getProperty("os.name").lowercase();

                when(os) {
                    "win" -> Runtime.getRuntime().exec("c_server.exe");
                    else -> {
                        Runtime.getRuntime().exec("./c_server");
                    }
                }

                val st: StringTokenizer = StringTokenizer(msg);
                val str1: String = st.nextToken("~");
                val uname: String = st.nextToken("~")

                val success: File = File("user/" + uname + ".login");
                val scan: BufferedReader = BufferedReader(FileReader(success));
                var key1: String = "";
                var key2: String = "";
                var enckey1: String = "";
                var enckey3: String = "";
                var encrypt2: String = "";

                while(true) {
                    val line: String = scan.readLine() ?: break;

                    if (line.contains("KEY1~")) {
                        key1 = line;
                    } else if(line.contains("KEY2~")) {
                        key2 = line
                    }
                }
                /*if(suc.contains("SUCCESS=TRUE")) {
                    server_sendstring("true");
                } else {
                    server_sendstring("false");
                    continue;
                }*/
                scan.close();
            } else if(msg.contains("SIGNUP~")) {
                server_signups();
            } else {
                continue;
            }

        }
    }

    fun server_sendstring(string: String) {

        val PORT = serverarr[1];
        val port: Int = PORT.toInt();

        val socket: ServerSocket = ServerSocket(port);
        val server: Socket = socket.accept();

        val dos: DataOutputStream = DataOutputStream(server.getOutputStream());
        dos.writeUTF(string);
    }

    fun server_recievestring(): String {

        val PORT = serverarr[3];
        val port: Int = PORT.toInt();

        val socket: ServerSocket = ServerSocket(port);
        val server: Socket = socket.accept();

        val output: DataInputStream = DataInputStream(server.getInputStream())
        val msg: String = output.readUTF();

        output.close();
        server.close();

        return msg;

    }

}