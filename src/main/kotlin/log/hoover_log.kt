package log

import java.util.*

class hoover_log {

    public val cal: Calendar = Calendar.getInstance();
    public val date: Date = cal.time;
    public val log: String = "::[ $date ]::"

    public val RESET = "\u001B[0m";
    public val RED = "\u001B[31m";
    public val YELLOW = "\u001B[33m";
    public val GREEN = "\u001B[32m";

    fun log_info(string: String) {
        println("$log" + "$GREEN Info::$RESET" + " $string");
    }

    fun log_error(string: String) {
        println("$log" + "$RED ERROR::$RESET" + " $string");
    }

    fun log_warning(string: String) {
        println("$log" + "$YELLOW ERROR::$RESET" + " $string");
    }

}