class Dvd extends Document {
    private String auteur;
    private int nb_titres;
    
    Dvd(String auteur, int nb_titres, String titre) {
        super(titre);
        this.auteur = auteur;
        this.nb_titres = nb_titres;
    }

    Dvd() {
        super();
        this.auteur = "Unknown";
        this.nb_titres = 0;
    }

    public String get_auteur() {
        return this.auteur;
    }

    public int get_nb_titres() {
        return this.nb_titres;
    }

    public void set_auteur(String auteur) {
        this.auteur = auteur;
    }

    public void set_nb_titres(int nb_titres) {
        this.nb_titres = nb_titres;
    }

    public void print() {
        System.out.println("Titre: " + this.titre);
        System.out.println("Auteur: " + this.auteur);
        System.out.println("Nombre de Titres: " + this.nb_titres);
    }
}