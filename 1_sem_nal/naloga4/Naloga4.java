import java.io.*;
import java.util.*;

public class Naloga4 {

    public static PrintWriter izhod;
    public static Scanner in;

    public static void main(String[] args) throws FileNotFoundException, IOException {


        File file = new File(args[0]);

        izhod = new PrintWriter(new FileWriter(args[1]));

        in = new Scanner(file);

        int stUkazov = in.nextInt();
        LinkedList vrece = new LinkedList();

        //String[] ukaz0 = in.next().split(",");
        /*
            ukaz0[2] = 14:4
            ukaz0[3] = 80:6

        */
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
                    vrece.ustvari(Integer.parseInt(ukaz[1]), ukaz);
                break;
                case 'Z':
                    int vrecaA = Integer.parseInt(ukaz[1]);
                    int vrecaB = Integer.parseInt(ukaz[2]);
                    vrece.zdruzi(vrecaA, vrecaB);
                break;
                case 'R':
                    int vreca_A = Integer.parseInt(ukaz[1]);
                    int vreca_B = Integer.parseInt(ukaz[2]);
                    vrece.razlika(vreca_A, vreca_B);
                break;
                case 'S':
                    int v1 = Integer.parseInt(ukaz[1]);
                    int v2 = Integer.parseInt(ukaz[2]);
                    vrece.skupno(v1, v2);
                break;
                case 'P':
                    int vreca = Integer.parseInt(ukaz[1]);
                    int constant = Integer.parseInt(ukaz[2]);
                    vrece.porezi(vreca, constant);
                break;
                case 'O':
                    int x = Integer.parseInt(ukaz[1]);
                    int constant2 = Integer.parseInt(ukaz[2]);
                    vrece.obdrzi(x, constant2);
                break;
                default:
                    /*
                        I,V -> V izhodno datoteko zapise vsebino vrece V v formatu E1:N1,E2:N2,...,Ek:Nk pri cemer so E elementi vrece V, N pa njihove ponovitve
                    */
                    int vreca2 = Integer.parseInt(ukaz[1]);
                    izhod.println(vrece.izpisi(vreca2));
                break;
            }
        }
        //vrece.printArray();
        in.close();
        izhod.close();
    }
}

class LinkedList {
    protected Node first;

    public LinkedList() {
        first = new Node(null);
    }


    public void ustvari(int value, String[] ukaz) {
        Node it = first;
        while (it.next != null) {
            it = it.next;
        }
        Node novaVreca = new Node(value, null);
        int arrSize = ukaz.length - 2;
        int[] arr = new int[arrSize];
        int[] arr2 = new int[arrSize];

        int index = 0;
        for (int i = 2; i < ukaz.length; i++) {
            String[] prvaDva = ukaz[i].split(":");
            int prvi = Integer.parseInt(prvaDva[0]);
            int drugi = Integer.parseInt(prvaDva[1]);
            arr[index] = prvi;
            arr2[index] = drugi;
            index++;
        }
        novaVreca.elements = arr;
        novaVreca.occurences = arr2;
        sortiraj(novaVreca);
        it.next = novaVreca;
    }

