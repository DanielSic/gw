package gw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JComponent;

class Trajectory extends JComponent
{

   Color one = new Color(72, 160, 220);
   Color two  = new Color(255, 89, 230);
   int _focus;

  private ArrayList<Pair> traj;

  Trajectory(int focus)
  {
    _focus = focus;
    traj = new ArrayList<Pair>();
  }

  public void push(Pair p)
  {
    traj.add(p);
  }

  @Override
  protected void paintComponent(Graphics g)
  {
    if(_focus == 0){
      g.setColor(one);
    } else {
      g.setColor(two);
    }


    Graphics2D g2 = (Graphics2D)g;

    double px = traj.get(0).getx();
    double py = traj.get(0).gety();
    for (Pair p : traj)
    {
      Line2D punto = new Line2D.Double(p.getx(),p.gety(),px,py);
      px = p.getx();
      py = p.gety();
      g2.draw(punto);
    }
  }
}
