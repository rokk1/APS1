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
        cards.first.next = null;

        for (int i = 0; i < karte.length; i++) {
            cards.first.next = new LinkedListElement(karte[i]);
            cards.first = cards.first.next;
        }


        String directions = in.next();
        String[] navodila = directions.split(",");

        printList(cards);


    }

    public static void printList(LinkedList l) {
        LinkedListElement first = l.first;
        while (first.next != null) {
            System.out.printf("%s - ", first.card);
            first = first.next;
        }
    }
}

class LinkedList {

    LinkedListElement first;

    public LinkedList() {
        first = null;
    }

    public LinkedList(LinkedListElement prvi) {
        first = prvi;
    }
}

class LinkedListElement {
    String card;
    LinkedListElement next;

    public LinkedListElement(String karta) {
        card = karta;
        next = null;
    }

    public LinkedListElement(String karta, LinkedListElement next1) {
        card = karta;
        next = next1;
    }
}