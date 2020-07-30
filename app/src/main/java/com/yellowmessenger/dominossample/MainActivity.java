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

//TATA Cap
    String botId = "x1593069769065";
    String configData = "{" +
            "\"botID\": \""+botId+"\"," +
            "\"enableSpeech\": \"true\"" +
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
                        case "even-name-1" :
                            Log.d("Bot event", botEvent.getData()); break;
                        case "even-name-2" : break;
                        case "even-name-3" : break;
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
