package com.xinlan.recyclerdecor;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panyi on 2017/10/13.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.TextVH> {
    public static final Integer CHANGE_ACTION_CLICKED = 1;

    private final List<String> mMessages;

    public Adapter(List<String> msgs) {
        mMessages = new ArrayList<>(msgs);
    }

    @Override
    public TextVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TextVH(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.ui_item, parent, false),
                this);
    }

    @Override
    public void onBindViewHolder(TextVH holder, int position) {
        holder.bind(mMessages.get(position));
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    public static class TextVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final Adapter mAdapter;
        final TextView mTv;
        final View vBgLike;
        final ImageView ivLike;

        public TextVH(View itemView, Adapter adapter) {
            super(itemView);
            mAdapter = adapter;
            mTv = itemView.findViewById(R.id.mTv);
            vBgLike = itemView.findViewById(R.id.vBgLike);
            ivLike = itemView.findViewById(R.id.ivLike);
            mTv.setOnClickListener(this);
        }

        void bind(String text) {
            mTv.setText(text);
        }

        @Override
        public void onClick(View v) {
            mAdapter.notifyItemChanged(getAdapterPosition(), Adapter.CHANGE_ACTION_CLICKED);
        }
    }//end inner class

}//end class
