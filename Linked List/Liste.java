class Liste {
    Maillon debut;
    
    Liste() {
        debut = null;
    }

    void ajouter(int val) {

        if(debut == null) {
            debut = new Maillon(val, debut);
        }
        else {
            Maillon tempList = debut;

            while(tempList.suiv != null) {
                tempList = tempList.suiv;
            }
            tempList.suiv = new Maillon(val, null);
        }
    }

    void ajouter_debut(int val) {
        debut = new Maillon(val, debut);
    }

    boolean supprimer(Maillon supMaillon) {
        if(supMaillon == debut) {
            debut = debut.suiv;
        }
        else {
            Maillon tempMaillon = debut;
            while(tempMaillon.suiv != supMaillon || tempMaillon.suiv == null) {
                tempMaillon = tempMaillon.suiv;
            }
            if(tempMaillon.suiv != null) {
                tempMaillon.suiv = tempMaillon.suiv.suiv;
                //Delete supMaillon
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    boolean estVide() {
        if(debut == null) {
            return true;
        }
        else {
            return false;
        }
    }

    int count() {
        int count = 0;
        Maillon tempMaillon = debut;
        while(tempMaillon != null) {
            count++;
            tempMaillon = tempMaillon.suiv;
        }
        return count;
    }

    void afficher() {
        Maillon tempMaillon = debut;
        while(tempMaillon != null) {
            System.out.println(tempMaillon.valeur);
            tempMaillon = tempMaillon.suiv;
        }
    }
};