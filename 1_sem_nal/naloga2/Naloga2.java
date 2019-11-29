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

        kup.init();
        Node iter = kup.first;
        for (int i = 0; i < karte.length; i++) {
            iter.next = new Node(karte[i]);
            iter = iter.next;
        }

        for (int i = 0; i < stMesanj; i++) {
            String in_next = in.next();
            String[] navodilo = in_next.split(",");

            /*
                1. korak: navodilo[0] = karta, ki kup razdeli na [...., navodilo[0]] in [... ostalo ...]
            */

            kup1 = new LinkedList();
            kup1.init();
            Node it1 = kup1.first;

            kup2 = new LinkedList();
            kup2.init();
            Node it2 = kup2.first;

            Node iterator = kup.first;
            if (kup.inKup1(navodilo[0])) {
                // Karta navodilo[0] je v kupu -> razdeli kup na kup1 in kup2
                while (iterator.next != null && !iterator.next.card.equals(navodilo[0])) {
                    it1.next = new Node(iterator.next.card);
                    it1 = it1.next;
                    iterator = iterator.next;
                }
                it1.next = new Node(iterator.next.card);
                iterator = iterator.next;
                while (iterator.next != null) {
                    it2.next = new Node(iterator.next.card);
                    it2 = it2.next;
                    iterator = iterator.next;
                }

            } else {
                // Karte navodilo[0] ni v kupu, zato je kup2 = kup, kup1 pa ostane prazen
                kup2 = kup;
            }
            

            /*
                2. korak: navodilo[1] = mesto vstavljanja kup2 v kup1
            */
            int stVstavljanj = Integer.parseInt(navodilo[2]);
            //System.out.println("St vstavljanj: " + stVstavljanj);

            Node za = kup1.vrniNode(navodilo[1]);
            while (kup2.first.next != null) {
                int vstavljenih = 0;
                Node it_2 = kup2.first;
                Node iter_1 = za;
                Node temp = za.next;
                while (vstavljenih < stVstavljanj && it_2.next != null) {
                    // Prestavi jih navodilo[2] ali pa toliko da se izprazne kup2
                    iter_1.next = new Node(it_2.next.card);
                    vstavljenih++;
                    iter_1 = iter_1.next;
                    it_2.next = it_2.next.next;
                }
                iter_1.next = temp;
            }

            kup = kup1;

            
        }
        in.close();
        printList(kup);
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
            //System.out.printf("%s", iterator.card);
            
            izhod.printf("%s", iterator.card);
            iterator = iterator.next;
            if (iterator != null) {
                //System.out.printf(",");
                izhod.printf(",");
            }
        }
        izhod.println();
        //System.out.println();
    }
}

class LinkedList {
    protected Node first;

    public LinkedList() {
        first = null;
    }


    public void init() {
        LinkedList list = new LinkedList();

        first = new Node();
    }

    public void add(String card) {
        Node dodaj = new Node(card);
        
        Node it = first.next;
        while (it != null) {
            it = it.next;
        }
        it = dodaj;

    }

    public boolean inKup1(String value) {
        Node it = first;
        while (it.next != null) {
            if (it.next.card.equals(value)) {
                return true;
            }
            it = it.next;
        }
        return false;
    }

    public Node vrniNode(String val) {
        Node it = first;
        while (it.next != null && !it.next.card.equals(val)) {
            it = it.next;
        }
        if (it.next == null) {
            return first;
        }
        if (it.next.card.equals(val)) {
            // Nasli smo pravo karto -> ta je zadnja v seznamu
            return it.next;
        } else {
            return first;
        }
    }

    public void removeNode(String val) {
        Node it = first;
        while (it.next != null && !it.next.card.equals(val)) {
            it = it.next;
        }
        it.next = it.next.next;
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
