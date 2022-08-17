CC= gcc
CFLAGS= -lpthread -g
NAME= hoover_cserver
KOTLIN= ./gradlew
KFLAGS= build
COMMON_C= src/main/kotlin/hoover/c/server.c src/main/kotlin/hoover/c/files.c

base:
	$(CC) $(CFLAGS) -L src/main/kotlin/hoover/c -c $(COMMON_C)
	$(CC) $(CFLAGS) -o c_server $(COMMON_C)
	$(KOTLIN) $(KFLAGS)
run:
	java -javaagent:/usr/share/idea/lib/idea_rt.jar=39539:/usr/share/idea/bin -Dfile.encoding=UTF-8 -classpath build/classes/kotlin/main:~/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib-jdk8/1.7.10/d70d7d2c56371f7aa18f32e984e3e2e998fe9081/kotlin-stdlib-jdk8-1.7.10.jar:~/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib-jdk7/1.7.10/1ef73fee66f45d52c67e2aca12fd945dbe0659bf/kotlin-stdlib-jdk7-1.7.10.jar:/home/wt/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib/1.7.10/d2abf9e77736acc4450dc4a3f707fa2c10f5099d/kotlin-stdlib-1.7.10.jar:/home/wt/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib-common/1.7.10/bac80c520d0a9e3f3673bc2658c6ed02ef45a76a/kotlin-stdlib-common-1.7.10.jar:/home/wt/.gradle/caches/modules-2/files-2.1/org.jetbrains/annotations/13.0/919f0dfe192fb4e063e7dacadee7f8bb9a2672a9/annotations-13.0.jar MainKt
