package com.example.ipi;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ipi.R;
import java.lang.Math;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    static {
        System.loadLibrary("ipi");
    }
    //创建Button对象   也就是activity_main.xml里所设置的ID
    Button btn_0,btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9,btn_00;
    Button btn_leibniz,btn_ramanujan,btn_chudnovsky,btn_wallis;
    Button btn_clr,btn_del,btn_eq;
    EditText et_input, et_computing_time;
    boolean clr_flag;    //判断et编辑文本框中是否清空
    int formula_type = 0; // 判断使用计算公式类型，默认使用Leibniz公式
    int ACCURACY = 1; // 精度

    // Leibniz
    // Ramanujan
    // Chudnovsky
    // Wallis

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例化对象
        setContentView(R.layout.activity_main);
        btn_0= (Button) findViewById(R.id.btn_0);
        btn_1= (Button) findViewById(R.id.btn_1);
        btn_2= (Button) findViewById(R.id.btn_2);
        btn_3= (Button) findViewById(R.id.btn_3);
        btn_4= (Button) findViewById(R.id.btn_4);
        btn_5= (Button) findViewById(R.id.btn_5);
        btn_6= (Button) findViewById(R.id.btn_6);
        btn_7= (Button) findViewById(R.id.btn_7);
        btn_8= (Button) findViewById(R.id.btn_8);
        btn_9= (Button) findViewById(R.id.btn_9);
        btn_00= (Button) findViewById(R.id.btn_00);
        btn_leibniz= (Button) findViewById(R.id.btn_leibniz);
        btn_ramanujan= (Button) findViewById(R.id.btn_ramanujan);
        btn_chudnovsky= (Button) findViewById(R.id.btn_chudnovsky);
        btn_wallis= (Button) findViewById(R.id.btn_wallis);
        btn_clr= (Button) findViewById(R.id.btn_clr);
        btn_del= (Button) findViewById(R.id.btn_del);
        btn_eq= (Button) findViewById(R.id.btn_eq);
        et_input= (EditText) findViewById(R.id.et_input);
        et_computing_time= (EditText) findViewById(R.id.et_computing_time);
        //给按钮设置的点击事件
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_00.setOnClickListener(this);
        btn_leibniz.setOnClickListener(this);
        btn_ramanujan.setOnClickListener(this);
        btn_chudnovsky.setOnClickListener(this);
        btn_wallis.setOnClickListener(this);
        btn_clr.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_eq.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String str=et_input.getText().toString();
        if (v.getId() == R.id.btn_00 || v.getId() == R.id.btn_0 || v.getId() == R.id.btn_1 || v.getId() == R.id.btn_2 || v.getId() == R.id.btn_3 || v.getId() == R.id.btn_4 ||
                v.getId() == R.id.btn_5 || v.getId() == R.id.btn_6 || v.getId() == R.id.btn_7 || v.getId() == R.id.btn_8 || v.getId() == R.id.btn_9) {
            if(clr_flag){
                clr_flag=false;
                str="";
                et_input.setText("");
            }
            et_input.setText(str+((Button)v).getText());
        } else if (v.getId() == R.id.btn_leibniz) {
            formula_type = 0;
            et_input.setText(str);
        } else if (v.getId() == R.id.btn_ramanujan) {
            formula_type = 1;
            et_input.setText(str);
        } else if (v.getId() == R.id.btn_chudnovsky) {
            formula_type = 2;
            et_input.setText(str);
        } else if (v.getId() == R.id.btn_wallis) {
            formula_type = 3;
            et_input.setText(str);
        } else if (v.getId() == R.id.btn_clr) {
            if(clr_flag)
                clr_flag=false;
            str="";
            formula_type = 0; // 重置计算公式
            et_input.setText("");
        } else if (v.getId() == R.id.btn_del) {
            if(clr_flag){
                clr_flag=false;
                str="";
                et_input.setText("");
            }
            else if(str!=null&&!str.equals("")){
                et_input.setText(str.substring(0,str.length()-1));
            }
        } else if (v.getId() == R.id.btn_eq) {
            getResult();//调用下面的方法
        }
    }
    private void getResult() {
        long starttime = System.nanoTime();
        String exp=et_input.getText().toString();
        String computing_time = "";
        if(exp==null||exp.equals("")) return ;
        if(clr_flag){
            clr_flag=false;
            return;
        }
        clr_flag=true;
        double myPi = 0;
        int ACCURACY = Integer.parseInt(exp); // 考虑精度，位数增加k，结果减少k位
        if (formula_type == 0) { // Leibniz
            computing_time = "Leibniz: ";
        } else if (formula_type == 1) { // Ramanujan
            computing_time = "Ramanujan: ";
        } else if (formula_type == 2) { // Chudnovsky
            computing_time = "Chudnovsky: ";
        } else if (formula_type == 3) { // Wallis
            computing_time = "Wallis: ";
        }
        Random numList = new Random(); // 随机选择使用哪种语言计算
        int languageType = numList.nextInt(2);
        if (languageType == 0)
            myPi = javaGetPi(formula_type, ACCURACY);
        else
            myPi = getPi(formula_type, ACCURACY);
        et_input.setText(myPi + "");

        long endtime = System.nanoTime();
        et_computing_time.setText(computing_time + (endtime - starttime) + " (ns)" + "  " + "Iterations: " + ACCURACY + " (times)");
    }
    public  native double getPi(int formula_type, int accuracy);
    public double javaGetPi(int formula_type, int ACCURACY) {
        double myPi = 0;
        if (formula_type == 0) { // Leibniz
            int pn_value = 1;
            for (int i = 0; i < ACCURACY; i++) {
                myPi += pn_value * (1 / (1.0 * 2 * i + 1));
                pn_value *= -1;
            }
            myPi = 4 * myPi;
        } else if (formula_type == 1) { // Ramanujan
            for (int i = 0; i < ACCURACY; i++) {
                myPi += calculateFactorial(4 * i) * (26390 * i + 1103) / (Math.pow(calculateFactorial(i), 4) * Math.pow(396, 4 * i));
            }
            myPi = 99 * 99 / (2 * Math.sqrt(2) * myPi);
        } else if (formula_type == 2) { // Chudnovsky
            int pn_value = 1;
            for (int i = 0; i < ACCURACY; i++) {
                myPi += pn_value * calculateFactorial(6 * i) * (13591409 + 545140134 * i) / (Math.pow(calculateFactorial(i), 3) * calculateFactorial(3 * i) * Math.pow(640320, 3 * i));
                pn_value *= -1;
            }
            myPi = 53360 * Math.sqrt(640320) / myPi;
        } else if (formula_type == 3) { // Wallis
            myPi = 1;
            int numerator = 2;
            int denominator = 1;
            for (int i = 0; i < ACCURACY; i++) {
                myPi *= numerator / (1.0 * denominator);
                int j = numerator;
                numerator = denominator + 1;
                denominator = j + 1;
            }
            myPi *= 2;
        }
        return myPi;
    }
    public static long calculateFactorial(int n) {
        long factorial = 1;
        for (int i = 1; i <= n; i++)
            factorial *= i;
        return factorial;
    }
}