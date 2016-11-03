package com.mindjet.mygallery;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mindjet.mygallery.Adapter.FaceAdapter;
import com.mindjet.mygallery.Bean.MovieInfo;
import com.mindjet.mygallery.Interface.MovieInfoService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private FaceAdapter mFaceAdapter;
    private Retrofit mRetrofit;
    private MovieInfoService mMovieInfoService;
    private ArrayList<MovieInfo> mMovieInfoList;

    private final String URL = "http://api.androidhive.info/json/glide.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovieInfoList = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_gallery);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mFaceAdapter = new FaceAdapter(this);
        mRecyclerView.setAdapter(mFaceAdapter);

        mFaceAdapter.setListener(new recyclerViewOnClickListener() {
            @Override
            public void onClick(int position) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("list", mMovieInfoList);
                bundle.putInt("position", position);

                //launch the fragment
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                ViewPagerFragment viewPagerFragment = ViewPagerFragment.newInstance();
                viewPagerFragment.setArguments(bundle);
                viewPagerFragment.show(fragmentTransaction, "SLIDE_SHOW");

            }

            @Override
            public void onLongClick(int position) {

            }
        });

        fetchMovieInfo(URL);

    }

    private void fetchMovieInfo(String url) {

        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.github.com/")         //It is required but will be ignored if the call is given absolute path
                .build();
        mMovieInfoService = mRetrofit.create(MovieInfoService.class);

        Call<ArrayList<MovieInfo>> movieInfoCall = mMovieInfoService.movieInfoList(url);
        movieInfoCall.enqueue(new Callback<ArrayList<MovieInfo>>() {
            @Override
            public void onResponse(Call<ArrayList<MovieInfo>> call, Response<ArrayList<MovieInfo>> response) {
                if (response.isSuccessful()) {
                    mMovieInfoList = response.body();
                    mFaceAdapter.setMovieInfoList(mMovieInfoList);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<MovieInfo>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public interface recyclerViewOnClickListener {

        void onClick(int position);

        void onLongClick(int position);

    }

}
