class Livre extends Document {
    private String auteur;
    private int annee_edit;

    Livre(String auteur, int annee_edit, String titre) {
        super(titre);
        this.auteur = auteur;
        this.annee_edit = annee_edit;
    }

    Livre() {
        super();
        this.auteur = "Unknown";
        this.annee_edit = 0;
    }

    public String get_auteur() {
        return this.auteur;
    }

    public int get_annee_edit() {
        return this.annee_edit;
    }

    public void set_auteur(String auteur) {
        this.auteur = auteur;
    }

    public void set_annee_edit(int annee_edit) {
        this.annee_edit = annee_edit;
    }

    public void print() {
        System.out.println("Titre: " + this.titre);
        System.out.println("Auteur: " + this.auteur);
        System.out.println("Annee d'edition: " + this.annee_edit);
    }
};