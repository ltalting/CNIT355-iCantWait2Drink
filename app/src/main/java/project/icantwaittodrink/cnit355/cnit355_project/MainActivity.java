package project.icantwaittodrink.cnit355.cnit355_project;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout bobbys_const, brothers_const, cactus_const, harrys_const, whereElse_const;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("iCantWait2Drink");

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

    }
}
