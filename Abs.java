package gw;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class Abs extends JComponent {

    protected double _x;
    protected double _y;
    protected double _m;

    protected int _width;
    protected int _height;
    protected boolean visible;
    protected Image _image;
    protected ImageIcon _icon;

    public Abs() {


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

    public double getM() {
        return _m;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    // public Rectangle getBounds() {
    //     return new Rectangle(_x, _y, _width, _height);
    // }
}
