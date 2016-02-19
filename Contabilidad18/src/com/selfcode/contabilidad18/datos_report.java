package com.selfcode.contabilidad18;

import java.text.ParseException;
import java.util.List;

import com.selfcode.Dao.DatosDao;
import com.selfcode.Dao.DatosDao;
import com.selfcode.To.DatosTo;
import com.selfcode.To.DatosTo;
import com.selfcode.Until.categoria_adapter;
import com.selfcode.Until.datos_adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class datos_report extends ListActivity implements OnItemClickListener{
	DatosDao dao;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datos_report);
		init();
	}
	public void init(){
		dao=new DatosDao(this);		
        getListView().setFastScrollEnabled(true);
        getListView().setTextFilterEnabled(true);
        setTitle("Reporte de Gastos");
        List<DatosTo> dts;
		try {
			dts = (List<DatosTo>)dao.listar();
			setListAdapter(new datos_adapter(this, dts));
	        getListView().setOnItemClickListener(this);	
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void selectTitulo(final DatosTo book) {
		dao = new DatosDao(this);
		final int id=book.getId_dato();
		final String decrip=book.getDescripcion();
		final CharSequence[] items = { "Editar", "Eliminar" };
    	new AlertDialog.Builder(this).setTitle("Opciones")
    	    .setItems(items, new DialogInterface.OnClickListener() {
    	        public void onClick(DialogInterface dialog, int item) {
    	    		if (item == 0) {
    	    			editRecord(id);
    	        	}else if (item == 1) {
    	    		    DeletedRecord(id);
    	    		}
    	        	dialog.dismiss();
    	        }
    	    }).show();
	}
	private void DeletedRecord(int _id){
		boolean deleteSuccessful = new DatosDao(this).eliminar(_id);
		if(deleteSuccessful){
		    Toast.makeText(this, "Categoria Eliminada correctamente.", Toast.LENGTH_SHORT).show();
		    init();
		}else{
		    Toast.makeText(this, "Error al eliminar.", Toast.LENGTH_SHORT).show();
		}
	}
	public void editRecord(final int _id) {
		Intent intent = new Intent(datos_report.this, datos_main.class);
		intent.putExtra("action", "edit");
		intent.putExtra("id", ""+_id);
		startActivity(intent);
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}
	
}
