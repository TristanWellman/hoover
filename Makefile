CC= gcc
CFLAGS= -lpthread -g
NAME= hoover_cserver
KOTLIN= ./gradlew
KFLAGS= build
COMMON_C= src/main/kotlin/hoover/c/server.c src/main/kotlin/hoover/c/files.c

base:
	$(CC) $(CFLAGS) -L src/main/kotlin/hoover/c -c $(COMMON_C)
	$(CC) $(CFLAGS) -o c_server $(COMMON_C)
run:
	java -jar hoover.jar