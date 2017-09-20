package kmitl.lab03.natcha58070069.simplemydot.fragment;


import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kmitl.lab03.natcha58070069.simplemydot.R;
import kmitl.lab03.natcha58070069.simplemydot.model.Dot;
import kmitl.lab03.natcha58070069.simplemydot.model.Dots;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditDotFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_edit_dot, container, false);
        String message = getArguments().getString("HELO");
        TextView b = (TextView) rootView.findViewById(R.id.textView);
        b.setText(message);
       return rootView;
    }

    public static EditDotFragment newInstance(Dots dots, Dot dot, int dotPosition) {

        Bundle args = new Bundle();

//        args.putParcelable(dots, dots);
//        args.putParcelable(dot, dot);
//        args.putParcelable(dotPosition, dotPosition);

        EditDotFragment fragment = new EditDotFragment();
        fragment.setArguments(args);

//        System.out.println(dots + "---" + dot + "---" + dotPosition);

        return fragment;
    }



}
