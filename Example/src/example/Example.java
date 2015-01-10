        /*      GOMOKU

 Aktualis allas szamontartasa, pl.: 3-1, az eredmenyhez tartozo pont(ozasi)rendszer kidolgosasa.
 Perzisztens adattarolas (XML).
 A gepnek legyen valaszthato tudasszintje, akar randomtol kezdve a tokeletes strategia megvalositasaig.
 Lehessen valasztani szimbolumot (X,O, stb.), kezdolepest, legyen lehetoseg lepes visszavonasara.
 Tudjon egymas ellen jatszani gep-gep, ember-ember, es ember-gep.
 */
package example;

import static example.Menu.*;
import static example.Board.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Example extends JFrame implements ActionListener, MouseListener {

    static final Dimension sizeScreen = Toolkit.getDefaultToolkit().getScreenSize();
    static Timer timer, t;
    private static short coordLineX, coordLineY;
    static long startTime, stopTime;
    private static Graphics2D graphics;

    static byte rows, columns, menuHeight, sec, min, currentSimbol, top, bottom, left, right, fields[][];
    static short numberSteps, screenWidth, screenHeight, windowWidth, windowHeight, drawAreaX, drawAreaY, sumX, sumY;
    static Example e;

    public Example() {

        setLayout(null);

        addMenu();

        addMouseListener(this);
        setDefaultCloseOperation(3);
        setResizable(false);
        setTitle("0:00");
        setVisible(true);

        varInit();

        setLocation((screenWidth - windowWidth) >> 1, (screenHeight - windowHeight) >> 1);
        setSize(windowWidth, windowHeight);
        graphics = (Graphics2D) getGraphics();
    }

    private void addMenu() {
        createMenu();

        mFile.addActionListener(this);
        mHelp.addActionListener(this);
        mNewGame.addActionListener(this);

        miExit.addActionListener(this);
        miAbout.addActionListener(this);
        miTicTacToe.addActionListener(this);
        miOther.addActionListener(this);
        miCustom.addActionListener(this);

        setJMenuBar(mb);
    }

    private void varInit() {

        t = new Timer(1000, this);
        timer = new Timer(35, this);

        InitVar.initVar();

        top = (byte) getInsets().top;
        left = (byte) getInsets().left;
        right = (byte) getInsets().right;
        bottom = (byte) getInsets().bottom;

        menuHeight = (byte) getJMenuBar().getHeight();

        coordLineX = left;
        coordLineY = (short) (top + menuHeight);

        if ((windowHeight - top - bottom - menuHeight) != (windowWidth - left - right)) {
            windowWidth = (short) (windowHeight - top - bottom - menuHeight + left + right);
        }

        drawAreaX = (short) (windowWidth - left - right);
        drawAreaY = (short) (windowHeight - top - bottom - menuHeight);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        columns = Converter.conPixToCol(e.getX());
        Simbol.setCoordSimbolX(Converter.conColToPix(columns));

        rows = Converter.conPixToRow(e.getY());
        Simbol.setCoordSimbolY(Converter.conRowToPix(rows));

        System.out.println("MyStep, row = " + rows + ", column = " + columns);
        System.out.println("Coords: x = " + Simbol.getCoordSimbolX() + ", y = " + Simbol.getCoordSimbolY());

        if (isEnabled(rows, columns)) {
            Simbol.drawSimbol(graphics, Simbol.getCoordSimbolX(), Simbol.getCoordSimbolY());
            StepLogic.stepAI();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == t) {
            setTitle(min + ":" + (sec < 10 ? "0" : "") + sec);
            sec = (sec < 59 ? ++sec : 0);
            min = (sec != 0 ? min : ++min);
        } else if (e.getSource() != timer) {
            if (e.getSource() == miTicTacToe) {
                newGame((byte) 3);
            } else if (e.getSource() == miOther) {
                newGame((byte) 5);
            } else if (e.getSource() == miCustom) {
                //Open new Windows
                newGame((byte) 9);
            } else if (e.getSource() == miAbout) {
                //Open new Windows
            } else if (e.getSource() == miExit) {
                System.exit(3);
            }
        } else {
            drawBoard();
            timer.stop();

        }
    }

    void newGame(byte a) {
        varInit();
        setNumberLines(a);
        setTitle("0:00");
        update(graphics);
        drawBoard();
    }

    static boolean isEnabled(int a, int b) {
        return fields[a][b] == 0;
    }

    static Graphics2D getGraphic() {
        return graphics;
    }

    public static short getCoordLineX() {
        return coordLineX;
    }

    public static void setCoordLineX(short x) {
        coordLineX = x;
    }

    public static short getCoordLineY() {
        return coordLineY;
    }

    public static void setCoordLineY(short y) {
        coordLineY = y;
    }

    public static void main(String[] args) {
        e = new Example();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

//    void setNumberLines(byte a) {
//        numberLines = a;
//    }
}
