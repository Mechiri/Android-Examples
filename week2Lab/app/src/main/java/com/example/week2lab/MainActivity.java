package com.example.week2lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button bSubmit;
    EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bSubmit = findViewById(R.id.ambSubmit);
        etName = findViewById(R.id.ametName);

        Log.d("textScreen1", "onCreate Screen1");
        Toast.makeText(this, "Scree1", Toast.LENGTH_LONG).show();

        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentObject = new Intent(view.getContext(), greetActitvity.class);
                intentObject.putExtra( "NAME", etName.getText().toString());
                startActivity(intentObject);
            }
        });
    }
}