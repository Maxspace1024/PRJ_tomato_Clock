package com.example.tomato_clock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView iv1;
    TextView tv1;
    EditText et1;
    Button bt1;
    long init_sec=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv1 = (ImageView) findViewById(R.id.ivTomato);
        tv1 = (TextView) findViewById(R.id.tvTime);
        et1 = (EditText) findViewById(R.id.etMins);
        bt1 = (Button) findViewById(R.id.btSet);

        tv1.setText(String.format("%02d:%02d",init_sec/60,init_sec%60));

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init_sec = Long.valueOf(et1.getText().toString())*60;
                tv1.setText(String.format("%02d:%02d",init_sec/60,init_sec%60));
                tv1.setTextColor(Color.parseColor("#404040"));

                Toast t = Toast.makeText(       getApplicationContext()                             //cannot use "this"
                                                ,String.format("set %d mins",init_sec/60)
                                                ,Toast.LENGTH_SHORT);

                //View toastView = t.getView();                                                       //make View object
                //toastView.setBackgroundColor(Color.parseColor("#000000"));

                t.show();                                                                           //remember to show


            }
        });

        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv1.setText(String.format("%02d:%02d",init_sec/60,init_sec%60));
                tv1.setTextColor(Color.parseColor("#404040"));

                CountDownTimer cdt = new CountDownTimer(init_sec*1000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        long s = millisUntilFinished/1000;
                        Log.i("time",String.format("%d",s));
                        tv1.setText(String.format("%02d:%02d",s/60,s%60));
                    }

                    @Override
                    public void onFinish() {
                        tv1.setText("Times up!!");
                        tv1.setTextColor(Color.parseColor("#ff0000"));
                    }
                };

                cdt.start();                                                                        //need to launch
            }
        });

    }
}