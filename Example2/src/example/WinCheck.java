package example;

import static example.Example.*;

public class WinCheck {

    private static boolean win;

    static void winCheck() {
        for (int b = 4; b < fields.length - 4; b++) {
            for (int c = 4; c < fields.length - 4; c++) {
                System.out.println("bttomb = " + fields[b][c]);
                if (fields[b][c + 1] == fields[b][c] && fields[b][c - 1] == fields[b][c]
                        || fields[b + 1][c] == fields[b][c] && fields[b - 1][c] == fields[b][c]
                        || fields[b + 1][c + 1] == fields[b][c] && fields[b - 1][c - 1] == fields[b][c]
                        || fields[b + 1][c - 1] == fields[b][c] && fields[b - 1][c + 1] == fields[b][c]) {
                    win = true;
                }
            }
        }
        if (win) {
            System.out.println("___Win___");
            e.newGame(Board.getNumberLines());
        }
    }

    static void setWin(boolean w) {
        win = w;
    }

}
