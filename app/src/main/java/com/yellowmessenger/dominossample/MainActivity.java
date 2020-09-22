package com.yellowmessenger.dominossample;

import android.os.Bundle;

import com.example.ymwebview.BotEventListener;
import com.example.ymwebview.YMBotPlugin;
import com.example.ymwebview.models.BotEventsModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
//    String botId = "x1597918994847";

 // Home Credit
    String botId = "x1599123773718";
//    String botId = "x1587388939730";

    // Dominos
//    String botId = "x1572447766397";

//TATA Cap
//    String botId = "x1593069769065";

    String configData = "{" +
            "\"botID\": \""+botId+"\"," +
            "\"enableSpeech\": \"false\"" +
            "}";

// IDK
//    String configData = "{" +
//            "\"botName\": \"SomeBotName\"," +
//            "\"botID\": \"x1562765523121\"," +
//            "\"enableSpeech\": \"false\"" +
//            "}";

//MPL
//        String configData = "{" +
//            "\"botName\": \"SomeBotName\"," +
//            "\"botID\": \"x1592302653259\"," +
//            "\"enableSpeech\": \"false\"" +
//            "}";

    HashMap<String, Object> payloadData = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        YMBotPlugin pluginYM =  YMBotPlugin.getInstance();
        try {
            pluginYM.init(configData, new BotEventListener() {

                @Override
                public void onSuccess(BotEventsModel botEvent) {



                    switch (botEvent.getCode()){
                        case "mpin-or-device-lock" :
                            HashMap<String, Object> payloadData1 = new HashMap<>();
                            payloadData1.put("Platform", "Android-App");
                            payloadData1.put("permissions", "allowed");
                            payloadData1.put("UserId", "9640203005");
                            pluginYM.setPayload(payloadData1);
                            pluginYM.startChatBot(MainActivity.this);
                            break;
                        case "request-email-popup" :
                            HashMap<String, Object> payloadData2 = new HashMap<>();
                            payloadData2.put("Platform", "Android-App");
                            payloadData2.put("email", "ahmed@yellowmessenger.com");
                            payloadData2.put("UserId", "9640203005");
                            payloadData2.put("capture-email", false);
                            pluginYM.setPayload(payloadData2);
                            pluginYM.startChatBot(MainActivity.this);
                            break;
                        case "user-message" :
                            Log.d("bleeeehhhh", "Yeaaaaaahhhh");
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

        payloadData.put("Platform", "Android-App");
//        payloadData.put("userStatus", "new");
//        payloadData.put("userName", "");
//        payloadData.put("UserId", "9640203005");
//        payloadData.put("userMobile", "9640203005");
        pluginYM.setPayload(payloadData);






        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
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
