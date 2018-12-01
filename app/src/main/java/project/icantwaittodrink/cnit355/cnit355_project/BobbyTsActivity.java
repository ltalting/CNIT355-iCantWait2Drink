package project.icantwaittodrink.cnit355.cnit355_project;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class BobbyTsActivity extends AppCompatActivity {

    ImageView btnTimer, btnMaps, btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bobby_ts);

        //Initialize Buttons
        btnTimer = findViewById(R.id.btnBobbyTimer);
        btnMaps = findViewById(R.id.btnMapBobby);
        btnCall = findViewById(R.id.btnCallBobby);
        //Go to Timer
        btnTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TimerActivity.class);
                startActivity(intent);
            }
        });
        //Go to Google Maps
        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=308 W State St, West Lafayette, IN 47906"));
                startActivity(intent);
            }
        });
        //Dial Bobby Ts
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+3173456789"));
                startActivity(intent);
            }
        });
    }
}
