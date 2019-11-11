import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Naloga1 {

    public static Scanner in;

    public static void main(String[] args) throws FileNotFoundException {
        
        File file = new File(args[0]);
        
        in = new Scanner(file);

        String wh = in.next();
        String[] a = wh.split(",");
        
        int width = Integer.parseInt(a[0]);
        int height = Integer.parseInt(a[1]);
        System.out.println(width);
        System.out.println(height);

        char[][] arr = new char[width][height];

        for (int i = 0; i < width; i++) {
            String line = in.nextLine();
            String[] lineArr = line.split(",");
            for (int j = 0; j < height; j++) {
                arr[i][j] = lineArr[j].charAt(0);
            }
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                System.out.printf("%c, ", arr[i][j]);
            }
            System.out.println();
        }
    }
}