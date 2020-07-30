package com.example.ymwebview;



import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;

import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentManager;

import com.example.ymwebview.models.BotEventsModel;
import com.example.ymwebview.models.ConfigDataModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.skyfishjy.library.RippleBackground;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;


public class BotWebView extends AppCompatActivity {
    private final String TAG = "YM WebView Plugin";
    WebviewOverlay fh;
    private boolean willStartMic = false;

    public void startMic(long countdown_time){
        RelativeLayout voiceArea = findViewById(R.id.voiceArea);
        if(!willStartMic){
        willStartMic = true;
            new CountDownTimer(countdown_time, 1000) {
                public void onTick(long millisUntilFinished) {}
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                public void onFinish() {
                    if(voiceArea.getVisibility() == View.INVISIBLE && willStartMic){
                        toggleBottomSheet();
                    }
                }
            }.start();
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
            fh.closeBot();
            this.finish();
        });




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        fh.closeBot();
        this.finish();
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
        Map payload = ConfigDataModel.getInstance().getPayload();
        String defaultLanguage = (String) payload.get("defaultLanguage");
        if(defaultLanguage == null){
            defaultLanguage = "en";
        }
        Log.d(TAG, "startListeningWithoutDialog: " + defaultLanguage);
        String languagePref = defaultLanguage;
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, languagePref);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, languagePref);
        intent.putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE, languagePref);



        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS,true);
//        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                appContext.getPackageName());

        // Add custom listeners.

        sr = SpeechRecognizer.createSpeechRecognizer(appContext);
        CustomRecognitionListener listener = new CustomRecognitionListener();
        sr.setRecognitionListener(listener);
        sr.startListening(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public  void toggleBottomSheet() {

        final RippleBackground rippleBackground=(RippleBackground)findViewById(R.id.animated_btn);
        RelativeLayout voiceArea = findViewById(R.id.voiceArea);
        FloatingActionButton micButton = findViewById(R.id.floatingActionButton);
        TextView textView = findViewById(R.id.speechTranscription);

        if(voiceArea.getVisibility() == View.INVISIBLE){
            textView.setText("I'm listening...");
            willStartMic = false;
            voiceArea.setVisibility(View.VISIBLE);
            rippleBackground.startRippleAnimation();
            startListeningWithoutDialog();

            micButton.setImageDrawable(getDrawable(R.drawable.ic_back_button));
        }else {
            voiceArea.setVisibility(View.INVISIBLE);
            rippleBackground.stopRippleAnimation();
            micButton.setImageDrawable(getDrawable(R.drawable.ic_mic_button));
            sr.stopListening();
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void closeVoiceArea(){
        final RippleBackground rippleBackground=(RippleBackground)findViewById(R.id.animated_btn);
        RelativeLayout voiceArea = findViewById(R.id.voiceArea);
        FloatingActionButton micButton = findViewById(R.id.floatingActionButton);
        TextView textView = findViewById(R.id.speechTranscription);

            voiceArea.setVisibility(View.INVISIBLE);
            rippleBackground.stopRippleAnimation();
            micButton.setImageDrawable(getDrawable(R.drawable.ic_mic_button));
            sr.stopListening();
            sr.destroy();
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

//        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public void onBeginningOfSpeech() {

        }

        public void onRmsChanged(float rmsdB) {
            Log.d(TAG, "onRmsChanged");

        }

        public void onBufferReceived(byte[] buffer) {
            Log.d(TAG, "onBufferReceived");
        }

        public void onEndOfSpeech() {
            Log.d(TAG, "onEndofSpeech");
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public void onError(int error) {
            closeVoiceArea();
            View parentLayout = findViewById(android.R.id.content);
            Snackbar snackbar = Snackbar
                    .make(parentLayout, "We've encountered an error. Please press Mic to continue with voice input.", Snackbar.LENGTH_LONG);
            snackbar.show() ;

        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public void onResults(Bundle results) {
            ArrayList<String> result = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            TextView textView = findViewById(R.id.speechTranscription);
            textView.setText(result.get(0));

            if (singleResult) {
                if (result != null && result.size() > 0) {
                    speech_result = result.get(0);
                    sr.cancel();
                    fh.sendEvent(result.get(0));
                }
                closeVoiceArea();
                singleResult=false;
            }


        }

        public void onPartialResults(Bundle partialResults) {
            Log.d(TAG, "onPartialResults "+partialResults.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION).get(0));
            TextView textView = findViewById(R.id.speechTranscription);
            textView.setText(partialResults.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION).get(0));
        }

        public void onEvent(int eventType, Bundle params) {
            Log.d(TAG, "onEvent " + eventType);
        }
    }


}

