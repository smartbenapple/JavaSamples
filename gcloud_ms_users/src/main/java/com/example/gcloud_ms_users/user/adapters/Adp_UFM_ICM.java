package com.example.gcloud_ms_users.user.adapters;

import com.example.gcloud_ms_users.user.messages.IcAnsUsrMessage;
import com.example.gcloud_ms_users.user.messages.UsersFirebaseMessage;

public class Adp_UFM_ICM
{
    private IcAnsUsrMessage _icAnsUsrMessage;

    public Adp_UFM_ICM(UsersFirebaseMessage usersFirebaseModel)
    {
        _icAnsUsrMessage = new IcAnsUsrMessage("","","",usersFirebaseModel.get_id(),"","",usersFirebaseModel.get_destSrv(), usersFirebaseModel);
    }

    public IcAnsUsrMessage Output()
    {
        return _icAnsUsrMessage;
    }
}
