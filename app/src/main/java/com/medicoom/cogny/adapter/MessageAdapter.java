package com.medicoom.cogny.adapter;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.medicoom.cogny.R;
import com.medicoom.cogny.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    private List<Message> mMessageList;
    private static int mType;
    private Context mContext;
    private int mLastPosition;
    private RecyclerViewAdapterListener mRecyclerViewAdapterListener;

    public MessageAdapter(List<Message> messageList, int category, RecyclerViewAdapterListener recyclerViewAdapterListener, Context context) {
        mMessageList = messageList;
        mType = category;
        mRecyclerViewAdapterListener = recyclerViewAdapterListener;
        mContext = context;
        mLastPosition = -1;
    }

    public MessageAdapter(int category, RecyclerViewAdapterListener recyclerViewAdapterListener, Context context) {
        mMessageList = new ArrayList<>();
        mType = category;
        mRecyclerViewAdapterListener = recyclerViewAdapterListener;
        mContext = context;
        mLastPosition = -1;
    }

    public void addItem(Message message) {
        mMessageList.add(message);
        notifyItemInserted(mMessageList.size() - 1);
        if (mRecyclerViewAdapterListener != null) {
            mRecyclerViewAdapterListener.onItemAdded();
        }
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        MessageViewHolder viewHolder;
        switch (viewType) {
            case Message.Type.WELCOME:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_welcome, parent, false);
                viewHolder = new MessageViewHolder(view);
                break;
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_welcome, parent, false);
                viewHolder = new MessageViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MessageAdapter.MessageViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case Message.Type.WELCOME:
                viewHolder.title.setText(mMessageList.get(position).getTitle());
                viewHolder.body.setText(mMessageList.get(position).getText());
                viewHolder.image.setVisibility(View.GONE);
                int category = Integer.parseInt(mMessageList.get(position).getCategory());
                if(category != Message.Type.WELCOME){
                    viewHolder.image.setImageResource(getWelcomeIcon(category));
                    viewHolder.image.setVisibility(View.VISIBLE);
                }
        }

        setAnimation(viewHolder.cv, position);
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return Integer.parseInt(mMessageList.get(position).getCategory());
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView title;
        TextView body;
        ImageView image;

        MessageViewHolder(View itemView) {
            super(itemView);
            if (mType == Message.Type.WELCOME) {
                cv = (CardView) itemView.findViewById(R.id.card_view);
                title = (TextView) itemView.findViewById(R.id.card_title);
                body = (TextView) itemView.findViewById(R.id.card_body);
                image = (ImageView) itemView.findViewById(R.id.card_image);
            }
        }
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > mLastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            animation.setDuration(1000);
            viewToAnimate.startAnimation(animation);
            mLastPosition = position;
        }
    }

    private int getWelcomeIcon(int itemCategory) {
        int icon = 0;
        switch (itemCategory) {
            case Message.Type.MEDICINE:
                icon = R.drawable.ic_medicine;
                break;
            case Message.Type.DIET:
                icon = R.drawable.ic_food;
                break;
            case Message.Type.CARBOHYDRATES:
                icon = R.drawable.ic_carbohydrates;
                break;
            case Message.Type.EXERCISE:
                icon = R.drawable.ic_exercise;
                break;
            case Message.Type.MOOD:
                icon = R.drawable.ic_mood;
                break;
            case Message.Type.TIPS:
                icon = R.drawable.ic_tip;
                break;
        }
        return icon;
    }
}
