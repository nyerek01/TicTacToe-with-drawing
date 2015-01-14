package example;

import static example.Game.b;
import static example.Simbol.*;
import javax.swing.*;

public class WinCheck {

    private static boolean win;
    private static boolean vertical;
    private static boolean horizontal;
    private static byte[][] fields;

    static void winCheck() {
        win = false;
        vertical = true;
        horizontal = true;
        fields = b.getFields();
        System.out.println("row = " + b.getRows() + ", col = " + b.getColumns());
//        for (int a = 0; a < fields.length; a++) {
        for (int a = b.getRows(); a < b.getRows() + 5; a++) {
//            for (int c = 0; c < fields.length; c++) {
            for (int c = b.getColumns(); c < b.getColumns() + 5; c++) {
                if (fields[a][c] != getCurrentSimbol()) {
                    continue;
                }
                if (c - 1 < 0) {
                    System.out.println("c - 1 < 0");
                    horizontal = false;
                } else if (c + 1 >= fields.length) {
                    System.out.println("c + 1 >= fields.length");
                    horizontal = false;
                }
                if (a - 1 < 0) {
                    System.out.println("a - 1 < 0");
                    vertical = false;
                } else if (a + 1 >= fields.length) {
                    System.out.println("a + 1 >= fields.length");
                    vertical = false;
                }
                if (horizontal && fields[a][c + 1] == fields[a][c] && fields[a][c - 1] == fields[a][c]) {//Horizontal
                    win = true;
                    System.out.println("Horizontal");
                } else if (vertical && fields[a + 1][c] == fields[a][c] && fields[a - 1][c] == fields[a][c]) {//Vertical
                    win = true;
                    System.out.println("Vertical");
                } else if ((horizontal || vertical) && fields[a + 1][c + 1] == fields[a][c] && fields[a - 1][c - 1] == fields[a][c]) {//MDiagonal
                    win = true;
                    System.out.println("MainDiagonal");
                } else if ((horizontal || vertical) && fields[a + 1][c - 1] == fields[a][c] && fields[a - 1][c + 1] == fields[a][c]) {//SDiagonal
                    win = true;
                    System.out.println("SecundDiagonal");
                }
            }
        }
        if (win) {
            System.out.println((isX()) ? "X" : "O" + " win");
            new Windows(new JRootPane(), true, Literals.Win);
//            e.newGame(Board.getNumberLines());
        }
    }

    static void setWin(boolean w) {
        win = w;
    }

    static boolean getWin() {
        return win;
    }
}
