/*
 * 移쒓뎄紐⑸줉 �솕硫댁쓣 ���떊 �븯�뒗 遺�遺꾩엫!
 * �긽���쓽 �긽�깭�뿉 �뵲�씪 �떎�쓬 �븸�떚鍮꾪떚媛� 諛붾�뚭쾶 �릺�뒗�뜲,
 * �뿬湲곗꽌�뒗
 * 
 * 0-鍮꾩옣�븷�씤
 * 1-泥�媛곸옣�븷�씤
 * 2-�뼵�뼱�옣�븷�씤
 * 3-泥�媛�,�뼵�뼱�옣�븷�씤
 * 
 * �쑝濡� �몴�쁽�븯���떎.
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
		System.out.println("눌림!");
        startActivity(new Intent(this,TtsTextCommunication.class));
	}
}