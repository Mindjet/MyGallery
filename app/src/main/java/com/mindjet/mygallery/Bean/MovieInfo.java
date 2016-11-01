package com.mindjet.mygallery.Bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mindjet on 01/11/2016.
 */

public class MovieInfo {

    public String name;
    public Url url;

    @SerializedName("timestamp")
    public String timeStamp;

}
