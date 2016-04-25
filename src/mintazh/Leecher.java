package mintazh;

import java.io.IOException;
import java.net.Socket;

public class Leecher {

    private final int PORT = 11111;
    Socket leecher;

    public Leecher() {
        try {
            leecher = new Socket("localhost", PORT);
            
            
        } catch (IOException ex) {
            
        }
        
    }

    public static void main(String[] args) {
        new Leecher();
    }

}
