import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Naloga4 {


    public static Scanner in;

    public static void main(String[] args) throws FileNotFoundException {


        File file = new File(args[0]);

        in = new Scanner(file);

        int stUkazov = in.nextInt();

        System.out.println(stUkazov);

        for (int i = 0; i < stUkazov; i++) {
            String line = in.nextLine();
            System.out.println(line);
        }
    }
}

class Bag {
    protected Node first;

    public Bag() {
        first = null;
    }

    

    public void ustvari(String[] a) {
        Bag list = new LinkedList();

        first = new Node(null);
        Node element = new Node(Integer.parseInt(a[0]), null);

        first.next = element;

        for (int i = 1; i < a.length; i++) {
            Node el = new Node(Integer.parseInt(a[i]), null);

            element.next = el;
            element = el;
        }
    }
}

class Node {
    int value;
    Node next;

    public Node(int v, Node nxt) {
        value = v;
        next = nxt;
    }

    public Node(Node nxt) {
        next = nxt;
    }
}