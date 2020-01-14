import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.annotation.ElementType;

public class BinaryTree implements Serializable {

    private static final long serialVersionUID = 1L;
    private BinaryTree left;
    private BinaryTree right;
    private Livre element;

    public BinaryTree() {
        this.element =null;
        this.left = null;
        this.right = null;
    }
    
    public BinaryTree(Livre element) {
        this.element = element;
        this.left = null;
        this.right = null;
    }

    public Livre getElement() {
        return this.element;
    }

    public void setElement(Livre element) {
        this.element = element;
    }

    public BinaryTree add(Livre newElement) {
        BinaryTree temp = this;
        boolean exit = false;
        while(!exit) {
            if(temp.element == null) {
                temp.element = newElement;
                exit = true;
            }
            if(newElement.compareTo(temp.element) == 0) {
                exit = true;
            } else if(newElement.compareTo(temp.element) > 0) {
                if(temp.right != null) {
                    temp = temp.right;
                } else {
                    temp.right = new BinaryTree(newElement);
                    exit = true;
                }
            } else if(newElement.compareTo(temp.element) < 0) {
                if(temp.left != null) {
                    temp = temp.left;
                } else {
                    temp.left =  new BinaryTree(newElement);
                    exit = true;
                }
            }
        }
        return null;
    }

    public boolean search(BinaryTree node) {
        BinaryTree temp = this;
        while((temp.left != null && this.right != null)) {
            if(temp == null) {
                return false;
            }
            if(node.element.compareTo(temp.element) == 0) {
                return true;
            } else if(node.element.compareTo(temp.element) > 0) {
                if(temp.right != null) {
                    temp = temp.right;
                } else {
                    return false;
                }
            } else if(node.element.compareTo(temp.element) < 0) {
                if(temp.left != null) {
                    temp = temp.left;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public void print() {
        BinaryTree temp = this;
        if(temp.left != null) {
            temp.left.print();
        }
        if(temp.element != null) {
            temp.element.print();
        }
        if(temp.right != null) {
            temp.right.print();
        }
    }

    public void saveToFile(String filePath) {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filePath));
            os.writeObject(this);
        } catch(IOException e) {
            System.out.println("Error ObjectOutputStream: " + e.getMessage());
        }
    }

    static public BinaryTree loadFromFile(String filePath) {
        BinaryTree temp;
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(filePath));
            temp = (BinaryTree) is.readObject();
            return temp;
        } catch(IOException e) {
            System.out.println("Error ObjectOutputStream: " + e.getMessage());
        } catch(ClassNotFoundException e) {
            System.out.println("Error ClassNotFoundExeption: " + e.getMessage());
        }
        return null;
    }

    public void saisir(int n) {
        for (int i = 0; i < n; i++) {
            Livre newElement = new Livre();
            newElement.saisir();
            this.add(newElement);
        }
    }
}
