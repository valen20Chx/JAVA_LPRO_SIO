JC = "javac"
OPT = "-d bin"

all : src/*.java
	${JC} src/*.java ${OPT}

run :
	cd bin
	java Main

launch-server :
	cd bin
	java MainServer

clean :
	rm bin/*.class