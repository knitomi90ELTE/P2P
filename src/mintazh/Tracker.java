package mintazh;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tracker {

    private ServerSocket server;
    private final int PORT = 11111;
    private List<Data> seeders;

    public Tracker() {
        seeders = new ArrayList<>();
        try {
            server = new ServerSocket(PORT);
        } catch (IOException ex) {
            System.out.println("Szerver inicializálás sikertelen.");
        }
    }

    public void handleClients() {
        while (true) {
            try {
                Socket s = server.accept();
                PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
                Scanner sc = new Scanner(s.getInputStream());
                if (s.getClass().equals(Seeder.class)) {
                    System.out.println("Seeder csatlakozott");
                    String fromSeeder = sc.nextLine();
                    String[] spl = fromSeeder.split(";");
                    Data d = new Data(spl[0],Integer.parseInt(spl[1]));
                    seeders.add(d);
                    System.out.println("Seeder csatlakozott");
                    System.out.println("Seeder küldte: " + d.toString());
                    
                } else {
                    System.out.println("Leecher csatlakozott");
                    pw.println(seeders.get(0).host + seeders.get(0).port);
                }
                pw.close();
                sc.close();
                s.close();

            } catch (IOException e) {
                System.out.println("SERVER-LOG: Hiba a kliensek fogadasakor.");
                break;
            }
        }
    }

    private static class Data {

        public String host;
        public int port;

        public Data(String host, int port) {
            this.host = host;
            this.port = port;
        }

        @Override
        public String toString() {
            return "port: " + port + ", host " + host;
        }
        
        

    }

    public static void main(String[] args) {
        new Tracker();
    }

}
