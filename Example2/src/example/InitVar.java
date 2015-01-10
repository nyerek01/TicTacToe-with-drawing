package example;

import static example.Example.*;
import static example.Simbol.*;
import static example.Board.*;

public class InitVar {

    static void initVar() {

        startTime = 0;
        stopTime = 0;
        sec = 0;
        min = 0;
        rows = 0;
        columns = 0;
        setNumberLines((byte) 5);
        setSizeSimbol((byte) 0);
        setCoordSimbolX((byte) 0);
        setCoordSimbolY((byte) 0);
        numberSteps = 0;
        currentSimbol = 0;
        setNextStepIsX(true);
        WinCheck.setWin(false);

        fields = new byte[getNumberLines()][getNumberLines()];//Elvileg letrehozaskor a tomb 0 ertekekkel van feltoltve, de ez a biztosabb
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
