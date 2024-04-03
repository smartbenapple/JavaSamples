package com.example.gcloud_ms_innerconnect;

import com.example.gcloud_ms_api.utility.OMHelper;
import com.example.gcloud_ms_api.utility.ThreadHelper;
import com.example.gcloud_ms_innerconnect.messages.IcMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

public class Innerconnect
{
    // Atomic Thread-Safe value-types
    // https://www.tutorialspoint.com/java_concurrency/concurrency_atomicboolean.htm
    //AtomicBoolean safeBool = new AtomicBoolean(false);

    ArrayList<IcMessage> items = new ArrayList<IcMessage>();

    public Innerconnect()
    {
        //WatchAtomic();
    }

    public String AddData(IcMessage innerConnectMessage)
    {
        System.out.println("IC:[Innerconnect.AddData] Start = " + innerConnectMessage.toString());
        items.add(innerConnectMessage);
        WatchQueues();
        return "IC:[Innerconnect.AddData] Add Data Success.";
    }

    public void SendData(IcMessage innerConnectMessage)
    {
        System.out.println("IC:[Innerconnect.SendData] Start");

        // reset Atomic value
        //safeBool.set(false);

        // TODO: remove thread call for testing - Results: It appears the thread context does interfere with sending wire data.
        RunSendData(innerConnectMessage);
        // Run Consumer work through ThreadHelper
        /*Consumer<String> work = (name) ->
                {
                    System.out.println("IC:[Innerconnect.SendData.ConsumerWork] Consumer work start.");
                    RunSendData(innerConnectMessage);
                    //safeBool.set(true);
                };
        ThreadHelper.Async(work, "empty");*/

        System.out.println("IC:[Innerconnect.SendData] Innerconnect exit.");
    }

    private void RunSendData(IcMessage innerConnectMessage)
    {
        String port = innerConnectMessage.get_port().isEmpty() ? "" : ":" + innerConnectMessage.get_port();
        String url = "https://" + innerConnectMessage.get_host() + port + innerConnectMessage.get_path();
        System.out.println("IC:[Innerconnect.RunSendData] url=" + url);

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
                .body(innerConnectMessage)
                .retrieve()
                .toBodilessEntity());

        String resultStr = OMHelper.Parse(result);
        System.out.println("IC:[Innerconnect.RunSendData] Result=" + resultStr);
    }

    public void WatchQueues()
    {
        System.out.println("IC:[Innerconnect.watchQueues] Start");

        // TODO: Research how to do a Thread in this java.
        if (items.size() > 0)
        {
            // if true, then pop off item and send to destination.
            IcMessage item = items.get(0);
            items.remove(item); // remove from array

            // Check destination
            switch (item.get_destSrv())
            {
                case "MovieSrv": // Send to Java Movie service
                    System.out.println("IC:[Innerconnect.watchQueues] MovieSrv");
                    item.set_host("gcloud-ms-movies-axxh6chama-wl.a.run.app"); // gcloud-ms-movies-axxh6chama-wl.a.run.app
                    item.set_port(""); // was:8081
                    item.set_path(Objects.equals(item.get_cmd(), "create") ? "/movieCreate" : "/movies");
                    SendData(item);
                    break;
                case "UserSrv": // Send to Java User service
                    System.out.println("IC:[Innerconnect.watchQueues] UserSrv");
                    item.set_host("gcloud-ms-users-axxh6chama-wl.a.run.app"); // gcloud-ms-users-axxh6chama-wl.a.run.app
                    item.set_port(""); // was:8082
                    item.set_path(Objects.equals(item.get_cmd(), "create") ? "/userCreate" : "/users");
                    SendData(item);
                    break;
                case "ApiSrv": // Send to API service
                    System.out.println("IC:[Innerconnect.watchQueues] ApiSrv");
                    item.set_host("gcloud-ms-api-front-axxh6chama-wl.a.run.app"); // gcloud-ms-api-front-axxh6chama-wl.a.run.app
                    item.set_port(""); // was:8080
                    item.set_path(item.get_path()); // was: "/sendme" - now can be different paths for users/movies answers.
                    SendData(item);
                    break;
            }
            System.out.println("IC:[Innerconnect.watchQueues] url=https://" + item.get_host() + "/" + item.get_path());
        }
    }

   /* public void WatchAtomic()
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while(true)
                {
                    try
                    {
                        if (safeBool.get() == true)
                        {
                            System.out.println("The Atomic Boolean switch to 'true'.");
                            return;
                        }
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }*/
}
