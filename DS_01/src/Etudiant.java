import java.util.Scanner;

class Etudiant {
    private String nom;
    private double[] notes;
    private double moyenne;
    static int nbNotes = 3;

    Etudiant() {
        this.nom = "Undefined";
        this.notes = new double[nbNotes];
        this.moyenne = 0;
        for(int i = 0; i < nbNotes; i++) {
            this.notes[i] = 0;
        }
    }

    Etudiant(String nom) {
        this.nom = nom;
        this.notes = new double[nbNotes];
        this.moyenne = 0;
        for(int i = 0; i < nbNotes; i++) {
            this.notes[i] = 0;
        }
    }

    public void set_notes(double note1, double note2, double note3) {
        this.notes[0] = note1;
        this.notes[1] = note2;
        this.notes[3] = note3;

        this.avg();
    }

    public void set_notes(double[] notes) {
        if(notes.length == 3) {
            for (int i = 0; i < nbNotes; i++) {
                this.notes[i] = notes[i];
            }
        }
        this.avg();
    }

    private void avg() {
        this.moyenne = 0;
        for (int i = 0; i < nbNotes; i++) {
            this.moyenne += this.notes[i];
        }
        this.moyenne /= nbNotes;
    }

    public void print() {
        for (int i = 0; i < nbNotes - 1; i++) {
            System.out.println("Note " + i + " = " + this.notes[i]);
        }
        System.out.println("Option = " + this.notes[nbNotes - 1]);
    }

    public void saisir() {
        Scanner s = new Scanner(System.in);

        System.out.println("Saisir nom : ");
        this.nom = s.nextLine();

        for (int i = 0; i < nbNotes - 1; i++) {
            System.out.println("Saisir notes[" + i + "] : ");
            this.notes[i] = Integer.parseInt(s.nextLine());
        }
        System.out.println("Saisir option : ");
        this.notes[nbNotes - 1] = Integer.parseInt(s.nextLine());
        
        //s.close(); // Exception "No new line"
    }

    public double[] get_notes() {
        return this.notes;
    }

    public double get_note(int i) {
        return this.notes[i];
    }

    public void set_nom(String nom) {
        this.nom = nom;
    }

    public String get_nom() {
        return this.nom;
    }

    public double get_moyenne() {
        return this.moyenne;
    }
}

