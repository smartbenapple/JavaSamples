package com.example.gcloud_ms_api.user;

import com.example.gcloud_ms_api.communication.Connect;

public class Controller
{
    Connect connect = new Connect();
    Model model = new Model();

    public void GetAllAction()
    {
        try
        {
            // todo: message
            model.GetAll();
        }
        catch(Exception e)
        {
            System.out.println("Api:[Controller.GetAllAction]");
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
