package com.example.gcloud_ms_users.user.messages;

import com.example.gcloud_ms_innerconnect.messages.IcMessage;
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

    public IcNewUsrMessage(@JsonProperty("id") String id, @JsonProperty("role") String role, @JsonProperty("cmd") String cmd, @JsonProperty("destSrv") String destSrv, @JsonProperty("username") String userName, @JsonProperty("password") String password)
    {
        super("", "", "", id, role, cmd, destSrv);
        _userMessage = new UserMessage(userName,password);
    }
}
