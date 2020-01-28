class Matrice {

    private int cote;
    private int[][] mat; 

    Matrice(int cote) {
        this.cote = cote;

        this.mat = new int[cote][cote];
    }

    public void init() {
        for(int i = 0; i < this.cote; i++) {
            for (int j = 0; j < this.cote; j++) {
                this.mat[i][j] = 0;
            }
        }
    }

    public void afficher() {
        for(int i = 0; i < this.cote; i++) {
            for (int j = 0; j < this.cote; j++) {
                System.out.print("[" + mat[j][i] + "] ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public void fill1() {
        for (int i = 0; i < this.cote; i++) {
            for (int j = 0; j < i; j++) {
                this.mat[i][j] = i;
            }
            for (int k = i; k >= 0; k--) {
                this.mat[k][i] = i;
            }
        }
    }

    public void fill2() {
        for(int i = 0; i < this.cote; i++) {
            for (int j = 0; j < i + 1; j++) {
                this.mat[i][j] = i;
            }
            for (int k = i + 1; k < this.cote; k++) {
                this.mat[i][k] = k;
            }
        }
    }

    public void fill3() {
        for(int i = 0; i < this.cote; i++) {
            for (int j = 0; j < i + 1; j++) {
                this.mat[j][i] = i;
            }
            for (int k = i + 1; k < this.cote; k++) {
                this.mat[k][i] = k;
            }
        }
    }
};
