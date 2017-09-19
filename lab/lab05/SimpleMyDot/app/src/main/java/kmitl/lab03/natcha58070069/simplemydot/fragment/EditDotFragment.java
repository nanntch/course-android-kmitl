package kmitl.lab03.natcha58070069.simplemydot.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kmitl.lab03.natcha58070069.simplemydot.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditDotFragment extends Fragment {


    public EditDotFragment() {
        // Required empty public constructor
    }

    public static EditDotFragment newInstance() {

        Bundle args = new Bundle();

        EditDotFragment fragment = new EditDotFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_dot, container, false);
    }

}
