package com.example.gfgbasics.layout.viewadapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gfgbasics.R;
import com.example.gfgbasics.layout.demo.RCDemo;

import java.util.ArrayList;

public class RCAdapter extends RecyclerView.Adapter<RCAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<RCDemo> arrayList;

    public RCAdapter(Context context, ArrayList<RCDemo> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RCAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RCAdapter.ViewHolder holder, int position) {
        RCDemo demo = arrayList.get(position);
        holder.cName.setText(demo.getCourseName());
        holder.cRate.setText(String.valueOf(demo.getRate()));

        if(demo.getRate() > 0){
            holder.cIndicator.setColorFilter(ContextCompat.getColor(context, R.color.RED), android.graphics.PorterDuff.Mode.SRC_IN);
        }
        if(demo.getRate() > 2){
            holder.cIndicator.setColorFilter(ContextCompat.getColor(context, R.color.YELLOW), android.graphics.PorterDuff.Mode.SRC_IN);
        }
        if(demo.getRate() > 3){
            holder.cIndicator.setColorFilter(ContextCompat.getColor(context, R.color.GREEN), android.graphics.PorterDuff.Mode.SRC_IN);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cName, cRate;
        ImageView cIndicator;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cName = itemView.findViewById(R.id.courseName);
            cRate = itemView.findViewById(R.id.courseRating);
            cIndicator = itemView.findViewById(R.id.courseIndicator);
        }
    }
}
