import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;

public class Naloga2 {      // OVERHAND SHUFFLE

    public static Scanner in;
    public static LinkedList kup1;
    public static LinkedList kup2;
    public static PrintWriter izhod;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        File file = new File(args[0]);

        izhod = new PrintWriter(new FileWriter(args[1]));
        in = new Scanner(file);

        String stevilke = in.next();
        String[] st = stevilke.split(",");
        int stKart = Integer.parseInt(st[0]);
        int stMesanj = Integer.parseInt(st[1]);

        String zacZaporedje = in.next();
        String[] karte = zacZaporedje.split(",");

        LinkedList kup = new LinkedList();

        kup.init(karte);


        for (int i = 0; i < stMesanj; i++) {
            String[] navodilo = in.next().split(",");

            /*
                1. korak: navodilo[0] = karta, ki kup razdeli na [...., navodilo[0]] in [... ostalo ...]
            */

            kup1 = new LinkedList();
            kup1.first = new Node();
            Node it1 = kup1.first;
            

            kup2 = new LinkedList();
            kup2.first = new Node();
            Node it2 = kup2.first;

            Node iterator = kup.first.next;
            while (iterator != null && !iterator.card.equals(navodilo[0])) {
                //System.out.println(iterator.card);
                it1.next = new Node(iterator.card, null);
                it1 = it1.next;
                iterator = iterator.next;
            }

            if (iterator != null) {
                it1.next = new Node(iterator.card, null);
                iterator = iterator.next;
            }

            while (iterator != null) {
                it2.next = new Node(iterator.card, null);
                it2 = it2.next;
                iterator = iterator.next;
            }


            if (kup2.first.next == null) {
                // zamenjaj kup1 in kup2
                kup2 = new LinkedList();
                kup2.first = new Node();
                it2 = kup2.first;

                it1 = kup1.first;
                while (it1.next != null) {
                    it2.next = new Node(it1.next.card, null);
                    it1 = it1.next;
                    it2 = it2.next;
                }

                kup1 = new LinkedList();
                kup1.first = new Node();
            }
            

            /*
                2. korak: navodilo[1] = mesto vstavljanja kup2 v kup1
            */
            

            while (kup2.first.next != null) {
                insert(Integer.parseInt(navodilo[2]), navodilo[1]);
            }
            kup = kup1;
        }
        in.close();
        printList(kup1);
        izhod.close();
    }

    public static void insert(int count, String nav) {
        // Find nav in kup1
        Node it1 = kup1.first;
        while (it1.next != null && !it1.next.card.equals(nav)) {
            it1 = it1.next;
        }
        
        if (it1.next != null) {
            it1 = it1.next;
        }
        if (it1.next == null) {
            // Nismo nasli karte, vstavi na zacetek kup1!
            it1 = kup1.first;
        }
        Node temp = it1.next;
        Node it2 = kup2.first;
        while (it2.next != null && count > 0) {
            Node novi = new Node(it2.next.card, null);
            it2.next = it2.next.next;
            it1.next = novi;
            it1 = it1.next;
            count--;
        }
        it1.next = temp;
    }

    public static void printList(LinkedList l) {
        Node iterator = l.first.next;
        while (iterator != null) {
            System.out.printf("%s", iterator.card);
            izhod.printf("%s", iterator.card);
            iterator = iterator.next;
            if (iterator != null) {
                System.out.printf(",");
                izhod.printf(",");
            }
        }
        izhod.println();
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
