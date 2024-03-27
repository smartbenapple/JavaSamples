package com.example.gcloud_ms_api.user;

import com.example.gcloud_ms_api.messages.apiMessage;
import com.example.gcloud_ms_api.utility.OMHelper;

public class Model
{
    public Model() {}

    public void GetAll()
    {
        // Note: Set id as blank to trigger automatic UUID internally.
        apiMessage message = new apiMessage("","user","getAll","UserSrv"); // todo: research uuid equivalent

        String output = OMHelper.Parse(message);
        System.out.println("API[Model.GetAll] message=" + output);
    }

    public void Create()
    {

    }
}
