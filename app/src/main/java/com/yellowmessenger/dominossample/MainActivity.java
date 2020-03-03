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
            "\"botName\": \"Dominos\"," +
            "\"botID\": \"x1569558732722\"," +
            "\"enableSpeech\": \"false\"," +
            "\"enableHistory\": \"true\"" +
            "}";
    String configDataTest = "{" +
            "\"botName\": \"Foodie\"," +
            "\"botID\": \"x1577702191385\"" +
            "}";

    HashMap<String, Object> payloadData = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        YMBotPlugin pluginYM =  YMBotPlugin.getInstance();
        pluginYM.init(configData, new BotEventListener() {

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
                        //Start login activity.
                        HashMap<String, Object> payloadData1 = new HashMap<>();
                        payloadData1.put("Platform", "Android-App");
                        payloadData1.put("UserId", "143448352");
                        payloadData1.put("JourneySlug", "issue-with-my-orders");
                        payloadData1.put("RefreshToken", "eyJjdHkiOiJKV1QiLCJlbmMiOiJBMjU2R0NNIiwiYWxnIjoiUlNBLU9BRVAifQ.ibnFH8Hw_aWrVV6YKj2DilmFGuQnjw4QI-_EnRWXeL2v7thp4zAOdLB6AnAqXqHcLM5BVybi9hhKDhOdO9eij1WYLExSmMkieMXEoRvyN9j4bIRkiBfabdu2Rc1yeA6AEvpT4yQjoCjU_8y_gG6jlOhXC21CS6NIBvuzNnh04jnW6PyIsR4I1SfFrQmpzsbP7evS5STkh4oipgIoQEzNKEZgoiUOoLnU_0aUcdHthumMfL0Pa1HIaNJP3opvTtoKvJsDpOyElEx8mnW7aPiYLYMkIRQHckKF2NUWxbLhiugRTVp1BkwBEM4Rg1alv5TaUV_y92IZAvGyedIZButO-Q.dFL3L4nypMWBRmde.TNNMuCgLqcEJxrfX2PsGnZpc3ncdV5-a8wAMKrJKNuILiXD9o41n0LdD9ZE1RHBl4T4aoKInigpVbjur68yl_Wkl5Gk-VqMNghC1xm4rOgRLvk3zyiKqIP0hf2UApTrS2p5bvMhsA_10jYIzUEFCzC2WYnbwxrTzv5Rb4kHt3qNh3qZXzt_BYtrGCrmXt16cft1FcRENV58W60yI4E3bU0fA82W7u5HfUbxbnHSzxeeIQmPkA8nCEjQHdzydVpYTji_NTMaWr8VhaENCSqgdqTHjTVxDXGz-PlwYUVCexEJCSlDoTe7OLXTsw0IutmqbNkx-7l2gJC_z5KnLtY7XXPqp1XWbSQyGclV7_HLJX6_A0U3BKkKB9K1dxIgLPKLs5gnDBy6VuXVZz16MhOCuMbjMJ4qFb6t1aYbAfZ812JDOjONzMTK3-fgfVP1_e9MZZ20Mi3_uUNb3frzzeNEs9-MYZWtT7i5_35q2MzDThiOBDiDctI6bl7ATx-5dauwI4Vzj--zQSPb80nNXdHqiu_arlUxZWvetnVV1CCJWQ4zfISyVSXdo2pGJ-Rh8KIh5wyYnGmZ785twME43Sslu0bhob9IuDZdYqv2f8ywZZvYfDWgrNiWEsXMMGH8Ape0hXJ_67xyv9O9mZzTrtH5ay3rpNYGnPcjdb_8ijeN1gIaJL8jhIeVkza3Qza_b8feHK8LzufIvyjvipmujkln6cCsxVmSS8RYfspJYzn0vD4kCr8a8DvRGtsD5LJopOIiTOMxtKwxPKN-lKBo3n-rFIX1F4n9SeNy9rCLifG8P1KoXN9X-a9nNtl6jpeU63DJmVwOd0N7FIJ2oCFmxlvMHP0qCczINSyB21xigiolZAPd0rJEpz5b9cnRxnsg05rS5kyV4ApbKBkcnsNzolpbz86A5DnVw3MN7KGTKBmWHLLLMUP5uGI8_wZRksHiTsbwVYyD5z0DfE9zHIOheCKpIL_3xT-rGTq0oWUljjpfq-f1rQJsYwKIVEnXb0aUaDq9z5uRntiMpdlWfnDfleslSOEDp-XliLoLznHsNcE7H7iKP7v5M3EpeJ4Equ9UO8njDcGl1vKc7vYAptT-GbP2rT3AwV9rHhN67D4SWtdWT59c__bwufa5pVaMypLApvBlc6h1uAF2l1zX4wow55xy4-qXJ044w_j0Tvlly6TrjfWUbskXh66RDF2AwXSKL98QYH6SzDyjM0fKqPWzI9StVmugMCSbrGL1DD_cHZNSURcpqpkYZ-gt207XbU5-veHKgSaqGxVZKAuoLiuWGgrCYqlmRO6WXu2rdLj8_zB7fptLUNL_b7YPgKdhDR9_k_A.0qL7L3uo9K-Oopi-I2AsEA");
                        payloadData1.put("AccessToken", "eyJraWQiOiJETjE2MUtTSEQrQjdReDV6bGhkNlZ0Z1dhbkZidU1WOU91WTcxdWk3TmZZPSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiIyMTdiNjlmYi03ZmY1LTRiMWItOTlmNS0xMjI1YTYzY2YyMzMiLCJldmVudF9pZCI6ImM5MjhjNTk3LTFiZmItNDI1MS05MTcxLTdhMDZiOTA1NDUyZCIsInRva2VuX3VzZSI6ImFjY2VzcyIsInNjb3BlIjoiYXdzLmNvZ25pdG8uc2lnbmluLnVzZXIuYWRtaW4iLCJhdXRoX3RpbWUiOjE1ODE0MjgwNjgsImlzcyI6Imh0dHBzOlwvXC9jb2duaXRvLWlkcC5hcC1zb3V0aC0xLmFtYXpvbmF3cy5jb21cL2FwLXNvdXRoLTFfT2NWaVhWYVJiIiwiZXhwIjoxNTgxNDMxNjY4LCJpYXQiOjE1ODE0MjgwNjgsImp0aSI6IjY5YjI2NGM2LTdkYmYtNDAxZC1iZGMzLTljMDVlM2FjYTg1NCIsImNsaWVudF9pZCI6IjE1MWY1NXBuamJyNWU5b3Qzbjkzbmx0bms5IiwidXNlcm5hbWUiOiIyMTdiNjlmYi03ZmY1LTRiMWItOTlmNS0xMjI1YTYzY2YyMzMifQ.RGHfp4tV9YjYlM8TofwsCpdqFG1_HGBMraJyp9wClVxH4W3yusvtIax6ucSNGWuWnh2NOZxHEt11Z4iEfVqUK4FPA1PJH5df9-MJlgWIHf73CKJqH68YL-uU1ruAKL4u2sa314v8ih5VTgNVlzQEjYcaoXJ6OAZriZghoiSKGhgMnOs6KbaKm8ZdZWcnd0x3G24p8lXsuXvFkfJK6HL1OIitBRbrBgxeDy4idBJjFDJZ_l6aGpuq7NvCqm4GeITigWOXmOPDtI2izIjnpqsDBHuU9iGqJvNPh_lKf42cuTAJ0YDNSkni5VdaLqMB68urmCvsAibEkp2gDGSweIzWJg");
                        pluginYM.setPayload(MainActivity.this,payloadData1);
                        pluginYM.startChatBot();
                        break;
                    case "locate-stores":
                        Toast.makeText(getApplicationContext(), "Recieved Event: " + botEvent.getCode() + " : " + botEvent.getData(), Toast.LENGTH_SHORT).show();
                        break;
                    case "Message Received":
                        Toast.makeText(getApplicationContext(), "Recieved Event: " + botEvent.getCode() + " : " + botEvent.getData(), Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onFailure(String error) {
            }
        });

        payloadData.put("Platform", "Android-App");
        payloadData.put("JourneySlug", "issue-with-my-orders");

//        payloadData.put("MobileNumber", "8587040431");
//        payloadData.put("OrderId", "739238324");
//        payloadData.put("UserId", "221715679");
        payloadData.put("UserState", "Anonymous");


//        payloadData.put("UserState", "LoggedIn");
        payloadData.put("UserId", "143448352");
//        payloadData.put("RefreshToken", "eyJjdHkiOiJKV1QiLCJlbmMiOiJBMjU2R0NNIiwiYWxnIjoiUlNBLU9BRVAifQ.ibnFH8Hw_aWrVV6YKj2DilmFGuQnjw4QI-_EnRWXeL2v7thp4zAOdLB6AnAqXqHcLM5BVybi9hhKDhOdO9eij1WYLExSmMkieMXEoRvyN9j4bIRkiBfabdu2Rc1yeA6AEvpT4yQjoCjU_8y_gG6jlOhXC21CS6NIBvuzNnh04jnW6PyIsR4I1SfFrQmpzsbP7evS5STkh4oipgIoQEzNKEZgoiUOoLnU_0aUcdHthumMfL0Pa1HIaNJP3opvTtoKvJsDpOyElEx8mnW7aPiYLYMkIRQHckKF2NUWxbLhiugRTVp1BkwBEM4Rg1alv5TaUV_y92IZAvGyedIZButO-Q.dFL3L4nypMWBRmde.TNNMuCgLqcEJxrfX2PsGnZpc3ncdV5-a8wAMKrJKNuILiXD9o41n0LdD9ZE1RHBl4T4aoKInigpVbjur68yl_Wkl5Gk-VqMNghC1xm4rOgRLvk3zyiKqIP0hf2UApTrS2p5bvMhsA_10jYIzUEFCzC2WYnbwxrTzv5Rb4kHt3qNh3qZXzt_BYtrGCrmXt16cft1FcRENV58W60yI4E3bU0fA82W7u5HfUbxbnHSzxeeIQmPkA8nCEjQHdzydVpYTji_NTMaWr8VhaENCSqgdqTHjTVxDXGz-PlwYUVCexEJCSlDoTe7OLXTsw0IutmqbNkx-7l2gJC_z5KnLtY7XXPqp1XWbSQyGclV7_HLJX6_A0U3BKkKB9K1dxIgLPKLs5gnDBy6VuXVZz16MhOCuMbjMJ4qFb6t1aYbAfZ812JDOjONzMTK3-fgfVP1_e9MZZ20Mi3_uUNb3frzzeNEs9-MYZWtT7i5_35q2MzDThiOBDiDctI6bl7ATx-5dauwI4Vzj--zQSPb80nNXdHqiu_arlUxZWvetnVV1CCJWQ4zfISyVSXdo2pGJ-Rh8KIh5wyYnGmZ785twME43Sslu0bhob9IuDZdYqv2f8ywZZvYfDWgrNiWEsXMMGH8Ape0hXJ_67xyv9O9mZzTrtH5ay3rpNYGnPcjdb_8ijeN1gIaJL8jhIeVkza3Qza_b8feHK8LzufIvyjvipmujkln6cCsxVmSS8RYfspJYzn0vD4kCr8a8DvRGtsD5LJopOIiTOMxtKwxPKN-lKBo3n-rFIX1F4n9SeNy9rCLifG8P1KoXN9X-a9nNtl6jpeU63DJmVwOd0N7FIJ2oCFmxlvMHP0qCczINSyB21xigiolZAPd0rJEpz5b9cnRxnsg05rS5kyV4ApbKBkcnsNzolpbz86A5DnVw3MN7KGTKBmWHLLLMUP5uGI8_wZRksHiTsbwVYyD5z0DfE9zHIOheCKpIL_3xT-rGTq0oWUljjpfq-f1rQJsYwKIVEnXb0aUaDq9z5uRntiMpdlWfnDfleslSOEDp-XliLoLznHsNcE7H7iKP7v5M3EpeJ4Equ9UO8njDcGl1vKc7vYAptT-GbP2rT3AwV9rHhN67D4SWtdWT59c__bwufa5pVaMypLApvBlc6h1uAF2l1zX4wow55xy4-qXJ044w_j0Tvlly6TrjfWUbskXh66RDF2AwXSKL98QYH6SzDyjM0fKqPWzI9StVmugMCSbrGL1DD_cHZNSURcpqpkYZ-gt207XbU5-veHKgSaqGxVZKAuoLiuWGgrCYqlmRO6WXu2rdLj8_zB7fptLUNL_b7YPgKdhDR9_k_A.0qL7L3uo9K-Oopi-I2AsEA");
//        payloadData.put("AccessToken", "eyJraWQiOiJETjE2MUtTSEQrQjdReDV6bGhkNlZ0Z1dhbkZidU1WOU91WTcxdWk3TmZZPSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiIyMTdiNjlmYi03ZmY1LTRiMWItOTlmNS0xMjI1YTYzY2YyMzMiLCJldmVudF9pZCI6ImM5MjhjNTk3LTFiZmItNDI1MS05MTcxLTdhMDZiOTA1NDUyZCIsInRva2VuX3VzZSI6ImFjY2VzcyIsInNjb3BlIjoiYXdzLmNvZ25pdG8uc2lnbmluLnVzZXIuYWRtaW4iLCJhdXRoX3RpbWUiOjE1ODE0MjgwNjgsImlzcyI6Imh0dHBzOlwvXC9jb2duaXRvLWlkcC5hcC1zb3V0aC0xLmFtYXpvbmF3cy5jb21cL2FwLXNvdXRoLTFfT2NWaVhWYVJiIiwiZXhwIjoxNTgxNDMxNjY4LCJpYXQiOjE1ODE0MjgwNjgsImp0aSI6IjY5YjI2NGM2LTdkYmYtNDAxZC1iZGMzLTljMDVlM2FjYTg1NCIsImNsaWVudF9pZCI6IjE1MWY1NXBuamJyNWU5b3Qzbjkzbmx0bms5IiwidXNlcm5hbWUiOiIyMTdiNjlmYi03ZmY1LTRiMWItOTlmNS0xMjI1YTYzY2YyMzMifQ.RGHfp4tV9YjYlM8TofwsCpdqFG1_HGBMraJyp9wClVxH4W3yusvtIax6ucSNGWuWnh2NOZxHEt11Z4iEfVqUK4FPA1PJH5df9-MJlgWIHf73CKJqH68YL-uU1ruAKL4u2sa314v8ih5VTgNVlzQEjYcaoXJ6OAZriZghoiSKGhgMnOs6KbaKm8ZdZWcnd0x3G24p8lXsuXvFkfJK6HL1OIitBRbrBgxeDy4idBJjFDJZ_l6aGpuq7NvCqm4GeITigWOXmOPDtI2izIjnpqsDBHuU9iGqJvNPh_lKf42cuTAJ0YDNSkni5VdaLqMB68urmCvsAibEkp2gDGSweIzWJg");

        pluginYM.setPayload( this ,payloadData);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
           pluginYM.startChatBot();
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
