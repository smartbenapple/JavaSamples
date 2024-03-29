package com.example.gcloud_ms_api.messages;

import com.example.gcloud_ms_users.user.messages.UserMessage;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiFrontUsers
{
    private String _id;
    private UserMessage _data;
    private String _path;

    public UserMessage get_data()
    {
        return _data;
    }

    public String get_id()
    {
        return _id;
    }

    public String get_path()
    {
        return _path;
    }

    @JsonCreator
    public ApiFrontUsers(@JsonProperty("id") String id, @JsonProperty("data") UserMessage data, @JsonProperty("path") String path)
    {
        _id = id;
        _data = data;
        _path = path;
    }
}
