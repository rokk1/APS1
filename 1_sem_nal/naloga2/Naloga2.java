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

        LinkedList cards = new LinkedList();
        Node iterator = new Node(karte[0]);
        cards.first = iterator;
        

        for (int i = 1; i < karte.length; i++) {
            iterator.next = new Node(karte[i]);
            iterator = iterator.next;
        }

        printList(cards);

        while (in.hasNext()) {
            String directions = in.next();
            String[] navodila = directions.split(",");

            /*
                navodila = [
                    2E,       karta, ki razdeli kup
                    1A,       mesto vstavljanja v kup 1
                    3         stevilo kart, ki se vstavijo v 1 iteraciji postopka
                ]
            */

            LinkedList kup1 = new LinkedList();
            Node kup1First = kup1.first;
            LinkedList kup2 = new LinkedList();
            Node kup2First = kup2.first;
            Node it = cards.first;

            while (iterator != null && !it.card.equals(navodila[0])) {
                kup1First = new Node(it.card);
                System.out.println(kup1First.card);
                kup1First = kup1First.next;
                it = it.next;
            }

            printList(kup1);

        }
    }

    public static void printList(LinkedList l) {
        Node iterator = l.first;
        while (iterator != null) {
            System.out.printf("%s - ", iterator.card);
            iterator = iterator.next;
        }
        System.out.println();
    }
}

class LinkedList {

    Node first;

    public LinkedList() {
        this.first = null;
    }

    public LinkedList(Node prvi) {
        this.first = prvi;
    }
}

class Node {
    String card;
    Node next;

    Node(String karta) {
        this.card = karta;
        this.next = null;
    }

    Node(String karta, Node next) {
        this.card = karta;
        this.next = next;
    }

    Node() {
        this.card = null;
        this.next = null;
    }
}
