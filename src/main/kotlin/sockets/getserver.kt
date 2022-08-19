package sockets

import java.io.BufferedReader
import java.io.FileReader
import java.util.*

class getserver {

    fun server_and_port(): Array<String> {

        val scan: BufferedReader = BufferedReader(FileReader("user.config"));
        var server_ip: String = "";
        var server_port: String = "";
        var server_ip2: String = "";
        var server_port2: String = "";

        while(true) {
            val line: String = scan.readLine() ?: break;

            if(line.contains("SERVER_IP=")) {
                server_ip = line;
            } else if(line.contains("SERVER_PORT=")) {
                server_port = line;
            } else if(line.contains("SERVER_IP2=")) {
                server_ip2 = line;
            } else if(line.contains("SERVER_PORT2=")) {
                server_port2 = line;
            }
        }

        val st: StringTokenizer = StringTokenizer(server_ip);
        val str1: String = st.nextToken("~");
        val SERVER_IP: String = st.nextToken("~");

        val st2: StringTokenizer = StringTokenizer(server_port);
        val str2: String = st2.nextToken("~");
        val SERVER_PORT: String = st2.nextToken("~");

        val st3: StringTokenizer = StringTokenizer(server_port);
        val str3: String = st3.nextToken("~");
        val SERVER_PORT2: String = st3.nextToken("~");

        val st4: StringTokenizer = StringTokenizer(server_port);
        val str4: String = st4.nextToken("~");
        val SERVER_IP2: String = st4.nextToken("~");

        val arr: Array<String> = arrayOf(
            SERVER_IP,
            SERVER_PORT,
            SERVER_IP2,
            SERVER_PORT2
        );

        return arr;

    }

}