Yellow Messenger WebView SDK
=======================

### Configuration

Root level gradle file
```gradle
allprojects {
    repositories {
        jcenter()
        // Add these two lines 
        maven { url "https://jitpack.io" }
        maven { url "https://oss.sonatype.org/content/repositories/snapshots/" }
    }
}
```
App level gradle file
```gradle
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])

    implementation 'com.github.yellowmessenger:webviewsdk:TAG'

}
```

Android Application class
```java
import com.example.ymwebview.BotEventListener;
import com.example.ymwebview.YMBotPlugin;
import com.example.ymwebview.models.BotEventsModel;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        String configData = "{" +
            "\"botName\": \"Dominos\"," +
            "\"botID\": \"x1572447766397\"" +
            "}";
        HashMap<String, Object> payloadData = new HashMap<>();
        payloadData.put("platform","Android-App");
        payloadData.put("user-id","");
        payloadData.put("access-token","");
        payloadData.put("refresh-token","");
        payloadData.put("mobile-number","");
        payloadData.put("order-id","");
        payloadData.put("journey-slug","");
        payloadData.put("user-state","Android-App");
        YMBotPlugin pluginYM =  YMBotPlugin.getInstance();
        
        pluginYM.init(this ,configDataSandbox, new BotEventListener() {
            @Override
            public void onSuccess(BotEventsModel botEvent) {
                Log.d("EventListener", "Event Recieved: "+ botEvent.getCode());
                switch (botEvent.getCode()){
                    case "test" : break;
                    case "track-order" : break;
                    case "combos-and-offers" :  break;
                    case "token-expire" : break;
                    case "login-user" : break;
                    case "stores-near-me" :  break;
                }
            }

            @Override
            public void onFailure(String error) {
            }
        });
        pluginYM.setPayload(payloadData);
        
        //To start chabot call the pluginYm.startChatBot() method.
         FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
           pluginYM.startChatBot();
        });
    }
    

}
```


