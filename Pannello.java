package gw;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

class Pair
{
  private double _x;
  private double _y;
  Pair(double x, double y )
  {
    _x = x;
    _y = y;
  }

  public double getx()
  {
    return _x;
  }
  public double gety()
  {
    return _y;
  }
  public void setx(double x)
  {
    _x = x ;
  }
  public void sety(double y)
  {
    _x = y ;
  }
  
  

}

class Trajectory extends JComponent
{
  private ArrayList<Pair> traj;
  Trajectory()
  {
    traj = new ArrayList<Pair>();
  }
  public void push(Pair p)
  {
    traj.add(p);
  }
  @Override
  protected void paintComponent(Graphics g)
  {

    g.setColor(new Color(0f,0.9f,0.9f,0.4f));
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
    //Line2D punto = new Line2D.Double(_x,_y,_x,_y);

  }

}
class Pannello extends JPanel implements ActionListener
{

  private Timer time = new Timer(20,this);
  private int _counter = 0;
  private int _focus = 0;
  private Nave _ships[];
  private static int h=1200;
  private static int w=1200;
  Pair[][] _ForceMatrix = new Pair[h][w];
  private Proiettile pew;
  private int _planetnum = 5;
  private Sfera ball[]; ;
  private int _hheigth = 400;
  private int _hwidth = 610;
  private int _x = 50;
  private int _y = 50;
  private int _raggio = 30;
  private Nave _sheep;
  private int _conteggio=0;

  private Settings s ;

  private ArrayList<Trajectory> _tr = new ArrayList<Trajectory>();
  private Trajectory _current ;
  private Image bg = new ImageIcon("src/sfondo.jpg").getImage();


  private JFormattedTextField angles[] = new JFormattedTextField[2];
  private JFormattedTextField forces[] = new JFormattedTextField[2];

  private JButton butts[] = new JButton[2];

  private JLabel labels[] = new JLabel[2];
  private int _points[] = {0,0};

  public Pannello()
  {

    initUI();

    loadGame();


  }

