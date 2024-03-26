package com.example.gcloud_ms_movies;

import com.example.gcloud_ms_movies.messages.MoviesFirebaseMessage;
import com.example.gcloud_ms_users.user.messages.IcMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoviesRestController
{
    Controller ctrl = new Controller();

    @PostMapping("/movie") // in: maps path
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
            return "Post Users Failed.";
        }
        return "Post Users Worked.";
    }
}
