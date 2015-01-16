package example;

import static example.GUI.*;
import static example.Game.*;
import static example.Simbol.*;
import java.util.*;

public class AI {

    private static byte nL;
    private static byte rows;
    private static byte columns;
    private static byte[][] fields;
    private static boolean logicalStep;

    static void stepAI() {
        nL = b.getNumberLines();
        rows = b.getRows();
        fields = b.getFields();
        columns = b.getColumns();
        logicalStep = true;
//Lehetne rakni bele kesleltetest, hogy direkt lassabban rakjon. Jol jonne akkor is amikor 2 gep jatszik egymas ellen, jol nezne ki... ha lesz mesterseges intelligencia a jatekban akkor tanulhatnanak az egymas elleni jatekbol...
        logicalStep();
        if (logicalStep) {
            randomStep();
        }

        b.setRows(rows);
        b.setColumns(columns);
        setCoordSimbolY(Converter.conRowToPix(rows));
        setCoordSimbolX(Converter.conColToPix(columns));

        if (b.isEnabled(rows, columns)) {//Ha jo lenne a logikai lepes ez az if nem kene
            drawSimbolPixel(getGraphic(), getCoordSimbolX(), getCoordSimbolY());
//            drawSimbolRowCol(getGraphic(), rows, columns);
            comp.getSteps().add(0, "" + ((nL + 7) * rows + columns));
//            Printer.printList(comp.getSteps());
//            System.out.println("AI last step = " + comp.getSteps().get(0));
//            System.out.println("My last step = " + human.getSteps().get(0));
        } else {
            randomStep();//Ha a logikai lepes garantalna a jo lepest, akkor ez nem kene

            b.setRows(rows);
            b.setColumns(columns);
            setCoordSimbolY(Converter.conRowToPix(rows));
            setCoordSimbolX(Converter.conColToPix(columns));
//human.getSteps().add(0, "" + ((b.getNumberLines() + 7) * b.getRows() + b.getColumns()));
            comp.getSteps().add(0, "" + ((nL + 7) * rows + columns));
//            Printer.printList(comp.getSteps());
//            System.out.println("AI last step = " + comp.getSteps().get(0));
//            System.out.println("My last step = " + human.getSteps().get(0));
            drawSimbolPixel(getGraphic(), getCoordSimbolX(), getCoordSimbolY());
        }
    }

    private static void logicalStep() {//Most csak az en lepesemre nezi, tehat csak vedekezni tud valamennyire. Ugy kene hogy parameterkent kapja meg az utolso lepes koordinatajat, eloszor a sajatjat arra lefut, ha nem ad eredmenyt akkor az en lepesemre fut le, ha arra sem kap eredmenyt akkor random.
//        System.out.println("logicalStep() row = " + rows + ", col = " + columns);
        for (byte a = -2; a < 3 && logicalStep; a++) {
            for (byte c = -2; c < 3 && logicalStep; c++) {
                if (fields[a + rows][c + columns] != getCurrentSimbol() || (a == 0 && c == 0)) {
                    continue;
                }
                if (a == 0) {//Horizontal
                    System.out.print("Horizontal ");
                    if (c < 0) {
                        System.out.println("Left ");
                        if (b.isEnabled(rows, columns - 1)) {
                            columns -= 1;
                            System.out.println("Koze");
                        } else if (b.isEnabled(rows, columns + 1)) {
                            columns += 1;
                        } else if (b.isEnabled(rows, columns - 2)) {
                            columns -= 2;
                        }
                    } else {
                        System.out.println("Right ");
                        if (b.isEnabled(rows, columns + 1)) {
                            columns += 1;
                            System.out.println("Koze");
                        } else if (b.isEnabled(rows, columns - 1)) {
                            columns -= 1;
                        } else if (b.isEnabled(rows, columns + 2)) {
                            columns += 2;
                        }
                    }
                    logicalStep = false;
                } else if (c == 0) {//Vertical
                    System.out.print("Vertical ");
                    if (a < 0) {
                        System.out.print("Up ");
                        if (b.isEnabled(rows - 1, columns)) {
                            rows -= 1;
                            System.out.println("Koze");
                        } else if (b.isEnabled(rows + 1, columns)) {
                            rows += 1;
                        } else if (b.isEnabled(rows - 2, columns)) {
                            rows -= 2;
                        }
                    } else {
                        System.out.print("Down ");
                        if (b.isEnabled(rows + 1, columns)) {
                            rows += 1;
                            System.out.println("Koze");
                        } else if (b.isEnabled(rows - 1, columns)) {
                            rows -= 1;
                        } else if (b.isEnabled(rows + 2, columns)) {
                            rows += 2;
                        }
                    }
                    logicalStep = false;
                } else if (a == c) {//MDiagonal
                    System.out.print("MDiagonal ");
                    if (a < 0) {
                        System.out.print("UpLeft ");
                        if (b.isEnabled(rows - 1, columns - 1)) {
                            rows -= 1;
                            columns -= 1;
                            System.out.println("Koze");
                        } else if (b.isEnabled(rows + 1, columns + 1)) {
                            rows += 1;
                            columns += 1;
                        } else if (b.isEnabled(rows - 2, columns - 2)) {
                            rows -= 2;
                            columns -= 2;
                        }
                    } else {
                        System.out.print("DownRight ");
                        if (b.isEnabled(rows + 1, columns + 1)) {
                            rows += 1;
                            columns += 1;
                            System.out.println("Koze");
                        } else if (b.isEnabled(rows - 1, columns - 1)) {
                            rows -= 1;
                            columns -= 1;
                        } else if (b.isEnabled(rows + 2, columns + 2)) {
                            rows += 2;
                            columns += 2;
                        }
                    }
                    logicalStep = false;
                } else if (a == -c) {//SDiagonal
                    System.out.println("SDiagonal");
                    if (a < 0) {
                        System.out.print("UpRight ");
                        if (b.isEnabled(rows - 1, columns + 1)) {
                            rows -= 1;
                            columns += 1;
                            System.out.println("Koze");
                        } else if (b.isEnabled(rows + 1, columns - 1)) {
                            rows += 1;
                            columns -= 1;
                        } else if (b.isEnabled(rows - 2, columns + 2)) {
                            rows -= 2;
                            columns -= 2;
                        }
                    } else {
                        System.out.print("DownLeft ");
                        if (b.isEnabled(rows + 1, columns - 1)) {
                            rows += 1;
                            columns -= 1;
                            System.out.println("Koze");
                        } else if (b.isEnabled(rows - 1, columns + 1)) {
                            rows -= 1;
                            columns += 1;
                        } else if (b.isEnabled(rows + 2, columns - 2)) {
                            rows += 2;
                            columns -= 2;
                        }
                    }
                    logicalStep = false;
                } else {
                    System.out.println("else");
                }
            }
        }
        System.out.println("logical row = " + rows + ", col = " + columns);
    }

    private static void randomStep() {
        Random rn = new Random();
        int a = Integer.parseInt(b.getEmptyFields().get(rn.nextInt(b.getEmptyFields().size())));
        rows = (byte) (a / (nL + 8));
        columns = (byte) (a % (nL + 8));
        System.out.println("random row = " + rows + ", col = " + columns);
    }
}
