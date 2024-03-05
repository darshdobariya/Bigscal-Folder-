package com.example.androidtest.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidtest.R;
import com.example.androidtest.activity.UserProfileActivity;
import com.example.androidtest.demo.User;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<User> arrayList;

    public UserAdapter(Context context, ArrayList<User> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcevent, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = arrayList.get(position);
        holder.uName.setText(user.getuNAme());
        holder.uAge.setText(String.valueOf(user.getuAge()));
        holder.uGender.setText(user.getuGender());
        holder.uStudy.setText(user.getuStudy());
        holder.uAddress.setText(user.getuAddress());

        // Write a code onClick(imgInfo) to open new activity
        holder.imgInfo.setOnClickListener(v->{
            Intent i = new Intent(context, UserProfileActivity.class);

            i.putExtra("uName", user.getuNAme());
            i.putExtra("uAge", String.valueOf(user.getuAge()));
            i.putExtra("uGender", user.getuGender());
            i.putExtra("uStudy", user.getuStudy());
            i.putExtra("uAddress", user.getuAddress());

            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView uName, uAge, uGender, uStudy, uAddress;
        ImageView imgInfo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            uName = itemView.findViewById(R.id.tvName);
            uAge = itemView.findViewById(R.id.tvAge);
            uGender = itemView.findViewById(R.id.tvGender);
            uStudy = itemView.findViewById(R.id.tvStudy);
            uAddress = itemView.findViewById(R.id.tvAddress);
            imgInfo = itemView.findViewById(R.id.imgInfo);
        }
    }
}
