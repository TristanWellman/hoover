package hoover

import java.util.Date
import java.util.Calendar
import sockets.server_side

import log.hoover_log

class hoover_server {

    val log = hoover_log();

    fun hoover_start() {
        val hoover_reciever_thread = Thread {
            val server = server_side();
            server.server_logins();
        }
        val hoover_socket_thread = Thread {
            val server = server_side();
            server.server_main();
        }
        /*val choover_thread = Thread {
            Runtime.getRuntime().exec("./c_server");
        }*/
        //hoover_socket_thread.start();
        //log.log_info("started server_socket thread: " + hoover_socket_thread.name);
        /*choover_thread.start();
        log.log_info("started c_server thread: " + choover_thread.name);*/
        hoover_reciever_thread.start();
        log.log_info("started hoover reciever thread: " + hoover_reciever_thread.name);
    }

}