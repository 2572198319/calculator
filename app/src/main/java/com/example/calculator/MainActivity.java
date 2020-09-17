package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PermissionGroupInfo;
import android.icu.util.VersionInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.math.BigDecimal;
import java.net.CacheRequest;
import java.sql.BatchUpdateException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private GridLayout mGridLauout;
    private TextView text,answer;
    private int colCount,rowCount;
    private int screenWidthDp,screenHeightDp,ScreenWidthPx,ScreenHeightPx,flag=0;
    float density;
    int fontMaxCount1=11;
    float fontSize1=60;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();//去掉标题栏
        mGridLauout = (GridLayout) findViewById(R.id.GridLayout1);
        colCount = mGridLauout.getColumnCount();//按钮列数
        rowCount = mGridLauout.getRowCount();//按钮行数
        text=(TextView)findViewById(R.id.input_number);
        answer=(TextView)findViewById(R.id.out_number);
        text.setHorizontallyScrolling(true);//使text可以滑动
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        ScreenWidthPx = dm.widthPixels;// 屏幕宽度（像素）
        ScreenHeightPx= dm.heightPixels; // 屏幕高度（像素）
        density = dm.density;//屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = dm.densityDpi;//屏幕密度dpi（120 / 160 / 240）
        //屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        screenWidthDp = (int) (ScreenWidthPx/density);//屏幕宽度(dp)
        screenHeightDp = (int)(ScreenHeightPx/density);//屏幕高度(dp)
        //设置按钮在下半屏幕内均匀分布
        for (int i = 0; i < mGridLauout.getChildCount(); i++) {
            Button button = (Button) mGridLauout.getChildAt(i);
            Log.e(TAG, "column:" + colCount + ";  screenwidth:" + ScreenWidthPx);
            button.setWidth(ScreenWidthPx / colCount);
            if(screenWidthDp<screenHeightDp){
                flag=1;
                button.setHeight(ScreenHeightPx/ 2 / rowCount);
            }//竖屏时按钮部分占屏幕的二分是一
            else{
                button.setHeight(ScreenHeightPx/rowCount*2/3);
            }//横屏时按钮部分占屏幕的三分之二
            button.setOnClickListener(this);
        }//为网格布局中的每个按钮注册监听器并动态设置大小
        if(savedInstanceState!=null){
            String s=savedInstanceState.getString("KEY");
            String s1=savedInstanceState.getString("ANSWER");
            text.setText(s);
            answer.setText(s1);
        }//恢复text和answer的数据
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("KEY",text.getText().toString());
        outState.putString("ANSWER",answer.getText().toString());
    }//在旋转屏幕时保存text和answer中的数据
    @Override
    public void onClick(View view) {
        answer=(TextView)findViewById(R.id.out_number);
        String input=text.getText().toString();
        Button button=(Button)findViewById(view.getId());
        String btn=button.getText().toString();
        if(btn.charAt(0)>='0'&&btn.charAt(0)<='9'){
            if(input.equals("0")||input.matches("^.*[+−×÷]+0$")==true){
                text.setText(input.substring(0,input.length()-1)+btn);
            }//如果遇到以0打头的数字则先去掉0
            else if(!input.matches("^.*[%)]$")){
                text.setText(input+btn);
            }//如果text中为合法的式子则在末尾追加数字
            answer.setText(Calculator.Calculate(text.getText().toString()));//每次输入数字之后更新一次计算结果
        }//数字按钮
        else if(btn.matches("[+−×÷]+")&&!input.equals("")){
            if(input.matches("^.*[+−×÷.]+$")){
                text.setText(input.substring(0,text.length()-1)+btn);
            }//如果以运算符结尾,则改变运算符
            else{
                text.setText(input+btn);
            }//如果不以运算符结尾则追加运算符
        }//普通运算符
        else if(btn.equals("C")){
            text.setText("");
            answer.setText("");
            fontMaxCount1=11;
            fontSize1=60;//字符大小初始化
            if(flag==1)
                text.setTextSize(TypedValue.COMPLEX_UNIT_SP,fontSize1);
        }//清0按钮
        else if(!input.equals("")&&btn.equals("CE")){
            String str=input.substring(0,input.length()-1);
            if(str.equals("")){
                answer.setText("");
            }
            else if(str.charAt(str.length()-1)>='0'&&str.charAt(str.length()-1)<='9'){
                answer.setText(Calculator.Calculate(str));
            }//每回退一次计算结果更新一次
            text.setText(str);
            if(flag==1&&fontMaxCount1>11){
                fontMaxCount1-=1;
            } else if(flag==1){
                fontMaxCount1=11;
                fontSize1=60;
                text.setTextSize(TypedValue.COMPLEX_UNIT_SP,fontSize1);
            }//根据字符串长度动态调整字体大小
        }//退格按钮
        else if(btn.equals("%")){
            if(input.matches("^.*[0-9%]+$")){
                text.setText(input+btn);
                answer.setText(Calculator.Calculate(text.getText().toString()));
            }//只有数字或百分号之后才可以接百分号
        }//百分号
        else if(btn.equals(".")){
            if(!input.matches("^.*[0-9][.]+[0-9]+$|^.*[+−×÷.%)]$")){
                text.setText(input+btn);
            }//如果当前text最后一个数字含有小数点或以预算符结尾则加上小数点
        }//小数点
        else if(!input.equals("")&&btn.equals("+/−")){
            if(input.matches("^.*\\(−[0-9.]+\\)$")){
                String s1=input.substring(0,input.lastIndexOf("(−"))+input.substring(input.lastIndexOf("(−")+2,input.length()-1);
                text.setText(s1);
                answer.setText(Calculator.Calculate(s1));
            }//复数变正数
            else if(input.charAt(input.length()-1)>='0'&&input.charAt(input.length()-1)<='9'){
                int i;
                for (i = input.length()-1; i >= 0; i--) {
                    if (!(input.charAt(i) == '.' || input.charAt(i) >= '0' && input.charAt(i) <= '9')) {
                        break;
                    }
                }
                int len=input.length();
                String s1=input.substring(0, i +1),s2=input.substring(i + 1,len );
                text.setText(s1+ "(−" + s2+ ")");
                answer.setText(Calculator.Calculate(text.getText().toString()));
            }//正数变复数
        }//正负号转换
        else if(btn.equals("=")){
            fontMaxCount1=10;
            fontSize1=60;
            if(flag==1)
                text.setTextSize(TypedValue.COMPLEX_UNIT_SP,fontSize1);
            if(!answer.getText().toString().equals("")){
                text.setText(answer.getText());
            }//answer的结果转到text显示
            answer.setText("");//answer清空
        }//等号
        else if(btn.equals("x²")){
            if(!btn.matches("^.*[+−×÷]$")){
                text.setText(input+"^2");
                answer.setText(Calculator.Calculate(input+"^2"));
            }
        }//平方
        else if(btn.equals("x³")){
            if(!btn.matches("^.*[+−×÷]$")){
                text.setText(input+"^3");
                answer.setText(Calculator.Calculate(input+"^3"));
            }
        }
        else if(btn.equals("yˣ")){
            if(!btn.matches("^.*[+−×÷]$")){
                text.setText(input+"^");
            }
        }
        else if(btn.equals("10ˣ")){
            if(!btn.matches("^.*[+−×÷]$")){
                System.out.println(input+"10^");
                text.setText(input+"10^");
            }
            else{
                System.out.println(input+"×10^");
                text.setText(input+"×10^");
            }
        }
        else if(btn.equals("√")){
            text.setText(input+"√");
        }//开平方
        else if(btn.equals("ˣ√y")){
            text.setText(input+btn);
        }
        else if(btn.equals("ln")||btn.equals("lg")){
            text.setText(input+btn+"(");
        }
        else if(btn.equals("Rad")){
            button.setText("Deg");
        }
        else if(btn.equals("Deg")){
            button.setText("Rad");
        }
        else if(btn.equals("x!")){
            if(!btn.matches("^.*[+−×÷]$")){
                text.setText(input+"!");
                answer.setText(Calculator.Calculate(input+"!"));
            }
        }//阶乘
        else if(btn.equals("EE")){
            text.setText(input+"E");
        }
        else if(btn.equals("eˣ")){
            text.setText(input+"e^");
        }
        else if(btn.equals("1/x")){
            text.setText(input+"^-1");
        }
        else if(btn.equals("(")){
            text.setText(input+btn);
        }
        else if(btn.equals(")")){
            input=input+btn;
            text.setText(input);
            answer.setText(Calculator.Calculate(input));//输入右括号之后也要更新answer
        }
        else if(btn.equals("sin")||btn.equals("cos")||btn.equals("tan")||btn.equals("sinh")||btn.equals("cosh")||btn.equals("tanh")){
            text.setText(input+btn+"(");
        }//三角函数运算
        else if(btn.equals("Rand")){
            input=input+btn;
            text.setText(input);
            answer.setText(Calculator.Calculate(input));
        }//随机数
        else if(btn.equals("π")){
            text.setText(input+Math.PI);
        }
        if(flag==1&&input.length()>fontMaxCount1){
            fontMaxCount1=fontMaxCount1+1;
            fontSize1=40;
            text.setTextSize(TypedValue.COMPLEX_UNIT_SP,fontSize1);//根据字符串长度动态调整字符大小
        }
    }//根据按钮的内容设置不同的响应函数
}