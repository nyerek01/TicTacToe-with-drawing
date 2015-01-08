        /*      GOMOKU

 Aktualis allas szamontartasa, pl.: 3-1, az eredmenyhez tartozo pont(ozasi)rendszer kidolgosasa.
 Perzisztens adattarolas (XML).
 A gepnek legyen valaszthato tudasszintje, akar randomtol kezdve a tokeletes strategia megvalositasaig.
 Lehessen valasztani szimbolumot (X,O, stb.), kezdolepest, legyen lehetoseg lepes visszavonasara.
 Tudjon egymas ellen jatszani gep-gep, ember-ember, es ember-gep.
 */
package example;

import static example.Menu.*;
import static example.InitVar.*;
import static example.Board.*;
import static example.Simbol.*;
import static example.StepLogic.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Example extends JFrame implements ActionListener, MouseListener {

    static final Dimension sizeScreen = Toolkit.getDefaultToolkit().getScreenSize();
    static Graphics graphics;
    static byte rows, columns, menuHeight, sec, min, currentSimbol, top, bottom, left, right, fields[][];
    static short numberSteps, screenWidth, screenHeight, windowWidth, windowHeight, drawAreaX, drawAreaY, coordLineX, coordLineY, sumX, sumY;
    static boolean nextStepIsX;
    static JMenuBar mb;
    static JMenu mFile, mHelp, mNewGame;
    static JMenuItem miExit, miAbout, miTicTacToe, miOther, miCustom;
    static Timer timer, t;
    static long startTime, stopTime;
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
        graphics = getGraphics();

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
        timer = new Timer(25, this);

        initVar();

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

        sumX = (short) (drawAreaX / numberLines);
        sumY = (short) (drawAreaY / numberLines);

    }

    @Override
    public void mouseReleased(MouseEvent e) {//Lehet hogy nem az egesz logika kene ide, ki lehetne szervezni?
        boolean coordX0 = true, coordY0 = true;
        short varX = 0;
        short varY = 0;

        for (byte i = 0; i < numberLines && (coordX0 || coordY0); i++) {
            if (e.getX() < (varX += sumX) + left && coordX0) {
                coordX0 = false;
                columns = i;
                coordSimbolX = (short) ((sumX * (i + 1) + left) - (sumX >> 1));
            }
            if (e.getY() < (varY += sumY) + top + menuHeight && coordY0 && e.getY() > (top + menuHeight)) {
                coordY0 = false;
                rows = i;
                coordSimbolY = (short) ((sumY * (i + 1) + top + menuHeight) - (sumY >> 1));
            }
        }
        System.out.println("MyStep, row = " + rows + ", column = " + columns);
        if (isEnabled(rows, columns) && !coordY0 && !coordX0) {
            drawSimbol(graphics, coordSimbolX, coordSimbolY);
            stepAI();
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

    void b() {
        stopTime = System.currentTimeMillis() + 500;
        tabla();
    }

    void tabla() {
        while (startTime != stopTime) {
            startTime = System.currentTimeMillis();
        }
        drawBoard();
    }

    static boolean isEnabled(int a, int b) {
        System.out.println("isEnabled = " + (fields[a][b] == 0));
        return fields[a][b] == 0;
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

    void setNumberLines(byte a) {
        numberLines = a;
    }

}
