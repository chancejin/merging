package com.example.merging;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class ClientThread extends Thread{

	BufferedReader bufferReader;
	BufferedWriter bufferWriter;
	
	Socket client;
	Handler handler;
	
	public ClientThread(Socket client, Handler handler){
		this.handler = handler;
		try{
			this.client = client;
			System.out.println("클라이언트쓰레드생성");
			bufferReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			bufferWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	//보내기
	public void send(String text){
		try{
			System.out.println(text);
			bufferWriter.write(text+"\n");
			bufferWriter.flush();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	//받기
	public String receive(){
		String msg = null;
		try{
			while(true){
				msg = bufferReader.readLine();
				Message m = new Message();
				Bundle bundle = new Bundle();
				bundle.putString("msg", msg);
				m.setData(bundle);
				handler.sendMessage(m);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return msg;
	}
	
	// 여기서 변수에 따라 run을 나눠서 receive를 할지 말지 생각해야함!
	public void run(){
		super.run();
		System.out.println("쓰레드 run!");
		receive();
	}
}