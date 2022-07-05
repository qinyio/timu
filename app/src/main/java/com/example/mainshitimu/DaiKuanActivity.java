package com.example.mainshitimu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DaiKuanActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText_jine;
    private EditText editText_nianxian;
    private EditText editText_lixi;
    private Button button_jisuan;
    private RadioButton radioButton_benjin;
    private RadioButton radioButton_benxi;
    private double DaiKuanJinE = 0, DaiKuanLiXi = 0;
    private int DaiKuanYue = 0;
    private TextView textView_zonglixi;
    private RecyclerView recyclerView;
    private TextView textView_meiyuehuankuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dai_kuan);


        initViews();
    }

    private void initViews() {
        editText_jine = findViewById(R.id.daikuan_edittext_jine);
        editText_nianxian = findViewById(R.id.daikuan_edittext_nianxian);
        editText_lixi = findViewById(R.id.daikuan_edittext_lixi);
        recyclerView = findViewById(R.id.daikuan_recy);
        textView_meiyuehuankuan = findViewById(R.id.daikuan_textview_meiyue);

        textView_zonglixi = findViewById(R.id.daikuan_textview_zonglixi);

        button_jisuan = findViewById(R.id.daikuan_button_jisuan);
        radioButton_benjin = findViewById(R.id.benjin);
        radioButton_benxi = findViewById(R.id.benxi);

        button_jisuan.setOnClickListener(this);

        editText_jine.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editText_nianxian.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText_lixi.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.daikuan_button_jisuan:
                String jine = editText_jine.getText().toString();
                String nianxian = editText_nianxian.getText().toString();
                String lixi = editText_lixi.getText().toString();

                //判断输入是否为空
                if (!jine.isEmpty() && !nianxian.isEmpty() && !lixi.isEmpty()) {
                    DaiKuanJinE = Double.parseDouble(jine);
                    DaiKuanYue = Integer.parseInt(nianxian);
                    DaiKuanLiXi = Double.parseDouble(lixi);

                    double zonglixi = 0;
                    ArrayList<HuanKuan> list = new ArrayList<>();
                    if (radioButton_benjin.isChecked()) {//选择等额本金
                        textView_meiyuehuankuan.setText("");
                        double YuE = DaiKuanJinE;
                        for (int i = 0; i < DaiKuanYue; i++) {

                            YuE = DaiKuanJinE - i * DaiKuanJinE / DaiKuanYue;
                            zonglixi = zonglixi + YuE * DaiKuanLiXi;
                            HuanKuan huanKuan = new HuanKuan();
                            huanKuan.setBenjin(DaiKuanJinE / DaiKuanYue);
                            huanKuan.setLixi(YuE * DaiKuanLiXi);

                            list.add(huanKuan);

                        }
                        textView_zonglixi.setText(zonglixi + "");
                        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                        recyclerView.setLayoutManager(layoutManager);
                        MyAdapter adapter = new MyAdapter(this, list);
                        recyclerView.setAdapter(adapter);

                    } else if (radioButton_benxi.isChecked()) {//选择等额本息
                        double meiyuehuankuan = DaiKuanJinE * (DaiKuanLiXi * Math.pow((1 + DaiKuanLiXi), DaiKuanYue)) / (Math.pow((1 + DaiKuanLiXi), DaiKuanYue) - 1);
                        textView_meiyuehuankuan.setText(meiyuehuankuan + "");
                        textView_zonglixi.setText(meiyuehuankuan * DaiKuanYue - DaiKuanJinE + "");

                        double yihuanbenjin=0;
                        for (int i = 0; i < DaiKuanYue; i++) {

                            HuanKuan huanKuan = new HuanKuan();
                            huanKuan.setBenjin(meiyuehuankuan-(DaiKuanJinE - yihuanbenjin) * DaiKuanLiXi);
                            huanKuan.setLixi((DaiKuanJinE - yihuanbenjin) * DaiKuanLiXi);
                            list.add(huanKuan);

                            yihuanbenjin=yihuanbenjin+(meiyuehuankuan-(DaiKuanJinE - yihuanbenjin) * DaiKuanLiXi);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                            recyclerView.setLayoutManager(layoutManager);
                            MyAdapter adapter = new MyAdapter(this, list);
                            recyclerView.setAdapter(adapter);
                        }
                    }
                } else {
                    Toast.makeText(this, "请检查输入", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


}