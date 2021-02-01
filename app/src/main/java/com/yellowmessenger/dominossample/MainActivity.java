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
        configurations.put("enableHistory", "true");
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
        payloadData.put("deviceToken", firebaseToken);


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
