package com.example.gcloud_ms_movies.adapters;

import com.example.gcloud_ms_movies.messages.IcAnsMoviesMessage;
import com.example.gcloud_ms_movies.messages.MoviesFirebaseMessage;

public class Adp_MFM_ICM
{
    private IcAnsMoviesMessage _icAnsMoviesMessage;

    public Adp_MFM_ICM(MoviesFirebaseMessage moviesFirebaseMessage)
    {
        _icAnsMoviesMessage = new IcAnsMoviesMessage(moviesFirebaseMessage.get_id(),moviesFirebaseMessage.get_destSrv(),moviesFirebaseMessage.Movies());
    }

    public IcAnsMoviesMessage Output()
    {
        return _icAnsMoviesMessage;
    }
}