  private void initUI()
  {
    angles[0] = new JFormattedTextField();
    angles[1] = new JFormattedTextField();
    forces[0] = new JFormattedTextField();
    forces[1] = new JFormattedTextField();

    setLayout(null);
    angles[0].setFont(new Font("Verdana", Font.BOLD,14));
    angles[0].setValue(new Double(0));
    angles[0].setOpaque(false);
    angles[0].setForeground(new Color(250,250,250));
    //one_angle.setPreferredSize(new Dimension(100,20));
    angles[0].setBounds(100,700,100,20);
    add(angles[0]);

    forces[0].setFont(new Font("Verdana", Font.BOLD,14));
    forces[0].setValue(new Double(0));
    forces[0].setOpaque(false);
    forces[0].setForeground(new Color(250,250,250));
    forces[0].setBounds(100,720,100,20);
    add(forces[0]);

    butts[0] = new JButton("Shoot");
    butts[0].addActionListener(this);
    butts[0].setBounds(100,740,100,20);
    add(butts[0]);

    angles[1].setFont(new Font("Verdana", Font.BOLD,14));
    angles[1].setValue(new Double(0));
    angles[1].setOpaque(false);
    angles[1].setForeground(new Color(250,250,250));
    angles[1].setBounds(1040,700,100,20);
    add(angles[1]);

    forces[1].setFont(new Font("Verdana", Font.BOLD,14));
    forces[1].setValue(new Double(0));
    forces[1].setOpaque(false);
    forces[1].setForeground(new Color(250,250,250));
    forces[1].setBounds(1040,720,100,20);
    add(forces[1]);

    butts[1] = new JButton("Shoot");
    butts[1].addActionListener(this);
    butts[1].setBounds(1040,740,100,20);
    butts[1].setEnabled(false);
    add(butts[1]);
    labels[0] = new JLabel(Integer.toString(_points[0]));
    labels[0].setFont(new Font("Verdana", Font.BOLD,40));

    labels[0].setBounds(1000,50,100,100);
    add(labels[0]);
    labels[1] = new JLabel(Integer.toString(_points[1]));
    labels[1].setFont(new Font("Verdana", Font.BOLD,40));

    labels[1].setBounds(1100,50,100,100);
    add(labels[1]);
  }
  private void loadGame()
  {
    ball = new Sfera[_planetnum];
    double dist;
    _ships = new Nave[2];

    _ships[0] = new Nave(_hwidth*2, _hheigth*2);
    do
    {
      _ships[1] = new Nave(_hwidth*2, _hheigth*2);
      dist = Math.sqrt(Math.pow(_ships[0].getx() - _ships[1].getx(),2)+Math.pow(_ships[0].gety() - _ships[1].gety(),2));
      // System.out.println(_ships[0].getx());
    } while (dist < 100f);
    for (int i = 0; i < _planetnum ; i++)
    {

      do
      {
        ball[i] = new Sfera();
      } while((ball[i].getx() + ball[i].getR() < _ships[0].getx() &&   ball[i].getx()-ball[i].getR()>_ships[0].getx())||(ball[i].gety() + ball[i].getR() <_ships[0].gety() && ball[i].gety() - ball[i].getR()>_ships[0].gety()));

    }
    for (Sfera p : ball)
    {
      System.out.println(p.getx()+" "+ p.gety()+" "+p.getR());
    }
    
    for(int i=0; i<h; i++) {
    	for(int j=0; j<w; j++) {
    		try {
    			//  Block of code to try
    			_ForceMatrix[i][j] = Forze(i,j,ball);
    			//System.out.println(i);
    			}
    		catch(Exception e) {
    			//  Block of code to handle errors
    			_ForceMatrix[i][j] = new Pair(-1,-1);
    			System.out.println(e);
    		}
        }
    }
    
    
    //draw here
    
  }
  public Dimension getPreferredSize()
  {
    return new Dimension(_hwidth*2,_hheigth*2);
  }
  private void freccia(int x, int y)
  {
    if (x != _x || y!= _y)
    {
      int hord,vertd;
      _x = x;
      _y=y;
      _ships[_focus].getMouse(x,y);
      repaint();
    }
  }
  public void actionPerformed(ActionEvent evento)
  {
    if (evento.getSource() instanceof JButton) {
      double angle = (double)angles[_focus].getValue();
      double pewX = _ships[_focus].getx() + 10 + ((Math.cos(Math.toRadians(angle)))*20);
      double pewY = _ships[_focus].gety() + 10 + ((Math.sin(Math.toRadians(angle)))*20);
      pew = new Proiettile(pewX,pewY);
      _current = new Trajectory();
      _current.push(new Pair(pewX,pewY));

      pew.Shoot(angle,(double)forces[_focus].getValue());
      _counter++;
      butts[_focus].setEnabled(false);
      _focus=_counter%2;
      butts[_focus].setEnabled(true);
      //TODO la riga qui sopra va spostata e copiata per ogni volta in cui il proiettile sparisce
      //per ora è qui per evitare di bloccare il gioco in caso il proiettile non colpisse nulla
    }
    if (pew != null)
    //	if(true)
    {
      if (pew.Hit(_ships[0]))
      {
        _current.push(new Pair(pew.getx(),pew.gety()));
        repaint();
        _tr.add(_current);
        _ships[1].increase();
        _points[1]++;
        labels[1].setText(Integer.toString(_points[1]));

        pew = null;
        _tr = new ArrayList<Trajectory>();
        _current= new Trajectory();
        loadGame();
      }else if (pew.Hit(_ships[1]))
      {
        _current.push(new Pair(pew.getx(),pew.gety()));
        repaint();
        _tr.add(_current);
        _ships[0].increase();
        _points[0]++;
        labels[0].setText(Integer.toString(_points[0]));
        pew = null;
        _tr = new ArrayList<Trajectory>();
        _current= new Trajectory();
        loadGame();

      }else if ( pew.Hit(ball))
      {
        double minx,miny,maxx,maxy;
        //repaint(pew.getx(),pew.gety(),pew.getPX(),pew.getPY());
        if (pew.getx() < pew.getPX())
        {
          minx = pew.getx();
          maxx = pew.getPX();
        } else
        {
          minx = pew.getPX();
          maxx = pew.getx();
        }
        if (pew.gety() < pew.getPY())
        {
          miny = pew.gety();
          maxy = pew.getPY();
        } else
        {
          miny = pew.getPY();
          maxy = pew.gety();
        }
        repaint((int)minx,(int)miny,(int)maxx,(int)maxy);
        _tr.add(_current);
        pew = null;
      }else {
        //pew.Forze(ball);
        //pew.Update();
        Pair forces = Forze(pew.getx(),pew.gety(),ball);
        System.out.println(forces.getx());
        if (Math.abs(forces.getx()) < 1e-4 && Math.abs(forces.gety()) < 1e-4)
        {
          _tr.add(_current);
          pew = null;
        }else
        {
          pew.Update(forces);
          _current.push(new Pair(pew.getx(),pew.gety()));
        }
        repaint();
        //repaint();
      }
    }
  }

  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    //navetta.paintIcon(this,g);
    Graphics2D g2d = (Graphics2D)g;
    double vertd, hord;
    vertd = (_y - _ships[_focus].gety())/3;
    hord = (_x - _ships[_focus].getx() )/3;

