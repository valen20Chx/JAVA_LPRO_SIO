import java.io.Serializable;
import java.util.Scanner;

public class Livre implements Serializable {

    private static final long serialVersionUID = 1L;
    private String auteur;
    private String titre;

    public Livre() {
        this.auteur = null;
        this.titre = null;
    }

    public Livre(String auteur, String titre) {
        this.auteur = auteur;
        this.titre = titre;
    }

    public void print() {
        System.out.println("Auteur: " + this.auteur);
        System.out.println("Titre: " + this.titre);
    }

    public int compareTo(Livre other) {
        if(this.auteur.compareTo(other.auteur) != 0) {
            return this.auteur.compareTo(other.auteur);
        } else {
            return this.titre.compareTo(other.titre);
        }
    }

    public void saisir() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrer le nom de l'auteur: ");
        this.auteur = scanner.nextLine();
        System.out.println("Entrer le titre du livre: ");
        this.titre = scanner.nextLine();
        scanner.close();
    }
}