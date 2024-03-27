package com.example.gcloud_ms_users.user;

import com.example.gcloud_ms_users.user.messages.IcMessage;
import com.example.gcloud_ms_users.user.messages.IcNewUsrMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

// Springs: RestClient
// https://docs.spring.io/spring-framework/reference/integration/rest-clients.html
public class Connect
{
    public Connect() {}

    // Send request to Users_Firebase service.
    public void GetUsersData(IcMessage icMessage)
    {
        System.out.println("Users:[Connect.GetUsersData] Start...");

        String url = "https://gcloud-ms-users-firebase-axxh6chama-wl.a.run.app/users";
        String url2 = "http://localhost:8283/users";

        RestClient rest = RestClient.create();

        // Failed: Test creating headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin","*");
        headers.add("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
        headers.add("Access-Control-Allow-Methods","GET, POST, PUT, DELETE");

        // Expect to receive: {"message":"success"}
        // Firebase NodeJs service will send answer directly to innerconnect.
        String result = String.valueOf(rest.post()
                .uri(url2)
                //.contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                //.headers((Consumer<HttpHeaders>) headers)
                .body(icMessage)
                .retrieve()
                .toBodilessEntity());

        System.out.println("Users:[Connect.GetUsersData] Response=" + result);
    }

    // Send request to Users_Firebase service.
    public void CreateUser(IcNewUsrMessage icNewUsrMessage)
    {
        System.out.println("Users:[Connect.CreateUser] Start...");

        String url = "https://gcloud-ms-users-firebase-axxh6chama-wl.a.run.app/users";
        String url2 = "http://localhost:8283/users";

        RestClient rest = RestClient.create();

        // Failed: Test creating headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin","*");
        headers.add("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
        headers.add("Access-Control-Allow-Methods","GET, POST, PUT, DELETE");

        // Expect to receive: {"message":"success"}
        // Firebase NodeJs service will send answer directly to innerconnect.
        String result = String.valueOf(rest.post()
                .uri(url2)
                //.contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                //.headers((Consumer<HttpHeaders>) headers)
                .body(icNewUsrMessage)
                .retrieve()
                .toBodilessEntity());

        System.out.println("Users:[Connect.CreateUser] Response=" + result);
    }
}
