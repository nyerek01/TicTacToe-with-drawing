package example;

import static example.Literals.Win;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

enum Literals {

    Win, Lose, Tie, Help, About, Custom, Options, CheckUpdates;
}

public class Windows extends JDialog implements ActionListener, KeyListener, Interface {

    JButton btOK, btCancel;
    JLabel lbText, lbRows, lbCols, lbLevel;
    JTextField tfRows, tfCols, tfLevel;
    Literals li;

    Windows(JRootPane tulajdonos, boolean modal, Literals l) {

        li = l;

        btOK = new JButton("OK");
        btCancel = new JButton("Cancel");

        btOK.addActionListener(this);
        btCancel.addActionListener(this);

        lbText = new JLabel();
        lbRows = new JLabel("rows: ");
        lbCols = new JLabel("cols: ");
        lbLevel = new JLabel("level: ");

        tfRows = new JTextField();
        tfCols = new JTextField();
        tfLevel = new JTextField();

        pn0.removeAll();
        pn1.removeAll();

        whichWindow(l);

        add(pn0, BorderLayout.PAGE_START);

        setResizable(false);
        setTitle(l.name());
        setVisible(true);
    }

    private void whichWindow(Literals l) {
        switch (l) {
            case Win:
                setBounds(150, 150, 200, 200);
                System.out.println("Win");
                lbText.setText(win);
                pn0.add(lbText);
                btOK.setText("Igen");
                btCancel.setText("Nem");
                pn1.add(btOK);
                pn1.add(btCancel);
                add(pn1, BorderLayout.PAGE_END);
                break;
            case Lose:
                setBounds(150, 150, 200, 200);
                System.out.println("Lose");
                lbText.setText(lose);
                pn0.add(lbText);
                btOK.setText("Igen");
                btCancel.setText("Nem");
                pn1.add(btOK);
                pn1.add(btCancel);
                add(pn1, BorderLayout.PAGE_END);
                break;
            case Tie:
                setBounds(150, 150, 200, 200);
                System.out.println("Tie");
                lbText.setText(tie);
                pn0.add(lbText);
                btOK.setText("Igen");
                btCancel.setText("Nem");
                pn1.add(btOK);
                pn1.add(btCancel);
                add(pn1, BorderLayout.PAGE_END);
                break;
            case Help:
                setBounds(150, 150, 200, 200);
                System.out.println("Help");
                lbText.setText(help);
                lbText.setAutoscrolls(true);
                pn0.add(lbText);
                pn0.add(btOK);

                break;
            case About:
                setBounds(150, 150, 200, 200);
                System.out.println("About");
                lbText.setText(about);
                pn0.add(lbText);
                pn0.add(btOK);
                break;
            case Custom:
                setBounds(150, 150, 175, 150);
                System.out.println("Custom");
                setLayout(new GridLayout(0, 2));
                add(lbRows);
                add(tfRows);
                add(lbCols);
                add(tfCols);
                add(lbLevel);
                add(tfLevel);
                add(btOK);
                add(btCancel);
                break;
            case Options:
                setBounds(150, 150, 200, 200);
                System.out.println("Options");
                lbText.setText(options);
                pn0.add(lbText);
                pn1.add(btOK);
                add(pn1, BorderLayout.PAGE_END);
                break;
            case CheckUpdates:
                setBounds(150, 150, 200, 200);
                System.out.println("CheckUpdates");
                lbText.setText(checkUpdates);
                pn0.add(lbText);
                pn1.add(btOK);
                add(pn1, BorderLayout.AFTER_LAST_LINE);
                break;
            default:
                setBounds(150, 150, 200, 200);
                System.out.println("Default Window");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btOK && li == Literals.Custom) {
            byte r, c;
            String l;
            r = Byte.parseByte(tfRows.getText());
            c = Byte.parseByte(tfCols.getText());
            l = tfLevel.getText();
            System.out.println("NewGame, r = " + r + ", c = " + c);
            if (r > 2 && c > 2 && r < 25 && c < 25) {//min. 3x3-as legyen, max. sajat dontes
                Game.comp.setLevel(l);
                Game.g.newGame(r, c);
                dispose();
            }
        } else if (e.getSource() == btOK && (li == Literals.Win || li == Literals.Lose || li == Literals.Tie)) {
            Game.g.newGame(Game.b.getNumberLines());
            dispose();
        } else {
            dispose();
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
