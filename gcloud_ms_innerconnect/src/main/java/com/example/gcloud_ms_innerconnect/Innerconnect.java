package com.example.gcloud_ms_innerconnect;

import com.example.gcloud_ms_users.user.InnerConnectMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.util.*;

public class Innerconnect
{
    ArrayList<InnerConnectMessage> items = new ArrayList<InnerConnectMessage>();

    public String AddData(InnerConnectMessage innerConnectMessage)
    {
        System.out.println("IC:[Innerconnect.AddData] Start = " + innerConnectMessage.toString());
        items.add(innerConnectMessage);
        WatchQueues();
        return "IC:[Innerconnect.AddData] Add Data Success.";
    }

    public void SendData(InnerConnectMessage innerConnectMessage)
    {
        System.out.println("IC:[Innerconnect.SendData] Start = ");

        String url = "https://" + innerConnectMessage.get_host() + ":" + innerConnectMessage.get_port() + innerConnectMessage.get_path();

        RestClient rest = RestClient.create();

        // Failed: Test creating headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin","*");
        headers.add("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
        headers.add("Access-Control-Allow-Methods","GET, POST, PUT, DELETE");

        String result = String.valueOf(rest.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                //.headers((Consumer<HttpHeaders>) headers)
                .body(innerConnectMessage)
                .retrieve()
                .toBodilessEntity());

        System.out.println("Users:[Connect.SendData] Innerconnect response=" + result);
    }

    public void WatchQueues()
    {
        System.out.println("IC:[Innerconnect.watchQueues] Start");

        // TODO: Research how to do a Thread in this java.
        if (items.size() > 0)
        {
            // if true, then pop off item and send to destination.
            InnerConnectMessage item = items.get(0);

            // Check destination
            switch (item.get_destSrv())
            {
                case "MovieSrv":
                    System.out.println("IC:[Innerconnect.watchQueues] MovieSrv");
                    item.set_host("gcloud-ms-movies-axxh6chama-wl.a.run.app");
                    item.set_port("");
                    item.set_path("/movie");
                    SendData(item);
                    break;
                case "UserSrv":
                    System.out.println("IC:[Innerconnect.watchQueues] UserSrv");
                    item.set_host("gcloud-ms-users-axxh6chama-wl.a.run.app");
                    item.set_port("");
                    item.set_path("/users");
                    SendData(item);
                    break;
                case "ApiSrv":
                    System.out.println("IC:[Innerconnect.watchQueues] ApiSrv");
                    item.set_host("gcloud-ms-api-axxh6chama-wl.a.run.app");
                    item.set_port("");
                    item.set_path("/sendme");
                    SendData(item);
                    break;
            }
        }
    }
}
