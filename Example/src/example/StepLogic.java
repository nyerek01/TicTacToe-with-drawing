package example;

import static example.Example.*;
import static example.Simbol.*;

public class StepLogic {

    static void stepAI() {
        boolean AI = true;
        byte i = 0, j = 0;
        for (byte b = 0; b < fields.length && AI; b++) {
            for (byte c = 0; c < fields.length && AI; c++) {
                if (fields[b][c] == 0) {//Az aktualis mezon nincs se 'X', se 'O', tehat ures
                    System.out.println("b= " + b + ", c= " + c);
                    fields[b][c] = 2;//'O' szimbolum
                    setCoordSimbolX((short) (b * Board.getSizeSquareX()));
                    setCoordSimbolY((short) (c * Board.getSizeSquareY()));
                    System.out.println("coordx" + getCoordSimbolX() + " coordy: " + getCoordSimbolY());
                    AI = false;//A lepes helye megvan, a ciklus ne fusson le tobbet
                    i = b;
                    j = c;
                }
            }
        }
        System.out.println("AIStep row = " + j + ", column = " + i);
        //Elvileg az i, j valtozok taroljak a sor, oszlop koordinatakat es veluk lehetne ellenorizni hogy ures az aktualis negyzet, mert csak akkor lehet rajzolni. Valamiert helytelen ertekeket vesznek fel a valtozok, ellenorizni
//        if (isEnabled(i, j)) {//Azonos helyre ne lehessen rakni egynel tobb szimbolumot
        drawSimbol(Example.getGraphic(), getCoordSimbolX(), getCoordSimbolY());//A komponensre kirajzoljuk az adott helyre a megfelelo szimbolumot
//        }
    }
}
