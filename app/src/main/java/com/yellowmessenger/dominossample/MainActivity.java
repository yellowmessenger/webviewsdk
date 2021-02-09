package com.yellowmessenger.dominossample;

import android.graphics.Color;
import android.os.Bundle;

import com.example.ymwebview.BotEventListener;
import com.example.ymwebview.YMBotPlugin;
import com.example.ymwebview.models.BotEventsModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.messaging.FirebaseMessaging;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
//    String botId = "x1597918994847";

 // Home Credit
    String botId = "x1599123773718";


// fREADom
//    String botId = "x1589521906227"; // testing notification etc.
//    String botId = "x1585723893614"; // staging

//    String botId = "x1597918994847";


    // HLA
//    String botId = "x1592218269082";


//    String botId = "x1589521906227";

//    zenyum
//    String botId = "x1595342641260";

//    String botId = "x1569558732722"; // Dominos Staging





    HashMap<String, Object> payloadData = new HashMap<>();
    HashMap<String, Object> configurations = new HashMap<>();
    String configData;
    String firebaseToken;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);






        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        int actionBarColor = Color.parseColor("#ffffff");
        int actionBarColor = ContextCompat.getColor(this, R.color.customColor);
        int statusBarColor = ContextCompat.getColor(this, R.color.customColor);



        YMBotPlugin pluginYM =  YMBotPlugin.getInstance();

        configurations.put("botID", botId);
        configurations.put("enableSpeech", "false");
        configurations.put("enableHistory", "false");
