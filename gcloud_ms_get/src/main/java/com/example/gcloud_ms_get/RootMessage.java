package com.example.gcloud_ms_get;

import org.springframework.web.bind.annotation.RequestParam;

// Properties in Java => use get/set in front of private names.
// https://www.w3schools.com/java/java_encapsulation.asp
public class RootMessage
{
    private String _message;

    // Note: Testing shows the @RequestBody matches the set() calls; not the ctr call.
    // Match: {"message":""} in json
    public String getMessage()
    {
        return _message;
    }

    public void setMessage(String newMessage)
    {
        _message = newMessage;
    }

    public void RootMessage(String message)
    {
        _message = message;
    }
}
