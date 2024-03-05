package com.example.gfgbasics.advanceview.diprecycleview.nested.adapter;

import static com.example.gfgbasics.advanceview.diprecycleview.nested.adapter.AdapterChild.*;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gfgbasics.R;
import com.example.gfgbasics.advanceview.diprecycleview.nested.demo.DemoChild;

import java.util.List;

public class AdapterChild extends RecyclerView.Adapter<ViewHolder> {

    Context context;
    List<DemoChild> list;

    public AdapterChild(Context context, List<DemoChild> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcnestedmainchild, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(list.get(position).getText());
        holder.imageView.setImageResource(R.drawable.home);
        holder.imageView.setBackgroundColor(list.get(position).getColor());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tvName);
            imageView = itemView.findViewById(R.id.imgViewq);
        }
    }
}
