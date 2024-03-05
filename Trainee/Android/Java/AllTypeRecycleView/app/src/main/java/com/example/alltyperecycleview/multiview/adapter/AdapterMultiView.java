package com.example.alltyperecycleview.multiview.adapter;

import static com.example.alltyperecycleview.multiview.demo.MultiView.LayoutOne;
import static com.example.alltyperecycleview.multiview.demo.MultiView.LayoutTwo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alltyperecycleview.R;
import com.example.alltyperecycleview.multiview.demo.MultiView;

import java.util.List;

public class AdapterMultiView extends RecyclerView.Adapter {
    List<MultiView> list;

    public AdapterMultiView(List<MultiView> list) {
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        switch (list.get(position).getViewType()) {
            case 0:
                return LayoutOne;
            case 1:
                return LayoutTwo;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case LayoutOne:
                View layoutOne = LayoutInflater.from(parent.getContext()).inflate(R.layout.multiview_layout_one, parent, false);
                return new LayoutOneViewHolder(layoutOne);
            case LayoutTwo:
                View layoutTwo = LayoutInflater.from(parent.getContext()).inflate(R.layout.multiview_layout_two, parent, false);
                return new LayoutTwoViewHolder(layoutTwo);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (list.get(position).getViewType()) {
            case LayoutOne:
                String text = list.get(position).getText();
                ((LayoutOneViewHolder)holder).setView(text);
                break;

            case LayoutTwo:
                String text_one = list.get(position).getText_one();
                String text_two = list.get(position).getText_two();
                ((LayoutTwoViewHolder)holder).setViews( text_one, text_two);
                break;
            default:
                return;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class LayoutOneViewHolder extends RecyclerView.ViewHolder {
        private final TextView textview;
        public LayoutOneViewHolder(@NonNull View itemView)
        {
            super(itemView);

            textview = itemView.findViewById(R.id.tvHeader);
        }
        private void setView(String text)
        {
            textview.setText(text);
        }
    }

    static class LayoutTwoViewHolder extends RecyclerView.ViewHolder {
        private final TextView text_one;
        private final TextView text_two;

        public LayoutTwoViewHolder(@NonNull View itemView)
        {
            super(itemView);
            text_one = itemView.findViewById(R.id.tvText);
            text_two = itemView.findViewById(R.id.tvItemName);
        }

        private void setViews(String textOne, String textTwo)
        {
            text_one.setText(textOne);
            text_two.setText(textTwo);
        }
    }
}
