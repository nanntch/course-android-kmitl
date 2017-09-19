package kmitl.lab03.natcha58070069.simplemydot;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;
import java.util.Random;

import kmitl.lab03.natcha58070069.simplemydot.model.Colors;
import kmitl.lab03.natcha58070069.simplemydot.model.Dot;
import kmitl.lab03.natcha58070069.simplemydot.model.Dots;
import kmitl.lab03.natcha58070069.simplemydot.view.DotView;
import kmitl.lab03.natcha58070069.simplemydot.view.Screenshot;

public class MainActivity extends AppCompatActivity implements MainFragment.MyFragmentListener {

//    private Dots dots;
//    private DotView dotView;
//    private ImageView imageView;
//    private final int EXTERNAL_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Just Button, Listener, Meth onClick

//Button move to fragment (MAIN FRAGMENT)

//        imageView = (ImageView) findViewById(R.id.imageView);
//        dotView = (DotView) findViewById(R.id.dotView);
//        dotView.setOnDotViewPressListener(this);
//        dots = new Dots();
//        dots.setListener(this);


        if(savedInstanceState == null){
            initialFragment();
        }

    }

    private void initialFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fragmentContainer,MainFragment.newInstance())
                .commit();
    }

    private void viewFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void editDotFragment(){

    }

//    public void btnCapture(View view){
//        //ARK PERMISSION
//        if (askPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, EXTERNAL_REQUEST_CODE) == 0){
//            Bitmap b = Screenshot.takescreenshotOfRootView(imageView);
//            Uri uri = createFile(this,b);
//            //CREATE INTENT
//            createIntent(uri);
//        }
//    }

//    //CREATE FILE FOR SAVE PICTURE(SCREENSHOT)
//    private Uri createFile(Context context, Bitmap b){
//            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
//            //COMPRESS BITMAP
//            b.compress(Bitmap.CompressFormat.PNG, 80, byteArray);
//            String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), b,"screenshot_pic", null);
//        return Uri.parse(path);
//    }

//    //INTENT TO APP
//    private void createIntent(Uri uri){
//        Intent shareIntent = new Intent();
//        shareIntent.setAction(Intent.ACTION_SEND);
//        shareIntent.setType("image/*");
//        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
//        startActivity(Intent.createChooser(shareIntent, "Share image"));
//    }

//    private int askPermission(String permission, int requestCode){
//        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
//            //dont have permission
//            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
//            return 1;
//        }
//        else {
//            //have permission
//            Toast.makeText(this, "Permission is Already Granted", Toast.LENGTH_SHORT).show();
//            return 0;
//        }
//    }

//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permission, @NonNull int[] grantResults){
//        switch (requestCode){
//            case EXTERNAL_REQUEST_CODE:
//                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
//                    Toast.makeText(this, "External Permission Granted", Toast.LENGTH_SHORT).show();
//                    btnCapture(imageView);
//                } else {
//                    Toast.makeText(this, "External Permission Denied", Toast.LENGTH_SHORT).show();
//                }
//                break;
//        }
//    }

//    public void onRandomDot(View view) {
//        Random random = new Random();
//        //Random locate
//        int centerX = random.nextInt(dotView.getWidth());
//        int centerY = random.nextInt(dotView.getHeight());
//        int radius = (int) (30+(Math.random()*150));
//        Dot newDot = new Dot(centerX, centerY, radius, new Colors().getColor());
//        dots.addDot(newDot);
//    }

//    public void onDotViewPressed(int x, int y){
//        final int dotPosition = dots.findDot(x, y);
//        final int radius = (int) (30+(Math.random()*150));
//        if(dotPosition == -1){
//            Dot newDot = new Dot(x, y, radius, new Colors().getColor());
//            dots.addDot(newDot);
//        } else {
//            dots.editDot(this, dotPosition, radius);
//        }
//    }

//    public void onDotsChanged(Dots dots) {
//        dotView.setDots(dots); //create meth setDot bec setDot เรื่อย ๆ
//        dotView.invalidate();
//    }

//    public void onClick(View view){
//        if (R.id.button == view.getId()){
//            viewFragment(MainFragment);
//        }
//    }


//    public void onClearDot(View view) {
//        dots.clearAll();
//        dotView.invalidate();
//    }

//    public void btnUndo(View view){
//        dots.undoDot();
//    }
}
