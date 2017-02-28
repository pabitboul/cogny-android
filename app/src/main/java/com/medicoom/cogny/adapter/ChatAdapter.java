package com.medicoom.cogny.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.medicoom.cogny.R;
import com.medicoom.cogny.model.ChatItem;

import java.util.List;

import static com.medicoom.cogny.adapter.ChatAdapter.ChatViewType.ASSISTANT;
import static com.medicoom.cogny.adapter.ChatAdapter.ChatViewType.USER;


public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    private List<ChatItem> mChatItemList;
    private RecyclerViewAdapterListener mRecyclerViewAdapterListener;

    interface ChatViewType {
        int ASSISTANT = 0;
        int USER = 1;
    }

    public ChatAdapter(List<ChatItem> chatItemList) {
        mChatItemList = chatItemList;
    }

    public void addItem(ChatItem chatItem) {
        mChatItemList.add(chatItem);
        notifyItemInserted(mChatItemList.size() - 1);
        if (mRecyclerViewAdapterListener != null) {
            mRecyclerViewAdapterListener.onItemAdded();
        }
    }

    public void setRecyclerViewAdapterListener(RecyclerViewAdapterListener listener) {
        mRecyclerViewAdapterListener = listener;
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == ASSISTANT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_assistant, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_user, parent, false);
        }
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        holder.message.setText(mChatItemList.get(position).getMessage());
        holder.date.setText(mChatItemList.get(position).getDateTime());
    }

    @Override
    public int getItemViewType(int position) {
        ChatItem chatItem = mChatItemList.get(position);
        if (!chatItem.isAssistant()) {
            return USER;
        }
        return ASSISTANT;
    }


    @Override
    public int getItemCount() {
        return mChatItemList.size();
    }

    static class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView message;
        TextView date;

        ChatViewHolder(View itemView) {
            super(itemView);
            message = (TextView) itemView.findViewById(R.id.txt_message);
            date = (TextView) itemView.findViewById(R.id.txt_date);
        }

    }
}
