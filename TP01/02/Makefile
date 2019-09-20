# NOT WORKING

JC = javac
CLASSES = \
	Main.class \
	Bibliotheque.class \
	Document.class \
	Livre.class \
	Revue.class \
	Dvd.class
SPATH = src/
BPATH = bin/
FLAGS = -g -d $(BPATH)

All: $(CLASSES)
	make Livre.class
	make Revue.class
	make Dvd.class
	make Main.class
	make Bibliotheque.class
	make Document.class

run:
	java Main

Main.class: $(SPATH)Main.java
	$(JC) $(FLAGS) $(SPATH)Main.java

Bibliotheque.class: Livre.java \
					Revue.java \
					Dvd.java \
					Document.java \
					Bibliotheque.java
	$(JC) $(FLAGS) $(SPATH)Bibliotheque.java

Document.class: $(SPATH)Document.java \
				$(SPATH)Livre.java \
				$(SPATH)Revue.java \
				$(SPATH)Dvd.java
	$(JC) $(FLAGS) $(SPATH)Document.java

Livre.class: $(SPATH)Livre.java
	$(JC) $(FLAGS) $(SPATH)Livre.java

Revue.class: $(SPATH)Revue.java
	$(JC) $(FLAGS) $(SPATH)Revue.java

Dvd.class: $(SPATH)Dvd.java
	$(JC) $(FLAGS) $(SPATH)Dvd.java

clean:
	rm $(CLASSES)