package com.example.gcloud_ms_api.communication;

import com.example.gcloud_ms_api.messages.apiMessage;
import com.example.gcloud_ms_api.utility.BytesHelper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Connect
{
    //ArrayList<String> registry = new ArrayList<>();
    Dictionary<String, HttpServletResponse> registry = new Hashtable<>();

    public void Register(apiMessage message, HttpServletResponse response)
    {
        String id = message.get_id();
        registry.put(id, response);
        System.out.println("API:[Connect.Register]  Stored 'Response' successfully for id=" + id);
    }

    public void RegisterId(String id, HttpServletResponse response)
    {
        registry.put(id, response);
        System.out.println("API:[Connect.RegisterId]  Stored 'Response' successfully for id=" + id);
    }

    public <T extends Serializable> void Answer(String id, T data) // todo: set to specific class type?
    {
        HttpServletResponse response = registry.get(id);

        try
        {
            // To output through response stream, simply grab onto the OutputStream reference.
            // Then write to the OutputStream using byte arrays.
            OutputStream output = response.getOutputStream();
            byte[] dataBytes = BytesHelper.Convert(data);
            output.write(dataBytes);
        }
        catch(Exception e)
        {
            System.out.println("API:[Connect.Answer] Error=" + e.getMessage());
        }
    }
}
