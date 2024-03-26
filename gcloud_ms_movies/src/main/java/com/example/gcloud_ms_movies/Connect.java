package com.example.gcloud_ms_movies;

import com.example.gcloud_ms_movies.messages.MovieMessage;
import com.example.gcloud_ms_movies.messages.MoviesFirebaseMessage;
import com.example.gcloud_ms_users.user.messages.IcMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

public class Connect
{
    // Springs: RestClient
    // https://docs.spring.io/spring-framework/reference/integration/rest-clients.html
    public MoviesFirebaseMessage GetMoviesData(IcMessage icMessage) throws JsonProcessingException
    {
        System.out.println("Movies:[Connect.GetMoviesData] Start...");

        String url = "https://gcloud-ms-users-firebase-axxh6chama-wl.a.run.app/users";
        String url2 = "http://localhost:8383/movies";

        RestClient rest = RestClient.create();

        // Failed: Test creating headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin","*");
        headers.add("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
        headers.add("Access-Control-Allow-Methods","GET, POST, PUT, DELETE");

        // Expect to receive: {"message":"success"}
        // Firebase NodeJs service will send answer directly to innerconnect.
        ResponseEntity<MoviesFirebaseMessage> result = rest.post()
                .uri(url2)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                //.headers((Consumer<HttpHeaders>) headers)
                .body(icMessage)
                .retrieve()
                .toEntity(MoviesFirebaseMessage.class);

        // Cast to class type from GetBody().
        MoviesFirebaseMessage data = result.getBody();
        MovieMessage[] movies = data.Movies();

        // Using ObjectMapper to map Java->String (similar to JSON.Parse)
        // https://www.baeldung.com/jackson-object-mapper-tutorial
        ObjectMapper om = new ObjectMapper();
        String moviesAsString = om.writeValueAsString(movies);

        System.out.println("Movies:[Connect.GetUsersData] Response=" +moviesAsString);

        return data;
    }

    // Sends to innerconnect service
    public void SendMovies(MoviesFirebaseMessage moviesFirebaseMessage)
    {
        System.out.println("Movies:[Connect.SendData] Start");

        String port = "";
        String url = "http://localhost:8181/movieanswer";

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
                .body(moviesFirebaseMessage)
                .retrieve()
                .toBodilessEntity());

        System.out.println("Movies:[Connect.SendData] Result=" + result);
    }
}
