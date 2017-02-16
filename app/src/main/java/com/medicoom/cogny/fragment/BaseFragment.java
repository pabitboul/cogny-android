package com.medicoom.cogny.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.medicoom.cogny.R;

import static com.medicoom.cogny.adapter.SectionPagerAdapter.PageType.GYMS;
import static com.medicoom.cogny.adapter.SectionPagerAdapter.PageType.MEDICINES;


public class BaseFragment extends Fragment {

    private static final String ARG_PAGE = "page_number";
    private int mPage;
    private RecyclerView mRecyclerView;

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
        TextView textView = (TextView) rootView.findViewById(R.id.tv_title);
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.rv_assistant);

        textView.setText(getTitle());

        return rootView;
    }

    private String getTitle(){
        switch (mPage) {
            case MEDICINES:
                return getActivity().getString(R.string.meds_fragment);
            case GYMS:
                return getActivity().getString(R.string.gyms_fragment);
            default:
                return getActivity().getString(R.string.foods_fragment);
        }
    }
}