    public void sortiraj(Node node) {
        // Sortiraj po vrednosti elementa
        int n = node.elements.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (node.elements[j] > node.elements[j + 1])  {
                    int tmp = node.elements[j];
                    int tmp2 = node.occurences[j];
                    node.elements[j] = node.elements[j + 1];
                    node.elements[j + 1] = tmp;
                    node.occurences[j] = node.occurences[j + 1];
                    node.occurences[j + 1] = tmp2;
                }
            }
        }
    }

    public Node poisciVreco(int val) {
        Node it = first.next;
        while (it.name != val) {
            it = it.next;
        }
        return it;
    }

    public void zdruzi(int vrecaA, int vrecaB) {
        // V vreco A dodaj vsebino vrece B
        Node vreca_a = poisciVreco(vrecaA);
        Node vreca_b = poisciVreco(vrecaB);
        
        int indexNovega = vreca_a.elements.length;
        int[] newElements = new int[vreca_a.elements.length + vreca_b.elements.length];
        int[] newOccurences = new int[vreca_a.elements.length + vreca_b.elements.length];
        for (int i = 0; i < vreca_a.elements.length; i++) {
            newElements[i] = vreca_a.elements[i];
            newOccurences[i] = vreca_a.occurences[i];
        }
        for (int i = 0; i < vreca_b.elements.length; i++) {
            boolean same = false;
            for (int j = 0; j < vreca_a.elements.length; j++) {
                if (vreca_a.elements[j] == vreca_b.elements[i]) {
                    // Povecaj occurences
                    same = true;
                    vreca_a.occurences[j] += vreca_b.occurences[i];
                    break;
                }
            }
            if (!same) {
                newElements[indexNovega] = vreca_b.elements[i];
                newOccurences[indexNovega] = vreca_b.occurences[i];
                indexNovega++;
            }
        }
        for (int i = 0; i < vreca_a.occurences.length; i++) {
            newOccurences[i] = vreca_a.occurences[i];
        }
        vreca_a.elements = newElements;
        vreca_a.occurences = newOccurences;
        sortiraj(vreca_a);
    }



    public boolean find(int[] a, int value) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == value) {
                return true;
            }
        }
        return false;
    }

    public void razlika(int v1, int v2) {
        Node vreca1 = poisciVreco(v1);
        Node vreca2 = poisciVreco(v2);

        int[] a1 = vreca1.elements;
        int[] a2 = vreca1.occurences;
        int[] b1 = vreca2.elements;
        int[] b2 = vreca2.occurences;

        for (int i = 0; i < b1.length; i++) {
            for (int j = 0; j < a1.length; j++) {
                if (b1[i] == a1[j]) {
                    // Odstrani doloceno stevilo elementov
                    a2[j] -= b2[i];
                    a2[j] = a2[j] < 0 ? 0 : a2[j];
                }
            }
        }

        vreca1.occurences = a2;
    }

    public void skupno(int v1, int v2) {
        Node vreca1 = poisciVreco(v1);
        Node vreca2 = poisciVreco(v2);

        int[] a1 = vreca1.elements;
        int[] a2 = vreca1.occurences;
        int[] b1 = vreca2.elements;
        int[] b2 = vreca2.occurences;
        
        for (int i = 0; i < a1.length; i++) {
            boolean found = false;
            for (int j = 0; j < b1.length; j++) {
                if (a1[i] == b1[j]) {
                    found = true;
                    a2[i] = Math.min(a2[i], b2[j]);
                }
            }
            if (!found) {
                a2[i] = 0;
            }
        }

        vreca1.occurences = a2;
        sortiraj(vreca1);
    }

    public void porezi(int id, int constant) {
        Node vreca = poisciVreco(id);
        for (int i = 0; i < vreca.occurences.length; i++) {
            if (vreca.occurences[i] > constant) {
                vreca.occurences[i] = constant;
            }
        }
    }

    public void obdrzi(int vreca1, int constant) {
        Node vreca = poisciVreco(vreca1);
        int[] a1 = vreca.occurences;

        for (int i = 0; i < a1.length; i++) {
            if (a1[i] < constant) {
                a1[i] = 0;
            }
        }
        vreca.occurences = a1;
    }

    public String izpisi(int id) {
        Node vreca = poisciVreco(id);
        
        String rez = "";
        for (int i = 0; i < vreca.elements.length; i++) {
            if (vreca.occurences[i] > 0) {
                rez += Integer.toString(vreca.elements[i]) + ":" + Integer.toString(vreca.occurences[i]) + ",";
            }
        }
        if (rez.charAt(rez.length() - 1) == ',') {
            rez = rez.substring(0, rez.length() - 1);
        } 
        //izhod.println(rez);
        return rez;
        //System.out.println(rez);
    }

    public void arrayPrint(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.printf("%d ", a[i]);
        }
        System.out.println();
    }

    public void printArray() {
        Node iter = first;
        while (iter.next != null) {
            System.out.println("Vreca: " + iter.next.name);
            System.out.println("Elements");
            for (int i = 0; i < iter.next.elements.length; i++) {
                System.out.printf("%d", iter.next.elements[i]);
                if (i < iter.next.elements.length - 1) {
                    System.out.printf(",");
                }
            }
            System.out.println();
            System.out.println("Occurences");
            for (int i = 0; i < iter.next.occurences.length; i++) {
                System.out.printf("%d", iter.next.occurences[i]);
                if (i < iter.next.occurences.length - 1) {
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
    int[] elements;
    int[] occurences;
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