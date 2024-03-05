package com.example.pseven;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{
    Context context;
    List<Recipe> list;
    OnItemClickListener listener;

    public ItemAdapter(Context context, List<Recipe> list, OnItemClickListener listener){
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        Recipe recipe = list.get(position);

        holder.itemName.setText(recipe.getItem());
        holder.itemDescription.setText(recipe.getDescription());

        holder.cvItem.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(position);

                SharedPreferences sp = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();

                editor.putInt("imageId", recipe.getImgId());
                editor.putString("itemName", recipe.getItem());
                editor.putString("itemDescription", recipe.getDescription());
                editor.apply();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemDescription;
        CardView cvItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemDescription = itemView.findViewById(R.id.itemDescription);
            cvItem = itemView.findViewById(R.id.cvItem);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private void openChildFragment(int imgId, String name, String description){

       /* Fragment childFragment = new ItemInfoFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("imageId", imgId);
        bundle.putString("itemName", name);
        bundle.putString("itemDescription", description);
        childFragment.setArguments(bundle);


        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frmItemClick, childFragment)
                .addToBackStack(null)
                .commit();*/
    }
}
