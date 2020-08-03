package gw;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;

import javax.swing.JButton;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

class Pannello extends JPanel implements ActionListener
{

  private Timer time = new Timer(17,this);
  private int _counter = 0;
  private int _focus = 0;
  private Nave _ships[];
  private Proiettile pew;
  private Sfera ball[] = new Sfera[5];
  private int _hheigth = 400;
  private int _hwidth = 610;
  private int _x = 50;
  private int _y = 50;
  private int _raggio = 30;
  private Nave _sheep;
  private int _conteggio=0;

  private Image bg = new ImageIcon("gw/sfondo.jpg").getImage();

  JFormattedTextField txt = new JFormattedTextField();

  JFormattedTextField txt2 = new JFormattedTextField();
  JButton butt = new JButton("Enter");

  public Pannello()
  {
    txt.setFont(new Font("Verdana", Font.BOLD,14));
    txt.setValue(new Integer(0));
    txt.setOpaque(false);
    txt.setForeground(new Color(250,250,250));
    txt.setPreferredSize(new Dimension(100,20));
    add(txt);

    txt2.setFont(new Font("Verdana", Font.BOLD,14));
    txt2.setValue(new Double(0));
    txt2.setPreferredSize(new Dimension(100,20));
    txt2.setOpaque(false);
    txt2.setForeground(new Color(250,250,250));
    add(txt2);

    butt.addActionListener(this);
    add(butt);
    double dist;
    _ships = new Nave[2];

    _ships[0] = new Nave(_hwidth*2, _hheigth*2);
    _ships[1] = new Nave(_hwidth*2, _hheigth*2);
    do
    {
      dist = Math.sqrt(Math.pow(_ships[0].getX() - _ships[1].getX(),2)+Math.pow(_ships[0].getY() - _ships[1].getY(),2));
      _ships[1] = new Nave(_hwidth*2, _hheigth*2);
    } while ((float)dist < 100f);
    for (int i = 0; i < 5 ; i++)
    {

      do
      {
        ball[i] = new Sfera();
      } while((ball[i].getX() + ball[i].getR() < _ships[0].getX() &&   ball[i].getX()-ball[i].getR()>_ships[0].getX())||(ball[i].getY() + ball[i].getR() <_ships[0].getY() && ball[i].getY() - ball[i].getR()>_ships[0].getY()));

    }



    // addMouseMotionListener(new MouseAdapter()
    // {
    //   public void mouseDragged(MouseEvent evento)
    //   {   //Da Togliere

    //     // int distx = evento.getX() - _ships[_focus].getX()+10;
    //     // int disty = evento.getY() - _ships[_focus].getY()+10;
    //     // int a = Math.abs(distx);
    //     // int b = Math.abs(disty);
    //     // double tan =(float)disty/(float)distx;
    //     // double theta = Math.atan(tan);
    //     // if (distx*disty < 0)
    //     // {
    //     //   theta = Math.PI + theta;
    //     // }
    //     // if ( disty < 0)
    //     // {
    //     //   theta = Math.PI + theta;
    //     // }
    //     //
    //     // System.out.println("Angolo "+ Math.toDegrees(theta));
    //
    //       //-Fine
    //     freccia(evento.getX(),evento.getY());
    //   }
    // });

    // addMouseListener(new MouseAdapter()
    // {
    //   public void mouseReleased(MouseEvent evento)
    //   {
    //
    //     int distx = evento.getX() - _ships[_focus].getX()+10;
    //     int disty = evento.getY() - _ships[_focus].getY()+10;
    //     int a = Math.abs(distx);
    //     int b = Math.abs(disty);
    //     double tan =(float)disty/(float)distx;
    //     double theta = Math.atan(tan);
    //
    //
    //     if (distx*disty < 0)
    //     {
    //       theta = Math.PI + theta;
    //     }
    //     if ( disty < 0)
    //     {
          // theta = Math.PI + theta;
    //     }
    //
    //     int pewX = _ships[_focus].getX() + 10 - (int)((Math.cos(theta))*50);
    //     int pewY = _ships[_focus].getY() + 10 - (int)((Math.sin(theta))*50);
    //
    //
    //
    //     System.out.println("Sin " + (Math.sin(theta)));
    //     System.out.println("Cos " + (Math.cos(theta)));
    //
    //
    //     pew = new Proiettile(pewX,pewY);
    //     pew.Shoot(-distx,-disty);
    //     System.out.println("ANGOLO: " + Math.toDegrees(theta));
    //     System.out.println("Mouse Rilasciato " + evento.getX() + " " + evento.getY());
    //     _counter++;
    //     _focus = _counter%2;
    //   }
    // });
  }

