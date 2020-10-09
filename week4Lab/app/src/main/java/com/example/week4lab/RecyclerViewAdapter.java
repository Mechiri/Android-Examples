package com.example.week4lab;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    //private ArrayList<String> ifImages;
    //private ArrayList<String> ifImageNames;
    ArrayList<RecycleViewList> ifrlist;
    private Context ifParent;

    public RecyclerViewAdapter(Context ifParent, ArrayList<RecycleViewList> ifrlist) {
        //this.ifImages = ifImages;
        //this.ifImageNames = ifImageNames;
        this.ifrlist = ifrlist;
        this.ifParent = ifParent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG,"REcyclerView Adapate: onCreate");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerlist, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "RecyclerView Adapter: onBindViewHolder");

        Glide.with(ifParent)
                .asBitmap()
                .load(ifrlist.get(position).getImage())
                .into(holder.mImage);

        holder.mImagename.setText(ifrlist.get(position).getImageName());
        holder.mParentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "OnBino: clicked "+ifrlist.get(position).getImageName());
                Toast.makeText(ifParent, ifrlist.get(position).getImageName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ifrlist.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        CircleImageView mImage;
        TextView mImagename;
        ConstraintLayout mParentlayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mImage = itemView.findViewById(R.id.rl_image);
            this.mImagename = itemView.findViewById(R.id.rl_imagename);
            this.mParentlayout = itemView.findViewById(R.id.rl_parent);
        }
    }
}
