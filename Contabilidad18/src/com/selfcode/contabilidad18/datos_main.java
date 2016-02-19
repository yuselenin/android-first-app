package com.selfcode.contabilidad18;
import java.text.ParseException;
import java.util.Calendar;

import com.selfcode.Dao.DatosDao;
import com.selfcode.Dao.GastosfijosDao;
import com.selfcode.To.DatosTo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class datos_main extends Activity{
	EditText Descripcion, Costo,Cantidad;
	Spinner Gastos;
	Button btnGuardar;
	private DatePicker date_picker;
    private int year;
    private int month;
    private int day;
    static final int DATE_DIALOG_ID = 100;
    int _id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datos_main);
		Gastos=(Spinner)findViewById(R.id.spinner_);
		Descripcion=(EditText)findViewById(R.id.descripcion_dato);
		Costo=(EditText)findViewById(R.id.txtCosto);
		Cantidad=(EditText)findViewById(R.id.txtCantidad);
		btnGuardar=(Button)findViewById(R.id.btnGuardar);
		date_picker = (DatePicker) findViewById(R.id.fecha_registro);
		
		GastosfijosDao gastos_fijos=new GastosfijosDao(this);
		final int cant_gastos=gastos_fijos.Cantidad();
		final String[][] datos =gastos_fijos.Gastos(cant_gastos);
		final String[] id_Gasto=new String[cant_gastos];
		final String[] descr_Gasto=new String[cant_gastos];
		for (int i = 0; i <cant_gastos; i++) {
			id_Gasto[i]=datos[i][0];
			descr_Gasto[i]=datos[i][1];
		}
		ArrayAdapter<String> adaptador =new ArrayAdapter<String>(this,
		            android.R.layout.simple_spinner_item, descr_Gasto);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Gastos.setAdapter(adaptador);
		//1
		final String action=getIntent().getStringExtra("action");
		if(action.equals("new")){
			setCurrentDate();
		}else if(action.equals("edit")){
			_id=Integer.parseInt(getIntent().getStringExtra("id"));
			DatosDao func_edit=new DatosDao(this);
			try {
				DatosTo dats_edit=func_edit.listar_Id(_id);
				Gastos.setSelection((dats_edit.getId_gastos())-1);//revisar: denpen id 
				Descripcion.setText(dats_edit.getDescripcion());
				Costo.setText(""+dats_edit.getCosto());
				Cantidad.setText(""+dats_edit.getCantidad());
				day=dats_edit.getFecha_dia();
				month=dats_edit.getFecha_mes();
				month=month-1;//
				year=dats_edit.getFecha_año();
	            date_picker.init(year, month, day, null);
	            setTitle(day+"-"+(month+1)+"-"+year);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		OnClickListener clickGuardar=new OnClickListener() {			
		@Override
		public void onClick(View v) {
			String txtDescripcion=Descripcion.getText().toString(); 
			String txtCosto=Costo.getText().toString().trim();
			String txtCatidad=Cantidad.getText().toString().trim();
			if(txtCosto.length()!=0){
				DatosTo dts=new DatosTo();
				for (int i = 0; i < cant_gastos; i++) {
					if(Gastos.getSelectedItemPosition()==i){
						dts.setId_gastos(Integer.parseInt(id_Gasto[i]));
					}
				}
				dts.setDescripcion(txtDescripcion);
				dts.setCosto(Double.parseDouble(txtCosto));
				if(txtCatidad.length()!=0){dts.setCantidad(Integer.parseInt(txtCatidad));}else{dts.setCantidad(0);}
				dts.setFecha_dia(day);
				dts.setFecha_mes(month);
				dts.setFecha_año(year);
				if(action.equals("new")){
					boolean createSuccessful = new DatosDao(v.getContext()).insertar(dts);
	        		if(createSuccessful){
	        		    Toast.makeText(v.getContext(), "Datos Guardados correctamente", Toast.LENGTH_SHORT).show();
	        		    startActivity(new Intent(v.getContext(),datos_report.class));
	        		}else{
	        		    Toast.makeText(v.getContext(), "Error al Guardar", Toast.LENGTH_SHORT).show();
	        		}
				}else if(action.equals("edit")){
					dts.setId_dato(_id);
					boolean updateSuccessful = new DatosDao(v.getContext()).actualizar(dts);
	        		if(updateSuccessful){
	        		    Toast.makeText(v.getContext(), "Datos Actulizados correctamente", Toast.LENGTH_SHORT).show();
	        		    startActivity(new Intent(v.getContext(),datos_report.class));
	        		}else{
	        		    Toast.makeText(v.getContext(), "Error al Actualizar", Toast.LENGTH_SHORT).show();
	        		}
				}
			}else{
				Toast.makeText(v.getContext(), "Ingrese el Costo", Toast.LENGTH_LONG).show();
			}
			}
		};
		btnGuardar.setOnClickListener(clickGuardar);
		
	}
	public void onCancelar(View v){
		startActivity(new Intent(this,contabilidad18_main.class));
	}
	 @Override
	 public boolean onCreateOptionsMenu(Menu menu) {
	 	// Inflate the menu; this adds items to the action bar if it is present.
	 	getMenuInflater().inflate(R.menu.datos, menu);
	 	return true;
	 }
	 public boolean onOptionsItemSelected(MenuItem item) {
	     switch (item.getItemId()) {
	         case R.id.action_nuevo:
	        	 showDialog(DATE_DIALOG_ID);
	             return true;
	         default:
	             return super.onOptionsItemSelected(item);
	     }
	 }
	 public void setCurrentDate() {
			//text_date = (TextView) findViewById(R.id.text_date);
			final Calendar calendar = Calendar.getInstance();
			year = calendar.get(Calendar.YEAR);
			month = calendar.get(Calendar.MONTH);
			day = calendar.get(Calendar.DAY_OF_MONTH);
			        date_picker.init(year, month, day, null);
			        month=month+1;
			        setTitle(day+"-"+month+"-"+year);
			    }
			    @Override
		protected Dialog onCreateDialog(int id) {
			        switch (id) {
			        case DATE_DIALOG_ID:
			           return new DatePickerDialog(this, datePickerListener, year, month,day);
			        }
			        return null;
			    }
		public DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
			        public void onDateSet(DatePicker view, int selectedYear,int selectedMonth, int selectedDay) {
			            year = selectedYear;
			            month = selectedMonth;
			            day = selectedDay;
			            date_picker.init(year, month, day, null);
			            month=month+1;
			            setTitle(day+"-"+month+"-"+year);
			        }
			 };
}
