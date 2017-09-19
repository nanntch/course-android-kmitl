package kmitl.lab03.natcha58070069.simplemydot;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kmitl.lab03.natcha58070069.simplemydot.R;
import kmitl.lab03.natcha58070069.simplemydot.model.Dot;
import kmitl.lab03.natcha58070069.simplemydot.model.Dots;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditDotFragment extends Fragment implements View.OnClickListener {


    public EditDotFragment() {
        // Required empty public constructor
    }

    public static EditDotFragment newInstance(Dot dot, Dots dots, int position, int radius) {
        
        Bundle args = new Bundle();
        
        EditDotFragment fragment = new EditDotFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_edit_dot, container, false);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        
    }

}
