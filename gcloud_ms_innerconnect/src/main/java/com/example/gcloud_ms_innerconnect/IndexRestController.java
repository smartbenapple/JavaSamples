package com.example.gcloud_ms_innerconnect;

import com.example.gcloud_ms_users.user.InnerConnectMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexRestController
{
    Innerconnect connect = new Innerconnect();

    @GetMapping("/sendme") // in: path
    public String TestResponse()
    {
        return "Innerconnect:[IndexRestController.TestResponse] Test Connection Success.";
    }

    @PostMapping("/sendme") // in: path
    public String AddData(@RequestBody InnerConnectMessage innerConnectMessage)
    {
        return connect.AddData(innerConnectMessage);
    }
}
