package example;

import static example.Game.b;

public class Converter {

    static byte conPixToRow(int y) {//convertYPixelToRow
//        return (byte) ((y - GUI.getCoordLineY()) / b.getSizeSquareY());
        return (byte) ((y - GUI.getCoordLineY()) / b.getSizeSquareY() + 4);
    }

    static byte conPixToCol(int x) {//convertXPixelToColumn
//        return (byte) ((x - GUI.getCoordLineX()) / b.getSizeSquareX());
        return (byte) ((x - GUI.getCoordLineX()) / b.getSizeSquareX() + 4);
    }

    static short conRowToPix(byte r) {//convertRowToPixel
        return (short) ((r - 4) * b.getSizeSquareY() + GUI.getCoordLineY());
    }

    static short conColToPix(byte c) {//convertColumnToPixel
        return (short) ((c - 4) * b.getSizeSquareX() + GUI.getCoordLineX());
    }
}
