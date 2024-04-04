package com.example.gcloud_ms_api.user;

import com.example.gcloud_ms_api.communication.Connect;
import com.example.gcloud_ms_api.messages.ApiFrontUsers;
import com.example.gcloud_ms_api.messages.apiMessage;
import com.example.gcloud_ms_api.utility.OMHelper;
import com.example.gcloud_ms_innerconnect.messages.IcMessage;
import com.example.gcloud_ms_users.user.messages.IcNewUsrMessage;

public class Model
{
    Connect connect = Connect.GetSingleton();

    public Model() {}

    public IcMessage GetAll(ApiFrontUsers users)
    {
        // Note: Set id as blank to trigger automatic UUID internally.
        apiMessage apiMessage = new apiMessage(users.get_id(),"user","getAll","UserSrv","");
        IcMessage message = new IcMessage(apiMessage);

        String output = OMHelper.Parse(message);
        System.out.println("API[Model.GetAll] message=" + output);

        // Send to innerconnect service
        connect.SendData(message, "sendme");

        return message;
    }

    public IcMessage Create(ApiFrontUsers users)
    {
        // Note: Set id as blank to trigger automatic UUID internally.
        //String data = OMHelper.Parse(users.get_data());
        //apiMessage apiMessage = new apiMessage(users.get_id(),"user","create","UserSrv", data);
        //IcMessage message = new IcMessage(apiMessage);

        // Create the IcNewUserMessage
        String userName = users.get_data().get_userName();
        String password = users.get_data().get_password();
        System.out.println("API:[User:Model.Create] new-item=" + "username=" + userName + ",password=" + password);
        IcNewUsrMessage message = new IcNewUsrMessage(users.get_id(),"user","create","UserSrv",userName,password);

        String output = OMHelper.Parse(message);
        System.out.println("API:[User:Model.Create] message=" + output);

        // Send to innerconnect service
        // Expects: IcNewUsrMessage
        connect.SendData(message, "sendmeNewuser");

        return message;
    }
}
