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
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    String configData = "{" +
            "\"botName\": \"Adani Electra\"," +
            "\"botID\": \"x1565100503080\"" +
            "}";

    HashMap<String, Object> payloadData = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        YMBotPlugin pluginYM =  YMBotPlugin.getInstance();
        pluginYM.init(this ,configData, new BotEventListener() {

            @Override
            public void onSuccess(BotEventsModel botEvent) {
                switch (botEvent.getCode()) {
                    case "test":
                        Toast.makeText(getApplicationContext(), "Recieved Event: " + botEvent.getCode() + " : " + botEvent.getData(), Toast.LENGTH_SHORT).show();
                        break;
                    case "track-my-order":
                        Toast.makeText(getApplicationContext(), "Recieved Event: " + botEvent.getCode() + " : " + botEvent.getData(), Toast.LENGTH_SHORT).show();
                        break;
                    case "offers-and-combos":
                        Toast.makeText(getApplicationContext(), "Recieved Event: " + botEvent.getCode() + " : " + botEvent.getData(), Toast.LENGTH_SHORT).show();
                        break;
                    case "renew-token":
                        Toast.makeText(getApplicationContext(), "Recieved Event: " + botEvent.getCode() + " : " + botEvent.getData(), Toast.LENGTH_SHORT).show();
                        break;
                    case "login-user":
                        Toast.makeText(getApplicationContext(), "Recieved Event: " + botEvent.getCode() + " : " + botEvent.getData(), Toast.LENGTH_SHORT).show();
                        break;
                    case "locate-stores":
                        Toast.makeText(getApplicationContext(), "Recieved Event: " + botEvent.getCode() + " : " + botEvent.getData(), Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onFailure(String error) {
            }
        });

        payloadData.put("Platform", "Android-App");
        pluginYM.setPayload(payloadData);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
           pluginYM.startChatBot();
        });
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
