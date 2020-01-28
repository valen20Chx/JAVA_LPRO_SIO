class Livre extends Document {
    private String auteur;
    private int annee_edit;

    /*
        Fonction: Livre
        Type retour: Livre
        Parametre: auteur: String, annee_edit: int, titre: String

        Contructeur de Livre, initialise les membres.
    */
    Livre(String auteur, int annee_edit, String titre) {
        super(titre);
        this.auteur = auteur;
        this.annee_edit = annee_edit;
    }

    /*
        Fonction: Livre
        Type retour: Livre

        Contructeur de Livre vide, initialise les membres a 0.
    */
    Livre() {
        super();
        this.auteur = "Unknown";
        this.annee_edit = 0;
    }

    /*
        Fonction: get_auteur
        Type retour: String
        
        Retourne l'auteur du Livre
    */
    public String get_auteur() {
        return this.auteur;
    }

    /*
        Fonction: get_annee_edit
        Type retour: int

        Retourne l'annee d'edition du livre
    */
    public int get_annee_edit() {
        return this.annee_edit;
    }

    /*
        Fonction: set_auteur
        Type retour: void
        Parametre: auteur: String

        Assigne le parametre auteur au membre auteur du Livre
    */
    public void set_auteur(String auteur) {
        this.auteur = auteur;
    }

    /*
        Fonction: set_annee_edit
        Type retour: void
        Parametre: auteur: int

        Assigne le parametre annee_edit au membre annee_edit du Livre
    */
    public void set_annee_edit(int annee_edit) {
        this.annee_edit = annee_edit;
    }

    /*
        Fonction: print
        Type retour: void

        Affiche les information du Livre
    */
    public void print() {
        System.out.println("Titre: " + this.titre);
        System.out.println("Auteur: " + this.auteur);
        System.out.println("Annee d'edition: " + this.annee_edit);
    }
};