package kmitl.lab03.natcha58070069.simplemydot;


import android.app.Activity;
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

import kmitl.lab03.natcha58070069.simplemydot.model.Colors;
import kmitl.lab03.natcha58070069.simplemydot.model.Dot;
import kmitl.lab03.natcha58070069.simplemydot.model.Dots;
import kmitl.lab03.natcha58070069.simplemydot.view.DotView;
import kmitl.lab03.natcha58070069.simplemydot.view.Screenshot;

import static android.R.attr.fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements Dots.OnDotsChangeListener,
       DotView.OnDotViewPressListener, View.OnClickListener {

    private Dots dots;
    private DotView dotView;
    private ImageView imageView;
    private MyFragmentListener listener;
//    private final int EXTERNAL_REQUEST_CODE = 2;

//    public MainFragment() {
//        // Required empty public constructor
//    }

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        imageView = (ImageView) rootView.findViewById(R.id.imageView);
        dotView = (DotView) rootView.findViewById(R.id.dotView);
        dotView.setOnDotViewPressListener(this);
        dots = new Dots();
        dots.setListener(this);

        Button btnCapture = (Button) rootView.findViewById(R.id.btnCapture);
        Button onRandomDot = (Button) rootView.findViewById(R.id.button);
        Button onClearDot = (Button) rootView.findViewById(R.id.button2);
        Button btnUndo = (Button) rootView.findViewById(R.id.btnUndo);

        btnCapture.setOnClickListener(this);
        onRandomDot.setOnClickListener(this);
        onClearDot.setOnClickListener(this);
        btnUndo.setOnClickListener(this);

        return rootView;
    }

    public void onRandomDot() {
        Random random = new Random();
        //Random locate
        int centerX = random.nextInt(dotView.getWidth());
        int centerY = random.nextInt(dotView.getHeight());
        int radius = (int) (30+(Math.random()*150));
        Dot newDot = new Dot(centerX, centerY, radius, new Colors().getColor());
        dots.addDot(newDot);
    }

    public void onClearDot() {
        dots.clearAll();
        dotView.invalidate();
    }

    public void btnUndo(){
        dots.undoDot();
    }

//    public void onAttach(Context context){
//        super.onAttach(context);
//        try{
//            listener = (MyFragmentListener) getActivity();
//        } catch (ClassCastException e){
//            throw new ClassCastException("Must implement MyFragmentListener");
//        }
//    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.button){
            onRandomDot();
        } else if (id == R.id.button2){
            onClearDot();
        } else if (id == R.id.btnUndo){
            btnUndo();
        } else if (id == R.id.btnCapture)
    }

    @Override
    public void onDotsChanged(Dots dots) {
        dotView.setDots(dots); //create meth setDot bec setDot เรื่อย ๆ
        dotView.invalidate();
    }

    @Override
    public void onDotViewPressed(int x, int y) {
        final int dotPosition = dots.findDot(x, y);
        final int radius = (int) (30+(Math.random()*150));
        if(dotPosition == -1){
            Dot newDot = new Dot(x, y, radius, new Colors().getColor());
            dots.addDot(newDot);
        } else {
            dots.editDot(this, dotPosition, radius);
        }
    }

    public interface MyFragmentListener{
        //call EditDot but it not finish
    }

    public  void setListener(MyFragmentListener listener){
        this.listener = listener;
    }

//    public void btnCapture(View v){
//        //ARK PERMISSION
//        if (askPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, EXTERNAL_REQUEST_CODE) == 0){
//            Bitmap b = Screenshot.takescreenshotOfRootView(imageView);
//            Uri uri = createFile(this,b);
//            //CREATE INTENT
//            createIntent(uri);
//        }
//    }

//    private int askPermission(String permission, int requestCode){
//        if (ContextCompat.checkSelfPermission(getContext(),permission) != PackageManager.PERMISSION_GRANTED){
//            //dont have permission
//            ActivityCompat.requestPermissions((Activity) getContext(), new String[]{permission}, requestCode);
//            return 1;
//        }
//        else {
//            //have permission
//            Toast.makeText(getContext(), "Permission is Already Granted", Toast.LENGTH_SHORT).show();
//            return 0;
//        }
//    }
//
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permission, @NonNull int[] grantResults){
//        switch (requestCode){
//            case EXTERNAL_REQUEST_CODE:
//                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
//                    Toast.makeText(getContext(), "External Permission Granted", Toast.LENGTH_SHORT).show();
//                    btnCapture(imageView);
//                } else {
//                    Toast.makeText(getContext(), "External Permission Denied", Toast.LENGTH_SHORT).show();
//                }
//                break;
//        }
//    }
//    //INTENT TO APP
//    private void createIntent(Uri uri){
//        Intent shareIntent = new Intent();
//        shareIntent.setAction(Intent.ACTION_SEND);
//        shareIntent.setType("image/*");
//        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
////        startActivity(Intent.createChooser(shareIntent, "Share image"));
//        startActivity(Intent.createChooser(shareIntent, "share image"));
//    }
//
//    //CREATE FILE FOR SAVE PICTURE(SCREENSHOT)
//    private Uri createFile(MainFragment context, Bitmap b){
//        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
//        //COMPRESS BITMAP
//        b.compress(Bitmap.CompressFormat.PNG, 80, byteArray);
//        String path = MediaStore.Images.Media.insertImage(getContext().getContentResolver(), b,"screenshot_pic", null);
//        return Uri.parse(path);
//    }
}
