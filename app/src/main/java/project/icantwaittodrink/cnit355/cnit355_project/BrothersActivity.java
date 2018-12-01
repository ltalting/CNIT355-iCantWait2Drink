package project.icantwaittodrink.cnit355.cnit355_project;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class BrothersActivity extends AppCompatActivity {

    ImageView btnTimer, btnMaps, btnCall;

    String address, phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brothers);

        //Initialize Intent Uses
        address = getString(R.string.brothersAddr);
        phoneNumber = getString(R.string.BrothersPhone);

        //Initialize buttons
        btnTimer = findViewById(R.id.btnBrothersTimer);
        btnMaps = findViewById(R.id.btnMapBrothers);
        btnCall = findViewById(R.id.btnCallBrothers);

        //Start Timer
        btnTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TimerActivity.class);
                startActivity(intent);
            }
        });
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
}
