package com.unithon.com.shortube;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class RightFragment extends Fragment {
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    RecyclerViewAdapter mAdapter;
    RecyclerViewAdapter bottom_Adapter;

    RecyclerView mHorizonRecycler;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.right_fragment, container, false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycle_right);
        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mHorizonRecycler = (RecyclerView)view.findViewById(R.id.recycle_buttom);
        mHorizonRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
        mHorizonRecycler.setItemAnimator(new DefaultItemAnimator());


        ArrayList<Items> items= new ArrayList();

        for(int i= 0; i < 5; i++) {
            items.add(new Items(null, null, "[4K] 내가 좋아하는 팬케이크 레시피 : My Favorite Pancake Recipe", "꿀키honeykki", 1));
        }
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new RecyclerViewAdapter(items,R.layout.activity_item_right);
        bottom_Adapter = new RecyclerViewAdapter(items, R.layout.activity_item_buttom);
        mRecyclerView.setAdapter(mAdapter);
        mHorizonRecycler.setAdapter(bottom_Adapter);

        return view;
    }
}
