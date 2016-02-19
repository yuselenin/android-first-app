package com.selfcode.contabilidad18;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void onLogin(View v){
		startActivity(new Intent(this,contabilidad18_main.class));
	}
	
}
