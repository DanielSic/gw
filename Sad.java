package gw;
import java.io.*;


class Sad implements Serializable
{
  public boolean _modified = false;
  public int _planetnum;
  public boolean _inbetween;
  public boolean _frecce;
  public int _W;
  public int _H;
  public int _imgEdge=20;

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
