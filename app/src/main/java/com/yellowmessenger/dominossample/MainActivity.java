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
            "\"enableSpeech\": \"true\"," +
            "\"enableHistory\": \"true\"" +
            "}";
    String configDataTest = "{" +
            "\"botName\": \"Foodie\"," +
            "\"botID\": \"x1577702191385\"," +
            "\"enableSpeech\": \"true\"" +

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
                            Log.d("Priyank", "onSuccess: " + botEvent.getData());
                            HashMap<String, Object> payloadData1 = new HashMap<>();
                            payloadData1.put("Platform", "Android-App");
                            payloadData1.put("UserId", "143448352");
                            payloadData1.put("MobileNumber", "9742909625");
                            payloadData1.put("JourneySlug", botEvent.getData());
                            payloadData1.put("UserState", "LoggedIn");
                            payloadData1.put("RefreshToken", "eyJjdHkiOiJKV1QiLCJlbmMiOiJBMjU2R0NNIiwiYWxnIjoiUlNBLU9BRVAifQ.ibnFH8Hw_aWrVV6YKj2DilmFGuQnjw4QI-_EnRWXeL2v7thp4zAOdLB6AnAqXqHcLM5BVybi9hhKDhOdO9eij1WYLExSmMkieMXEoRvyN9j4bIRkiBfabdu2Rc1yeA6AEvpT4yQjoCjU_8y_gG6jlOhXC21CS6NIBvuzNnh04jnW6PyIsR4I1SfFrQmpzsbP7evS5STkh4oipgIoQEzNKEZgoiUOoLnU_0aUcdHthumMfL0Pa1HIaNJP3opvTtoKvJsDpOyElEx8mnW7aPiYLYMkIRQHckKF2NUWxbLhiugRTVp1BkwBEM4Rg1alv5TaUV_y92IZAvGyedIZButO-Q.dFL3L4nypMWBRmde.TNNMuCgLqcEJxrfX2PsGnZpc3ncdV5-a8wAMKrJKNuILiXD9o41n0LdD9ZE1RHBl4T4aoKInigpVbjur68yl_Wkl5Gk-VqMNghC1xm4rOgRLvk3zyiKqIP0hf2UApTrS2p5bvMhsA_10jYIzUEFCzC2WYnbwxrTzv5Rb4kHt3qNh3qZXzt_BYtrGCrmXt16cft1FcRENV58W60yI4E3bU0fA82W7u5HfUbxbnHSzxeeIQmPkA8nCEjQHdzydVpYTji_NTMaWr8VhaENCSqgdqTHjTVxDXGz-PlwYUVCexEJCSlDoTe7OLXTsw0IutmqbNkx-7l2gJC_z5KnLtY7XXPqp1XWbSQyGclV7_HLJX6_A0U3BKkKB9K1dxIgLPKLs5gnDBy6VuXVZz16MhOCuMbjMJ4qFb6t1aYbAfZ812JDOjONzMTK3-fgfVP1_e9MZZ20Mi3_uUNb3frzzeNEs9-MYZWtT7i5_35q2MzDThiOBDiDctI6bl7ATx-5dauwI4Vzj--zQSPb80nNXdHqiu_arlUxZWvetnVV1CCJWQ4zfISyVSXdo2pGJ-Rh8KIh5wyYnGmZ785twME43Sslu0bhob9IuDZdYqv2f8ywZZvYfDWgrNiWEsXMMGH8Ape0hXJ_67xyv9O9mZzTrtH5ay3rpNYGnPcjdb_8ijeN1gIaJL8jhIeVkza3Qza_b8feHK8LzufIvyjvipmujkln6cCsxVmSS8RYfspJYzn0vD4kCr8a8DvRGtsD5LJopOIiTOMxtKwxPKN-lKBo3n-rFIX1F4n9SeNy9rCLifG8P1KoXN9X-a9nNtl6jpeU63DJmVwOd0N7FIJ2oCFmxlvMHP0qCczINSyB21xigiolZAPd0rJEpz5b9cnRxnsg05rS5kyV4ApbKBkcnsNzolpbz86A5DnVw3MN7KGTKBmWHLLLMUP5uGI8_wZRksHiTsbwVYyD5z0DfE9zHIOheCKpIL_3xT-rGTq0oWUljjpfq-f1rQJsYwKIVEnXb0aUaDq9z5uRntiMpdlWfnDfleslSOEDp-XliLoLznHsNcE7H7iKP7v5M3EpeJ4Equ9UO8njDcGl1vKc7vYAptT-GbP2rT3AwV9rHhN67D4SWtdWT59c__bwufa5pVaMypLApvBlc6h1uAF2l1zX4wow55xy4-qXJ044w_j0Tvlly6TrjfWUbskXh66RDF2AwXSKL98QYH6SzDyjM0fKqPWzI9StVmugMCSbrGL1DD_cHZNSURcpqpkYZ-gt207XbU5-veHKgSaqGxVZKAuoLiuWGgrCYqlmRO6WXu2rdLj8_zB7fptLUNL_b7YPgKdhDR9_k_A.0qL7L3uo9K-Oopi-I2AsEA");
                            payloadData1.put("AccessToken", "eyJraWQiOiJETjE2MUtTSEQrQjdReDV6bGhkNlZ0Z1dhbkZidU1WOU91WTcxdWk3TmZZPSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiIyMTdiNjlmYi03ZmY1LTRiMWItOTlmNS0xMjI1YTYzY2YyMzMiLCJldmVudF9pZCI6ImM5MjhjNTk3LTFiZmItNDI1MS05MTcxLTdhMDZiOTA1NDUyZCIsInRva2VuX3VzZSI6ImFjY2VzcyIsInNjb3BlIjoiYXdzLmNvZ25pdG8uc2lnbmluLnVzZXIuYWRtaW4iLCJhdXRoX3RpbWUiOjE1ODE0MjgwNjgsImlzcyI6Imh0dHBzOlwvXC9jb2duaXRvLWlkcC5hcC1zb3V0aC0xLmFtYXpvbmF3cy5jb21cL2FwLXNvdXRoLTFfT2NWaVhWYVJiIiwiZXhwIjoxNTgxNDMxNjY4LCJpYXQiOjE1ODE0MjgwNjgsImp0aSI6IjY5YjI2NGM2LTdkYmYtNDAxZC1iZGMzLTljMDVlM2FjYTg1NCIsImNsaWVudF9pZCI6IjE1MWY1NXBuamJyNWU5b3Qzbjkzbmx0bms5IiwidXNlcm5hbWUiOiIyMTdiNjlmYi03ZmY1LTRiMWItOTlmNS0xMjI1YTYzY2YyMzMifQ.RGHfp4tV9YjYlM8TofwsCpdqFG1_HGBMraJyp9wClVxH4W3yusvtIax6ucSNGWuWnh2NOZxHEt11Z4iEfVqUK4FPA1PJH5df9-MJlgWIHf73CKJqH68YL-uU1ruAKL4u2sa314v8ih5VTgNVlzQEjYcaoXJ6OAZriZghoiSKGhgMnOs6KbaKm8ZdZWcnd0x3G24p8lXsuXvFkfJK6HL1OIitBRbrBgxeDy4idBJjFDJZ_l6aGpuq7NvCqm4GeITigWOXmOPDtI2izIjnpqsDBHuU9iGqJvNPh_lKf42cuTAJ0YDNSkni5VdaLqMB68urmCvsAibEkp2gDGSweIzWJg");
                            pluginYM.setPayload(MainActivity.this, payloadData1);
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
        }
        catch (Exception e){
            Log.e("Some error", e.getLocalizedMessage());
        }

        payloadData.put("Platform", "Android-App");
