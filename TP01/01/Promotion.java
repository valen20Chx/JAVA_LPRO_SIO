class Promotion {
    private Etudiant[] list;
    private int nb;

    Promotion(int nb) {
        this.nb = nb;
        this.list = new Etudiant[this.nb];
        this.init(0);
    }

    Promotion(int nb, int nbNotes) {
        this.nb = nb;
        this.list = new Etudiant[this.nb];
        this.init(nbNotes);
    }

    private void init(int nbNotes) {
        for (int i = 0; i < this.nb; i++) {
            this.list[i] = new Etudiant(nbNotes);
        }
    }

    public void fillRandAll(float min, float max) {
        for (int i = 0; i < this.nb; i++) {
            this.list[i].inputRand(min, max);
        }
    }

    public void displayAll() {
        for (int i = 0; i < this.nb; i++) {
            this.list[i].print();
            System.out.println("");
        }
    }

    //Meilleur Note + Nom
    public void printTop() {
        Etudiant top = list[0];
        for (int i = 1; i < this.nb; i++) {
            if(this.list[i].get_avg() > top.get_avg()) {
                top = this.list[i];
            }
        }
        System.out.println("TOP:");
        top.print();
    }
};