package com.example.gcloud_ms_api.user;

import com.example.gcloud_ms_api.communication.Connect;
import com.example.gcloud_ms_api.messages.apiMessage;
import com.example.gcloud_ms_api.utility.OMHelper;
import com.example.gcloud_ms_innerconnect.messages.IcMessage;

public class Model
{
    Connect connect = new Connect();

    public Model() {}

    public IcMessage GetAll()
    {
        // Note: Set id as blank to trigger automatic UUID internally.
        apiMessage apiMessage = new apiMessage("","user","getAll","UserSrv");
        IcMessage message = new IcMessage(apiMessage);

        String output = OMHelper.Parse(message);
        System.out.println("API[Model.GetAll] message=" + output);

        // Send to innerconnect service
        connect.SendData(message, "sendme");

        return message;
    }

    public void Create()
    {

    }
}
