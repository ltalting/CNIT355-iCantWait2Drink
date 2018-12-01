package project.icantwaittodrink.cnit355.cnit355_project;

import android.content.Intent;
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

        btnTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TimerActivity.class);
                startActivity(intent);
            }
        });


    }
}
