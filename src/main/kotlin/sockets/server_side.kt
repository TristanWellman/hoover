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

    fun server_logins() {

        while(true) {

            val msg: String = server_recievestring();

            if(msg.contains("LOGIN~")) {

                File("logins.log").appendText(msg + "\n");
                val os = System.getProperty("os.name").lowercase();

                when(os) {
                    "win" -> Runtime.getRuntime().exec("hoover_cserver");
                    else -> {
                        Runtime.getRuntime().exec("./hoover_cserver");
                    }
                }
                val success: File = File("logins.log");
                val scan: BufferedReader = BufferedReader(FileReader("user.config"));
                var suc: String = "";

                while(true) {
                    val line: String = scan.readLine() ?: break;

                    if (line.contains("SUCCESS=TRUE")) {
                        suc = line;
                    } else if(line.contains("SUCCESS=false")) {
                        log.log_warning("User tried to login and did not succeed!");
                        continue;
                    }
                }
                scan.close();
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