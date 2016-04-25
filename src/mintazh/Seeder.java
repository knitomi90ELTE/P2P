package mintazh;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Seeder {

    private int port;
    private PrintWriter pw;
    private final String separator = ";";
    
    public Seeder(int port) {
        this.port = port;
        try {
            Socket client = new Socket("localhost", this.port);
            pw = new PrintWriter(client.getOutputStream(), true);
            pw.println("localhost" + separator + this.port);
        } catch (IOException ex) {
            
        }
        new Thread(){
            @Override
            public void run() {
                try {
                    ServerSocket seederServer = new ServerSocket(port);
                    Socket leecher = seederServer.accept();
                    PrintWriter leecherStream = new PrintWriter(leecher.getOutputStream(), true);
                    leecherStream.println("ZH");
                    leecherStream.close();
                    leecher.close();
                } catch (IOException ex) {
                    
                }
            }
            
        }.start();
    }

    public static void main(String[] args) {
        new Seeder(11111);
    }

}
