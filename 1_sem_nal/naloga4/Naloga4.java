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
    LinkedList elements;

    public Bag() {
        LinkedList elements = 
    }
}

class LinkedList {
    LinkedListNode first;
    LinkedListN
}

class LinkedListNode {
    int value;
    LinkedListNode next;

    public LinkedListNode() {
        
    }
}