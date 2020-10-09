package com.example.week5lab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class picture extends AppCompatActivity {

    private static final String TAG = "picture";

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private Button blogout;
    private Button btakephoto;
    private Button bSubmit;
    private TextView displayName;
    private TextView tagTextView;
    private EditText tagEditText;
    private ImageView img;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        Log.w(TAG,"onCreate: Coming");
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        blogout = findViewById(R.id.logoutButton);
        btakephoto = findViewById(R.id.clickButton);
        bSubmit = findViewById(R.id.submitPhotoButton);
        displayName = findViewById(R.id.userNameText);
        tagTextView = findViewById(R.id.promptTextView);
        tagEditText = findViewById(R.id.foodTagsEditText);
        img = findViewById(R.id.capturedimageView);

        tagTextView.setVisibility(View.INVISIBLE);
        tagEditText.setVisibility(View.INVISIBLE);
        bSubmit.setVisibility(View.INVISIBLE);
        img.setVisibility(View.INVISIBLE);

        blogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        btakephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"btakephoto: coming!");
                dispatchTakePictureIntent();
            }
        });

        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadTags();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null)
            startActivity(new Intent (this,MainActivity.class));
        else
        {
            String email = currentUser.getEmail();
            displayName.setText("Hello: "+email);

        }
    }

    protected void logout()
    {
        mAuth.signOut();
        startActivity(new Intent(this, MainActivity.class));
    }

    private void dispatchTakePictureIntent()
    {
        Log.d(TAG,"dispatchTakePictureIntent: coming!");
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Log.d(TAG,"onActivityResult: coming!");
        super.onActivityResult(requestCode,resultCode,data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
        {
            Log.d(TAG,"onActivityResult: In condition coming!");
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            img.setImageBitmap(imageBitmap);
            tagTextView.setVisibility(View.VISIBLE);
            tagEditText.setVisibility(View.VISIBLE);
            bSubmit.setVisibility(View.VISIBLE);
            img.setVisibility(View.VISIBLE);
            cameraAction();
        }
    }

    protected void cameraAction()
    {
        Log.d(TAG,"cameraAction: coming!");
        tagTextView.setVisibility(View.VISIBLE);
        tagEditText.setVisibility(View.VISIBLE);
        bSubmit.setVisibility(View.VISIBLE);
        img.setVisibility(View.VISIBLE);
    }

    protected void uploadTags()
    {
        String tags = tagEditText.getText().toString();
        Map<String, Object> user = new HashMap<>();
        user.put("text", tags);
        Log.d(TAG,"uploadTags: coming!");
        db.collection("users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "Object Added: "+documentReference.getId());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error Occurred: " + e.getMessage());
            }
        });
        startActivity(new Intent(this, MainActivity.class));
    }
}