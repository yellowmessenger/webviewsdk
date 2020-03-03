package com.example.ymwebview;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.ymwebview.models.ConfigDataModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Locale;


public class BotWebView extends AppCompatActivity {
    private final String TAG = "YM WebView Plugin";
    WebviewOverlay fh;
    AVLoadingIndicatorView avi;
    RelativeLayout voiceArea;
    TextView speechTranscription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot_web_view);
        fh=new WebviewOverlay(getApplicationContext());
        FragmentManager fragManager=getSupportFragmentManager();
        fragManager.beginTransaction()
                .add(R.id.container,fh)
                .commit();
        String enableSpeech = ConfigDataModel.getInstance().getConfig("enableSpeech");
        Log.d(TAG, "enableSpeech : "+ enableSpeech);

        if(Boolean.parseBoolean(enableSpeech)){
            FloatingActionButton micButton = findViewById(R.id.floatingActionButton);
            voiceArea = findViewById(R.id.voiceArea);
            speechTranscription = findViewById(R.id.speechTranscription);
             avi = findViewById(R.id.speechAnimation);
            micButton.setVisibility(View.VISIBLE);
            micButton.setOnClickListener(view -> {
                toggleBottomSheet();
            });
        }
    }

    private void speechRecognition() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
        startActivityForResult(intent, 10);

    }

    public void startListeningWithoutDialog() {
        // Intent to listen to user vocal input and return the result to the same activity.
        Context appContext = getApplicationContext();
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        // Use a language model based on free-form speech recognition.
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);
        intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);

        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                appContext.getPackageName());

        // Add custom listeners.
        CustomRecognitionListener listener = new CustomRecognitionListener();
        SpeechRecognizer sr = SpeechRecognizer.createSpeechRecognizer(appContext);
        sr.setRecognitionListener(listener);
        sr.startListening(intent);
    }

    public void toggleBottomSheet() {
        if(voiceArea.getVisibility() == View.INVISIBLE){
            voiceArea.setVisibility(View.VISIBLE);
//            speechRecognition();
            startListeningWithoutDialog();
        }else voiceArea.setVisibility(View.INVISIBLE);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case 10:
//                    TextView textView = findViewById(R.id.speechTranscription);

//                    textView.setText(data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0).toString());

                    break;
            }
        } else {
            Toast.makeText(getApplicationContext(), "Failed to recognize speech!", Toast.LENGTH_LONG).show();
        }
    }
    class CustomRecognitionListener implements RecognitionListener {
        private static final String TAG = "RecognitionListener";

        public void onReadyForSpeech(Bundle params) {
            Log.d(TAG, "onReadyForSpeech");
        }

        public void onBeginningOfSpeech() {
            Log.d(TAG, "onBeginningOfSpeech");
            startAnim();
        }

        public void onRmsChanged(float rmsdB) {
            Log.d(TAG, "onRmsChanged");

        }

        public void onBufferReceived(byte[] buffer) {
            Log.d(TAG, "onBufferReceived");
        }

        public void onEndOfSpeech() {
            Log.d(TAG, "onEndofSpeech");
            stopAnim();
        }

        public void onError(int error) {
            Log.e(TAG, "error " + error);

//            conversionCallaback.onErrorOccured(TranslatorUtil.getErrorText(error));
        }

        public void onResults(Bundle results) {
            ArrayList<String> result = results
                    .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

            speechTranscription.setText(result.get(0));
            fh.sendEvent(result.get(0));
            toggleBottomSheet();
            speechTranscription.setText("");

        }

        public void onPartialResults(Bundle partialResults) {
            Log.d(TAG, "onPartialResults"+ partialResults.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION).get(0));
            speechTranscription.setText(partialResults.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION).get(0));

        }

        public void onEvent(int eventType, Bundle params) {
            Log.d(TAG, "onEvent " + eventType);
        }
    }

    void startAnim(){
        avi.show();
        // or avi.smoothToShow();
    }

    void stopAnim(){
        avi.hide();
        // or avi.smoothToHide();
    }



}

