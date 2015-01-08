package example;

import static example.Example.*;
import static example.Simbol.*;
import static example.Board.*;
import static example.WinCheck.*;
import javax.swing.Timer;

public class InitVar {

    static void initVar() {

        startTime = 0;
        stopTime = 0;
        sec = 0;
        min = 0;
        rows = 0;
        columns = 0;
        numberLines = 5;
        sizeSimbol = 0;
        coordSimbolX = 0;
        coordSimbolY = 0;
        numberSteps = 0;
        currentSimbol = 0;
        nextStepIsX = true;
        win = false;

        fields = new byte[numberLines][numberLines];
        for (byte i = 0; i < fields.length; i++) {
            for (byte j = 0; j < fields.length; j++) {
                fields[i][j] = 0;//0 ures, 1 X, 2 O
            }
        }
        screenWidth = (short) sizeScreen.width;
        screenHeight = (short) sizeScreen.height;
        windowWidth = 600;
        windowHeight = 600;
        t.restart();
        timer.restart();
    }
}
