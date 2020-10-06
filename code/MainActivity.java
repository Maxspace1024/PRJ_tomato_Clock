package com.example.tomato_clock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
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
    Vibrator vibrator,vibSignal;

    ObjectAnimator sx,sy;
    AnimatorSet animSet;

    int tomatoFlag=0;
    long animDuration=200;
    long init_time=10;


    long c=225;
    long cE=c/3;
    long pattern[] = {0,c,c,c,c,c,c*3};                                                             //[rest , vibrate,...]
    long patternErr[] = {0,cE,cE,cE,cE};
    long patternToma[] ={0,cE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv1 = (ImageView) findViewById(R.id.ivTomato);
        tv1 = (TextView) findViewById(R.id.tvTime);
        et1 = (EditText) findViewById(R.id.etMins);
        bt1 = (Button) findViewById(R.id.btSet);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibSignal= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        tv1.setText(String.format("%02d:%02d",init_time/60,init_time%60));

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = et1.getText().toString();

                if(str.equals("")){
                    Toast.makeText(getApplicationContext(),"mins can not be empty",Toast.LENGTH_SHORT).show();
                    vibSignal.vibrate(patternErr,-1);
                }
                else{
                    long mins = Long.valueOf(str);
                    if(20<=mins && mins<=30){
                        tv1.setText(String.format("%02d:%02d",mins,mins%1));
                        tv1.setTextColor(Color.parseColor("#404040"));

                        Toast.makeText(getApplicationContext(),String.format("set %d mins",mins),Toast.LENGTH_SHORT).show();
                        init_time=mins*60;
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"mins is between 20 and 30",Toast.LENGTH_SHORT).show();
                        vibSignal.vibrate(patternErr,-1);
                    }
                }
            }
        });

        iv1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    vibSignal.vibrate(patternToma,-1);
                    sx = ObjectAnimator.ofFloat(iv1,"scaleX",1.1f);
                    sy = ObjectAnimator.ofFloat(iv1,"scaleY",1.1f);
                }
                else if(event.getAction() == MotionEvent.ACTION_UP){
                    sx = ObjectAnimator.ofFloat(iv1,"scaleX",1.1f,0.5f,1.0f);
                    sy = ObjectAnimator.ofFloat(iv1,"scaleY",1.1f,0.5f,1.0f);
                }

                sx.setDuration(animDuration);
                sy.setDuration(animDuration);

                animSet = new AnimatorSet();
                animSet.playTogether(sx,sy);
                animSet.start();

                return false;
            }
        });

        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tomatoFlag==0) {                                                                 //0 -- time init
                    tv1.setText(String.format("%02d:%02d",init_time/60,init_time%60));
                    tv1.setTextColor(Color.parseColor("#404040"));

                    bt1.setEnabled(true);                                                           //Enable btn
                    vibrator.cancel();                                                              //stop vibrator

                    cdt = new CountDownTimer(init_time*1000,1000) {
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