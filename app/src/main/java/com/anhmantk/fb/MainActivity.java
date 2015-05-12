package com.anhmantk.fb;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.*;
import com.facebook.login.*;
import com.facebook.login.LoginResult;
import java.util.Arrays;

import android.content.Intent;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
    CallbackManager callbackManager;
    TextView txt1;
    AccessTokenTracker accessTokenTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        Log.e("login","start login facebook");

        setContentView(R.layout.layout2);

        txt1 = (TextView) findViewById(R.id.txt1);
        txt1.setText("Login with facebook");
        callbackManager = CallbackManager.Factory.create();


        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "user_friends", "email"));
        LoginManager.getInstance().logInWithPublishPermissions(this, Arrays.asList("publish_actions"));

        Log.e("login","den doan nay roi");
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        txt1.setText("Login ");
                        Log.e("login", loginResult.getAccessToken().toString());
                    }

                    @Override
                    public void onCancel() {
                        txt1.setText("Huy dang nhap");
                        Log.e("login", "==> onCancel");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        txt1.setText("Dang nhap co loi");
                        Log.e("login", "Error: " + exception.getMessage().toString());
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
