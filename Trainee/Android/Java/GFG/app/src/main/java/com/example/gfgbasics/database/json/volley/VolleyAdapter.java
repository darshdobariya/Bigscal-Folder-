package com.example.gfgbasics.database.json.volley;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gfgbasics.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VolleyAdapter extends RecyclerView.Adapter<VolleyAdapter.ViewHolder> {

    Context context;
    ArrayList<DemoVolley> list;

    public VolleyAdapter(Context context, ArrayList<DemoVolley> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public VolleyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_volley, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VolleyAdapter.ViewHolder holder, int position) {
        // setting data to our views of recycler view.
        DemoVolley modal = list.get(position);
        holder.courseNameTV.setText(modal.getCourseName());
        holder.courseTracksTV.setText(modal.getCourseTracks());
        holder.courseModeTV.setText(modal.getCourseMode());
        Picasso.get().load(modal.getCourseimg()).into(holder.courseIV);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView courseNameTV, courseModeTV, courseTracksTV;
        private ImageView courseIV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // initializing our views with their ids.
            courseNameTV = itemView.findViewById(R.id.idTVCourseName);
            courseModeTV = itemView.findViewById(R.id.idTVBatch);
            courseTracksTV = itemView.findViewById(R.id.idTVTracks);
            courseIV = itemView.findViewById(R.id.idIVCourse);
        }
    }
}
