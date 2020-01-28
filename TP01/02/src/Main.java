class Main {
    public static void main(String[] args) {
        Bibliotheque maBiblio = new Bibliotheque(0);
        
        maBiblio.input();
        maBiblio.input();

        Document tempDoc = maBiblio.search_doc_by_titre("maison");
        if(tempDoc != null) {
            tempDoc.print();
        }

        tempDoc = maBiblio.search_doc_by_auteur("valentin");
        if(tempDoc != null) {
            tempDoc.print();
        }
    }
}