  public Dimension getPreferredSize()
  {
    return new Dimension(_hwidth*2,_hheigth*2);
  }
  private void freccia(int x, int y)
  {
    if (x != _x || y!= _y)
    {
      int hord,vertd;
      _x = x;
      _y=y;
      _ships[_focus].getMouse(x,y);
      repaint();
    }
  }
  public void actionPerformed(ActionEvent evento)
  {
    if (evento.getSource() instanceof JButton) {
      //System.out.println(txt2.getValue());
      int angle = (Integer)txt.getValue();
      int pewX = _ships[_focus].getX() + 10 + (int)((Math.cos(angle))*50);
      int pewY = _ships[_focus].getY() + 10 + (int)((Math.sin(angle))*50);
      pew = new Proiettile(pewX,pewY);
      pew.Shoot(angle,(Double)txt2.getValue());
      _counter++;
      _focus=_counter%2;
    }
    if (pew != null)
    //	if(true)
    {
      if ((pew.Hit(_ships[0]))||(pew.Hit(_ships[1])))
      {
        System.out.println("YEEEEEEEET");
        pew = null;
      }else if ( pew.Hit(ball)) // si può mettere la condizione nell'if di sopra C:
      {
        pew = null;
      }else {
        //pew.Forze(ball);
        pew.Update();
        //repaint(pew.getX(),pew.getY(),pew.getPX(),pew.getPY());
        repaint(pew.getX(),pew.getY(),pew.getPX(),pew.getPY());
        //repaint();
      }
    }

    if ("new".equals(evento.getActionCommand())) { //new
        System.out.println("new");
    }
    if ("quit".equals(evento.getActionCommand())) { //new
        System.out.println("quit");
    }

  }

  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    //navetta.paintIcon(this,g);
    Graphics2D g2d = (Graphics2D)g;
    int vertd, hord;
    vertd = (_y - _ships[_focus].getY())/3;
    hord = (_x - _ships[_focus].getX() )/3;

    g2d.drawImage(bg,0,0,null);
    Line2D lin = new Line2D.Float(_x, _y, 200, 200);
    // Line2D h = new Line2D.Float(200, 0, 200, 400);
    // Line2D v = new Line2D.Float(0, 200, 400, 200);
    Line2D lin2 = new Line2D.Float(_ships[_focus].getX()+10,_ships[_focus].getY()+10, _ships[_focus].getX()-hord, _ships[_focus].getY()-vertd);
    g2d.draw(lin2);
    //g2d.draw(h);
    //g2d.draw(v);
    //_icon.paintIcon(this,g2d,_sheep.getX()-10,_sheep.getY()-10);
    _ships[0].paintComponent(g);
    _ships[1].paintComponent(g);


    for (Sfera balla : ball)
    {
      balla.paintComponent(g);
    }
    _conteggio++;
    if (pew != null)
    {
      pew.paintComponent(g);
    }
    time.start();
  }

  protected JMenuBar createMenuBar() { //funzione che crea una menuBar
        JMenuBar menuBar = new JMenuBar();

        //Crea il primo menu.
        JMenu menu = new JMenu("Document");
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



}
