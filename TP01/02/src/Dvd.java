class Dvd extends Document {
    private String auteur;
    private int nb_titres;
    
    /*
        Fonction: Dvd
        Type retour: Dvd
        Parametre: auteur: String, nb_titres: int, titre: String

        Constructeur de Dvd, initialise les membres
    */
    Dvd(String auteur, int nb_titres, String titre) {
        super(titre);
        this.auteur = auteur;
        this.nb_titres = nb_titres;
    }

    /*
        Fonction: Dvd
        Type retour: Dvd

        Constructeur de Dvd vide, initialise les membres a 0
    */
    Dvd() {
        super();
        this.auteur = "Unknown";
        this.nb_titres = 0;
    }

    /*
        Fonction: get_auteur
        Type retour: String

        Retourne l'auteur du Dvd.
    */
    public String get_auteur() {
        return this.auteur;
    }

    /*
        Fonction: get_nb_titres
        Type retour: int

        Retourne le nombre de titres du Dvd
    */
    public int get_nb_titres() {
        return this.nb_titres;
    }

    /*
        Fonction: set_auteur
        Type retour: void
        Parametre: auteur: String

        Assigne le parametre auteur au membre auteur de Dvd
    */
    public void set_auteur(String auteur) {
        this.auteur = auteur;
    }

    /*
        Fonction: set_nb_titres
        Type retour: void
        Parametre: nb_titres: int

        Assigne le parametre nb_titres au membre nb_titres de Dvd
    */
    public void set_nb_titres(int nb_titres) {
        this.nb_titres = nb_titres;
    }

    /*
        Fonction: print
        Type retour: void

        Affiche les informations du Dvd
    */
    public void print() {
        System.out.println("Titre: " + this.titre);
        System.out.println("Auteur: " + this.auteur);
        System.out.println("Nombre de Titres: " + this.nb_titres);
    }
}