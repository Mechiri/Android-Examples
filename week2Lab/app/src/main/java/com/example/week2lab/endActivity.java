package com.example.week2lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class endActivity extends AppCompatActivity {

    Button eaButton;
    TextView eatvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        eaButton = findViewById(R.id.eabutton);
        eatvName = findViewById(R.id.eatvName);

        Intent iEndIntent = getIntent();
        String Name = iEndIntent.getStringExtra("Name");
        Name = "Damn! "+ Name +", your voice So CUTE!";
        eatvName.setText(Name);

        eaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}