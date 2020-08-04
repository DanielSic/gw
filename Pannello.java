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

class Pair
{
  private double _x;
  private double _y;
  Pair(double x, double y )
  {
    _x = x;
    _y = y;
  }

  public double getx()
  {
    return _x;
  }
  public double gety()
  {
    return _y;
  }

}
class Pannello extends JPanel implements ActionListener
{

  private Timer time = new Timer(500,this);
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
    txt.setValue(new Double(0));
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
    do
    {
      _ships[1] = new Nave(_hwidth*2, _hheigth*2);
      dist = Math.sqrt(Math.pow(_ships[0].getx() - _ships[1].getx(),2)+Math.pow(_ships[0].gety() - _ships[1].gety(),2));
      System.out.println(_ships[0].getx());
    } while (dist < 100f);
    for (int i = 0; i < 5 ; i++)
    {

      do
      {
        ball[i] = new Sfera();
      } while((ball[i].getx() + ball[i].getR() < _ships[0].getx() &&   ball[i].getx()-ball[i].getR()>_ships[0].getx())||(ball[i].gety() + ball[i].getR() <_ships[0].gety() && ball[i].gety() - ball[i].getR()>_ships[0].gety()));

    }
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
      double angle = (double)txt.getValue();
      double pewX = _ships[_focus].getx() + 10 + ((Math.cos(Math.toRadians(angle)))*20);
      double pewY = _ships[_focus].gety() + 10 + ((Math.sin(Math.toRadians(angle)))*20);
      pew = new Proiettile(pewX,pewY);
      pew.Shoot(angle,(double)txt2.getValue());
      _counter++;
      _focus=_counter%2;
    }
    if (pew != null)
    //	if(true)
    {
      if ((pew.Hit(_ships[0]))||(pew.Hit(_ships[1])))
      {
        double minx,miny,maxx,maxy;
        //repaint(pew.getx(),pew.gety(),pew.getPX(),pew.getPY());
        if (pew.getx() < pew.getPX())
        {
          minx = pew.getx();
          maxx = pew.getPX();
        } else
        {
          minx = pew.getPX();
          maxx = pew.getx();
        }
        if (pew.gety() < pew.getPY())
        {
          miny = pew.gety();
          maxy = pew.getPY();
        } else
        {
          miny = pew.getPY();
          maxy = pew.gety();
        }
        repaint((int)minx,(int)miny,(int)maxx,(int)maxy);
        System.out.println("YEEEEEEEET");
        pew = null;
      }else if ( pew.Hit(ball)) // si può mettere la condizione nell'if di sopra C:
      {
        double minx,miny,maxx,maxy;
        //repaint(pew.getx(),pew.gety(),pew.getPX(),pew.getPY());
        if (pew.getx() < pew.getPX())
        {
          minx = pew.getx();
          maxx = pew.getPX();
        } else
        {
          minx = pew.getPX();
          maxx = pew.getx();
        }
        if (pew.gety() < pew.getPY())
        {
          miny = pew.gety();
          maxy = pew.getPY();
        } else
        {
          miny = pew.getPY();
          maxy = pew.gety();
        }
        repaint((int)minx,(int)miny,(int)maxx,(int)maxy);
        pew = null;
      }else {
        //pew.Forze(ball);
        //pew.Update();
        pew.Update(Forze(pew.getx(),pew.gety(),ball));
        double minx,miny,maxx,maxy;
        //repaint(pew.getx(),pew.gety(),pew.getPX(),pew.getPY());
        if (pew.getx() < pew.getPX())
        {
          minx = pew.getx();
          maxx = pew.getPX();
        } else
        {
          minx = pew.getPX();
          maxx = pew.getx();
        }
        if (pew.gety() < pew.getPY())
        {
          miny = pew.gety();
          maxy = pew.getPY();
        } else
        {
          miny = pew.getPY();
          maxy = pew.gety();
        }
        repaint((int)minx,(int)miny,(int)maxx,(int)maxy);
        //repaint();
      }
    }
  }

  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    //navetta.paintIcon(this,g);
    Graphics2D g2d = (Graphics2D)g;
    double vertd, hord;
    vertd = (_y - _ships[_focus].gety())/3;
    hord = (_x - _ships[_focus].getx() )/3;

    g2d.drawImage(bg,0,0,null);
    Line2D lin = new Line2D.Double(_x, _y, 200, 200);
    // Line2D h = new Line2D.Double(200, 0, 200, 400);
    // Line2D v = new Line2D.Double(0, 200, 400, 200);
    Line2D lin2 = new Line2D.Double(_ships[_focus].getx()+10,_ships[_focus].gety()+10, _ships[_focus].getx()-hord, _ships[_focus].gety()-vertd);
    g2d.draw(lin2);
    //g2d.draw(h);
    //g2d.draw(v);
    //_icon.paintIcon(this,g2d,_sheep.getx()-10,_sheep.gety()-10);
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

  public Pair Forze(double x, double y, Sfera[] palle)
  {
    double distx;
    double disty;
    double dist;
    double f;
    double fx = 0;
    double fy = 0;
    for (Sfera i : palle)
    {
      distx = x - i.getx();
      disty = y - i.gety();
      dist = Math.sqrt(distx*distx + disty*disty);
      f = -pew.getM()*i.getM()/Math.pow(dist,2);
      fx += f * (double)(distx/dist);
      fy += f * (double)(disty/dist);
      System.out.println(pew.getM());
    }
    double ax = (fx);
    double ay = (fy);
    System.out.println(ax+", "+ay);
    return new Pair(ax,ay);
  }
}
