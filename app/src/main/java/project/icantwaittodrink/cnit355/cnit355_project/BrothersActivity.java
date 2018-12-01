package project.icantwaittodrink.cnit355.cnit355_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class BrothersActivity extends AppCompatActivity {

    ImageView btnTimer, btnMaps, btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brothers);

        //Initialize buttons
        btnTimer = findViewById(R.id.btnBrothersTimer);
        btnMaps = findViewById(R.id.btnMapBrothers);
        btnCall = findViewById(R.id.btnCallBrothers);

        btnTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TimerActivity.class);
                startActivity(intent);
            }
        });
    }
}
