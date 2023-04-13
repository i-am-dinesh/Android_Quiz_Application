package com.example.quizzes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Categories extends AppCompatActivity {
    private Button b1,b2,b3,b4;
    private TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        b1 = (Button)findViewById(R.id.cat1);
        b2 = (Button)findViewById(R.id.cat2);
        b3 = (Button)findViewById(R.id.cat3);
        b4 = (Button)findViewById(R.id.cat4);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(getApplicationContext(), CSQuiz.class);
                startActivity(i1);
                finish();

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(getApplicationContext(), SSQuiz.class);
                startActivity(i2);
                finish();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(getApplicationContext(),GKQuiz.class);
                startActivity(i3);
                finish();
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i4 = new Intent(getApplicationContext(), MathQuiz.class);
                startActivity(i4);
                finish();
            }
        });

        t1 = (TextView)findViewById(R.id.txt_name);

        String username = getIntent().getStringExtra("keyname");

        t1.setText(username);

    }
}
