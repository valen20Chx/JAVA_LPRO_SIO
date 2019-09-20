import java.util.ArrayList;
import java.util.Scanner;

class Bibliotheque {
    private ArrayList<Document> liste;
    private int nb_doc;

    Bibliotheque(int nb_doc) {
        this.nb_doc = nb_doc;
        this.liste = new ArrayList<Document>(this.nb_doc);

        this.init_empty();
    }

    Bibliotheque() {
        this.nb_doc = 0;
        this.liste = new ArrayList<Document>();
    }

    private void init_empty() {
        for (int i = 0; i < this.nb_doc; i++) {
            this.liste.forEach((ele) -> ele = new Document());
        }
    }

    public void input() {
        Scanner s = new Scanner(System.in);
        s.reset();
        int docType = 0;
        int tempInput = 0;
        boolean correct_input = false;
        Livre newLivre = new Livre();
        Dvd newDvd = new Dvd();
        Revue newRevue = new Revue();

        while (!correct_input) {
            //Ask Type
            System.out.println("Choisir type: (1. Livre | 2. Revue | 3. DVD)");
            System.out.println("Entrer -1 pour sortir");
            docType = Integer.parseInt(s.nextLine());
            switch (docType) {
                case 1:
                    correct_input = true;
                    break;
                case 2:
                    correct_input = true;
                    break;
                case 3:
                    correct_input = true;
                    break;
                case -1:
                    correct_input = true;
                    break;
                default:
                    System.out.println("Valeur entre non correcte: " + docType);
                    break;
            }
        }
        correct_input = false;
        while (!correct_input) {
            switch (docType) {
                case 1: //Livre
                    System.out.println("Entrer Titre:");
                    newLivre.set_titre(s.nextLine());
                    System.out.println("Entrer Auteur:");
                    newLivre.set_auteur(s.nextLine());
                    System.out.println("Entrer Annee d'edition:");
                    newLivre.set_annee_edit(Integer.parseInt(s.nextLine()));
                    correct_input = true;
                    break;
                case 2: //Revue
                    System.out.println("Entrer Titre:");
                    newRevue.set_titre(s.nextLine());
                    System.out.println("Entrer Numero:");
                    newRevue.set_numero(Integer.parseInt(s.nextLine()));
                    System.out.println("Entrer Annee de publication:");
                    newRevue.set_annee_public(Integer.parseInt(s.nextLine()));
                    correct_input = true;
                    break;
                case 3: //DVD
                    System.out.println("Entrer Titre:");
                    newDvd.set_titre(s.nextLine());
                    System.out.println("Entrer Auteur:");
                    newDvd.set_auteur(s.nextLine());
                    System.out.println("Entrer Nombre de Titre:");
                    newDvd.set_nb_titres(Integer.parseInt(s.nextLine()));
                    correct_input = true;
                    break;
                case -1:
                    correct_input = true;
                    break;
                default:
                    System.out.println("Erreur, entre non reusit.");
                    correct_input = true;
                    break;
            }
        }
        correct_input = false;
        while(!correct_input) {
            System.out.println("Entrer fini, voulez vous comfirmer? (1. Oui | 0. Non)");
            switch(docType) {
                case 1:
                    newLivre.print();
                    break;
                case 2:
                    newRevue.print();
                    break;
                case 3:
                newDvd.print();
                    break;
                case -1:
                    correct_input = true;
                    break;
                default:
                    System.out.println("Erreur, entre non reusit.");
                    correct_input = true;
                    break;
            }
            tempInput = Integer.parseInt(s.nextLine());
            if(tempInput == 0) {
                s.close();
                return;
            } else if(tempInput == 1) {
                correct_input = true;
            }
        }

        switch(docType) {
            case 1:
                this.add(newLivre);
                break;
            case 2:
                this.add(newRevue);
                break;
            case 3:
            this.add(newDvd);
                break;
            case -1:
                break;
            default:
                System.out.println("Erreur, entre non reusit.");
                break;
        }

        //s.close();
    }

    private void add(Document newDoc) {
        this.liste.add(newDoc);
    }

    public void print() {
        this.liste.forEach((ele) -> ele.print());
        System.out.println("");
    }
}