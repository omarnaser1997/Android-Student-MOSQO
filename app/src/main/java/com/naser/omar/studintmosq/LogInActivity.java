package com.naser.omar.studintmosq;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

public class LogInActivity extends AppCompatActivity {
    LoginButton login_button;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        printKeyHash();


        loginfacebook();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //The CallbackManager manages
        // the callbacks into the FacebookSdk from an Activity's
        // or Fragment's onActivityResult() method.
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void loginfacebook() {
        //just init Facebook into MainActivity
        FacebookSdk.sdkInitialize(getApplicationContext());

        callbackManager= CallbackManager.Factory.create();
        login_button=(LoginButton)findViewById(R.id.login_button);
        login_button.setReadPermissions("public_profile","email","user_birthday","user_friends");

        //callbackManager معلومات الحساب بعد تنفيذ onActivityResult
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            //اذا نجح تسجيل الدخول
            @Override
            public void onSuccess(LoginResult loginResult) {






                GraphRequest request =GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        Intent Addstudiint =new Intent(LogInActivity.this,MainActivity1.class);
                        startActivity(Addstudiint);



                    }
                });




                //Request Graph API
                Bundle parameters=new Bundle();
                parameters.putString("fields","id,name,email,birthday,friends");

                request.setParameters(parameters);
                //  تنفيذ الطلب بشكل غير متزامن
                //  ستعود هذه الوظيفة على الفور و وسيتم معالجة الطلب على مؤشر ترابط منفصل من أجل معالجه نتيجه الطلب
                //  و تحديد ما إذا كان الطلب قد نجح أو فشل
                //  وهذا التابع يعيد الوظيفة المطلوبه منه
                request.executeAsync();



            }

            @Override
            public void onCancel() {


            }

            @Override
            public void onError(FacebookException error) {


            }
        });

    }

    private void printKeyHash() {
        try{
            PackageInfo info =getPackageManager().getPackageInfo(
                    "com.naser.omar.facebookkit",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature:info.signatures)
            {
                MessageDigest md =MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(),Base64.DEFAULT));

            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
