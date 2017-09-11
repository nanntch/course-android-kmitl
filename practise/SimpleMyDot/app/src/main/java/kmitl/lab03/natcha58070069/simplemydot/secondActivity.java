package kmitl.lab03.natcha58070069.simplemydot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import kmitl.lab03.natcha58070069.simplemydot.model.DotParcelable;
import kmitl.lab03.natcha58070069.simplemydot.model.DotSerializable;

public class secondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //Intent intent = new Intent(secondActivity.this, MainActivity.class);
                finish();
            }
    });

        TextView tvValueX = (TextView) findViewById(R.id.tvValueX);
        TextView tvDot = (TextView) findViewById(R.id.tvDot);


        int x = getIntent().getIntExtra("xValue", 0); //0 is default
        //รับด้วย key เดิมที่ส่งมาจาก Main
        DotSerializable dotSerializable = (DotSerializable) getIntent().getSerializableExtra("dotSerializable");

        tvValueX.setText(String.valueOf(x)); //ทำให้สิ่งที่แสดงผลเป็นstring

        //tvDot.setText("center x : " + dotSerializable.getCenterX()); มันเยอะเราสามารถเจนได้ตั้งแต่หลังกำหนดค่าเลย toString()
        tvDot.setText(dotSerializable.toString());

        //Parcelable
        DotParcelable dotParcelable = getIntent().getParcelableExtra("dotParcelable");
        tvDot.setText(dotParcelable.toString());

    }
}
