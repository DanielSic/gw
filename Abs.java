package gw;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import java.io.*;

public class Abs extends JComponent implements java.io.Serializable{

    protected double _x;
    protected double _y;
    protected double _m;
    protected double _r;

    protected int _width;
    protected int _height;
    protected boolean visible;
    protected Image _image;
    protected ImageIcon _icon;

    public Abs()
    {

    }

    public double getR()
    {
      return _r;
    }

    public void setR(double r)
    {
      _r = r;
    }

    protected void getImageDimensions() {

        _width = _image.getWidth(null);
        _height = _image.getHeight(null);
    }

    protected void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(imageName);
        _image = ii.getImage();
    }

    public Image getImage() {
        return _image;
    }

    public ImageIcon getIcon() {
        return _icon;
    }

    public double getx() {
        return _x;
    }

    public double gety() {
        return _y;
    }
    public void setM(double m)
    {
      _m = m;
    }
    public double getM()
    {
        return _m;
    }

    public boolean isVisible()
    {
        return visible;
    }

    // public void setVisible(Boolean visible) {
    //     this.visible = visible;
    // }
    public void setx(double x)
    {
      _x = x;
    }
    public void sety(double y)
    {
      _y = y;
    }

    public boolean overlap(Abs altro)
    {
      double distx = (_x + _r/2) - (altro.getx()+altro.getR()/2);
      double disty = (_y + _r/2) - (altro.gety()+altro.getR()/2);
      double dist = Math.sqrt(distx*distx + disty*disty);
      System.out.println("ciao");
      if (dist <= ((_r/2f) + (altro.getR()/2f)))
      {
        return true;
      }
    return false;
    }

    // public Rectangle getBounds() {
    //     return new Rectangle(_x, _y, _width, _height);
    // }
}
