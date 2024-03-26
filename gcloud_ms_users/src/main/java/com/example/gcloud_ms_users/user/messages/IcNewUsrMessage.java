package com.example.gcloud_ms_users.user.messages;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class IcNewUsrMessage extends IcMessage
{
    private UserMessage _userMessage;

    @JsonGetter("data")
    public UserMessage get_userMessage()
    {
        return _userMessage;
    }

    public IcNewUsrMessage(@JsonProperty("host") String host, @JsonProperty("port") String port, @JsonProperty("path") String path, @JsonProperty("id") String id,
                           @JsonProperty("role") String role, @JsonProperty("cmd") String cmd, @JsonProperty("destSrv") String destSrv, @JsonProperty("username") String userName, @JsonProperty("password") String password)
    {
        super(host, port, path, id, role, cmd, destSrv);
        _userMessage = new UserMessage(userName,password);
    }
}
