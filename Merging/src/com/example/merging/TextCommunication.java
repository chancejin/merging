
package com.example.merging;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TextCommunication extends Activity implements OnClickListener{
	
	Button btn_connect;
	Button btn_send;
	TextView textView;
	EditText editText;
	
	Socket client;
	String ip = "14.63.226.208";
	int port = 8080;
	
	Thread thread;
	
	ClientThread clientThread;
	
	Handler handler;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_textcommunication);
		
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

}