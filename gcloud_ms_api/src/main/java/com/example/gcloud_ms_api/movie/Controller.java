package com.example.gcloud_ms_api.movie;

import com.example.gcloud_ms_api.communication.Connect;
import com.example.gcloud_ms_api.messages.ApiFrontMovies;
import com.example.gcloud_ms_api.movie.Model;
import com.example.gcloud_ms_innerconnect.messages.IcMessage;
import jakarta.servlet.ServletResponse;

public class Controller
{
    Connect connect = Connect.GetSingleton();
    Model model = new Model();

    public void GetAllAction(ServletResponse response, ApiFrontMovies movies) // HttpServletResponse response; ServletResponse; Response
    {
        try
        {
            // Java: Use Consumer as function callback
            /*Consumer<String> work = (name) ->
            {
                IcMessage message = model.GetAll();
                // Register response
                connect.Register(message.get_apiMessage(), response);
            };

            // Create Message
            ThreadHelper.Async(work, null);*/

            IcMessage message = model.GetAll(movies);

            // Register response
            //connect.Register(message.get_apiMessage(), response);
        }
        catch(Exception e)
        {
            System.out.println("Api:[Controller.GetAllAction] Error=" + e.getMessage());
            //response.setStatus(500);
        }
    }

    public void CreateAction()
    {
        try
        {

        }
        catch(Exception e)
        {
            System.out.println("Api:[Controller.CreateAction]");
        }
    }
}
