package com.example.gcloud_ms_api.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiFrontMovies
{
    private String _title;
    private String _year;

    public String get_title()
    {
        return _title;
    }

    public String get_year()
    {
        return _year;
    }

    @JsonCreator
    public ApiFrontMovies(@JsonProperty("title") String title, @JsonProperty("year") String year)
    {
        _title = title;
        _year = year;
    }
}
