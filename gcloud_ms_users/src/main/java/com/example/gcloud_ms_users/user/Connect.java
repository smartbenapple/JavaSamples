package com.example.gcloud_ms_users.user;

import com.example.gcloud_ms_api.utility.OMHelper;
import com.example.gcloud_ms_innerconnect.messages.IcMessage;
import com.example.gcloud_ms_users.user.messages.IcNewUsrMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.util.function.Consumer;

// Springs: RestClient
// https://docs.spring.io/spring-framework/reference/integration/rest-clients.html
public class Connect
{
    public Connect() {}

    // Send request to Users_Firebase service.
    public void GetUsersData(IcMessage icMessage)
    {
        String messageOut = OMHelper.Parse(icMessage);
        System.out.println("Users:[Connect.GetUsersData] message=" + messageOut);

        String url = "https://gcloud-ms-users-firebase-axxh6chama-wl.a.run.app/users";
        //String url2 = "http://localhost:8283/users";
        System.out.println("Users:[Connect.GetUsersData] url=" + url);

        RestClient rest = RestClient.create();

        // Possible-Pass: Setup a Consumer<HttpHeaders> callback
        Consumer<HttpHeaders> headersWork = (headers) ->
        {
            System.out.println("IC:[Innerconnect.RunSendData.Consumer<HttpHeaders>] triggered.");
            headers.add("Access-Control-Allow-Origin","*");
            headers.add("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
            headers.add("Access-Control-Allow-Methods","GET, POST, PUT, DELETE");
        };

        // Expect to receive: {"message":"success"}
        // Firebase NodeJs service will send answer directly to innerconnect.
        String result = String.valueOf(rest.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .headers(headersWork)
                .body(icMessage)
                .retrieve()
                .toBodilessEntity());

        System.out.println("Users:[Connect.GetUsersData] Response=" + result);
    }

    // Send request to Users_Firebase service.
    public void CreateUser(IcNewUsrMessage icNewUsrMessage)
    {
        String messageOut = OMHelper.Parse(icNewUsrMessage);
        System.out.println("Users:[Connect.CreateUser] message=" + messageOut);

        String url = "https://gcloud-ms-users-firebase-axxh6chama-wl.a.run.app/users";
        //String url2 = "http://localhost:8283/users";
        System.out.println("Users:[Connect.CreateUser] url=" + url);

        RestClient rest = RestClient.create();

        // Possible-Pass: Setup a Consumer<HttpHeaders> callback
        Consumer<HttpHeaders> headersWork = (headers) ->
        {
            System.out.println("IC:[Innerconnect.RunSendData.Consumer<HttpHeaders>] triggered.");
            headers.add("Access-Control-Allow-Origin","*");
            headers.add("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
            headers.add("Access-Control-Allow-Methods","GET, POST, PUT, DELETE");
        };

        // Expect to receive: {"message":"success"}
        // Firebase NodeJs service will send answer directly to innerconnect.
        String result = String.valueOf(rest.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .headers(headersWork)
                .body(icNewUsrMessage)
                .retrieve()
                .toBodilessEntity());

        System.out.println("Users:[Connect.CreateUser] Response=" + result);
    }
}
