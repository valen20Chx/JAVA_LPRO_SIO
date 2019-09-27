import java.util.Hashtable;
import java.util.Vector;
import java.util.Scanner;

class Promotion {
    private Vector<Etudiant> listeEtud;
    private Hashtable<Etudiant, String> optionHash;

    Promotion() {
        this.listeEtud = new Vector<Etudiant>();
        this.optionHash = new Hashtable<Etudiant, String>();
    }

    // Permet de saisir un etudiant et entrer dans le vecteur et la hashtable
    public void saisirEtud() {
        Etudiant newEtudiant = new Etudiant();

        System.out.println("Saisir Etudiant " + this.listeEtud.size() + " : ");
        newEtudiant.saisir();

        listeEtud.add(newEtudiant);

        System.out.println("Entrer Option:");
        Scanner s = new Scanner(System.in);
        optionHash.put(newEtudiant, s.nextLine());
        //s.close(); // Exception "No new line"
    }

    // Afficher promotion
    public void afficher() {
        for (int i = 0; i < listeEtud.size(); i++) {
            listeEtud.get(i).print();
            System.out.println("Option: " + optionHash.get(listeEtud.get(i)));
            System.out.println("");
        }
    }

    // Affiche le meilleur de la promotion
    public void afficherMeilleur() {
        if(this.listeEtud.size() > 0) {
            Etudiant meilleur = this.listeEtud.get(0);
            for (int i = 1; i < this.listeEtud.size(); i++) {
                if(this.listeEtud.get(i).get_moyenne() > meilleur.get_moyenne()) {
                    meilleur = this.listeEtud.get(i);
                }
            }
            System.out.println("Meilleur = ");
            meilleur.print();
            System.out.println("Option: " + optionHash.get(meilleur));
            System.out.println("");
        }
    }

    //Supprimer un Etudiant
    public boolean suprimer(String nom) {
        boolean estSup = false;
        for (int i = 0; i < this.listeEtud.size(); i++) {
            if(this.listeEtud.get(i).get_nom().equals(nom)) {
                Scanner s = new Scanner(System.in);
                int inputChoice = 2;
                while(inputChoice != 1 && inputChoice != 0) {
                    System.out.println("Voulez vous suprimer: (1: oui | 0: non)");
                    this.listeEtud.get(i).print();
                    inputChoice = Integer.parseInt(s.nextLine());
                    switch (inputChoice) {
                        case 1:
                            this.optionHash.remove(this.listeEtud.get(i));
                            this.listeEtud.remove(this.listeEtud.get(i));
                            System.out.println("Supression reussit");
                            estSup = true;
                            break;
                        case 0:
                            System.out.println("Supression annule");
                            estSup = true;
                            break;
                    }
                }
            }
        }
        return estSup;
    }

    // Autre modifications
    public void edit() {
        Scanner s = new Scanner(System.in);
        int choice;
        boolean exit = false;
        while(!exit) {
            System.out.println("Choisir operation:");
            System.out.println("1. Modifier Option");
            System.out.println("");
            
            choice = Integer.parseInt(s.nextLine());
            switch(choice) {
                case 1:
                    /*System.out.println("Entrer nom a changer");
                    Strin tempStr = s.nextLine();
                    this.optionHash.replace(this.chercher(nom), oldValue, newValue);
                    break;*/
                case 0:
                    exit = true;
                    break;
            }
        }
    }
    
    // Recherche etudiant
    private Etudiant chercher(String nom) {
        for (int i = 0; i < this.listeEtud.size(); i++) {
            if(this.listeEtud.get(i).get_nom().equals(nom)) {
                return this.listeEtud.get(i);
            }
        }
        return null;
    }
}