package com.example.gcloud_ms_innerconnect;

import com.example.gcloud_ms_users.user.messages.IcMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Innerconnect
{
    // Atomic Thread-Safe value-types
    // https://www.tutorialspoint.com/java_concurrency/concurrency_atomicboolean.htm
    AtomicBoolean safeBool = new AtomicBoolean(false);

    ArrayList<IcMessage> items = new ArrayList<IcMessage>();

    public Innerconnect()
    {
        WatchAtomic();
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
        System.out.println("IC:[Innerconnect.SendData] Start = ");

        // reset Atomic value
        safeBool.set(false);

        // Threads
        // https://www.w3schools.com/java/java_threads.asp
        // https://www.geeksforgeeks.org/asynchronous-synchronous-callbacks-java/
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                RunSendData(innerConnectMessage);
                safeBool.set(true);
            }
        }).start();

        System.out.println("Users:[Connect.SendData] Innerconnect exit.");
    }

    private void RunSendData(IcMessage innerConnectMessage)
    {
        String port = innerConnectMessage.get_port().isEmpty() ? "" : ":" + innerConnectMessage.get_port();
        String url = "http://" + innerConnectMessage.get_host() + port + innerConnectMessage.get_path();

        RestClient rest = RestClient.create();

        // Failed: Test creating headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin","*");
        headers.add("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
        headers.add("Access-Control-Allow-Methods","GET, POST, PUT, DELETE");

        // TODO: Add a promise like wrapper around this call to test async.
        String result = String.valueOf(rest.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                //.headers((Consumer<HttpHeaders>) headers)
                .body(innerConnectMessage)
                .retrieve()
                .toBodilessEntity());

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
                    item.set_host("localhost"); // gcloud-ms-movies-axxh6chama-wl.a.run.app
                    item.set_port("8081");
                    item.set_path("/movies");
                    SendData(item);
                    break;
                case "UserSrv": // Send to Java User service
                    System.out.println("IC:[Innerconnect.watchQueues] UserSrv");
                    item.set_host("localhost"); // gcloud-ms-users-axxh6chama-wl.a.run.app
                    item.set_port("8082");
                    item.set_path("/users");
                    SendData(item);
                    break;
                case "ApiSrv": // Send to API service
                    System.out.println("IC:[Innerconnect.watchQueues] ApiSrv");
                    item.set_host("gcloud-ms-api-axxh6chama-wl.a.run.app");
                    item.set_port("");
                    item.set_path("/sendme");
                    SendData(item);
                    break;
            }
        }
    }

    public void WatchAtomic()
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
    }
}
