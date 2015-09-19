package com.example.merging;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SttTextCommunication extends Activity implements OnClickListener{
	Button btn_connect;
	Button btn_send;
	TextView textView;
	EditText editText;
	
	Socket client;
	String ip = "192.168.10.104";
	int port = 50000;
	
	Thread thread;
	
	ClientThread clientThread;
	
	Handler handler;
	
	//STT
	private Button btn_stt;
    SpeechRecognizer mRecognizer;
    Intent i;
    
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stttextcommunication);
		
		btn_connect = (Button)findViewById(R.id.btn_connect);
		btn_send = (Button)findViewById(R.id.btn_send);
		textView = (TextView)findViewById(R.id.textView);
		editText = (EditText)findViewById(R.id.editText);
		handler = new Handler(){
			public void handleMessage(Message msg){
				super.handleMessage(msg);
				Bundle bundle = msg.getData();
				textView.append(bundle.getString("msg")+"\n");
			}
		};
		
		//STT
		btn_stt = (Button)findViewById(R.id.btn_stt);

        mRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        mRecognizer.setRecognitionListener(listener);

        i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"ko-KR");
		
		btn_connect.setOnClickListener(this);
		btn_send.setOnClickListener(this);
		
		//STT
        btn_stt.setOnClickListener(this);
	}
	
	private RecognitionListener listener = new RecognitionListener() {
        @Override
        public void onReadyForSpeech(Bundle params) {

        }

        @Override
        public void onBeginningOfSpeech() {

        }

        @Override
        public void onRmsChanged(float rmsdB) {

        }

        @Override
        public void onBufferReceived(byte[] buffer) {

        }

        @Override
        public void onEndOfSpeech() {

        }

        @Override
        public void onError(int error) {

        }

        @Override
        public void onResults(Bundle results) {
            String key = "";
            key = SpeechRecognizer.RESULTS_RECOGNITION;
            ArrayList<String> mResult = results.getStringArrayList(key);
            String[] rs = new String[mResult.size()];
            mResult.toArray(rs);
            editText.setText(rs[0].toString());
            System.out.println("mmmmmmmmmmmmmmm");
            System.out.println(editText.getText().toString());
        }

        @Override
        public void onPartialResults(Bundle partialResults) {

        }

        @Override
        public void onEvent(int eventType, Bundle params) {

        }
    };
	
	public void connect(){
		thread = new Thread(){
			public void run(){
				super.run();
				try{
					client = new Socket(ip,port);

					clientThread = new ClientThread(client,handler);
					clientThread.start();
				}catch(UnknownHostException e){
					e.printStackTrace();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		};
		thread.start();
		
	}
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btn_connect){

			connect();
		}
		else if(v.getId()==R.id.btn_send){
			clientThread.send(editText.getText().toString());
			editText.setText("");
		}
		else if(v.getId()==R.id.btn_stt){

			mRecognizer.startListening(i);
		}
	}


}
