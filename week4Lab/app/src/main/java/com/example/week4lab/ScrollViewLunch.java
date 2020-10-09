package com.example.week4lab;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class ScrollViewLunch extends AppCompatActivity {

    private static final String TAG = "ScrollViewLunch";
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view_lunch);

        Log.d(TAG,"ScrollViewLunch: onCreate");
        Toast.makeText(this,"SVLunch: OnCreate", Toast.LENGTH_SHORT).show();

        image = findViewById(R.id.Burrito);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(this,"Burrito", Toast.LENGTH_SHORT).show();
                //Toast.makeText(this,"SVLunch: OnCreate", Toast.LENGTH_SHORT).show();

                Log.d(TAG,"Burrito");
            }
        });
    }
}