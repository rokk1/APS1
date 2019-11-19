import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;

public class Naloga3 {

    public static PrintWriter izhod;
    public static Scanner in;
    public static void main(String[] args) throws FileNotFoundException, IOException {

        File file = new File(args[0]);

        izhod = new PrintWriter(new FileWriter(args[1]));

        in = new Scanner(file);

        String stevilke = in.next();

        String[] st = stevilke.split(",");

        int stOperacij = in.nextInt();
        
        // Kreiranje praznega seznama
        LinkedList seznam = new LinkedList();


        seznam.init(st);
        //printList(seznam);
        //seznam.ohrani('=', 10);
        //printList(seznam);
        //seznam.zdruzi('+');
        //printList(seznam);

        for (int i = 0; i < stOperacij; i++) {
            String[] ukaz = in.next().split(",");

            switch(ukaz[0].charAt(0)) {
                case 'p':
                    seznam.preslikaj(ukaz[1].charAt(0), Integer.parseInt(ukaz[2]));
                    printList(seznam);
                break;
                case 'o':
                    seznam.ohrani(ukaz[1].charAt(0), Integer.parseInt(ukaz[2]));
                    printList(seznam);
                break;
                default:
                    seznam.zdruzi(ukaz[1].charAt(0));
                    printList(seznam);
                break;
            }
        }

        in.close();
        izhod.close();
    }


    public static void printList(LinkedList l) {
        Node iterator = l.first.next;
        while (iterator != null) {
            System.out.printf("%s", iterator.value);
            izhod.printf("%s", iterator.value);
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

    public void preslikaj(char op, int val) {
        Node it = first.next;
        if (op == '*') {
            // Vsak element seznama pomnozi z val
            while (it != null) {
                it.value = it.value * val;
                it = it.next;
            }
        } else {
            // Vsakemu elementu seznama pristej val
            while (it != null) {
                it.value = it.value + val;
                it = it.next;
            }
        }
    }

    public void ohrani(char op, int val) {
        Node it = first;
        switch (op) {
            case '<':
                while (it.next != null) {
                    if (!(it.next.value < val)) {
                        // Izvrzi ga iz seznama
                        it.next = it.next.next;
                    } else {
                        it = it.next;
                    }
                    if (it == null) {
                        break;
                    }
                }
            break;
            case '>':
                while (it.next != null) {
                    if (!(it.next.value > val)) {
                        // Izvrzi ga iz seznama
                        it.next = it.next.next;
                    } else {
                        it = it.next;
                    }
                    if (it == null) {
                        break;
                    }
                    //printi();
                }
            break;
            default:
                while (it.next != null) {
                    if (it.next.value != val) {
                        // Izvrzi ga iz seznama
                        it.next = it.next.next;
                    } else {
                        it = it.next;
                    }
                    if (it == null) {
                        break;
                    }
                   // printi();
                }
            break;
        }

    }

    public void zdruzi(char op) {
        Node it = first.next;
        switch(op) {
            case '+':
                // Sestej vse elemente seznama med sabo
                while (it.next != null) {
                    it.value = it.value + it.next.value;
                    it.next = it.next.next;
                }
            break;
            default: 
                // Pomnozi vse elemente seznama med sabo
                while (it.next != null) {
                    it.value = it.value * it.next.value;
                    it.next = it.next.next;
                }
            break;
        }
    }

    public void init(String[] a) {
        LinkedList list = new LinkedList();

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