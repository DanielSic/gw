package gw;
import java.io.*;

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
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;

import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.Rectangle;

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
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.BorderFactory;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import java.util.ArrayList;
class LevelEditor extends JPanel implements java.io.Serializable {
  ArrayList<Sfera> balle;
  Nave _ships[] = new Nave[2];
  Sad _set = new Sad();
  Sfera s;
  protected Image bg = new ImageIcon("gw/sfondo.jpg").getImage();

  LevelEditor()
  {
    balle = new ArrayList<Sfera>();
    _ships[0] = new Nave(_set._W, _set._H);
    double dist;
    do
    {
      _ships[1] = new Nave(_set._W, _set._H,"gw/20x20spshp.png");
      dist = Math.sqrt(Math.pow(_ships[0].getx() - _ships[1].getx(),2)+Math.pow(_ships[0].gety() - _ships[1].gety(),2));
      // System.out.println(_ships[0].getx());
    } while (dist < 2*_set._imgEdge+100f);


    //super.removeAll(); O questo o l'override di initUI,
    // Non sono aancora completamente convinto di volere che loadgame sia un child di pannello
    addMouseListener(new MouseAdapter(){
      public void mouseClicked(MouseEvent e)
      {
        if (e.getClickCount() == 2)
        {
          addSfera(e.getX(),e.getY());
        }
      
      }
    });
    addMouseMotionListener(new MouseMotionAdapter(){
      Nave target = null;
      public void mouseDragged(MouseEvent e)
      {
        if (target == null){
          target = onShip(e);
        }else{
          target.setx(e.getX()-target.getL()/2);
          target.sety(e.getY()-target.getL()/2);
        }
        repaint();
        addMouseListener(new MouseAdapter()
        {
          public void mouseReleased(MouseEvent e)
          {
            target = null;
          }
        });
      }
    });
  }

  public void addSfera(int x, int y)
  {
    s = new Sfera();
    s.setx((double)x-s.getR()/2);
    s.sety((double)y-s.getR()/2);
    // s.addMouseListener(new MouseAdapter(){
    //   public void mouseClicked(MouseEvent e)
    //   {
    //
    //     // double distx = e.getX() - (s.getx()-s.getR()/2);
    //     // s.setR(distx+10);
    //     repaint();
    //
    //   }
    // });
    Sfera b[] = new Sfera[balle.size()];
    b= balle.toArray(b);
    if (s.isValid(_ships,b,balle.size())){balle.add(s);}
    repaint();

  }
  public Sfera onPlanet(MouseEvent e)
  {
    for (Sfera s : balle)
    {
      if (Math.sqrt(Math.pow(e.getX()-s.getx()-s.getR()/2,2)+Math.pow(e.getY()-s.gety()-s.getR()/2,2)) <= s.getR()/2)
      {
        System.out.println("Bullseye");
        return s;
      }
    }
    return null;
  }
  public Nave onShip(MouseEvent e)
  {
    for (Nave s : _ships)
    {
      if ((e.getX() >= s.getx() && e.getX() <=s.getx()+s.getL())&&(e.getY() > s.gety() && e.getY() <= s.gety()+s.getL()))
      {
        System.out.println("NAVE");
        return s;
      }
    }
    return null;
  }
  @Override
  protected void paintComponent(Graphics g)
  {

      super.paintComponent(g);
      //navetta.paintIcon(this,g);
      Graphics2D g2d = (Graphics2D)g;


      g2d.drawImage(bg,0,0,null);

      _ships[0].paintComponent(g);
      _ships[1].paintComponent(g);

      for (Sfera balla : balle)
      {
        balla.paintComponent(g);
      }


  }
  public Dimension getPreferredSize()
  {
    return new Dimension(_set._W,_set._H);
  }

}
