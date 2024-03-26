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

    public IcNewUsrMessage(@JsonProperty("Host") String host, @JsonProperty("Port") String port, @JsonProperty("Path") String path, @JsonProperty("Id") String id,
                           @JsonProperty("Role") String role, @JsonProperty("Cmd") String cmd, @JsonProperty("DestSrv") String destSrv, @JsonProperty("username") String userName, @JsonProperty("password") String password)
    {
        super(host, port, path, id, role, cmd, destSrv);
        _userMessage = new UserMessage(userName,password);
    }
}
