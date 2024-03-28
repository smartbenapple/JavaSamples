package com.example.gcloud_ms_api.communication;

import com.example.gcloud_ms_innerconnect.messages.IcMessage;
import com.example.gcloud_ms_api.messages.apiMessage;
import com.example.gcloud_ms_api.utility.BytesHelper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.io.OutputStream;
import java.util.Dictionary;
import java.util.Hashtable;

public class Connect
{
    private static Connect _singleton;

    // Singleton
    public static Connect GetSingleton()
    {
        if (_singleton == null)
            _singleton = new Connect();
        return _singleton;
    }

    private Connect() {}

    //ArrayList<String> registry = new ArrayList<>();
    private static Dictionary<String, HttpServletResponse> registry = new Hashtable<>();

    public void Register(apiMessage message, HttpServletResponse response)
    {
        // todo: Error caught during testing: "The response object has been recycled and is no longer associated with this facade."
        //       Apparently this error is by design in the Java framework.  The facade handle to the response object is recycles in memory for whatever reason.
        //       Solution: Use the NodeJs project to store the response objects in an array.  And then test retrieving the object.
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

    public void SendData(IcMessage message, String path)
    {
        System.out.println("Api:[Connect.SendData] Start");

        String url = "https://gcloud-innerconnect-axxh6chama-wl.a.run.app/" + path;
        String url2 = "http://localhost:8181/" + path; // sendmeNewmovie or sendmeNewuser or sendme

        RestClient rest = RestClient.create();

        // Failed: Test creating headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin","*");
        headers.add("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
        headers.add("Access-Control-Allow-Methods","GET, POST, PUT, DELETE");

        // TODO: Add a promise like wrapper around this call to test async.
        String result = String.valueOf(rest.post()
                .uri(url2)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                //.headers((Consumer<HttpHeaders>) headers)
                .body(message)
                .retrieve()
                .toBodilessEntity());

        System.out.println("Api:[Connect.SendData] Result=" + result);
    }
}
