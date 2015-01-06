package example;

import static example.Example.*;

import javax.swing.*;

public class Menu {

    static void createMenu() {

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
    }

}
