package src;

import java.util.Scanner;

class Menu {
    private Promotion promo;

    Menu() {
        this.promo = new Promotion();

        this.loop();
    }

    private void loop() {
        boolean exit = false;
        Scanner s = new Scanner(System.in);
        int choice;
        while(!exit) {
            System.out.println("Choisir Operation sur la promotion:");
            System.out.println("1. Ajouter Etudiant");
            System.out.println("2. Supprimer Etudiant");
            System.out.println("3. Afficher les Etudiants");
            System.out.println("4. Afficher meilleur");
            System.out.println("5. Modifier Etudiant");
            System.out.println("0. Quiter");
            
            choice = Integer.parseInt(s.nextLine());

            switch(choice) {
                case 1:
                    this.promo.saisirEtud();
                    break;
                case 2:
                    System.out.println("Entrer le nom de l'Etudiant:");
                    s = new Scanner(System.in);
                    if(!this.promo.suprimer(s.nextLine())) {
                        System.out.println("Etudiant non trouve");
                    }
                    break;
                case 3:
                    this.promo.afficher();
                    break;
                case 4:
                    this.promo.afficherMeilleur();
                    break;
                case 5:
                    this.promo.edit();
                    break;
                case 0:
                    exit = true;
                    break;
            }
            System.out.println("");
        }
    }
}