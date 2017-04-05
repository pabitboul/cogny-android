package com.medicoom.cogny.fragment;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.VolleyError;
import com.medicoom.cogny.R;
import com.medicoom.cogny.activity.HomeActivity;
import com.medicoom.cogny.adapter.MessageAdapter;
import com.medicoom.cogny.adapter.RecyclerViewAdapterListener;
import com.medicoom.cogny.model.Message;
import com.medicoom.cogny.model.Messages;
import com.medicoom.cogny.network.RequestListener;
import com.medicoom.cogny.network.WSManager;
import com.medicoom.cogny.view.SmoothScrollingLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * A placeholder fragment containing a simple view.
 */
public class WelcomeFragment extends Fragment implements RequestListener<Messages>, RecyclerViewAdapterListener {
    private RecyclerView rvItems;
    private SmoothScrollingLinearLayoutManager smoothScrollingLinearLayoutManager;
    private MessageAdapter mMessageAdapter;
    private Runnable mRunnable;
    private Handler mHandler;
    private List<Message> mWelcomeList;

    public WelcomeFragment() {
    }

    @Override
    public void onStop() {
        super.onStop();
        mHandler.removeCallbacks(mRunnable);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_welcome, container, false);
        rvItems = (RecyclerView) rootView.findViewById(R.id.rv_welcome);
        smoothScrollingLinearLayoutManager = new SmoothScrollingLinearLayoutManager(getActivity());
        Button startButton = (Button) rootView.findViewById(R.id.btn_start);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(getActivity(), HomeActivity.class);
                startActivity(homeIntent);
                getActivity().finish();
            }
        });
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        WSManager.getInstance().getWelcomeMessages(this, getClass().getName(), getActivity());
        mMessageAdapter = new MessageAdapter(Message.Type.WELCOME, this, getActivity());
        mHandler = new Handler();
        rvItems.setHasFixedSize(true);
        rvItems.setLayoutManager(smoothScrollingLinearLayoutManager);
        rvItems.setAdapter(mMessageAdapter);
    }

    @Override
    public void onItemAdded() {
        rvItems.smoothScrollToPosition(mMessageAdapter.getItemCount() - 1);
        if (mMessageAdapter.getItemCount() < 7) {
            mRunnable = new Runnable() {
                @Override
                public void run() {
                    mMessageAdapter.addItem(mWelcomeList.get(mMessageAdapter.getItemCount()));
                }
            };
            mHandler.postDelayed(mRunnable, 2000);
        } else {
            mHandler.removeCallbacks(mRunnable);
        }
    }

    @Override
    public void onSuccess(Messages messages) {
        mWelcomeList = messages.getMessages();
        mMessageAdapter.addItem(mWelcomeList.get(mMessageAdapter.getItemCount()));
    }

    @Override
    public void onSuccess(Messages messages, Map<String, String> headers) {

    }

    @Override
    public void onError(VolleyError error) {

    }


}