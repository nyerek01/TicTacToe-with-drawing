package example;

public class Converter {

    static byte conPixToRow(int y) {//convertYPixelToRow
        return (byte) ((y - Example.getCoordLineY()) / Board.getSizeSquareY());
    }

    static byte conPixToCol(int x) {//convertXPixelToColumn
        return (byte) ((x - Example.getCoordLineX()) / Board.getSizeSquareX());
    }

    static short conRowToPix(byte r) {//convertRowToPixel
        return (short) (r * Board.getSizeSquareY() + Example.getCoordLineY());
    }

    static short conColToPix(byte c) {//convertColumnToPixel
        return (short) (c * Board.getSizeSquareX() + Example.getCoordLineX());
    }
}
