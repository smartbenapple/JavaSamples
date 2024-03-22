package com.example.gcloud_ms_users.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.gcloud_ms_users.user.Connect;

// System.out.println() => https://www.baeldung.com/java-system-out-println-vs-loggers
public class Controller
{
    Connect connect = new Connect();

    public void CreateMessage()
    {
        // Note: Intentionally skipped - this originally created the innerConnectMessage in NodeJs version.
        //       The Java version creates the class during deserialization process.
    }

    public void ProcessAction(InnerConnectMessage innerConnectMessage)
    {
        System.out.println("Users:[Controller.ProcessAction] Triggered. Host=" + innerConnectMessage.get_host());

        String cmd = innerConnectMessage.get_apiMessage().get_cmd();
        switch (cmd)
        {
            case "getAll":
                System.out.println("Users:[Controller.ProcessAction] getAll found.");
                getAllAction(innerConnectMessage);
                break;
            case "create":
                System.out.println("Users:[Controller.ProcessAction] create found.");
                CreateAction(innerConnectMessage);
                break;
            default:
                break;
        }
    }

    public void getAllAction(InnerConnectMessage innerConnectMessage)
    {
        // todo: get users data from the NodeJs endpoint
        // todo: requires a userModel class.
        connect.SendData(innerConnectMessage);
    }

    public void CreateAction(InnerConnectMessage innerConnectMessage)
    {
        // todo: create new user data from the NodeJs endpoint.
        connect.SendData(innerConnectMessage);
    }
}
