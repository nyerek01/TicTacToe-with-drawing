package example;

import static example.Example.*;
import static example.WinCheck.*;
import static example.Board.*;

import java.awt.*;

public class Simbol {

//    static short sizeSimbol = (short) (getSizeSquareX() >> 1);
    static short sizeSimbol;
    static short coordSimbolX, coordSimbolY;

    static void drawSimbol(Graphics g2, int x, int y) {
        Graphics2D g = (Graphics2D) g2;
        g.setStroke(new BasicStroke(3));
        if (nextStepIsX) {
            g.setColor(Color.RED);
            g.drawOval(x - (sizeSimbol >> 1), y - (sizeSimbol >> 1), sizeSimbol, sizeSimbol);
        } else {
            g.setColor(Color.BLACK);
            g.drawLine(x - (sizeSimbol >> 1), y - (sizeSimbol >> 1), x + (sizeSimbol >> 1), y + (sizeSimbol >> 1));
            g.drawLine(x - (sizeSimbol >> 1), y + (sizeSimbol >> 1), x + (sizeSimbol >> 1), y - (sizeSimbol >> 1));
        }
        currentSimbol = (byte) ((nextStepIsX) ? 2 : 1);
        fields[rows + 4][columns + 4] = currentSimbol;//1 X, 2 O
        nextStepIsX = !nextStepIsX;
        if (++numberSteps > (numberLines < 9 ? 4 : 8)) {
            winCheck();
        }
    }
}
