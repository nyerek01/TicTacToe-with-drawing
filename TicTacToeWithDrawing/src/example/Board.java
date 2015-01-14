package example;

import static example.GUI.*;
import static example.Simbol.*;

import java.awt.*;
import java.util.*;

public class Board implements Interface {

    private byte rows, columns, numberLines, fields[][];
    private short numberSteps, sizeSquareX, sizeSquareY, drawAreaX, drawAreaY;
    private ArrayList<String> emptyFields;

    Board() {
        numberLines = 5;
        fields = new byte[numberLines][numberLines];
    }

    Board(byte n) {
        numberLines = n;
        fields = new byte[numberLines][numberLines];
    }

    void drawBoard() {
        sizeSquareX = (short) (drawAreaX / numberLines);
        sizeSquareY = (short) (drawAreaY / numberLines);
        setSizeSimbol((short) (sizeSquareX * perCent));

        Graphics2D g2 = GUI.getGraphic();

        g2.setStroke(new BasicStroke(1));
        g2.setColor(Color.BLACK);

        short yStart = getCoordLineY();
        short yStop = (short) (getWindowHeight() - getBottom());

        for (byte i = 1; i < numberLines; i++) {
            setCoordLineX((short) (getCoordLineX() + sizeSquareX));
            setCoordLineY((short) (getCoordLineY() + sizeSquareY));

            g2.drawLine(getCoordLineX(), yStart, getCoordLineX(), yStop);//Vertical Lines
            g2.drawLine(getLeft(), getCoordLineY(), drawAreaX, getCoordLineY());//Horizontal Lines
        }
        setCoordLineX(getLeft());
        setCoordLineY((short) (getTop() + getMenuHeight()));
    }

    byte getNumberLines() {
        return numberLines;
    }

    void setNumberLines(byte n) {
        numberLines = n;
    }

    short getSizeSquareX() {
        return sizeSquareX;
    }

    short getSizeSquareY() {
        return sizeSquareY;
    }

    ArrayList<String> getEmptyFields() {
        return emptyFields;
    }

    void setEmptyFields(ArrayList<String> e) {
        emptyFields = e;
    }

    boolean isEnabled(int a, int b) {
        return fields[a + 4][b + 4] == 0;
    }

    byte[][] getFields() {
        return fields;
    }

    void setFields(byte[][] f) {
        fields = f;
    }

    void setFields(byte r, byte c, byte v) {
        fields[r + 4][c + 4] = v;
    }

    short getDrawAreaX() {
        return drawAreaX;
    }

    void setDrawAreaX(short d) {
        drawAreaX = d;
    }

    short getDrawAreaY() {
        return drawAreaY;
    }

    void setDrawAreaY(short d) {
        drawAreaY = d;
    }

    public byte getRows() {
        return rows;
    }

    void setRows(int r) {
        rows = (byte) r;
    }

    byte getColumns() {
        return columns;
    }

    void setColumns(int c) {
        columns = (byte) c;
    }

    short getNumberSteps() {
        return numberSteps;
    }

    void setNumberSteps(int n) {
        numberSteps = (short) n;
    }

    void increaseNumberSteps() {
        numberSteps++;
    }
}
