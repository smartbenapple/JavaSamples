package com.example.gcloud_ms_movies;

import com.example.gcloud_ms_innerconnect.messages.IcMessage;
import com.example.gcloud_ms_movies.messages.IcNewMovieMessage;
import com.example.gcloud_ms_movies.messages.MoviesFirebaseMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.util.function.Consumer;

public class Connect
{
    // Springs: RestClient
    // https://docs.spring.io/spring-framework/reference/integration/rest-clients.html
    public void GetMoviesData(IcMessage icMessage) throws JsonProcessingException
    {
        System.out.println("Movies:[Connect.GetMoviesData] Start...");

        String url = "https://glcoud-ms-movies-firebase-axxh6chama-wl.a.run.app/movies";
        //String url2 = "http://localhost:8383/movies";

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

        // Using ObjectMapper to map Java->String (similar to JSON.Parse)
        // https://www.baeldung.com/jackson-object-mapper-tutorial
        ObjectMapper om = new ObjectMapper();
        String resultAsString = om.writeValueAsString(result);

        System.out.println("Movies:[Connect.GetUsersData] Response=" + resultAsString);
    }

    // todo: is this method required?
    // Sends to movies_firebase service.
    public void SendMovies(MoviesFirebaseMessage moviesFirebaseMessage)
    {
        System.out.println("Movies:[Connect.SendData] Start");

        String port = "";
        String url2 = "http://localhost:8383/movies";

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
                .uri(url2)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .headers(headersWork)
                .body(moviesFirebaseMessage)
                .retrieve()
                .toBodilessEntity());

        System.out.println("Movies:[Connect.SendData] Result=" + result);
    }

    // Sends to movies_firebase service.
    public void CreateMovie(IcNewMovieMessage icNewMovieMessage)
    {
        System.out.println("Movies:[Connect.CreateMovie] Start");

        String url = "https://glcoud-ms-movies-firebase-axxh6chama-wl.a.run.app/movies";
        //String url2 = "http://localhost:8383/movies";

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
                .body(icNewMovieMessage)
                .retrieve()
                .toBodilessEntity());

        System.out.println("Movies:[Connect.CreateMovie] Response=" + result);
    }
}
