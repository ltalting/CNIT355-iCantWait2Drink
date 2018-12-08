package project.icantwaittodrink.cnit355.cnit355_project;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CactusActivity extends AppCompatActivity {

    ImageView btnTimer, btnMaps, btnCall;
    String address, phoneNumber;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView currentMins, currentSeconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cactus);
        setTitle("iCantWait2Drink - Neon Cactus");

        //Initaialize current values
        currentMins = findViewById(R.id.lblCactusHours);
        currentSeconds = findViewById(R.id.lblCactusMins);

        //Initialize Intent Uses
        address = getString(R.string.cactusAddr);
        phoneNumber = getString(R.string.BrothersPhone);

        //Initialize Buttons
        btnTimer = findViewById(R.id.btnCactusTimer);
        btnMaps = findViewById(R.id.btnMapCactus);
        btnCall = findViewById(R.id.btnCallCactus);

        //Start Timer
        btnTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TimerActivity.class);
                intent.putExtra("bar","cactus");
                startActivity(intent);
            }
        });
        //Open Maps
        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + address));
                startActivity(intent);
            }
        });
        //Call Bar
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        System.out.println(">>>> onResume");
        String day = getTime().split(" ")[0];

        // update current time
        updateCurrentWaitTime(getTime());

        // update all the times
        getWaitTimes(day + " 0:00", (TextView) findViewById(R.id.lblCactus12AMWait));
        getWaitTimes(day + " 1:00", (TextView) findViewById(R.id.lblCactus1AMWait));
        getWaitTimes(day + " 2:00", (TextView) findViewById(R.id.lblCactus2AMWait));
        getWaitTimes(day + " 3:00", (TextView) findViewById(R.id.lblCactus3AMWait));
        getWaitTimes(day + " 4:00", (TextView) findViewById(R.id.lblCactus4AMWait));
        getWaitTimes(day + " 5:00", (TextView) findViewById(R.id.lblCactus5AMWait));
        getWaitTimes(day + " 6:00", (TextView) findViewById(R.id.lblCactus6AMWait));
        getWaitTimes(day + " 7:00", (TextView) findViewById(R.id.lblCactus7AMWait));
        getWaitTimes(day + " 8:00", (TextView) findViewById(R.id.lblCactus8AMWait));
        getWaitTimes(day + " 9:00", (TextView) findViewById(R.id.lblCactus9AMWait));
        getWaitTimes(day + " 10:00", (TextView) findViewById(R.id.lblCactus10AMWait));
        getWaitTimes(day + " 11:00", (TextView) findViewById(R.id.lblCactus11AMWait));
        getWaitTimes(day + " 12:00", (TextView) findViewById(R.id.lblCactus12PMWait));
        getWaitTimes(day + " 13:00", (TextView) findViewById(R.id.lblCactus1PMWait));
        getWaitTimes(day + " 14:00", (TextView) findViewById(R.id.lblCactus2PMWait));
        getWaitTimes(day + " 15:00", (TextView) findViewById(R.id.lblCactus3PMWait));
        getWaitTimes(day + " 16:00", (TextView) findViewById(R.id.lblCactus4PMWait));
        getWaitTimes(day + " 17:00", (TextView) findViewById(R.id.lblCactus5PMWait));
        getWaitTimes(day + " 18:00", (TextView) findViewById(R.id.lblCactus6PMWait));
        getWaitTimes(day + " 19:00", (TextView) findViewById(R.id.lblCactus7PMWait));
        getWaitTimes(day + " 20:00", (TextView) findViewById(R.id.lblCactus8PMWait));
        getWaitTimes(day + " 21:00", (TextView) findViewById(R.id.lblCactus9PMWait));
        getWaitTimes(day + " 22:00", (TextView) findViewById(R.id.lblCactus10PMWait));
        getWaitTimes(day + " 23:00", (TextView) findViewById(R.id.lblCactus11PMWait));
        super.onResume();
    }

    public void getWaitTimes(final String time, final TextView mTextView) {

        DocumentReference docRef = db.collection("bars").document("cactus");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        if (document.getData().get(time) == null) {
                            // no time...keep default time
                        } else {
                            // there is a time, update UI
                            int res = Integer.parseInt(document.getData().get(time).toString());

                            int mins = res / 60;
                            int seconds = res - (mins * 60);
                            mTextView.setText(mins + " mins " + seconds + " sec");
                        }
                    }
                }
            }
        });
    }

    public void updateCurrentWaitTime(final String time) {
        DocumentReference docRef = db.collection("bars").document("cactus");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        if (document.getData().get(time) == null) {
                            // no time...keep default time
                        } else {
                            // there is a time, update UI
                            System.out.println(">>>> update real time value");
                            int res = Integer.parseInt(document.getData().get(time).toString());

                            int mins = res / 60;
                            int seconds = res - (mins * 60);
                            currentMins.setText(mins + " mins");
                            currentSeconds.setText(seconds + " sec");
                        }
                    }
                }
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
}
