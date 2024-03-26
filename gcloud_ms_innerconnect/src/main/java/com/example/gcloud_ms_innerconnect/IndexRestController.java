package com.example.gcloud_ms_innerconnect;

import com.example.gcloud_ms_movies.messages.MoviesFirebaseMessage;
import com.example.gcloud_ms_users.user.adapters.Adp_UFM_ICM;
import com.example.gcloud_ms_users.user.messages.IcAnsUsrMessage;
import com.example.gcloud_ms_users.user.messages.IcMessage;
import com.example.gcloud_ms_users.user.messages.UsersFirebaseMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    // Expect to receive: {"id":"", "destSrv":"","data":"[{"password":"...","username":"..."}]"}
    @PostMapping("/useranswer")
    public String UserAddData(@RequestBody UsersFirebaseMessage usersFirebaseModel)
    {
        Adp_UFM_ICM adapter = new Adp_UFM_ICM(usersFirebaseModel);
        return connect.AddData(adapter.Output());
    }

    // Expect to receive: {"id":"", "destSrv":"","data":"[{"title":"...","year":"..."}]"}
    @PostMapping("/movieanswer")
    public String MoviesAddData(@RequestBody MoviesFirebaseMessage moviesFirebaseMessage)
    {
        // todo: requires an IcMoviesMessage class and adapter.
        //Adp_UFM_ICM adapter = new Adp_UFM_ICM(moviesFirebaseMessage);
        //return connect.AddData(adapter.Output());
        return "Success";
    }
}
