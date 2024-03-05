package com.example.gfgbasics20.exampleone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gfgbasics20.R;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private List<User> userList;
    private Context context;
    private ItemClickListener itemClickListener;

    public CustomAdapter(Context context, List<User> userArrayList) {
        this.context = context;
        this.userList = userArrayList;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_car, viewGroup, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);

        /*view.setOnClickListener(v -> {
            itemClickListener.onItemClick(userList.get(myViewHolder.getAdapterPosition()),myViewHolder.getAdapterPosition());
        });*/

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(CustomAdapter.MyViewHolder viewHolder, int position) {
        viewHolder.userId.setText(userList.get(position).getUserId());
        viewHolder.id.setText(userList.get(position).getId());

        viewHolder.userId.setOnClickListener(v->{
            Toast.makeText(context,userList.get(position).getId(),Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        if(this.userList != null) {
            return this.userList.size();
        }
        return 0;
    }

    public void setMovieList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private final TextView userId;
        private final TextView id;
        public MyViewHolder(View view) {
            super(view);
            userId = (TextView)view.findViewById(R.id.tvDescription);
            id = (TextView)view.findViewById(R.id.tvLocation);

        }
    }
}
