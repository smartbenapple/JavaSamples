package com.example.gcloud_ms_movies.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieMessage
{
    private String _title;
    private String _year;

    @JsonGetter("title")
    public String get_title()
    {
        return _title;
    }

    @JsonGetter("year")
    public String get_year()
    {
        return _year;
    }

    // Firebase returning as uppercase names
    @JsonCreator
    public MovieMessage(@JsonProperty("Title") String title, @JsonProperty("Year") String year)
    {
        _title = title;
        _year = year;
    }
}
