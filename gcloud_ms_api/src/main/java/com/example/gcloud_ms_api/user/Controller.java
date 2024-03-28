package com.example.gcloud_ms_api.user;

import com.example.gcloud_ms_api.communication.Connect;
import com.example.gcloud_ms_innerconnect.messages.IcMessage;
import jakarta.servlet.http.HttpServletResponse;

public class Controller
{
    Connect connect = new Connect();
    Model model = new Model();

    public void GetAllAction(HttpServletResponse response)
    {
        try
        {
            // Create Message
            IcMessage message = model.GetAll();
            // Register response
            connect.Register(message.get_apiMessage(), response);
        }
        catch(Exception e)
        {
            System.out.println("Api:[Controller.GetAllAction] Error=" + e.getMessage());
            response.setStatus(500);
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
