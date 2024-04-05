package com.example.gcloud_ms_users.user.adapters;

import com.example.gcloud_ms_api.utility.OMHelper;
import com.example.gcloud_ms_users.user.messages.IcAnsUsrMessage;
import com.example.gcloud_ms_users.user.messages.UserMessage;
import com.example.gcloud_ms_users.user.messages.UsersFirebaseMessage;

public class Adp_UFM_ICM
{
    private IcAnsUsrMessage _icAnsUsrMessage;

    public Adp_UFM_ICM(UsersFirebaseMessage usersFirebaseModel)
    {
        String id = usersFirebaseModel.get_id();
        String destSrv = usersFirebaseModel.get_destSrv();
        UserMessage[] users = usersFirebaseModel.Users();
        System.out.println("Users:[Adp_UFM_ICM.ctr] id=" + id + ",destSrv=" + destSrv + ",users=" + OMHelper.Parse(users));
        _icAnsUsrMessage = new IcAnsUsrMessage(id, destSrv, users);
    }

    public IcAnsUsrMessage Output()
    {
        return _icAnsUsrMessage;
    }
}
