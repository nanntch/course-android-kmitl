package kmitl.natcha58070069.com.moneyflow;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddDetail extends MainActivity implements View.OnClickListener {

    private Button incomeBtn;
    private Button expensesBtn;
    private Button saveBtn;
    private Button deleteBtn;

    private EditText editOrder;
    private EditText editAmount;

    private OrderDB orderDB;
    private OrderInfo orderInfo;

    public String type = "income";
    private String status;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_detail);

        //Database
        orderDB = Room.databaseBuilder(this, OrderDB.class, "ORDER_INFO")
                .fallbackToDestructiveMigration()
                .build();

        deleteBtn = findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(this);
        deleteBtn.setVisibility(View.GONE); //hide for click item

        saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(this);

        incomeBtn = findViewById(R.id.incomeBtn);
        incomeBtn.setOnClickListener(this);

        expensesBtn = findViewById(R.id.expensesBtn);
        expensesBtn.setOnClickListener(this);

        editOrder = findViewById(R.id.editOrder);
        editAmount = findViewById(R.id.editAmount);

        //when click item
        orderInfo = getIntent().getParcelableExtra("OrderInfo");

        if(orderInfo == null){
            orderInfo = new OrderInfo();
            status = "add";
        } else {
            status = "update";
            editOrder.setText(orderInfo.getOrder());
            editAmount.setText(orderInfo.getAmount()+"");
            deleteBtn.setVisibility(View.VISIBLE);
            if(orderInfo.getType().equals("income")){
                income();
            }else{
                expenses();
            }
        }
    }

    public void income(){
        incomeBtn.setBackgroundColor(Color.parseColor("#87CEFA"));
        expensesBtn.setBackgroundColor(Color.parseColor("#F8F8FF"));
        type = "income";
    }
    
    public void expenses(){
        incomeBtn.setBackgroundColor(Color.parseColor("#F8F8FF"));
        expensesBtn.setBackgroundColor(Color.parseColor("#87CEFA"));
        type = "expenses";
    }

    @Override
    public void onClick(View v) {
        //save button, delete button, income, expenses
        switch (v.getId()){
            case R.id.saveBtn:
                onSaveBtn();
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.deleteBtn:
                onDeleteBtn();
                Intent intent1 = new Intent();
                setResult(RESULT_OK, intent1);
                finish();
                break;
            case R.id.incomeBtn:
                income();
                break;
            case R.id.expensesBtn:
                expenses();
                break;
        }
    }

    private void onDeleteBtn() {
        new AsyncTask<Void, Void, OrderInfo>() {
            @Override
            protected OrderInfo doInBackground(Void... voids) {
                orderDB.getOrderInfoDAO().delete(orderInfo);
                return null;
            }
        }.execute();
    }

    private void onSaveBtn() {
        if(orderInfo == null){
            orderInfo = new OrderInfo();
        }

        String order = editOrder.getText().toString();
        String amount = editAmount.getText().toString();

        if(order.equals("") || amount.equals("")) {
            Toast.makeText(this, "Please enter fully information", Toast.LENGTH_LONG).show();
            return;
        }else {
            orderInfo.setType(type);
            orderInfo.setOrder(order);
            orderInfo.setAmount(Integer.parseInt(amount));
        }

        //insert or update
        if(status == "add"){
            new AsyncTask<Void, Void, OrderInfo>() {
                @Override
                protected OrderInfo doInBackground(Void... voids) {
                    orderDB.getOrderInfoDAO().insert(orderInfo);
                    return null;
                }
            }.execute();
        } else {
            new AsyncTask<Void, Void, OrderInfo>() {
                @Override
                protected OrderInfo doInBackground(Void... voids) {
                    orderDB.getOrderInfoDAO().update(orderInfo);
                    return null;
                }
            }.execute();
        }
    }
}
