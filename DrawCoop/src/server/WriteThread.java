import java.net.Socket;
import java.util.Vector;
import java.awt.Point;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriteThread extends Thread {
    private Socket socket;
    private Vector<Point> outputPointBuffer;
    private boolean running;

    public WriteThread(Socket socket) {
        this.socket = socket;
        this.outputPointBuffer = new Vector<Point>();
        this.running = true;
    }

    public void run() {
        while (this.running) {
            if(this.outputPointBuffer.size() > 0) {
                try {
                    
                    ObjectOutputStream os = new ObjectOutputStream(this.socket.getOutputStream());
                    os.writeObject(this.outputPointBuffer.firstElement());
                    this.outputPointBuffer.removeElementAt(0);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public void pushPoint(Point p) {
        if(p != null) {
            this.outputPointBuffer.add(p);
        }
    }
}