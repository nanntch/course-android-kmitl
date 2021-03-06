package kmitl.lab03.natcha58070069.simplemydot.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;

/**
 * Created by Nacha on 11-Sep-17.
 */

public class Screenshot {

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
