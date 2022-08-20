#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

void file_manage() {

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
                snprintf(buf, sizeof(buf), "users/%s.login", UNAME);
                FILE *done = fopen(buf, "a");
                fprintf(done, "LOGIN~%s~\nLOGIN_SUCCESS=true", UNAME);
                fclose(done);
            }
        }
        fclose(login);
}

int signup_manage() {

    FILE *signup = fopen("signups.log", "rw");

    if(signup != NULL) {
        char line[256];
        while(fgets(line, sizeof(line), signup) != NULL) {
            char search[] = "~";
            char *uname = strstr(line, search);
            if(uname != NULL) {
                uname++;
            }
            const char delim[] = "~";
            char *UNAME = strtok(uname, delim);

            char buf[0x100];
            UNAME[sizeof(uname)-1] = '\0';
            snprintf(buf, sizeof(buf), "users/%s.user", UNAME);
            printf("\n%s\n", buf);
            FILE *done = fopen(buf, "w");
            fprintf(done, "SIGNUP~%s~\n", UNAME);

            char *encrypt2 = strstr(uname, search);
            if(encrypt2 != NULL) {
                encrypt2++;
            }
            char *ENCRYPT2 = strtok(encrypt2, delim);
            ENCRYPT2[sizeof(ENCRYPT2)-1] = '\0';
            fprintf(done, "ENCRYPT2~%s~\n");

            char *enckey1 = strstr(encrypt2, search);
            if(enckey1 != NULL) {
                enckey1++;
            }
            char *ENCKEY1 = strtok(enckey1, delim);
            ENCKEY1[sizeof(ENCKEY1)-1] = '\0';
            fprintf(done, "ENCKEY1~%s~\n");

            char *enckey3 = strstr(enckey1, search);
            if(enckey3 != NULL) {
                enckey3++;
            }
            char *ENCKEY3 = strtok(enckey3, delim);
            ENCKEY3[sizeof(ENCKEY3)-1] = '\0';
            fprintf(done, "ENCKEY3~%s~\n", ENCKEY3);

            fclose(done);
        }
        fclose(signup);
        system("rm -f signups.log");
        return 0;
    }

}