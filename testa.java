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

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;



public class testa extends JFrame implements ActionListener {
    Pannello panel;

    public testa() {

        initUI();
    }

    private void initUI() {
        panel = new Pannello();
        add(panel);


        setResizable(false);
        pack();
        setVisible(true);
        //The pack() method causes this window to be sized to fit the preferred size and layouts of its children.

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

    protected JMenuBar createMenuBar() { //funzione che crea una menuBar
        JMenuBar menuBar = new JMenuBar();

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

        return menuBar;
    }

	//Action listener che risponde agli eventi della menubar
	@Override
	public void actionPerformed(ActionEvent e) {

        if ("new".equals(e.getActionCommand())) { //new
            System.out.println("new");
            remove(panel);
            panel = null;
            initUI();

        }
        if ("quit".equals(e.getActionCommand())) { //new
            System.out.println("quit");
            dispose();
            System.exit(0);
        }



    }


}
// class Texta extends JPanel
// {
//   JTextArea testo = new JTextArea(20, 5);
// }
