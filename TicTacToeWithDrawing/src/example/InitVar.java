package example;

import static example.Simbol.*;
import static example.Game.*;
import static example.GUI.*;
import java.util.*;

public class InitVar implements Interface {

    static void initVar(byte n) {

        setSec(0);
        setMin(0);
        b.setRows(0);
        b.setColumns(0);
        setStopTime(0);
        setStartTime(0);
        b.setNumberSteps(0);
        setWindowWidth(600);
        setWindowHeight(600);
        setScreenWidth(sizeScreen.width);
        setScreenHeight(sizeScreen.height);

        b.setNumberLines(n);
        setNextStepIsX(false);
        WinCheck.setWin(false);
        setSizeSimbol((byte) 0);
        setCoordSimbolX((byte) 0);
        setCoordSimbolY((byte) 0);
        setCurrentSimbol((byte) 0);

        byte nL = b.getNumberLines();//Csak egyszer kell elkerni az adatot, kevesebb eroforras
        byte nLP8 = (byte) (nL + 8);
        byte[][] tempFields = new byte[nLP8][nLP8];
        ArrayList<String> tempEmpty = new ArrayList<>(nL * nL);

        for (byte i = 0; i < nLP8; i++) {
            for (byte j = 0; j < nLP8; j++) {
                tempFields[i][j] = 0;//0 ures, 1 X, 2 O
                if (i < nL && j < nL) {
                    tempEmpty.add(nL * i + j, "" + (nL * i + j));//Elejen az osszes mezo ures, majd lepesenkent csokken a szamuk
                }
            }
        }
        b.setFields(tempFields);
        b.setEmptyFields(tempEmpty);
    }
}
