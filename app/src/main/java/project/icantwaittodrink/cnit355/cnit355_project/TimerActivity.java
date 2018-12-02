package project.icantwaittodrink.cnit355.cnit355_project;

import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TimerActivity extends AppCompatActivity {

    Button btnLeave, btnIn;
    TextView txtTime;
    Handler customHandler = new Handler();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

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

        Intent intent = getIntent();
        final String barName = intent.getStringExtra("bar");

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
                String wait = txtTime.getText().toString();
                int seconds = getSeconds(wait);
                int hours = getHours(wait);
                int total = (hours * 60) + seconds;
                String[] day = getTime().split(" ");
                String dayOfWeek = day[0];
                String time = day[1];
                WaitTimeData newData = new WaitTimeData();
                newData.setDayOfWeek(dayOfWeek);
                newData.setTimeOfDay(time);
//                getDataForBar(barName, time, total);
                String data = day + "  Bar: " + barName + "  Wait: Hours " + hours + " Seconds " + seconds;
                Toast.makeText(TimerActivity.this, data, Toast.LENGTH_SHORT).show();
            }
        });

        btnLeave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                timeSwapBuff+=timeInMilliseconds;
                customHandler.removeCallbacks(updateTimerThread);
                finish();
            }
        });

    }

    public String getTime() {
        Date date = new Date();
        DateFormat formatter = new SimpleDateFormat("EEE HH:mm");
        String dateStr = formatter.format(date);
        String[] dateSplit = dateStr.split(":| ");
        String dayOfWeek = dateSplit[0];
        int hourOfDay = Integer.parseInt(dateSplit[1]);
        int minOfDay = Integer.parseInt(dateSplit[2]);
        if (minOfDay >= 30) {
            hourOfDay++;
        }
        return dayOfWeek + " " + hourOfDay + ":00";
    }

    public int getSeconds(String data) {
        String[] nums = data.split(":");
        String seconds = nums[1];
        int sec = Integer.parseInt(seconds);
        return sec;
    }

    public int getHours(String data) {
        String[] nums = data.split(":");
        String hours = nums[0];
        int hr = Integer.parseInt(hours);
        return hr;
    }

    public void getDataForBar(final String barName, final String dataTime, final int seconds) {
        DocumentReference docRef = db.collection("bars").document(barName);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        System.out.println(">>>>>> " + document.getData().get(dataTime));
                        if (document.getData().get(dataTime) == null) {
                            postNewData(barName, dataTime, seconds);
                        } else {
                            updateData(barName, dataTime, seconds, document.getData().get(dataTime));
                        }
                    }
                }
            }
        });
    }

    public void updateData(final String barName, final String dataTime, final int seconds, Object currentData) {
        final int currentTime = Integer.parseInt(currentData.toString());
        DocumentReference docRef = db.collection("bars").document(barName);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        int entries = Integer.parseInt(document.getData().get("entries").toString());
                        int newAverage = currentTime + ((seconds - currentTime) / entries);
                        Map<String, Object> data = new HashMap<>();
                        data.put(dataTime, newAverage);

                        db.collection("bars").document(barName)
                                .set(data, SetOptions.merge());
                        // update entries
                        Map<String, Object> data2 = new HashMap<>();
                        entries = entries + 1;
                        data2.put("entries", entries);

                        db.collection("bars").document(barName)
                                .set(data2, SetOptions.merge());
                    }
                }
            }
        });
    }

    public void postNewData(String barName, String dataTime, int seconds) {
        Map<String, Object> data = new HashMap<>();
        data.put(dataTime, seconds);

        db.collection("bars").document(barName)
                .set(data, SetOptions.merge());
    }
}
