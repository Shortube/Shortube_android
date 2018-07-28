package com.unithon.com.shortube;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class LikeListAdapter extends RecyclerView.Adapter<LikeListAdapter.LikeViewHolder> {
    private TextView likeItemView;
    private WordViewModel mWordViewModel;
    class LikeViewHolder extends RecyclerView.ViewHolder {


        private LikeViewHolder(View itemView) {
            super(itemView);
            likeItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<LikeVideo> mLikes; // Cached copy of words

    LikeListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public LikeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);

        return new LikeViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(LikeViewHolder holder, int position) {
        LikeVideo current = mLikes.get(position);
        likeItemView.setText(current.getLike());
    }

    void setLikes(List<LikeVideo> likes){
        mLikes = likes;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mLikes != null)
            return mLikes.size();
        else return 0;
    }
}
