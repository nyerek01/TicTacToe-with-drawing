package example;

import static example.Example.*;
import static example.Board.*;

import java.awt.*;

public class Simbol {

    private static boolean nextStepIsX;

    private static short sizeSimbol;
    private static short coordSimbolX, coordSimbolY;

    static void drawSimbol(Graphics g2, int x, int y) {
        System.out.println("Megtortent a rajzolas!");
        Graphics2D g = (Graphics2D) g2;
        g.setStroke(new BasicStroke(3));
        if (nextStepIsX) {
            g.setColor(Color.RED);
            g.drawOval(x, y, getSizeSquareX(), getSizeSquareX());
        } else {
            g.setColor(Color.BLACK);
            g.drawLine(x + left, y + top + menuHeight, x + left + getSizeSquareX(), y + top + menuHeight + getSizeSquareX());
            g.drawLine(x + left + getSizeSquareX(), y + top + menuHeight, x + left, y + top + menuHeight + getSizeSquareX());
        }
        currentSimbol = (byte) ((nextStepIsX) ? 2 : 1);
        fields[rows][columns] = currentSimbol;//1 X, 2 O
        nextStepIsX = !nextStepIsX;
        if (++numberSteps > (getNumberLines() < 9 ? 4 : 8)) {//Csak akkor hivja meg az eljarast, ha lehetseges a nyeres. 3-nal min. 5, 5-nel min. 9 lepes kell
            WinCheck.winCheck();
        }
        if (numberSteps == getNumberLines() * getNumberLines()) {
            System.out.println("numberSteps == getNumberLines() * getNumberLines()\nDontetlen");
        }
        boolean tie = true;
        for (byte b = 0; b < fields.length; b++) {
            for (byte c = 0; c < fields.length; c++) {
                if (isEnabled(b, c)) {
                    tie = false;
                }
            }
        }
        if (tie) {
            System.out.println("___Dontetlen___");
        }
    }

    static void setNextStepIsX(boolean x) {
        nextStepIsX = x;
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
