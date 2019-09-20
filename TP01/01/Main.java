class Main {
    public static void main(String[] args) {
        Promotion myPromotion = new Promotion(4, 3);

        myPromotion.fillRandAll(5, 15);

        myPromotion.displayAll();

        myPromotion.printTop();
    }
};
