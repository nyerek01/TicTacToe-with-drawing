package example;

import java.util.*;

public class Printer {

    static void printArray(byte[][] a) {
        for (byte b = 0; b < a.length; b++) {
            for (byte c = 0; c < a.length; c++) {
                System.out.print(a[b][c] + ", ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    static void printList(ArrayList<String> f) {
        f.forEach(e -> System.out.print(e + ", "));//Java 8
    }

    static String getList(ArrayList<String> f) {
        String s = "";
        for (String f1 : f) {
            s += f1 + ", ";
        }
        return s;
    }
}
