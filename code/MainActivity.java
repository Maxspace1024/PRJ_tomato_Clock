package com.example.tomato_clock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
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

    CountDownTimer cdt;
    Vibrator vibrator;

    int tomatoFlag=0;
    long init_sec=10;
    long c=225;
    long pattern[] = {0,c,c,c,c,c,c*3};                                                             //[rest , vibrate,...]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv1 = (ImageView) findViewById(R.id.ivTomato);
        tv1 = (TextView) findViewById(R.id.tvTime);
        et1 = (EditText) findViewById(R.id.etMins);
        bt1 = (Button) findViewById(R.id.btSet);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        tv1.setText(String.format("%02d:%02d",init_sec/60,init_sec%60));

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = et1.getText().toString();

                if(str.equals("")){
                    Toast.makeText(getApplicationContext(),"mins can not be empty",Toast.LENGTH_SHORT).show();
                }
                else{
                    init_sec = Long.valueOf(str)*60;
                    tv1.setText(String.format("%02d:%02d",init_sec/60,init_sec%60));
                    tv1.setTextColor(Color.parseColor("#404040"));

                    Toast.makeText(getApplicationContext(),String.format("set %d mins",init_sec/60),Toast.LENGTH_SHORT).show();
                }
            }
        });


        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tomatoFlag==0) {                                                                 //0 -- time init
                    tv1.setText(String.format("%02d:%02d",init_sec/60,init_sec%60));
                    tv1.setTextColor(Color.parseColor("#404040"));

                    bt1.setEnabled(true);                                                           //Enable btn
                    vibrator.cancel();                                                              //stop vibrator

                    cdt = new CountDownTimer(init_sec*1000,1000) {
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
                            vibrator.vibrate(pattern,0);                                     //launch vibrator

                            tomatoFlag++;                                                           //skip cancel
                            tomatoFlag%=3;
                        }
                    };
                    Toast.makeText(getApplicationContext(),"init timer",Toast.LENGTH_SHORT).show();
                }
                else if(tomatoFlag==1){                                                             //1 -- timer on
                    cdt.start();                                                                    //launch timer
                    bt1.setEnabled(false);                                                          //disable btn

                    Toast.makeText(getApplicationContext(),"launch",Toast.LENGTH_SHORT).show();
                }
                else if(tomatoFlag==2){                                                             //2 -- timer off
                    cdt.cancel();                                                                   //stop timer

                    Toast.makeText(getApplicationContext(),"cancel",Toast.LENGTH_SHORT).show();
                }

                tomatoFlag++;
                tomatoFlag%=3;
            }
        });

    }
}