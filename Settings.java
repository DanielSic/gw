package gw;
import java.awt.Dimension;
import java.awt.EventQueue;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;

class Settings extends JFrame implements ActionListener
{
  private int _planets = 0;
  private Sad _set;
  private boolean lines = false;
  private int number;
  private JSpinner spin;
  private JCheckBox checkinb;
  private JCheckBox checkarr;
  Settings(Sad set)
  {
    _set = set;
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setFont(new Font("Verdana",Font.BOLD,14));

    spin = new JSpinner();
    number = _set._planetnum;

    setPreferredSize(new Dimension(400,600));
    setLayout(new FlowLayout());
    JPanel panel = new JPanel(new GridLayout(1,2,2,2));
    panel.add(new JLabel("Numero di pianeti"));
    JButton bot = new JButton("Apply and Close");
    bot.addActionListener(this);

    panel.setPreferredSize(new Dimension(400,100));
    add(panel);
    JPanel sotto = new JPanel();
    sotto.setPreferredSize(new Dimension(400,200));
    sotto.add(new JLabel("SONO SOTTO"));
    sotto.add(new JLabel("FTAFERA FENZA DI TE"));
    sotto.add(bot);
    add(sotto);
    spin.setValue(number);
    panel.add(spin);
    panel.add(new JLabel("Pianeta in mezzo"));
    checkinb = new JCheckBox();
    checkinb.setSelected(_set._inbetween);
    panel.add(checkinb);
    checkarr = new JCheckBox("Frecce ");
    checkarr.setSelected(_set._frecce);
    panel.add(checkarr);

    setResizable(false);
    pack();
    setVisible(true);
    //The pack() method causes this window to be sized to fit the preferred size and layouts of its children.

    setTitle("GravityWars-Settings");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  public Sad getSad()
  {
    return _set;
  }
  @Override
  public void actionPerformed(ActionEvent evento)
  {
    if (evento.getSource() instanceof JButton)
    {
      //System.out.println(spin.getValue());
      _set._planetnum = (int)spin.getValue();
      _set._inbetween = checkinb.isSelected();
      _set._frecce = checkarr.isSelected();
      dispose();
    }
  }
}
