package com.example.gcloud_ms_innerconnect;

import com.example.gcloud_ms_api.utility.OMHelper;
import com.example.gcloud_ms_movies.adapters.Adp_MFM_ICM;
import com.example.gcloud_ms_movies.messages.IcAnsMoviesMessage;
import com.example.gcloud_ms_movies.messages.IcNewMovieMessage;
import com.example.gcloud_ms_movies.messages.MoviesFirebaseMessage;
import com.example.gcloud_ms_users.user.adapters.Adp_UFM_ICM;
import com.example.gcloud_ms_users.user.messages.IcAnsUsrMessage;
import com.example.gcloud_ms_innerconnect.messages.IcMessage;
import com.example.gcloud_ms_users.user.messages.IcNewUsrMessage;
import com.example.gcloud_ms_users.user.messages.UsersFirebaseMessage;
import org.springframework.web.bind.annotation.*;

// gCloud: https://gcloud-ms-innerconnect-axxh6chama-wl.a.run.app
// locally: server.port=${port:8081}
// Cors => https://spring.io/guides/gs/rest-service-cors
//    Use:  @CrossOrigin(origins = "*", allowedHeaders = "Origin, X-Requested-With, Content-Type, Accept",methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RestController
public class IndexRestController
{
    Innerconnect connect = new Innerconnect();

    @CrossOrigin(origins = "*", allowedHeaders = "Origin, X-Requested-With, Content-Type, Accept",methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS})
    @GetMapping("/sendme") // in: path
    public String TestResponse()
    {
        return "Innerconnect:[IndexRestController.TestResponse] Test Connection Success.";
    }

    @CrossOrigin(origins = "*", allowedHeaders = "Origin, X-Requested-With, Content-Type, Accept",methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS})
    @PostMapping("/sendme") // in: path
    public String AddData(@RequestBody IcMessage icMessage)
    {
        String message = OMHelper.Parse(icMessage);
        System.out.println("IC:[IRC.AddData] Start > message=" + message);
        return connect.AddData(icMessage);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "Origin, X-Requested-With, Content-Type, Accept",methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS})
    @PostMapping("/sendmeNewmovie") // in: path
    public String MovieAddData(@RequestBody IcNewMovieMessage icNewMovieMessage)
    {
        String message = OMHelper.Parse(icNewMovieMessage);
        System.out.println("IC:[IRC.MovieAddData] Start > message=" + message);
        return connect.AddData(icNewMovieMessage);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "Origin, X-Requested-With, Content-Type, Accept",methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS})
    @PostMapping("/sendmeNewuser") // in: path
    public String UserAddData(@RequestBody IcNewUsrMessage icNewUsrMessage)
    {
        String message = OMHelper.Parse(icNewUsrMessage);
        System.out.println("IC:[IRC.UserAddData] Start > Message=" + message);
        return connect.AddData(icNewUsrMessage);
    }

    // Expect to receive: {"id":"", "destSrv":"","data":"[{"password":"...","username":"..."}]"}
    @CrossOrigin(origins = "*", allowedHeaders = "Origin, X-Requested-With, Content-Type, Accept",methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS})
    @PostMapping("/usersAnswer")
    public String UserAddData(@RequestBody UsersFirebaseMessage usersFirebaseModel)
    {
        System.out.println("IC:[IRC.UserAddData] Start");

        Adp_UFM_ICM adapter = new Adp_UFM_ICM(usersFirebaseModel);
        IcAnsUsrMessage object = adapter.Output();
        object.set_path("/sendmeUsers");
        return connect.AddData(object);
    }

    // Expect to receive: {"id":"", "destSrv":"","data":"[{"title":"...","year":"..."}]"}
    @CrossOrigin(origins = "*", allowedHeaders = "Origin, X-Requested-With, Content-Type, Accept",methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS})
    @PostMapping("/moviesAnswer")
    public String MoviesAddData(@RequestBody MoviesFirebaseMessage moviesFirebaseMessage) // MoviesFirebaseMessage moviesFirebaseMessage
    {
        System.out.println("IC:[IRC.MoviesAddData] Start");

        Adp_MFM_ICM adapter = new Adp_MFM_ICM(moviesFirebaseMessage);
        IcAnsMoviesMessage object = adapter.Output();
        object.set_path("/sendmeMovies");
        return connect.AddData(object);
    }
}
