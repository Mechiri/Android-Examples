package com.example.week6lab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class PrevLocation extends AppCompatActivity {

    private static final String TAG = "PrevLocation";

    private TextView commentsText, LatText, LonText;
    private FirebaseFirestore db;
    private Button bBack;

    private android.location.Location prevLocation;
    private String Comments, Lat, Lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prev_location);

        commentsText = findViewById(R.id.commentsdisplay);
        LatText = findViewById(R.id.Latitudedisplay);
        LonText = findViewById(R.id.Longitudedisplay);
        bBack = findViewById(R.id.bBack);

        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( PrevLocation.this, Locations.class));
            }
        });

        db = FirebaseFirestore.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "Coming Here!!!!!!!!!...........:");

        db.collection("users")
                .orderBy("timestamp", Query.Direction.ASCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {
                            for(QueryDocumentSnapshot document: task.getResult()) {
                                //Log.d(TAG, document.getId() +" => " + document.getData());
                                Map<String, Object> user = document.getData();
                                if(user.get("prevloc") != null)
                                {
                                    Map<String, Object> locDetails = (Map<String, Object>) user.get("prevloc");
                                    Lat = locDetails.get("latitude").toString();
                                    Lon = locDetails.get("longitude").toString();
                                    LonText.setText(Lon);
                                    LatText.setText(Lat);
                                    Log.d(TAG, "Latitude: "+Lat+"Longitude"+Lon);

                                }
                                if(user.get("comments") != null) {
                                    Comments = user.get("comments").toString();
                                    commentsText.setText(Comments);
                                    Log.d(TAG, Comments);
                                }

                            }
                        }
                        else
                        {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

        //db.collection()

        //Log.d(TAG, "Coming Here------------!!!!!!!!!...........:");
        //commentsText.setText(Comments.toString());
        //LonText.setText(Lon.toString());
        //LatText.setText(Lat.toString());
    }
}