public class Main {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.add(new Livre("Mark Twain", "Tom Sawyer"));
        tree.add(new Livre("Herman Melville", "Mobby Dick"));
        tree.add(new Livre("Brian Kernighan and Dennis Ritchie", "The C Programming language"));
        tree.print();
        tree.saveToFile("res/Livres.dat");
    }
}