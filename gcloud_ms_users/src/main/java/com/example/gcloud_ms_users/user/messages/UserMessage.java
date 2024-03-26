package com.example.gcloud_ms_users.user.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserMessage
{

    private String _userName;
    private String _password;

    @JsonCreator
    UserMessage(@JsonProperty("username") String userName, @JsonProperty("password") String password)
    {
        _userName = userName;
        _password = password;
    }
}
