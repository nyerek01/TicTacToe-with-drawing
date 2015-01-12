package example;

import static example.Game.*;
import static example.MinSteps.*;

import java.awt.*;

public class Simbol {

    private static byte currentSimbol;
    private static Simbols X, O, Z;
    private static short sizeSimbol, coordSimbolX, coordSimbolY;
    private static boolean nextStepIsX;

    static void drawSimbol(Graphics g2, int x, int y) {
        // byte rows = getRows();
        // byte columns = getColumns();
        Graphics2D g = (Graphics2D) g2;
        g.setStroke(new BasicStroke(3));
        byte center = (byte) ((b.getSizeSquareX() - sizeSimbol) >> 1);
        if (nextStepIsX) {
            g.setColor(Color.RED);
            g.drawOval(x + center, y + center, sizeSimbol, sizeSimbol);
        } else {
            g.setColor(Color.BLACK);
            g.drawLine(x + center, y + center, x + center + sizeSimbol, y + center + sizeSimbol);
            g.drawLine(x + center + sizeSimbol, y + center, x + center, y + center + sizeSimbol);

            //------------------------------------------
//            g.clearRect(55, 55, 200, 200);//Lepes visszavonasanal a szimbolumot eltavolitja
//            drawBoard();//Palya ujrarajzolasa, a menu a kitakart reszeket eltunteti
            //------------------------------------------
        }
//        g.drawString("You Win", x, y);
        currentSimbol = (nextStepIsX) ? Simbols.O.getValue() : Simbols.X.getValue();

        //Idaig tartozik a Simbol osztalyra, innentol egy masik osztaly kene hogy csinalja
        System.out.println("Game.human.getName() = " + Game.human.getName());
        System.out.println("Game.comp.getName() = " + Game.comp.getName());
        b.setFields(b.getRows(), b.getColumns(), currentSimbol);
        b.getEmptyFields().remove("" + ((b.getNumberLines() * b.getRows() + b.getColumns())));
        Printer.printList(b.getEmptyFields());
        nextStepIsX = !nextStepIsX;
        b.increaseNumberSteps();
        if (b.getNumberSteps() > (b.getNumberLines() < 9 ? TicTacToeMinSteps.getValue() : GomokuMinSteps.getValue())) {//Csak akkor hivja meg az eljarast, ha lehetseges a nyeres. 3-nal min. 5, 5-nel min. 9 lepes kell
            WinCheck.winCheck();
        }
        if (b.getEmptyFields().isEmpty()) {
            System.out.println("___Dontetlen___");
        }
    }

    static void setCurrentSimbol(byte c) {
        currentSimbol = c;
    }

    static byte getCurrentSimbol() {
        return currentSimbol;
    }

    static void setNextStepIsX(boolean x) {
        nextStepIsX = x;
    }

    static boolean isX() {
        return nextStepIsX;
    }

    static short getCoordSimbolX() {
        return coordSimbolX;
    }

    static void setCoordSimbolX(short x) {
        coordSimbolX = x;
    }

    static short getCoordSimbolY() {
        return coordSimbolY;
    }

    static void setCoordSimbolY(short y) {
        coordSimbolY = y;
    }

    static short getSizeSimbol() {
        return sizeSimbol;
    }

    static void setSizeSimbol(short s) {
        sizeSimbol = s;
    }
}
