import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;

public class Naloga8 {

    public static PrintWriter izhod;
    public static Scanner in;
    public static Tree tree;
    public static int[] rez = new int[2];
    // rez[0] = stPotopljenih
    // rez[1] = stObiskanih
    public static void main(String[] args) throws FileNotFoundException, IOException {

        File file = new File(args[0]);

        izhod = new PrintWriter(new FileWriter(args[1]));

        in = new Scanner(file);

        int a = in.nextInt();
        //System.out.println(a);

        int[][] map = new int[a][a];

        for (int i = 0; i < a; i++) {
            String line = in.next();
            //System.out.println(line.charAt(0));
            String[] nums = line.split(",");
            for (var j = 0; j < nums.length; j++) {
                map[i][j] = Integer.parseInt(nums[j]);
            }
        }

        //printMap(map);

        // Build a Tree
        int min = returnMin(map);
        int max = returnMax(map);
        //System.out.printf("MIN: %d ... MAX: %d\n", min, max);

        Node root = new Node(min, max, map.length * map.length);
        tree = new Tree(root);

        //System.out.printf("root min: %d ... root max: %d\n", tree.root.min, tree.root.max);
        int size = a;
        Node it = tree.root;

        makeTree(tree.root, map, size);
        int stGladin = in.nextInt();
        //System.out.println(stGladin);

        for (int i = 0; i < stGladin; i++) {
            int gladina = in.nextInt();
            //System.out.printf("%d. iteracija, gladina = %d\n", i + 1, gladina);
            
            count(tree.root, gladina);
            System.out.printf("stPotopljenih: %d ... stObiskanih: %d\n", rez[0], rez[1]);
            izhod.printf("%d,%d\n", rez[0], rez[1]);
            rez[0] = 0;
            rez[1] = 0;
        }
        in.close();
        izhod.close();

        //System.out.println(tree.root.leftDown.rightUp.max);
    }

    public static void count(Node nd, int gladina) {
        if (nd == null) {
            return;
        }

        if (gladina < nd.min) {
            // Potopljena ni nobena tocka tega vozlisca
            rez[1]++;
            //System.out.printf("stPotopljenih: %d ... Visited: %d\n", rez[0], rez[1]);
            return;
        }
        if (gladina >= nd.max) {
            // Potopljene so vse tocke tega vozlisca
            rez[1]++;
            rez[0] += nd.size;
            //System.out.printf("stPotopljenih: %d ... Visited: %d\n", rez[0], rez[1]);
            return;
        } else {
            rez[1]++;
            // Nekaj tock je potopljenih -> preglej sinove
            count(nd.leftUp, gladina);
            count(nd.rightUp, gladina);
            count(nd.leftDown, gladina);
            count(nd.rightDown, gladina);
            return;
        }
    }


    public static void makeTree(Node nd, int[][] map, int size) {
        if (nd.min == nd.max) {
            return;
        }
        //System.out.printf("Size: %d ... min: %d ... max: %d\n", size, nd.min, nd.max);
        if (size > 1) {
            int[][] tmp1 = new int[size/2][size/2];
            int[][] tmp2 = new int[size/2][size/2];
            int[][] tmp3 = new int[size/2][size/2];
            int[][] tmp4 = new int[size/2][size/2];
            
            // Left Up
            for (int i = 0; i < size/2; i++) {
                for (int j = 0; j < size/2; j++) {
                    tmp1[i][j] = map[i][j];
                }
            }

            int min1 = returnMin(tmp1);
            int max1 = returnMax(tmp1);
            
            Node leftUp = new Node(min1, max1, size/2 * size/2);
            nd.leftUp = leftUp;
            
            makeTree(nd.leftUp, tmp1, size/2);

            // Right up
            for (int i = 0; i < size/2; i++) {
                for (int j = 0; j < size/2; j++) {
                    tmp2[i][j] = map[i][j + size/2];
                }
            }

            int min2 = returnMin(tmp2);
            int max2 = returnMax(tmp2);
            
            Node rightUp = new Node(min2, max2, size/2 * size/2);
            nd.rightUp = rightUp;
            makeTree(nd.rightUp, tmp2, size/2);

            // Left Down
            for (int i = 0; i < size/2; i++) {
                for (int j = 0; j < size/2; j++) {
                    tmp3[i][j] = map[i + size/2][j];
                }
            }

            int min3 = returnMin(tmp3);
            int max3 = returnMax(tmp3);
            Node leftDown = new Node(min3, max3, size/2 * size/2);
            nd.leftDown = leftDown;
            
            makeTree(nd.leftDown, tmp3, size/2);

            // Right Down
            for (int i = 0; i < size/2; i++) {
                for (int j = 0; j < size/2; j++) {
                    tmp4[i][j] = map[i + size/2][j + size/2];
                }
            }

            int min4 = returnMin(tmp4);
            int max4 = returnMax(tmp4);
            Node rightDown = new Node(min4, max4, size/2 * size/2);
            nd.rightDown = rightDown;
            
            makeTree(nd.rightDown, tmp4, size/2);
        }
    }

    public static void printMap(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.printf("%d ", a[i][j]);
            }
            System.out.println();
        }
    }

    public static int returnMax(int[][] a) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] > max) {
                    max = a[i][j];
                }
            }
        }

        return max;
    }

    public static void printTree(Node a) {
        System.out.printf("min: %d ... max: %d\n", a.min, a.max);
        printTree(a.leftUp);
        printTree(a.rightUp);
        printTree(a.leftDown);
        printTree(a.rightDown);
    }

    public static int returnMin(int[][] a) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] < min) {
                    min = a[i][j];
                }
            }
        }

        return min;
    }

}

class Tree {
    Node root;

    public Tree(Node root) {
        this.root = root;
    }
}

class Node {
    int min;
    int max;
    int size;

    Node leftUp;
    Node rightUp;
    Node leftDown;
    Node rightDown;

    public Node(int min, int max, int size) {
        this.min = min;
        this.max = max;
        this.size = size;

        this.leftUp = null;
        this.leftDown = null;
        this.rightDown = null;
        this.rightUp = null;
    }
}