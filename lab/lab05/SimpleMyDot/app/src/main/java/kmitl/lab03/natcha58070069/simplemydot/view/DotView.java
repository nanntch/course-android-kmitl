package kmitl.lab03.natcha58070069.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;

import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import kmitl.lab03.natcha58070069.simplemydot.model.Dot;
import kmitl.lab03.natcha58070069.simplemydot.model.Dots;

public class DotView extends View {

    private Paint paint;
    private Dots allDot;
    GestureDetector gestureDetector;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.allDot != null) {
            for (Dot dot : allDot.getAllDot()) {
                paint.setColor(dot.getColor());
                canvas.drawCircle(dot.getCenterX(), dot.getCenterY(), dot.getRadius(), paint);
            }
        }
    }

    private OnDotViewPressListener onDotViewPressListener;


    public interface OnDotViewPressListener {
        void onDotViewPressed(int x, int y);

        void onDotViewLongPress(int x, int y);

    }

    public void setOnDotViewPressListener(OnDotViewPressListener onDotViewPressListener) {
        this.onDotViewPressListener = onDotViewPressListener;
    }

    public void onTouchType(Context context) {
        gestureDetector = new GestureDetector(this.getContext(), new GestureDetector.SimpleOnGestureListener() {
            public boolean onDown(MotionEvent e) {
                return true;
            }

            public void onShowPress(MotionEvent e) {

            }

            public boolean onSingleTapUp(MotionEvent e) {
                onDotViewPressListener.onDotViewPressed((int)e.getX(),(int)e.getY());
                return true;
            }

            public void onLongPress(MotionEvent e) {
                onDotViewPressListener.onDotViewLongPress((int) e.getX(), (int) e.getY());
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return gestureDetector.onTouchEvent(e);
    }


    public DotView(Context context) {
        super(context);
        onTouchType(context);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        onTouchType(context);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        onTouchType(context);
        paint = new Paint();
    }

    public void setDots(Dots dots) {
        this.allDot = dots;
    }

}
