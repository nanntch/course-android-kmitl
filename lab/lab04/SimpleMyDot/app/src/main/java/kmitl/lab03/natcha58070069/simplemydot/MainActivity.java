package kmitl.lab03.natcha58070069.simplemydot;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


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
    private final int EXTERNAL_REQUEST_CODE = 2;


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
                //CREATE FILE FOR SAVE PICTURE(SCREENSHOT)
                createFile(b);
                //URI
                Uri uri = Uri.parse("file://" + createFile(b).getAbsolutePath());
                //CREATE INTENT
                createIntent(uri);
            }
        });

        //ARK PERMISSION
        askPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, EXTERNAL_REQUEST_CODE);

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

    private void createIntent(Uri uri){
        //INTENT TO APP
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(shareIntent, "send"));
    }

    private File createFile(Bitmap b){
        //CREATE FILE FOR SAVE PICTURE(SCREENSHOT)
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File imageFile = new File(path, getCurrentTime()+ ".png");
        try {
            FileOutputStream fileOutPutStream = new FileOutputStream(imageFile);
            //COMPRESS BITMAP
            b.compress(Bitmap.CompressFormat.PNG, 80, fileOutPutStream);

            fileOutPutStream.flush();
            fileOutPutStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageFile;
    }

    private void askPermission(String permission, int requestCode){
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
            //dont have permission
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }
        else {
            //have permission
            Toast.makeText(this, "Permission is Already Granted", Toast.LENGTH_SHORT).show();
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permission,
                                           @NonNull int[] grantResaults){
        switch (requestCode){
            case EXTERNAL_REQUEST_CODE:
                if (grantResaults.length>0&&grantResaults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "External Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "External Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
        }
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
            dots.editDot(this, dotPosition, radius);
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
