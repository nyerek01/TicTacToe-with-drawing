package example;

import static example.Example.*;
import static example.Board.*;

public class WinCheck {

    static boolean win;

    static void winCheck() {
        System.out.println("winCheck()");

        for (int b = 4; b < fields.length-4; b++) {
            for (int c = 4; c < fields.length-4; c++) {
                System.out.println("bttomb = "+fields[b][c]);
//                if (fields[b][c]) {
                if (fields[b][c + 1]  == fields[b][c]  && fields[b][c - 1]  == fields[b][c] 
                             || fields[b + 1][c]  == fields[b][c]  && fields[b - 1][c]  == fields[b][c] 
                             || fields[b + 1][c + 1]  == fields[b][c]  && fields[b - 1][c - 1]  == fields[b][c] 
                             || fields[b + 1][c - 1]  == fields[b][c]  && fields[b - 1][c + 1]  == fields[b][c]  ) {
                    win = true;//Gyozelem vizsgalatat megcsinalni, TicTacToe-nal 3 szomszedos, egyebkent 5 azonos szomszed es nyert valaki
                }
            }
        }
        if (win) {
            System.out.println("if (win==true)");
//            e.newGame(numberLines);
        }
    }
}
