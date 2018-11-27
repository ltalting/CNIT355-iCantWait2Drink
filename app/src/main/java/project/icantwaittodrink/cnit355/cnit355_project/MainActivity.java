package project.icantwaittodrink.cnit355.cnit355_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView bobbyImg,brotherImg,cactusImg,harryImg,whereElseimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bobbyImg = findViewById(R.id.bobby);
        brotherImg = findViewById(R.id.brothers);
        cactusImg = findViewById(R.id.cactus);
        harryImg = findViewById(R.id.harrys);
        whereElseimg = findViewById(R.id.whereElse);

        bobbyImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BobbT.class);
                startActivity(intent);
            }
        });
    }
}
