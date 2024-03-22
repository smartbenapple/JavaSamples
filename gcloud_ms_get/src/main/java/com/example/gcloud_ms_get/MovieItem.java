package com.example.gcloud_ms_get;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

// Using @JsonCreator, @JsonProperty("name") during deserialization
// https://www.baeldung.com/jackson-annotations#bd-jackson-deserialization-annotations
public class MovieItem
{
    private String _title;

    private String _year;

    /*public void set_title(String title)
    {
        _title = title;
    }

    public void set_year(String year)
    {
        _year = year;
    }*/

    @JsonGetter("Title")
    public String get_title()
    {
        return _title;
    }

    @JsonGetter("Year")
    public String get_year()
    {
        return _year;
    }

    @JsonCreator
    public MovieItem(@JsonProperty("Title") String title, @JsonProperty("Year") String year)
    {
        _title = title;
        _year = year;
    }
}
