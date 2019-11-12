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


        // BEGIN
        int start_i = -1;
        int start_j = -1;
        int end_i = -1;
        int end_j = -1;
        print(arr);
        
        for (int i = 0; i < besede.length; i++) {
            // FIND EVERY WORD INTO PUZZLE
        }

    }

    public static void find() {
        
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