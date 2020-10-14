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
//    String botId = "x1589521906227";



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
                        case "request-camera-open" :
                            HashMap<String, Object> customData = new HashMap<>();
                            customData.put("imagePath","/storage/emulated/0/Pictures/JPEG_20200930_152654_7830371160876443634.jpg");
                            pluginYM.setCustomData(customData);
                            payloadData.put("Platform", "Android-App");
                            payloadData.put("UserId", "Priyank");
                            pluginYM.setPayload(payloadData);
                            pluginYM.startChatBot(MainActivity.this);

                            break;
                        case "event-code-2" :
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
        payloadData.put("UserId", "Priyank");



        pluginYM.setPayload(payloadData);

        //Setting image path







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
