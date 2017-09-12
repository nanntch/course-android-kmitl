package kmitl.lab03.natcha58070069.simplemydot;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Random;

import javax.xml.transform.URIResolver;

import kmitl.lab03.natcha58070069.simplemydot.model.Colors;
import kmitl.lab03.natcha58070069.simplemydot.model.Dot;
import kmitl.lab03.natcha58070069.simplemydot.model.DotParcelable;
import kmitl.lab03.natcha58070069.simplemydot.model.DotSerializable;
import kmitl.lab03.natcha58070069.simplemydot.model.Dots;
import kmitl.lab03.natcha58070069.simplemydot.view.DotView;
import kmitl.lab03.natcha58070069.simplemydot.view.Screenshot;

import static android.R.attr.centerX;
import static android.R.attr.centerY;
import static android.R.attr.clickable;
import static android.R.attr.contextClickable;
import static android.R.attr.contextUri;
import static android.R.attr.radius;
import static android.R.attr.touchscreenBlocksFocus;

public class MainActivity extends AppCompatActivity implements Dots.OnDotsChangeListener,
        DotView.OnDotViewPressListener {

    private Dots dots;
    private DotView dotView;
    private View main;
    private ImageView imageView;
    private String currentTime;
    private MenuItem item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main = findViewById(R.id.dotView);
        imageView = (ImageView) findViewById(R.id.imageView);
        Button btn = (Button) findViewById(R.id.btnCapture);

        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Bitmap b = Screenshot.takescreenshotOfRootView(imageView);
                imageView.setImageBitmap(b);
                main.setBackgroundColor(Color.parseColor("#999999"));

                File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                File imageFile = new File(path, getCurrentTime()+ ".png");
                try {
                    FileOutputStream fileOutPutStream = new FileOutputStream(imageFile);
                    b.compress(Bitmap.CompressFormat.PNG, 80, fileOutPutStream);

                    fileOutPutStream.flush();
                    fileOutPutStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Uri uri = Uri.parse("file://" + imageFile.getAbsolutePath());

                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.setType("image/*");
//                shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(path, getCurrentTime()+ ".png")));

                shareIntent.putExtra(Intent.EXTRA_STREAM, uri);


                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(Intent.createChooser(shareIntent, "send"));


//                Intent openInChooser = new Intent(shareIntent);
//                openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, b);
//                startActivity(openInChooser);

//                Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
//                photoPickerIntent.setType("image/*");
//                startActivityForResult(photoPickerIntent,2);
            }
        });

        //SERIALIZABLE
        final DotSerializable dotSerializable = new DotSerializable();
        dotSerializable.setCenterX(150);
        dotSerializable.setCenterY(150);
        dotSerializable.setColor(Color.RED);
        dotSerializable.setRadius(30);

        //PARCELABLE
        final DotParcelable dotParcelable = new DotParcelable(150, 150, 50);

        //building
        Button btnOpenActivity = (Button) findViewById(R.id.btnOpenActivity);
        btnOpenActivity.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this, secondActivity.class);
                //put sth into intent
                intent.putExtra("xValue", 30);
                //put dotSerializable in intent
                intent.putExtra("dotSerializable", dotSerializable);
                //put dotparcelable in intent
                intent.putExtra("dotParcelable", dotParcelable);
                startActivity(intent);
            }
        });

        dotView = (DotView) findViewById(R.id.dotView);
        dotView.setOnDotViewPressListener(this);
        dots = new Dots();
        dots.setListener(this);
    }

    public void onRandomDot(View view) {
        Random random = new Random();
        //Random locate
        int centerX = random.nextInt(dotView.getWidth());
        int centerY = random.nextInt(dotView.getHeight());
        int radius = (int) (30+(Math.random()*200));
        Dot newDot = new Dot(centerX, centerY, radius, new Colors().getColor());
        dots.addDot(newDot);
    }

    public void onDotViewPressed(int x, int y){
        final int dotPosition = dots.findDot(x, y);
        final int radius = (int) (30+(Math.random()*200));
        if(dotPosition == -1){
            Dot newDot = new Dot(x, y, radius, new Colors().getColor());
            dots.addDot(newDot);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setItems(new CharSequence[]{"Size", "Color", "Remove"},
                    new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case 0:
                            dots.changeSize(dotPosition, radius);
                            dialog.dismiss();
                            break;
                        case 1:
                            dots.changeColor(dotPosition);
                            dialog.dismiss();
                            break;
                        case 2:
                            dots.removeBy(dotPosition);
                            dialog.dismiss();
                            break;
                    }
                }
            });
            builder.show();
        }
    }

    public void onDotsChanged(Dots dots) {
        dotView.setDots(dots); //create meth setDot bec setDot เรื่อย ๆ
        dotView.invalidate();
    }

    public void onClearDot(View view) {
        dots.clearAll();
        dotView.invalidate();
    }

    public String getCurrentTime() {
        return currentTime;
    }
}
