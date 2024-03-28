package com.example.gcloud_ms_api.communication;

import com.example.gcloud_ms_api.messages.apiMessage;
import com.example.gcloud_ms_api.utility.BytesHelper;
import com.example.gcloud_ms_users.user.messages.IcMessage;
import jakarta.servlet.http.HttpServletResponse;

import java.io.OutputStream;
import java.io.Serializable;
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

    public void Answer(String id, String data)
    {
        System.out.println("API:[Connect.Answer] Start answering call for id=" + id);

        // Get response from registry array
        HttpServletResponse response = registry.get(id);

        try
        {
            // To output through response stream, simply grab onto the OutputStream reference.
            // Then write to the OutputStream using byte arrays.
            OutputStream output = response.getOutputStream();
            byte[] dataBytes = BytesHelper.Convert(data);
            output.write(dataBytes);
            System.out.println("API:[Connect.Answer] Triggering send for id=" + id);

            // Delete item from registry array
            registry.remove(id);
        }
        catch(Exception e)
        {
            System.out.println("API:[Connect.Answer] Error=" + e.getMessage());
        }
    }

    public void RegistryHandler(String id, String data) // todo: replace object with specific class
    {
        System.out.println("API:[Connect.RegistryHandler] id=" + id);
        Answer(id, data);
    }
}
