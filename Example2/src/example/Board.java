package example;

import static example.Example.*;

import java.awt.*;

public class Board {

    private static byte numberLines;
    private static short sizeSquareX, sizeSquareY;

    static void drawBoard() {

        sizeSquareX = (short) (drawAreaX / numberLines);
        sizeSquareY = (short) (drawAreaY / numberLines);
        Simbol.setSizeSimbol((short) (sizeSquareX >> 1));//Bitmuvelet gyorsabb mint az aritmetikai szorzas, osztas.

        Graphics2D g2 = Example.getGraphic();

        g2.setStroke(new BasicStroke(1));
        g2.setColor(Color.BLACK);

        short yStart = getCoordLineY();//Nem szukseges valtozok, de elvileg optimalisabb nem a ciklusban szamoltatni, igy csak egyszer ertekelodik ki
        short yStop = (short) (windowHeight - bottom);//memoriabol tobbet (+4 Byte), procibol kevesebbet hasznal.

        for (byte i = 1; i < numberLines; i++) {
            setCoordLineX((short) (getCoordLineX() + sizeSquareX));
            setCoordLineY((short) (getCoordLineY() + sizeSquareY));

            g2.drawLine(getCoordLineX(), yStart, getCoordLineX(), yStop);//Vertical Lines
            g2.drawLine(left, getCoordLineY(), drawAreaX, getCoordLineY());//Horizontal Lines
        }
        setCoordLineX(left);
        setCoordLineY((short) (top + menuHeight));
    }

    static byte getNumberLines() {
        return numberLines;
    }

    static void setNumberLines(byte n) {
        numberLines = n;
    }

    static short getSizeSquareX() {
        return sizeSquareX;
    }

    static short getSizeSquareY() {
        return sizeSquareY;
    }
}
