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
        for (int a = 4; a < b.getNumberLines() + 4 && !win; a++) {//Vegig megy az egesz jatekmezon, nem lenne szukseges
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
            if (isX()) {
                win();
            } else {
                lose();
            }
        }
    }

    static void win() {
        Game.human.increaseNumberOfWins();
        Game.comp.increaseNumberOfLoses();

        Game.human.increasePoints();
        checkLevel(Game.human);
        Game.comp.increasePoints((byte) -1);
        checkLevel(Game.comp);
        new Windows(new JRootPane(), true, Literals.Win);
        GUI.getT().stop();
        b.getEmptyFields().clear();
    }

    static void lose() {
        Game.human.increaseNumberOfLoses();
        Game.comp.increaseNumberOfWins();

        Game.human.increasePoints((byte) -1);
        checkLevel(Game.human);
        Game.comp.increasePoints();
        checkLevel(Game.comp);
        new Windows(new JRootPane(), true, Literals.Lose);
        GUI.getT().stop();
        b.getEmptyFields().clear();
    }

    static void tie() {
        Game.human.increaseNumberOfTies();
        Game.comp.increaseNumberOfTies();

        Game.human.increasePoints((byte) -1);
        checkLevel(Game.human);
        Game.comp.increasePoints();
        checkLevel(Game.comp);
        new Windows(new JRootPane(), true, Literals.Tie);
        GUI.getT().stop();
        b.getEmptyFields().clear();
    }

    static void checkLevel(Player p) {
        if (p.getPoints() < 50) {
            p.setLevel("Noob");
        } else if (p.getPoints() < 150) {
            p.setLevel("Beginner");
        } else if (p.getPoints() < 250) {
            p.setLevel("Normal");
        } else if (p.getPoints() < 350) {
            p.setLevel("Expert");
        } else if (p.getPoints() < 500) {
            p.setLevel("Pro");
        } else if (p.getPoints() < 1000) {
            p.setLevel("Master");
        } else {

        }
    }

    static void setWin(boolean w) {
        win = w;
    }

    static boolean getWin() {
        return win;
    }
}
