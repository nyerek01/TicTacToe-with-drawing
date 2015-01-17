package example;

import static example.Game.b;
import static example.Simbol.*;
import javax.swing.*;

public class WinCheck {

    private static boolean win;
    private static byte[][] fields;

    static void winCheck() {
        win = false;
        fields = b.getFields();
        byte row = b.getRows();
        byte col = b.getColumns();
        for (int a = 4; a < b.getNumberLines() + 4 && !win; a++) {
            for (int c = 4; c < b.getNumberLines() + 4 && !win; c++) {
                if (fields[a][c] != getCurrentSimbol()) {
                    continue;
                }
                if (b.getNumberLines() == 3) {
                    if (fields[a][c + 1] == fields[a][c] && fields[a][c - 1] == fields[a][c]) {//Horizontal
                        win = true;
                    } else if (fields[a + 1][c] == fields[a][c] && fields[a - 1][c] == fields[a][c]) {//Vertical
                        win = true;
                    } else if (fields[a + 1][c + 1] == fields[a][c] && fields[a - 1][c - 1] == fields[a][c]) {//MDiagonal
                        win = true;
                    } else if (fields[a + 1][c - 1] == fields[a][c] && fields[a - 1][c + 1] == fields[a][c]) {//SDiagonal
                        win = true;
                    }
                } else {
                    if (fields[a][c + 1] == fields[a][c] && fields[a][c - 1] == fields[a][c]
                            && fields[a][c + 2] == fields[a][c] && fields[a][c - 2] == fields[a][c]) {//Horizontal
                        win = true;
                    } else if (fields[a + 1][c] == fields[a][c] && fields[a - 1][c] == fields[a][c]
                            && fields[a + 2][c] == fields[a][c] && fields[a - 2][c] == fields[a][c]) {//Vertical
                        win = true;
                    } else if (fields[a + 1][c + 1] == fields[a][c] && fields[a - 1][c - 1] == fields[a][c]
                            && fields[a + 2][c + 2] == fields[a][c] && fields[a - 2][c - 2] == fields[a][c]) {//MDiagonal
                        win = true;
                    } else if (fields[a + 1][c - 1] == fields[a][c] && fields[a - 1][c + 1] == fields[a][c]
                            && fields[a + 2][c - 2] == fields[a][c] && fields[a - 2][c + 2] == fields[a][c]) {//SDiagonal
                        win = true;
                    }
                }
            }
        }
        if (win) {
            if (Game.human.getSimbol() == Simbols.X) {//Ez nem a WinCheck osztaly feladata lenne, masik osztalyba kene
                Game.human.increasePoints();
            } else if (Game.comp.getSimbol() == Simbols.O) {//A feltetel nem is jo, javitani kene. Nem csak O-val lehet
                Game.comp.increasePoints();
            }
            if (isX()) {//Nem biztos hogy helyes a vizsgalat, a fuggveny csak azt adja vissza hogy X lepett
                new Windows(new JRootPane(), true, Literals.Win);
            } else {
                new Windows(new JRootPane(), true, Literals.Lose);
            }
        }
    }

    static void setWin(boolean w) {
        win = w;
    }

    static boolean getWin() {
        return win;
    }
}
