package gw;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;


public class Sfera extends Abs
{

  private int  _r;
  private Color colore;

  public int getR()
  {
    return _r;
  }

  public Sfera()
  {
    super();
    _x = (int)(Math.random() * 1080);
    _y = (int)(Math.random() * 720);
    _r = (int)(Math.random() * 140 + 10);
    _m = (int)(Math.random() * (1000 - 100) + 100);
    double col = ((double)_m-100)/(900);
    colore = new Color((int)col,0,(int)(1-col));
  }

  @Override
  protected void paintComponent(Graphics g)
  {
    g.setColor(colore);
    g.fillOval((int)_x,(int)_y,_r,_r);
  }

  @Override
  public Dimension getPreferredSize()
  {
    return new Dimension(_r,_r);
  }
}
