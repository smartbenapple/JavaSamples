package com.example.gcloud_ms_api.messages;

import com.example.gcloud_ms_api.utility.OMHelper;
import com.example.gcloud_ms_movies.messages.MovieMessage;
import com.example.gcloud_ms_users.user.messages.UserMessage;
import com.fasterxml.jackson.annotation.JsonCreator;
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

    public String get_id()
    {
        return _id;
    }

    public String get_path()
    {
        return _path;
    }

    @JsonCreator
    public ApiFrontMovies(@JsonProperty("id") String id, @JsonProperty("data") String data, @JsonProperty("path") String path)
    {
        _id = id;
        _path = path;

        try
        {
            // Pass: Tested conversion to MovieMessage
            MovieMessage movieMessage = OMHelper.Convert(data, MovieMessage.class);
            _data = movieMessage;
        }
        catch(Exception e)
        {
            System.out.println("API[ApiFrontMovies.ctr] Crash on conversion of 'data'; Error =" + e.getMessage());
        }
    }
}
