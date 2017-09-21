package kmitl.lab03.natcha58070069.simplemydot.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import kmitl.lab03.natcha58070069.simplemydot.R;
import kmitl.lab03.natcha58070069.simplemydot.model.Dot;
import kmitl.lab03.natcha58070069.simplemydot.model.Dots;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditDotFragment extends Fragment implements SeekBar.OnSeekBarChangeListener, View.OnClickListener{

    SeekBar red, green, blue;
//    ConstraintLayout co;
    private TextView prototype;

    private static final String dotString = "dot";
    private static final String dotsString = "dots";
    private static final String dotPositionString = "dotPosition";

    private Dots dots;
    private Dot dot;
    private int dotPosition;
    private int id;
    private EditText centerX;
    private EditText centerY;
    private EditText radius;

    private int color;


    public static EditDotFragment newInstance(Dots dots, Dot dot, int dotPosition) {

        Bundle args = new Bundle();
        EditDotFragment fragment = new EditDotFragment();

        args.putParcelable(dotsString, dots);
        args.putParcelable(dotString, dot);
        args.putInt(dotPositionString, dotPosition);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_edit_dot, container, false);

        red = (SeekBar) rootView.findViewById(R.id.seekBar);
        green = (SeekBar) rootView.findViewById(R.id.seekBar2);
        blue = (SeekBar) rootView.findViewById(R.id.seekBar3);
//        co = (ConstraintLayout) rootView.findViewById(R.id.constrain);
        prototype = (TextView) rootView.findViewById(R.id.prototype);


        centerX = (EditText) rootView.findViewById(R.id.putCenterX);
        centerY = (EditText) rootView.findViewById(R.id.putCenterY);
        radius = (EditText) rootView.findViewById(R.id.putRadius);

        Button summit = (Button) rootView.findViewById(R.id.summit);
        summit.setOnClickListener(this);

        red.setOnSeekBarChangeListener(this);
        green.setOnSeekBarChangeListener(this);
        blue.setOnSeekBarChangeListener(this);

        dots = getArguments().getParcelable(dotsString);
        dot = getArguments().getParcelable(dotString);
        dotPosition = getArguments().getInt(dotPositionString);

        centerX.setText(String.valueOf(dot.getCenterX()));
        centerY.setText(String.valueOf(dot.getCenterY()));
        radius.setText(String.valueOf(dot.getRadius()));
        red.getProgress();
        green.getProgress();
        blue.getProgress();
        Color.rgb(red.getProgress(), green.getProgress(), blue.getProgress());

       return rootView;
    }



    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//        co.setBackgroundColor(Color.rgb(red.getProgress(), green.getProgress(), blue.getProgress()));
        prototype.setBackgroundColor(Color.rgb(red.getProgress(), green.getProgress(), blue.getProgress()));
        color = Color.rgb(red.getProgress(), green.getProgress(), blue.getProgress());
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onClick(View v) {
        id = v.getId();
        if (id == R.id.summit){
            dot.setColor(color);
            dot.setCenterX(Float.parseFloat(String.valueOf(centerX.getText())));
            dot.setCenterY(Float.parseFloat(String.valueOf(centerY.getText())));
            dot.setRadius(Float.parseFloat(String.valueOf(radius.getText())));
            dots.editDotAttr(dotPosition, dot);
            getActivity().onBackPressed();
        }
    }
}
