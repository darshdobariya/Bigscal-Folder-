package com.example.gfgbasics.advanceview.diprecycleview.nested.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gfgbasics.R;
import com.example.gfgbasics.advanceview.diprecycleview.nested.demo.DemoChild;
import com.example.gfgbasics.advanceview.diprecycleview.nested.demo.DemoParent;

import java.util.List;

public class AdapterParent extends RecyclerView.Adapter<AdapterParent.ViewHolder> {

    Context context;
    List<DemoParent> list;

    public AdapterParent(Context context, List<DemoParent> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterParent.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcnestedmainparent, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterParent.ViewHolder holder, int position) {
        holder.tvHeader.setText(list.get(position).getHeader());
        setChildItemRecycler(holder.rcView,list.get(position).getList());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rcView;
        TextView tvHeader;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rcView = itemView.findViewById(R.id.rcParent);
            tvHeader = itemView.findViewById(R.id.tvHeader);
        }
    }
    private void setChildItemRecycler(RecyclerView recyclerView, List<DemoChild> categoryItemList){

        AdapterChild adapter = new AdapterChild(context, categoryItemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
    }
}
