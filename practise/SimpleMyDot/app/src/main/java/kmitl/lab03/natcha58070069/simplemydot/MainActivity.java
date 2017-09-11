package kmitl.lab03.natcha58070069.simplemydot;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import kmitl.lab03.natcha58070069.simplemydot.model.Dot;
import kmitl.lab03.natcha58070069.simplemydot.model.DotParcelable;
import kmitl.lab03.natcha58070069.simplemydot.model.DotSerializable;
import kmitl.lab03.natcha58070069.simplemydot.view.DotView;

import static android.R.attr.centerX;
import static android.R.attr.centerY;
import static android.R.attr.clickable;
import static android.R.attr.contextClickable;
import static android.R.attr.touchscreenBlocksFocus;

public class MainActivity extends AppCompatActivity implements Dot.onDotChangedListener{

    private Dot dot;
    private DotView dotView;
    private ArrayList<Dot> allDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DotSerializable dotSerializable = new DotSerializable();
        dotSerializable.setCenterX(150);
        dotSerializable.setCenterY(150);
        dotSerializable.setColor(Color.RED);
        dotSerializable.setRadius(30);

        final DotParcelable dotParcelable = new DotParcelable(150, 150, 50);


        dotView = (DotView) findViewById(R.id.dotView);
        dot = new Dot(this, 0, 0, 30); //default value start
        allDot = new ArrayList<>();

        //building
        Button btnOpenActivity = (Button) findViewById(R.id.btnOpenActivity);
        btnOpenActivity.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this, secondActivity.class);
                //put sth เข้า intent
                intent.putExtra("xValue", 30);
                //put dotSerializable in intent
                intent.putExtra("dotSerializable", dotSerializable);

                //put dotparcelable in intent
                intent.putExtra("dotParcelable", dotParcelable);
                startActivity(intent);
            }
        });
    }

    //Click on Random Butt
    public void onRandomDot(View view) {
        Random random = new Random();
        //Random locate
        int centerX = random.nextInt(this.dotView.getWidth());
        int centerY = random.nextInt(this.dotView.getHeight());
        createDot(centerX, centerY);
    }

    //Touch for create dot
    @Override
    public boolean onTouchEvent(MotionEvent e){
        float eventX = e.getX();
        float eventY = e.getY()-200;

        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                createDot(eventX, eventY);
                return true;
            case MotionEvent.ACTION_MOVE:
                createDot(eventX, eventY);
                break;
            case MotionEvent.ACTION_UP:

                break;
            default:
                return false;
        }
        invalidateOptionsMenu();
        return true;

    }

    //Create dot & add to ArrayList(allDot)
    public void createDot(float centerX, float centerY){
        Random random = new Random();

        //Random Color
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);

        //Random Radius
        int radius = (int) (30+(Math.random()*200));

        //Create dot for add into allDot
        dot = new Dot(this,centerX,centerY,radius);

        //this have interface
        this.dot.setCenterX(centerX);
        this.dot.setCenterY(centerY);
        this.dot.setRadius(radius);
        this.dot.setColor(red, green, blue);

        allDot.add(dot);
    }

    @Override
    public void onDotChanged(Dot dot) {
        dotView.setAllDot(allDot); //create meth setDot bec setDot เรื่อย ๆ
        dotView.invalidate();
    }

    public void onClearDot(View view) {
        allDot.clear();
        dotView.invalidate();
    }
}
