package gw;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.Color;
import java.awt.Dimension;


public class Proiettile extends Abs
{
  private static double G = 1e6f;
  //private static double _m= 100;
  private double  _px=0;
  private double  _py=0;
  private double  _vx=0;
  private double  _vy=0;
  private double  _ax=0;
  private double  _ay=0;
  private boolean is = false;

  private double _dt, _dx, _dy;
  private double _vmod;

  Proiettile(double x, double y)
  {
    super();
    _x=x;
    _y=y;

    _m = 100;
    is = true;
  }

  // public void Shoot(int vx, int vy)
  // {
  //   _vx = vx/10;
  //   _vy = vy/10;
  // }
  public void Shoot(double angle, double str)
  {
    _vx = (str*Math.sin(Math.toRadians(angle)));
    _vy = -(str*Math.cos(Math.toRadians(angle)));
  }

  public void Update(Pair forze)
  {

    _dt=1;
    _px = _x;
    _py = _y;
    _vx += forze.getx()*_dt;
    _vy += forze.gety()*_dt;
    _vmod = Math.sqrt(Math.pow(_vx,2) + Math.pow(_vy,2));


    if( _vmod > 1){
      _dt = 1/_vmod;
      _dx += forze.getx()*_dt;
      _dy += forze.gety()*_dt;
    }
    _x  += _vx*_dt;
    _y  += _vy*_dt;
  }

  public double getPX()
  {
    return _px;
  }
  public double getPY()
  {
    return _py;
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
    Line2D punto = new Line2D.Double((int)_x,(int)_y,(int)_px,(int)_py);
    //Line2D punto = new Line2D.Double(_x,_y,_x,_y);

    g2.draw(punto);
  }
  @Override
  public Dimension getPreferredSize()
  {
    int distx = Math.abs((int)_px-(int)_x);
    int disty = Math.abs((int)_py-(int)_y);
    return new Dimension(distx,disty);
  }

  // public boolean Hit(Nave nave)
  // {
  //   if ((_x < nave.getx() + nave.getL() && _x > nave.getx())&&(_y < nave.gety() + nave.getL() && _y > nave.gety()))
  //   {
  //     System.out.println("Bersaglio Colpito in " + _x + " "+_y+" <3");
  //     return true;
  //   }
  //   return false;
  // }
  //
  // public boolean Hit(Sfera[] palle)
  // {
  //   for (Sfera i : palle)
  //   {
  //     if(Check(i))
  //     {
  //       return true;
  //     }
  //   }
  //   return false;
  // }
  //
  // private boolean Check(Sfera palla)
  // {
  //   double distx = Math.abs((palla.getx()+(palla.getR()/2))- _x);
  //   double disty = Math.abs((palla.gety()+(palla.getR()/2))- _y);
  //   double distq = Math.pow(distx,2)+Math.pow(disty,2);
  //   if (Math.sqrt(distq) <= palla.getR()/2)
  //   {
  //     System.out.println("COLPITO PIANETA: "+ _x + " " + _y+" "+Math.sqrt(distq));
  //     System.out.println(+ palla.getx() + " " + palla.gety()+" "+palla.getR());
  //
  //     return true;
  //   }
  //
  //   return false;
  // }

  public boolean Hit(Nave nave)
  {
    if ((_x < nave.getx() + nave.getL() && _x > nave.getx())&&(_y < nave.gety() + nave.getL() && _y > nave.gety()))
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
    double distx = Math.abs((palla.getx()+(palla.getR()/2))- _x);
    double disty = Math.abs((palla.gety()+(palla.getR()/2))- _y);
    double distq = Math.pow(distx,2)+Math.pow(disty,2);
    if (Math.sqrt(distq) <= palla.getR()/2)
    {
      System.out.println("COLPITO PIANETA: "+ _x + " " + _y+" "+Math.sqrt(distq));
      System.out.println(+ palla.getx() + " " + palla.gety()+" "+palla.getR());

      return true;
    }

    return false;
  }
}
