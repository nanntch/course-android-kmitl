package kmitl.lab03.natcha58070069.simplemydot.model;

import android.graphics.Color;

public class Dot {
    private float centerX;
    private float centerY;
    private int radius;
    private int color;

    public Dot(float centerX, float centerY, int radius) {
        this(centerX, centerY, radius, Color.RED);
    }

    public Dot(float centerX, float centerY, int radius, int color) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.color = color;
    }

    public float getCenterX() {
        return centerX;
    }

    public void setCenterX(float centerX) {
        this.centerX = centerX;
    }

    public float getCenterY() {
        return centerY;
    }

    public void setCenterY(float centerY) {
        this.centerY = centerY;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
