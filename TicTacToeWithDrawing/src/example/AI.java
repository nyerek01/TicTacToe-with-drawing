package example;

import static example.GUI.*;
import static example.Game.*;
import static example.Simbol.*;
import java.util.*;

public class AI {

    private static boolean vertical = true;
    private static boolean horizontal = true;
    private static boolean logicalStep;
    private static byte nL;
    private static byte rows;
    private static byte columns;
    private static byte[][] fields;

    static void stepAI() {
        logicalStep = true;
        fields = b.getFields();
        nL = b.getNumberLines();
//Lehetne rakni bele kesleltetest, hogy direkt lassabban rakjon. Jol jonne akkor is amikor 2 gep jatszik egymas ellen, jol nezne ki... ha lesz mesterseges intelligencia a jatekban akkor tanulhatnanak az egymas elleni jatekbol...
//        logicalStep();
        if (logicalStep) {
            randomStep();
        }

        b.setRows(rows);
        b.setColumns(columns);
        setCoordSimbolY(Converter.conRowToPix(rows));
        setCoordSimbolX(Converter.conColToPix(columns));

        drawSimbol(getGraphic(), getCoordSimbolX(), getCoordSimbolY());
    }

    private static void logicalStep() {
        vertical = true;
        horizontal = true;
        for (byte a = 0; a < fields.length; a++) {
            for (byte c = 0; c < fields.length; c++) {
                if ((a == 0 && c == 0) || fields[a][c] != getCurrentSimbol()) {
                    continue;
                }
                if (c - 1 < 0) {
//                    System.out.println("c - 1 < 0");
                    horizontal = false;
//                    System.out.println("horizontal = " + horizontal);
                } else if (c + 1 >= fields.length) {
//                    System.out.println("c + 1 >= fields.length");
                    horizontal = false;
//                    System.out.println("horizontal = " + horizontal);
                }
                if (a - 1 < 0) {
//                    System.out.println("b - 1 < 0");
                    vertical = false;
//                    System.out.println("vertical = " + vertical);
                } else if (a + 1 >= fields.length) {
//                    System.out.println("b + 1 >= fields.length");
                    vertical = false;
//                    System.out.println("vertical = " + vertical);
                }
                if (vertical && (fields[a][c] == fields[a + 1][c] || fields[a][c] == fields[a - 1][c])) {
                    if (b.isEnabled(a + 1, c)) {
//                        System.out.println("Vertical Defence");
                        rows = (byte) (a + 1);
                        logicalStep = false;
                    } else if (b.isEnabled(a - 1, c)) {
//                        System.out.println("Vertical Defence");
                        rows = (byte) (a - 1);
                        logicalStep = false;
                    }
                    columns = c;
                } else if (horizontal && (fields[a][c] == fields[a][c + 1] || fields[a][c] == fields[a][c - 1]) && b.isEnabled(a, c)) {
//                    System.out.println("Horizontal Defence");
                    rows = 1;
                    columns = 2;
                    logicalStep = false;
                } else if ((horizontal && vertical) && (fields[a][c] == fields[a + 1][c + 1] || fields[a][c] == fields[a - 1][c - 1]) && b.isEnabled(a, c)) {
//                    System.out.println("MainDiagonal Defence");
                    rows = 1;
                    columns = 2;
                    logicalStep = false;
                } else if ((horizontal && vertical) && (fields[a][c] == fields[a - 1][c + 1] || fields[a][c] == fields[a + 1][c - 1]) && b.isEnabled(a, c)) {
//                    System.out.println("SecondDiagonal Defence");
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
