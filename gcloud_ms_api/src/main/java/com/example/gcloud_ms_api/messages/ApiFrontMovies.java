package com.example.gcloud_ms_api.messages;

import com.example.gcloud_ms_api.utility.OMHelper;
import com.example.gcloud_ms_movies.messages.MovieMessage;
import com.example.gcloud_ms_users.user.messages.UserMessage;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiFrontMovies
{
    private String _id;
    private MovieMessage _data;
    private String _path;

    public MovieMessage get_data()
    {
        return _data;
    }

    @JsonGetter("id")
    public String get_id()
    {
        return _id;
    }

    @JsonGetter("path")
    public String get_path()
    {
        return _path;
    }

    @JsonCreator
    public ApiFrontMovies(@JsonProperty("id") String id, @JsonProperty("data_title") String title, @JsonProperty("data_year") String year, @JsonProperty("path") String path)
    {
        System.out.println("API:[ApiFrontMovies.ctr] id=" + id + ",data-title=" + title + ",data-year=" + year + ",path=" + path);

        _id = id;
        _path = path;

        try
        {
            // Pass: Tested conversion to MovieMessage
            MovieMessage movieMessage = new MovieMessage(title,year); //OMHelper.Convert(data, MovieMessage.class);
            _data = movieMessage;
        }
        catch(Exception e)
        {
            System.out.println("API:[ApiFrontMovies.ctr] Crash on conversion of 'data'; Error =" + e.getMessage());
        }
    }
}
