import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Naloga1 {

    public static Scanner in;

    public static void main(String[] args) throws FileNotFoundException {
        
        File file = new File(args[0]);
        
        in = new Scanner(file);

        // READING INPUT DATA FROM FILE

        String wh = in.next();
        String[] a = wh.split(",");
        
        int stVrstic = Integer.parseInt(a[0]);
        int stStolpcev = Integer.parseInt(a[1]);

        char[][] arr = new char[stVrstic][stStolpcev];

        for (int i = 0; i < stVrstic; i++) {
            String line = in.next();
            
            String[] parsed = line.split(",");
            for (int j = 0; j < parsed.length; j++) {
                arr[i][j] = parsed[j].charAt(0);
            }
        }

        int stBesed = in.nextInt();
        String[] besede = new String[stBesed];

        for (int i = 0; i < stBesed; i++) {
            besede[i] = in.next();
        }


       print(arr);
        zunanja_zanka:
        for (int i = 0; i < besede.length; i++) {

            String word = besede[i];
            for (int k = 0; k < arr.length; k++) {
                for (int j = 0; j < arr[k].length; j++) {
                    if (findWord(besede[i], arr, 0, 0)) {
                        break zunanja_zanka;
                    }
                }
            }
        }


    }

    public static boolean findWord(String beseda, char[][] arr, int vrstica, int stolpec) {
        // Preveri trenutno pozicijo v arr[vrstica][stolpec], ce tam ni prve crke besede, potem vrni false
        if (arr[vrstica][stolpec] != beseda.charAt(0)) {
            return false;
        }
        int start_v = vrstica;
        int start_s = stolpec;
        
        // Isci po vseh smereh
        for (int smer = 0; smer < 8; smer++) {
            for (int k = 1; k < beseda.length(); k++) {
                // Preveri ce smo se v mejah krizanke
                if (vrstica + k >= arr.length || vrstica - k < 0 || stolpec + k >= arr[i].length || stolpec - k < 0) {
                    return false;
                }
                // Preveri ce je v
            }
        }

    }

    public static void print(char[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.printf("%c ", a[i][j]);
            }
            System.out.println();
        }
    }
}