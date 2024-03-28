package com.example.gcloud_ms_movies.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class IcNewMovieMessage extends IcMessage
{
    private MovieMessage _movieMessage = new MovieMessage("new?","new?");

    @JsonGetter("data")
    public MovieMessage get_movieMessage()
    {
        return _movieMessage;
    }

    @JsonGetter("title")
    public String getTitle()
    {
        return _movieMessage.get_title();
    }

    @JsonGetter("year")
    public String getYear()
    {
        return _movieMessage.get_year();
    }

    @JsonCreator
    public IcNewMovieMessage(@JsonProperty("id") String id, @JsonProperty("role") String role, @JsonProperty("cmd") String cmd, @JsonProperty("destSrv") String destSrv, @JsonProperty("title") String title, @JsonProperty("year") String year)
    {
        super("", "", "", id, role, cmd, destSrv);
        _movieMessage = new MovieMessage(title, year);
    }
}
