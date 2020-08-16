package gw;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;

import javax.swing.JButton;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.BorderFactory;

import java.util.ArrayList;

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
}

class Sad
{
  public boolean _modified = false;
  public int _planetnum;
  public boolean _inbetween;
  public boolean _frecce;
  public int _W;
  public int _H;

  Sad()
  {
    _planetnum = 5;
    _inbetween = false;
    _frecce = false;
    _W = 1200;
    _H = 700;

  }
  // public void setPlanets(int num)
  // {
  //   _planetnum = num;
  // }
  // public void setInb(boolean a)
  // {
  //   _inbetween = a;
  // }
  // public int getPlanets()
  // {
  //   return _planetnum;
  // }
  // public boolean getInb()
  // {
  //   return _inbetween;
  // }
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
  // private int W_RES = 800;
  // private int H_RES = 600;
  private Timer time = new Timer(20,this);
  private int _counter = 0;
  private int _focus = 0;
  private Nave _ships[];
  private Proiettile pew;
  //private int _planetnum = 5;
  //private boolean _inbetween  = true;
  private Sad _set = new Sad();
  // _set._planetnum = 5;
  // _set._inbetween = false;
  private Sfera ball[];
  // private int _hheigth = 400;
  // private int _hwidth = 610;
  private int _x = 50;
  private int _y = 50;
  private int _raggio = 30;
  private Nave _sheep;
  // private static int h=1200;
  // private static int w=800;
  private Pair[][] _ForceMatrix = new Pair[_set._H][_set._W];
  private int _conteggio=0;

  private Settings s ;

  private ArrayList<Trajectory> _tr ;
  private Trajectory _current ;
  private Image bg = new ImageIcon("gw/sfondo.jpg").getImage();


  //private JFormattedTextField angles[] ;
  //private JFormattedTextField forces[] ;
  private JSpinner angles[];
  private JSpinner forces[];
  private JButton butts[];
  private JLabel labels[] ;
  private int _points[];

  public Pannello()
  {
    initUI();
    loadGame();
  }

  public void refreshUI()
  {
    _counter=0;
    _focus = _counter%2;
    removeAll();
    revalidate();
    initUI();
  }

  public void initUI()
  {
    // angles = new JFormattedTextField[2];
    // forces = new JFormattedTextField[2];
    angles = new JSpinner[2];
    forces = new JSpinner[2];
    butts = new JButton[2];
    labels = new JLabel[2];
    _points = new int[2];
    _points[0] = 0;
    _points[1] = 0;
    // angles[0] = new JFormattedTextField();
    // angles[1] = new JFormattedTextField();
    // forces[0] = new JFormattedTextField();
    // forces[1] = new JFormattedTextField();

    angles[0] = new JSpinner(new SpinnerNumberModel(0.0,-180,180,1));
    angles[1] = new JSpinner(new SpinnerNumberModel(0.0,-180,180,1));
    forces[0] = new JSpinner(new SpinnerNumberModel(0.0,0.0,5.0,0.01));
    forces[1] = new JSpinner(new SpinnerNumberModel(0.0,0.0,5.0,0.01));
    setLayout(null);

    angles[0].setOpaque(false);
    angles[0].setBorder(BorderFactory.createLineBorder(new Color(72, 160, 220),2,true));
    angles[0].getEditor().setOpaque(false);
    ((JSpinner.NumberEditor)angles[0].getEditor()).getTextField().setFont(new Font("Verdana", Font.BOLD,14));
    //angles[0].setValue(new Double(0));
    ((JSpinner.NumberEditor)angles[0].getEditor()).getTextField().setOpaque(false);
    ((JSpinner.NumberEditor)angles[0].getEditor()).getTextField().setForeground(new Color(250,250,250));
    //one_angle.setPreferredSize(new Dimension(100,20));
    angles[0].setBounds(20,_set._H-100,100,20);
    add(angles[0]);

    forces[0].setOpaque(false);
    forces[0].setBorder(BorderFactory.createLineBorder(new Color(72, 160, 220),2,true));
    forces[0].getEditor().setOpaque(false);
    ((JSpinner.NumberEditor)forces[0].getEditor()).getTextField().setOpaque(false);
    ((JSpinner.NumberEditor)forces[0].getEditor()).getTextField().setFont(new Font("Verdana", Font.BOLD,14));
    //forces[0].setValue(new Double(0));
    ((JSpinner.NumberEditor)forces[0].getEditor()).getTextField().setForeground(new Color(250,250,250));
    forces[0].setBounds(20,_set._H-80,100,20);
    add(forces[0]);

    butts[0] = new JButton("Shoot");
    butts[0].addActionListener(this);
    butts[0].setBounds(20,_set._H-60,100,20);
    butts[0].setBorder(BorderFactory.createLineBorder(new Color(72, 160, 220),2,true));
    add(butts[0]);

    angles[1].setOpaque(false);
    angles[1].setBorder(BorderFactory.createLineBorder(new Color(255, 89, 230),2,true));
    angles[1].getEditor().setOpaque(false);
    ((JSpinner.NumberEditor)angles[1].getEditor()).getTextField().setFont(new Font("Verdana", Font.BOLD,14));
    //angles[0].setValue(new Double(0));
    ((JSpinner.NumberEditor)angles[1].getEditor()).getTextField().setOpaque(false);
    ((JSpinner.NumberEditor)angles[1].getEditor()).getTextField().setForeground(new Color(250,250,250));
    angles[1].setBounds(_set._W-120,_set._H-100,100,20);
    add(angles[1]);

    forces[1].setOpaque(false);
    forces[1].setBorder(BorderFactory.createLineBorder(new Color(255, 89, 230),2,true));
    forces[1].getEditor().setOpaque(false);
    ((JSpinner.NumberEditor)forces[1].getEditor()).getTextField().setOpaque(false);
    ((JSpinner.NumberEditor)forces[1].getEditor()).getTextField().setFont(new Font("Verdana", Font.BOLD,14));
    //forces[0].setValue(new Double(0));
    ((JSpinner.NumberEditor)forces[1].getEditor()).getTextField().setForeground(new Color(250, 250, 250));
    forces[1].setBounds(_set._W-120,_set._H-80,100,20);
    add(forces[1]);

    butts[1] = new JButton("Shoot");
    butts[1].addActionListener(this);
    butts[1].setBounds(_set._W-120,_set._H-60,100,20);
    butts[1].setBorder(BorderFactory.createLineBorder(new Color(255, 89, 230),2,true));
    butts[1].setEnabled(false);
    add(butts[1]);

    labels[0] = new JLabel(Integer.toString(_points[0]));
    labels[0].setFont(new Font("Verdana", Font.BOLD,40));
    labels[0].setBounds(_set._W-150,20,100,100);
    add(labels[0]);

    labels[1] = new JLabel(Integer.toString(_points[1]));
    labels[1].setFont(new Font("Verdana", Font.BOLD,40));
    labels[1].setBounds(_set._W-50,20,100,100);
    add(labels[1]);
  }

