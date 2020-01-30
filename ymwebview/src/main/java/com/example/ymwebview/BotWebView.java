package com.example.ymwebview;

import android.Manifest;
import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;

import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.ymwebview.models.ConfigDataModel;
import com.google.gson.Gson;

import java.util.Map;

import im.delight.android.webview.AdvancedWebView;


public class BotWebView extends Activity implements  AdvancedWebView.Listener{
    private final String TAG = "YM WebView Plugin";
    private AdvancedWebView myWebView;
    private static final int REQUEST_FINE_LOCATION = 1;
    private String geolocationOrigin;
    private GeolocationPermissions.Callback geolocationCallback;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       if(myWebView != null) setContentView(myWebView);
       else{
           preLoadWebView();
           setContentView(myWebView);
       }

    }

    public boolean preLoadWebView(){
        final Context context = this.getApplicationContext();
        String botId = ConfigDataModel.getInstance().getConfig("botID");
        Map payload = ConfigDataModel.getInstance().getPayload();
        String payloadJSON = new Gson().toJson(payload).replace("\"", "");;
        Log.d(TAG, "onCreate: "+payloadJSON);

        myWebView = new AdvancedWebView(context);
        myWebView.setListener(this, this);

        String botUrl = "https://app.yellowmessenger.com/pwa/mobile/"+botId+"?ym.payload="+payloadJSON;
        Log.d(TAG, "onCreate: "+botUrl);

        myWebView.loadUrl(botUrl);

        myWebView.getSettings().setSupportMultipleWindows(true);
        myWebView.getSettings().setGeolocationDatabasePath( context.getFilesDir().getPath() );
        myWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
                AdvancedWebView newWebView = new AdvancedWebView(context);
                setContentView(newWebView);
                WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
                transport.setWebView(newWebView);
                resultMsg.sendToTarget();

                return true;
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if(title == "Domino\'s"){
                    Toast.makeText(BotWebView.this, "This is the first page.", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(BotWebView.this, title, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onGeolocationPermissionsShowPrompt(String origin,
                                                           GeolocationPermissions.Callback callback) {
                // Geolocation permissions coming from this app's Manifest will only be valid for devices with
                // API_VERSION < 23. On API 23 and above, we must check for permissions, and possibly
                // ask for them.
                String perm = Manifest.permission.ACCESS_FINE_LOCATION;
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                        ContextCompat.checkSelfPermission(BotWebView.this, perm) == PackageManager.PERMISSION_GRANTED) {
                    // we're on SDK < 23 OR user has already granted permission
                    callback.invoke(origin, true, false);
                } else {
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(BotWebView.this, perm)) {
                        // ask the user for permission
                        ActivityCompat.requestPermissions(BotWebView.this, new String[] {perm}, REQUEST_FINE_LOCATION);

                        // we will use these when user responds
                        geolocationOrigin = origin;
                        geolocationCallback = callback;
                    }
                }
            }


        });

        return true;
    }

    @Override
    public void onBackPressed() {
        if (myWebView.canGoBack()) {
            myWebView.goBack();
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {
//        myWebView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onPageFinished(String url) {
        myWebView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {
        Toast.makeText(BotWebView.this, "onPageError(errorCode = "+errorCode+",  description = "+description+",  failingUrl = "+failingUrl+")", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {

    }

    @Override
    public void onExternalPageRequest(String url) {
        Toast.makeText(BotWebView.this, "onExternalPageRequest(url = "+url+")", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        myWebView.onActivityResult(requestCode, resultCode, intent);
    }
}
