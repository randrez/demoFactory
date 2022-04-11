package com.factory.demofactory;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void exitApp(){
        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        this.startActivity(i);
    }

    public void changeNameActionBar(int value){
        this.getSupportActionBar().setTitle(value);
    }

    public void hideActionBar(){
        this.getSupportActionBar().hide();
    }

    public void showActionBar(){
        this.getSupportActionBar().show();
    }
}