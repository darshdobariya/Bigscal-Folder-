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
import com.example.test3.demo.AddUser;
import com.example.test3.chat.ChatActivity;

import java.util.List;

public class AddNewAdapter extends RecyclerView.Adapter<AddNewAdapter.ViewHolder>{

    List<AddUser> list;
    Context context;

    public AddNewAdapter( Context context, List<AddUser> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AddNewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddNewAdapter.ViewHolder holder, int position) {
        AddUser addUser = list.get(position);

        holder.tvName.setText(addUser.getName());
        holder.tvNumber.setText(addUser.getPhone());

        holder.imgNext.setOnClickListener(v->{
            Intent i = new Intent(context, ChatActivity.class);
            i.putExtra("name", addUser.getName());
            i.putExtra("uid", addUser.getUid());
            i.putExtra("mobile", addUser.getPhone());
            context.startActivity(i);
        });
    }

    public void addUserData(String name, String phone, String uid){
        list.add(new AddUser(phone, uid, name));
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvNumber;
        ImageView imgNext;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvUserName);
            tvNumber = itemView.findViewById(R.id.tvMobile);
            imgNext = itemView.findViewById(R.id.imgNext);
        }
    }
}
