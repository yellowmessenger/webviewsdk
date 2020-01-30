package com.example.ymwebview;


import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.ymwebview.models.BotEventsModel;
import com.google.gson.Gson;

import  com.example.ymwebview.models.ConfigDataModel;

import java.util.Map;

public class YMBotPlugin {
    private Context myContext;
    private Intent _intent;
    private BotEventListener listener;
    private static YMBotPlugin botPluginInstance;
    private boolean isInitialized;

    private YMBotPlugin(){}

    public static  YMBotPlugin getInstance(){
        if (botPluginInstance == null) {
            synchronized (YMBotPlugin.class) {
                if (botPluginInstance == null) {
                    botPluginInstance = new YMBotPlugin();
                }
            }
        }
        return  botPluginInstance;
    }

    public void init(Context context, String configData, BotEventListener listener){
        if(!isInitialized){
            isInitialized = true;
            if (context != null && configData != null && listener != null) {
                ConfigDataModel.getInstance().setConfig(new Gson().fromJson(configData, Map.class));
                myContext = context;
                this.listener = listener;
            } else {
                throw new RuntimeException("Mandatory arguments not present");
            }
        } else {
            throw new RuntimeException("Cannot initialize " + this.getClass().getName() + " multiple times");
        }
    }
    public  void startChatBot(){
        myContext.startActivity(_intent);
    }

    public void setPayload(Map botPayload){
        ConfigDataModel.getInstance().setPayload(botPayload);
        _intent = new Intent(myContext, BotWebView.class);
    }

    public void emitEvent(BotEventsModel event){
        if(event != null){
            Log.v("WebView","From Bot: "+event.getCode());
            listener.onSuccess(event);
        }
        else
            listener.onFailure("An error occurred.");
    }

}