  public void loadGame()
  {
    _tr = new ArrayList<Trajectory>();
    ball = new Sfera[_set._planetnum];
    double dist;
    _ships = new Nave[2];

    _ships[0] = new Nave(_set._W, _set._H);
    do
    {
      _ships[1] = new Nave(_set._W, _set._H,"gw/20x20spshp.png");
      dist = Math.sqrt(Math.pow(_ships[0].getx() - _ships[1].getx(),2)+Math.pow(_ships[0].gety() - _ships[1].gety(),2));
      // System.out.println(_ships[0].getx());
    } while (dist < 100f);
    loadPlanets();

    for (Sfera p : ball)
    {
      System.out.println(p.getx()+" "+ p.gety()+" "+p.getR());
    }
    for(int i=0; i<_set._H; i++) {
    	for(int j=0; j<_set._W; j++) {
    		try {
    			//  Block of code to try
    			_ForceMatrix[i][j] = Forze(i,j,ball);
    			}
    		catch(Exception e) {
    			//  Block of code to handle errors
    			_ForceMatrix[i][j] = new Pair(-1,-1);
          //System.out.println(_ForceMatrix[i][j].getx());
    			System.out.println(e);
    		}
      }
    }
  }
  private void loadPlanets()
  {
    int i = 0;
    if (_set._inbetween)
    {
      do
      {
        double x = (_ships[1].getx()>_ships[0].getx())? _ships[0].getx()+10+(_ships[1].getx()+10 - (_ships[0].getx()+10))/2:_ships[1].getx()+10+(_ships[0].getx()+10 - (_ships[1].getx()+10))/2;
        double y = (_ships[1].gety()>_ships[0].gety())? _ships[0].gety()+10+(_ships[1].gety()+10 - (_ships[0].gety()+10))/2:_ships[1].gety()+10+(_ships[0].gety()+10 - (_ships[1].gety()+10))/2;

        ball[i] = new Sfera(x,y);
      }while(!ball[i].isValid(_ships,ball,i));
      i++;

    }
    for (i = i; i < _set._planetnum; i++)
    {
      do
      {
        ball[i] = new Sfera(_set._W,_set._H);
      } while(!ball[i].isValid(_ships,ball,i));
    }
  }
  public Dimension getPreferredSize()
  {
    return new Dimension(_set._W,_set._H);
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
      // Line2D punto;
      // for (Sfera b : ball)
      // {
      //   punto = new Line2D.Double(b.getx()+(b.getR()/2),b.gety()+(b.getR()/2),pew.getx(),pew.gety());
      //   g2d.draw(punto);
      // }
      pew.paintComponent(g);
      _current.paintComponent(g);
    }
    if(_set._frecce)
    {
      for(int i=50; i<_set._H; i+=100) {
        for(int j=50; j<_set._W; j+=100) {
          Shape arrow = createArrowShape(new Pair(i,j),_ForceMatrix[i][j]);
          g2d.draw(arrow);
        }
      }
    }


    for (Sfera balla : ball)
    {
      balla.paintComponent(g);
    }
    _conteggio++;

    time.start();
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

  public Sad getSettings()
  {
    return _set;
  }

  public void setSettings(Sad set) // best name
  {
    //_planetnum = s.getNumber();
    _set = set;
    if (_set._modified)
    {
      System.out.println(_set._planetnum);
      pew = null;
      refreshUI();
      loadGame();
      repaint();
    }
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
