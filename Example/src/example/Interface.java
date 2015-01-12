package example;

import java.awt.*;
import javax.swing.*;

public interface Interface {

    Dimension sizeScreen = Toolkit.getDefaultToolkit().getScreenSize();
    float perCent = 0.8f;
    String help = "<HTML>Help text. The game's rules:<br>1. Try to win.<br>2. Try harder.<br>3. Good luck.</HTML>", about = "<HTML>This is a sample text. 2015.01.12. maybe the actual date? (getDate())<br>TicTacToe v1.2</HTML>", title = "Keszitette: Dora David, Andras";

    static JMenuBar mb = new JMenuBar();
    static JMenu mFile = new JMenu("File"), mSettings = new JMenu("Settings"), mHelp = new JMenu("Help"), mNewGame = new JMenu("New Game");
    static JMenuItem miUnDo = new JMenuItem("Undo"), miSave = new JMenuItem("Save"), miOptions = new JMenuItem("Opitons..."), miExit = new JMenuItem("Exit"), miReStart = new JMenuItem("Restart"), miTicTacToe = new JMenuItem("TicTacToe"), miGomoku = new JMenuItem("Gomoku"), miCustom = new JMenuItem("Custom..."), miCheckUpdates = new JMenuItem("Check for Updates"), miHelp = new JMenuItem("Help"), miAbout = new JMenuItem("About...");
}
