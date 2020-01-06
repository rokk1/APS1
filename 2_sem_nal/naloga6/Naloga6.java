import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;

public class Naloga6 {


    public static PrintWriter izhod;
    public static Scanner in;
    public static ArrayList<String> fst = new ArrayList<String>();
    public static ArrayList<String> scnd = new ArrayList<String>();
    public static void main(String[] args) throws FileNotFoundException, IOException {


        File file = new File(args[0]);

        izhod = new PrintWriter(new FileWriter(args[1]));

        in = new Scanner(file);

        int stZapisov = in.nextInt();

        for (int i = 0; i < stZapisov; i++) {
            String[] car_driver = in.next().split("-");
            String one = car_driver[0];
            String two = car_driver[1];
            int one_in = find(one);
            int two_in = find(two);
            if (one_in == two_in && one_in > -1) {
                System.out.printf("NAPAKA!: one: %s .. one_in: %d, two: %s ..two_in: %d\n", one, one_in, two, two_in);
                return;
            }
            if (one_in <= -1) {
                if (two_in <= -1) {
                    fst.add(one);
                    scnd.add(two);
                } else {
                    // Prvi ni nikjer, daj ga drugam kot je drugi
                    if (two_in == 0) {
                        // Drugi je v fst, dodaj prvega v scnd
                        scnd.add(one);
                    } else {
                        // Obratno
                        fst.add(one);
                    }
                }
            } else {
                // Prvega smo nasli v fst
                if (one_in == 0) {
                    if (two_in <= -1) {
                        scnd.add(two);
                    }
                } else {
                    // Prvega smo nasli v scnd
                    if (two_in <= -1) {
                        fst.add(two);
                    }
                }
            }
            System.out.println("Prvi");
            printList(fst);
            System.out.println("Drugi");
            printList(scnd);
            //System.out.printf("1st found in %d .. 2nd found in %d\n", one_in, two_in);
            // Prvega vedno isci, ce ga najdes poglej ce je drugi slucajno v istem arraylistu, ce je potem je napaka
        }

        if (fst.size() == scnd.size()) {
            System.out.println("-1");
        } else {
            if (fst.size() > scnd.size()) {
                printList(fst);
            } else {
                printList(scnd);
            }
        }
    }

    public static int find(String value) {
        /*
            - If value is found in 1st return 0
            - If value is found in 2nd return 1
            - If value isn't found in any return -1
        */
        // First search in fst
        ListIterator it = fst.listIterator();
        while (it.hasNext()) {
            if (it.next().equals(value)) {
                return 0;
            }
        }
        it = scnd.listIterator();
        while (it.hasNext()) {
            if (it.next().equals(value)) {
                return 1;
            }
        }
        return -1;
    }

    public static void insertUnique(ArrayList list, String value) {
        ListIterator<String> it = list.listIterator();
        while (it.hasNext()) {
            if (it.next().equals(value)) {
                return;
            }
        }
        list.add(value);
        return;
    }

    public static void printList(ArrayList l) {
        ListIterator it = l.listIterator();
        while (it.hasNext()) {
            System.out.printf("%s, ", it.next());
        }
        System.out.println();
    }
}