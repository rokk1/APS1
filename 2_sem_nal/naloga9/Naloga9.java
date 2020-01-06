import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;

public class Naloga9 {
 
    public static int stSkupin;
    public static PrintWriter izhod;
    public static Scanner in;

    public static void main(String[] args) throws FileNotFoundException, IOException {

        File file = new File(args[0]);

        izhod = new PrintWriter(new FileWriter(args[1]));

        in = new Scanner(file);

        int stTock = in.nextInt();
        stSkupin = stTock;

        double[][] arr = new double[stTock][4];

        // Primer: Točka z ID = 1, X = -4.12, Y = 2.86 -> prva iteracija
        // [0][0] = 1 (ID)
        // [0][1] = -4.12 (X)
        // [0][2] = 2.86 (Y)
        // [0][3] = 1 (ID grupe)

        double id = 1;
        for (int i = 0; i < stTock; i++) {
            
            String coords = in.next();
            String[] koordinateTocke = coords.split(",");

            arr[i][0] = id;
            arr[i][1] = Double.parseDouble(koordinateTocke[0]);
            arr[i][2] = Double.parseDouble(koordinateTocke[1]);
            arr[i][3] = id;
            id++;

            System.out.printf("ID: %.2f  X: %f  Y: %f  ID GRUPE: %.2f\n", arr[i][0], arr[i][1], arr[i][2], arr[i][3]);
        }

        int k = in.nextInt();

        System.out.printf("max: %d\n", returnMax(arr));
        while (stSkupin > k) {

        }
        System.out.printf("K: %d\n", k);

    }

    public static int returnMax(double[][] a) {
        double max = Double.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (a[i][3] > max) {
                max = a[i][3];
            }
        }
        String rez = Double.toString(max);
        System.out.println(rez);
        String[] r = rez.split(".");
        System.out.println(r.length);
        return Integer.parseInt(r[0]);
    }

}