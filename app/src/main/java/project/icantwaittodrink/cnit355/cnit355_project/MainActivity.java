package project.icantwaittodrink.cnit355.cnit355_project;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imgBobbyTs, imgBrothers, imgCactus, imgHarrys, imgWhereElse;
    ConstraintLayout bobbys_const, brothers_const, cactus_const, harrys_const, whereElse_const;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Images
        imgBobbyTs = findViewById(R.id.imgBobbyTs);
        imgBrothers = findViewById(R.id.imgBrothers);
        imgCactus = findViewById(R.id.imgCactus);
        imgHarrys = findViewById(R.id.imgHarrys);
        imgWhereElse = findViewById(R.id.imgWhereElse);

        // Containers (ConstraintLayouts)
        bobbys_const = findViewById(R.id.bobbys_const);
        brothers_const = findViewById(R.id.brothers_const);
        cactus_const = findViewById(R.id.cactus_const);
        harrys_const = findViewById(R.id.harrys_const);
        whereElse_const = findViewById(R.id.whereElse_const);

        // Image onClickListeners
        imgBobbyTs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BobbyTsActivity.class);
                startActivity(intent);
            }
        });
        imgBrothers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BrothersActivity.class);
                startActivity(intent);
            }
        });

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
    }
}
