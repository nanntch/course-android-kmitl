package kmitl.lab03.natcha58070069.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;

import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import kmitl.lab03.natcha58070069.simplemydot.fragment.DotFragment;
import kmitl.lab03.natcha58070069.simplemydot.fragment.EditDotFragment;
import kmitl.lab03.natcha58070069.simplemydot.model.Dot;
import kmitl.lab03.natcha58070069.simplemydot.model.Dots;

public class DotView extends View{

//    implement GestureDetector.OnGestureListener
    private Paint paint;
    private Dots allDot;
    private Dots dots;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.allDot != null) {
            for(Dot dot : allDot.getAllDot()){
                paint.setColor(dot.getColor());
                canvas.drawCircle(dot.getCenterX(), dot.getCenterY(), dot.getRadius(), paint);
            }
        }
    }

    //GESTURE DETECTOR
//    @Override
//    public boolean onDown(MotionEvent e) {
//        return false;
//    }
//
//    @Override
//    public void onShowPress(MotionEvent e) {
//
//    }
//
//    @Override
//    public boolean onSingleTapUp(MotionEvent e) {
//        return false;
//    }
//
//    @Override
//    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//        return false;
//    }
//
//    @Override
//    public void onLongPress(MotionEvent e) {
//
//    }
//
//    @Override
//    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//        return false;
//    }
    // END GESTURE DETECTOR

    public interface OnDotViewPressListener{
        void onDotViewPressed(int x, int y);
    }

    private OnDotViewPressListener onDotViewPressListener;

    public void setOnDotViewPressListener(OnDotViewPressListener onDotViewPressListener) {
        this.onDotViewPressListener = onDotViewPressListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e){
        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                this.onDotViewPressListener.onDotViewPressed((int)e.getX(), (int)e.getY());
                if (e.getEventTime() - e.getDownTime() > 500 && dots.findDot((int) e.getX(), (int) e.getY()) != -1){
                    ///
                    EditDotFragment.newInstance();
                    return true;
                }else {
                    return true;
                }

        }
        return false;
    }



    public DotView(Context context) {
        super(context);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }

    public void setDots(Dots dots) {
        this.allDot = dots;
    }

}