//        payloadData.put("UserName", "");
//        payloadData.put("UserId", "143448352");
//        payloadData.put("AccessToken", "eyJraWQiOiJETjE2MUtTSEQrQjdReDV6bGhkNlZ0Z1dhbkZidU1WOU91WTcxdWk3TmZZPSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiIyMTdiNjlmYi03ZmY1LTRiMWItOTlmNS0xMjI1YTYzY2YyMzMiLCJldmVudF9pZCI6IjY5MTQwNDgwLThhOGEtNDVjZi05OGQ3LTdiNDIzYjA3Nzc3NCIsInRva2VuX3VzZSI6ImFjY2VzcyIsInNjb3BlIjoiYXdzLmNvZ25pdG8uc2lnbmluLnVzZXIuYWRtaW4iLCJhdXRoX3RpbWUiOjE1ODI2NTQxNTcsImlzcyI6Imh0dHBzOlwvXC9jb2duaXRvLWlkcC5hcC1zb3V0aC0xLmFtYXpvbmF3cy5jb21cL2FwLXNvdXRoLTFfT2NWaVhWYVJiIiwiZXhwIjoxNTgyODA1MTgzLCJpYXQiOjE1ODI4MDE1ODMsImp0aSI6IjNjYWI4NGVhLTYwZTYtNGNkNy05ZjlmLTNjMTJkMGJkMTUzMSIsImNsaWVudF9pZCI6IjE1MWY1NXBuamJyNWU5b3Qzbjkzbmx0bms5IiwidXNlcm5hbWUiOiIyMTdiNjlmYi03ZmY1LTRiMWItOTlmNS0xMjI1YTYzY2YyMzMifQ.hfrF2SBQVIUxqQ2meKU-nHZR3WiOmS_ndM9rItB3-F9XpI91rXpwiMIEMLky8nfoLJDwNk03AR-nnPX7h41sKun9SB-B_dMe7nMuW2886kw2w6RFRR2-_f46mWzT_87EW0cQocQOoQZ_pyxagomqNmeUjvFC29mWCWOTLZZCvU6TB1FUMdjXqvsR3POlfuvmOXIr-hEzuDRGPoSyhePgiO07Tm3MXiO4CGhL1-XG5r6DGeCSGu1hYJEMM9rmYqecQrLHrkbrgC2nxGSwibhOAcExj8JEcsUCSNrBHpSrxEA68HBzbB3DInfXUONUZpbHcBFbjdVvOtg8PJiIfJDa3A");
//        payloadData.put("JourneySlug", "issue-with-my-orders");
//        payloadData.put("RefreshToken", "eyJjdHkiOiJKV1QiLCJlbmMiOiJBMjU2R0NNIiwiYWxnIjoiUlNBLU9BRVAifQ.VCNWLa4IvKhd2q7qm0bCxTxAEL1dYGTP6-20dHrhttNKpD-qkgrcejGToxOZjMjUN8Zi-tNB1F8Ov0f4sYWY5VtCNK3O5rvH95E8Ma6kKdCF0tDaGB-UYvGIwKUjzQ2Pxe3qmqMYHdhrqBQqsFI3p30LrOd7RKAHEPUSywOeLigwFLsx-BiJn8L36h70cR2dMw4bKAiqXASNCZSnHLHVL9CsVAQ1sob8o48EZQuSz_loSKBF9hD1RHyzL55_YbAd1yWm74Dn-67jkgzbq6tug01wbCrEDgvHPuMzzDiMozERGp7b_W7DUYHmdn1l1cG6M60FmIbCOgRKeSqJFs8o7w.uEU16QUXq0G__Zcw.3Oz3i0E1TPSvSkYFeEf48r6pdtNKz8kWnh_5Fsv6sYYE_Tv3qBe9VseewxJ9Ynh_n07zvDejHeeg6mlQGHNSwVe3WvfUyVAEwbuHVQ3XnnoflVCfHXnp-J11No7PFCjSKBOn92nwrhLOMF36ubL0zBGBF4_ZG0GTkL3QSlqPcSfwHEYCCvynF26c2ryOMnZDh6jVVH3n52HLTsCQxiNJ6mnrK23eCugzMcBpbUuWZYpicu9KM4msp-Ee4WzIoEDdjw4yWfnx9L3NLiroKPeOk-dcc2YN-LqB3rlc3IySuZIg_PH98X5ls7U2c9rB0Ugort42LAajMQxQhOyR4rng1YvzsmdAJLv9IDKhascV8wyYcUJjhotnMDttwgqQNELUyU9lVVHcfywxJJHFXCJY50qa3ZdiJjczfwwuThwXzztZTxn2bmbF97tJOo_cq1aPMkTY6FOuSUtnajg-0OIPyklEpQFO-lmrPqB5GA_g-AxGb-F8lx-LrTunSkt0oawPd1G5X1K8XPP4HQl0jmVT2rlVzGQMSLi1iRfzITq11YoeOWOPpcQhBhdbMfYOMhDHwfReuYqDtoF9BV94PLbly678t7UI_5WShbAhn6gg75ekdBCNhzL5RBh-A35XaJTr22HLPsshFPKhd-oSZpsIG5MCeSxUswWgYWRIVmwnvl6GnAuiP0aib_MO2k9NXV_rOpFxE2ZHP1BVyCd48roe7jOU6NPW1_1VVGZfS3zRh-cXE934mG_gtBBl9n0zlNwZUQoAJgNHF-lMaXFk3reVoh46B7ibpztRRkpk3y6g2X1LJNuu44tl4Pp9-WaRCpXPyOfIJFd3fuPRX6ONpNPyGv2WrLbg8Bk8FveFUa3TsBCQYeXSx8qB3Mxe_cImjP33JnTMy9BSB9syU450Uhq55Z1CaFitjPzZrlpPU1kqYypSiAYTwoWnS-9IcyJyTPZ6r1EUhVyRj2QlXQB2n4NeztzTmQme-XjpX7SZCYlVGb8OJY5bCLVbyQvKr69Quj6PKxP-IaVsZBLEjYMKUOlminL_sRBrPo-vn3pbCewQfYJpKTklEKYJu_B4Vkl_NvznPmbFCqyibNAGPkbXunjOiaKNZDlX3Xt8pJ9EAnCeuR6GAJx-r4Svq5iBtifj7F3LZilq24uxHdQNhVMhfVFsRf0m5Ol2MLY7YadaMlzrf9LZe5bRef6U1k5h7B5i-kGu2BbKaYOCxw6hdP0yZgIKfzqXbH9mGzG-3J0r9v-GtXz0es2hCNJBpHaROdCikWJnq9shF8E1DbBIwNgKqqPTCQQ0bJES_0PSkU1bhzJPYDcfNuIcLxlbXpb-Gl67FA.zhJtQHQDkOLUaopnTVOrag");
        payloadData.put("JourneySlug", "track-my-order");

//        payloadData.put("MobileNumber", "6301898157");
//        payloadData.put("OrderId", "23");
//        payloadData.put("UserId", "221715679");
        payloadData.put("UserState", "Anonymous");
//        payloadData.put("UserState", "LoggedIn");
//        payloadData.put("UserFirstName", "RavinutalaMaheshKumar");
//        payloadData.put("UserLastName", "RavinutalaMaheshKumar");
//        payloadData.put("UserEmail", "gtasansandreas.iv@gmail.com");
//        payloadData.put("MobileNumber", "9742909625");



//        payloadData.put("UserState", "LoggedIn");
//        payloadData.put("UserId", "143448352");
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
