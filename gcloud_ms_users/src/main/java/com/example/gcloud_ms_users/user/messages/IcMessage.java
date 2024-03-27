package com.example.gcloud_ms_users.user.messages;

import com.example.gcloud_ms_api.messages.apiMessage;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class IcMessage
{
    private String _host = "";
    private String _port = "";
    private String _path = "";
    private apiMessage _apiMessage;

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

    @JsonGetter("host")
    public String get_host()
    {
        return _host;
    }

    @JsonGetter("port")
    public String get_port()
    {
        return _port;
    }

    @JsonGetter("path")
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
    public IcMessage(@JsonProperty("host") String host, @JsonProperty("port") String port, @JsonProperty("path") String path, @JsonProperty("id") String id,
                     @JsonProperty("role") String role, @JsonProperty("cmd") String cmd, @JsonProperty("destSrv") String destSrv)
    {
        System.out.println("[InnerConnectMessage.ctr] Triggered");

        _host = host;
        _port = port;
        _path = path;
        _apiMessage = new apiMessage(id, role, cmd, destSrv);
    }
}
