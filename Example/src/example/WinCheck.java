package example;

import static example.Example.*;
import static example.Board.*;

public class WinCheck {

    static boolean win;

    static void winCheck() {
        System.out.println("winCheck()");

        for (byte b = 4; b < fields.length-4; b++) {
            for (byte c = 4; c < fields.length-4; c++) {
                if (true) {
                    win = true;//Gyozelem vizsgalatat megcsinalni, TicTacToe-nal 3 szomszedos, egyebkent 5 azonos szomszed es nyert valaki
                }
            }
        }
        if (win) {
            System.out.println("if (win==true)");
            e.newGame(numberLines);
//        e.newGame(numberLines);
        }
    }
}
