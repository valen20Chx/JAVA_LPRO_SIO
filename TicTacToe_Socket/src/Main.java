class Main {
    public static void main(String[] args) {
        int port = 3070;
        if(args.length == 0) {
            Grid maGrid = new Grid("localhost", port);
        } else {
            Grid maGrid = new Grid(args[1], port);
        }
    }
}