package kmitl.lab03.natcha58070069.simplemydot.model;

public class Dot {
    private int centerX;
    private int centerY;
    private int radius;

    //Give info from main act
    private onDotChangedListener listener;

    public void setListener(onDotChangedListener listener) {
        this.listener = listener;
    }

    public interface onDotChangedListener {
        void onDotChanged(Dot dot);
    }

    public Dot(onDotChangedListener listener, int centerX, int centerY, int radius) {
        //if dot changed call back to onDotChanges(in main act)
        this.listener = listener;
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;

        //call back to main act onDotChanged
        //this.listener.onDotChanged(this);
    }

    //new dot and change
    public Dot(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
        this.listener.onDotChanged(this);
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
        this.listener.onDotChanged(this);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }


}
