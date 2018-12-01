package project.icantwaittodrink.cnit355.cnit355_project;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TimerActivity extends AppCompatActivity {

    Button btnLeave, btnIn;
    TextView txtTime;
    Handler customHandler = new Handler();

    long startTime = 0L, timeInMilliseconds = 0L, timeSwapBuff = 0L, updateTime=0L;

    Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis()-startTime;
            updateTime = timeSwapBuff+timeInMilliseconds;
            int secs = (int)(updateTime/1000);
            int mins = secs/60;
            secs%=60;
            int milliseconds = (int)(updateTime);
            txtTime.setText(""+String.format("%02d", mins)+":"+String.format("%02d", secs));
            customHandler.postDelayed(this, 0);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        btnLeave = findViewById(R.id.btnTimerILeft);
        btnIn = findViewById(R.id.btnImIn);
        txtTime = findViewById(R.id.timerClock);


        startTime = SystemClock.uptimeMillis();
        customHandler.postDelayed(updateTimerThread, 0);
        //timeSwapBuff = timeInMilliseconds;
        //customHandler.removeCallbacks(updateTimerThread);

        btnIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeSwapBuff+=timeInMilliseconds;
                customHandler.removeCallbacks(updateTimerThread);
            }
        });

        btnLeave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                timeSwapBuff+=timeInMilliseconds;
                customHandler.removeCallbacks(updateTimerThread);
            }
        });

    }
}
