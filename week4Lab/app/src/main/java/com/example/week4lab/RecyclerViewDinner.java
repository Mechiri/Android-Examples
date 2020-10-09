package com.example.week4lab;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewDinner extends AppCompatActivity {

    private static final String TAG = "RecyclerViewDinner";

    private RecyclerView recyclerView;
    ArrayList<RecycleViewList> rItems = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);

        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layManage = new LinearLayoutManager(this);
        layManage.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layManage);

        initImageBitMaps();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter( this, rItems );
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initImageBitMaps()
    {
        Log.d(TAG, "initImageBitMaps");

        rItems.add(new RecycleViewList( "https://2.bp.blogspot.com/-OmRwEysWbFY/Vy19qUUBTlI/AAAAAAAAADQ/wxcHdLpTWJA-6RjhQQa6fiftUeQHiIM1gCLcB/s1600/south-Indian.jpg", "Dosa & Idli" ));
        rItems.add(new RecycleViewList( "https://previews.123rf.com/images/indianfoodimages/indianfoodimages1810/indianfoodimages181000824/110071401-indian-lunch-dinner-main-course-food-in-group-includes-paneer-butter-masala-dal-makhani-palak-paneer.jpg", "Panneer Butter Masala" ));
        rItems.add(new RecycleViewList( "https://st2.depositphotos.com/1354142/7950/i/950/depositphotos_79501056-stock-photo-south-indian-meals-served-on.jpg", "South Indian Meals" ));
        rItems.add(new RecycleViewList( "https://i2.wp.com/www.eatthis.com/wp-content/uploads/2019/10/peach-cherry-cobbler.jpg?resize=1024%2C750&ssl=1", "Peach Cobbler" ));
        rItems.add(new RecycleViewList( "https://i2.wp.com/www.eatthis.com/wp-content/uploads/2020/05/hot-wings.jpg?resize=1024%2C750&ssl=1", "Buffalo Wings" ));
        rItems.add(new RecycleViewList( "https://i1.wp.com/www.eatthis.com/wp-content/uploads/2020/02/strawberry-rhubarb-pie.jpg?resize=1024%2C750&ssl=1", "Strawberry Rhubarb Pie" ));
        rItems.add(new RecycleViewList( "https://www.cookwithmanali.com/wp-content/uploads/2019/10/Gulab-Jamun-Shrikhand-Jars.jpg", "Gulab Jamun" ));
        rItems.add(new RecycleViewList( "https://www.whiskaffair.com/wp-content/uploads/2019/12/Bagara-Rice-1-1.jpg", "Bagara Rice" ));
        rItems.add(new RecycleViewList( "https://www.thedeliciouscrescent.com/wp-content/uploads/2016/05/Easy-Hyderabadi-Chicken-Biryani.jpg", "Hyderabadi Biriyani" ));
    }
}

