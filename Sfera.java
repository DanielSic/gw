package gw;
import java.awt.*;
import javax.swing.*;


public class Sfera extends JComponent
{
  private int  _x;
  private int  _y;
  private int  _r;
  private float  _m;
  private int rag;
  private Color colore;
  public int getX()
  {
    return _x;
  }
  public int getY()
  {
    return _y;
  }
  public float getM()
  {
    return _m;
  }
  public int getR()
  {
    return _r;
  }

  public Sfera()
  {
    _x = (int)(Math.random() * 1080);
    _y = (int)(Math.random() * 720);
    _r = (int)(Math.random() * 140 + 10);
    // _r = (int)(Math.random() * (71492680 - 2439640) + 2439640);
    _m = (int)(Math.random() * (1000 - 100) + 100);
    // rag = 90*(_r-2439640/(71492680 - 2439640))+10
    float col = (_m-100)/(900);
    colore = new Color(col,0,1-col);
  }
  @Override
  protected void paintComponent(Graphics g)
  {
    g.setColor(colore);
    g.fillOval(_x,_y,_r,_r);
  }

  @Override
  public Dimension getPreferredSize()
  {
    return new Dimension(_r,_r);
  }
}
