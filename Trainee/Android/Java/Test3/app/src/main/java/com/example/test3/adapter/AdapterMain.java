package com.example.test3.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test3.R;
import com.example.test3.chat.ChatActivity;
import com.example.test3.demo.Main;

import java.util.List;

public class AdapterMain extends RecyclerView.Adapter<AdapterMain.ViewHolder> {

    List<Main> list;
    Context context;

    public AdapterMain( Context context, List<Main> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterMain.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMain.ViewHolder holder, int position) {
        Main main = list.get(position);

        holder.tvName.setText(main.getName());
        holder.tvCount.setText(main.getCount());
        holder.tvTime.setText(main.getTime());

        holder.imgNext.setOnClickListener(v->{
            Intent i = new Intent(context, ChatActivity.class);
            i.putExtra("name", main.getName());
            i.putExtra("uid", main.getUid());
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addData(String name, String time, String count, String uid){
        list.add(new Main(name, time, count, uid));
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvCount, tvTime;
        ImageView imgNext;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvUserName);
            tvCount = itemView.findViewById(R.id.tvCount);
            tvTime = itemView.findViewById(R.id.tvTime);
            imgNext = itemView.findViewById(R.id.imgNext);
        }
    }
}
