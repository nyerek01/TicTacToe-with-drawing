        /*      GOMOKU
        
 Aktualis allas szamontartasa, pl.: 3-1, az eredmenyhez tartozo pont(ozasi)rendszer kidolgosasa.
 Perzisztens adattarolas (XML).
 A gepnek legyen valaszthato tudasszintje, akar randomtol kezdve a tokeletes strategia megvalositasaig.
 Lehessen valasztani szimbolumot (X,O, stb.), kezdolepest, legyen lehetoseg lepes visszavonasara.
 Tudjon egymas ellen jatszani gep-gep, ember-ember, es ember-gep.
 */
package example;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Example extends JFrame implements ActionListener, MouseListener {

    static Dimension sizeScreen = Toolkit.getDefaultToolkit().getScreenSize();
    static Graphics graphics;

    byte rows, columns, numberLines, menuHeight, sec, min, sizeSimbol, top, bottom, left, right, currentSimbol, fields[][];
    short coordSimbolX, coordSimbolY, numberSteps, screenWidth, screenHeight, windowWidth, windowHeight, drawAreaX, drawAreaY, sizeSquareX, sizeSquareY, coordLineX, coordLineY, modX, modY, sumX, sumY;
    boolean nextStepIsX, win;
    JMenuBar mb;
    JMenu mFile, mHelp, mNewGame;
    JMenuItem miExit, miAbout, miTicTacToe, miOther, miCustom;
    Timer timer, t;
    long startTime, stopTime;

    public Example() {

        setLayout(null);

        createMenu();

        addMouseListener(this);
        setDefaultCloseOperation(3);
        setResizable(false);
        setTitle("0:00");
        setVisible(true);

        variablesInit();

        setLocation((screenWidth - windowWidth) >> 1, (screenHeight - windowHeight) >> 1);
        setSize(drawAreaX % numberLines == 0 ? ++windowWidth : windowWidth, drawAreaY % numberLines == 0 ? ++windowHeight : windowHeight);
        graphics = getGraphics();
        t = new Timer(1000, this);
        timer = new Timer(25, this);
        t.restart();
        timer.restart();
//        drawBoard();//Nem rajzolja ki a tablat
    }

    void createMenu() {

        mb = new JMenuBar();

        mFile = new JMenu("File");
        mHelp = new JMenu("Help");
        mNewGame = new JMenu("New Game");

        mb.add(mFile);
        mb.add(mHelp);

        miExit = new JMenuItem("Exit");
        miAbout = new JMenuItem("About");

        miTicTacToe = new JMenuItem("TicTacToe");
        miOther = new JMenuItem("Other");
        miCustom = new JMenuItem("Custom");

        mFile.add(mNewGame);

        mNewGame.add(miTicTacToe);
        mNewGame.add(miOther);
        mNewGame.add(miCustom);

        mFile.add(miExit);
        mHelp.add(miAbout);

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

    void variablesInit() {

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

        fields = new byte[numberLines + 8][numberLines + 8];
        for (byte i = 0; i < fields.length; i++) {
            for (byte j = 0; j < fields.length; j++) {
                fields[i][j] = 0;//0 ures, 1 X, 2 O
            }
        }
        screenWidth = (short) sizeScreen.width;
        screenHeight = (short) sizeScreen.height;
        windowWidth = 600;
        windowHeight = 600;

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
    public void mouseReleased(MouseEvent e) {
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
        if (isEnabled(rows + 4, columns + 4) && !coordY0 && !coordX0) {
            drawSimbol(graphics, coordSimbolX, coordSimbolY);
            stepAI();
        }
    }

    public void drawSimbol(Graphics g2, int x, int y) {
        Graphics2D g = (Graphics2D) g2;
        g.setStroke(new BasicStroke(3));
        if (nextStepIsX) {
            g.setColor(Color.RED);
            g.drawOval(x - (sizeSimbol >> 1), y - (sizeSimbol >> 1), sizeSimbol, sizeSimbol);
        } else {
            g.setColor(Color.BLACK);
            g.drawLine(x - (sizeSimbol >> 1), y - (sizeSimbol >> 1), x + (sizeSimbol >> 1), y + (sizeSimbol >> 1));
            g.drawLine(x - (sizeSimbol >> 1), y + (sizeSimbol >> 1), x + (sizeSimbol >> 1), y - (sizeSimbol >> 1));
        }
        currentSimbol = (byte) ((nextStepIsX) ? 2 : 1);
        fields[rows + 4][columns + 4] = currentSimbol;//1 X, 2 O
        nextStepIsX = !nextStepIsX;
        if (++numberSteps > (numberLines < 9 ? 4 : 8)) {
            winCheck();
        }
    }

    void winCheck() {

        for (byte i = 4; i < numberLines + 4 && !win; i++) {
            for (byte j = 4; j < numberLines + 4 && !win; j++) {
//        for (byte i = (byte) rows - 2; i <= rows + 2 && !win; i++) {
//            for (byte j = (byte) columns - 2; j <= columns + 2 && !win; j++) {//Optimalizalas, eleg lenne az aktualis lepes kornyezetet, szomszedait vizsgalni, mert ugyis csak akkor nyerhet hogy ahova rak, ott kijon valahogy az 5 egyforma
                if (fields[i][j] == currentSimbol) {
                    //tictactoe
                    if (numberLines < 9) {
                        if (fields[i][j] == fields[i][j - 1] && fields[i][j] == fields[i][j + 1]
                                || fields[i][j] == fields[i - 1][j] && fields[i][j] == fields[i + 1][j]
                                || fields[i][j] == fields[i - 1][j - 1] && fields[i][j] == fields[i + 1][j + 1]
                                || fields[i][j] == fields[i - 1][j + 1] && fields[i][j] == fields[i + 1][j - 1]) {
                            JOptionPane.showMessageDialog(rootPane, (currentSimbol == 1 ? "X" : "O") + " nyertel!", "A jatek veget ert.", JOptionPane.PLAIN_MESSAGE);
                            win = true;
                        }
                    } //gomoku
                    else {
                        if (rootPaneCheckingEnabled) {
                            if (fields[i][j] == fields[i][j - 1] && fields[i][j] == fields[i][j + 1]
                                    && fields[i][j] == fields[i][j - 2] && fields[i][j] == fields[i][j + 2]
                                    || fields[i][j] == fields[i - 1][j] && fields[i][j] == fields[i + 1][j]
                                    && fields[i][j] == fields[i - 2][j] && fields[i][j] == fields[i + 2][j]
                                    || fields[i][j] == fields[i - 1][j - 1] && fields[i][j] == fields[i + 1][j + 1]
                                    && fields[i][j] == fields[i - 2][j - 2] && fields[i][j] == fields[i + 2][j + 2]
                                    || fields[i][j] == fields[i - 1][j + 1] && fields[i][j] == fields[i + 1][j - 1]
                                    && fields[i][j] == fields[i - 2][j + 2] && fields[i][j] == fields[i + 2][j - 2]) {
                                JOptionPane.showMessageDialog(rootPane, (currentSimbol == 1 ? "X" : "O") + " nyertel!", "A jatek veget ert.", JOptionPane.PLAIN_MESSAGE);
                                win = true;
                            }
                        }
                    }
                }
            }
        }
        if (win) {
            newGame(numberLines);
        }
    }

    void stepAI() {
        boolean AI = true;
        int i=0,j=0;
        for (byte b = 4; b < fields.length - 4 && AI; b++) {
            for (byte c = 4; c < fields.length - 4 && AI; c++) {
                if (fields[b][c] == 0) {
                    fields[b][c] = 2;//O, mert a jatekos van az X-el
                    coordSimbolX = (short) ((b - 3) * sumX + left - (sumX >> 1));
                    coordSimbolY = (short) ((c - 3) * sumY + top + menuHeight - (sumY >> 1));
                    i=b;
                    j=c;
                    System.out.println("b= " + b + ", c= " + c);
                    AI = false;
                }
            }
        }
        System.out.println("sumX= " + sumX + ", sumY= " + sumY);
        System.out.println("coordSimbolX= " + coordSimbolX + ", coordSimbolY= " + coordSimbolY);
        if (isEnabled(i, j)) {
            drawSimbol(graphics, coordSimbolX, coordSimbolY);
        }
    }

    private void drawBoard() {

        sizeSquareX = (short) (drawAreaX / numberLines);
        modX = (short) (drawAreaX % numberLines);
        sizeSquareY = (short) (drawAreaY / numberLines);
        modY = (short) (drawAreaY % numberLines);
        sizeSimbol = (byte) (sizeSquareX >> 1);

        Graphics2D g2 = (Graphics2D) graphics;
        g2.setStroke(new BasicStroke(1));
        g2.setColor(Color.BLACK);
        for (byte i = 1; i < numberLines; i++) {
            g2.drawLine(coordLineX += sizeSquareX, top + menuHeight, coordLineX, windowHeight - bottom);
            g2.drawLine(left, coordLineY += sizeSquareY, drawAreaX, coordLineY);
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
        variablesInit();
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

    boolean isEnabled(int a, int b) {
        return fields[a][b] == 0;
    }

    public static void main(String[] args) {
        Example e = new Example();
//        System.out.println("o= " + EnumSimbols.o);
//        System.out.println("state = " + EnumStates.playing);
//        e.drawBoard();//Nem rajzolja ki a tablat
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
        this.numberLines = a;
    }

}
