package com.example.salmanmapkar.demo.Service;

import com.example.salmanmapkar.demo.Common;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import com.example.salmanmapkar.demo.Common;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Salman Mapkar on 06-04-2018.
 */

public class MyFirebaseidService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String RefreshedToken = FirebaseInstanceId.getInstance().getToken();
        Common.currentToken = RefreshedToken;
    }
}