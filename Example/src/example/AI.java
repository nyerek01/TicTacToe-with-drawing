package example;

import static example.GUI.*;
import static example.Game.*;
import static example.Simbol.*;
import java.util.*;

public class AI {

    static boolean vertical = true;
    static boolean horizontal = true;
    static boolean logicalStep = true;
    private static byte[][] fields = b.getFields();
    static byte nL = b.getNumberLines();
    static byte rows = b.getRows();
    static byte columns = b.getColumns();

    static void stepAI() {
//Lehetne rakni bele kesleltetest, hogy direkt lassabban rakjon. Jol jonne akkor is amikor 2 gep jatszik egymas ellen, jol nezne ki... ha lesz mesterseges intelligencia a jatekban akkor tanulhatnanak az egymas elleni jatekbol...
        logicalStep();
        if (logicalStep) {
            randomStep();
        }
        System.out.println((logicalStep) ? "random" : "logical" + "Step");

        b.setRows(rows);
        b.setColumns(columns);
        setCoordSimbolY(Converter.conRowToPix(rows));
        setCoordSimbolX(Converter.conColToPix(columns));

        drawSimbol(getGraphic(), getCoordSimbolX(), getCoordSimbolY());//A komponensre kirajzoljuk az adott helyre a megfelelo szimbolumot
    }

    private static void logicalStep() {
        vertical = true;
        horizontal = true;
        for (byte a = 0; a < fields.length; a++) {
            for (byte c = 0; c < fields.length; c++) {
//        for (byte b = -2; b < 3; b++) {
//            for (byte c = -2; c < 3; c++) {
                if ((a == 0 && c == 0) || fields[a][c] != getCurrentSimbol()) {
                    continue;
                }
                if (c - 1 < 0) {
                    System.out.println("c - 1 < 0");
                    horizontal = false;
                    System.out.println("horizontal = " + horizontal);
                } else if (c + 1 >= fields.length) {
                    System.out.println("c + 1 >= fields.length");
                    horizontal = false;
                    System.out.println("horizontal = " + horizontal);
                }
                if (a - 1 < 0) {
                    System.out.println("b - 1 < 0");
                    vertical = false;
                    System.out.println("vertical = " + vertical);
                } else if (a + 1 >= fields.length) {
                    System.out.println("b + 1 >= fields.length");
                    vertical = false;
                    System.out.println("vertical = " + vertical);
                }
                if (vertical && (fields[a][c] == fields[a + 1][c] || fields[a][c] == fields[a - 1][c])) {
                    if (b.isEnabled(a + 1, c)) {
                        System.out.println("Vertical Defence");
                        rows = (byte) (a + 1);
                        logicalStep = false;
                    } else if (b.isEnabled(a - 1, c)) {
                        System.out.println("Vertical Defence");
                        rows = (byte) (a - 1);
                        logicalStep = false;
                    }
                    columns = c;
                } else if (horizontal && (fields[a][c] == fields[a][c + 1] || fields[a][c] == fields[a][c - 1]) && b.isEnabled(a, c)) {
                    System.out.println("Horizontal Defence");
                    rows = 1;
                    columns = 2;
                    logicalStep = false;
                } else if ((horizontal && vertical) && (fields[a][c] == fields[a + 1][c + 1] || fields[a][c] == fields[a - 1][c - 1]) && b.isEnabled(a, c)) {
                    System.out.println("MainDiagonal Defence");
                    rows = 1;
                    columns = 2;
                    logicalStep = false;
                } else if ((horizontal && vertical) && (fields[a][c] == fields[a - 1][c + 1] || fields[a][c] == fields[a + 1][c - 1]) && b.isEnabled(a, c)) {
                    System.out.println("SecondDiagonal Defence");
                    rows = 1;
                    columns = 2;
                    logicalStep = false;
                }
            }
        }
    }

    private static void randomStep() {
        Random rn = new Random();
        int a = Integer.parseInt(b.getEmptyFields().get(rn.nextInt(b.getEmptyFields().size())));

        rows = (byte) (a / nL);
        columns = (byte) (a % nL);
    }
}
