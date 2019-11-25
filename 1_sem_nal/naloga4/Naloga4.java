import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Naloga4 {


    public static Scanner in;

    public static void main(String[] args) throws FileNotFoundException {


        File file = new File(args[0]);

        in = new Scanner(file);

        int stUkazov = in.nextInt();
        LinkedList vrece = new LinkedList();

        //String[] ukaz0 = in.next().split(",");
        /*
            ukaz0[2] = 14:4
            ukaz0[3] = 80:6

        */
        vrece.init();
        for (int i = 0; i < stUkazov; i++) {
            String line = in.next();
            
            /*
                Primer ukaza: U,40,14:4,80:6 -> pomeni pa: 

                1. U = Ustvari
                2. 40 = Ime vrece
                3. V parih so: 4 pojavitve elementa 14, 6 ponovitev elementa 80
            */
            

            String[] ukaz = line.split(",");
            switch(ukaz[0].charAt(0)) {
                case 'U':
                    // Ustvari vreco
                    System.out.println("Ustvari vreco");
                    vrece.ustvari(ukaz);
                break;
                case 'Z':
                    vrece.zdruzi(ukaz);
                break;
                case 'R':
                    vrece.razlika(ukaz);
                break;
                case 'S':
                    vrece.skupno(ukaz);
                break;
                case 'P':
                    vrece.porezi(ukaz);
                break;
                case 'O':
                    vrece.obdrzi(ukaz);
                break;
                default:
                    /*
                        I,V -> V izhodno datoteko zapise vsebino vrece V v formatu E1:N1,E2:N2,...,Ek:Nk pri cemer so E elementi vrece V, N pa njihove ponovitve
                    */
                break;
            }
        }
        vrece.printArray();
    }
}

class LinkedList {
    protected Node first;

    public LinkedList() {
        first = null;
    }

    public void init() {
        LinkedList list = new LinkedList();
        first = new Node(null);
    }

    public void ustvari(String[] ukaz) {
        Node iter = first;
        while (iter.next != null) {
            iter = iter.next;
        }

        Node novi = new Node(Integer.parseInt(ukaz[1]), null);
        int dolzina = (ukaz.length - 2) * 2;
        int[] arr = new int[dolzina];
        int index = 0;
        for (int i = 2; i < ukaz.length; i++) {
            String[] parametra = ukaz[i].split(":");
            arr[index] = Integer.parseInt(parametra[0]);
            index++;
            arr[index] = Integer.parseInt(parametra[1]);
            index++;
        }
        novi.content = arr;
        iter.next = novi;
    }

    public void zdruzi(String[] ukaz) {
        int v1 = Integer.parseInt(ukaz[1]);
        int v2 = Integer.parseInt(ukaz[2]);

        //System.out.printf("Vreca 1: %d\nVreca 2: %d\n", v1, v2);

        int[] vreca1;
        int[] vreca2;
        Node iter = first;
        while (iter.next != null && iter.next.name != v1) {
            // Poisci prvo vreco
            iter = iter.next;
        }
        vreca1 = iter.next.content;
        iter = first;
        while (iter.next != null && iter.next.name != v2) {
            // Poisci prvo vreco
            iter = iter.next;
        }
        vreca2 = iter.next.content;

        // Zdruzi vsebino obeh vrec
        for (int i = 0; i < vreca2.length - 1; i = i + 2) {
            // Poisci ce ze kje obstaja
        }
    }

    public void razlika(String[] ukaz) {
        
    }

    public void skupno(String[] ukaz) {
        
    }

    public void porezi(String[] ukaz) {
        
    }

    public void obdrzi(String[] ukaz) {
        
    }

    public void printArray() {
        Node iter = first;
        while (iter.next != null) {
            System.out.println("Vreca: " + iter.next.name);
            for (int i = 0; i < iter.next.content.length; i++) {
                System.out.printf("%d", iter.next.content[i]);
                if (i < iter.next.content.length - 1) {
                    System.out.printf(",");
                }
            }
            System.out.println();
            iter = iter.next;
        }
    }
}

class Node {
    int name;
    int[] content;
    /*
        content je oblike [1, 10, 2, 20, 3, 15], kar pomeni: 1x element 10, 2x element 20, 3x element 15
    */
    Node next;

    public Node(int v, Node nxt) {
        name = v;
        next = nxt;
    }

    public Node(Node nxt) {
        next = nxt;
    }
}