package com.selfcode.contabilidad18;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.selfcode.Dao.CategoriaDao;
import com.selfcode.Dao.GastosfijosDao;
import com.selfcode.To.CategoriaTo;
import com.selfcode.To.GastosfijosTo;
import com.selfcode.Until.gastosfijos_adapter;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class gastosfijos_main extends ListActivity implements OnItemClickListener {
	GastosfijosDao dao;
	EditText txtDescripcion,txtId_categoria;
	TextView lblAyuda;
	Spinner txtColor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gastosfijos_main);
		refresh();
	}
	public void inicio(){
		dao=new GastosfijosDao(this);		
        getListView().setFastScrollEnabled(true);
        getListView().setTextFilterEnabled(true);
        setTitle("Gastos Fijos");
        List<GastosfijosTo> dts;
		try {
			dts = (List<GastosfijosTo>)dao.listar();
			setListAdapter(new gastosfijos_adapter(this, dts));
	        getListView().setOnItemClickListener(this);	
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public void onNuevo(View view){
		final Context context = view.getContext();
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View formElementsView = inflater.inflate(R.layout.gastosfijos_form, null, false);
//		CategoriaDao categorias=new CategoriaDao(context);
//		final int cant_cat=categorias.Cantidad();
//		Log.e("111111111111111",""+cant_cat );
//		final String[][] datos =categorias.Categorias(cant_cat);
//		lblAyuda=(TextView) findViewById(R.id.txt_text_help);
//		for (int i = 0; i <cant_cat; i++) {
//			Log.e("-----------", ""+datos[i][0]+". "+datos[i][1]+" \n ");
//			lblAyuda.append(""+datos[i][0]+". "+datos[i][1]+" \n ");
//		}
		//-----------------------------------------------------
//		String[] datos=new String[]{"Item 1","Item 2","Item 3"};
//		Spinner Gastos=(Spinner)findViewById(R.id.CmbOpciones);
//		ArrayAdapter<String> adaptador =new ArrayAdapter<String>(context,
//	            android.R.layout.simple_spinner_item, datos);
//		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		Gastos.setAdapter(adaptador);
			new AlertDialog.Builder(context).setView(formElementsView).setTitle("Nuevo Ragistro").setPositiveButton("Agregar",
					new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int id) {
			            	txtDescripcion = (EditText) formElementsView.findViewById(R.id.txtDescripcion);
			        		txtId_categoria = (EditText) formElementsView.findViewById(R.id.txtId_categoria);
//			        		txtColor=(Spinner) formElementsView.findViewById(R.id.CmbOpciones);
			        		String Descripcion = txtDescripcion.getText().toString();
			        		String id_Categoria=txtId_categoria.getText().toString().trim();
			        		//String Color = txtColor.getText().toString().trim().toUpperCase();
			        		
			        		GastosfijosTo data=new GastosfijosTo();
			        		
//			        		data.set_id(Integer.parseInt(personaId));
			        		if(Descripcion.length()!=0 && id_Categoria.length()!=0){
			        			data.setDescripcion(Descripcion);
			        			data.setId_categoria(Integer.parseInt(id_Categoria));
//				        		data.setColor(Color);
				        		
				        		boolean createSuccessful = new GastosfijosDao(context).insertar(data);
				        		if(createSuccessful){
				        		    Toast.makeText(context, "Informacion Guardada Exitosamente.", Toast.LENGTH_SHORT).show();
				        		    refresh();
				        		}else{
				        		    Toast.makeText(context, "Error al Insertar.", Toast.LENGTH_SHORT).show();
				        		    refresh();
				        		}
				        		dialog.cancel();
			        		}else{
			        			Toast.makeText(context, "Debe Ingresar una Descripcion y una Categoria", Toast.LENGTH_SHORT).show();
			        		}
			            }
			 
			        }).show();
	}
	public void countRecords() {
//		int recordCount = new GastosfijosDao(this).Cantidad();
//		TextView textViewRecordCount = (TextView) findViewById(R.id.textViewRecordCount);
//		textViewRecordCount.setText(recordCount + " Registros Encontrados.");
	}
	public void refresh(){
		inicio();
		countRecords();
	}
	
	public void selectTitulo(final GastosfijosTo book) {
		dao = new GastosfijosDao(this);
		final int id=book.getId_gastos();
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
//    	return false;
	}
	private void DeletedRecord(int _id){
		int cod_elim=new GastosfijosDao(this).validarElim(_id);
		if(cod_elim==0){
			boolean deleteSuccessful = new GastosfijosDao(this).eliminar(_id);
			if(deleteSuccessful){
				Toast.makeText(this, "Registro Eliminado correctamente.", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(this, "Error al eliminar.", Toast.LENGTH_SHORT).show();
			}
			inicio();
			countRecords();
		}else{
			Toast.makeText(this, "No se pudo eliminar por que hay "+cod_elim+" registros de gastos diarios que dependen de este registro", Toast.LENGTH_LONG).show();
		}
	}
	public void editRecord(final int _id) {
		try {
			LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final View formElementsView = inflater.inflate(R.layout.gastosfijos_form, null, false);
			
			final GastosfijosDao dao = new GastosfijosDao(this);
			final GastosfijosTo to = dao.listar_Id(_id); 
			txtDescripcion = (EditText) formElementsView.findViewById(R.id.txtDescripcion);
			txtId_categoria = (EditText) formElementsView.findViewById(R.id.txtId_categoria);
//			txtColor = (Spinner) formElementsView.findViewById(R.id.txtColor);
			txtDescripcion.setText(to.getDescripcion());
			txtId_categoria.setText(""+to.getId_categoria());
//			CategoriaDao categorias=new CategoriaDao(formElementsView.getContext());
//			final int cant_cat=categorias.Cantidad();
//			final String[][] datos =categorias.Categorias(cant_cat);
//			lblAyuda=(TextView) findViewById(R.id.txt_text_help);
//			lblAyuda.setText("_______ \n");
//			for (int i = 0; i <cant_cat; i++) {
//				lblAyuda.append(""+datos[i][0]+". "+datos[i][1]+" \n ");
//			}
			
			new AlertDialog.Builder(this).setView(formElementsView).setTitle("Actualizar").setPositiveButton("Guardar Cambios",
					new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int id) {
			            	Context _context = formElementsView.getContext();
			            	String _Descripcion = txtDescripcion.getText().toString();
			            	String id_Categoria=txtId_categoria.getText().toString().trim();
//			        		String Color = txtColor.getText().toString().trim().toUpperCase();
			            	if(_Descripcion.length()!=0&& id_Categoria.length()!=0){
				            	GastosfijosTo data=new GastosfijosTo();
				        		data.setId_gastos(_id);
				        		data.setDescripcion(_Descripcion);
				        		data.setId_categoria(Integer.parseInt(id_Categoria));
				        		
				        		boolean updateSuccessful = new GastosfijosDao(_context).actualizar(data);
				        		if(updateSuccessful){
				        		    Toast.makeText(_context, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show();
				        		    refresh();
				        		}else{
				        		    Toast.makeText(_context, "Error al actualizar Persona", Toast.LENGTH_SHORT).show();
				        		    refresh();
				        		}
				                dialog.cancel();
			            	}else{
			        			Toast.makeText(_context, "Debe Ingresar una Descripcion.", Toast.LENGTH_SHORT).show();
			        		}
		            }
		        }).show();
		} catch (Exception e) {
			 Toast.makeText(this, "Error al actualizar :"+e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}
}
