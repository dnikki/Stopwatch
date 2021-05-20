package com.example.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static int seconds=0;
    private  boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState !=null){
            seconds=savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("running");
        }
        onRun();


    }

    public void onSaveInstanceState(Bundle b) {
        super.onSaveInstanceState(b);
        b.putInt("seconds", seconds);
        b.putBoolean("running", running);
    }



    public void start_button(View view) {
        running=true;
    }

    public void stop_button(View view) {
        running=false;
    }

    public void reset_button(View view) {
        running=false;
        seconds=0;
    }

    public void onRun(){
        final TextView time=(TextView)findViewById(R.id.time_view);
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours=seconds/3600;
                int min=(seconds%3600)/60;
                int sec=seconds%60;
                String mytime=String.format("%d:%02d:%02d",hours,min,sec);
                time.setText(mytime);

                if(running){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }

        });
    }
}