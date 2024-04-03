package com.example.gcloud_ms_innerconnect;

import com.example.gcloud_ms_movies.adapters.Adp_MFM_ICM;
import com.example.gcloud_ms_movies.messages.IcAnsMoviesMessage;
import com.example.gcloud_ms_movies.messages.IcNewMovieMessage;
import com.example.gcloud_ms_movies.messages.MoviesFirebaseMessage;
import com.example.gcloud_ms_users.user.adapters.Adp_UFM_ICM;
import com.example.gcloud_ms_users.user.messages.IcAnsUsrMessage;
import com.example.gcloud_ms_innerconnect.messages.IcMessage;
import com.example.gcloud_ms_users.user.messages.IcNewUsrMessage;
import com.example.gcloud_ms_users.user.messages.UsersFirebaseMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// gCloud: https://gcloud-ms-movies-axxh6chama-wl.a.run.app
// locally: server.port=${port:8081}
@RestController
public class IndexRestController
{
    Innerconnect connect = new Innerconnect();

    @GetMapping("/sendme") // in: path
    public String TestResponse()
    {
        return "Innerconnect:[IndexRestController.TestResponse] Test Connection Success.";
    }

    @PostMapping("/sendme") // in: path
    public String AddData(@RequestBody IcMessage icMessage)
    {
        return connect.AddData(icMessage);
    }

    @PostMapping("/sendmeNewmovie") // in: path
    public String MovieAddData(@RequestBody IcNewMovieMessage icNewMovieMessage)
    {
        return connect.AddData(icNewMovieMessage);
    }

    @PostMapping("/sendmeNewuser") // in: path
    public String UserAddData(@RequestBody IcNewUsrMessage icNewUsrMessage)
    {
        return connect.AddData(icNewUsrMessage);
    }

    // Expect to receive: {"id":"", "destSrv":"","data":"[{"password":"...","username":"..."}]"}
    @PostMapping("/usersAnswer")
    public String UserAddData(@RequestBody UsersFirebaseMessage usersFirebaseModel)
    {
        Adp_UFM_ICM adapter = new Adp_UFM_ICM(usersFirebaseModel);
        IcAnsUsrMessage object = adapter.Output();
        object.set_path("/sendmeUsers");
        return connect.AddData(object);
    }

    // Expect to receive: {"id":"", "destSrv":"","data":"[{"title":"...","year":"..."}]"}
    @PostMapping("/moviesAnswer")
    public String MoviesAddData(@RequestBody MoviesFirebaseMessage moviesFirebaseMessage) // MoviesFirebaseMessage moviesFirebaseMessage
    {
        Adp_MFM_ICM adapter = new Adp_MFM_ICM(moviesFirebaseMessage);
        IcAnsMoviesMessage object = adapter.Output();
        object.set_path("/sendmeMovies");
        return connect.AddData(object);
    }
}
