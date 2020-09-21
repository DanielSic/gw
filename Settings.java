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
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

class Settings extends JFrame implements ActionListener
{
  private int _planets = 0;
  private Sad _set;
  private boolean lines = false;
  private JSpinner spin_plan;
  private JSpinner spin_w;
  private JSpinner spin_h;
  private JCheckBox checkinb;
  private JCheckBox checkarr;
  private JSpinner spin_img;

  Settings(Sad set)
  {
    _set = set;
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setFont(new Font("Verdana",Font.BOLD,14));
    setPreferredSize(new Dimension(600,400));

    JPanel panel = new JPanel();
    panel.setPreferredSize(new Dimension(600,50));

    GridBagLayout layout = new GridBagLayout();
    panel.setLayout(layout);
    GridBagConstraints gbc = new GridBagConstraints();

    spin_plan = new JSpinner(new SpinnerNumberModel(_set._planetnum,1,100,1));
    spin_plan.setValue(_set._planetnum);

    spin_w = new JSpinner(new SpinnerNumberModel(_set._W,400,3840,10));
    spin_w.setValue(_set._W);

    spin_h = new JSpinner(new SpinnerNumberModel(_set._H,200,2160,10));
    spin_h.setValue(_set._H);

    spin_img = new JSpinner(new SpinnerNumberModel(_set._imgEdge,20,100,1));
    spin_img.setValue(_set._imgEdge);

    JButton bot = new JButton("Apply and Close");
    bot.addActionListener(this);

    checkinb = new JCheckBox("Pianeta in mezzo");
    checkinb.setSelected(_set._inbetween);

    checkarr = new JCheckBox("Frecce");
    checkarr.setSelected(_set._frecce);

    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.ipady = 20;
    gbc.gridx = 0;
    gbc.gridy = 0;
    panel.add(new JLabel("Numero di pianeti: "),gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    panel.add(spin_plan,gbc);

    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.ipady = 20;
    gbc.gridx = 0;
    gbc.gridy = 1;
    panel.add(new JLabel("Larghezza: "),gbc);

    gbc.gridx = 1;
    gbc.gridy = 1;
    panel.add(spin_w,gbc);

    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.ipady = 20;
    gbc.gridx = 0;
    gbc.gridy = 2;
    panel.add(new JLabel("Altezza: "),gbc);

    gbc.gridx = 1;
    gbc.gridy = 2;
    panel.add(spin_h,gbc);

    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.ipady = 20;
    gbc.gridx = 0;
    gbc.gridy = 3;
    panel.add(new JLabel("Lato Navetta: "),gbc);

    gbc.gridx = 1;
    gbc.gridy = 3;
    panel.add(spin_img,gbc);

    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.ipady = 20;
    gbc.gridx = 0;
    gbc.gridy = 4;
    panel.add(checkinb,gbc);

    gbc.gridx = 1;
    gbc.gridy = 4;
    panel.add(checkarr,gbc);

    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 2;
    gbc.gridy = 5;
    panel.add(bot,gbc);

    add(panel);

    setResizable(false);
    pack();
    setVisible(true);

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
      _set._planetnum = (int)spin_plan.getValue();
      _set._inbetween = checkinb.isSelected();
      _set._frecce = checkarr.isSelected();
      _set._W = (int)spin_w.getValue();
      _set._H = (int)spin_h.getValue();
      _set._imgEdge = (int)spin_img.getValue();
      _set._modified = true;
      dispose();
    }
  }
}
