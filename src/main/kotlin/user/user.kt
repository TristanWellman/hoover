package user

import encrypt.hoovercrypt
import hoover.hoover_server
import hoover.hoover_user
import log.hoover_log
import sockets.server_side
import sockets.sock
import java.io.File
import java.io.FileInputStream
import java.util.Scanner

class user {

    val log = hoover_log();

    public fun user_init() {

        val user_config: File = File("user.config");

        val real: Boolean = user_config.exists();

        if(real == true) {
            user_config_init(user_config)
        } else if (real == false) {

            println("New user detected\nare you a (s)erver or a (u)ser?\n");

            val UOS = readLine();

            if (UOS == "s") {
                File("user.config").appendText("\n\nUOS=SERVER\nSERVER_TYPE=NORMAL\n");
                println("Enter IP address for port1: ");
                val ip = readLine();
                File("user.config").appendText("SERVER_IP=~$ip~\n");
                log.log_info("Enter the desired port for traffic: ");
                val port = readLine();
                File("user.config").appendText("SERVER_PORT=~$port~\n");
                log.log_info("Enter IP addresss for port2: ");
                val ip2 = readLine();
                File("user.config").appendText("SERVER_IP2=~$ip2~\n");
                log.log_info("Enter the desired port for receiving: ");
                val port2 = readLine();
                File("user.config").appendText("SERVER_PORT2=~$port2~\n");
                log.log_info("Finished server user config!\n");
                val sock = server_side();
                sock.server_main();
            }  else if(UOS == "u") {

                File("user.config").appendText("\n\nUOS=USER\n");
                File("user.config").appendText(
                    "SERVER_IP=~isbn-dining.at.playit.gg~\n" +
                            "SERVER_PORT=~28609~\n" +
                            "SERVER_IP2=~property-workshop.at.playit.gg~\n" +
                            "SERVER_PORT2=~28610~"
                );

                val hoover_user = hoover_user();
                hoover_user.signup();

                /*println("Encryption test & decryption test: \n\n");

                val input = "Hello World!";
                println("Input text: " + input + "\n");

                val hoover = hoovercrypt();
                val arr: Array<String> = hoover.hoover_encrypt_message_toData(input);

                println(arr[0] + "\n" + arr[1] + "\n" + arr[2] + "\n" + arr[3] + "\n" + arr[4] + "\n");
                File("data.log").appendText("\n\t" + arr[1] + "\n", Charsets.UTF_8);
                File("data.log").appendText(arr[2] + "\n\t" + arr[0] + "\n\t" + arr[3] + "\n\n", Charsets.UTF_8);

                println("\nDecrypting data...\n\n");
                val decrypt = hoover.hoover_decrypt(arr);
                println(decrypt + "\n");*/

                user_config_init(user_config);

                /*val socket = sock();
                socket.socket_start_data_log_test("isbn-dining.at.playit.gg", 28609);*/
            }
        }

    }

    private fun user_config_init(file: File) {
        val scan: Scanner = Scanner(file);
        while(scan.hasNextLine()) {
            val scann = scan.nextLine();
            if(scann == "UOS=SERVER") {
                val hoover = hoover_server();
                hoover.hoover_start();
            } else if(scann == "UOS=USER") {
                val hoover = hoover_user();
                val success: Boolean = hoover.login();
                if(success == true) {
                    hoover.hoover_start();
                }
            }
        }
    }


}