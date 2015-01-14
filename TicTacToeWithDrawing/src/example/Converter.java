package example;

import static example.Game.b;

public class Converter {

    static byte conPixToRow(int y) {//convertYPixelToRow
//        return (byte) ((y - GUI.getCoordLineY()) / b.getSizeSquareY());
        return (byte) ((y - GUI.getCoordLineY()) / b.getSizeSquareY());
    }

    static byte conPixToCol(int x) {//convertXPixelToColumn
        return (byte) ((x - GUI.getCoordLineX()) / b.getSizeSquareX());
    }

    static short conRowToPix(byte r) {//convertRowToPixel
        return (short) (r * b.getSizeSquareY() + GUI.getCoordLineY());
    }

    static short conColToPix(byte c) {//convertColumnToPixel
        return (short) (c * b.getSizeSquareX() + GUI.getCoordLineX());
    }
}
