package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //variables
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImagesUrls = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: started");

        initImageBitmaps();
    }
    private void initImageBitmaps()
    {
        Log.d(TAG, "initImageBitmaps: preparing Bitmaps.");

        mImagesUrls.add("https://images.hdqwalls.com/download/movie-wall-e-ad-3840x2160.jpg");
        mNames.add("Image1");

        mImagesUrls.add("https://images.hdqwalls.com/download/movie-wall-e-ad-3840x2160.jpg");
        mNames.add("Image2");

        mImagesUrls.add("https://images.hdqwalls.com/download/movie-wall-e-ad-3840x2160.jpg");
        mNames.add("Image3");

        mImagesUrls.add("https://images.hdqwalls.com/download/movie-wall-e-ad-3840x2160.jpg");
        mNames.add("Image4");

        mImagesUrls.add("https://images.hdqwalls.com/download/movie-wall-e-ad-3840x2160.jpg");
        mNames.add("Image5");

        mImagesUrls.add("https://images.hdqwalls.com/download/movie-wall-e-ad-3840x2160.jpg");
        mNames.add("Image6");

        mImagesUrls.add("https://images.hdqwalls.com/download/movie-wall-e-ad-3840x2160.jpg");
        mNames.add("Image7");

        mImagesUrls.add("https://images.hdqwalls.com/download/movie-wall-e-ad-3840x2160.jpg");
        mNames.add("Image8");

        mImagesUrls.add("https://images.hdqwalls.com/download/movie-wall-e-ad-3840x2160.jpg");
        mNames.add("Image9");

        initRecyclerView();
    }

    private void initRecyclerView()
    {
        Log.d(TAG, "initRecyclerView: init recycler view");
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter( this, mNames, mImagesUrls );
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}