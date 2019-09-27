class Revue extends Document {
    private int numero;
    private int annee_public;

    /*
        Fonction: Revue
        Type retour: Revue
        Parametre: numero: int, annee_public: int, titre: String

        Constructeur de Revue, initialise les membres
    */
    Revue(int numero, int annee_public, String titre) {
        super(titre);
        this.numero = numero;
        this.annee_public = annee_public;
    }

    /*
        Fonction: Revue
        Type retour: Revue

        Constructeur vide de Revue, initialise les membres a 0
    */
    Revue() {
        super();
        this.numero = -1;
        this.annee_public = 0;
    }

    /*
        Fonction: get_numero
        Type retour: int

        Retourne le membre get_numero de la Revue
    */
    public int get_numero() {
        return this.numero;
    }

    /*
        Fonction: get_annee_public
        Type retour: int

        Retourne le membre annee_public de la Revue
    */
    public int get_annee_public() {
        return this.annee_public;
    }

    /*
        Fonction: set_numero
        Type retour: void
        Parametre: numero: int

        Assigne le parametre numero au membre numero de la Revue
    */
    public void set_numero(int numero) {
        this.numero = numero;
    }

    /*
        Fonction: set_annee_public
        Type retour: void
        Parametre: annee_public: int

        Assigne le parametre annee_public au membre annee_public de la Revue
    */
    public void set_annee_public(int annee_public) {
        this.annee_public = annee_public;
    }

    /*
        Fonction: print
        Type retour: void

        Affiche les informations de la Revue
    */
    public void print() {
        System.out.println("Titre: " + this.titre);
        System.out.println("Numero: " + this.numero);
        System.out.println("Annee de publication: " + this.annee_public);
    }
};