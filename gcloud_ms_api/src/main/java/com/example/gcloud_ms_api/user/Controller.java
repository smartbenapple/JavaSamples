package com.example.gcloud_ms_api.user;

import com.example.gcloud_ms_api.communication.Connect;
import com.example.gcloud_ms_api.messages.ApiFrontUsers;
import com.example.gcloud_ms_innerconnect.messages.IcMessage;
import jakarta.servlet.ServletResponse;
import org.apache.coyote.Response;
import jakarta.servlet.http.HttpServletResponse;

import java.util.function.Consumer;

// Java: How to pass function callbacks to a method; Consumer<T>
// https://www.baeldung.com/java-callback-functions
// https://www.geeksforgeeks.org/java-8-consumer-interface-in-java-with-examples/
public class Controller
{
    Connect connect = Connect.GetSingleton();
    Model model = new Model();

    public void GetAllAction(ServletResponse response, ApiFrontUsers users) // HttpServletResponse response; ServletResponse; Response
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

            IcMessage message = model.GetAll(users);

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
