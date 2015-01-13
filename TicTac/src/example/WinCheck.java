package example;

import static example.Game.b;
import static example.Simbol.*;

public class WinCheck {

    private static boolean win = false;
    private static boolean horizontal;
    private static boolean vertical;
    private static byte[][] fields = b.getFields();

    static void winCheck() {
        for (byte b = 0; b < fields.length; b++) {
            for (byte c = 0; c < fields.length; c++) {
            }
        }
        for (int b = 0; b < fields.length; b++) {
            for (int c = 0; c < fields.length; c++) {
//                for (byte b = -2; b < 3; b++) {
//                    for (byte c = -2; c < 3; c++) {
//
//                    }
//                }
                if (fields[b][c] != getCurrentSimbol()) {
                    continue;
                }
                if (c - 1 < 0) {
                    horizontal = false;
                } else if (c + 1 >= fields.length) {
                    horizontal = false;
                }
                if (b - 1 < 0) {
                    vertical = false;
                } else if (b + 1 >= fields.length) {
                    vertical = false;
                }
                if (horizontal && fields[b][c + 1] == fields[b][c] && fields[b][c - 1] == fields[b][c]) {//Horizontal
                    win = true;
                    System.out.println("Horizontal");
                } else if (vertical && fields[b + 1][c] == fields[b][c] && fields[b - 1][c] == fields[b][c]) {//Vertical
                    win = true;
                    System.out.println("Vertical");
                } else if ((horizontal || vertical) && fields[b + 1][c + 1] == fields[b][c] && fields[b - 1][c - 1] == fields[b][c]) {//MDiagonal
                    win = true;
                    System.out.println("MainDiagonal");
                } else if ((horizontal || vertical) && fields[b + 1][c - 1] == fields[b][c] && fields[b - 1][c + 1] == fields[b][c]) {//SDiagonal
                    win = true;
                    System.out.println("SecundDiagonal");
                }
            }
        }
        if (win) {
            System.out.println((isX()) ? "X" : "O" + " win");
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
