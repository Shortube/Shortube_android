package com.unithon.com.shortube;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class LeftFragment extends Fragment {

    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    RecyclerViewAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.left_fragment, container, false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_left);
        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        FloatingActionButton floatingActionButton = (FloatingActionButton)view.findViewById(R.id.floatingActionButton);


        ArrayList<Items> items= new ArrayList();
        items.add(new Items(null, null, "[4K] 내가 좋아하는 팬케이크 레시피 : My Favorite Pancake Recipe","꿀키honeykki",1));
        items.add(new Items(null, null, "title2","describe2",2));
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new RecyclerViewAdapter(items,R.layout.activity_item);
        mRecyclerView.setAdapter(mAdapter);



        final UrlDialog urlDialog = new UrlDialog(getContext());
        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        WindowManager.LayoutParams wm = urlDialog.getWindow().getAttributes();  //다이얼로그의 높이 너비 설정하기위해
        wm.copyFrom(urlDialog.getWindow().getAttributes());
        wm.width = width;  //화면 너비의 절반
        wm.height = height /3;  //화면 높이의 절반

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                urlDialog.show();

            }
        });


        return view;

    }

    public void OnClick(View view){
        switch (view.getId()){
            case R.id.floatingActionButton:

        }

    }
}
