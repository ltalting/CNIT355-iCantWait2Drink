package project.icantwaittodrink.cnit355.cnit355_project;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CactusActivity extends AppCompatActivity {

    ImageView btnTimer, btnMaps, btnCall;
    String address, phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cactus);

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
}
