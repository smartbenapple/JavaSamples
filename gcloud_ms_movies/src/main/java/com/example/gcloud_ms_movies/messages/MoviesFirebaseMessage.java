package com.example.gcloud_ms_movies.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MoviesFirebaseMessage
{
    private String _id;
    private String _destSrv;

    private MovieMessage[] _movies;

    @JsonGetter("id")
    public String get_id()
    {
        return _id;
    }

    @JsonGetter("destSrv")
    public String get_destSrv()
    {
        return _destSrv;
    }

    @JsonGetter("data")
    public MovieMessage[] Movies()
    {
        return _movies;
    }

    @JsonCreator
    MoviesFirebaseMessage(@JsonProperty("id") String id, @JsonProperty("destSrv") String destSrv, @JsonProperty("data") MovieMessage[] data)
    {
        _id = id;
        _destSrv = destSrv;
        _movies = data;
    }
}
