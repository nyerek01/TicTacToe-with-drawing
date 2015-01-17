package example;

import java.awt.*;
import javax.swing.*;

public interface Interface {//Minden valtozo Static Final

    Dimension sizeScreen = Toolkit.getDefaultToolkit().getScreenSize();
    float perCent = 0.8f;
    String help = "<HTML>Help text. The game's rules:<br>1. Try to win.<br>2. Try harder.<br>3. Good luck.</HTML>", about = "<HTML>This is a sample text.<br>2015.01.12.<br>maybe the actual date? (getDate())<br>TicTacToe v1.2</HTML>", title = "Készítette: Somodi András && Dóra Dávid", checkUpdates = "Checking for Updates...", options = "<html>Under development<br>Cooming Soon</html>", win = "<html>You are the winer.<br>Are you want start a new game?<html>", lose = "<html>You are the loser.<br>Are you want start a new game?<html>", tie = "<html>The game was ended. Tie<br>Are you want start a new game?<html>", path = "./src/xml/", ext = ".xml";

    JPanel pn0 = new JPanel();
    JPanel pn1 = new JPanel();
    JMenuBar mb = new JMenuBar();
    JMenu mFile = new JMenu("File"), mSettings = new JMenu("Settings"), mHelp = new JMenu("Help"), mNewGame = new JMenu("New Game");
    JMenuItem miUnDo = new JMenuItem("Undo"), miSave = new JMenuItem("Save"), miOptions = new JMenuItem("Opitons..."), miExit = new JMenuItem("Exit"), miReStart = new JMenuItem("Restart"), miTicTacToe = new JMenuItem("TicTacToe"), miGomoku = new JMenuItem("Gomoku"), miCustom = new JMenuItem("Custom..."), miCheckUpdates = new JMenuItem("Check for Updates"), miHelp = new JMenuItem("Help"), miAbout = new JMenuItem("About...");
}
