package com.selfcode.Dao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.selfcode.To.DatosTo;
import com.selfcode.Until.ConexionData;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
public class DatosDao extends ConexionData{
	SQLiteDatabase db;
	Cursor cur;
	String sql;
	
	public DatosDao(Context context) {
		super(context);
	}
	public List<DatosTo> listar() throws ParseException {
		DatosTo to;
	    List<DatosTo> recordsList = new ArrayList<DatosTo>();
	    String sql = "SELECT d.id_dato,d.id_gastos,d.cantidad,d.costo,"
	    		+ "d.descripcion,d.fecha_dia,d.fecha_mes,d.fecha_año,"
	    		+ "g.descripcion,c.color FROM datos d, gastosfijos g,"
	    		+ " categorias c where g.id_categoria=c.id_categoria and d.id_gastos=g.id_gastos";
	    db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(sql, null);
	    if (cursor.moveToFirst()) {
	        do {
	        	to=new DatosTo();
	        	to.setId_dato(cursor.getInt(0));
	        	to.setId_gastos(cursor.getInt(1));
	        	to.setCantidad(cursor.getInt(2));
	        	to.setCosto(cursor.getDouble(3));
	        	to.setDescripcion(cursor.getString(4));
	        	to.setFecha_dia(cursor.getInt(5));
	        	to.setFecha_mes(cursor.getInt(6));
	        	to.setFecha_año(cursor.getInt(7));
	        	to.setDescripcion_gastos(cursor.getString(8));
	        	to.setColor(cursor.getString(9));
	            recordsList.add(to);
	        } while (cursor.moveToNext());
	    }
	    cursor.close();
	    db.close();
	    return recordsList;
	}
	public DatosTo listar_Id(int id) throws ParseException {
		DatosTo to = null;
	    String sql = "SELECT * FROM datos WHERE id_dato = " + id;
	    db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(sql, null);
	    if (cursor.moveToFirst()) {
	    	to=new DatosTo();
	    	to.setId_dato(cursor.getInt(0));
        	to.setId_gastos(cursor.getInt(1));
        	to.setCantidad(cursor.getInt(2));
        	to.setCosto(cursor.getDouble(3));
        	to.setDescripcion(cursor.getString(4));
        	to.setFecha_dia(cursor.getInt(5));
        	to.setFecha_mes(cursor.getInt(6));
        	to.setFecha_año(cursor.getInt(7));
        	
	    }
	    cursor.close();
	    db.close();
	    return to;
	}
	
	public DatosTo listar_porfecha(int id) throws ParseException {
		DatosTo to = null;
	    String sql = "SELECT * FROM datos WHERE id_dato = " + id;
	    db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(sql, null);
	    if (cursor.moveToFirst()) {
	    	to=new DatosTo();
	    	to.setId_dato(cursor.getInt(0));
        	to.setId_gastos(cursor.getInt(1));
        	to.setCantidad(cursor.getInt(2));
        	to.setCosto(cursor.getDouble(3));
        	to.setDescripcion(cursor.getString(4));
        	to.setFecha_dia(cursor.getInt(5));
        	to.setFecha_mes(cursor.getInt(6));
        	to.setFecha_año(cursor.getInt(7));
        	
	    }
	    cursor.close();
	    db.close();
	    return to;
	}
	
	public boolean  insertar(DatosTo object){
		
	
		ContentValues values = new ContentValues();
		//values.put("fecha", " "+object.getFecha());
		values.put("fecha_año",object.getFecha_año());
		values.put("fecha_mes",object.getFecha_mes());
		values.put("fecha_dia",object.getFecha_dia());
		values.put("descripcion", object.getDescripcion());
		values.put("costo", object.getCosto());
		values.put("cantidad", object.getCantidad());
		values.put("id_gastos", object.getId_gastos());
//		values.put("_id", object.get_id());
		db = getWritableDatabase();
		boolean createSuccessful = db.insert("datos ", null, values) > 0;
		db.close();
		return createSuccessful;		
	}
	
	public boolean actualizar(DatosTo to) {
	    ContentValues values = new ContentValues();
	    values.put("id_gastos", to.getId_gastos());
	    values.put("cantidad", to.getCantidad());
	    values.put("costo", to.getCosto());
	    values.put("descripcion", to.getDescripcion());
	    values.put("fecha_dia", to.getFecha_dia());
	    values.put("fecha_mes", to.getFecha_mes());
	    values.put("fecha_año", to.getFecha_año());
	   
	    String where = "id_dato = ?";
	    String[] whereArgs = {Integer.toString(to.getId_dato()) };
	    SQLiteDatabase db = this.getWritableDatabase();
	    boolean updateSuccessful = db.update("datos ", values, where, whereArgs) > 0;
	    db.close();
	    return updateSuccessful;
	}
	public boolean eliminar(int id){
		String where = "id_dato = "+id;
		SQLiteDatabase db1 = this.getWritableDatabase();
		boolean deleteSuccessful = db1.delete("datos ", where, null) > 0;
	    db1.close();
		return deleteSuccessful;
	}
	public int Cantidad() {		
		int cantidad=0;
		db= getReadableDatabase();	
		sql="select count(*) from datos";
		cur=db.rawQuery(sql, null);	
		if (cur.moveToNext()) {
			cantidad=cur.getInt(0);
		}
		cur.close();
		db.close();
		return cantidad;
	}
	
	public int Cantidad_datos(int dia,int mes) {		
		int cantidad=0;
		db= getReadableDatabase();	
		sql="select count(*) from datos where fecha_dia>=1 and fecha_dia<="+dia+" and fecha_mes ="+mes+"";
         cur=db.rawQuery(sql, null);	
		if (cur.moveToNext()) {
			cantidad=cur.getInt(0);
		}
		cur.close();
		db.close();
		return cantidad;
	}
	public int[] idActive(int mes) {	
		int cant=0;
		db= getReadableDatabase();
		sql="select count(distinct fecha_dia) from datos where fecha_mes="+mes;
        cur=db.rawQuery(sql, null);	
		if (cur.moveToNext()) {
			cant=cur.getInt(0);
		}
		cur.close();
		int [] cantidad=new int[cant];
		sql="SELECT distinct  fecha_dia FROM datos where fecha_mes="+mes;
		db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(sql, null);
	    int cont=0;
	    if (cursor.moveToFirst()) {
	        do {
	        	cantidad[cont]=cursor.getInt(0);
	        	cont++;
	        } while (cursor.moveToNext());
	    }
	    cursor.close();
		db.close();
		return cantidad;
	}
	public Double listar_IdGastos(int id_gastos,int d,int m){
		Double to = 0.00;
	    String sql = "SELECT sum(costo) FROM datos  WHERE  id_gastos ="+id_gastos+" and fecha_dia ="+d+" and fecha_mes="+m;
	    db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(sql, null);
	    if (cursor.moveToFirst()) {
	    	to=cursor.getDouble(0);
	    	String aux=""+to;
	    	if(aux.trim().length()==0){
	    		to=0.00;
	    	}
	    }
	    cursor.close();
	    db.close();
		return to;
	}	
}
