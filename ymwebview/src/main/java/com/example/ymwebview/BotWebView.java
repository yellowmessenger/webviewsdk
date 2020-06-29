package com.example.ymwebview;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentManager;

import com.example.ymwebview.models.BotEventsModel;
import com.example.ymwebview.models.ConfigDataModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;


public class BotWebView extends AppCompatActivity {
    private final String TAG = "YM WebView Plugin";
    WebviewOverlay fh;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(android.R.id.content), (v, insets) -> {
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
                    params.bottomMargin = insets.getSystemWindowInsetBottom();
                    return insets.consumeSystemWindowInsets();
                });
        setContentView(R.layout.activity_bot_web_view);
        fh=new WebviewOverlay();
        FragmentManager fragManager=getSupportFragmentManager();
        fragManager.beginTransaction()
                .add(R.id.container,fh)
                .commit();
        String enableSpeech = ConfigDataModel.getInstance().getConfig("enableSpeech");
        Log.d(TAG, "enableSpeech : "+ enableSpeech);

        if(Boolean.parseBoolean(enableSpeech)){
            FloatingActionButton micButton = findViewById(R.id.floatingActionButton);
            micButton.setVisibility(View.VISIBLE);
            micButton.setOnClickListener(view -> {
                toggleBottomSheet();
            });
        }

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(view->{
            YMBotPlugin.getInstance().emitEvent(new BotEventsModel("bot-closed",""));
            this.finish();
        });
    }


    private void speechRecognition() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
        startActivityForResult(intent, 100);

    }

    SpeechRecognizer sr;

    public void startListeningWithoutDialog() {
        // Intent to listen to user vocal input and return the result to the same activity.
        Context appContext = getApplicationContext();
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        // Use a language model based on free-form speech recognition.
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                appContext.getPackageName());

        // Add custom listeners.

        sr = SpeechRecognizer.createSpeechRecognizer(appContext);
        CustomRecognitionListener listener = new CustomRecognitionListener();
        sr.setRecognitionListener(listener);
        sr.startListening(intent);
    }

    public void toggleBottomSheet() {
//        Toast.makeText(this, "opening Mic", Toast.LENGTH_SHORT).show();
//        fh.sendEvent("Bleh Bleh");
        RelativeLayout voiceArea = findViewById(R.id.voiceArea);
        FloatingActionButton micButton = findViewById(R.id.floatingActionButton);

        if(voiceArea.getVisibility() == View.INVISIBLE){
            voiceArea.setVisibility(View.VISIBLE);
//            speechRecognition();
            startListeningWithoutDialog();
        }else {
            voiceArea.setVisibility(View.INVISIBLE);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            super.onActivityResult(requestCode, resultCode, data);
            if (fh != null) {
                fh.onActivityResult(requestCode, resultCode, data);
            }


        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case 100:
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    fh.sendEvent(result.get(0));
//                    toggleBottomSheet();
                    break;
            }
        } else {
            Toast.makeText(getApplicationContext(), "Failed to recognize speech!", Toast.LENGTH_LONG).show();
        }
    }
    private String speech_result = "";
    class CustomRecognitionListener implements RecognitionListener {
        boolean singleResult = true;

        private static final String TAG = "RecognitionListener";


        public void onReadyForSpeech(Bundle params) {
            Log.d(TAG, "onReadyForSpeech");
        }

        public void onBeginningOfSpeech() {
            Log.d(TAG, "onBeginningOfSpeech");
        }

        public void onRmsChanged(float rmsdB) {
            Log.d(TAG, "onRmsChanged");

        }

        public void onBufferReceived(byte[] buffer) {
            Log.d(TAG, "onBufferReceived");
        }

        public void onEndOfSpeech() {
            Log.d(TAG, "onEndofSpeech");
            if(!speech_result.equals("")){

            }

        }

        public void onError(int error) {
            Log.e(TAG, "error " + error);
        }

        public void onResults(Bundle results) {

            if (singleResult) {
                ArrayList<String> result = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                if (result != null && result.size() > 0) {
                    TextView textView = findViewById(R.id.speechTranscription);
                    textView.setText(result.get(0));
                    speech_result = result.get(0);
                    sr.cancel();
                    fh.sendEvent(result.get(0));
                    toggleBottomSheet();

                }
                singleResult=false;
            }


        }

        public void onPartialResults(Bundle partialResults) {
            Log.d(TAG, "onPartialResults"+partialResults.toString());

        }

        public void onEvent(int eventType, Bundle params) {
            Log.d(TAG, "onEvent " + eventType);
        }
    }


}

