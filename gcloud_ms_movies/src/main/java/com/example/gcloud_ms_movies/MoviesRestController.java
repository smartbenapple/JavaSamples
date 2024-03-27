package com.example.gcloud_ms_movies;

import com.example.gcloud_ms_movies.messages.IcNewMovieMessage;
import com.example.gcloud_ms_movies.messages.MoviesFirebaseMessage;
import com.example.gcloud_ms_users.user.messages.IcMessage;
import com.example.gcloud_ms_users.user.messages.IcNewUsrMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// this service -> https://gcloud-ms-movies-axxh6chama-wl.a.run.app
@RestController
public class MoviesRestController
{
    Controller ctrl = new Controller();

    @PostMapping("/movies") // in: maps path
    public String ProcessAction(@RequestBody IcMessage icMessage)
    {
        try
        {
            //ctrl.CreateMessage();
            ctrl.ProcessAction(icMessage);
        }
        catch(Exception e)
        {
            System.out.println("[ProcessAction] Error=" + e.getMessage());
            return "Post Movies Failed.";
        }
        return "Post Movies Worked.";
    }

    @PostMapping("/movieCreate") // in: maps path
    public String ProcessCreate(@RequestBody IcNewMovieMessage icNewMovieMessage) // IcNewMovieMessage icNewMovieMessage
    {
        try
        {
            ctrl.ProcessAction(icNewMovieMessage);
            //System.out.println("TEST = "+ icNewMovieMessage);
        }
        catch(Exception e)
        {
            System.out.println("[ProcessAction] Error=" + e.getMessage());
            return "Post Movies Failed.";
        }
        return "Post Movies Worked.";
    }
}
