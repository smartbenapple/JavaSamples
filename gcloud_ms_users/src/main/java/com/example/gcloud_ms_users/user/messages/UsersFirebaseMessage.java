package com.example.gcloud_ms_users.user.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UsersFirebaseMessage
{
    private String _id;
    private String _destSrv;

    private UserMessage[] _users;

    public String get_id()
    {
        return _id;
    }

    public String get_destSrv()
    {
        return _destSrv;
    }

    public UserMessage[] Users()
    {
        return _users;
    }

    @JsonCreator
    UsersFirebaseMessage(@JsonProperty("id") String id, @JsonProperty("destSrv") String destSrv, @JsonProperty("data") UserMessage[] data)
    {
        _id = id;
        _destSrv = destSrv;
        _users = data;
    }
}
