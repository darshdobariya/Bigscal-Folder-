package com.example.peighteen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peighteen.R;

import java.util.List;

public class ThoughtsAdapter extends RecyclerView.Adapter<ThoughtsAdapter.ThoughtViewHolder> {

    private List<String> thoughts;

    public ThoughtsAdapter(List<String> thoughts) {
        this.thoughts = thoughts;
    }

    @NonNull
    @Override
    public ThoughtViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thought, parent, false);
        return new ThoughtViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThoughtViewHolder holder, int position) {
        holder.bind(thoughts.get(position));
    }

    @Override
    public int getItemCount() {
        return thoughts.size();
    }

    static class ThoughtViewHolder extends RecyclerView.ViewHolder {

        private TextView thoughtTextView;

        public ThoughtViewHolder(@NonNull View itemView) {
            super(itemView);
            thoughtTextView = itemView.findViewById(R.id.tvThoughts);
        }

        public void bind(String thought) {
            thoughtTextView.setText(thought);
        }
    }
}