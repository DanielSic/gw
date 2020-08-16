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
  private int plan_def;
  private int w_def;
  private int h_def;
  private JSpinner spin_plan;
  private JSpinner spin_w;
  private JSpinner spin_h;
  private JCheckBox checkinb;
  private JCheckBox checkarr;

  Settings(Sad set)
  {
    _set = set;
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setFont(new Font("Verdana",Font.BOLD,14));
    setPreferredSize(new Dimension(600,400));

    JPanel panel = new JPanel();
    setLayout(new GridLayout(2,2,2,2));
    spin_plan = new JSpinner();
    plan_def = _set._planetnum;
    spin_w = new JSpinner();
    w_def = _set._W;
    spin_h = new JSpinner();
    h_def = _set._H;
    panel.add(new JLabel("Numero di pianeti"));
    panel.setPreferredSize(new Dimension(600,50));
    spin_plan.setValue(plan_def);
    panel.add(spin_plan);
    checkinb = new JCheckBox("Pianeta in mezzo");
    checkinb.setSelected(_set._inbetween);
    panel.add(checkinb);
    checkarr = new JCheckBox("Frecce");
    checkarr.setSelected(_set._frecce);
    panel.add(checkarr);
    add(panel);

    JPanel sotto = new JPanel();
    sotto.setPreferredSize(new Dimension(600,50));
    JButton bot = new JButton("Apply and Close");
    bot.addActionListener(this);
    sotto.add(new JLabel("Larghezza schermo:"));
    sotto.add(new JLabel("FTAFERA FENZA DI TE"));
    sotto.add(bot);
    add(sotto);

    setResizable(false);
    pack();
    setVisible(true);
    //The pack() method causes this window to be sized to fit the preferred size and layouts of its children.

    setTitle("GravityWars - Settings");
    setLocationRelativeTo(null);
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
      //System.out.println(spin_plan.getValue());
      _set._planetnum = (int)spin_plan.getValue();
      _set._inbetween = checkinb.isSelected();
      _set._frecce = checkarr.isSelected();
      _set._modified = true;
      dispose();
    }
  }
}
