        /*      GOMOKU

 Aktualis allas szamontartasa, pl.: 3-1, az eredmenyhez tartozo pont(ozasi)rendszer kidolgosasa.
 Perzisztens adattarolas (XML).
 A gepnek legyen valaszthato tudasszintje, akar randomtol kezdve a tokeletes strategia megvalositasaig.
 Lehessen valasztani szimbolumot (X,O, stb.), kezdolepest, legyen lehetoseg lepes visszavonasara.
 Tudjon egymas ellen jatszani gep-gep, ember-ember, es ember-gep.
 */
package example;

import static example.Menu.*;
import static example.Game.*;
import static example.Interface.*;
import static example.WinCheck.getWin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener, MouseListener, Interface {

    private static Timer timer, t;
    private static short coordLineX, coordLineY;
    private static long startTime, stopTime;
    private static Graphics2D graphics;
    private static byte menuHeight, sec, min, top, bottom, left, right;
    private static short screenWidth, screenHeight, windowWidth, windowHeight, sumX, sumY;

    GUI() {

        setLayout(null);

        addMenu();

        addMouseListener(this);
        setDefaultCloseOperation(3);
        setResizable(false);
        setTitle(title + " 0:00");
        setVisible(true);

        varInit(b.getNumberLines());

        setLocation((screenWidth - windowWidth) >> 1, (screenHeight - windowHeight) >> 1);
        setSize(windowWidth, windowHeight);
        graphics = (Graphics2D) getGraphics();
    }

    private void addMenu() {
        createMenu();

        mFile.addActionListener(this);
        mSettings.addActionListener(this);
        mHelp.addActionListener(this);
        mNewGame.addActionListener(this);

        miUnDo.addActionListener(this);
        miSave.addActionListener(this);
        miExit.addActionListener(this);

        miReStart.addActionListener(this);
        miTicTacToe.addActionListener(this);
        miGomoku.addActionListener(this);
        miCustom.addActionListener(this);

        miOptions.addActionListener(this);

        miHelp.addActionListener(this);
        miCheckUpdates.addActionListener(this);
        miAbout.addActionListener(this);

        setJMenuBar(mb);
    }

    private void varInit(byte n) {

        InitVar.initVar(n);

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

        b.setDrawAreaX((short) (windowWidth - left - right));
        b.setDrawAreaY((short) (windowHeight - top - bottom - menuHeight));

        t = new Timer(1000, this);
        timer = new Timer(75, this);
        t.restart();
        timer.restart();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getY() > coordLineY) {

            b.setColumns(Converter.conPixToCol(e.getX()));
            Simbol.setCoordSimbolX(Converter.conColToPix(b.getColumns()));

            b.setRows(Converter.conPixToRow(e.getY()));
            Simbol.setCoordSimbolY(Converter.conRowToPix(b.getRows()));

            if (b.isEnabled(b.getRows(), b.getColumns())) {
                Simbol.drawSimbol(graphics, Simbol.getCoordSimbolX(), Simbol.getCoordSimbolY());
                if (!getWin()) {
                    AI.stepAI();
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == t) {
            setTitle(title + " " + min + ":" + (sec < 10 ? "0" : "") + sec);
            sec = (sec < 59 ? ++sec : 0);
            min = (sec != 0 ? min : ++min);
        } else if (e.getSource() != timer) {
            if (e.getSource() == miReStart) {
                newGame(b.getNumberLines());
            } else if (e.getSource() == miTicTacToe) {
                newGame((byte) 3);
            } else if (e.getSource() == miGomoku) {
                newGame((byte) 14);
            } else if (e.getSource() == miUnDo) {
                //emptyFields.remove();
            } else if (e.getSource() == miCustom) {
                new Windows(new JRootPane(), true, Literals.Custom);
            } else if (e.getSource() == miOptions) {
                new Windows(new JRootPane(), true, Literals.Options);
            } else if (e.getSource() == miSave) {
                //Serialization objects
            } else if (e.getSource() == miHelp) {
                new Windows(new JRootPane(), true, Literals.Help);
            } else if (e.getSource() == miCheckUpdates) {
                new Windows(new JRootPane(), true, Literals.CheckUpdates);
            } else if (e.getSource() == miAbout) {
                new Windows(new JRootPane(), true, Literals.About);
            } else if (e.getSource() == miExit) {
                System.exit(3);
            }
        } else {
            b.drawBoard();
            timer.stop();

        }
    }

    void newGame(byte a) {
        b.setNumberLines(a);
        varInit(a);
        setTitle("0:00");
        update(graphics);
        b.drawBoard();
    }

    static Graphics2D getGraphic() {
        return graphics;
    }

    static short getCoordLineX() {
        return coordLineX;
    }

    static void setCoordLineX(short x) {
        coordLineX = x;
    }

    static short getCoordLineY() {
        return coordLineY;
    }

    static void setCoordLineY(short y) {
        coordLineY = y;
    }

    static Timer getTimer() {
        return timer;
    }

    static void setTimer(Timer timer) {
        GUI.timer = timer;
    }

    static Timer getT() {
        return t;
    }

    static void setT(Timer t) {
        GUI.t = t;
    }

    static long getStartTime() {
        return startTime;
    }

    static void setStartTime(long startTime) {
        GUI.startTime = startTime;
    }

    static long getStopTime() {
        return stopTime;
    }

    static void setStopTime(long stopTime) {
        GUI.stopTime = stopTime;
    }

    static void setGraphics(Graphics2D graphics) {
        GUI.graphics = graphics;
    }

    static byte getMenuHeight() {
        return menuHeight;
    }

    static void setMenuHeight(byte menuHeight) {
        GUI.menuHeight = menuHeight;
    }

    static byte getSec() {
        return sec;
    }

    static void setSec(int sec) {
        GUI.sec = (byte) sec;
    }

    static byte getMin() {
        return min;
    }

    static void setMin(int min) {
        GUI.min = (byte) min;
    }

    static byte getTop() {
        return top;
    }

    static void setTop(byte top) {
        GUI.top = top;
    }

    static byte getBottom() {
        return bottom;
    }

    static void setBottom(byte bottom) {
        GUI.bottom = bottom;
    }

    static byte getLeft() {
        return left;
    }

    static void setLeft(byte left) {
        GUI.left = left;
    }

    static byte getRight() {
        return right;
    }

    static void setRight(byte right) {
        GUI.right = right;
    }

    static short getScreenWidth() {
        return screenWidth;
    }

    static void setScreenWidth(int screenWidth) {
        GUI.screenWidth = (short) screenWidth;
    }

    static short getScreenHeight() {
        return screenHeight;
    }

    static void setScreenHeight(int screenHeight) {
        GUI.screenHeight = (short) screenHeight;
    }

    static short getWindowWidth() {
        return windowWidth;
    }

    static void setWindowWidth(int windowWidth) {
        GUI.windowWidth = (short) windowWidth;
    }

    static short getWindowHeight() {
        return windowHeight;
    }

    static void setWindowHeight(int windowHeight) {
        GUI.windowHeight = (short) windowHeight;
    }

    static short getSumX() {
        return sumX;
    }

    static void setSumX(short sumX) {
        GUI.sumX = sumX;
    }

    static short getSumY() {
        return sumY;
    }

    static void setSumY(short sumY) {
        GUI.sumY = sumY;
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
}
