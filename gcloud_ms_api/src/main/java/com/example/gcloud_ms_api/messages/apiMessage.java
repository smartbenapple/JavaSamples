package com.example.gcloud_ms_api.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

// Java's UUID
// https://www.baeldung.com/java-uuid
public class apiMessage
{
    private String _id;
    private String _role;
    private String _cmd;
    private String _destSrv;

    @JsonGetter("id")
    public String get_id()
    {
        return _id;
    }

    @JsonGetter("role")
    public String get_role()
    {
        return _role;
    }

    @JsonGetter("cmd")
    public String get_cmd()
    {
        return _cmd;
    }

    @JsonGetter("destSrv")
    public String get_destSrv()
    {
        return _destSrv;
    }

    @JsonCreator
    public apiMessage(@JsonProperty("id") String id, @JsonProperty("role") String role, @JsonProperty("cmd") String cmd, @JsonProperty("destSrv") String destSrv)
    {
        // Create UUID
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();

        _id = id.isEmpty() ? uuidString : id;
        _role = role;
        _cmd = cmd;
        _destSrv = destSrv;
    }
}
