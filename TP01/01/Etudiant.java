import java.util.Random;
import java.util.Scanner;

class Etudiant {
    private int nbNotes;
    private Note[] list;
    private String nom;
    private float avg;

    Etudiant(int nb, String nom) {
        this.nbNotes = nb;
        this.list = new Note[this.nbNotes];
        this.nom = nom;
        this.avg = 0;
        this.init();
    }

    Etudiant(int nb) {
        this.nbNotes = nb;
        this.list = new Note[this.nbNotes];
        this.nom = "Undefined";
        this.avg = 0;
        this.init();
    }

    Etudiant() {
        this.nbNotes = 0;
        this.nom = "Undefined";
        this.avg = 0;
        this.init();
    }

    private void init() {
        for (int i = 0; i < this.nbNotes; i++) {
            this.list[i] = new Note();
        }
    }

    //Saisie
    public void inputNotes() {
        Scanner s = new Scanner(System.in);
        for (int i = 0; i < this.nbNotes; i++) {
            System.out.println("Entrer nom de note " + i + ":");
            this.list[i].set_name(s.nextLine());
            System.out.println("Entrer valeur de note " + i + ":");
            this.list[i].set_value(Float.parseFloat(s.nextLine()));
            System.out.println("Entrer coefficient de note " + i + ":");
            this.list[i].set_coef(Integer.parseInt(s.nextLine()));
        }
        s.close();
        this.avg();
    }

    //Remplissage Rand
    public void inputRand(float min, float max) {
        Random r = new Random();
        for (int i = 0; i < this.nbNotes; i++) {
            this.list[i].set_value(r.nextFloat() * (max - min) + min);
            this.list[i].set_coef(r.nextInt(8) + 1);
        }
        this.avg();
    }

    public float get_avg() {
        return this.avg;
    }

    //Calculate Moyenne
    public void avg() {
        int sumCoef = 0;
        float sumNotes = 0;
        for (int i = 0; i < this.nbNotes; i++) {
            sumNotes += this.list[i].get_value() * this.list[i].get_coef();
            sumCoef += this.list[i].get_coef();
        }
        this.avg = (sumNotes / sumCoef);
    }

    public void print() {
        System.out.println(this.nom);
        for (int i = 0; i < this.nbNotes; i++) {
            this.list[i].print();
        }
        System.out.println("Avg: " + this.avg);
        System.out.println("");
    }
};
