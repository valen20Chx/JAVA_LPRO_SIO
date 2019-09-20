class Document {
    protected String titre;

    Document(String titre) {
        this.titre = titre;
    }

    Document() {
        this.titre = "Untitled";
    }

    public void set_titre(String titre) {
        this.titre = titre;
    }

    public String get_titre() {
        return this.titre;
    }

    public void print() {
        System.out.println("Titre: " + this.titre);
    }
};