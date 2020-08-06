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

class Settings extends JFrame //implements ActionListener
{
  private int _planets = 0;
  private boolean lines = false;
  Settings()
  {
    setPreferredSize(new Dimension(400,600));
    setLayout(new FlowLayout());
    JPanel panel = new JPanel(new GridLayout(1,2,2,2));
    panel.add(new JLabel("Quello è un bottone"));
    panel.add(new JButton("DIO"));

    panel.setPreferredSize(new Dimension(400,400));
    add(panel);
    JPanel sotto = new JPanel();
    sotto.setPreferredSize(new Dimension(400,200));
    sotto.add(new JLabel("SONO SOTTO"));
    sotto.add(new JLabel("FTAFERA FENZA DI TE"));
    add(sotto);


    setResizable(false);
    pack();
    setVisible(true);
    //The pack() method causes this window to be sized to fit the preferred size and layouts of its children.

    setTitle("GravityWars-Settings");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
