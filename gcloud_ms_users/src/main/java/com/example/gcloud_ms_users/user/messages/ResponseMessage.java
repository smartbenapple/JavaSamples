package com.example.gcloud_ms_users.user.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseMessage
{
    private String _message;

    @JsonGetter("message")
    public String get_message()
    {
        return _message;
    }

    @JsonCreator
    public ResponseMessage(@JsonProperty("message") String message)
    {
        _message = message;
    }
}
