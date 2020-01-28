class Main {
    public static void main(String[] args) {
        Liste l = new Liste();

        l.ajouter_debut(5);
        l.ajouter_debut(3);
        l.ajouter(6);

        l.afficher();
        
        System.out.println(l.count());
    }
};