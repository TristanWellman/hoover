package hoover

import encrypt.hoovercrypt
import encrypt.symenc
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.*
import javax.crypto.AEADBadTagException

import sockets.getserver
import sockets.sock

class hoover_user {

    public val cal: Calendar = Calendar.getInstance();
    public val date: Date = cal.time;
    public val log: String = "::[ $date ]::"

    fun hoover_start() {

        val recieve_thread = Thread {
            val sock = sock();
            val server = getserver();
            val serverarr: Array<String> = server.server_and_port();
            while(true) {
                val run = sock.socket_recievestring(serverarr[2], serverarr[3]);
            }
        }

    }

    fun signup() {
        print("\n--- HOOVER SIGNUP ---\nEnter a username: ");
        val uname = readLine();
        print("\nEnter a secure password: ");
        val passwd = readLine();
        val pass = passwd.toString();
        val hoover = hoovercrypt();
        val arr: Array<String> = hoover.hoover_encrypt_message_toData(pass);
        println(":: IMPORTANT :: THESE ARE YOUR PASSWORD KEYS, KEEP THEM IN A SAFE PLACE ::\n\nKEY1: " + arr[0] + "\nKEY2: " + arr[2] + "\n");
        File("user.config").appendText("\nUSER_NAME=$uname\n");
        File("user.config").appendText("\nPASSWORD=~" + arr[4] + "~\nENCKEY1=~" + arr[1] + "~\nENCKEY3=~" + arr[3] + "~\n");
        println("$log Finished hoover signup");
    }

    fun login(): Boolean {

        print("$log please login to hoover\n Please enter password key 1: ");
        val keys1 = readLine();
        val key1 = keys1.toString();

        print("\nPlease enter password key 2: ");
        val keys2 = readLine();
        val key2 = keys2.toString();

        val scan: BufferedReader = BufferedReader(FileReader("user.config"));
        var encrypt2_line: String = "";
        var enckey3_line: String = "";
        var enckey1_line: String = "";

        while(true) {
            val line: String = scan.readLine() ?: break;

            if(line.contains("PASSWORD=")) {
                encrypt2_line = line;
            } else if(line.contains("ENCKEY1=")) {
                enckey1_line = line;
            } else if(line.contains("ENCKEY3=")) {
                enckey3_line = line;
            }
        }
        scan.close();

        val st: StringTokenizer = StringTokenizer(encrypt2_line);
        val str1: String = st.nextToken("~");
        //println(str1);
        val encrypt2: String = st.nextToken("~")
        //println(encrypt2);

        val st2: StringTokenizer = StringTokenizer(enckey3_line);
        val str2: String = st2.nextToken("~");
        //println(str2);
        val enckey3: String = st2.nextToken("~");
        //println(enckey3);

        val st3: StringTokenizer = StringTokenizer(enckey1_line);
        val str3: String = st3.nextToken("~");
        //println(str3);
        val enckey1: String = st3.nextToken("~");
        //println(enckey1);

        val arr: Array<String> = arrayOf(
            key2,
            enckey1,
            key1,
            enckey3,
            encrypt2
        );

        /*File("data.log").appendText(
                arr[0] + "\n" +
                    arr[1] + "\n" +
                        arr[2] + "\n" +
                        arr[3] + "\n" +
                        arr[4] + "\n"
        );*/

        val data_log: File = File("data.log");

        val getserver = getserver();
        val serverarr: Array<String> = getserver.server_and_port();

        val sock = sock();
        //sock.socket_sendfile(serverarr[0], serverarr[1], data_log);
        sock.socket_sendstring(serverarr[0], serverarr[1],
            arr[0] + "~" + arr[1] + "~" + arr[2] + "~" + arr[3] + "~" + arr[4]
        );

        val success: Boolean = true;
        return success;
    }

}