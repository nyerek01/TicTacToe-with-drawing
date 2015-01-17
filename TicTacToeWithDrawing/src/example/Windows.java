package example;

import static example.Literals.Win;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

enum Literals {

    Win, Lose, Tie, Help, About, Custom, Options, CheckUpdates;
}

public class Windows extends JDialog implements ActionListener, KeyListener, Interface {

    String[] levels;
    JButton btOK, btCancel;
    JLabel lbText, lbRows, lbCols, lbLevel;
    JTextField tfRows, tfCols;
    JComboBox<String> cbLevel;
    Literals li;

    Windows(JRootPane tulajdonos, boolean modal, Literals l) {

        levels = new String[]{"Easy", "Medium", "Hard"};//Lehetne enumeracio, kesobb akar DB-bol is johetne
        li = l;

        btOK = new JButton("OK");
        btCancel = new JButton("Cancel");

        cbLevel = new JComboBox<>(levels);

        btOK.addActionListener(this);
        btCancel.addActionListener(this);

        lbText = new JLabel();
        lbRows = new JLabel("rows: ");
        lbCols = new JLabel("cols: ");
        lbLevel = new JLabel("level: ");

        tfRows = new JTextField();
        tfCols = new JTextField();

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
                setBounds(150, 150, 200, 200);//A szulo ablak kozepen is megjelenhetne
                lbText.setText(win);
                pn0.add(lbText);
                btOK.setText("Yes");
                btCancel.setText("No");
                pn1.add(btOK);
                pn1.add(btCancel);
                add(pn1, BorderLayout.PAGE_END);
                break;
            case Lose:
                setBounds(150, 150, 200, 200);
                lbText.setText(lose);
                pn0.add(lbText);
                btOK.setText("Yes");
                btCancel.setText("No");
                pn1.add(btOK);
                pn1.add(btCancel);
                add(pn1, BorderLayout.PAGE_END);
                break;
            case Tie:
                setBounds(150, 150, 200, 200);
                lbText.setText(tie);
                pn0.add(lbText);
                btOK.setText("Yes");
                btCancel.setText("No");
                pn1.add(btOK);
                pn1.add(btCancel);
                add(pn1, BorderLayout.PAGE_END);
                break;
            case Help:
                setBounds(150, 150, 200, 200);
                lbText.setText(help);
                lbText.setAutoscrolls(true);
                pn0.add(lbText);
                pn0.add(btOK);

                break;
            case About:
                setBounds(150, 150, 200, 200);
                lbText.setText(about);
                pn0.add(lbText);
                pn0.add(btOK);
                break;
            case Custom:
                setBounds(150, 150, 175, 150);
                setLayout(new GridLayout(0, 2));
                add(lbRows);
                add(tfRows);
                add(lbCols);
                add(tfCols);
                add(lbLevel);
                add(cbLevel);
                add(btOK);
                add(btCancel);
                break;
            case Options:
                setBounds(150, 150, 200, 200);
                lbText.setText(options);
                pn0.add(lbText);
                pn1.add(btOK);
                add(pn1, BorderLayout.PAGE_END);
                break;
            case CheckUpdates:
                setBounds(150, 150, 200, 200);
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
            try {
                byte r, c;
                String l;
                r = Byte.parseByte(tfRows.getText().trim());
                c = Byte.parseByte(tfCols.getText().trim());
                l = (String) cbLevel.getSelectedItem();
                if (r > 2 && c > 2 && r < 35 && c < 35) {//min. 3x3-as legyen, max. sajat dontes
                    Game.comp.setLevel(l);
                    Game.g.newGame(r, c);
                    dispose();
                } else if (r < 3 || c < 3) {
                    JOptionPane.showMessageDialog(rootPane, (r < 3) ? "A sorok" : "Az oszlopok" + " szama tul keves, 2-nel nagyobb es 35-nel kisebb, egesz szamot irjon be.", "Hibas parameterek", -1);
                } else if (r > 34 || c > 34) {
                    JOptionPane.showMessageDialog(rootPane, (r > 34) ? "A sorok" : "Az oszlopok" + " szama tul nagy, 2-nel nagyobb es 35-nel kisebb, egesz szamot irjon be.", "Hibas parameterek", -1);
                }
            } catch (Exception ex) {//NumberFormatException, IllegalArgumentException
                JOptionPane.showMessageDialog(rootPane, "2-nel nagyobb es 35-nel kisebb, egesz szamot irjon be.", "Hibas parameterek", -1);
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
