package com.example.gcloud_ms_movies.adapters;

import com.example.gcloud_ms_api.utility.OMHelper;
import com.example.gcloud_ms_movies.messages.IcAnsMoviesMessage;
import com.example.gcloud_ms_movies.messages.MovieMessage;
import com.example.gcloud_ms_movies.messages.MoviesFirebaseMessage;

public class Adp_MFM_ICM
{
    private IcAnsMoviesMessage _icAnsMoviesMessage;

    public Adp_MFM_ICM(MoviesFirebaseMessage moviesFirebaseMessage)
    {
        String id = moviesFirebaseMessage.get_id();
        String destSrv = moviesFirebaseMessage.get_destSrv();
        MovieMessage[] movies = moviesFirebaseMessage.Movies();
        System.out.println("Movies:[Adp_MFM_ICM] id=" + id + ",destSrv=" + destSrv + ",movies=" + OMHelper.Parse(movies));
        _icAnsMoviesMessage = new IcAnsMoviesMessage(id,destSrv,movies);
    }

    public IcAnsMoviesMessage Output()
    {
        return _icAnsMoviesMessage;
    }
}
