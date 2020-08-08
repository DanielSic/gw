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

class Settings extends JFrame implements ActionListener
{
  private int _planets = 0;
  private boolean lines = false;
  private int number;
  private JSpinner spin;
  Settings(int planets)
  {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    spin = new JSpinner();
    number = planets;

    setPreferredSize(new Dimension(400,600));
    setLayout(new FlowLayout());
    JPanel panel = new JPanel(new GridLayout(1,2,2,2));
    panel.add(new JLabel("Quello è un bottone"));
    JButton bot = new JButton("Save");
    bot.addActionListener(this);
    panel.add(bot);

    panel.setPreferredSize(new Dimension(400,400));
    add(panel);
    JPanel sotto = new JPanel();
    sotto.setPreferredSize(new Dimension(400,200));
    sotto.add(new JLabel("SONO SOTTO"));
    sotto.add(new JLabel("FTAFERA FENZA DI TE"));
    add(sotto);
    spin.setValue(number);
    panel.add(spin);

    setResizable(false);
    pack();
    setVisible(true);
    //The pack() method causes this window to be sized to fit the preferred size and layouts of its children.

    setTitle("GravityWars-Settings");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  public int getNumber()
  {
    return number;
  }
  @Override
  public void actionPerformed(ActionEvent evento)
  {
    if (evento.getSource() instanceof JButton)
    {
      //System.out.println(spin.getValue());
      number = (int)spin.getValue();
      dispose();
    }
  }
}
