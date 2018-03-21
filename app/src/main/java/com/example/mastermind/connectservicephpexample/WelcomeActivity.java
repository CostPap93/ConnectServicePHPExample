package com.example.mastermind.connectservicephpexample;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by mastermind on 20/3/2018.
 */

public class WelcomeActivity extends Activity{
    TextView tvWelcome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        tvWelcome = findViewById(R.id.tv_welcome);
        tvWelcome.setText("Welcome: " +  getIntent().getStringExtra("username"));

    }

    public void btnBackClicked(View view){
        finish();
    }
}
