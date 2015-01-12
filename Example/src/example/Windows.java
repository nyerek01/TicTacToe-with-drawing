package example;

import static example.Literals.Win;
import java.awt.event.*;
import javax.swing.*;

enum Literals {

    Win, Help, About, Custom, Options, CheckUpdates;
}

public class Windows extends JDialog implements ActionListener, KeyListener, Interface {

    JButton btOk, btCancel;
    JLabel lbtext;

    Windows(JRootPane tulajdonos, boolean modal, Literals l) {

        btOk = new JButton("Ok");
        btCancel = new JButton("Cancel");
        lbtext = new JLabel(l.name());
        whichWindow(l);
//        add(lbtext);//Kell panel, elrendezes manager stb....
//        add(btOk);
        add(btCancel);
        setResizable(false);
        setTitle(l.name());
        setVisible(true);
    }

    void whichWindow(Literals l) {
        switch (l) {
            case Win:
                setBounds(150, 150, 200, 200);
                System.out.println("Win");
                break;
            case Help:
                setBounds(150, 150, 200, 200);
                System.out.println("Help");
                lbtext.setText(help);
                lbtext.setAutoscrolls(true);

                break;
            case About:
                setBounds(150, 150, 200, 200);
                System.out.println("About");
                lbtext.setText(about);
                break;
            case Custom:
                setBounds(150, 150, 200, 200);
                System.out.println("Custom");
                break;
            case Options:
                setBounds(150, 150, 200, 200);
                System.out.println("Options");
                break;
            case CheckUpdates:
                setBounds(150, 150, 200, 200);
                System.out.println("CheckUpdates");
                break;
            default:
                throw new AssertionError();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btOk) {
            System.out.println("btOk");
        } else if (e.getSource() == btCancel) {
            System.out.println("btCancel");
        } else if (e.getSource() == btOk) {
            System.out.println("btOk");
        } else if (e.getSource() == btCancel) {
            System.out.println("btCancel");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        dispose();
    }

}
