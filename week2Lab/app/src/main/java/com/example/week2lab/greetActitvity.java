package com.example.week2lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class greetActitvity extends AppCompatActivity {

    Button bBack;
    TextView tvName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greet_actitvity);

        bBack = findViewById(R.id.gabGreet);
        tvName = findViewById(R.id.gatvName);

        Intent iCurrentIntent = getIntent();
        //String myName = "Hi, I am WallE";
        final String name = iCurrentIntent.getStringExtra("NAME");
        String name1 = "I am WallE! Hi "+ name +".";
        tvName.setText(name1);

        Toast.makeText(this,"Screen 2", Toast.LENGTH_LONG).show();
        Log.d("textScreen2", "at Screen 2: ");

        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(view.getContext(), endActivity.class);
                i.putExtra("Name", name);
                startActivity(i);
            }
        });
    }
}