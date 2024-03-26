package com.example.gcloud_ms_movies;

import com.example.gcloud_ms_movies.messages.MoviesFirebaseMessage;
import com.example.gcloud_ms_users.user.messages.IcMessage;

public class Controller
{
    Connect connect = new Connect();

    public void ProcessAction(IcMessage icMessage)
    {
        System.out.println("Movies:[Controller.ProcessAction] Triggered. Host=" + icMessage.get_host());

        String cmd = icMessage.get_apiMessage().get_cmd();
        switch (cmd)
        {
            case "getAll":
                System.out.println("Movies:[Controller.ProcessAction] getAll found.");
                getAllAction(icMessage);
                break;
            case "create":
                System.out.println("Movies:[Controller.ProcessAction] create found.");
                //CreateAction((IcNewUsrMessage) icMessage);
                break;
            default:
                break;
        }
    }

    public void getAllAction(IcMessage icMessage)
    {
        try
        {
            // Get movies from firebase service
            MoviesFirebaseMessage data = connect.GetMoviesData(icMessage);
            // send movies results to innerconnect.
            connect.SendMovies(data);
        }
        catch(Exception e)
        {
            // return 500 error message.
            System.out.println("Movies:[Controller.getAllAction] Error.");
        }
    }
}
