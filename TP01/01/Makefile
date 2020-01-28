JC = javac
FLAGS = -g
CLASSES = \
	Main.class \
	Etudiant.class \
	Note.class \
	Promotion.class \
	Matrice.class

All: $(CLASSES)
	make Main.class
	make Etudiant.class
	make Note.class
	make Promotion.class
	make Matrice.class

run:
	java Main

Main.class: Main.java
	$(JC) $(FLAGS) Main.java

Etudiant.class: Etudiant.java
	$(JC) $(FLAGS) Etudiant.java

Note.class: Note.java
	$(JC) $(FLAGS) Note.java

Promotion.class: Promotion.java
	$(JC) $(FLAGS) Promotion.java

Matrice.class: Matrice.java
	$(JC) $(FLAGS) Matrice.java

clean:
	rm $(CLASSES)