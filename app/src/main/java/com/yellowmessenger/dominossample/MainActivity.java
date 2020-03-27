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

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    String configData = "{" +
            "\"botName\": \"SomeBotName\"," +
            "\"botID\": \"x1569558732722\"," +
            "\"enableHistory\": \"true\"" +
            "}";


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
                        case "even-name-1" : break;
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
