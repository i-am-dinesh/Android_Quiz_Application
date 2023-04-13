package com.example.quizzes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizzes.Model1.CQuestion;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CSQuiz extends AppCompatActivity {

    Button b1,b2,b3,b4,br;
    TextView t1_question, timerTxt;
    int total = 0 ;
    int correct = 0 ;
    DatabaseReference reference;
    int wrong = 0 ;
    private TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csquiz);

        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);
        b3 = (Button)findViewById(R.id.button3);
        b4 = (Button)findViewById(R.id.button4);

        t1_question = (TextView)findViewById(R.id.questionTxt);
        timerTxt = (TextView)findViewById(R.id.timerTxt);

        br = (Button)findViewById(R.id.result);
        br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iresult = new Intent(getApplicationContext(),HomeScreen.class);
                finish();
            }
        });

        checkConnection();


        updateQuestion();
        reverseTimer(300,timerTxt);



    }

    private void checkConnection() {

        ConnectivityManager connectivityManager= (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (null != networkInfo){
            if (networkInfo.getType()==ConnectivityManager.TYPE_MOBILE);
            Toast.makeText(this,"Great...!! Fatched Questions And Their Options", Toast.LENGTH_LONG).show();
        }

        else {

            Toast.makeText(this, "Oops...!! Unable To Fatching Questions And Their Options" +
                    " Check Your Internet Connection", Toast.LENGTH_LONG).show();
        }
    }

    private void updateQuestion() {
        total ++ ;
        if (total > 10 )
        {
            // Result Activiy
            Toast.makeText(getApplicationContext(),"Quiz Over !!!",Toast.LENGTH_SHORT).show();
            Intent ir = new Intent(CSQuiz.this,CSResult.class);
            ir.putExtra("total",String.valueOf(total));
            ir.putExtra("correct",String.valueOf(correct));
            ir.putExtra("incorrect",String.valueOf(wrong));
            startActivity(ir);
            finish();


        }

        else
        {
            reference = FirebaseDatabase.getInstance().getReference().child("Questions").child(String.valueOf(total));
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    final CQuestion question = snapshot.getValue(CQuestion.class);

                   t1_question.setText(question.getQuestion());
                    b1.setText(question.getOption1());
                    b2.setText(question.getOption2());
                    b3.setText(question.getOption3());
                    b4.setText(question.getOption4());

                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (b1.getText().toString().equals(question.getAnswer()))
                            {
                                Toast.makeText(getApplicationContext(),"Correct Answer !",Toast.LENGTH_SHORT).show();
                                b1.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct ++ ;
                                        b1.setBackgroundColor(Color.parseColor("#C84C88"));
                                        updateQuestion();

                                    }
                                }, 1500);


                            }

                            else
                            {

                                // if answer is wrong then we will find the correct one and make it green
                                Toast.makeText(getApplicationContext(),"Incorrect Answer !",Toast.LENGTH_SHORT).show();
                                wrong ++ ;
                                b1.setBackgroundColor(Color.RED);
                                if (b2.getText().toString().equals(question.getAnswer()))
                                {
                                    b2.setBackgroundColor(Color.GREEN);
                                }
                                else if (b3.getText().toString().equals(question.getAnswer()))
                                {
                                    b3.setBackgroundColor(Color.GREEN);
                                }
                                else if (b4.getText().toString().equals(question.getAnswer()))
                                {
                                    b4.setBackgroundColor(Color.GREEN);
                                }


                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        b1.setBackgroundColor(Color.parseColor("#C84C88"));
                                        b2.setBackgroundColor(Color.parseColor("#C84C88"));
                                        b3.setBackgroundColor(Color.parseColor("#C84C88"));
                                        b4.setBackgroundColor(Color.parseColor("#C84C88"));
                                        updateQuestion();
                                    }
                                }, 1500);

                            }
                        }
                    });

                    // now for b2

                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (b2.getText().toString().equals(question.getAnswer()))
                            {
                                Toast.makeText(getApplicationContext(),"Correct Answer !",Toast.LENGTH_SHORT).show();
                                b2.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b2.setBackgroundColor(Color.parseColor("#C84C88"));
                                        updateQuestion();
                                    }
                                },1500);
                            }
                            else
                            {
                                //ans is wrong...then we will find the correct and make it green color

                                Toast.makeText(getApplicationContext(),"Incorrect Answer !",Toast.LENGTH_SHORT).show();
                                wrong++;
                                b2.setBackgroundColor(Color.RED);
                                if (b1.getText().toString().equals(question.getAnswer()))
                                {
                                    b1.setBackgroundColor(Color.GREEN);
                                }
                                else if (b3.getText().toString().equals(question.getAnswer()))
                                {
                                    b3.setBackgroundColor(Color.GREEN);
                                }
                                else if (b4.getText().toString().equals(question.getAnswer()))
                                {
                                    b4.setBackgroundColor(Color.GREEN);
                                }



                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        b1.setBackgroundColor(Color.parseColor("#C84C88"));
                                        b2.setBackgroundColor(Color.parseColor("#C84C88"));
                                        b3.setBackgroundColor(Color.parseColor("#C84C88"));
                                        b4.setBackgroundColor(Color.parseColor("#C84C88"));
                                        updateQuestion();
                                    }
                                }, 1500);

                            }

                        }
                    });

                    // now for b3
                    b3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (b3.getText().toString().equals(question.getAnswer()))
                            {
                                Toast.makeText(getApplicationContext(),"Correct Answer !",Toast.LENGTH_SHORT).show();
                                b3.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b3.setBackgroundColor(Color.parseColor("#C84C88"));
                                        updateQuestion();
                                    }
                                },1500);
                            }
                            else
                            {
                                //ans is wrong...then we will find the correct and make it green color

                                Toast.makeText(getApplicationContext(),"Incorrect Answer !",Toast.LENGTH_SHORT).show();
                                wrong++;
                                b3.setBackgroundColor(Color.RED);
                                if (b1.getText().toString().equals(question.getAnswer()))
                                {
                                    b1.setBackgroundColor(Color.GREEN);
                                }
                                else if (b2.getText().toString().equals(question.getAnswer()))
                                {
                                    b2.setBackgroundColor(Color.GREEN);
                                }
                                else if (b4.getText().toString().equals(question.getAnswer()))
                                {
                                    b4.setBackgroundColor(Color.GREEN);
                                }



                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        b1.setBackgroundColor(Color.parseColor("#C84C88"));
                                        b2.setBackgroundColor(Color.parseColor("#C84C88"));
                                        b3.setBackgroundColor(Color.parseColor("#C84C88"));
                                        b4.setBackgroundColor(Color.parseColor("#C84C88"));
                                        updateQuestion();
                                    }
                                }, 1500);

                            }
                        }
                    });

                    // now for b4
                    b4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (b4.getText().toString().equals(question.getAnswer()))
                            {
                                Toast.makeText(getApplicationContext(),"Correct Answer !",Toast.LENGTH_SHORT).show();
                                b4.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b4.setBackgroundColor(Color.parseColor("#C84C88"));
                                        updateQuestion();
                                    }
                                },1500);
                            }
                            else
                            {
                                //ans is wrong...then we will find the correct and make it green color

                                Toast.makeText(getApplicationContext(),"Incorrect Answer !",Toast.LENGTH_SHORT).show();
                                wrong++;
                                b4.setBackgroundColor(Color.RED);
                                if (b1.getText().toString().equals(question.getAnswer()))
                                {
                                    b1.setBackgroundColor(Color.GREEN);
                                }
                                else if (b2.getText().toString().equals(question.getAnswer()))
                                {
                                    b2.setBackgroundColor(Color.GREEN);
                                }
                                else if (b3.getText().toString().equals(question.getAnswer()))
                                {
                                    b3.setBackgroundColor(Color.GREEN);
                                }



                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        b1.setBackgroundColor(Color.parseColor("#C84C88"));
                                        b2.setBackgroundColor(Color.parseColor("#C84C88"));
                                        b3.setBackgroundColor(Color.parseColor("#C84C88"));
                                        b4.setBackgroundColor(Color.parseColor("#C84C88"));
                                        updateQuestion();
                                    }
                                }, 1500);

                            }
                        }
                    });


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    public void reverseTimer(int Seconds,final TextView tv){

        new CountDownTimer(Seconds* 1000+1000, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                tv.setText(String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));
            }

            public void onFinish() {
                tv.setText("Completed");
                Intent myIntent = new Intent(CSQuiz.this,CSResult.class);
                myIntent.putExtra("total",String.valueOf(total));
                myIntent.putExtra("correct",String.valueOf(correct));
                myIntent.putExtra("incorrect",String.valueOf(wrong));
                startActivity(myIntent);
                finish();
            }
        }.start();

    }
}
