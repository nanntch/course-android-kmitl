package kmitl.natcha58070069.com.moneyflow;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MyAdapter.MyAdapterListener {

    private OrderDB orderDB;
    private MyAdapter adapter;

    private TextView totalText;
    private RecyclerView list;
    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Database
        orderDB = Room.databaseBuilder(this, OrderDB.class, "ORDER_INFO")
                .fallbackToDestructiveMigration()
                .build();

        //set Adapter
        adapter = new MyAdapter(this);
        adapter.setContext(this);
        adapter.setListener(this);

        //find view
        totalText = findViewById(R.id.totalText);
        list = findViewById(R.id.list_item);

        //set RecyclerView
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        //Button
        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(this);

        //for Set total Balance
        loadData();
    }

    private void loadData() {
        //set Total Balance
        new AsyncTask<Void, Void, BalanceInfo>() {
            @Override
            protected BalanceInfo doInBackground(Void... voids) {
                return orderDB.getOrderInfoDAO().getBalance();
            }

            @Override
            protected void onPostExecute(BalanceInfo balanceInfo) {
                super.onPostExecute(balanceInfo);

                int total = balanceInfo.getBalance();
                int sum_income = balanceInfo.getSum_income();

                if(total > sum_income/2){
                    totalText.setTextColor(Color.parseColor("#00BF00"));
                } else if (total < sum_income/4){
                    totalText.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    totalText.setTextColor(Color.parseColor("#FFCC33"));
                }
                totalText.setText(NumberFormat.getNumberInstance().format(total));
            }
        }.execute();

        //set List of all detail
        new AsyncTask<Void, Void, List<OrderInfo>>() {
            @Override
            protected List<OrderInfo> doInBackground(Void... voids) {
                List<OrderInfo> result = orderDB.getOrderInfoDAO().findAll();
                return result;
            }

            @Override
            protected void onPostExecute(List<OrderInfo> orderInfos) {
                super.onPostExecute(orderInfos);
                adapter.setData(orderInfos);
                adapter.notifyDataSetChanged();
            }
        }.execute();
    }

    //When Click Info item (for edit or delete)
    @Override
    public void onClickInfoItem(OrderInfo orderInfo) {
        Intent intent = new Intent(this, AddDetail.class);
        intent.putExtra("OrderInfo", orderInfo);
        startActivityForResult(intent, 999);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addBtn:
                Intent intent = new Intent(MainActivity.this, AddDetail.class);
                startActivityForResult(intent, 999);
        }
    }

    //for request Code == 999
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(resultCode == RESULT_OK && requestCode == 999){
            loadData();
        }
    }
}
