import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Naloga1 {

    public static Scanner in;

    public static int[] tmp = new int[2];

    public static void main(String[] args) throws FileNotFoundException {
        
        File file = new File(args[0]);
        
        in = new Scanner(file);

        // READING INPUT DATA FROM FILE

        String wh = in.next();
        String[] a = wh.split(",");
        
        int stVrstic = Integer.parseInt(a[0]);
        int stStolpcev = Integer.parseInt(a[1]);

        char[][] arr = new char[stVrstic][stStolpcev];
        int[][] visited = new int[stVrstic][stStolpcev];

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
        for (int i = 0; i < besede.length; i++) {
            System.out.println(besede[i]);
        }

        String[][] rez = new String[stBesed][5];
        int najdenih = 0;

        

    for (int i = 0; i < rez.length; i++) {
        System.out.printf("%s,%s,%s,%s,%s\n", rez[0], rez[1], rez[2], rez[3], rez[4]);
    }

    public static boolean solve(String[] besede, int zaporednaBeseda, char[][] arr, int[][] visited) {
        if (zaporednaBeseda >= besede.length) {
            return true;
        }
        return solve(besede, zaporednaBeseda++, arr, visited);
        
    }

    public static boolean poisciBesedo(String beseda, int indexCrke, char[][] arr, int[][] visited) {

    }

    public static boolean findWord(String beseda, char[][] arr, int vrstica, int stolpec, int[][] zasedeno) {
        // Preveri trenutno pozicijo v arr[vrstica][stolpec], ce tam ni prve crke besede, potem vrni false
        if (arr[vrstica][stolpec] != beseda.charAt(0) || zasedeno[vrstica][stolpec] == 1) {
            return false;
        }
        int start_v = vrstica;
        int start_s = stolpec;
        
        zasedeno[vrstica][stolpec] = 1;
        
        if (findHorizontal(beseda, arr, start_v, start_s, zasedeno)) {
            System.out.println("Najdeno horizontalno!");
            System.out.printf("Beseda: %s, start_v: %d, start_s: %d\n", beseda, start_v, start_s);
            print2(zasedeno);
            return true;
        }
        if (findHorizontalNeg(beseda, arr, start_v, start_s, zasedeno)) {
            System.out.println("Najdeno horizontalno v neg. smer!");
            System.out.printf("Beseda: %s, start_v: %d, start_s: %d\n", beseda, start_v, start_s);
            print2(zasedeno);
            return true;
        }
        if (findVertical(beseda, arr, start_v, start_s, zasedeno)) {
            System.out.println("Najdeno vertikalno!");
            System.out.printf("Beseda: %s, start_v: %d, start_s: %d\n", beseda, start_v, start_s);
            print2(zasedeno);
            return true;
        }
        if (findVerticalNeg(beseda, arr, start_v, start_s, zasedeno)) {
            System.out.println("Najdeno vertikalno v neg. smer!");
            System.out.printf("Beseda: %s, start_v: %d, start_s: %d\n", beseda, start_v, start_s);
            print2(zasedeno);
            return true;
        }
        if (findDiagonal(beseda, arr, start_v, start_s, zasedeno)) {
            System.out.println("Najdeno diagonalno!");
            System.out.printf("Beseda: %s, start_v: %d, start_s: %d\n", beseda, start_v, start_s);
            print2(zasedeno);
            return true;
        }
        if (findDiagonalNeg(beseda, arr, start_v, start_s, zasedeno)) {
            System.out.println("Najdeno diagonalno v neg. smer!");
            System.out.printf("Beseda: %s, start_v: %d, start_s: %d\n", beseda, start_v, start_s);
            print2(zasedeno);
            return true;
        }


        zasedeno[vrstica][stolpec] = 0;
        return false;
    }

    public static void print(char[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.printf("%c ", a[i][j]);
            }
            System.out.println();
        }
    }

    public static void print2(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.printf("%d ", a[i][j]);
            }
            System.out.println();
        }
    }

    public static boolean findHorizontal(String beseda, int indexBesede, char[][] arr, int vrstica, int stolpec, int[][] zasedeno) {
        if (indexBesede >= beseda.length()) {
            return true;
        }
        if (stolpec + beseda.length() > arr.length) {
            return false;
        }
        if (arr[vrstica][stolpec] == beseda.charAt(indexBesede)) {
            zasedeno[vrstica][stolpec] = 1;
            return findHorizontal(beseda, indexBesede++, arr, vrstica + 1, stolpec, zasedeno)
        } else {
            
        }
    }

    public static boolean findVertical(String beseda, char[][] arr, int vrstica, int stolpec, int[][] zasedeno) {
        if (vrstica + beseda.length() > arr[0].length) {
            return false;
        }
        for (int i = 1; i < beseda.length(); i++) {
            if (zasedeno[vrstica + i][stolpec] == 1) {
                return false;
            } else {
                if (arr[vrstica + i][stolpec] == beseda.charAt(i)) {
                    zasedeno[vrstica + i][stolpec] = 1;
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean findDiagonal(String beseda, char[][] arr, int vrstica, int stolpec, int[][] zasedeno) {
        if (vrstica + beseda.length() > arr[0].length || stolpec + beseda.length() > arr.length) {
            return false;
        }
        for (int i = 1; i < beseda.length(); i++) {
            if (zasedeno[vrstica + i][stolpec + i] == 1) {
                return false;
            } else {
                if (arr[vrstica + i][stolpec + i] == beseda.charAt(i)) {
                    zasedeno[vrstica + i][stolpec + i] = 1;
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean findDiagonalNeg(String beseda, char[][] arr, int vrstica, int stolpec, int[][] zasedeno) {
        if (vrstica - beseda.length() < 0 || stolpec - beseda.length() < 0) {
            return false;
        }
        for (int i = 1; i < beseda.length(); i++) {
            if (zasedeno[vrstica - i][stolpec - i] == 1) {
                return false;
            } else {
                if (arr[vrstica - i][stolpec - i] == beseda.charAt(i)) {
                    zasedeno[vrstica - i][stolpec - i] = 1;
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean findVerticalNeg(String beseda, char[][] arr, int vrstica, int stolpec, int[][] zasedeno) {
        if (vrstica - beseda.length() < 0) {
            return false;
        }
        for (int i = 1; i < beseda.length(); i++) {
            if (zasedeno[vrstica - i][stolpec] == 1) {
                return false;
            } else {
                if (arr[vrstica - i][stolpec] == beseda.charAt(i)) {
                    zasedeno[vrstica - i][stolpec] = 1;
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean findHorizontalNeg(String beseda, char[][] arr, int vrstica, int stolpec, int[][] zasedeno) {
        if (stolpec - beseda.length() < 0) {
            return false;
        }
        for (int i = 1; i < beseda.length(); i++) {
            if (zasedeno[vrstica][stolpec - i] == 1) {
                return false;
            } else {
                if (arr[vrstica][stolpec - i] == beseda.charAt(i)) {
                    zasedeno[vrstica][stolpec - i] = 1;
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}