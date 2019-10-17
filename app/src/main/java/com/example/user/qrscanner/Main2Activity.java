package com.example.user.qrscanner;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Locale;

public class Main2Activity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    TextView txt;
    String str;
    public TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txt=findViewById(R.id.txt2_id);
        tts = new TextToSpeech(Main2Activity.this,  Main2Activity.this);
        SpeakOut();
        Intent i=getIntent();
        str=i.getStringExtra("rst");

        txt.setText(str);


    }


    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported.");
            } else {
                txt.setEnabled(true);
                SpeakOut();
            }
        } else {
            Log.e("TTS", "Initialization Failed..!");
        }
    }//end of onInit

    private void SpeakOut() {
        String text = txt.getText().toString();
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}
