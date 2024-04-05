package com.example.gcloud_ms_users.user.messages;

import com.example.gcloud_ms_innerconnect.messages.IcMessage;

import com.example.gcloud_ms_movies.messages.MovieMessage;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class IcAnsUsrMessage extends IcMessage
{
    private UserMessage[] _users;

    @JsonGetter("data")
    public UserMessage[] get_users()
    {
        return _users;
    }

    @JsonCreator
    public IcAnsUsrMessage(@JsonProperty("id") String id, @JsonProperty("destSrv") String destSrv, @JsonProperty("data") UserMessage[] users)
    {
        super("", "", "", id, "", "", destSrv,""); // todo: set base data to users?
        _users = users;
    }
}
