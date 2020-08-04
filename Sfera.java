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

  public Sfera()
  {
    super();
    _x = (Math.random() * 1080);
    _y = (Math.random() * 720);
    _r = (Math.random() * 140 + 10);
    _m = (Math.random() * (1000 - 100) + 100);
    double col = ((double)_m-100)/(900);
    colore = new Color((float)col,0f,(float)(1-col));
  }

  @Override
  protected void paintComponent(Graphics g)
  {
    g.setColor(colore);
    g.fillOval((int)_x,(int)_y,(int)_r,(int)_r);
  }

  @Override
  public Dimension getPreferredSize()
  {
    return new Dimension((int)_r,(int)_r);
  }
}
