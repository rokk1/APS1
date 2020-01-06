import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;

public class Naloga10 {


    public static PrintWriter izhod;
    public static Scanner in;
    public static int indeks = 0;
    public static int[] vrsta;
    public static void main(String[] args) throws FileNotFoundException, IOException {


        File file = new File(args[0]);

        izhod = new PrintWriter(new FileWriter(args[1]));

        in = new Scanner(file);

        String vozlisca = in.next();
        String[] nodes = vozlisca.split(",");
        int[] nds = new int[nodes.length];
        int[] nds2 = new int[nodes.length];
        vrsta = new int[nodes.length];

        for (int i = 0; i < nodes.length; i++) {
            nds[i] = Integer.parseInt(nodes[i]);
        }

        for (int i = 0; i < nds.length; i++) {
            System.out.printf("%d, ", nds[i]);
        }
        System.out.println();

        int index = findMax(nds, 0, nds.length);
        generateTree(nds, 0, nds.length - 1);

        for (int i = 0; i < nds.length; i++) {
            System.out.printf("%d, ", vrsta[i]);
        }
        System.out.println();

        System.out.printf("Max: %d ... Index: %d\n", nds[index], index);
    }

    /*
    public static void generateTree(int[] a, int start, int end) {
        if (start >= end || vnesenih == a.length) {
            rez[vnesenih] = a[start];
            System.out.printf("Vstavim %d na %d. mesto...\n", a[start], vnesenih);
            vnesenih++;
            return;
        }
        int rootIndex = findMax(a, start, end);
        rez[vnesenih] = a[rootIndex];
        vnesenih++;
        //System.out.printf("Iscem od %d do %d\n", start, r)
        System.out.printf("Vstavim %d na %d. mesto...\n", a[rootIndex], vnesenih);
        generateTree(a, start, rootIndex - 1);
        generateTree(a, rootIndex, end);
    }
    */

    public static void generateTree(int[] a, int start, int end) {
        if (indeks == a.length) {
            return;
        }
        if (start == end) {
            vrsta[indeks] = a[start];
            indeks++;
        }
        
    }

    public static int findMax(int[] a, int start, int end) {
        int max = Integer.MIN_VALUE;
        int index = -1;
        for (int i = start; i < end; i++) {
            if (a[i] > max) {
                max = a[i];
                index = i;
            }
        }
        return index;
    }
}

class Tree {
    Node root;

    public Tree(Node root) {
        this.root = root;
    }
}

class Node {
    int val;
    Node left;
    Node right;

    public Node(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}