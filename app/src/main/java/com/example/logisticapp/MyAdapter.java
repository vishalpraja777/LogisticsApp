package com.example.logisticapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private ArrayList ind , froml, tol, distance, vehi;

    private onNoteListener monNoteListener;

    public MyAdapter(Context context, ArrayList ind, ArrayList froml, ArrayList tol, ArrayList distance, ArrayList vehi,onNoteListener onNoteListener) {
        this.context = context;
        this.ind = ind;
        this.froml = froml;
        this.tol = tol;
        this.distance = distance;
        this.vehi = vehi;
        this.monNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.bookings,parent,false);
        return new MyViewHolder(v,monNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.froml.setText(String.valueOf(froml.get(position)));
        holder.tol.setText(String.valueOf(tol.get(position)));
        holder.distance.setText(String.valueOf(distance.get(position)));
        holder.vehi.setText(String.valueOf(vehi.get(position)));
    }

    @Override
    public int getItemCount() {
        return froml.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView  froml, tol, distance, vehi;
        onNoteListener onNoteListener;

        public MyViewHolder(@NonNull View itemView,onNoteListener onNoteListener) {
            super(itemView);

            froml = itemView.findViewById(R.id.from);
            tol = itemView.findViewById(R.id.to);
            distance = itemView.findViewById(R.id.distance);
            vehi = itemView.findViewById(R.id.vehicle);
            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface onNoteListener{
        void onNoteClick(int position);
    }
}
