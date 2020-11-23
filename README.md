Yellow Messenger WebView SDK
=======================

### Configuration


App level gradle file
```gradle
allprojects {
    repositories {
        jcenter()
        // Add these two lines 
        maven { url "https://jitpack.io" }
        maven { url "https://oss.sonatype.org/content/repositories/snapshots/" }
    }
}

...
...
...

dependencies {
    ...
    ...
	   implementation 'com.github.yellowmessenger:webviewsdk:0.3.2'


}
```
Android Application class Example
```java
import com.example.ymwebview.BotEventListener;
import com.example.ymwebview.YMBotPlugin;
import com.example.ymwebview.models.BotEventsModel;

public class MainActivity extends AppCompatActivity {
    
    @Override
    public void onCreate() {
        super.onCreate();
        //Configuration data
        HashMap<String, Object> configurations = new HashMap<>();
        String configData;
        //Payload attributes
        HashMap<String, Object> payloadData = new HashMap<>();
        //Important
        payloadData.put("platform","Android-App");
        
        payloadData.put("UserId","<Some-Unique-Identifier>");
        payloadData.put("access-token","");
        payloadData.put("refresh-token","");
        payloadData.put("mobile-number","");
        payloadData.put("journey-slug","");
        payloadData.put("user-state","");
        //You can add other payload attributes in the same format.
        
        //Initialize the bot
        YMBotPlugin pluginYM =  YMBotPlugin.getInstance();

        //Setting Config data.
        configurations.put("botID", botId);
        configurations.put("enableSpeech", "false");
        configurations.put("enableHistory", "true");
        configurations.put("actionBarColor", Integer.toString(actionBarColor));
        configurations.put("statusBarColor", Integer.toString(statusBarColor));
        configData = YMBotPlugin.mapToString(configurations);




        pluginYM.init(configData, new BotEventListener() {
            @Override
            public void onSuccess(BotEventsModel botEvent) {
                Log.d("EventListener", "Event Recieved: "+ botEvent.getCode());
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
        //Send Payload Data
        pluginYM.setPayload(payloadData);
        
        //To start chabot call the pluginYm.startChatBot() method.
         FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
           //Starting the bot activity
           pluginYM.startChatBot(this);
        });
    }
    

}

```
## Features
The SDK takes in the botId as a input configuration when the plugin is initialised. to change the default BotId, use the following method.

```java
 pluginYM.setBotId("<NEW-BOT-ID>");
```

```
## Important
If facing problem in release build, add the following configuration in the app's proguard-rules.pro file.
```java
-keep public class com.example.ymwebview.** {
   *;
}
```


