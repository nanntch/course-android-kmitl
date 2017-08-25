package kmitl.lab03.natcha58070069.simplemydot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import kmitl.lab03.natcha58070069.simplemydot.model.Dot;
import kmitl.lab03.natcha58070069.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dot.onDotChangedListener{

    private Dot dot;
    private DotView dotView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dotView = (DotView) findViewById(R.id.dotView);
        dot = new Dot(this, 0, 0, 30);
    }

    public void onRandomDot(View view) {
        //Random a Dot
        Random random = new Random();
//        int centerX = random.nextInt(500);
//        int centerY = random.nextInt(500);
        int centerX = random.nextInt(this.dotView.getWidth());
        int centerY = random.nextInt(this.dotView.getHeight());
        //this have interface
        //new Dot(this, centerX, centerY, 50);
        this.dot.setCenterX(centerX);
        this.dot.setCenterY(centerY);
    }

    @Override
    public void onDotChanged(Dot dot) {
        //receive call back and view
        //Draw dot model to view
//        TextView centerXTextView = (TextView)findViewById(R.id.centerXTextView);
//        TextView centerYTextView = (TextView)findViewById(R.id.centerYTextView);
//
//        centerXTextView.setText(String.valueOf(dot.getCenterX()));
//        centerYTextView.setText(String.valueOf(dot.getCenterY()));
        dotView.setDot(dot); //create meth setDot bec setDot เรื่อย ๆ
        dotView.invalidate();
    }
}
