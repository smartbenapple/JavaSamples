package com.example.gcloud_ms_api.movie;

import com.example.gcloud_ms_api.communication.Connect;
import com.example.gcloud_ms_api.messages.ApiFrontMovies;
import com.example.gcloud_ms_api.messages.apiMessage;
import com.example.gcloud_ms_api.utility.OMHelper;
import com.example.gcloud_ms_innerconnect.messages.IcMessage;
import com.example.gcloud_ms_movies.messages.IcNewMovieMessage;
import com.example.gcloud_ms_users.user.messages.IcNewUsrMessage;

public class Model
{
    Connect connect = Connect.GetSingleton();

    public Model() {}

    public IcMessage GetAll(ApiFrontMovies movies)
    {
        // Note: Set id as blank to trigger automatic UUID internally.
        apiMessage apiMessage = new apiMessage(movies.get_id(),"user","getAll","MovieSrv", "");
        IcMessage message = new IcMessage(apiMessage);

        String output = OMHelper.Parse(message);
        System.out.println("API[User:Model.GetAll] message=" + output);

        // Send to innerconnect service
        connect.SendData(message, "sendme");

        return message;
    }

    public IcMessage Create(ApiFrontMovies movies)
    {
        // Note: Set id as blank to trigger automatic UUID internally.
        //String data = OMHelper.Parse(movies.get_data());
        //apiMessage apiMessage = new apiMessage(movies.get_id(),"user","create","MovieSrv", data);
        //IcMessage message = new IcMessage(apiMessage);

        // Create the IcNewUserMessage
        String title = movies.get_data().get_title();
        String year = movies.get_data().get_year();
        System.out.println("API:[User:Model.Create] new-item=" + "title=" + title + ",year=" + year);
        IcNewMovieMessage message = new IcNewMovieMessage(movies.get_id(),"user","create","MovieSrv",title,year);

        String output = OMHelper.Parse(message);
        System.out.println("API[User:Model.Create] message=" + output);

        // Send to innerconnect service
        connect.SendData(message, "sendmeNewmovie");

        return message;
    }
}
