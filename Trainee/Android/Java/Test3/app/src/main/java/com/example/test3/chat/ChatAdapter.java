package com.example.test3.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test3.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;
import java.util.Objects;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_SEND = 1;
    private static final int VIEW_TYPE_RECEIVE = 2;

    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private final List<ChatDemo> itemClassList;

    public ChatAdapter(List<ChatDemo> itemClassList) {
        this.itemClassList = itemClassList;
    }

    @Override
    public int getItemViewType(int position) {
        String currentUserUid = mAuth.getCurrentUser() != null ? mAuth.getCurrentUser().getUid() : "";
        String viewType = itemClassList.get(position).getViewType();

        if (Objects.equals(viewType, currentUserUid)) {
            return VIEW_TYPE_SEND;
        } else {
            return VIEW_TYPE_RECEIVE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_SEND) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_send, parent, false);
            return new SendViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_receive, parent, false);
            return new ReceiveViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatDemo chatDemo = itemClassList.get(position);
        if (holder instanceof SendViewHolder) {
            ((SendViewHolder) holder).bind(chatDemo);
        } else if (holder instanceof ReceiveViewHolder) {
            ((ReceiveViewHolder) holder).bind(chatDemo);
        }
    }

    @Override
    public int getItemCount() {
        return itemClassList.size();
    }

    static class SendViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        SendViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvSend);
        }

        void bind(ChatDemo chatDemo) {
            textView.setText(chatDemo.getMsg());
        }
    }

    static class ReceiveViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        ReceiveViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvReceive);
        }

        void bind(ChatDemo chatDemo) {
            textView.setText(chatDemo.getMsg());
        }
    }
}
