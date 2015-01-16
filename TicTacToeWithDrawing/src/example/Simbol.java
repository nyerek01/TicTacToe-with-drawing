package example;

import static example.Game.*;
import static example.MinSteps.*;

import java.awt.*;
import javax.swing.*;

public class Simbol {

    private static byte currentSimbol;
    private static short sizeSimbol, coordSimbolX, coordSimbolY;
    private static boolean nextStepIsX;
    private static boolean tie;//Nem ebbe az osztalyba kene

//    static void drawSimbolRowCol(Graphics g2, byte r, byte c) {
//        drawSimbolPixel(g2, Converter.conRowToPix(r), Converter.conColToPix(c));
//    }
    static void drawSimbolPixel(Graphics g2, int x, int y) {
        tie = false;//Nem ebbe az osztalyba kene
        Graphics2D g = (Graphics2D) g2;
        g.setStroke(new BasicStroke(3));
        byte center = (byte) ((b.getSizeSquareX() - sizeSimbol) >> 1);
        if (nextStepIsX) {
            g.setColor(Color.RED);
            g.drawOval(x + center, y + center, sizeSimbol, sizeSimbol);
        } else {
            g.setColor(Color.BLACK);
            g.drawLine(x + center, y + center, x + center + sizeSimbol, y + center + sizeSimbol);
            g.drawLine(x + center + sizeSimbol, y + center, x + center, y + center + sizeSimbol);

            //------------------------------------------
//            g.clearRect(55, 55, 200, 200);//Lepes visszavonasanal a szimbolumot eltavolitja
            //------------------------------------------
        }
        currentSimbol = (nextStepIsX) ? Simbols.O.getValue() : Simbols.X.getValue();

        //Idaig tartozik a Simbol osztalyra, innentol egy masik osztaly kene hogy csinalja
        b.setFields(b.getRows(), b.getColumns(), currentSimbol);
        b.getEmptyFields().remove("" + (((b.getNumberLines() + 8) * b.getRows() + b.getColumns())));
        nextStepIsX = !nextStepIsX;
        b.increaseNumberSteps();
        if (b.getNumberSteps() > (b.getNumberLines() < 9 ? TicTacToeMinSteps.getValue() : GomokuMinSteps.getValue())) {
            WinCheck.winCheck();
        }
        tie = b.getEmptyFields().isEmpty() && !WinCheck.getWin();
        if (tie) {
            new Windows(new JRootPane(), true, Literals.Tie);
        }
    }

    static boolean isTie() {//Nem ebbe az osztalyba kene
        return tie;
    }

    static void setTie(boolean t) {//Nem ebbe az osztalyba kene
        tie = t;
    }

    static void setCurrentSimbol(byte c) {
        currentSimbol = c;
    }

    static byte getCurrentSimbol() {
        return currentSimbol;
    }

    static void setNextStepIsX(boolean x) {
        nextStepIsX = x;
    }

    static boolean isX() {
        return nextStepIsX;
    }

    static short getCoordSimbolX() {
        return coordSimbolX;
    }

    static void setCoordSimbolX(short x) {
        coordSimbolX = x;
    }

    static short getCoordSimbolY() {
        return coordSimbolY;
    }

    static void setCoordSimbolY(short y) {
        coordSimbolY = y;
    }

    static short getSizeSimbol() {
        return sizeSimbol;
    }

    static void setSizeSimbol(short s) {
        sizeSimbol = s;
    }
}
