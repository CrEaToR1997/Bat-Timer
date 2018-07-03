package com.example.acer.advance;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    Button button;
    TextView textView;

    CountDownTimer countDownTimer;

    boolean counterIsActive = false;

    int max = 600;
    int time ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        seekBar.setMax(max);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                time = progress*1000;
                if(progress < 60){
                    int y = progress;
                    if(y<10){
                        textView.setText("00:0"+y);
                    }else{
                        textView.setText("00:"+y);
                    }
                }else {
                    int x = Math.abs(progress / 60);
                    int y = progress % 60;
                    if (y < 10) {
                        textView.setText(x + ":0" + y);
                    } else {
                        textView.setText(x + ":" + y);
                    }

                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void StartTimer(View view) {
        seekBar.setVisibility(View.INVISIBLE);
        button.setText("Stop!");
        if(counterIsActive){
            seekBar.setVisibility(View.VISIBLE);
            button.setText("Go!");
            countDownTimer.cancel();
            counterIsActive = false;
        }
        else {
            counterIsActive = true;
            countDownTimer = new CountDownTimer(time, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    Log.i("Timer run", "Timer has started");

                    if (millisUntilFinished / 1000 < 60) {
                        int y = (int) millisUntilFinished / 1000;
                        if (y < 10) {
                            textView.setText("00:0" + y);
                            y--;
                        } else {
                            textView.setText("00:" + y);
                            y--;
                        }
                    } else {
                        String secString;
                        int min = (int) (millisUntilFinished / 1000) / 60;
                        int sec = (int) (millisUntilFinished / 1000) - (min * 60);
                        if (sec < 10) {
                            secString = "0" + Integer.toString(sec);
                        } else {
                            secString = Integer.toString(sec);
                        }
                        if (secString == "0") {
                            secString = "00";
                        }
                        if (min == 10) {
                            textView.setText(Integer.toString(min) + ":" + secString);
                        } else {
                            textView.setText("0" + Integer.toString(min) + ":" + secString);
                        }

                    }
                }

                @Override
                public void onFinish() {
                    Toast.makeText(MainActivity.this, "Timer has completed", Toast.LENGTH_SHORT).show();
                    button.setVisibility(View.VISIBLE);
                    seekBar.setVisibility(View.VISIBLE);
                }
            };
            countDownTimer.start();
        }
    }
}