//        configurations.put("disableCloseButton", "true");
//        configurations.put("hideCameraForUpload", "true");
        configurations.put("actionBarColor", Integer.toString(actionBarColor));
        configurations.put("statusBarColor", Integer.toString(statusBarColor));
        configData = YMBotPlugin.mapToString(configurations);
        HashMap<String, Object> customData = new HashMap<>();
        customData.put("imagePath","/storage/emulated/0/Pictures/JPEG_20200930_152654_7830371160876443634.jpg");
        pluginYM.setCustomData(customData);

        try {
            pluginYM.init(configData, new BotEventListener() {

                @Override
                public void onSuccess(BotEventsModel botEvent) {


                    switch (botEvent.getCode()){
                        case "request-camera-open" :
                            HashMap<String, Object> customData = new HashMap<>();
                            HashMap<String, Object> payloadData1 = new HashMap<>();
                            customData.put("imagePath","/storage/emulated/0/Pictures/JPEG_20200930_152654_7830371160876443634.jpg");
                            pluginYM.setCustomData(customData);
                            payloadData1.put("UserId","1602659471019");
                            payloadData1.put("platform","Android-App");
                            pluginYM.setPayload(payloadData1);
                            pluginYM.startChatBot(MainActivity.this);
                            payloadData1.toString();

                        break;
                        case "login-user" :
                            Log.d("Dominos Event", "onSuccess: "+botEvent.getData().toString());
                            HashMap<String, Object> payloadData3 = new HashMap<>();
                            payloadData3.put("UserLastName","qwerty");
                            payloadData3.put("UserState","LoggedIn");
                            payloadData3.put("platform","Android-App");
                            payloadData3.put("UserEmail","dfgh@fghj.com");
                            payloadData3.put("MobileNumber","8520805015");
                            payloadData3.put("UserFirstName","ytrewq");
                            payloadData3.put("UserId","15058877");
                            payloadData3.put("JourneySlug","track-my-order");
                            payloadData3.put("AccessToken","eyJraWQiOiJETjE2MUtTSEQrQjdReDV6bGhkNlZ0Z1dhbkZidU1WOU91WTcxdWk3TmZZPSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiJmZjY2NzI0Yy04ZThmLTQxNTYtOTUwNi00ZTZkODJhNWRjYjYiLCJldmVudF9pZCI6ImU5NjQ4YzVkLWJiZTEtNGQ3YS1hMjY4LTA5MjdkYTllYzkyOCIsInRva2VuX3VzZSI6ImFjY2VzcyIsInNjb3BlIjoiYXdzLmNvZ25pdG8uc2lnbmluLnVzZXIuYWRtaW4iLCJhdXRoX3RpbWUiOjE1ODYxNTIxMjYsImlzcyI6Imh0dHBzOlwvXC9jb2duaXRvLWlkcC5hcC1zb3V0aC0xLmFtYXpvbmF3cy5jb21cL2FwLXNvdXRoLTFfT2NWaVhWYVJiIiwiZXhwIjoxNTg2MjQxMDUyLCJpYXQiOjE1ODYyMzc0NTIsImp0aSI6ImJmOTI4NjJjLTdkMTUtNDFkMy1iNTkxLTliMGU0NTMyMjY1OCIsImNsaWVudF9pZCI6IjE1MWY1NXBuamJyNWU5b3Qzbjkzbmx0bms5IiwidXNlcm5hbWUiOiJmZjY2NzI0Yy04ZThmLTQxNTYtOTUwNi00ZTZkODJhNWRjYjYifQ.g9dqDDtJOw3IdQdmMchmRwAKgu5J5ZaspZYuFkJIA8-3k4vR2-Zi7ptXTCzNuRLQipYTNxhFY1nnrE8vZ9NWW6VSk5qahWI6tomru71bRUVTzxyYgfwBR3EMx1YG44WVipHKzMHdRbh311hYfXGwwNibpvrlui3EkZMPDYo0Xs6dHPEmz3qnNV3LV1VFnVzjQu6Lkf2CPBR5LemHzZwNlsafuj9PT0FT32P3Op07BrgFtIYdK6-xKnDnoxFYbX8fnGfxPGuf3tTFvujsPmLp1uKGas4PPJyrd3fb5XKJfnXPDLPkbojoPN7i5Uh3c6LvDc2PCHVyxyemyayC20gTGQ");
                            payloadData3.put("RefreshToken","eyJjdHkiOiJKV1QiLCJlbmMiOiJBMjU2R0NNIiwiYWxnIjoiUlNBLU9BRVAifQ.RXKdxc818ccDnmHacGVnETa_bXKmL6hQO4C4cb7sHEgIhuRBAa6v1BXfNR57Fs1p4fo2K-xaUEN6MZOo0V0OVn1T8WSayW22_08R4z3Rh92d2kPtWzTXIUuxRVtUH9kDo6HG4P0Rj7WqK4dJY412drpcn_uBQPlGhV15r6ZcrGk2WCz6fQAtrIYn56LlpPcdCFKoOIdAU7YnENGtbPHWKVGqdmBLCCVWlINGdcoOHNq202DUoChKLOYQmuX_P7bneiaWVkWTd_gehfpzS7z_icwvy5lPLBdfssnYxVaC7xjMW3LT3zT5WhEj-Xo-4yVVFMoey2YWaHYLKZMwJrlE6w.Slv2zJZtgHKBAdWX.K1Qjqp_fwZnIuPcQ1HDBfAJaiRDO1rTsc2i1C7zh6w0BY0I68Ct5EJfWu7aoXGOF99eGXPwHfXXkaMo1sgI1oN-ubbbyDiAQtTxISUAeyqHCQKQy_0CPCMpIzIOo1rlLiyXJxkl4hREH717TXTjoh0Bmb6KH4qxaB0IvMM6tYT4GlM_XLu6XJSk-AX_BtzEyhTREOkAVaJ6x8pTvpY-fAJlj8r5CMJXtbbDIyCOgfrlh6glK32a6SnlBTQYYddjMrx-dgI4xoyimPspsYFUqC0hA8zDKCqTm0xUtnAU8DCRH3T2i4O_BnomVfoZClOcwpW1VjHkYOzEfjsqDYg68faJ8_Rgdqff0TC3BG1SXKPxni5RgQO7w-pQBHLK9d6M2PjdlOY4mpdsnO6Fvb7oNH5Z-nkTM2slzundZkxjmPag-XEXcvY_6uabAgeMg6kUT7Acr1xe20Z2ZptnvSNt11mp6hzhmNXHBgS-9Gc4Zquqp0nHlH1SqKxrlaiCQqk6HPiimjdvWkBtczvJ--6X9-QDKi3OnD4Gi6Uk-Bz83xtwWqNvu2WbFu-25WRnHkGTzzJ2xN_2FU-s2Ufwthp4kb1a2ysI9ddDvNxeQrHfbYHU5SGW2spXhf_JLDoUDuaSMFiWkZxyggI-K4pBcxGH_9wWdpEInyP8sLXZSLkW7JW1jOof2PBNCZLz8sy875mM67tJiEDDLth4EhXMQ9kTQ3Fc5rbO22wYg2tVNSF1R8xYdXc9F80r_yntc1Z044RPzDeumYoG37rOSdpPLtex8hM9M986ALmAWTG8bra-OKkrIZQMewLUgJ9gnVRRZqtNLhpkHCPl75kmwizd0Gp7QuzC8Nt2re9EaXiCfBusKCeqcvP3Yt-DXEB67m2v1a6KcEyQuKALPY6xa7oOJ_DETVJaVHf11QfTJnS3wln-S7VvdmmOOUZHdhIJzqm18F-rrIJFmUY1KI0uwF2tWk8Y3Y3667tx1y3NQDDGA05hXjGdQ3EJxChySGGE_iyTj3ekcnpixhg4WqREgzeWAb9BHM-KP9HQ6AGBjM-6ySwly0oc7hG19u8oVZIyre8EJk4qNOL94Rumbn5d2rxln9gwG2Iq3bqQtKAcKQgN04xGHWhkg_y-fkHu91b1BkW3m-z6WzEt-5aFPR_NgK8d2Ct26Bl7A4n5aPsc8R_Ak8d5d2jjod85PaNRuWZL42wKpDOi1om1tt6HTPJFVgXamELSlbqS3-1oY8xX_r89UITKTx_9AgBlNZfUbnLKv2qEnpY8_2aYzWem5lq0nH96RZ9opdDrbtmiNL0h-ybO3GquUWe0cHrMUz2jMUF_1OIzzew.UTitsH1sBjYwekOGNthyAg");
                            pluginYM.setPayload(payloadData3);
                            pluginYM.startChatBot(MainActivity.this);

                        break;

                        case "mpin-or-device-lock" :
                            HashMap<String, Object> customData2 = new HashMap<>();
                            customData2.put("UserId","1602659471019");
                            customData2.put("permissions","pending");
                            customData2.put("platform","Android-App");
                            pluginYM.setPayload(customData2);
                            pluginYM.startChatBot(MainActivity.this);

                        break;

                        case "request-email-popup" :
                            HashMap<String, Object> customData3 = new HashMap<>();
                            customData3.put("UserId","1602659471019");
                            customData3.put("capture-manual",false);
                            customData3.put("platform","Android-App");
                            customData3.put("email", "ahmed@yellowmessenger.com");
                            pluginYM.setPayload(customData3);
                            pluginYM.startChatBot(MainActivity.this);
                        break;

                        case "get-encrypted-phone" :
                            HashMap<String, Object> customData4 = new HashMap<>();
                            customData4.put("UserId","1602659471019");
                            customData4.put("encrypted-phone","Lol");
                            customData4.put("platform","Android-App");
                            customData4.put("email", "ahmed@yellowmessenger.com");
                            pluginYM.setPayload(customData4);
                            pluginYM.startChatBot(MainActivity.this);
                        break;
                    }
                }

                @Override
                public void onFailure(String error) {
                }
            });
        }
        catch (RuntimeException e){
            Log.w("Plugin Exception", "onCreate: "+e.getMessage());
        }

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("Notification", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();
                        firebaseToken = token;
                        payloadData.put("Platform", "Android-App");
                        payloadData.put("deviceToken", firebaseToken);

                        pluginYM.setPayload(payloadData);

                        // Log and toast
                        String msg = token;
                        Log.d("Notification", msg);
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }


                });


        Switch languageSwitch = findViewById(R.id.toggleLanguage);

        languageSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    payloadData.put("Platform", "Android-App");
                    payloadData.put("defaultLanguage", "hi");
                    pluginYM.setPayload(payloadData);
                } else {
                    payloadData.put("Platform", "Android-App");
                    payloadData.put("defaultLanguage", "en");
                    pluginYM.setPayload(payloadData);
                }
            }
        });


// let obj = {
//     "treatmentID": "5f80321c0bf784701b43bb0d",
//     "devicePlatform": "IOS",
//     "userId": "5f8030a40bf784701b43bb0a",
//     "email": "shani111@zenyumtest.com",
//     "firstName": "Shani",
//     "lastName": "Shahul",
//     "country": "Singapore",
//     "phoneNumber": " ",
//     "employeeEmail": "akshayv@zenyumtest.com",
//     "employeeFirstName": "Akshay",
//     "employeeLastName": "Venugopal"
// }

        payloadData.put("Platform", "Android-App");
//        payloadData.put("deviceToken", firebaseToken);
        payloadData.put("UserState", "Anonymous");


        pluginYM.setPayload(payloadData);



        //Setting Color
//        HashMap<String, Object> customSettings = new HashMap<> ();
//        int actionBarColor = ContextCompat.getColor(this, R.color.colorPrimary);
//        int statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark);
//        customSettings.put("actionBarColor", String.valueOf(actionBarColor));
//        customSettings.put("statusBarColor", String.valueOf(statusBarColor));
//        pluginYM.setCustomData(customSettings);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
//            pluginYM.setBotId(botId2);
           pluginYM.startChatBot(this);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
