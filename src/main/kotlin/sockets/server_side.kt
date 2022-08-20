package sockets

import encrypt.hoovercrypt
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

    fun server_signups(signup: String) {
        File("signups.log").appendText(signup + "\n");
        val os = System.getProperty("os.name").lowercase();

        /*when(os) {
            "win" -> Runtime.getRuntime().exec("c_server.exe signup");
            else -> {
                Runtime.getRuntime().exec("./c_server signup");
            }
        }*/

        val st: StringTokenizer = StringTokenizer(signup);
        val delim: String = "~";
        st.nextToken(delim);
        val uname: String = st.nextToken(delim);
        val encrypt2: String = st.nextToken(delim);
        val enckey1: String = st.nextToken(delim);
        val enckey3: String = st.nextToken(delim);

        File("user/" + uname + ".user").appendText(
            "USER_NAME=~" + uname + "~\n" +
                    "ENCRYPT2=~" + encrypt2 + "~\n" +
                    "ENCKEY1=~" + enckey1 + "~\n" +
                    "ENCKEY3" + enckey3 + "~\n"
        );

    }

    fun server_logins() {

        while(true) {

            val msg: String = server_recievestring();

            if(msg.contains("LOGIN~")) {

                File("logins.log").appendText(msg + "\n");
                val os = System.getProperty("os.name").lowercase();

                /*when(os) {
                    "win" -> Runtime.getRuntime().exec("c_server.exe login");
                    else -> {
                        Runtime.getRuntime().exec("./c_server login");
                    }
                }*/

                val st: StringTokenizer = StringTokenizer(msg);
                val str1: String = st.nextToken("~");
                val uname: String = st.nextToken("~")

                val success: File = File("user/" + uname + ".user");
                success.createNewFile();
                val scan: BufferedReader = BufferedReader(FileReader(success));
                var key1: String = "";
                var key2: String = "";
                var enckey1: String = "";
                var enckey3: String = "";
                var encrypt2: String = "";

                while(true) {
                    val line: String = scan.readLine() ?: break;

                    if (line.contains("ENCKEY1=")) {
                        enckey1 = line;
                    } else if(line.contains("ENCKEY3=")) {
                        enckey3 = line
                    } else if(line.contains("ENCRYPT2=")) {
                        encrypt2 = line;
                    }
                }
                scan.close();

                key1 = st.nextToken("~");
                key2 = st.nextToken("~");

                val crypt = hoovercrypt();
                val arr: Array<String> = arrayOf(key1, enckey1, key2, enckey3, encrypt2);
                crypt.hoover_decrypt(arr);

            } else if(msg.contains("SIGNUP~")) {
                server_signups(msg);
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