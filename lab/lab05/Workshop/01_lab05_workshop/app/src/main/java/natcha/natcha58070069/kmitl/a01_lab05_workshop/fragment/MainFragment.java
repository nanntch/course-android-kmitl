package natcha.natcha58070069.kmitl.a01_lab05_workshop.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import natcha.natcha58070069.kmitl.a01_lab05_workshop.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    String message = "";
    public static MainFragment newInstance(String message) {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        args.putString("message", message);
        fragment.setArguments(args);
        return fragment;
    }

    //String message = "";
    public  void onCreate(@Nullable Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        message = getArguments().getString("message");
    }

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_main, container, false);
        TextView fragmentTextView = (TextView) rootview .findViewById(R.id.fragmentTextView);

        if(!message.isEmpty()){
            fragmentTextView.setText(message);
        }
        return rootview;

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_main, container, false);
    }

}
