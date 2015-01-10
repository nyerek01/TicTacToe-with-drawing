package example;

import static example.Example.*;
import static example.Simbol.*;

public class StepLogic {

    static void stepAI() {
        boolean AI = true;
        byte i = 0, j = 0;
        for (byte r = 0; r < fields.length && AI; r++) {
            for (byte c = 0; c < fields.length && AI; c++) {

                if (isEnabled(r, c)) {//Az aktualis mezon nincs se 'X', se 'O', tehat ures
                    setCoordSimbolX(Converter.conColToPix(c));
                    columns = Converter.conPixToCol(getCoordSimbolX());

                    setCoordSimbolY(Converter.conRowToPix(r));
                    rows = Converter.conPixToRow(getCoordSimbolY());

                    System.out.println("row = " + r + ", col = " + c);
                    System.out.println("coordx" + getCoordSimbolX() + " coordy: " + getCoordSimbolY());

                    AI = false;//A lepes helye megvan, a ciklus ne fusson le tobbet

                    i = r;
                    j = c;
                }
            }
        }
        System.out.println("AIStep row = " + j + ", column = " + i);
        drawSimbol(Example.getGraphic(), getCoordSimbolX(), getCoordSimbolY());//A komponensre kirajzoljuk az adott helyre a megfelelo szimbolumot
    }
}
