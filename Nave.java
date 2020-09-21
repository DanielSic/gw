package gw;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.Image;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.Rectangle;

import java.awt.Graphics2D;

class Nave extends Abs
{
  private int _mx,_my;
  private boolean focus = false;
  private int _punteggio=0;
  // private int _l = 20;
  private Color _color;
  private double _angle;


  Nave(int w, int h)
  {
	   super();
     _icon = new ImageIcon("gw/starship.png");
    _x = (Math.random() * (w-60))+30;
    _y = (Math.random() * (h-60))+30;
    _r = 20;
  }

  Nave(int w, int h,String img)
  {
	  super();
    _icon = new ImageIcon(img);
    _x = (Math.random() * (w-60))+30;
    _y = (Math.random() * (h-60))+30;
    _r = 20;
  }

  public Color getColor()
  {
    return _color;
  }

  public void setColor(Color c)
  {
    _color = c;
  }

  public int getPunteggio()
  {
    return _punteggio;
  }

  public void getMouse(int mx, int my)
  {
    _mx = mx;
    _my = my;
  }

  public void rotate(double angle)
  {
    _angle = angle-45;
  }

  @Override
  protected void paintComponent(Graphics g)
  {
    Graphics2D g2 = (Graphics2D)g;
    g2.rotate(Math.toRadians(_angle),(int)_x+_r/2,(int)_y+_r/2);
    _icon.paintIcon(this, g, (int)_x,(int)_y);
    g2.rotate(Math.toRadians(-_angle),(int)_x+_r/2,(int)_y+_r/2);

    g2.drawOval((int)_x-(((int)(Math.sqrt(2)*(int)_r)-(int)_r)/2),(int)_y-(((int)(Math.sqrt(2)*(int)_r)-(int)_r)/2),(int)(Math.sqrt(2)*(int)_r),(int)(Math.sqrt(2)*(int)_r));
  }

  @Override
  public Dimension getPreferredSize()
  {
    // if (focus)
    // {
    //   return new Dimension((int)Math.abs(_x - _mx), (int)Math.abs(_y-_my));
    // }
    return new Dimension((int)_r,(int)_r);
  }

  public void increase()
  {
    _punteggio++;
  }

  public void Scale(int l)
  {
    _r = l;
    _icon = new ImageIcon(_icon.getImage().getScaledInstance(l,l,Image.SCALE_DEFAULT));
  }

  // public int getL()
  // {
  //   return _l;
  // }

  public Rectangle getRect()
  {
    return new Rectangle(
      (int)_x-(((int)(Math.sqrt(2)*(int)_r)-(int)_r)/2),(int)_y-(((int)(Math.sqrt(2)*(int)_r)-(int)_r)/2),(int)(Math.sqrt(2)*(int)_r),(int)(Math.sqrt(2)*(int)_r)
    );
  }
}
//Image newImage = yourimage.getScaledInstance(nuovalarghezza, nuovaaltezza,Image.SCALE_DEFAULT);
//ImageIcon newicon
