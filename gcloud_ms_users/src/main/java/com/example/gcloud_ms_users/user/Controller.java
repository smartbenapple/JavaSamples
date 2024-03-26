package com.example.gcloud_ms_users.user;

import com.example.gcloud_ms_users.user.messages.IcMessage;

// System.out.println() => https://www.baeldung.com/java-system-out-println-vs-loggers
public class Controller
{
    Connect connect = new Connect();

    public void CreateMessage()
    {
        // Note: Intentionally skipped - this originally created the innerConnectMessage in NodeJs version.
        //       The Java version creates the class during deserialization process.
    }

    public void ProcessAction(IcMessage icMessage)
    {
        System.out.println("Users:[Controller.ProcessAction] Triggered. Host=" + icMessage.get_host());

        String cmd = icMessage.get_apiMessage().get_cmd();
        switch (cmd)
        {
            case "getAll":
                System.out.println("Users:[Controller.ProcessAction] getAll found.");
                getAllAction(icMessage);
                break;
            case "create":
                System.out.println("Users:[Controller.ProcessAction] create found.");
                CreateAction(icMessage);
                break;
            default:
                break;
        }
    }

    public void getAllAction(IcMessage icMessage)
    {
        // todo: get users data from the NodeJs endpoint
        // todo: requires a userModel class?
        // '{"Host":"localhost","Port":"8080","Path":"/users","Id":"99","Role":"user","Cmd":"getAll","DestSrv":"UserSrv"}'
        // Talking to firebase requires 'Cmd' portion.
        connect.GetUsersData(icMessage);
    }

    public void CreateAction(IcMessage icMessage)
    {
        // todo: create new user data from the NodeJs endpoint.
        // '{"Host":"localhost","Port":"8080","Path":"/users","Id":"99","Role":"user","Cmd":"getAll","DestSrv":"UserSrv"}'
        //connect.SendData(innerConnectMessage);
    }
}
