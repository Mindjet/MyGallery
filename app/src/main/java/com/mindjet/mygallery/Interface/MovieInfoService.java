package com.mindjet.mygallery.Interface;

import com.mindjet.mygallery.Bean.MovieInfo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by mindjet on 01/11/2016.
 */

public interface MovieInfoService {

    @GET
    Call<ArrayList<MovieInfo>> movieInfoList(
            @Url String url
    );

}
