package com.selfcode.contabilidad18;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.selfcode.Export.Export;

public class contabilidad18_main extends Activity{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contabilidad18_main);
		setTitle("Inicio");
	}
	
	//----------------------------------------------------------------
		 @Override
		 public boolean onCreateOptionsMenu(Menu menu) {
		 	// Inflate the menu; this adds items to the action bar if it is present.
		 	getMenuInflater().inflate(R.menu.main, menu);
		 	return true;
		 }
		 public boolean onOptionsItemSelected(MenuItem item) {
		     switch (item.getItemId()) {
		         case R.id.action_nuevo:
		        	// showDialog(DATE_DIALOG_ID);
		             return true;
		         case R.id.action_buscar:
		        	 Toast.makeText(this, "Buscar", Toast.LENGTH_SHORT).show();
		             return true;
		         case R.id.action_settings:
		        	 startActivity(new Intent(this,acerca.class));
		             return true;
		         case R.id.action_help:
		        	 startActivity(new Intent(this,ayuda.class));
		             return true;
		         default:
		             return super.onOptionsItemSelected(item);
		     }
		 }
	//----------------------------------------------------------------
	public void onCategoria(View v){
		startActivity(new Intent(this,categoria_main.class));
	}
	public void onGastos(View v){
		startActivity(new Intent(this,gastosfijos_main.class));
	}
	public void onDatos(View v){
		Intent intent = new Intent(contabilidad18_main.this, datos_main.class);
		intent.putExtra("action", "new");
		startActivity(intent);
	}
	public void onListarDatos(View v){
		Intent intent = new Intent(contabilidad18_main.this, datos_report.class);
		startActivity(intent);
	}
	public void onReportar(View v){
		startActivity(new Intent(this,Export.class));
	}
}
