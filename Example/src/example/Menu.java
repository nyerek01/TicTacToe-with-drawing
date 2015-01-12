package example;

public class Menu implements Interface {

    static void createMenu() {

        mb.add(mFile);
        mb.add(mSettings);
        mb.add(mHelp);

        mFile.add(miUnDo);
        mFile.add(miSave);
        mFile.add(mNewGame);
        mFile.addSeparator();
        mFile.add(miExit);

        mNewGame.add(miReStart);
        mNewGame.add(miTicTacToe);
        mNewGame.add(miGomoku);
        mNewGame.addSeparator();
        mNewGame.add(miCustom);

        mSettings.add(miOptions);

        mHelp.add(miHelp);
        mHelp.add(miCheckUpdates);
        mHelp.add(miAbout);
    }

}
