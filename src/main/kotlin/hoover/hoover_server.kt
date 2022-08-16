package hoover

import java.util.Date
import java.util.Calendar
import sockets.server_side


class hoover_server {
    fun hoover_start() {
        val hoover_socket_thread = Thread {
            val server = server_side();
            server.server_main();
        }
        hoover_socket_thread.start();
        val cal: Calendar = Calendar.getInstance();
        val date: Date = cal.time;
        println("::[ $date ]:: started server thread: " + hoover_socket_thread.name);
    }

}