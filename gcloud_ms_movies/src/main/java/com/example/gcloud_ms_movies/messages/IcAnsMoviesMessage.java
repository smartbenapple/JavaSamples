package com.example.gcloud_ms_movies.messages;

import com.example.gcloud_ms_innerconnect.messages.IcMessage;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class IcAnsMoviesMessage extends IcMessage
{
    private MovieMessage[] _movies;

    @JsonGetter("data")
    public MovieMessage[] get_movies()
    {
        return _movies;
    }

    public IcAnsMoviesMessage(@JsonProperty("id") String id, @JsonProperty("destSrv") String destSrv, @JsonProperty("data") MovieMessage[] movies)
    {
        super("", "", "", id, "", "", destSrv,""); // todo: set base data to movies?
        _movies = movies;
    }
}
