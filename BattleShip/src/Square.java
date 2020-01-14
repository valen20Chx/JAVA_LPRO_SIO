
public class Square {
    private boolean boat;
    private boolean bombed;

    Square(boolean boat)
    {
        this.boat = boat;
        this.bombed = false;
    }

    public boolean getBoat() {
        return this.boat;
    }

    public boolean isBombed() {
        return this.bombed;
    }

    public void boomb() {
        this.bombed = true;
    }
}