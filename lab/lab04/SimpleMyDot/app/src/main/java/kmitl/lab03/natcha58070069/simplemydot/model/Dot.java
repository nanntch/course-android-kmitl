package kmitl.lab03.natcha58070069.simplemydot.model;

public class Dot {
    private float centerX;
    private float centerY;
    private float radius;
    private int red, green, blue;

    //Give info from main act
    private onDotChangedListener listener;

    public void setListener(onDotChangedListener listener) {
        this.listener = listener;
    }

    public interface onDotChangedListener {
        void onDotChanged(Dot dot);
    }

    public Dot(onDotChangedListener listener,float centerX, float centerY, float radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.listener = listener;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public void setColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.listener.onDotChanged(this);
    }

    public float getCenterX() {
        return centerX;
    }

    public void setCenterX(float centerX) {
        this.centerX = centerX;
        this.listener.onDotChanged(this);
    }

    public float getCenterY() {return centerY;}

    public void setCenterY(float centerY) {
        this.centerY = centerY;
        this.listener.onDotChanged(this);
    }

    public int getRadius() {
        return (int) radius;
    }

    public void setRadius(int radius) { this.radius = radius;}
}
