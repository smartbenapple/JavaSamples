package com.example.gcloud_ms_users.user.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class IcMessage
{
    private String _host = "";
    private String _port = "";
    private String _path = "";
    private apiMessage _apiMessage; // todo: change String to apiMessage type.

    public void set_host(String value)
    {
        _host = value;
    }

    public void set_port(String value)
    {
        _port = value;
    }

    public void set_path(String value)
    {
        _path = value;
    }

    @JsonGetter("Host")
    public String get_host()
    {
        return _host;
    }

    @JsonGetter("Port")
    public String get_port()
    {
        return _port;
    }

    @JsonGetter("Path")
    public String get_path()
    {
        return _path;
    }

    // Note: Property required for deserialization process to gain access to nested class.
    @JsonGetter("id")
    public String get_id()
    {
        return _apiMessage.get_id();
    }

    // Note: Property required for deserialization process to gain access to nested class.
    @JsonGetter("role")
    public String get_role()
    {
        return _apiMessage.get_role();
    }

    // Note: Property required for deserialization process to gain access to nested class.
    @JsonGetter("cmd")
    public String get_cmd()
    {
        return _apiMessage.get_cmd();
    }

    // Note: Property required for deserialization process to gain access to nested class.
    @JsonGetter("destSrv")
    public String get_destSrv()
    {
        return _apiMessage.get_destSrv();
    }

    public apiMessage get_apiMessage()
    {
        return _apiMessage;
    }

    @JsonCreator
    public IcMessage(@JsonProperty("Host") String host, @JsonProperty("Port") String port, @JsonProperty("Path") String path, @JsonProperty("Id") String id, @JsonProperty("Role") String role, @JsonProperty("Cmd") String cmd, @JsonProperty("DestSrv") String destSrv)
    {
        System.out.println("[InnerConnectMessage.ctr] Triggered");

        _host = host;
        _port = port;
        _path = path;
        _apiMessage = new apiMessage(id, role, cmd, destSrv);
    }
}
