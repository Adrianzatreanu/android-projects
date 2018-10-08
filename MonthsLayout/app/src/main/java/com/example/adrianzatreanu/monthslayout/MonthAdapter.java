package com.example.adrianzatreanu.monthslayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MonthAdapter extends RecyclerView.Adapter<MonthAdapter.MyViewHolder> {

    private List<Run> runsList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView dateTextView;
        private TextView distanceTextView;
        private TextView paceView;
        private TextView timeView;
        private TextView distanceKM;
        private ImageView firstIcon;
        private ImageView secondIcon;
        private ImageView thirdIcon;
        private ImageView fourthIcon;

        public MyViewHolder(View view) {
            super(view);
            distanceTextView = (TextView) view.findViewById(R.id.distance_run);
            paceView = (TextView) view.findViewById(R.id.pace);
            timeView = (TextView) view.findViewById(R.id.time);
            dateTextView = (TextView) view.findViewById(R.id.date);
            distanceKM = (TextView) view.findViewById(R.id.text_km);

            firstIcon = (ImageView) view.findViewById(R.id.icon1);
            secondIcon = (ImageView) view.findViewById(R.id.icon2);
            thirdIcon = (ImageView) view.findViewById(R.id.icon3);
            fourthIcon = (ImageView) view.findViewById(R.id.icon4);
        }
    }


    public MonthAdapter(List<Run> moviesList, Context context) {
        this.context = context;
        this.runsList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.run_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Run movie = runsList.get(position);
        holder.distanceTextView.setText(movie.getDistance());
        holder.paceView.setText(movie.getPace());
        holder.timeView.setText(movie.getTime());
        holder.dateTextView.setText(movie.getDay() + "/" + movie.getMonth() + "/" + movie.getYear());
        holder.distanceKM.setText("km");

        Picasso.with(context)
                .load("http://lorempixel.com/50/50/")
                .placeholder(R.mipmap.sun)
                .into(holder.firstIcon);
    }

    @Override
    public int getItemCount() {
        return runsList.size();
    }
}
