package com.example.gcloud_ms_api.user;

import com.example.gcloud_ms_api.messages.apiMessage;
import com.example.gcloud_ms_api.utility.OMHelper;

public class Model
{
    public Model() {}

    public void GetAll()
    {
        apiMessage message = new apiMessage("{uuid?}","user","getAll","UserSrv"); // todo: research uuid equivalent

        String output = OMHelper.Parse(message);
        System.out.println("API[Model.GetAll] message=" + output);
    }

    public void Create()
    {

    }
}
