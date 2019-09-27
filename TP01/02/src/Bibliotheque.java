import java.util.ArrayList;
import java.util.Scanner;

class Bibliotheque {
    private ArrayList<Document> liste;
    private int nb_doc;

    /*
        Fonction: Bibliotheque
        Type retour: Bibliotheque
        Parametre: nb_doc: int

        Constructeur de bibliotheque, cree liste de longueur nb_doc.
    */
    Bibliotheque(int nb_doc) {
        this.nb_doc = nb_doc;
        this.liste = new ArrayList<Document>(this.nb_doc);

        this.init_empty();
    }

    /*
        Fonction: Bibliotheque
        Type retour: Bibliotheque

        Constructeur vide, cree une liste vide.
    */
    Bibliotheque() {
        this.nb_doc = 0;
        this.liste = new ArrayList<Document>();
    }

    /*
        Fonction: init_empty
        Type retour: void

        Initialise la liste si sa taille a deja ete defini
    */
    private void init_empty() {
        for (int i = 0; i < this.nb_doc; i++) {
            this.liste.forEach((ele) -> ele = new Document());
        }
    }

    /*
        Fonction: input
        Type retour: void

        Interface de saisie de multiple documents.
    */
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
                case 1: // Livre
                    correct_input = true;
                    break;
                case 2: // Revue
                    correct_input = true;
                    break;
                case 3: // Dvd
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
                case 1: // Livre
                    System.out.println("Entrer Titre:");
                    newLivre.set_titre(s.nextLine());
                    System.out.println("Entrer Auteur:");
                    newLivre.set_auteur(s.nextLine());
                    System.out.println("Entrer Annee d'edition:");
                    newLivre.set_annee_edit(Integer.parseInt(s.nextLine()));
                    correct_input = true;
                    break;
                case 2: // Revue
                    System.out.println("Entrer Titre:");
                    newRevue.set_titre(s.nextLine());
                    System.out.println("Entrer Numero:");
                    newRevue.set_numero(Integer.parseInt(s.nextLine()));
                    System.out.println("Entrer Annee de publication:");
                    newRevue.set_annee_public(Integer.parseInt(s.nextLine()));
                    correct_input = true;
                    break;
                case 3: // Dvd
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
                case 1: // Livre
                    newLivre.print();
                    break;
                case 2: // Revue
                    newRevue.print();
                    break;
                case 3: // Dvd
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
            case 1: // Livre
                this.add(newLivre);
                break;
            case 2: // Revue
                this.add(newRevue);
                break;
            case 3: // Dvd
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

    /*
        Fonction: add
        Type retour: void
        Parametre: newDoc: Document

        Ajoute le document passe en parametre dans la liste.
    */
    private void add(Document newDoc) {
        this.liste.add(newDoc);
    }

    /*
        Fonction: print
        Type retour: void

        Affiche la liste des documents dans la biblioteche.
    */
    public void print() {
        this.liste.forEach((ele) -> ele.print());
        System.out.println("");
    }

    /*
        Fonction: search_doc_by_titre
        Type retour: Document
        Parametre: titre: String

        Cherche le document ayant le meme titre que celui
        passe en parametre.
    */
    public Document search_doc_by_titre(String titre) {
        for (int i = 0; i < this.liste.size(); i++) {
            if(this.liste.get(i).get_titre().equals(titre)) {
                return this.liste.get(i);
            }
        }
        return null;
    }

    /*
        Fonction: search_doc_by_auteur
        Type retour: Document
        Parametre: auteur: String

        Cherche le document ayant le meme auteur que celui
        passe en parametre.
    */
    public Document search_doc_by_auteur(String auteur) {
        for (int i = 0; i < this.liste.size(); i++) {
            if(this.liste.get(i) instanceof Livre)
            {
                if(((Livre)this.liste.get(i)).get_auteur().equals(auteur)) {
                    return this.liste.get(i);
                }
            } else if(this.liste.get(i) instanceof Dvd) {
                if(((Dvd)this.liste.get(i)).get_auteur().equals(auteur)) {
                    return this.liste.get(i);
                }
            }
        }
        return null;
    }
}