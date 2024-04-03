package com.example.gcloud_ms_api;

import com.example.gcloud_ms_api.communication.Connect;
import com.example.gcloud_ms_api.messages.ApiFrontMovies;
import com.example.gcloud_ms_api.messages.ApiFrontUsers;
import com.example.gcloud_ms_api.user.Controller;
import com.example.gcloud_ms_api.utility.OMHelper;
import com.example.gcloud_ms_api.utility.ResponseHelper;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.OutputStream;

// gCloud: https://gcloud-ms-api-axxh6chama-wl.a.run.app
// Locally: server.port=${port:8084}
// Cors => https://spring.io/guides/gs/rest-service-cors
//    Use:  @CrossOrigin(origins = "*", allowedHeaders = "Origin, X-Requested-With, Content-Type, Accept",methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS})
// Get the request/response objects using @RequestMapping
// Note: Testing shows the request/response objects also map with @PostMapping.
// https://stackoverflow.com/questions/4564465/spring-controller-get-request-response
// Write to OutputStream in the response object
// https://www.javaguides.net/2019/03/httpservletresponse-interface-with-example.html
@RestController
public class IndexRestController
{
    Controller userCtrl = new Controller();
    com.example.gcloud_ms_api.movie.Controller movieCtrl = new com.example.gcloud_ms_api.movie.Controller();
    Connect connect = Connect.GetSingleton();

    public IndexRestController()
    {
    }

    @CrossOrigin(origins = "*", allowedHeaders = "Origin, X-Requested-With, Content-Type, Accept",methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS})
    @PostMapping("/usersGet") // in: path
    public void UserGetRouter(HttpServletResponse srvResponse, @RequestBody String users) // ApiFrontUsers
    {
        System.out.println("API:[IRC.UserGetRouter] Start");

        try
        {
            // Pass: Tested conversion to ApiFrontUsers
            ApiFrontUsers usersResult = OMHelper.Convert(users, ApiFrontUsers.class);
            System.out.println("Convert Json String Successfully");
            userCtrl.GetAllAction(srvResponse, usersResult);
        }
        catch(Exception e)
        {
            System.out.println("API:[IRC.UserGetRouter] Error=" + e.getMessage());
            ResponseHelper.Write(srvResponse, "Error Occurred during JSON Convert.");
            srvResponse.setStatus(500);
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "Origin, X-Requested-With, Content-Type, Accept",methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS})
    @PostMapping("/usersPost") // in: path
    //@RequestMapping(value = "/users", method = RequestMethod.POST)
    public void UserRouter(@RequestBody ApiFrontUsers body, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        System.out.println("API:[IRC.UserRouter] Start");

        try
        {
            // Pass: Tested conversion to ApiFrontUsers
            //ApiFrontUsers users = OMHelper.Convert(body, ApiFrontUsers.class);
            //System.out.println("TESTING: result= " + users.get_id());

            // Pass: Tested OmHelper.Convert.
            /*UserMessage users = new UserMessage("john","123");
            String usersData = OMHelper.Parse(users);
            UserMessage result = OMHelper.Convert(usersData, UserMessage.class);
            System.out.println("TESTING: result= " + result.get_password());*/

            userCtrl.CreateAction(response, body);
        }
        catch(Exception e)
        {
            System.out.println("API[IRC.UserRouter] Error=" + e.getMessage());
        }
        // To output through response stream, simply grab onto the OutputStream reference.
        // Then write to the OutputStream using byte arrays.
        ResponseHelper.Write(response, "Hello");

        // todo: create call
    }

    @CrossOrigin(origins = "*", allowedHeaders = "Origin, X-Requested-With, Content-Type, Accept",methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS})
    @PostMapping("/moviesGet") // in: path
    public void MovieGetRouter(ServletRequest servletRequest, ServletResponse srvResponse, @RequestBody ApiFrontMovies movies)
    {
        System.out.println("API:[IRC.MovieGetRouter] Start");

        movieCtrl.GetAllAction(srvResponse, movies);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "Origin, X-Requested-With, Content-Type, Accept",methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS})
    @PostMapping("/moviesPost") // in: path
    //@RequestMapping(value = "/users", method = RequestMethod.POST)
    public void movieRouter(@RequestBody ApiFrontMovies body, HttpServletRequest request, HttpServletResponse response) throws IOException // ApiFrontMovies
    {
        System.out.println("API:[IRC.UserRouter] Start");

        try
        {
            // Pass: Tested conversion to ApiFrontMovies
            //ApiFrontMovies movies = OMHelper.Convert(body, ApiFrontMovies.class);
            //System.out.println("TESTING: result= " + movies.get_id());

            // Pass: Tested OmHelper.Convert.
            /*UserMessage users = new UserMessage("john","123");
            String usersData = OMHelper.Parse(users);
            UserMessage result = OMHelper.Convert(usersData, UserMessage.class);
            System.out.println("TESTING: result= " + result.get_password());*/

            movieCtrl.CreateAction(response, body);
        }
        catch(Exception e)
        {
            System.out.println("API[IRC.UserRouter] Error=" + e.getMessage());
        }
        // To output through response stream, simply grab onto the OutputStream reference.
        // Then write to the OutputStream using byte arrays.
        OutputStream output = response.getOutputStream();
        String test = "Hello";
        byte[] testBytes = test.getBytes();
        output.write(testBytes);

        // todo: create call
    }

    // Note: Use API-Front
    /*@PostMapping("/sendmeUsers") // in: path
    public void SendmeUsersRouter(@RequestBody IcAnsUsrMessage icAnsUsrMessage)
    {
        System.out.println("API:[IRC.SendmeUsersRouter] Start");

        // get required data
        String id = icAnsUsrMessage.get_id();
        UsersFirebaseMessage data = icAnsUsrMessage.get_usersFirebaseMessage();
        String jsonData = OMHelper.Parse(data);
        //connect.RegistryHandler(id, jsonData);
    }

    @PostMapping("/sendmeMovies") // in: path
    public void SendmeMoviesRouter(@RequestBody IcAnsMoviesMessage icAnsMoviesMessage)
    {
        System.out.println("API:[IRC.SendmeMoviesRouter] Start");

        // get required data
        String id = icAnsMoviesMessage.get_id();
        MovieMessage[] data = icAnsMoviesMessage.get_movies();
        String jsonData = OMHelper.Parse(data);
        //connect.RegistryHandler(id, jsonData);
    }*/
}
