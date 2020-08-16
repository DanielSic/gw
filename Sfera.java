package gw;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;


public class Sfera extends Abs
{

  private double  _r;
  private Color colore;

  public double getR()
  {
    return _r;
  }

  public Sfera(int W, int H)
  {
    super();
    _r = (Math.random() * 140 + 10);
    _x = (Math.random() * (W-_r));
    _y = (Math.random() * (H-_r));
    _m = (Math.random() * (700 - 100) + 100);
    double col = ((double)_m-100)/(900);
    colore = new Color((float)col,0f,(float)(1-col));
  }

  public Sfera(double x, double y)
  {
    _r = (Math.random() * 140 + 10);
    _x = x-_r/2;
    _y = y-_r/2;
    _m = (Math.random() * (700 - 100) + 100);
    double col = ((double)_m-100)/(900);
    colore = new Color((float)col,0f,(float)(1-col));

  }
  public boolean isValid(Nave[] ships, Sfera[] pal,int len)
  {

    for (Nave ship : ships)
    {
      //Cerca il punto sul perimetro del quadrato più vicino al cerchio,
      // se dista meno del raggio del cerchio allora c'è intersezione
      double minx = (_x+_r/2 < ship.getx()+20)? _x+_r/2 : ship.getx()+20;
      double miny = (_y+_r/2 < ship.gety()+20)? _y+_r/2 : ship.gety()+20;
      double nearx = (ship.getx() > minx)? ship.getx() : minx;
      double neary = (ship.gety() > miny)? ship.gety() : miny;
      if (Math.sqrt(Math.pow(_x+_r/2-nearx,2)+Math.pow(_y+_r/2-neary,2))<= _r/2)
      {
        return false;
      }


    }
    for (int i = 0;i < len; i++)
    {
      double distx = (_x + _r/2) - (pal[i].getx()+pal[i].getR()/2);
      double disty = (_y + _r/2) - (pal[i].gety()+pal[i].getR()/2);
      double dist = Math.sqrt(distx*distx + disty*disty);
      System.out.println("ciao");
      if (dist <= ((_r/2f) + (pal[i].getR()/2f)))
      {
        return false;
      }
    }
    return true;
  }

  @Override
  protected void paintComponent(Graphics g)
  {
    g.setColor(colore);
    g.drawOval((int)_x,(int)_y,(int)_r,(int)_r);
    g.fillOval((int)_x,(int)_y,(int)_r,(int)_r);
  }

  @Override
  public Dimension getPreferredSize()
  {
    return new Dimension((int)_r,(int)_r);
  }
}
