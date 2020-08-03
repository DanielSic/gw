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
import java.awt.Graphics2D;

class Nave extends JComponent
{
  private int _x,_mx,_my;
  private ImageIcon _icon;
  private boolean focus = false;
  private int _y;
  Nave(int w, int h)
  {
    _icon = new ImageIcon("gw/starship.png");
    _x = (int)(Math.random() * (w-60))+30;
    _y = (int)(Math.random() * (h-60))+30;
  }

  Nave(int w, int h,String img)
  {
    _x = (int)(Math.random() * (w-60))+30;
    _y = (int)(Math.random() * (h-60))+30;
    _icon = new ImageIcon(img);
  }
  public int getX()
  {
    return _x;
  }
  public int getY()
  {
    return _y;
  }
  public void getMouse(int mx, int my)
  {
    _mx = mx;
    _my = my;
  }
  @Override
  protected void paintComponent(Graphics g)
  {

    _icon.paintIcon(this, g, _x,_y);
    if (focus)
    {
      int vertd, hord;
      vertd = (_my - _y-10);
      hord = (_mx - _x-10 );
      g.setColor(Color.WHITE);
      Graphics2D g2 = (Graphics2D)g;
      Line2D l2 = new Line2D.Float(_x+10,_y+10,_x-hord+10,_y-vertd+10);
      g2.draw(l2);
    }
  }
  @Override
  public Dimension getPreferredSize()
  {
    if (focus)
    return new Dimension(Math.abs(_x - _mx), Math.abs(_y-_my));
    return new Dimension(20,20);
  }
}
