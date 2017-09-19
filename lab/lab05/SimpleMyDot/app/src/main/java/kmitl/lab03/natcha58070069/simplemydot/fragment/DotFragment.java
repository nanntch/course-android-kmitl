package kmitl.lab03.natcha58070069.simplemydot.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Random;

import kmitl.lab03.natcha58070069.simplemydot.R;
import kmitl.lab03.natcha58070069.simplemydot.model.Colors;
import kmitl.lab03.natcha58070069.simplemydot.model.Dot;
import kmitl.lab03.natcha58070069.simplemydot.model.Dots;
import kmitl.lab03.natcha58070069.simplemydot.view.DotView;
import kmitl.lab03.natcha58070069.simplemydot.view.Screenshot;

/**
 * A simple {@link Fragment} subclass.
 */
public class DotFragment extends Fragment implements Dots.OnDotsChangeListener, DotView.OnDotViewPressListener, View.OnClickListener {

    private Dots dots;
    private DotView dotView;
    private ImageView imageView;
    private final int EXTERNAL_REQUEST_CODE = 2;
    private dotFragmentListener listener;

    public DotFragment() {
        // Required empty public constructor
    }

    public static DotFragment newInstance() {

        Bundle args = new Bundle();
        DotFragment fragment = new DotFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dot, container, false);
//        imageView = (ImageView) rootView.findViewById(R.id.imageView);
        dotView = (DotView) rootView.findViewById(R.id.dotView);
        dotView.setOnDotViewPressListener(this);
        dots = new Dots();
        dots.setListener(this);

        Button onRandomDot = (Button) rootView.findViewById(R.id.button);
        Button onUndo = (Button) rootView.findViewById(R.id.btnUndo);
        Button onClearDot = (Button) rootView.findViewById(R.id.button2);
        Button onCapture = (Button) rootView.findViewById(R.id.btnCapture);

        onRandomDot.setOnClickListener(this);
        onUndo.setOnClickListener(this);
        onClearDot.setOnClickListener(this);
        onCapture.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button) {
            onRandomDot(v);
        }else if (id == R.id.button2) {
            onClearDot(v);
        }else if (id == R.id.btnUndo) {
            btnUndo(v);
        }else if (id == R.id.btnCapture) {
            btnCapture(v);
        }
    }

    public void onRandomDot(View view) {
        Random random = new Random();
        //Random locate
        int centerX = random.nextInt(this.dotView.getWidth());
        int centerY = random.nextInt(this.dotView.getHeight());
        int radius = (int) (30 + (Math.random() * 150));
        Dot newDot = new Dot(centerX, centerY, radius, new Colors().getColor());
        dots.addDot(newDot);
    }

    @Override
    public void onDotsChanged(Dots dots) {
        dotView.setDots(dots); //create meth setDot bec setDot เรื่อย ๆ
        dotView.invalidate();
    }

    @Override
    public void onDotViewPressed(int x, int y) {
        final int dotPosition = dots.findDot(x, y);
        final int radius = (int) (30 + (Math.random() * 150));
        if (dotPosition == -1) { //if don't have dot on this place
            Dot newDot = new Dot(x, y, radius, new Colors().getColor());
            dots.addDot(newDot);
        } else {
            dots.editDot(this, dotPosition, radius);
        }
    }

    public void btnCapture(View view) {
        //ARK PERMISSION
        if (askPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, EXTERNAL_REQUEST_CODE) == 0) {
            Bitmap b = Screenshot.takescreenshotOfRootView(imageView);
            Uri uri = createFile(getContext(), b);
            //CREATE INTENT
            createIntent(uri);
        }
    }

    private int askPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(getContext(), permission) != PackageManager.PERMISSION_GRANTED) {
            //dont have permission
            ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, requestCode);
            return 1;
        } else {
            //have permission
            Toast.makeText(getContext(), "Permission is Already Granted", Toast.LENGTH_SHORT).show();
            return 0;
        }
    }

    //CREATE FILE FOR SAVE PICTURE(SCREENSHOT)
    private Uri createFile(Context context, Bitmap b) {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        //COMPRESS BITMAP
        b.compress(Bitmap.CompressFormat.PNG, 80, byteArray);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), b, "screenshot_pic", null);
        return Uri.parse(path);
    }

    //INTENT TO APP
    private void createIntent(Uri uri) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(shareIntent, "Share image"));
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permission, @NonNull int[] grantResults) {
        switch (requestCode) {
            case EXTERNAL_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getContext(), "External Permission Granted", Toast.LENGTH_SHORT).show();
                    btnCapture(imageView);
                } else {
                    Toast.makeText(getContext(), "External Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void onClearDot(View view) {
        dots.clearAll();
        dotView.invalidate();
    }


    public void btnUndo(View view) {
        dots.undoDot();
    }

    public void setListener(dotFragmentListener listener) {
        this.listener = listener;
    }

    public interface dotFragmentListener {
        void showAndUpdateB(String value);
    }

}
