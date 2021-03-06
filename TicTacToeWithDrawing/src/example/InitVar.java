package example;

import static example.Simbol.*;
import static example.Game.*;
import static example.GUI.*;
import java.util.*;

public class InitVar implements Interface {

    static void initVar(byte n) {//Valtozok alaphelyzetbe allitasa

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
        human.setTime((short) (n * n));//Kb. annyi ido van masodpercben mint ahany mezo, tehat 1 lepes 1 sec
        comp.setTime((short) (n * n));
        human.increaseNumberOfGames();//Az elkezdett jateknal mar novelve van, attol fuggetlenul hogy nem fejezi be, vagy egybol kilep
        comp.increaseNumberOfGames();

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
                tempFields[i][j] = -1;//Ezekre a mezokre nem lehet lepni, azert vannak hogy ne legyen tulindexeles
                if (i < nL && j < nL) {
                    tempEmpty.add(nL * i + j, "" + (nLP8 * (i + 4) + j + 4));//Elejen az osszes mezo ures, majd lepesenkent csokken a szamuk
                }
                if (i > 3 && j > 3 && i < nL + 4 && j < nL + 4) {
                    tempFields[i][j] = 0;//A megjeleno jatekter, ezek az elerheto mezok az ervenyes lepeshez
                }
            }
        }
        b.setFields(tempFields);
        b.setEmptyFields(tempEmpty);
        human.getSteps().clear();
        comp.getSteps().clear();
    }
}
