package com.example.gcloud_ms_users;

import com.example.gcloud_ms_users.user.Controller;

import com.example.gcloud_ms_innerconnect.messages.IcMessage;
import com.example.gcloud_ms_users.user.messages.IcNewUsrMessage;
import org.springframework.web.bind.annotation.*;

// gCloud => https://gcloud-ms-users-axxh6chama-wl.a.run.app
// locally: server.port=${port:8082}
// try-catch => https://www.w3schools.com/java/java_try_catch.asp
// Cors => https://spring.io/guides/gs/rest-service-cors
//    Use:  @CrossOrigin(origins = "*", allowedHeaders = "Origin, X-Requested-With, Content-Type, Accept",methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS})
// Use for testing => '{"host":"localhost","port":"8080","path":"/users","id":"99","role":"user","cmd":"getAll","destSrv":"UserSrv"}'
@RestController
public class IndexRestController
{
    Controller ctrl = new Controller();

    @GetMapping("/talktome")
    public String TalkToMeTest()
    {
        return "Hello Civilian...";
    }

    @CrossOrigin(origins = "*", allowedHeaders = "Origin, X-Requested-With, Content-Type, Accept",methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS})
    @PostMapping("/users") // in: maps path
    public String ProcessAction(@RequestBody IcMessage icMessage)
    {
        try
        {
            System.out.println("Users:[IRC.ProcessAction] Start");
            ctrl.ProcessAction(icMessage);
        }
        catch(Exception e)
        {
            System.out.println("Users:[IRC.ProcessAction] Error=" + e.getMessage());
            return "Post Users Failed.";
        }
        return "Post Users Worked.";
    }

    @CrossOrigin(origins = "*", allowedHeaders = "Origin, X-Requested-With, Content-Type, Accept",methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS})
    @PostMapping("/userCreate") // in: maps path
    public String ProcessCreate(@RequestBody IcNewUsrMessage icNewUsrMessage)
    {
        try
        {
            System.out.println("Users:[IRC.ProcessCreate] Start");
            ctrl.ProcessAction(icNewUsrMessage);
        }
        catch(Exception e)
        {
            System.out.println("Users:[IRC.ProcessAction] Error=" + e.getMessage());
            return "Post Users Failed.";
        }
        return "Success";
    }
}
