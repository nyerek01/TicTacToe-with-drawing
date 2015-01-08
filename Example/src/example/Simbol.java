package example;

import static example.Example.*;
import static example.WinCheck.*;
import static example.Board.*;

import java.awt.*;

public class Simbol {

    static short sizeSimbol;
    static short coordSimbolX, coordSimbolY;

    static void drawSimbol(Graphics g2, int x, int y) {
        System.out.println("Megtortent a rajzolas!");
        Graphics2D g = (Graphics2D) g2;
        g.setStroke(new BasicStroke(3));
        if (nextStepIsX) {
            g.setColor(Color.RED);
            g.drawOval(x - sizeSimbol, y - sizeSimbol, sizeSquareX, sizeSquareX);
        } else {
            g.setColor(Color.BLACK);
            g.drawLine(x + left, y + top + menuHeight, x + left + sizeSquareX, y + top + menuHeight + sizeSquareX);
            g.drawLine(x + left + sizeSquareX, y + top + menuHeight, x + left, y + top + menuHeight + sizeSquareX);
        }
        currentSimbol = (byte) ((nextStepIsX) ? 2 : 1);
        fields[rows][columns] = currentSimbol;//1 X, 2 O
        nextStepIsX = !nextStepIsX;
        if (++numberSteps > (numberLines < 9 ? 4 : 8)) {//Csak akkor hivja meg az eljarast, ha lehetseges a nyeres. 3-nal min. 5, 5-nel min. 9 lepes kell
            winCheck();
        }
    }
}
