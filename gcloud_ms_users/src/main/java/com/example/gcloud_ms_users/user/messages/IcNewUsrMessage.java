package com.example.gcloud_ms_users.user.messages;

import com.example.gcloud_ms_api.utility.OMHelper;
import com.example.gcloud_ms_innerconnect.messages.IcMessage;
import com.fasterxml.jackson.annotation.JsonCreator;
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

    @JsonGetter("username")
    public String getUserName()
    {
        return _userMessage.get_userName();
    }

    @JsonGetter("password")
    public String getPassword()
    {
        return _userMessage.get_password();
    }

    @JsonCreator
    public IcNewUsrMessage(@JsonProperty("id") String id, @JsonProperty("role") String role, @JsonProperty("cmd") String cmd, @JsonProperty("destSrv") String destSrv, @JsonProperty("username") String userName, @JsonProperty("password") String password)
    {
        super("", "", "", id, role, cmd, destSrv, OMHelper.Parse(new UserMessage(userName,password))); // "{username:" + userName + ",password:" + password + "}"
        System.out.println("Users:[IcNewUsrMessage:ctr] id=" + id + ",role=" + role + ",cmd=" + cmd + ",destSrv=" + destSrv + ",userName=" + userName + ",password=" + password);
        _userMessage = new UserMessage(userName,password);
    }
}
