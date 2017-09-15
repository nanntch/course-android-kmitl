package natcha.natcha58070069.kmitl.a01_lab05_workshop;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import natcha.natcha58070069.kmitl.a01_lab05_workshop.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //เอา group's fragment มา
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                //add
//                .add(R.id.fragmentContainer, new MainFragment())

                //add color
                .add(R.id.fragmentContainer, new MainFragment().newInstance("My name is Nacha"))
                .commit();

        Button accessFragment = (Button) findViewById(R.id.accessFragment);

        accessFragment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, new MainFragment().newInstance("From Activity"))
                        .commit();
            }
        });

    }
}
