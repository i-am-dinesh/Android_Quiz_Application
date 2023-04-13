package com.example.quizzes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo logo = new logo();
        logo.start();
    }
    private class logo extends Thread
    {
        public void run()
        {
            try {

                sleep(2000);

            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            Intent in = new Intent(MainActivity.this,HomeScreen.class);
            startActivity(in);
            MainActivity.this.finish();
        }
    }
}
