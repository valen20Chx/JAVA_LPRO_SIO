class Revue extends Document {
    private int numero;
    private int annee_public;

    Revue(int numero, int annee_public, String titre) {
        super(titre);
        this.numero = numero;
        this.annee_public = annee_public;
    }

    Revue() {
        super();
        this.numero = -1;
        this.annee_public = 0;
    }

    public int get_numero() {
        return this.numero;
    }

    public int get_annee_public() {
        return this.annee_public;
    }

    public void set_numero(int numero) {
        this.numero = numero;
    }

    public void set_annee_public(int annee_public) {
        this.annee_public = annee_public;
    }

    public void print() {
        System.out.println("Titre: " + this.titre);
        System.out.println("Numero: " + this.numero);
        System.out.println("Annee de publication: " + this.annee_public);
    }
};