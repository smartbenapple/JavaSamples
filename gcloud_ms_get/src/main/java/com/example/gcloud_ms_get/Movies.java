package com.example.gcloud_ms_get;

public class Movies
{
    private MovieItem[] message;

    public void setMessage(MovieItem[] movies)
    {
        message = movies;
    }

    public MovieItem[] GetMovies()
    {
        return message;
    }
}
