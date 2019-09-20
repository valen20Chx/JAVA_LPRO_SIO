class Main {
    public static void main(String[] args) {
        Bibliotheque maBiblio = new Bibliotheque(0);
        
        maBiblio.input();

        maBiblio.print();

        if(maBiblio.search_doc("Clio") != null) {
            maBiblio.search_doc("Clio").print();
        }
    }
}