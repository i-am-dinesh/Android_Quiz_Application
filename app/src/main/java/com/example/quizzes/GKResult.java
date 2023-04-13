package com.example.quizzes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GKResult extends AppCompatActivity {
    TextView t1,t2,t3;
    private Button move;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gkresult);
        t1 = (TextView)findViewById(R.id.textView4);
        t2 = (TextView)findViewById(R.id.textView5);
        t3 = (TextView)findViewById(R.id.textView6);

        Intent i = getIntent();

        String questions = i.getStringExtra("total+1");
        String correct = i.getStringExtra("correct");
        String wrong = i.getStringExtra("incorrect");

        t1.setText(questions);
        t2.setText(correct);
        t3.setText(wrong);

        move=(Button)findViewById(R.id.again);
        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),HomeScreen.class);
                startActivity(intent);

            }
        });
    }
}
