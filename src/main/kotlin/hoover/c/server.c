#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "files.h"

int main(int argc, char *argv[]) {

    if(!strcmp(argv[1], "login")) {
        file_manage();
    } else if(!strcmp(argv[1], "signup")) {
        printf("signup");
        signup_manage();
    }
    return 0;
}