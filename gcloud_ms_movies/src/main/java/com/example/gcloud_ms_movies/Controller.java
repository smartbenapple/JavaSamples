package com.example.gcloud_ms_movies;

import com.example.gcloud_ms_movies.messages.IcNewMovieMessage;
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
                GetAllAction(icMessage);
                break;
            case "create":
                System.out.println("Movies:[Controller.ProcessAction] create found.");
                CreateAction((IcNewMovieMessage) icMessage); // todo: why is IcMessage from the wire going to cast up? - test please.
                break;
            default:
                break;
        }
    }

    public void GetAllAction(IcMessage icMessage)
    {
        try
        {
            // Get movies from firebase service
            connect.GetMoviesData(icMessage);
        }
        catch(Exception e)
        {
            // return 500 error message.
            System.out.println("Movies:[Controller.getAllAction] Error.");
        }
    }

    public void CreateAction(IcNewMovieMessage icNewMovieMessage)
    {

    }
}
