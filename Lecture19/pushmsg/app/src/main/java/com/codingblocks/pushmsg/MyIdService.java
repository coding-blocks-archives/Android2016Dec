package com.codingblocks.pushmsg;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by championswimmer on 19/02/17.
 */

public class MyIdService extends FirebaseInstanceIdService {

    public static final String TAG = "FBS";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String myToken = FirebaseInstanceId.getInstance().getToken();

        Log.d(TAG, "onTokenRefresh: " + myToken);
    }
}
