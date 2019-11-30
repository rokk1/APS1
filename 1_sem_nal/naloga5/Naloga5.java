import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;

public class Naloga5 {


    public static PrintWriter izhod;
    public static Scanner in;

    public static void main(String[] args) throws FileNotFoundException {
        
        File file = new File(args[0]);

        //izhod = new PrintWriter(new FileWriter(args[1]));

        in = new Scanner(file);

        String stevila1 = in.next();
        String[] stevila = stevila1.split(",");
        int stTrakov = Integer.parseInt(stevila[0]);
        Trak[] traki = new Trak[stTrakov];
        int dolzinaTrakov = Integer.parseInt(stevila[1]);


        for (int i = 0; i < stTrakov; i++) {
            // Preberi konfiguracijo posameznega traku
            String command = in.next();
            String[] ukaz = command.split(":");
            int idTraku = Integer.parseInt(ukaz[0]);
            traki[i] = new Trak(idTraku, dolzinaTrakov);
            if (ukaz.length > 1) {
                String[] vsebinaTraku = ukaz[1].split(",");
                for (int j = 0; j < vsebinaTraku.length; j++) {
                
                    traki[i].vsebina[j] = vsebinaTraku[j];
                }
            } else {
                // Trak je prazen
                
            }
            System.out.println("Trak: " + idTraku);
            traki[i].printTrak();
            
        }

        // Koncna konfiguracija traku
        Trak[] koncni = new Trak[stTrakov];
        for (int i = 0; i < stTrakov; i++) {
            String command = in.next();
            String[] ukaz = command.split(":");
            int idTraku = Integer.parseInt(ukaz[0]);

            koncni[i] = new Trak(idTraku, dolzinaTrakov);
            if (ukaz.length > 1) {
                String[] vsebinaTraku = ukaz[1].split(",");
                for (int j = 0; j < vsebinaTraku.length; j++) {
                
                    koncni[i].vsebina[j] = vsebinaTraku[j];
                }
            } else {
                // Trak je prazen
                
            }
            System.out.println("Trak: " + idTraku);
            koncni[i].printTrak();
        }

    }
}

class Trak {
    int stTraku;
    String[] vsebina;

    public Trak(int id, int dolzinaVsebine) {
        stTraku = id;
        vsebina = new String[dolzinaVsebine];
    }

    public void printTrak() {
        for (int i = 0; i < vsebina.length; i++) {
            System.out.printf("%s", vsebina[i]);
            if (i < vsebina.length - 1) {
                System.out.printf(",");
            }
        }
        System.out.println();
    }
}