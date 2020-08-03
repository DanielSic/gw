package gw;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.Color;
import java.awt.Dimension;


public class Proiettile extends JComponent
{
  private static float G = 1e12f;
  private static float _m= 100;
  private int  _px=0;
  private int  _py=0;
  private int  _x=0;
  private int  _y=0;
  private int  _vx=0;
  private int  _vy=0;
  private float  _ax=0;
  private float  _ay=0;

  private boolean is = false;

  Proiettile(int x, int y)
  {
    _x=x;
    _y=y;
    is = true;
  }

  // public void Shoot(int vx, int vy)
  // {
  //   _vx = vx/10;
  //   _vy = vy/10;
  // }
  public void Shoot(int angle, double str)
  {
    _vx = (int)str*(int)Math.cos(Math.toRadians(angle));
    _vy = (int)str*(int)Math.sin(Math.toRadians(angle));
  }
  public void Forze(Sfera[] palle)
  {
    int distx, disty,dist;
    float forza,sint,cost;
    float fx=0;
    float fy=0;
    for ( Sfera palla : palle)
    {
      distx = (_x - palla.getX());
      disty = (_y - palla.getY());
      dist  = (int)Math.sqrt(Math.pow(distx,2)+Math.pow(disty,2));

      forza = G *_m* palla.getM() / (float)Math.pow(dist,2);
      System.out.println(forza);
      sint = disty/dist;
      cost = distx/dist;

      fx += forza*cost;
      fy += forza*sint;
    }
    _ax += (-fx/(_m));
    _ay += (-fy/(_m));
    System.out.println("ax: "+_ax+" ay: "+_ay);
  }

  public void Update()
  {
    // _vx = (_vx > 5)? 5: _vx;
    // _vy = (_vy > 5)? 5: _vy;
    _px = _x;
    _py = _y;
    _x  += _vx;
    _y  += _vy;
    _vx += (int)_ax*5;
    _vy += (int)_ay*5;
    _ax = 0;
    _ay = 0;
  }

  public int getPX()
  {
    return _px;
  }
  public int getPY()
  {
    return _py;
  }
  public int getX()
  {
    return _x;
  }
  public int getY()
  {
    return _y;
  }
  public boolean Ok()
  {
    return is;
  }
  @Override
  protected void paintComponent(Graphics g)
  {
    g.setColor(Color.YELLOW);
    Graphics2D g2 = (Graphics2D)g;
    Line2D punto = new Line2D.Float(_x,_y,_px,_py);
    //Line2D punto = new Line2D.Float(_x,_y,_x,_y);

    g2.draw(punto);
  }
  @Override
  public Dimension getPreferredSize()
  {
    int distx = Math.abs(_px-_x);
    int disty = Math.abs(_py-_y);
    return new Dimension(distx,disty);
  }
  public boolean Hit(Nave nave)
  {
    if ((_x < nave.getX() + 10 && _x > nave.getX() -10)&&(_y < nave.getY() + 10 && _y > nave.getY() -10))
    {
      System.out.println("Bersaglio Colpito in " + _x + " "+_y+" <3");
      return true;
    }
    return false;
  }
  public boolean Hit(Sfera[] palle)
  {
    for (Sfera i : palle)
    {
      if(Check(i))
      {
        return true;
      }
    }
    return false;
  }
  private boolean Check(Sfera palla)
  {
    int distx = (palla.getX()- _x);
    int disty = (palla.getY()- _y);
    int dist = (int)Math.sqrt(Math.pow(distx,2)+Math.pow(disty,2));
    if (dist <= palla.getR())
    {
      System.out.println("COLPITO PIANETA");
      return true;
    }
    return false;
  }
}
