/*
 * 연락처에 클릭하면 넘어와야 하는 click 메소드가 존재하는 곳!
 * DB에 접근해서 상대의 장애 상태를 파악하여 그 값에 따라 다음 activity를 다르게 해야 한다.
 * 이 때,
 * 0 - 비장애인
 * 1 - 청각장애
 * 2 - 언어장애
 * 3 - 청각+언어 장애
 * 라고 한다.
 * 
 * 나의 장애상태도 파악해야 하는데, 이는 설정에 저장하도록 하든지.. 아니면 역시 DB 접근하든지..
 * */


package com.example.merging;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

	Button btn_text;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

		System.out.println("�꽆�뼱�샂.");
		
		btn_text = (Button)findViewById(R.id.btn_text);
		btn_text.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		// 연락처 눌렀을 때 여기로 클릭되어야한다는거!!!!!
		// 이때 DB에 접근해서 상대의 장애상태에 따라 다음 activity를 다르게 해야함!!
        startActivity(new Intent(this,TextCommunication.class));
	}
}