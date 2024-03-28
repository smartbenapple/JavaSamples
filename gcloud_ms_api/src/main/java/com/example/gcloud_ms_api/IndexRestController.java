package com.example.gcloud_ms_api;

import com.example.gcloud_ms_api.communication.Connect;
import com.example.gcloud_ms_api.user.Controller;
import com.example.gcloud_ms_api.utility.OMHelper;
import com.example.gcloud_ms_movies.messages.IcAnsMoviesMessage;
import com.example.gcloud_ms_movies.messages.MovieMessage;
import com.example.gcloud_ms_users.user.messages.IcAnsUsrMessage;
import com.example.gcloud_ms_users.user.messages.UsersFirebaseMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.OutputStream;

// Get the request/response objects using @RequestMapping
// Note: Testing shows the request/response objects also map with @PostMapping.
// https://stackoverflow.com/questions/4564465/spring-controller-get-request-response
// Write to OutputStream in the response object
// https://www.javaguides.net/2019/03/httpservletresponse-interface-with-example.html
@RestController
public class IndexRestController
{
    Controller ctrl = new Controller();
    Connect connect = new Connect();

    public IndexRestController()
    {
    }

    @GetMapping("/users")
    public void UserGetRouter(HttpServletResponse response)
    {
        System.out.println("API:[IRC.UserGetRouter] Start");

        ctrl.GetAllAction(response);
    }

    @PostMapping("/users") // in: path
    //@RequestMapping(value = "/users", method = RequestMethod.POST)
    public void UserRouter(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        System.out.println("API:[IRC.UserRouter] Start");

        // To output through response stream, simply grab onto the OutputStream reference.
        // Then write to the OutputStream using byte arrays.
        OutputStream output = response.getOutputStream();
        String test = "Hello";
        byte[] testBytes = test.getBytes();
        output.write(testBytes);

        // todo: create call

    }

    @PostMapping("/movies") // in: path
    public void MovieRouter()
    {

    }

    @PostMapping("/sendmeUsers") // in: path
    public void SendmeUsersRouter(@RequestBody IcAnsUsrMessage icAnsUsrMessage)
    {
        System.out.println("API:[IRC.SendmeUsersRouter] Start");

        // get required data
        String id = icAnsUsrMessage.get_id();
        UsersFirebaseMessage data = icAnsUsrMessage.get_usersFirebaseMessage();
        String jsonData = OMHelper.Parse(data);
        connect.RegistryHandler(id, jsonData);
    }

    @PostMapping("/sendmeMovies") // in: path
    public void SendmeMoviesRouter(@RequestBody IcAnsMoviesMessage icAnsMoviesMessage)
    {
        System.out.println("API:[IRC.SendmeMoviesRouter] Start");

        // get required data
        String id = icAnsMoviesMessage.get_id();
        MovieMessage[] data = icAnsMoviesMessage.get_movies();
        String jsonData = OMHelper.Parse(data);
        connect.RegistryHandler(id, jsonData);
    }
}
