package com.mindjet.mygallery.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mindjet.mygallery.Bean.MovieInfo;
import com.mindjet.mygallery.MainActivity;
import com.mindjet.mygallery.R;

import java.util.List;

/**
 * Created by mindjet on 01/11/2016.
 */

public class FaceAdapter extends RecyclerView.Adapter<FaceAdapter.FaceViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    private LayoutInflater mInflater;
    private List<MovieInfo> mMovieInfoList;
    private Context mContext;
    private MainActivity.recyclerViewOnClickListener mListener;

    public FaceAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public void setListener(MainActivity.recyclerViewOnClickListener listener) {
        mListener = listener;
    }

    public void setMovieInfoList(List<MovieInfo> movieInfoList) {
        mMovieInfoList = movieInfoList;
        notifyItemRangeInserted(0, movieInfoList.size());
    }

    @Override
    public FaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FaceViewHolder(mInflater.inflate(R.layout.item_face, parent, false));
    }

    @Override
    public void onBindViewHolder(FaceViewHolder holder, int position) {

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);
        Glide.with(mContext)
                .load(mMovieInfoList.get(position).url.medium)
                .centerCrop()
                .animate(R.anim.anim_zoom)
                .thumbnail(0.1f)
                .into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mMovieInfoList == null ? 0 : mMovieInfoList.size();
    }

    @Override
    public void onClick(View v) {
        if (mListener != null)
            mListener.onClick((Integer) v.getTag());
    }

    @Override
    public boolean onLongClick(View v) {
        if (mListener != null)
            mListener.onLongClick((Integer) v.getTag());
        return false;
    }

    public class FaceViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;

        public FaceViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_face);
        }
    }
}
