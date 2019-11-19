import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Naloga2 {      // OVERHAND SHUFFLE

    public static Scanner in;

    public static void main(String[] args) throws FileNotFoundException {
        
        File file = new File(args[0]);

        in = new Scanner(file);

        String stevilke = in.next();
        String[] st = stevilke.split(",");
        int stKart = Integer.parseInt(st[0]);
        int stMesanj = Integer.parseInt(st[1]);

        String zacZaporedje = in.next();
        String[] karte = zacZaporedje.split(",");

        LinkedList kup = new LinkedList();

        kup.init(karte);
        printList(kup);


        for (int i = 0; i < stMesanj; i++) {
            String[] navodilo = in.next().split(",");

            /*
                1. korak: navodilo[0] = karta, ki kup razdeli na [...., navodilo[0]] in [... ostalo ...]
            */

            LinkedList kup1 = new LinkedList();
            Node prviKup1 = new Node(null);
            prviKup1 = kup1.first;
            
            LinkedList kup2 = new LinkedList();
            Node prviKup2 = kup2.first;


            Node iterator = kup.first.next;
            while (iterator != null && !iterator.card.equals(navodilo[0])) {
                //System.out.println(iterator.card);
                kup1.add(iterator.card);
                iterator = iterator.next;
            }

            if (iterator != null) {
                kup1.add(iterator.card);
                iterator = iterator.next;
            }

            while (iterator != null) {
                kup2.add(iterator.card);
                iterator = iterator.next;
            }
        }

    }

    public static void printList(LinkedList l) {
        Node iterator = l.first.next;
        while (iterator != null) {
            System.out.printf("%s", iterator.card);
            iterator = iterator.next;
            if (iterator != null) {
                System.out.printf(" "); 
            }
        }
        System.out.println();
    }
}

class LinkedList {
    protected Node first;

    public LinkedList() {
        first = null;
    }


    public void init(String[] a) {
        LinkedList list = new LinkedList();

        first = new Node();
        Node element = new Node(a[0], null);

        first.next = element;

        for (int i = 1; i < a.length; i++) {
            Node el = new Node(a[i], null);

            element.next = el;
            element = el;
        }
    }

    public void add(String card) {
        Node dodaj = new Node(card);
        
        Node it = first.next;
        while (it != null) {
            it = it.next;
        }
        it = dodaj;

    }
}

class Node {
    String card;
    Node next;

    public Node(String v) {
        card = v;
    }

    public Node(String v, Node nxt) {
        card = v;
        next = nxt;
    }

    public Node() {
        next = null;
    }
}
