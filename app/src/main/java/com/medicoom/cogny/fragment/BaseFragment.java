package com.medicoom.cogny.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.medicoom.cogny.R;
import com.medicoom.cogny.adapter.ChatAdapter;
import com.medicoom.cogny.adapter.RecyclerViewAdapterListener;
import com.medicoom.cogny.model.ChatItem;
import com.medicoom.cogny.view.SmoothScrollingLinearLayoutManager;

import java.util.ArrayList;

import static com.medicoom.cogny.fragment.BaseFragment.chatType.ASSISTANT;
import static com.medicoom.cogny.fragment.BaseFragment.chatType.USER;

public class BaseFragment extends Fragment implements RecyclerViewAdapterListener {

    private static final String ARG_PAGE = "page_number";
    private int mPage;
    private RecyclerView rvChatItems;
    private SmoothScrollingLinearLayoutManager smoothScrollingLinearLayoutManager;
    private ChatAdapter mChatAdapter;
    private int mSequence = 0;

    @Override
    public void onItemAdded() {
        rvChatItems.smoothScrollToPosition(mChatAdapter.getItemCount() - 1);
    }

    interface chatType {
        int ASSISTANT = 0;
        int USER = 1;
    }

    public BaseFragment() {
        // Required empty public constructor
    }

    public static BaseFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        BaseFragment fragment = new BaseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_base, container, false);
        rvChatItems = (RecyclerView) rootView.findViewById(R.id.rv_assistant);
        smoothScrollingLinearLayoutManager = new SmoothScrollingLinearLayoutManager(getActivity());
        Button continueButton = (Button) rootView.findViewById(R.id.btn_continue);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSequence % 2 == 0) {
                    mChatAdapter.addItem(createChatItem(USER, getString(R.string.user_answer_negative)));
                    mChatAdapter.addItem(createChatItem(ASSISTANT, getString(R.string.assistant_message_default)));
                } else {
                    mChatAdapter.addItem(createChatItem(ASSISTANT, getString(R.string.assistant_question_one)));
                }

                mSequence++;
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mChatAdapter = new ChatAdapter(new ArrayList<ChatItem>());
        mChatAdapter.setRecyclerViewAdapterListener(this);
        rvChatItems.setLayoutManager(smoothScrollingLinearLayoutManager);
        rvChatItems.setAdapter(mChatAdapter);
        initChat();
    }

    private void initChat() {
        mChatAdapter.addItem(createChatItem(ASSISTANT, getString(R.string.assistant_message_greeting)));
        mChatAdapter.addItem(createChatItem(ASSISTANT, getString(R.string.assistant_question_one)));
    }

    private ChatItem createChatItem(int type, String message) {

        ChatItem chatItem = new ChatItem();
        chatItem.setAssistant(type == ASSISTANT);
        chatItem.setMessage(message);
        chatItem.setDateTime("12:34 PM");

        return chatItem;

    }


}
