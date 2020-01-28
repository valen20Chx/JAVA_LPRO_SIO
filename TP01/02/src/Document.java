class Document {
    protected String titre;

    /*
        Fonction: Document
        Type retour: Document
        Parametre: titre: String

        Constructeur de document, initialise le titre.
    */
    Document(String titre) {
        this.titre = titre;
    }

    /*
        Fonction: Document
        Type retour: Document

        Constructeur vide, titre <- "Untitled".
    */
    Document() {
        this.titre = "Untitled";
    }

    /*
        Fonction: set_title
        Type retour: void
        Parametre: titre: String

        Assigne le parametre titre au membre
    */
    public void set_titre(String titre) {
        this.titre = titre;
    }

    /*
        Fonction: get_title
        Type retour: String

        Retourne le titre.
    */
    public String get_titre() {
        return this.titre;
    }

    /*
        Fonction: print
        Type retour: void

        Affiche les informations du document.
    */
    public void print() {
        System.out.println("Titre: " + this.titre);
    }
};