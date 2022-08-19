package user

import java.io.BufferedReader
import java.io.FileReader
import java.util.*

class getuser {

    fun username(): String {

        val scan: BufferedReader = BufferedReader(FileReader("user.config"));
        var user_name: String = "";

        while(true) {
            val line: String = scan.readLine() ?: break;

            if(line.contains("USER_NAME=~")) {
                user_name = line;
            }
        }

        val st: StringTokenizer = StringTokenizer(user_name);
        val str1: String = st.nextToken("~");
        val UNAME: String = st.nextToken("~");

        return UNAME;

    }

}