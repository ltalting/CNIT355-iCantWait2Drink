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
import java.util.Calendar;
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
                int mins = getMins(wait);
                int total = (mins * 60) + seconds;
                String day = getTime();
                getDataForBar(barName, day, total);
                String data = day + "  Bar: " + barName + "  Wait: Mins " + mins + " Seconds " + seconds;
//                Toast.makeText(TimerActivity.this, data, Toast.LENGTH_SHORT).show();
                finish();
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

    public int getMins(String data) {
        String[] nums = data.split(":");
        String hours = nums[0];
        int hr = Integer.parseInt(hours);
        return hr;
    }

    public void getDataForBar(final String barName, final String dataTime, final int seconds) {
        if (barisOpen(barName)) {
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
                        int entries = 1;
                        try {
                            entries = Integer.parseInt(document.getData().get("entries " + dataTime).toString());
                        } catch (Exception e) {
                            createEntry(barName, dataTime);
                        }
                        int newAverage = currentTime + ((seconds - currentTime) / entries);
                        Map<String, Object> data = new HashMap<>();
                        data.put(dataTime, newAverage);

                        db.collection("bars").document(barName)
                                .set(data, SetOptions.merge());
                        // update entries
                        Map<String, Object> data2 = new HashMap<>();
                        entries = entries + 1;
                        data2.put("entries " + dataTime, entries);

                        db.collection("bars").document(barName)
                                .set(data2, SetOptions.merge());
                    }
                }
            }
        });
    }

    public void createEntry(String barName, String dataTime) {
        Map<String, Object> data = new HashMap<>();
        data.put("entries " + dataTime, 1);

        db.collection("bars").document(barName)
                .set(data, SetOptions.merge());
    }

    public void postNewData(String barName, String dataTime, int seconds) {
        Map<String, Object> data = new HashMap<>();
        data.put(dataTime, seconds);

        db.collection("bars").document(barName)
                .set(data, SetOptions.merge());
    }

    public boolean barisOpen(String barName) {

        Calendar calendar = Calendar.getInstance();
        // Get current day.
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        // Get current hour.
        Date time = calendar.getTime();
        DateFormat hourFormat = new SimpleDateFormat("HH");
        String currentHour = hourFormat.format(time);
        int intHour = Integer.parseInt(currentHour);
        switch (day) {
            // Current day is Monday.
            case Calendar.MONDAY:
                // Set Bobby T's Open/Close.
                if (0 <= intHour && intHour < 3){
                    if (barName.equals("bobbyTs")) {
                        return true;
                    }
                }
                else if (17 <= intHour && intHour < 24){
                    if (barName.equals("bobbyTs")) {
                        return true;
                    }
                }
                else{
                    if (barName.equals("bobbyTs")) {
                        return false;
                    }
                }
                // Set Brother's Open/Close.
                if (0 <= intHour && intHour < 3){
                    if (barName.equals("brothers")) {
                        return true;
                    }
                }
                else if (11 <= intHour && intHour < 24){
                    if (barName.equals("brothers")) {
                        return true;
                    }
                }
                else{
                    if (barName.equals("brothers")) {
                        return false;
                    }
                }
                // Set Cactus Open/Close.
                if (barName.equals("cactus")) {
                    return false;
                }
                // Set Harry's Open/Close.
                if (0 <= intHour && intHour < 3){
                    if (barName.equals("harrys")) {
                        return true;
                    }
                }
                else if (11 <= intHour && intHour < 24){
                    if (barName.equals("harrys")) {
                        return true;
                    }
                }
                else{
                    if (barName.equals("harrys")) {
                        return false;
                    }
                }
                // Set Where else Open/Close.
                if (barName.equals("whereElse")) {
                    return false;
                }
                break;
            case Calendar.TUESDAY:
                // Current day is Tuesday.
                // Set Bobby T's Open/Close.
                if (0 <= intHour && intHour < 3){
                    if (barName.equals("bobbyTs")) {
                        return true;
                    }
                }
                else if (17 <= intHour && intHour < 24){
                    if (barName.equals("bobbyTs")) {
                        return true;
                    }
                }
                else{
                    if (barName.equals("bobbyTs")) {
                        return false;
                    }
                }
                // Set Brother's Open/Close.
                if (0 <= intHour && intHour < 3){
                    if (barName.equals("brothers")) {
                        return true;
                    }
                }
                else if (11 <= intHour && intHour < 24){
                    if (barName.equals("brothers")) {
                        return true;
                    }
                }
                else{
                    if (barName.equals("brothers")) {
                        return false;
                    }
                }
                // Set Cactus Open/Close.
                if (barName.equals("cactus")) {
                    return false;
                }
                // Set Harry's Open/Close.
                if (0 <= intHour && intHour < 3){
                    if (barName.equals("harrys")) {
                        return true;
                    }
                }
                else if (11 <= intHour && intHour < 24){
                    if (barName.equals("harrys")) {
                        return true;
                    }
                }
                else{
                    if (barName.equals("harrys")) {
                        return false;
                    }
                }
                // Set Where else Open/Close.
                if (barName.equals("whereElse")) {
                    return false;
                }
                break;
            case Calendar.WEDNESDAY:
                // Current day is Wednesday.
                // Set Bobby T's Open/Close.
                if (0 <= intHour && intHour < 3){
                    if (barName.equals("bobbyTs")) {
                        return true;
                    }
                }
                else if (17 <= intHour && intHour < 24){
                    if (barName.equals("bobbyTs")) {
                        return true;
                    }
                }
                else{
                    if (barName.equals("bobbyTs")) {
                        return false;
                    }
                }
                // Set Brother's Open/Close.
                if (0 <= intHour && intHour < 3){
                    if (barName.equals("brothers")) {
                        return true;
                    }
                }
                else if (11 <= intHour && intHour < 24){
                    if (barName.equals("brothers")) {
                        return true;
                    }
                }
                else{
                    if (barName.equals("brothers")) {
                        return false;
                    }
                }
                // Set Cactus Open/Close.
                if (barName.equals("cactus")) {
                        return false;
                    }
                // Set Harry's Open/Close.
                if (0 <= intHour && intHour < 3){
                    if (barName.equals("harrys")) {
                        return true;
                    }
                }
                else if (11 <= intHour && intHour < 24){
                    if (barName.equals("harrys")) {
                        return true;
                    }
                }
                else{
                    if (barName.equals("harrys")) {
                        return false;
                    }
                }
                // Set Where else Open/Close.
                if (12 <= intHour && intHour < 24){
                    if (barName.equals("whereElse")) {
                        return true;
                    }
                }
                else{
                    if (barName.equals("whereElse")) {
                        return false;
                    }
                }
                break;
            case Calendar.THURSDAY:
                // Current day is Thursday.
                // Set Bobby T's Open/Close.
                if (0 <= intHour && intHour < 3){
                    if (barName.equals("bobbyTs")) {
                        return true;
                    }
                }
                else if (17 <= intHour && intHour < 24){
                    if (barName.equals("bobbyTs")) {
                        return true;
                    }
                }
                else{
                    if (barName.equals("bobbyTs")) {
                        return false;
                    }
                }
                // Set Brother's Open/Close.
                if (0 <= intHour && intHour < 3){
                    if (barName.equals("brothers")) {
                        return true;
                    }
                }
                else if (11 <= intHour && intHour < 24){
                    if (barName.equals("brothers")) {
                        return true;
                    }
                }
                else{
                    if (barName.equals("brothers")) {
                        return false;
                    }
                }
                // Set Cactus Open/Close.
                if (20 <= intHour && intHour < 24){
                    if (barName.equals("cactus")) {
                        return true;
                    }
                }
                else{
                    if (barName.equals("cactus")) {
                        return false;
                    }
                }
                // Set Harry's Open/Close.
                if (0 <= intHour && intHour < 3){
                    if (barName.equals("harrys")) {
                        return true;
                    }
                }
                else if (11 <= intHour && intHour < 24){
                    if (barName.equals("harrys")) {
                        return true;
                    }
                }
                else{
                    if (barName.equals("harrys")) {
                        return false;
                    }
                }
                // Set Where else Open/Close.
                if (0 <= intHour && intHour < 3){
                    if (barName.equals("whereElse")) {
                        return true;
                    }
                }
                else if (12 <= intHour && intHour < 24){
                    if (barName.equals("whereElse")) {
                        return true;
                    }
                }
                else{
                    if (barName.equals("whereElse")) {
                        return false;
                    }
                }
            case Calendar.FRIDAY:
                // Current day is Friday.
                // Set Bobby T's Open/Close.
                if (0 <= intHour && intHour < 3){
                    if (barName.equals("bobbyTs")) {
                        return true;
                    }
                }
                else if (17 <= intHour && intHour < 24){
                    if (barName.equals("bobbyTs")) {
                        return true;
                    }
                }
                else{
                    if (barName.equals("bobbyTs")) {
                        return false;
                    }
                }
                // Set Brother's Open/Close.
                if (0 <= intHour && intHour < 3){
                    if (barName.equals("brothers")) {
                        return true;
                    }
                }
                else if (11 <= intHour && intHour < 24){
                    if (barName.equals("brothers")) {
                        return true;
                    }
                }
                else{
                    if (barName.equals("brothers")) {
                        return false;
                    }
                }
                // Set Cactus Open/Close.
                if (0 <= intHour && intHour <= 3){
                    if (barName.equals("cactus")) {
                        return true;
                    }
                }
                else if (20 <= intHour && intHour < 24){
                    if (barName.equals("cactus")) {
                        return true;
                    }
                }
                else{
                    if (barName.equals("cactus")) {
                        return false;
                    }
                }
                // Set Harry's Open/Close.
                if (0 <= intHour && intHour < 3){
                    if (barName.equals("harrys")) {
                        return true;
                    }
                }
                else if (11 <= intHour && intHour < 24){
                    if (barName.equals("harrys")) {
                        return true;
                    }
                }
                else{
                    if (barName.equals("harrys")) {
                        return false;
                    }
                }
                // Set Where else Open/Close.
                if (0 <= intHour && intHour < 3){
                    if (barName.equals("whereElse")) {
                        return true;
                    }
                }
                else if (12 <= intHour && intHour < 24){
                    if (barName.equals("whereElse")) {
                        return true;
                    }
                }
                else{
                    if (barName.equals("whereElse")) {
                        return false;
                    }
                }
                break;
            case Calendar.SATURDAY:
                // Current day is Saturday.
                // Set Bobby T's Open/Close.
                if (0 <= intHour && intHour < 3){
                    if (barName.equals("bobbyTs")) {
                        return true;
                    }
                }
                else if (17 <= intHour && intHour < 24){
                    if (barName.equals("bobbyTs")) {
                        return true;
                    }
                }
                else{
                    if (barName.equals("bobbyTs")) {
                        return false;
                    }
                }
                // Set Brother's Open/Close.
                if (0 <= intHour && intHour < 3){
                    if (barName.equals("brothers")) {
                        return true;
                    }
                }
                else if (11 <= intHour && intHour < 24){
                    if (barName.equals("brothers")) {
                        return true;
                    }
                }
                else{
                    if (barName.equals("brothers")) {
                        return false;
                    }
                }
                // Set Cactus Open/Close.
                if (0 <= intHour && intHour <= 3){
                    if (barName.equals("cactus")) {
                        return true;
                    }
                }
                else if (20 <= intHour && intHour < 24){
                    if (barName.equals("cactus")) {
                        return true;
                    }
                }
                else{
                    if (barName.equals("cactus")) {
                        return false;
                    }
                }
                // Set Harry's Open/Close.
                if (0 <= intHour && intHour < 3){
                    if (barName.equals("harrys")) {
                        return true;
                    }
                }
                else if (11 <= intHour && intHour < 24){
                    if (barName.equals("harrys")) {
                        return true;
                    }
                }
                else{
                    if (barName.equals("harrys")) {
                        return false;
                    }
                }
                // Set Where else Open/Close.
                if (0 <= intHour && intHour < 3){
                    if (barName.equals("whereElse")) {
                        return true;
                    }
                }
                else if (12 <= intHour && intHour < 24){
                    if (barName.equals("whereElse")) {
                        return true;
                    }
                }
                else{
                    if (barName.equals("whereElse")) {
                        return false;
                    }
                }
                break;
            case Calendar.SUNDAY:
                // Current day is Sunday.
                // Set Bobby T's Open/Close.
                if (0 <= intHour && intHour < 3){
                    if (barName.equals("bobbyTs")) {
                        return true;
                    }
                }
                else if (19 <= intHour && intHour < 24){
                    if (barName.equals("bobbyTs")) {
                        return true;
                    }
                }
                else{
                    if (barName.equals("bobbyTs")) {
                        return false;
                    }
                }
                // Set Brother's Open/Close.
                if (0 <= intHour && intHour < 3){
                    if (barName.equals("brothers")) {
                        return true;
                    }
                }
                else if (7 <= intHour && intHour < 24){
                    if (barName.equals("brothers")) {
                        return true;
                    }
                }
                else{
                    if (barName.equals("brothers")) {
                        return false;
                    }
                }
                // Set Cactus Open/Close.
                if (barName.equals("cactus")) {
                        return false;
                    }
                // Set Harry's Open/Close.
                if (0 <= intHour && intHour < 3){
                    if (barName.equals("harrys")) {
                        return true;
                    }
                }
                else if (12 <= intHour && intHour < 24){
                    if (barName.equals("harrys")) {
                        return true;
                    }
                }
                else{
                    if (barName.equals("harrys")) {
                        return false;
                    }
                }
                // Set Where else Open/Close.
                if (0 <= intHour && intHour < 3){
                    if (barName.equals("whereElse")) {
                        return true;
                    }
                }
                else{
                    if (barName.equals("whereElse")) {
                        return false;
                    }
                }
                break;
        }
        System.out.println(">>>>> Not open");
        return false;
    }
}
