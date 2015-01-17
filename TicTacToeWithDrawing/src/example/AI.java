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
        columns = b.getColumns();
        fields = b.getFields();
        logicalStep = true;

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
//            drawSimbolRowCol(getGraphic(), rows, columns);//Sorral, oszloppal nem lehet meghivni
            comp.getSteps().add(0, "" + ((nL + 7) * rows + columns));
        } else {
            randomStep();//Ha a logikai lepes garantalna a jo lepest, akkor ez nem kene
            b.setRows(rows);
            b.setColumns(columns);
            setCoordSimbolY(Converter.conRowToPix(rows));
            setCoordSimbolX(Converter.conColToPix(columns));
            comp.getSteps().add(0, "" + ((nL + 7) * rows + columns));
            drawSimbolPixel(getGraphic(), getCoordSimbolX(), getCoordSimbolY());
        }
    }

    private static void logicalStep() {//Most csak az en lepesemre nezi, tehat csak vedekezni tud valamennyire. Ugy kene hogy parameterkent kapja meg az utolso lepes koordinatajat, eloszor a sajatjat arra lefut, ha nem ad eredmenyt akkor az en lepesemre fut le, ha arra sem kap eredmenyt akkor random.
        if (!comp.getSteps().isEmpty()) {

            for (byte a = -2; a < 3 && logicalStep; a++) {
                for (byte c = -2; c < 3 && logicalStep; c++) {
                    if (fields[a + rows][c + columns] != getCurrentSimbol() || (a == 0 && c == 0)) {//Azokat a mezokat nezzuk, ahol olyan szimbolum van mint a mienk
                        continue;
                    }
                    if (a == 0) {//Horizontal
                        for (byte d = -2; d < 3 && logicalStep; d++) {
                            if (b.isEnabled(rows, columns + d)) {
                                columns += d;
                                logicalStep = false;
                            }
                        }
                    } else if (c == 0) {//Vertical
                        for (byte d = -2; d < 3 && logicalStep; d++) {
                            if (b.isEnabled(rows + d, columns)) {
                                rows += d;
                                logicalStep = false;
                            }
                        }
                    } else if (a == c) {//MDiagonal
                        for (byte d = -2; d < 3; d++) {
                            if (b.isEnabled(rows + d, columns + d)) {
                                rows += d;
                                columns += d;
                                logicalStep = false;
                            }
                        }
                    } else if (a == -c) {//SDiagonal
                        for (byte d = -2; d < 3; d++) {
                            if (b.isEnabled(rows + d, columns - d)) {
                                rows += d;
                                columns -= d;
                                logicalStep = false;
                            }
                        }
                    } else if (a < c) {//Egyeb lepesi logika kidolgozasa...
                    } else {
                    }
                }
            }
        } else {//TicTacToe-nal erdemes kozepre rakni
            rows = 5;
            columns = 5;
            logicalStep = false;
        }
    }

    private static void randomStep() {
        Random rn = new Random();
        int a = Integer.parseInt(b.getEmptyFields().get(rn.nextInt(b.getEmptyFields().size())));
        //Garantaltan ervenyes lepest ad, elsore. Nem kell ciklus
        rows = (byte) (a / (nL + 8));
        columns = (byte) (a % (nL + 8));
    }
}
