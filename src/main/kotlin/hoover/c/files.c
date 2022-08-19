#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void file_manage(int argc, char *argv[]) {

        FILE *user_in = fopen("data.log", "rw");
        FILE *user_list = fopen("user_list.log", "rw");
        FILE *login = fopen(logins.log);

        if(user_in != NULL) {

        } else if(user_list != NULL) {

        } else if(user_in == NULL && user_list == NULL) {
            continue;
        }
        fclose(user_list);
        fclose(user_in);

}