    g2d.drawImage(bg,0,0,null);

    _ships[0].paintComponent(g);
    _ships[1].paintComponent(g);
    for (Trajectory raj : _tr)
    {
      raj.paintComponent(g);
    }
    if (pew != null)
    {
      Line2D punto;
      for (Sfera b : ball)
      {
        punto = new Line2D.Double(b.getx()+(b.getR()/2),b.gety()+(b.getR()/2),pew.getx(),pew.gety());
        g2d.draw(punto);
      }
      //pew.paintComponent(g);
      //_current.paintComponent(g);
    }


    for (Sfera balla : ball)
    {
      balla.paintComponent(g);
    }
    _conteggio++;

    time.start();
    
    
    for(int i=50; i<h; i+=100) {
    	for(int j=50; j<w; j+=100) {
    		Shape arrow = createArrowShape(new Pair(i,j),_ForceMatrix[i][j]);
    		g2d.draw(arrow);
    	}
    }
    
  }

  public Pair Forze(double x, double y, Sfera[] palle)
  {
    double distx;
    double disty;
    double dist;
    double f;
    double fx = 0;
    double fy = 0;
    for (Sfera i : palle)
    {
      distx = x - (i.getx()+(i.getR()/2));
      disty = y - (i.gety()+(i.getR()/2));
      dist = Math.sqrt(distx*distx + disty*disty);
      //f = -pew.getM()*i.getM()/Math.pow(dist,2);
      f = -100*i.getM()/Math.pow(dist,2);
      fx += f * (double)(distx/dist);
      fy += f * (double)(disty/dist);
    }
    double ax = (fx*1/8);
    double ay = (fy*1/8);
    return new Pair(ax,ay);
  }
  // public Settings getSettings()
  // {
  //   s  = new Settings(_planetnum);
  //   return s;
  // }
  public int getSettings()
  {
    return _planetnum;
  }
  public void setSettings(int number) // best name
  {
    //_planetnum = s.getNumber();
    _planetnum=number;
    System.out.println(number);
    loadGame();
    repaint();
  }
  
  public static Shape createArrowShape(Pair fromPt, Pair toPt) {
	    Polygon arrowPolygon = new Polygon();
	    arrowPolygon.addPoint(-6,1);
	    arrowPolygon.addPoint(3,1);
	    arrowPolygon.addPoint(3,3);
	    arrowPolygon.addPoint(6,0);
	    arrowPolygon.addPoint(3,-3);
	    arrowPolygon.addPoint(3,-1);
	    arrowPolygon.addPoint(-6,-1);


	   //Pair MidPoint = midpoint(fromPt, toPt);
	   //Point midPoint = new Point((int)MidPoint.getx(),(int)MidPoint.gety());
	   
	   
	    double rotate = Math.atan2(toPt.gety(), toPt.getx());

	    AffineTransform transform = new AffineTransform();
	    transform.translate(fromPt.getx(), fromPt.gety());
	    double ptDistance = Math.pow((Math.pow(toPt.gety() - fromPt.gety(),2))+(Math.pow(toPt.getx() - fromPt.getx(),2)),0.5);
	    double scale = 2*Math.atan(ptDistance); 
	    transform.scale(scale, scale);
	    transform.rotate(rotate);

	    return transform.createTransformedShape(arrowPolygon);
	}

	
  
  
 

}
