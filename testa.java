package gw;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JLabel;

import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;



public class testa extends JFrame implements ActionListener,WindowListener{
    Pannello panel;
    Settings s;

    public testa() {

        initUI();
    }

    private void initUI() {
        panel = new Pannello();
        add(panel);


        setResizable(true);
        pack();
        setVisible(true);

        setTitle("GravityWars");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(createMenuBar());

    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            testa ex = new testa();
            ex.setVisible(true);
        });
    }

    protected JMenu createFileMenu(Pannello panne){
      JMenu fileMenu = new JMenu("File");
      JMenuItem save = new JMenuItem("Salva");
      save.setActionCommand("salva");
      save.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e)
          {
            panne.saveToFile();
          }
        }
      );
      fileMenu.add(save);
      JMenuItem load = new JMenuItem("Carica");
      load.setActionCommand("load");
      load.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e)
          {
            panne.readFromFile();
          }
        }
      );
      fileMenu.add(load);
      return fileMenu;
    }
    protected JMenu createFileMenu(LevelEditor panne){
      JMenu fileMenu = new JMenu("File");
      JMenuItem save = new JMenuItem("Salva");
      save.setActionCommand("salva");
      save.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e)
          {
            panne.saveToFile();
          }
        }
      );
      fileMenu.add(save);
      JMenuItem load = new JMenuItem("Carica");
      load.setActionCommand("load");
      load.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e)
          {
            panne.readFromFile();
          }
        }
      );
      fileMenu.add(load);
      return fileMenu;
    }
    protected JMenuBar createMenuBar() { //funzione che crea una menuBar
        JMenuBar menuBar = new JMenuBar();
        //Crea il primissimo menu
        menuBar.add(createFileMenu(panel));
        //Crea il primo menu.
        JMenu menu = new JMenu("Game");
        menu.setMnemonic(KeyEvent.VK_D);
        menuBar.add(menu);

        	//Crea il primo elemento del menu.
        	JMenuItem menuItem = new JMenuItem("New Game");
        	menuItem.setMnemonic(KeyEvent.VK_N);
        	menuItem.setAccelerator(KeyStroke.getKeyStroke(
        			KeyEvent.VK_N, ActionEvent.ALT_MASK));
        	menuItem.setActionCommand("new");
        	menuItem.addActionListener(this);
        	menu.add(menuItem);

          //Crea il secondo elemento del menu.
          menuItem = new JMenuItem("Settings");

          menuItem.setActionCommand("settings");
          menuItem.addActionListener(this);
          menu.add(menuItem);


        	//Crea il terzo elemento del menu.
        	menuItem = new JMenuItem("Quit");
        	menuItem.setMnemonic(KeyEvent.VK_Q);
        	menuItem.setAccelerator(KeyStroke.getKeyStroke(
        			KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        	menuItem.setActionCommand("quit");
        	menuItem.addActionListener(this);
        	menu.add(menuItem);

        //Crea il secondo menu.
        JMenu menu2 = new JMenu("Document 2");
        menu2.setMnemonic(KeyEvent.VK_D);
        menuBar.add(menu2);

        //Rendiamo utile il secondo menu per un po'
        menuItem = new JMenuItem("Level Editor");
        menuItem.setActionCommand("lvl");
        menuItem.addActionListener(this);
        menu2.add(menuItem);

        return menuBar;
    }

	//Action listener che risponde agli eventi della menubar
	@Override
	public void actionPerformed(ActionEvent e) {

        if ("new".equals(e.getActionCommand())) { //new

            panel.loadGame();

            panel.refreshUI();
            panel.repaint();

        }
        if ("settings".equals(e.getActionCommand()))
        {
          s = new Settings(panel.getSettings());
          s.addWindowListener(this);
          setEnabled(false);



        }
        if ("quit".equals(e.getActionCommand())) { //new
            dispose();
            System.exit(0);
        }
        if ( "lvl".equals(e.getActionCommand()))
        {
          JFrame lvl = new JFrame();
          lvl.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          LevelEditor edit = new LevelEditor();
          JMenuBar menubar = new JMenuBar();
          menubar.add(createFileMenu(edit));
          lvl.setJMenuBar(menubar);
          edit.setVisible(true);
          edit.setPreferredSize(new Dimension(400,400));
          lvl.add(edit);
          lvl.pack();
          lvl.setVisible(true);

        }
    }
  public void windowClosed(WindowEvent e)
  {
    setEnabled(true);
    panel.setSettings(s.getSad());
    pack();
  }
  public void windowDeactivated(WindowEvent e)
  {
  }

  public void windowActivated(WindowEvent e)
  {

  }

  public void windowClosing(WindowEvent e)
  {

  }

  public void windowIconified(WindowEvent e)
  {

  }

  public void windowDeiconified(WindowEvent e)
  {

  }

  public void windowOpened(WindowEvent e)
  {

  }


}
