package com.example.gcloud_ms_get;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.util.function.Consumer;

public class getData
{
    // Springs: RestClient
    // https://docs.spring.io/spring-framework/reference/integration/rest-clients.html
    public Movies GetData()
    {
        String url = "http://gcloud-ms-api-axxh6chama-wl.a.run.app/movies";
        String url2 = "https://gcloud-ms-get-axxh6chama-wl.a.run.app/get";

        RestClient rest = RestClient.create();

        // TODO: Test creating headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin","*");
        headers.add("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
        headers.add("Access-Control-Allow-Methods","GET, POST, PUT, DELETE");

        Movies result = rest.get()
                .uri(url2)
                .accept(MediaType.APPLICATION_JSON)
                //.headers((Consumer<HttpHeaders>) headers)
                .retrieve()
                .body(Movies.class);

        return result;
    }
}

