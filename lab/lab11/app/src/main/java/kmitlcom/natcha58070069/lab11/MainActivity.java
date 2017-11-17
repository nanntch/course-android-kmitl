package kmitlcom.natcha58070069.lab11;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CounterViewModel viewModel;
    private TextView result;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(CounterViewModel.class);

        result = findViewById(R.id.tv_count);
        Button clickMe = findViewById(R.id.clickMeBtn);

        clickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.setCounter(viewModel.getCounter() + 1);
//                counter ++;
                displayCounter();
            }
        });
    }

    private void displayCounter() {
        result.setText(String.valueOf(viewModel.getCounter()));
    }

}
