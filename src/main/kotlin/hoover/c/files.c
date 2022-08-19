#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

void file_manage(int argc, char *argv[]) {

        FILE *login = fopen("logins.log", "rw");

        if(login != NULL) {
            char line[256];
            int line_num;
            while(fgets(line, sizeof(line), login) != NULL) {
                char search[] = "~";
                char *uname = strstr(line, search);
                if(uname != NULL) {
                    uname++;
                }

                const char delim[] = "~";
                char *UNAME = strtok(uname, delim);

                char buf[0x100];
                UNAME[sizeof(uname)-1] = '\0';
                snprintf(buf, sizeof(buf), "user/%s.login", UNAME);
                FILE *done = fopen(buf, "a");
                fprintf(done, "UNAME=~%s~\nLOGIN_SUCCESS=true", UNAME);
                fclose(done);
            }
        }
        fclose(login);
}