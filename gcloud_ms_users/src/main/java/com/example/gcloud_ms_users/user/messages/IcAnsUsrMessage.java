package com.example.gcloud_ms_users.user.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class IcAnsUsrMessage extends IcMessage
{
    private UsersFirebaseMessage _usersFirebaseMessage;

    @JsonGetter("data")
    public UsersFirebaseMessage get_usersFirebaseMessage()
    {
        return _usersFirebaseMessage;
    }

    @JsonCreator
    public IcAnsUsrMessage(@JsonProperty("host") String host, @JsonProperty("port") String port, @JsonProperty("path") String path, @JsonProperty("id") String id,
                           @JsonProperty("role") String role, @JsonProperty("cmd") String cmd, @JsonProperty("destSrv") String destSrv, @JsonProperty("data") UsersFirebaseMessage usersFirebaseMessage)
    {
        super(host, port, path, id, role, cmd, destSrv);
        _usersFirebaseMessage = usersFirebaseMessage;
    }
}
