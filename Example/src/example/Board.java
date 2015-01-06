package example;

import static example.Example.*;
import static example.Simbol.*;

import java.awt.*;

public class Board {

    static byte numberLines;
    static short sizeSquareX, sizeSquareY;

    static void drawBoard() {

        sizeSquareX = (short) (drawAreaX / numberLines);
        sizeSquareY = (short) (drawAreaY / numberLines);
        sizeSimbol = (short) (sizeSquareX >> 1);

        Graphics2D g2 = (Graphics2D) graphics;
        g2.setStroke(new BasicStroke(1));
        g2.setColor(Color.BLACK);
        for (byte i = 1; i < numberLines; i++) {
            g2.drawLine(coordLineX += sizeSquareX, top + menuHeight, coordLineX, windowHeight - bottom);
            g2.drawLine(left, coordLineY += sizeSquareY, drawAreaX, coordLineY);
        }
    }

    static short getSizeSquareX() {
        return sizeSquareX;
    }
}
