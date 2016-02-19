package com.selfcode.contabilidad18;

import java.text.ParseException;
import java.util.List;

import com.selfcode.Dao.CategoriaDao;
import com.selfcode.To.CategoriaTo;
import com.selfcode.Until.categoria_adapter;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class categoria_main extends ListActivity implements OnItemClickListener{
	CategoriaDao dao;
	EditText txtDescripcion,txtColor;
//	String []itemColor={"GREEN","BLUE","RED","CYAN","MAGENTA",
//			"GRAY","WHITE","YELLOW"};
	String []item={
			"WHITE","#f44336","#e91e63","#9c27b0","#673ab7",
			"#3f51b5","#2196f3","#03A9F4","#00BCD4","#009688",
			"#4caf50","#8BC34A","#CDDC39","#FFEB3B","#FFC107",
			"#FF9800","#FF5722","#795548","#9E9E9E","#607D8B"};
//this colors are the material desing colors
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.categoria_main);
		refresh();
	}
	public void inicio(){
		dao=new CategoriaDao(this);		
        getListView().setFastScrollEnabled(true);
        getListView().setTextFilterEnabled(true);
        setTitle("Categorias");
        List<CategoriaTo> dts;
		try {
			dts = (List<CategoriaTo>)dao.listar();
			setListAdapter(new categoria_adapter(this, dts));
	        getListView().setOnItemClickListener(this);	
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public void onNuevo(View view){
		final Context context = view.getContext();
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View formElementsView = inflater.inflate(R.layout.categoria_form, null, false);
			new AlertDialog.Builder(context).setView(formElementsView).setTitle("Nueva Categoría").setPositiveButton("Agregar",
					new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int id) {
			        		txtDescripcion = (EditText) formElementsView.findViewById(R.id.txtDescripcion);
			        		txtColor=(EditText) formElementsView.findViewById(R.id.txtColor);
			        		String Descripcion = txtDescripcion.getText().toString();
			        		String Color = txtColor.getText().toString().trim().toUpperCase();
			        		CategoriaTo data=new CategoriaTo();
//			        		data.set_id(Integer.parseInt(personaId));
			        		if(Descripcion.length()!=0){
			        			data.setDescripcion(Descripcion);
				        		if(Color.equals("1")){data.setColor(item[1]);}
				        		else if(Color.equals("2")){data.setColor(item[2]);}
				        		else if(Color.equals("3")){data.setColor(item[3]);}
				        		else if(Color.equals("4")){data.setColor(item[4]);}
				        		else if(Color.equals("5")){data.setColor(item[5]);}
				        		else if(Color.equals("6")){data.setColor(item[6]);}
				        		else if(Color.equals("7")){data.setColor(item[7]);}
				        		else if(Color.equals("8")){data.setColor(item[8]);}
				        		else if(Color.equals("9")){data.setColor(item[9]);}
				        		else if(Color.equals("10")){data.setColor(item[10]);}
				        		else if(Color.equals("11")){data.setColor(item[11]);}
				        		else if(Color.equals("12")){data.setColor(item[12]);}
				        		else if(Color.equals("13")){data.setColor(item[13]);}
				        		else if(Color.equals("14")){data.setColor(item[14]);}
				        		else if(Color.equals("15")){data.setColor(item[15]);}
				        		else if(Color.equals("16")){data.setColor(item[16]);}
				        		else if(Color.equals("17")){data.setColor(item[17]);}
				        		else if(Color.equals("18")){data.setColor(item[18]);}
				        		else if(Color.equals("19")){data.setColor(item[19]);}
				        		else{data.setColor(item[0]);}
				        		boolean createSuccessful = new CategoriaDao(context).insertar(data);
				        		if(createSuccessful){
				        		    Toast.makeText(context, "Informacion Guardada Exitosamente.", Toast.LENGTH_SHORT).show();
				        		    refresh();
				        		}else{
				        		    Toast.makeText(context, "Error al Insertar.", Toast.LENGTH_SHORT).show();
				        		    refresh();
				        		}
				        		dialog.cancel();
			        		}else{
			        			Toast.makeText(context, "Debe Ingresar una Descripcion.", Toast.LENGTH_SHORT).show();
			        		}
			            }
			 
			        }).show();
	}
	public void countRecords() {
//		int recordCount = new CategoriaDao(this).Cantidad();
//		TextView textViewRecordCount = (TextView) findViewById(R.id.textViewRecordCount);
//		textViewRecordCount.setText(recordCount + " Registros Encontrados.");
	}
	public void refresh(){
		inicio();
		countRecords();
	}
	
	public void selectTitulo(final CategoriaTo book) {
		dao = new CategoriaDao(this);
		final int id=book.getId_categoria();
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
		int cod_elim=new CategoriaDao(this).validarElim(_id);
		if(cod_elim==0){
			boolean deleteSuccessful = new CategoriaDao(this).eliminar(_id);
			if(deleteSuccessful){
				
			    Toast.makeText(this, "Categoria Eliminada correctamente.", Toast.LENGTH_SHORT).show();
			}else{
			    Toast.makeText(this, "Error al eliminar.", Toast.LENGTH_SHORT).show();
			}
		}else{
			Toast.makeText(this, "No se pudo eliminar por que hay "+cod_elim+" gastos fijos que dependen de este registro", Toast.LENGTH_LONG).show();
		}
		
		inicio();
		countRecords();
	}
	public void editRecord(final int _id) {
		try {
			LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final View formElementsView = inflater.inflate(R.layout.categoria_form, null, false);
			
			final CategoriaDao dao = new CategoriaDao(this);
			final CategoriaTo to = dao.listar_Id(_id); 
			txtDescripcion = (EditText) formElementsView.findViewById(R.id.txtDescripcion);
			txtColor = (EditText) formElementsView.findViewById(R.id.txtColor);
			txtDescripcion.setText(to.getDescripcion());
			String color_aux=to.getColor().toString().trim();
			if(color_aux.equals("#f44336")){txtColor.setText("1");}
			else if(color_aux.equals("#e91e63")){txtColor.setText("2");}
			else if(color_aux.equals("#9c27b0")){txtColor.setText("3");}
			else if(color_aux.equals("#673ab7")){txtColor.setText("4");}
			else if(color_aux.equals("#3f51b5")){txtColor.setText("5");}
			else if(color_aux.equals("#2196f3")){txtColor.setText("6");}
			else if(color_aux.equals("#03A9F4")){txtColor.setText("7");}
			else if(color_aux.equals("#00BCD4")){txtColor.setText("8");}
			else if(color_aux.equals("#009688")){txtColor.setText("9");}
			else if(color_aux.equals("#4caf50")){txtColor.setText("10");}
			else if(color_aux.equals("#8BC34A")){txtColor.setText("11");}
			else if(color_aux.equals("#CDDC39")){txtColor.setText("12");}
			else if(color_aux.equals("#FFEB3B")){txtColor.setText("13");}
			else if(color_aux.equals("#FFC107")){txtColor.setText("14");}
			else if(color_aux.equals("#FF9800")){txtColor.setText("15");}
			else if(color_aux.equals("#FF5722")){txtColor.setText("16");}
			else if(color_aux.equals("#795548")){txtColor.setText("17");}
			else if(color_aux.equals("#9E9E9E")){txtColor.setText("18");}
			else if(color_aux.equals("#607D8B")){txtColor.setText("19");}
			else{txtColor.setText("0");}
			new AlertDialog.Builder(this).setView(formElementsView).setTitle("Actualizar Categoria").setPositiveButton("Guardar Cambios",
					new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int id) {
			            	Context _context = formElementsView.getContext();
			            	String _Descripcion = txtDescripcion.getText().toString();
			        		String Color = txtColor.getText().toString().trim().toUpperCase();
			            	if(_Descripcion.length()!=0){
				            	CategoriaTo data=new CategoriaTo();
				        		data.setId_categoria(_id);
				        		data.setDescripcion(_Descripcion);
				        		if(Color.equals("1")){data.setColor(item[1]);}
				        		else if(Color.equals("2")){data.setColor(item[2]);}
				        		else if(Color.equals("3")){data.setColor(item[3]);}
				        		else if(Color.equals("4")){data.setColor(item[4]);}
				        		else if(Color.equals("5")){data.setColor(item[5]);}
				        		else if(Color.equals("6")){data.setColor(item[6]);}
				        		else if(Color.equals("7")){data.setColor(item[7]);}
				        		else if(Color.equals("8")){data.setColor(item[8]);}
				        		else if(Color.equals("9")){data.setColor(item[9]);}
				        		else if(Color.equals("10")){data.setColor(item[10]);}
				        		else if(Color.equals("11")){data.setColor(item[11]);}
				        		else if(Color.equals("12")){data.setColor(item[12]);}
				        		else if(Color.equals("13")){data.setColor(item[13]);}
				        		else if(Color.equals("14")){data.setColor(item[14]);}
				        		else if(Color.equals("15")){data.setColor(item[15]);}
				        		else if(Color.equals("16")){data.setColor(item[16]);}
				        		else if(Color.equals("17")){data.setColor(item[17]);}
				        		else if(Color.equals("18")){data.setColor(item[18]);}
				        		else if(Color.equals("19")){data.setColor(item[19]);}
				        		else{data.setColor(item[0]);}
				        		boolean updateSuccessful = new CategoriaDao(_context).actualizar(data);
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
