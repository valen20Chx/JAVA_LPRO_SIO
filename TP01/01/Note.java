class Note {
    private float value;
    private int coef;
    private String name;

    Note() {
        this.value = 0;
        this.coef = 1;
        this.name = "Undefined";
    }

    Note(String name) {
        this.name = name;
    }

    Note(float value, int coef, String name) {
        this.value = value;
        this.coef = coef;
        this.name = name;
    }

    public void set_value(float value) {
        this.value = value;
    }

    public void set_coef(int coef) {
        this.coef = coef;
    }

    public void set_name(String name) {
        this.name = name;
    }

    public float get_value() {
        return this.value;
    }

    public int get_coef() {
        return this.coef;
    }

    public String get_name() {
        return this.name;
    }

    public void print() {
        System.out.println(this.name + " : " + this.value + " * " + this.coef + " = " + (this.value * this.coef));
    }
    
};