package kmitl.lab03.natcha58070069.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.provider.CalendarContract;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import kmitl.lab03.natcha58070069.simplemydot.MainActivity;
import kmitl.lab03.natcha58070069.simplemydot.model.Colors;
import kmitl.lab03.natcha58070069.simplemydot.model.Dot;
import kmitl.lab03.natcha58070069.simplemydot.model.Dots;

public class DotView extends View {

    private Paint paint;
    private Dots allDot;

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

    public interface OnDotViewPressListener{
        void onDotViewPressed(int x, int y);
    }

    private OnDotViewPressListener onDotViewPressListener;

    public void setOnDotViewPressListener(OnDotViewPressListener onDotViewPressListener) {
        this.onDotViewPressListener = onDotViewPressListener;
    }

//    private OnLongClickListener onLongClickListener;
//
//    public void setOnLongClickListener(OnLongClickListener onLongClickListener){
//        this.onLongClickListener = onLongClickListener;
//    }
//
//    public boolean onLongClick(View view){
//
//        return true;
//    }
//
//
//    public interface OnItemClickListener{
//        void onPositionButtonClick();
//
//    }

    @Override
    public boolean onTouchEvent(MotionEvent e){
        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                this.onDotViewPressListener.onDotViewPressed((int)e.getX(), (int)e.getY());
                return true;
//            case MotionEvent.ACTION_MOVE:
//                handler.removeCallbacks(mLongPressed);
//                return super.onTouchEvent(e);
//            case MotionEvent.ACTION_UP:
//                handler.removeCallbacks(mLongPressed);
//                return super.onTouchEvent(e);
        }
        return false;
    }

//    final GestureDetector gestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener(){
//        public void onLongPress(MotionEvent e){
//            Log.e("", "Longpress detected");
//        }
//    });

//    final Handler handler = new Handler();
//    Runnable mLongPressed = new Runnable() {
//        public void run() {
//            Log.i("", "Long press!");
//        }
//    };

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
