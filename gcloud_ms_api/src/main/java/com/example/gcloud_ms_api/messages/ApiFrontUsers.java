package com.example.gcloud_ms_api.messages;

import com.example.gcloud_ms_api.utility.OMHelper;
import com.example.gcloud_ms_users.user.messages.UserMessage;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.catalina.User;

public class ApiFrontUsers
{
    private String _id;
    private UserMessage _data;
    private String _path;

    public UserMessage get_data()
    {
        return _data;
    }

    @JsonGetter("id")
    public String get_id()
    {
        return _id;
    }

    @JsonGetter("path")
    public String get_path()
    {
        return _path;
    }

    @JsonCreator
    public ApiFrontUsers(@JsonProperty("id") String id, @JsonProperty("data_username") String username, @JsonProperty("data_password") String password, @JsonProperty("path") String path) // UserMessage
    {
        System.out.println("API:[ApiFrontUsers.ctr] id=" + id + ",username=" + username + ",password=" + password + ",path=" + path);

        _id = id;
        _path = path;

        try
        {
            // Pass: Tested conversion to UserMessage
            UserMessage userMessage = new UserMessage(username,password); //OMHelper.Convert(username, UserMessage.class);
            _data = userMessage;
        }
        catch(Exception e)
        {
            System.out.println("API[ApiFrontUsers.ctr] Crash on conversion of 'data'; Error =" + e.getMessage());
        }
    }
}
