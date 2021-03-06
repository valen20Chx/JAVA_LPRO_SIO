import java.awt.Point;

public class MainServer {
    public static void main(String[] args) {
        Server mServer = new Server(3070);
        int player = 1;
        while(!mServer.isClosed(1) && !mServer.isClosed(2)) {
            Point play = mServer.readPlay(player);
            if(player == 1) {
                player = 2;
            } else {
                player = 1;
            }
            if(!mServer.isClosed(player)) {
                mServer.sendPlay(play, player);
            }
        }
    }
}