package project.icantwaittodrink.cnit355.cnit355_project;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout bobbys_const, brothers_const, cactus_const, harrys_const, whereElse_const;
    ImageView bobbyClosed, bobbyOpen, brothersClosed, brothersOpen, cactusClosed, cactusOpen, harrysClosed, harrysOpen, whereElseClosed, whereElseOpen;
    Drawable closedFalse, closedTrue, openTrue, openFalse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("iCantWait2Drink");

        // ASSIGN UI VARIABLES

        // ImageViews
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

        // Drawables
        closedFalse = this.getResources().getDrawable(R.drawable.closed_false);
        closedTrue = this.getResources().getDrawable(R.drawable.closed_true);
        openFalse = this.getResources().getDrawable(R.drawable.open_false);
        openTrue = this.getResources().getDrawable(R.drawable.open_true);

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

        // Open/Close Thread
        Thread openClose = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (i == 0){
                    // Every one second...
                    SystemClock.sleep(1000);
                    // Update the UI
                    updateUI();
                }
            }
        };
        openClose.start();
    }
    public void updateUI(){
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
                    bobbyOpen.setImageDrawable(openTrue);
                    bobbyClosed.setImageDrawable(closedFalse);
                }
                else if (17 <= intHour && intHour < 24){
                    bobbyOpen.setImageDrawable(openTrue);
                    bobbyClosed.setImageDrawable(closedFalse);
                }
                else{
                    bobbyOpen.setImageDrawable(openFalse);
                    bobbyClosed.setImageDrawable(closedTrue);
                }
                // Set Brother's Open/Close.
                if (0 <= intHour && intHour < 3){
                    brothersOpen.setImageDrawable(openTrue);
                    brothersClosed.setImageDrawable(closedFalse);
                }
                else if (11 <= intHour && intHour < 24){
                    brothersOpen.setImageDrawable(openTrue);
                    brothersClosed.setImageDrawable(closedFalse);
                }
                else{
                    brothersOpen.setImageDrawable(openFalse);
                    brothersClosed.setImageDrawable(closedTrue);
                }
                // Set Cactus Open/Close.
                cactusOpen.setImageDrawable(openFalse);
                cactusClosed.setImageDrawable(closedTrue);
                // Set Harry's Open/Close.
                if (0 <= intHour && intHour < 3){
                    harrysOpen.setImageDrawable(openTrue);
                    harrysClosed.setImageDrawable(closedFalse);
                }
                else if (11 <= intHour && intHour < 24){
                    harrysOpen.setImageDrawable(openTrue);
                    harrysClosed.setImageDrawable(closedFalse);
                }
                else{
                    harrysOpen.setImageDrawable(openFalse);
                    harrysClosed.setImageDrawable(closedTrue);
                }
                // Set Where else Open/Close.
                whereElseOpen.setImageDrawable(openFalse);
                whereElseClosed.setImageDrawable(closedTrue);
                break;
            case Calendar.TUESDAY:
                // Current day is Tuesday.
                // Set Bobby T's Open/Close.
                if (0 <= intHour && intHour < 3){
                    bobbyOpen.setImageDrawable(openTrue);
                    bobbyClosed.setImageDrawable(closedFalse);
                }
                else if (17 <= intHour && intHour < 24){
                    bobbyOpen.setImageDrawable(openTrue);
                    bobbyClosed.setImageDrawable(closedFalse);
                }
                else{
                    bobbyOpen.setImageDrawable(openFalse);
                    bobbyClosed.setImageDrawable(closedTrue);
                }
                // Set Brother's Open/Close.
                if (0 <= intHour && intHour < 3){
                    brothersOpen.setImageDrawable(openTrue);
                    brothersClosed.setImageDrawable(closedFalse);
                }
                else if (11 <= intHour && intHour < 24){
                    brothersOpen.setImageDrawable(openTrue);
                    brothersClosed.setImageDrawable(closedFalse);
                }
                else{
                    brothersOpen.setImageDrawable(openFalse);
                    brothersClosed.setImageDrawable(closedTrue);
                }
                // Set Cactus Open/Close.
                cactusOpen.setImageDrawable(openFalse);
                cactusClosed.setImageDrawable(closedTrue);
                // Set Harry's Open/Close.
                if (0 <= intHour && intHour < 3){
                    harrysOpen.setImageDrawable(openTrue);
                    harrysClosed.setImageDrawable(closedFalse);
                }
                else if (11 <= intHour && intHour < 24){
                    harrysOpen.setImageDrawable(openTrue);
                    harrysClosed.setImageDrawable(closedFalse);
                }
                else{
                    harrysOpen.setImageDrawable(openFalse);
                    harrysClosed.setImageDrawable(closedTrue);
                }
                // Set Where else Open/Close.
                whereElseOpen.setImageDrawable(openFalse);
                whereElseClosed.setImageDrawable(closedTrue);
                break;
            case Calendar.WEDNESDAY:
                // Current day is Wednesday.
                // Set Bobby T's Open/Close.
                if (0 <= intHour && intHour < 3){
                    bobbyOpen.setImageDrawable(openTrue);
                    bobbyClosed.setImageDrawable(closedFalse);
                }
                else if (17 <= intHour && intHour < 24){
                    bobbyOpen.setImageDrawable(openTrue);
                    bobbyClosed.setImageDrawable(closedFalse);
                }
                else{
                    bobbyOpen.setImageDrawable(openFalse);
                    bobbyClosed.setImageDrawable(closedTrue);
                }
                // Set Brother's Open/Close.
                if (0 <= intHour && intHour < 3){
                    brothersOpen.setImageDrawable(openTrue);
                    brothersClosed.setImageDrawable(closedFalse);
                }
                else if (11 <= intHour && intHour < 24){
                    brothersOpen.setImageDrawable(openTrue);
                    brothersClosed.setImageDrawable(closedFalse);
                }
                else{
                    brothersOpen.setImageDrawable(openFalse);
                    brothersClosed.setImageDrawable(closedTrue);
                }
                // Set Cactus Open/Close.
                cactusOpen.setImageDrawable(openFalse);
                cactusClosed.setImageDrawable(closedTrue);
                // Set Harry's Open/Close.
                if (0 <= intHour && intHour < 3){
                    harrysOpen.setImageDrawable(openTrue);
                    harrysClosed.setImageDrawable(closedFalse);
                }
                else if (11 <= intHour && intHour < 24){
                    harrysOpen.setImageDrawable(openTrue);
                    harrysClosed.setImageDrawable(closedFalse);
                }
                else{
                    harrysOpen.setImageDrawable(openFalse);
                    harrysClosed.setImageDrawable(closedTrue);
                }
                // Set Where else Open/Close.
                if (12 <= intHour && intHour < 24){
                    whereElseOpen.setImageDrawable(openTrue);
                    whereElseClosed.setImageDrawable(closedFalse);
                }
                else{
                    whereElseOpen.setImageDrawable(openFalse);
                    whereElseClosed.setImageDrawable(closedTrue);
                }
                break;
            case Calendar.THURSDAY:
                // Current day is Thursday.
                // Set Bobby T's Open/Close.
                if (0 <= intHour && intHour < 3){
                    bobbyOpen.setImageDrawable(openTrue);
                    bobbyClosed.setImageDrawable(closedFalse);
                }
                else if (17 <= intHour && intHour < 24){
                    bobbyOpen.setImageDrawable(openTrue);
                    bobbyClosed.setImageDrawable(closedFalse);
                }
                else{
                    bobbyOpen.setImageDrawable(openFalse);
                    bobbyClosed.setImageDrawable(closedTrue);
                }
                // Set Brother's Open/Close.
                if (0 <= intHour && intHour < 3){
                    brothersOpen.setImageDrawable(openTrue);
                    brothersClosed.setImageDrawable(closedFalse);
                }
                else if (11 <= intHour && intHour < 24){
                    brothersOpen.setImageDrawable(openTrue);
                    brothersClosed.setImageDrawable(closedFalse);
                }
                else{
                    brothersOpen.setImageDrawable(openFalse);
                    brothersClosed.setImageDrawable(closedTrue);
                }
                // Set Cactus Open/Close.
                if (20 <= intHour && intHour < 24){
                    cactusOpen.setImageDrawable(openTrue);
                    cactusClosed.setImageDrawable(closedFalse);
                }
                else{
                    cactusOpen.setImageDrawable(openFalse);
                    cactusClosed.setImageDrawable(closedTrue);
                }
                // Set Harry's Open/Close.
                if (0 <= intHour && intHour < 3){
                    harrysOpen.setImageDrawable(openTrue);
                    harrysClosed.setImageDrawable(closedFalse);
                }
                else if (11 <= intHour && intHour < 24){
                    harrysOpen.setImageDrawable(openTrue);
                    harrysClosed.setImageDrawable(closedFalse);
                }
                else{
                    harrysOpen.setImageDrawable(openFalse);
                    harrysClosed.setImageDrawable(closedTrue);
                }
                // Set Where else Open/Close.
                if (0 <= intHour && intHour < 3){
                    whereElseOpen.setImageDrawable(openTrue);
                    whereElseClosed.setImageDrawable(closedFalse);
                }
                else if (12 <= intHour && intHour < 24){
                    whereElseOpen.setImageDrawable(openTrue);
                    whereElseClosed.setImageDrawable(closedFalse);
                }
                else{
                    whereElseOpen.setImageDrawable(openFalse);
                    whereElseClosed.setImageDrawable(closedTrue);
                }
            case Calendar.FRIDAY:
                // Current day is Friday.
                // Set Bobby T's Open/Close.
                if (0 <= intHour && intHour < 3){
                    bobbyOpen.setImageDrawable(openTrue);
                    bobbyClosed.setImageDrawable(closedFalse);
                }
                else if (17 <= intHour && intHour < 24){
                    bobbyOpen.setImageDrawable(openTrue);
                    bobbyClosed.setImageDrawable(closedFalse);
                }
                else{
                    bobbyOpen.setImageDrawable(openFalse);
                    bobbyClosed.setImageDrawable(closedTrue);
                }
                // Set Brother's Open/Close.
                if (0 <= intHour && intHour < 3){
                    brothersOpen.setImageDrawable(openTrue);
                    brothersClosed.setImageDrawable(closedFalse);
                }
                else if (11 <= intHour && intHour < 24){
                    brothersOpen.setImageDrawable(openTrue);
                    brothersClosed.setImageDrawable(closedFalse);
                }
                else{
                    brothersOpen.setImageDrawable(openFalse);
                    brothersClosed.setImageDrawable(closedTrue);
                }
                // Set Cactus Open/Close.
                if (0 <= intHour && intHour <= 3){
                    cactusOpen.setImageDrawable(openTrue);
                }
                else if (20 <= intHour && intHour < 24){
                    cactusOpen.setImageDrawable(openTrue);
                }
                else{
                    cactusOpen.setImageDrawable(openFalse);
                    cactusClosed.setImageDrawable(closedTrue);
                }
                // Set Harry's Open/Close.
                if (0 <= intHour && intHour < 3){
                    harrysOpen.setImageDrawable(openTrue);
                    harrysClosed.setImageDrawable(closedFalse);
                }
                else if (11 <= intHour && intHour < 24){
                    harrysOpen.setImageDrawable(openTrue);
                    harrysClosed.setImageDrawable(closedFalse);
                }
                else{
                    harrysOpen.setImageDrawable(openFalse);
                    harrysClosed.setImageDrawable(closedTrue);
                }
                // Set Where else Open/Close.
                if (0 <= intHour && intHour < 3){
                    whereElseOpen.setImageDrawable(openTrue);
                    whereElseClosed.setImageDrawable(closedFalse);
                }
                else if (12 <= intHour && intHour < 24){
                    whereElseOpen.setImageDrawable(openTrue);
                    whereElseClosed.setImageDrawable(closedFalse);
                }
                else{
                    whereElseOpen.setImageDrawable(openFalse);
                    whereElseClosed.setImageDrawable(closedTrue);
                }
                break;
            case Calendar.SATURDAY:
                // Current day is Saturday.
                // Set Bobby T's Open/Close.
                if (0 <= intHour && intHour < 3){
                    bobbyOpen.setImageDrawable(openTrue);
                    bobbyClosed.setImageDrawable(closedFalse);
                }
                else if (17 <= intHour && intHour < 24){
                    bobbyOpen.setImageDrawable(openTrue);
                    bobbyClosed.setImageDrawable(closedFalse);
                }
                else{
                    bobbyOpen.setImageDrawable(openFalse);
                    bobbyClosed.setImageDrawable(closedTrue);
                }
                // Set Brother's Open/Close.
                if (0 <= intHour && intHour < 3){
                    brothersOpen.setImageDrawable(openTrue);
                    brothersClosed.setImageDrawable(closedFalse);
                }
                else if (11 <= intHour && intHour < 24){
                    brothersOpen.setImageDrawable(openTrue);
                    brothersClosed.setImageDrawable(closedFalse);
                }
                else{
                    brothersOpen.setImageDrawable(openFalse);
                    brothersClosed.setImageDrawable(closedTrue);
                }
                // Set Cactus Open/Close.
                if (0 <= intHour && intHour <= 3){
                    cactusOpen.setImageDrawable(openTrue);
                }
                else if (20 <= intHour && intHour < 24){
                    cactusOpen.setImageDrawable(openTrue);
                }
                else{
                    cactusOpen.setImageDrawable(openFalse);
                    cactusClosed.setImageDrawable(closedTrue);
                }
                // Set Harry's Open/Close.
                if (0 <= intHour && intHour < 3){
                    harrysOpen.setImageDrawable(openTrue);
                    harrysClosed.setImageDrawable(closedFalse);
                }
                else if (11 <= intHour && intHour < 24){
                    harrysOpen.setImageDrawable(openTrue);
                    harrysClosed.setImageDrawable(closedFalse);
                }
                else{
                    harrysOpen.setImageDrawable(openFalse);
                    harrysClosed.setImageDrawable(closedTrue);
                }
                // Set Where else Open/Close.
                if (0 <= intHour && intHour < 3){
                    whereElseOpen.setImageDrawable(openTrue);
                    whereElseClosed.setImageDrawable(closedFalse);
                }
                else if (12 <= intHour && intHour < 24){
                    whereElseOpen.setImageDrawable(openTrue);
                    whereElseClosed.setImageDrawable(closedFalse);
                }
                else{
                    whereElseOpen.setImageDrawable(openFalse);
                    whereElseClosed.setImageDrawable(closedTrue);
                }
                break;
            case Calendar.SUNDAY:
                // Current day is Sunday.
                // Set Bobby T's Open/Close.
                if (0 <= intHour && intHour < 3){
                    bobbyOpen.setImageDrawable(openTrue);
                    bobbyClosed.setImageDrawable(closedFalse);
                }
                else if (19 <= intHour && intHour < 24){
                    bobbyOpen.setImageDrawable(openTrue);
                    bobbyClosed.setImageDrawable(closedFalse);
                }
                else{
                    bobbyOpen.setImageDrawable(openFalse);
                    bobbyClosed.setImageDrawable(closedTrue);
                }
                // Set Brother's Open/Close.
                if (0 <= intHour && intHour < 3){
                    brothersOpen.setImageDrawable(openTrue);
                    brothersClosed.setImageDrawable(closedFalse);
                }
                else if (7 <= intHour && intHour < 24){
                    brothersOpen.setImageDrawable(openTrue);
                    brothersClosed.setImageDrawable(closedFalse);
                }
                else{
                    brothersOpen.setImageDrawable(openFalse);
                    brothersClosed.setImageDrawable(closedTrue);
                }
                // Set Cactus Open/Close.
                cactusOpen.setImageDrawable(openFalse);
                cactusClosed.setImageDrawable(closedTrue);
                // Set Harry's Open/Close.
                if (0 <= intHour && intHour < 3){
                    harrysOpen.setImageDrawable(openTrue);
                    harrysClosed.setImageDrawable(closedFalse);
                }
                else if (12 <= intHour && intHour < 24){
                    harrysOpen.setImageDrawable(openTrue);
                    harrysClosed.setImageDrawable(closedFalse);
                }
                else{
                    harrysOpen.setImageDrawable(openFalse);
                    harrysClosed.setImageDrawable(closedTrue);
                }
                // Set Where else Open/Close.
                if (0 <= intHour && intHour < 3){
                    whereElseOpen.setImageDrawable(openTrue);
                    whereElseClosed.setImageDrawable(closedFalse);
                }
                else{
                    whereElseOpen.setImageDrawable(openFalse);
                    whereElseClosed.setImageDrawable(closedTrue);
                }
                break;
        }
    }
}
