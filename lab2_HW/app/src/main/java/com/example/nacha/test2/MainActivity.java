package com.example.nacha.test2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_at_home_1);
    }

    public void buttonApps(View view){
        Toast.makeText(
                getBaseContext(), "onClickApps : success Apps inner Class",
                Toast.LENGTH_LONG)
                .show();
    }

    public void buttonGames(View view){
        Toast.makeText(
                getBaseContext(), "onClickGames : success Games inner Class",
                Toast.LENGTH_LONG)
                .show();
    }

    public void buttonMovies(View view){
        Toast.makeText(
                getBaseContext(), "onClickMovies : success Movies inner Class",
                Toast.LENGTH_LONG)
                .show();
    }

    public void buttonBooks(View view){
        Toast.makeText(
                getBaseContext(), "onClickBooks : success Books inner Class",
                Toast.LENGTH_LONG)
                .show();
    }

    public void buttonMore1(View view){
        Toast.makeText(
                getBaseContext(), "onClickMore1 : success More inner Class",
                Toast.LENGTH_LONG)
                .show();
    }

    public void buttonMore2(View view){
        Toast.makeText(
                getBaseContext(), "onClickMore2 : success More inner Class",
                Toast.LENGTH_LONG)
                .show();
    }

    public void butt1(View view){
        Toast.makeText(
                getBaseContext(), "onClickButt1 : success See new picture inner Class",
                Toast.LENGTH_LONG)
                .show();
    }

    public void butt2(View view){
        Toast.makeText(
                getBaseContext(), "onClickButt2 : success Skip inner Class",
                Toast.LENGTH_LONG)
                .show();
    }
}