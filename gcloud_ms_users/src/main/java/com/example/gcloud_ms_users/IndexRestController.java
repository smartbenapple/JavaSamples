package com.example.gcloud_ms_users;

import com.example.gcloud_ms_users.user.Controller;
import com.example.gcloud_ms_users.user.InnerConnectMessage;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// Port=8080 - set in application.properties.
// try-catch => https://www.w3schools.com/java/java_try_catch.asp
// Use for testing => '{"Host":"localhost","Port":"8080","Path":"/users","Id":"99","role":"User","Cmd":"getAll","DestSrv":"UserSrv"}'
@RestController
public class IndexRestController
{
    Controller ctrl = new Controller();

    @PostMapping("/users") // in: maps path
    public String ProcessAction(@RequestBody InnerConnectMessage innerConnectMessage)
    {
        try
        {
            //ctrl.CreateMessage();
            ctrl.ProcessAction(innerConnectMessage);
        }
        catch(Exception e)
        {
            System.out.println("[ProcessAction] Error=" + e.getMessage());
            return "Post Users Failed.";
        }
        return "Post Users Worked.";
    }
}
