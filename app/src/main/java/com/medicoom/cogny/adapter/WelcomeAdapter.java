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
import com.medicoom.cogny.model.WelcomeItem;

import java.util.ArrayList;
import java.util.List;

public class WelcomeAdapter extends RecyclerView.Adapter<WelcomeAdapter.WelcomeViewHolder> {

    private List<WelcomeItem> mWelcomeItemList;
    private Context mContext;
    private int mLastPosition;
    private WelcomeAdapterListener mListener;

    public interface WelcomeAdapterListener{
        void onItemAdded();
    }

    @Override
    public WelcomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.welcome_view_item, parent, false);
        WelcomeViewHolder viewHolder = new WelcomeViewHolder(view);
        return viewHolder;
    }

    public WelcomeAdapter(List<WelcomeItem> list, Context context, WelcomeAdapterListener listener) {
        mWelcomeItemList = list;
        mContext = context;
        mListener = listener;
        mLastPosition = -1;
    }

    public WelcomeAdapter(WelcomeAdapterListener listener, Context context) {
        mWelcomeItemList = new ArrayList<>();
        mContext = context;
        mListener = listener;
        mLastPosition = -1;
    }

    public void addItem(WelcomeItem welcomeItem) {
        mWelcomeItemList.add(welcomeItem);
        notifyDataSetChanged();
        mListener.onItemAdded();
    }

    @Override
    public void onBindViewHolder(WelcomeViewHolder viewHolder, int position) {
        viewHolder.title.setText(mWelcomeItemList.get(position).getTitle());
        viewHolder.body.setText(mWelcomeItemList.get(position).getBody());
        viewHolder.image.setImageResource(mWelcomeItemList.get(position).getIcon());

        setAnimation(viewHolder.cv, position);
    }

    @Override
    public int getItemCount() {
        return mWelcomeItemList.size();
    }

    public static class WelcomeViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView title;
        TextView body;
        ImageView image;

        WelcomeViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.card_view);
            title = (TextView) itemView.findViewById(R.id.card_title);
            body = (TextView) itemView.findViewById(R.id.card_body);
            image = (ImageView) itemView.findViewById(R.id.card_image);
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
}