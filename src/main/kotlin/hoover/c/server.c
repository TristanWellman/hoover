#include <stdio.h>
#include <stdlib.h>

void file_manage() {
    FILE *user_list = fopen("user_list.log", "rw");

    for(;;) {
        FILE *user_in = fopen("", "rw");
    }
}

int main(int argc, char *argv[]) {
    file_manage();
    return 0;
}