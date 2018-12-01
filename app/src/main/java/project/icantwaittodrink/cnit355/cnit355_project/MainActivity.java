package project.icantwaittodrink.cnit355.cnit355_project;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout bobbys_const, brothers_const, cactus_const, harrys_const, whereElse_const;
    ImageView bobbyClosed, bobbyOpen, brothersClosed, brothersOpen, cactusClosed, cactusOpen, harrysClosed, harrysOpen, whereElseClosed, whereElseOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("iCantWait2Drink");

        // ASSIGN UI VARIABLES

        // Images
        bobbyClosed = findViewById(R.id.imgBobbyClosed);
        brothersClosed = findViewById(R.id.imgBrothersClosed);
        cactusClosed = findViewById(R.id.imgCactusClosed);
        harrysClosed = findViewById(R.id.imgHarrysClosed);
        whereElseClosed = findViewById(R.id.imgWhereElseClosed);

        bobbyOpen = findViewById(R.id.imgBobbyOpen);
        brothersOpen = findViewById(R.id.imgBrothersOpen);
        cactusOpen = findViewById(R.id.imgCactusOpen);
        harrysOpen = findViewById(R.id.imgHarrysOpen);
        whereElseOpen = findViewById(R.id.imgWhereElseOpen);

        // Containers (ConstraintLayouts)
        bobbys_const = findViewById(R.id.bobbys_const);
        brothers_const = findViewById(R.id.brothers_const);
        cactus_const = findViewById(R.id.cactus_const);
        harrys_const = findViewById(R.id.harrys_const);
        whereElse_const = findViewById(R.id.whereElse_const);

        // Container onClickListeners
        bobbys_const.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BobbyTsActivity.class);
                startActivity(intent);
            }
        });
        brothers_const.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BrothersActivity.class);
                startActivity(intent);
            }
        });
        harrys_const.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HarrysActivity.class);
                startActivity(intent);
            }
        });
        cactus_const.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CactusActivity.class);
                startActivity(intent);
            }
        });
        whereElse_const.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WhereElseActivity.class);
                startActivity(intent);
            }
        });

        Thread openClose = new Thread() {
            @Override
            public void run() {
                updateUI();
            }
        };
        openClose.start();
    }
    public void updateUI(){
        Bundle uiBundle = new Bundle();

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.SUNDAY:
                // Current day is Sunday
                break;
            case Calendar.MONDAY:
                // Current day is Monday
                break;
            case Calendar.TUESDAY:
                // etc.
                break;
        }

    }
    // Returns current hour in 24 hour format.
    public String currentHour(){
        Calendar cal = Calendar.getInstance();
        Date time = cal.getTime();
        DateFormat timeFormat = new SimpleDateFormat("HH");
        String currentTime = timeFormat.format(time);
        return currentTime;
    }
    // Returns current minutes in 24 hour format.
    public String currentMin(){
        Calendar cal = Calendar.getInstance();
        Date time = cal.getTime();
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String currentTime = timeFormat.format(time);
        return currentTime;
    }
}
