package project.icantwaittodrink.cnit355.cnit355_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CactusActivity extends AppCompatActivity {

    ImageView btnTimer, btnMaps, btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cactus);

        //Initialize Buttons
        btnTimer = findViewById(R.id.btnCactusTimer);
        btnMaps = findViewById(R.id.btnMapCactus);
        btnCall = findViewById(R.id.btnCallCactus);

        btnTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TimerActivity.class);
                startActivity(intent);
            }
        });
    }
}
