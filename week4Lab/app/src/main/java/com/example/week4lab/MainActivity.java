package com.example.week4lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    Button bDinner;
    Button bLunch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate Button");
        Toast.makeText(this,"Menu", Toast.LENGTH_SHORT).show();

        bLunch = findViewById(R.id.bLunch);
        bLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scrollLunch = new Intent(v.getContext(), ScrollViewLunch.class);
                startActivity(scrollLunch);
            }
        });

        bDinner = findViewById(R.id.bDinner);
        bDinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recyclerDinner = new Intent(v.getContext(), RecyclerViewDinner.class);
                startActivity(recyclerDinner);
            }
        });
    }
}