package com.example.merging;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TtsTextCommunication extends Activity implements OnClickListener,OnInitListener{

	Button btn_connect;
	Button btn_send;
	TextView textView;
	EditText editText;
	
	private TextToSpeech tts;
	
	Socket client;
	String ip = "192.168.0.27";
	int port = 50000;
	
	Thread thread;
	
	ClientThread clientThread;
	
	Handler handler;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		System.out.println("아하하하하하");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ttstextcommunication);
		System.out.println("넘어옴!");
		
		btn_connect = (Button)findViewById(R.id.btn_connect);
		btn_send = (Button)findViewById(R.id.btn_send);
		textView = (TextView)findViewById(R.id.textView);
		editText = (EditText)findViewById(R.id.editText);
		
		tts = new TextToSpeech(this, this);
		
		handler = new Handler(){
			public void handleMessage(Message msg){
				super.handleMessage(msg);
				Bundle bundle = msg.getData();
				textView.append(bundle.getString("msg")+"\n");
				//TTS����߰�
				speakOut();
			}
		};
		
		btn_connect.setOnClickListener(this);
		btn_send.setOnClickListener(this);
	}
	
	public void connect(){
		thread = new Thread(){
			public void run(){
				super.run();
				try{
					System.out.println("connect1");
					client = new Socket(ip,port);

					System.out.println("connect2");
					clientThread = new ClientThread(client,handler);
					System.out.println("connect3");
					clientThread.start();
					System.out.println("connect4");
				}catch(UnknownHostException e){
					e.printStackTrace();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		};
		System.out.println("connect5");
		thread.start();
		System.out.println("connect6");
		
	}

	private void speakOut() {
        //Get the text typed
        String text = textView.getText().toString();
        //If no text is typed, tts will read out 'You haven't typed text'
        //else it reads out the text you typed
        if (text.length() == 0) {
        } else {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            textView.setText("");
        }

    }
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btn_connect){

			System.out.println("button1");
			connect();
		}
		else if(v.getId()==R.id.btn_send){
			System.out.println("button2");
			clientThread.send(editText.getText().toString());
			System.out.println("button3");
			editText.setText("");
			System.out.println("button4");
		}
	}

	@Override
	public void onInit(int status) {
        // TODO Auto-generated method stub
        //TTS is successfully initialized
        if (status == TextToSpeech.SUCCESS) {
            //Setting speech language
            int result = tts.setLanguage(Locale.KOREA);
            //If your device doesn't support language you set above
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                //Cook simple toast message with message
                Toast.makeText(this, "Language not support", Toast.LENGTH_LONG).show();
                Log.e("TTS", "Language is not supported");
            }
            //Enable the button - It was disabled in main.xml (Go back and Check it)
            else {
            }
            //TTS is not initialized properly
        } else {
            Toast.makeText(this, "TTS Initilization Failed", Toast.LENGTH_LONG).show();
            Log.e("TTS", "Initilization Failed");
        }
    }

	
	 public void onDestroy() {
	        // Don't forget to shutdown!
	        if (tts != null) {
	            tts.stop();
	            tts.shutdown();
	        }
	        super.onDestroy();
	 }

}
