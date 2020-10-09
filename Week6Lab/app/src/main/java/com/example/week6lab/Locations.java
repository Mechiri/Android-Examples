package com.example.week6lab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Locations extends AppCompatActivity {
    private static final String TAG = "Location";

    private TextView tLat, tLon, tAlt, tSpe;
    private Switch sLoc, sUpd;
    private EditText commentsText;
    private Button bSubmit, bprevLocation, blogout;
    private FirebaseAuth mAuth;

    private FusedLocationProviderClient locClient;
    private FirebaseFirestore db;
    private LocationRequest locRequest;
    private LocationCallback locationCallback;
    private static final int LOCATION_PERMISSIONS = 13;
    private android.location.Location prevLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        Log.d(TAG,"onCreate...");

        mAuth = FirebaseAuth.getInstance();
        tLat = findViewById(R.id.LatitudeValue);
        tLon = findViewById(R.id.LongitudeValue);
        tAlt = findViewById(R.id.AltitudeValue);
        tSpe = findViewById(R.id.SpeedValue);
        sLoc = findViewById(R.id.LocationSwitch);
        sUpd = findViewById(R.id.UpdateSwitch);
        commentsText = findViewById(R.id.tCommentsList);
        bSubmit = findViewById(R.id.bSubmit);
        bprevLocation = findViewById(R.id.blastocation);
        blogout = findViewById(R.id.blogout);
        db = FirebaseFirestore.getInstance();

        locRequest = new LocationRequest();
        locRequest.setInterval(20*1000);
        locRequest.setFastestInterval(5*1000);
        locRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadPreviousLocation();
            }
        });

        bprevLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Locations.this, PrevLocation.class));
            }
        });

        sLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sLoc.isChecked())
                {
                    sLoc.setText("GPS");
                    locRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                }
                else
                {
                    sLoc.setText("WiFi");
                    locRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
                }
            }
        });

        sUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleUpdate();
            }
        });

        locationCallback = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                updateGPS();
            }
        };

        blogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        updateGPS();
    }

    protected void logout()
    {
        mAuth.signOut();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Log.d(TAG,"onRequestPermissionsResult...");
        if(requestCode == LOCATION_PERMISSIONS)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                updateGPS();
            }
            else
            {
                Toast.makeText(this, "GPS Permission Not Granted. Closing App", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void  updateGPS()
    {
        Log.d(TAG,"updateGPS...");
        locClient = LocationServices.getFusedLocationProviderClient(this);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            Log.d(TAG,"updateGPS...: if true....");
            locClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<android.location.Location>() {
                @Override
                public void onSuccess(android.location.Location location) {
                    Log.d(TAG,"updateGPS...: if Success....");
                    Log.d(TAG,location.toString());
                    prevLocation = location;
                    updateUI(location);
                }
            });
        }
        else
        {
            Log.d(TAG,"updateGPS...: if false....");
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSIONS);
            }
        }
    }

    private void updateUI(android.location.Location location)
    {
        Log.d(TAG,"updateUI...");
        tLat.setText(String.valueOf(location.getLatitude()));
        tLon.setText(String.valueOf(location.getLongitude()));

        if(location.hasAltitude())
        {
            tAlt.setText(String.valueOf(location.getLongitude()));
        }
        else
        {
            tAlt.setText("N/A");
        }

        if(location.hasSpeed())
        {
            tAlt.setText(String.valueOf(location.getSpeed()));
        }
        else
        {
            tAlt.setText("N/A");
        }
    }

    private void toggleUpdate() {
        Log.d(TAG,"toggleUpdate...");
        if (sUpd.isChecked() && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locClient.requestLocationUpdates(locRequest, locationCallback, null);
        } else {
            tLat.setText("OFF");
            tLon.setText("OFF");
            tAlt.setText("OFF");
            tSpe.setText("OFF");
            locClient.removeLocationUpdates(locationCallback);
        }
    }

    private void uploadPreviousLocation()
    {
        String tags = commentsText.getText().toString();
        Map<String, Object> user = new HashMap<>();
        user.put("comments", tags);
        user.put("prevloc", prevLocation);
        user.put("timestamp", FieldValue.serverTimestamp());
        Log.d(TAG,"uploadPreviousLocation: coming!");
        db.collection("users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "Previous Location Added: "+documentReference.getId());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error Occurred: " + e.getMessage());
            }
        });
    }
}