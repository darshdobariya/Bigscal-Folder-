package com.example.gfgbasics.database.room;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gfgbasics.R;

import java.util.ArrayList;
import java.util.List;

public class RoomDBAdapter extends RecyclerView.Adapter<RoomDBAdapter.ViewHolder> {
    List<Users> list;
    Context context;
    private AdapterListner adapterListner;
    public RoomDBAdapter(@NonNull Context context, AdapterListner adapterListner) {
        super();
        this.context = context;
        this.list = new ArrayList<>();
        this.adapterListner = adapterListner;
    }

    @NonNull
    @Override
    public RoomDBAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomDBAdapter.ViewHolder holder, int position) {
        Users users = list.get(position);

        holder.tvName.setText(users.getName());
        holder.tvEmail.setText(users.getEmail());

        holder.btnDelete.setOnClickListener(v->{
            adapterListner.OnDelete(users.getId(), position);
        });

        holder.btnEdit.setOnClickListener(v->{
            adapterListner.OnUpdate(users);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addUser(Users users) {
        list.add(users);
        notifyDataSetChanged();
    }

    public void removeUser(int pos){
        list.remove(pos);
        notifyDataSetChanged();
    }

    public void clearData(){
        list.clear();
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvEmail, tvName;
        ImageButton btnEdit, btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvName = itemView.findViewById(R.id.tvName);
            btnEdit = itemView.findViewById(R.id.imgEdit);
            btnDelete = itemView.findViewById(R.id.imgDelete);
        }
    }
}
