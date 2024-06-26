package com.example.gcloud_ms_api.communication;

import com.example.gcloud_ms_innerconnect.messages.IcMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.util.function.Consumer;

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

    // Note: Use the NodeJs Stg service instead.
    //private static Dictionary<String, ServletOutputStream> registry = new Hashtable<>();

    /*public void Register(apiMessage message, ServletResponse response) // HttpServletResponse response; ServletResponse; Response
    {
        try
        {
            // todo: Error caught during testing: "The response object has been recycled and is no longer associated with this facade."
            //       Apparently this error is by design in the Java framework.  The facade handle to the response object is recycles in memory for whatever reason.
            //       Solution: Use the NodeJs project to store the response objects in an array.  And then test retrieving the object.
            String id = message.get_id();
            registry.put(id, response.getOutputStream());
            System.out.println("API:[Connect.Register]  Stored 'Response' successfully for id=" + id);
        }
        catch(Exception e)
        {
            System.out.println("API[Connect.Register] Error = " + e.getMessage());
        }
    }*/

    /*public void RegisterId(String id, HttpServletResponse response)
    {
        //registry.put(id, response);
        System.out.println("API:[Connect.RegisterId]  Stored 'Response' successfully for id=" + id);
    }*/

    /*public void Answer(String id, String data)
    {
        System.out.println("API:[Connect.Answer] Start answering call for id=" + id);

        // Get response from registry array
        ServletOutputStream outputStream = registry.get(id);

        try
        {
            // todo: testing
            //ByteChunk bytesChunk = new ByteChunk();
            //bytesChunk.append("hello".getBytes(),0, "hello".getBytes().length);
            //ByteBuffer byteBuffer = ByteBuffer.wrap("hello".getBytes());
            //cOutputBuffer outputBuffer = new cOutputBuffer();
            //outputBuffer.doWrite(byteBuffer);
            //response.setOutputBuffer(outputBuffer);

            // To output through response stream, simply grab onto the OutputStream reference.
            // Then write to the OutputStream using byte arrays.
            //OutputStream output = response.getOutputStream();
            byte[] dataBytes = BytesHelper.Convert(data);
            outputStream.write(dataBytes);
            System.out.println("API:[Connect.Answer] Triggering send for id=" + id);

            // Delete item from registry array
            registry.remove(id);
        }
        catch(Exception e)
        {
            System.out.println("API:[Connect.Answer] Error=" + e.getMessage());
        }
    }*/

    /*public void RegistryHandler(String id, String data) // todo: replace object with specific class
    {
        System.out.println("API:[Connect.RegistryHandler] id=" + id);
        Answer(id, data);
    }*/

    public void SendData(IcMessage message, String path)
    {
        System.out.println("Api:[Connect.SendData] Start");

        String url = "https://gcloud-ms-innerconnect-axxh6chama-wl.a.run.app/" + path;
        String url2 = "http://localhost:8181/" + path; // sendmeNewmovie or sendmeNewuser or sendme
        System.out.println("Api:[Connect.SendData] url=" + url);

        RestClient rest = RestClient.create();

        // Possible-Pass: Setup a Consumer<HttpHeaders> callback
        Consumer<HttpHeaders> headersWork = (headers) ->
        {
            System.out.println("IC:[Innerconnect.RunSendData.Consumer<HttpHeaders>] triggered.");
            headers.add("Access-Control-Allow-Origin","*");
            headers.add("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
            headers.add("Access-Control-Allow-Methods","GET, POST, PUT, DELETE");
        };

        // TODO: Add a promise like wrapper around this call to test async.
        String result = String.valueOf(rest.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .headers(headersWork)
                .body(message)
                .retrieve()
                .toBodilessEntity());

        System.out.println("Api:[Connect.SendData] Result=" + result);
    }

}
