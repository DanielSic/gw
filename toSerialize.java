package gw;
import java.io.Serializable;
class toSerialize implements Serializable
{
  public Sfera[] sfere;
  public Nave[] navi;
  public toSerialize()
  {}
  public toSerialize(Sfera[] sf , Nave[] nv)
  {
    sfere = sf;
    navi = nv;
  }
}
