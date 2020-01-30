package com.example.ymwebview;


import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;

import  com.example.ymwebview.models.ConfigDataModel;

import java.util.Map;

public class YMBotPlugin {
    Context myContext;
    Intent _intent;


    public void initYMPlugin(Context context, String configData){
        ConfigDataModel.getInstance().setConfig(new Gson().fromJson(configData, Map.class));
        myContext = context;

    }
    public  void startChatBot(){
        myContext.startActivity(_intent);
    }
    public void setPayload(Map botPayload){
        ConfigDataModel.getInstance().setPayload(botPayload);
        _intent = new Intent(myContext, BotWebView.class);


    }

}

