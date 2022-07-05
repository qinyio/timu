package com.example.mainshitimu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ShuiShouActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView_shuihou;
    private Button button_jishui;
    private EditText editText_shuiqian;
    private double money = 0;
    private double gerenshui = 0, yanglao = 0, yiliao = 0, shiye = 0, shengyu = 0, gongshang = 0, gongjijin = 0;
    private TextView textView_geshui;
    private TextView textView_wuxian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shui_shou);

        initViews();
    }

    private void initViews() {
        editText_shuiqian = findViewById(R.id.shuishou_edittext_shuiqiangongzi);
        editText_shuiqian.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        button_jishui = findViewById(R.id.shuishou_button_jishui);

        textView_shuihou = findViewById(R.id.shuishou_textview_shuihougongzi);
        textView_wuxian = findViewById(R.id.shuishou_textview_wuxianyijin);
        textView_geshui = findViewById(R.id.shuishou_textview_gerenshui);

        button_jishui.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shuishou_button_jishui:
                textView_wuxian.setText("");
                textView_shuihou.setText("");

                String str = editText_shuiqian.getText().toString();
                //判断是否为空
                if (!str.isEmpty()) {
                    money = Double.parseDouble(str);
                    //五险一金
                    wuxianyijin(money);
                    textView_wuxian.append("养老保险:" + yanglao + "\n");
                    textView_wuxian.append("医疗保险:" + yiliao + "\n");
                    textView_wuxian.append("失业保险:" + shiye + "\n");
                    textView_wuxian.append("生育保险:" + shengyu + "\n");
                    textView_wuxian.append("工伤保险:" + gongshang + "\n");
                    textView_wuxian.append("住房公积金:" + String.format("%.5f", gongjijin));
                } else {
                    Toast.makeText(this, "请输入税前工资", Toast.LENGTH_SHORT).show();
                }
                //个人所得税
                personal_tax(money);
                textView_geshui.setText(gerenshui + "");

                textView_shuihou.setText(money - gerenshui - yanglao - yiliao - shiye - shengyu - gongshang - gongjijin + "");
                break;
        }
    }

    private void wuxianyijin(Double d) {
        // 养老保险
        yanglao = d * 0.08;
        // 医疗保险
        yiliao = d * 0.02;
        // 失业保险
        shiye = d * 0.005;
        // 生育保险
        // 工伤保险
        // 住房公积金
        gongjijin = d * 0.07;
    }

    private void personal_tax(Double d) {
        if (d > 1 && d <= 5000) {
            if (money - 5000 - yanglao - yiliao - shiye - gongjijin > 0) {
                gerenshui = (money - 5000 - yanglao - yiliao - shiye - gongjijin) * 0.00;
            } else {
                gerenshui = 0;
            }
        } else if (d > 5000 && d <= 8000) {
            if (money - 5000 - yanglao - yiliao - shiye - gongjijin > 0) {
                gerenshui = (money - 5000 - yanglao - yiliao - shiye - gongjijin) * 0.03;
            } else {
                gerenshui = 0;
            }
        } else if (d > 8000 && d <= 17000) {
            gerenshui = (money - 5000 - yanglao - yiliao - shiye - gongjijin) * 0.1 - 210;
        } else if (d > 17000 && d <= 30000) {
            gerenshui = (money - 5000 - yanglao - yiliao - shiye - gongjijin) * 0.2 - 1410;
        } else if (d > 30000 && d <= 40000) {
            gerenshui = (money - 5000 - yanglao - yiliao - shiye - gongjijin) * 0.25 - 2660;
        } else if (d > 40000 && d <= 60000) {
            gerenshui = (money - 5000 - yanglao - yiliao - shiye - gongjijin) * 0.3 - 4410;
        } else if (d > 60000 && d <= 85000) {
            gerenshui = (money - 5000 - yanglao - yiliao - shiye - gongjijin) * 0.35 - 7160;
        } else if (d > 85000) {
            gerenshui = (money - 5000 - yanglao - yiliao - shiye - gongjijin) * 0.45 - 15160;
        }
        System.out.println(gerenshui);
    }

    /*//判断字符串是否是数字
    public static boolean isNumber(String value) {
        return isInteger(value) || isDouble(value);
    }

    //判断字符串是否是整数
    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //判断字符串是否是浮点数
    public static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            if (value.contains("."))
                return true;
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }*/
}