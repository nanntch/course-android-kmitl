package kmitl.lab03.natcha58070069.simplemydot;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kmitl.lab03.natcha58070069.simplemydot.fragment.DotFragment;
import kmitl.lab03.natcha58070069.simplemydot.fragment.EditDotFragment;
import kmitl.lab03.natcha58070069.simplemydot.model.Dot;
import kmitl.lab03.natcha58070069.simplemydot.model.Dots;

//DotFragment.DataPassListener
public class MainActivity extends AppCompatActivity implements DotFragment.dotFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            initialFragment();
        }
    }

    private void initialFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, DotFragment.newInstance(MainActivity.this))
                .commit();
    }

    private void viewFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void EditDotFragment(Dots dots, Dot dot, int dotPosition) {
        viewFragment(EditDotFragment.newInstance(dots, dot, dotPosition));
    }
}
