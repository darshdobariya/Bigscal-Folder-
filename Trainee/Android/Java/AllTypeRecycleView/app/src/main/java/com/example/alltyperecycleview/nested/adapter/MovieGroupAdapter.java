package com.example.alltyperecycleview.nested.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.alltyperecycleview.R;
import com.example.alltyperecycleview.nested.demo.MovieGroup;

import org.w3c.dom.Text;

import java.util.List;

public class MovieGroupAdapter extends RecyclerView.Adapter<MovieGroupAdapter.ViewHolder> {
    Context context;
    List<MovieGroup> list;

    public MovieGroupAdapter(Context context, List<MovieGroup> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MovieGroupAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.nested_rc_movielist, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieGroupAdapter.ViewHolder holder, int position) {
        holder.tvGroupName.setText(list.get(position).getGroupName());

        MovieAdapter movieAdapter = new MovieAdapter(context, list.get(position).getList());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.rcView.setLayoutManager(layoutManager);
        holder.rcView.setAdapter(movieAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rcView;
        TextView tvGroupName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rcView = itemView.findViewById(R.id.rcNestedChild);
            tvGroupName = itemView.findViewById(R.id.tvGroupName);
        }
    }
}
