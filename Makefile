
.PHONY: build clean

build: P1.class P2.class P3.class P4.class

run-p1:
	java Numarare
run-p2:
	java Trenuri
run-p3:
	java Drumuri
run-p4:
	java -Xss2m P4

P1.class: Numarare.java
	javac $^
P2.class: Trenuri.java
	javac $^
P3.class: Drumuri.java
	javac $^
P4.class: P4.java
	javac $^

clean:
	rm -f *.class
