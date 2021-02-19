package com.example.demo;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Grid_Adapter extends RecyclerView.Adapter <Grid_Adapter.MyViewHolder> {

    ArrayList<Grid_Model>data;
    Context context;
    LayoutInflater inflater;

    public Grid_Adapter(ArrayList<Grid_Model> data, Context context) {
        this.data = data;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.grid_layout, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        final Grid_Model records = data.get(position);

        String img = Api_Config.img_url + records.getId();

        holder.textView.setText(records.getTitle());
        Picasso.get().load(img).into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setClickable(true);

            imageView = itemView.findViewById(R.id.image_view);
            textView = itemView.findViewById(R.id.title_txt);

        }
    }
}