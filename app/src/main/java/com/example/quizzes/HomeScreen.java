package com.example.quizzes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeScreen extends AppCompatActivity {
    private EditText name;
    private Button b1, b ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        checkConnection();

        name = (EditText)findViewById(R.id.name);
        b1 = (Button)findViewById(R.id.nbtn);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name.length() == 0 )
                {
                    name.setError("Enter Name");
                }

                String username = name.getText().toString();

                Intent i = new Intent(HomeScreen.this, Categories.class);
                i.putExtra("keyname", username);
                startActivity(i);
            }
        });

        b = (Button)findViewById(R.id.about);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent about = new Intent(getApplicationContext(), About_Activity.class);
                startActivity(about);
            }
        });
    }

    public  void  checkConnection(){
        ConnectivityManager connectivityManager= (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (null != networkInfo){
            if (networkInfo.getType()==ConnectivityManager.TYPE_MOBILE);
            Toast.makeText(this,"Great...!!! Internet Access.", Toast.LENGTH_LONG).show();
        }

        else {

            Toast.makeText(this, "Oops...!!! No Internet Access.", Toast.LENGTH_LONG).show();
        }
    }

}
