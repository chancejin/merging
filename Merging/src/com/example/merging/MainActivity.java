/*
 * 친구목록 화면을 대신 하는 부분임!
 * 상대의 상태에 따라 다음 액티비티가 바뀌게 되는데,
 * 여기서는
 * 
 * 0-비장애인
 * 1-청각장애인
 * 2-언어장애인
 * 3-청각,언어장애인
 * 
 * 으로 표현하였다.
 * 
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
		

		System.out.println("넘어옴.");
		
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

		System.out.println("넘기자.");

		System.out.println("넘기자2.");
        startActivity(new Intent(this,SttTextCommunication.class));

		System.out.println("넘기자3");
	}
}