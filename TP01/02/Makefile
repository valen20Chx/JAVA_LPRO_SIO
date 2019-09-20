JC = javac
FLAGS = -g
CLASSES = \
	Main.class \
	Bibliotheque.class \
	Document.class \
	Livre.class \
	Revue.class \
	Dvd.class

All: $(CLASSES)
	make Main.class
	make Bibliotheque.class
	make Document.class
	make Livre.class
	make Revue.class
	make Dvd.class

run:
	java Main

Main.class: Main.java
	$(JC) $(FLAGS) Main.java

Bibliotheque.class: Livre.java \
					Revue.java \
					Dvd.java \
					Bibliotheque.java \
					Document.java
	$(JC) $(FLAGS) Bibliotheque.java

Document.class: Document.java \
				Livre.java \
				Revue.java \
				Dvd.java
	$(JC) $(FLAGS) Document.java

Livre.class: Livre.java
	$(JC) $(FLAGS) Livre.java

Revue.class: Revue.java
	$(JC) $(FLAGS) Revue.java

Dvd.class: Dvd.java
	$(JC) $(FLAGS) Dvd.java

clean:
	rm $(CLASSES)