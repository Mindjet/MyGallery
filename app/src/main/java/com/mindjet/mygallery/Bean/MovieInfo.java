package com.mindjet.mygallery.Bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mindjet on 01/11/2016.
 */

public class MovieInfo implements Serializable {

    public String name;
    public Url url;

    @SerializedName("timestamp")
    public String timeStamp;

}
