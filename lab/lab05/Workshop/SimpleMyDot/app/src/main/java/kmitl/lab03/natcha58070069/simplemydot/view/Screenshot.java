package kmitl.lab03.natcha58070069.simplemydot.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import kmitl.lab03.natcha58070069.simplemydot.model.Dots;

/**
 * Created by Nacha on 11-Sep-17.
 */

public class Screenshot extends AppCompatActivity {
//    private Dots dots;
//    private DotView dotView;
//    private ImageView imageView;
//    private final int EXTERNAL_REQUEST_CODE = 2;
    //private Context context;

    public static Bitmap takescreenshot(View v){
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache(true);
        Bitmap b = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false);
        return b;
    }

    public static Bitmap takescreenshotOfRootView(View v){
        return takescreenshot(v.getRootView());
    }

}
