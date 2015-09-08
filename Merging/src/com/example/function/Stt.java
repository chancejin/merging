//package com.example.function;
//
//import java.util.ArrayList;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.speech.RecognitionListener;
//import android.speech.RecognizerIntent;
//import android.speech.SpeechRecognizer;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.EditText;
//
//public class Stt {
//
//	SpeechRecognizer mRecognizer;
//    Intent i;
//    private Button btn;
//    private EditText et;
//    
//    public Stt(Activity activity){
//    	
//    	//btn.setOnClickListener();
//    	
//    	mRecognizer = SpeechRecognizer.createSpeechRecognizer(activity);
//        mRecognizer.setRecognitionListener(listener);
//
//        i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//        i.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, activity.getPackageName());
//        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"ko-KR");
//    }
//    
//    private RecognitionListener listener = new RecognitionListener() {
//        @Override
//        public void onReadyForSpeech(Bundle params) {
//
//        }
//
//        @Override
//        public void onBeginningOfSpeech() {
//
//        }
//
//        @Override
//        public void onRmsChanged(float rmsdB) {
//
//        }
//
//        @Override
//        public void onBufferReceived(byte[] buffer) {
//
//        }
//
//        @Override
//        public void onEndOfSpeech() {
//
//        }
//
//        @Override
//        public void onError(int error) {
//
//        }
//
//        @Override
//        public void onResults(Bundle results) {
//            String key = "";
//            key = SpeechRecognizer.RESULTS_RECOGNITION;
//            ArrayList<String> mResult = results.getStringArrayList(key);
//            String[] rs = new String[mResult.size()];
//            mResult.toArray(rs);
//            //tv1.setText(""+rs[0]);
//        }
//
//        @Override
//        public void onPartialResults(Bundle partialResults) {
//
//        }
//
//        @Override
//        public void onEvent(int eventType, Bundle params) {
//
//        }
//    };
//   
//}
