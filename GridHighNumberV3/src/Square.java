
public class Square {
    private int val;
    private boolean hidden;

    Square(int val)
    {
        this.val = val;
        this.hidden = true;
    }

    public boolean is_hidden()
    {
        return this.hidden;
    }

    public int get_val()
    {
        return this.val;
    }

    public void discover()
    {
        this.hidden = false;
    }
}