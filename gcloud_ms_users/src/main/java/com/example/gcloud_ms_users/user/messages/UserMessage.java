package com.example.gcloud_ms_users.user.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserMessage
{
    private String _userName;
    private String _password;

    @JsonGetter("username")
    public String get_userName()
    {
        return _userName;
    }

    @JsonGetter("password")
    public String get_password()
    {
        return _password;
    }

    @JsonCreator
    public UserMessage(@JsonProperty("username") String userName, @JsonProperty("password") String password)
    {
        System.out.println("Users:[UserMessage.ctr] username=" + userName + ",password=" + password);
        _userName = userName;
        _password = password;
    }
}
