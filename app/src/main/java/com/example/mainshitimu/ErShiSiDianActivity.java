package com.example.mainshitimu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ErShiSiDianActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private TextView textView;
    private ArrayList<ZuHe> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_er_shi_si_dian);

        initViews();

    }

    private void initViews() {
        button = findViewById(R.id.ershisi_button);
        textView = findViewById(R.id.ershisi_textview);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ershisi_button:
                textView.setText("");
                //所有数字组合
                zuHe();
                //遍历组合
                for (int i = 0; i < list.size(); i++) {
                    hecheng(list.get(i).getI(), list.get(i).getJ(), list.get(i).getK());
                }
                break;
        }
    }

    private void hecheng(double i, double j, double k) {
        if (i + j + k == 24) {
            textView.append(i + "+" + j + "+" + k + "=24\n");
        }
        if (i + j * k == 24) {
            textView.append(i + "+" + j + "*" + k + "=24\n");
        }
        if (j + i * k == 24) {
            textView.append(j + "+" + i + "*" + k + "=24\n");
        }
        if (k + i * j == 24) {
            textView.append(k + "+" + i + "*" + j + "=24\n");
        }
        if (i * j - k == 24) {
            textView.append(i + "*" + j + "-" + k + "=24\n");
        }
        if (i * k - j == 24) {
            textView.append(i + "*" + k + "-" + j + "=24\n");
        }
        if (j * k - i == 24) {
            textView.append(j + "*" + k + "-" + i + "=24\n");
        }
        if (i * j * k == 24) {
            textView.append(i + "*" + j + "*" + k + "=24\n");
        }
        if (i * j / k == 24) {
            textView.append(i + "*" + j + "/" + k + "=24\n");
        }
        if (i * k / j == 24) {
            textView.append(i + "*" + k + "/" + j + "=24\n");
        }
        if (j * k / i == 24) {
            textView.append(j + "*" + k + "/" + i + "=24\n");
        }
        if (i / j * k == 24) {
            textView.append(i + "/" + j + "*" + k + "=24\n");
        }
        if (i / k * j == 24) {
            textView.append(i + "/" + k + "*" + j + "=24\n");
        }
        if (j / i * k == 24) {
            textView.append(j + "/" + i + "*" + k + "=24\n");
        }
        if (j / k * i == 24) {
            textView.append(j + "/" + k + "*" + i + "=24\n");
        }
        if (k / j * i == 24) {
            textView.append(k + "/" + j + "*" + i + "=24\n");
        }
        if (k / i * j == 24) {
            textView.append(k + "/" + i + "*" + j + "=24\n");
        }


    }


    private void zuHe() {
        list = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            for (int j = i + 1; j <= 9; j++) {
                for (int k = j + 1; k <= 9; k++) {
                    ZuHe zuHe = new ZuHe();
                    zuHe.setI(i);
                    zuHe.setJ(j);
                    zuHe.setK(k);
                    list.add(zuHe);
                }
            }
        }
    }
}