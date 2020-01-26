import java.net.Socket;
import java.util.Vector;
import java.awt.Point;
import java.awt.Color;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriteThread extends Thread {
    private Socket socket;

    private Vector<Point> outputPointBuffer;
    private Vector<NetFlag> outputFlagBuffer;
    private Vector<Integer> outputBrushBuffer;
    private Vector<Color> outputColorBuffer;
    private Vector<String> outputStringBuffer;

    private boolean running;

    public WriteThread(Socket socket) {
        this.socket = socket;
        this.outputPointBuffer = new Vector<Point>();
        this.outputBrushBuffer = new Vector<Integer>();
        this.outputColorBuffer = new Vector<Color>();
        this.outputFlagBuffer = new Vector<NetFlag>();
        this.outputStringBuffer = new Vector<String>();
        this.running = true;
    }

    public void run() {
        while (this.running) {
            try {
                if(this.outputFlagBuffer.size() > 0) {
                    DataOutputStream os = new DataOutputStream(this.socket.getOutputStream());
                    NetFlag flag = this.outputFlagBuffer.firstElement();
                    
                    // Send Flag
                    os.writeInt(this.outputFlagBuffer.firstElement().getValue());
                    this.outputFlagBuffer.removeElementAt(0);

                    // Then Send values
                    switch(flag) {
                        case QUIT:
                            this.running = false;
                            break;
                        case POINT:
                            if(this.outputPointBuffer.size() > 0) {
                                this.writePoint(os, this.outputPointBuffer.firstElement());
                                this.outputPointBuffer.removeElementAt(0);
                            }
                            break;
                        case COLOR:
                            if(this.outputColorBuffer.size() > 0) {
                                this.writeColor(os, this.outputColorBuffer.firstElement());
                                this.outputColorBuffer.removeElementAt(0);
                            }
                            break;
                        case STRING:
                            if(this.outputStringBuffer.size() > 0) {
                                this.writeString(os, this.outputStringBuffer.firstElement());
                                this.outputStringBuffer.removeElementAt(0);
                            }
                            break;
                        case BRUSH:
                            if(this.outputBrushBuffer.size() > 0) {
                                this.writeInt(os, this.outputBrushBuffer.firstElement());
                                this.outputBrushBuffer.removeElementAt(0);
                            }
                            break;
                        default:
                            System.out.println("NetFlag sending unknowed: " + flag);
                            break;
                    }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void close() {
        this.running = false;
    }

    public void pushPoint(Point p) {
        if(p != null) {
            this.outputPointBuffer.add(p);
        }
    }
    
    public void pushFlag(NetFlag value) {
        if(value != null) {
            this.outputFlagBuffer.add(value);
        }
    }

    public void pushBrush(Integer value) {
        if(value != null) {
            this.outputBrushBuffer.add(value);
        }
    }
    
    public void pushColor(Color value) {
        if(value != null) {
            this.outputColorBuffer.add(value);
        }
    }
    
    public void pushString(String value) {
        if(value != null) {
            this.outputStringBuffer.add(value);
        }
    }
    
    private void writeInt(DataOutputStream os, int value) throws IOException {
        os.writeInt(value);
    }

    private void writePoint(DataOutputStream os, Point value) throws IOException {
        os.writeInt(value.x);
        os.writeInt(value.y);
    }

    private void writeString(DataOutputStream os, String value) throws IOException {
        os.writeInt(value.length());
        for (int i = 0; i < value.length(); i++) {
            os.writeChar(value.charAt(i));
        }
    }

    private void writeColor(DataOutputStream os, Color value) throws IOException {
        os.writeInt(value.getRGB());
    }
}