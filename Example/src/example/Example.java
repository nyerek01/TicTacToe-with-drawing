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

    byte rows, columns, numberLines, menuHeight, sec, min, sizeSimbol, top, bottom, left, right, fields[][];
    short coordSimbolX, coordSimbolY, numberSteps, screenWidth, screenHeight, windowWidth, windowHeight, drawAreaX, drawAreaY, sizeSquareX, sizeSquareY, coordLineX, coordLineY, modX, modY;
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
        setDefaultCloseOperation(3);//EXIT_ON_CLOSE
        setBackground(Color.red);
        setResizable(false);
        setTitle("0:00");
        setVisible(true);

        variablesInit();

        if ((windowHeight - top - bottom) != (windowWidth - left - right)) {
            windowWidth = (short) (windowHeight - top - bottom + left + right);
        }
        drawAreaX = (short) (windowWidth - left - right);
        drawAreaY = (short) (windowHeight - top - bottom - menuHeight);

//        coordLineX = left;
//        coordLineY = (short) (top + menuHeight);
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
        numberLines = 17;
        sizeSimbol = 0;
        coordSimbolX = 0;
        coordSimbolY = 0;
        numberSteps = 0;
        nextStepIsX = true;
        win = false;

        fields = new byte[numberLines + 5][numberLines + 5];
        for (byte i = 0; i < fields.length; i++) {
            for (byte j = 0; j < fields.length; j++) {
                fields[i][j] = 0;//0 jelenti az ureset, 1 az X-et, 2 a O-t
            }
        }
        screenWidth = (short) sizeScreen.width;
        screenHeight = (short) sizeScreen.height;
        windowWidth = 608;
        windowHeight = 631;

        top = (byte) getInsets().top;
        left = (byte) getInsets().left;
        right = (byte) getInsets().right;
        bottom = (byte) getInsets().bottom;

        menuHeight = (byte) getJMenuBar().getHeight();

        coordLineX = left;
        coordLineY = (short) (top + menuHeight);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        boolean coordX0 = true, coordY0 = true;
        short sumX = (short) (left + drawAreaX);
        short sumY = (short) (top + drawAreaY);
        short varX = 0;
        short varY = 0;
        for (byte i = 0; i < numberLines && (coordX0 || coordY0); i++) {
            if (e.getX() < (varX += sumX) / numberLines + left && coordX0) {
                coordX0 = false;
                columns = i;
                coordSimbolX = (short) ((drawAreaX * (i + 1) / numberLines + left) - ((drawAreaX / numberLines) >> 1));
            }
            if (e.getY() < (varY += sumY) / numberLines + top && coordY0 && e.getY() > (top + menuHeight)) {
                coordY0 = false;
                rows = i;
                coordSimbolY = (short) ((drawAreaY * (i + 1) / numberLines + top) - ((drawAreaY / numberLines) >> 1) + menuHeight);
            }
        }
        if (fields[rows + 2][columns + 2] == 0 && !coordY0 && !coordX0) {
            drawSimbol(graphics, coordSimbolX, coordSimbolY);
        }
    }

    public void drawSimbol(Graphics g2, int x, int y) {
        Graphics2D g = (Graphics2D) g2;
        g.setStroke(new BasicStroke(3));
        System.out.println("x= " + x + ", y= " + y);
        if (nextStepIsX) {
            g.setColor(Color.RED);
            g.drawOval(x - (sizeSimbol >> 1), y - (sizeSimbol >> 1), sizeSimbol, sizeSimbol);
        } else {
            g.setColor(Color.BLACK);
            g.drawLine(x - (sizeSimbol >> 1), y - (sizeSimbol >> 1), x + (sizeSimbol >> 1), y + (sizeSimbol >> 1));
            g.drawLine(x - (sizeSimbol >> 1), y + (sizeSimbol >> 1), x + (sizeSimbol >> 1), y - (sizeSimbol >> 1));
        }
        fields[rows + 2][columns + 2] = (byte) ((nextStepIsX) ? 2 : 1);
        nextStepIsX = !nextStepIsX;
        if (++numberSteps > 2) {
            winCheck();
        }
    }

    void winCheck() {
        for (byte i = 2; i < 5 && !win; i++) {
            for (byte j = 2; j < 5 && !win; j++) {
                if (fields[i][j] != 0 && (fields[i][j] == fields[i - 1][j - 1] && fields[i][j] == fields[i + 1][j + 1] || fields[i][j] == fields[i - 1][j] && fields[i][j] == fields[i + 1][j] || fields[i][j] == fields[i][j - 1] && fields[i][j] == fields[i][j + 1])) {
                    System.out.println("Win");
                    win = true;
                }
            }
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
                newGame((byte)3);
            } else if (e.getSource() == miOther) {
                newGame((byte)5);
            } else if (e.getSource() == miCustom) {
                //Open new Windows
                newGame((byte)9);
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
