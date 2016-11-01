package com.mindjet.mygallery;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mindjet.mygallery.Bean.MovieInfo;

import java.util.List;

/**
 * Created by mindjet on 01/11/2016.
 */

public class ViewPagerFragment extends DialogFragment {

    private ViewPager mViewPager;
    private TextView mTimeStamp, mIndex;
    private List<MovieInfo> mMovieInfoList;
    private int mCurrentPos;

    public static ViewPagerFragment newInstance() {
        ViewPagerFragment fragment = new ViewPagerFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fullscreen, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_fullscreen);
        mTimeStamp = (TextView) view.findViewById(R.id.tv_time);
        mIndex = (TextView) view.findViewById(R.id.tv_index);

        //get the data passed from the MainActivity.
        mMovieInfoList = (List<MovieInfo>) getArguments().getSerializable("list");
        mCurrentPos = getArguments().getInt("position");

        //attach adapter.
        ViewPagerAdapter adapter = new ViewPagerAdapter();
        adapter.setMovieInfoList(mMovieInfoList);
        mViewPager.setAdapter(adapter);

        //attach listener
        mViewPager.addOnPageChangeListener(new ViewPagerListener());

        //show the item that is clicked instead of showing the first item by default.
        setTheCurrentItem(mCurrentPos);

        return view;
    }

    private void setTheCurrentItem(int currentPos) {
        mIndex.setText(currentPos + "/" + mMovieInfoList.size());
        mTimeStamp.setText(mMovieInfoList.get(currentPos).timeStamp);
        mViewPager.setCurrentItem(currentPos);
    }

    //adapter
    private class ViewPagerAdapter extends PagerAdapter {

        private List<MovieInfo> mMovieInfoList;
        private LayoutInflater mInflater;

        void setMovieInfoList(List<MovieInfo> movieInfoList) {
            mMovieInfoList = movieInfoList;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            mInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = mInflater.inflate(R.layout.fragment_fullscreen_page, container, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_fullscreen);

            Glide.with(getActivity())
                    .load(mMovieInfoList.get(position).url.large)
                    .thumbnail(0.1f)
                    .into(imageView);

            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return mMovieInfoList == null ? 0 : mMovieInfoList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((View) object);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    private class ViewPagerListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setTheCurrentItem(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

}
