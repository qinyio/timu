package com.example.mainshitimu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button_jishui;
    private Button button_daikuan;
    private Button button_24dian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        button_jishui = findViewById(R.id.main_button_jishui);
        button_daikuan = findViewById(R.id.main_button_daikuan);
        button_24dian = findViewById(R.id.main_button_24dian);

        button_jishui.setOnClickListener(this);
        button_daikuan.setOnClickListener(this);
        button_24dian.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_button_jishui:
                Intent intent1 = new Intent(this, ShuiShouActivity.class);
                startActivity(intent1);
                break;
            case R.id.main_button_daikuan:
                Intent intent2 = new Intent(this, DaiKuanActivity.class);
                startActivity(intent2);
                break;
            case R.id.main_button_24dian:
                Intent intent3 = new Intent(this, ErShiSiDianActivity.class);
                startActivity(intent3);
                break;
        }
    }
}