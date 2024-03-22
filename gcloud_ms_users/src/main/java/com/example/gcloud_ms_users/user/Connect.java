package com.example.gcloud_ms_users.user;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.nio.Buffer;

public class Connect
{
    public Connect() {}

    // Springs: RestClient
    // https://docs.spring.io/spring-framework/reference/integration/rest-clients.html
    public void SendData(InnerConnectMessage innerConnectMessage)
    {
        String url = "https://gcloud-innerconnect-axxh6chama-wl.a.run.app/sendme";
        String url2 = "http://localhost:8282/sendme";

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
}
