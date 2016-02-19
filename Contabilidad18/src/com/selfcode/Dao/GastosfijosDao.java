package com.selfcode.Dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.selfcode.To.GastosfijosTo;
import com.selfcode.Until.ConexionData;

public class GastosfijosDao extends ConexionData{
	SQLiteDatabase db;
	Cursor cur;
	String sql;
	public GastosfijosDao(Context context) {
		super(context);
	}
	public List<GastosfijosTo> listar() throws ParseException {
		GastosfijosTo to;
	    List<GastosfijosTo> recordsList = new ArrayList<GastosfijosTo>();
	    String sql = "SELECT g.id_gastos,g.descripcion,g.id_categoria,"
	    		+ "c.color FROM gastosfijos g, categorias c where g.id_categoria=c.id_categoria";
	    db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(sql, null);
	    if (cursor.moveToFirst()) {
	        do {
	        	to=new GastosfijosTo();
	        	to.setId_gastos(cursor.getInt(0));
	        	to.setDescripcion(cursor.getString(1));
	        	to.setId_categoria(cursor.getInt(2));
	        	to.setColor(cursor.getString(3));
	            recordsList.add(to);
	        } while (cursor.moveToNext());
	    }
	    cursor.close();
	    db.close();
	    return recordsList;
	}
	public GastosfijosTo listar_Id(int id) throws ParseException {
		GastosfijosTo to = null;
	    String sql = "SELECT g.id_gastos,g.descripcion,g.id_categoria,"
	    		+ "c.color FROM gastosfijos g, categorias c where g.id_categoria=c.id_categoria and id_gastos =" + id;
	    db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(sql, null);
	    if (cursor.moveToFirst()) {
	    	to=new GastosfijosTo();
	    	to.setId_gastos(cursor.getInt(0));
        	to.setDescripcion(cursor.getString(1));
        	to.setId_categoria(cursor.getInt(2));
        	to.setColor(cursor.getString(3));
	    }
	    cursor.close();
	    db.close();
	    return to;
	}
	public boolean  insertar(GastosfijosTo object){
		ContentValues values = new ContentValues();
		values.put("id_categoria",object.getId_categoria());
		values.put("descripcion", object.getDescripcion());
//		values.put("_id", object.get_id());
		db = getWritableDatabase();
		boolean createSuccessful = db.insert("gastosfijos ", null, values) > 0;
		db.close();
		return createSuccessful;		
	}
	public boolean actualizar(GastosfijosTo to) {
	    ContentValues values = new ContentValues();
	    values.put("descripcion", to.getDescripcion());
	    values.put("id_categoria",to.getId_categoria());
	    String where = "id_gastos = ?";
	    String[] whereArgs = {Integer.toString(to.getId_gastos()) };
	    SQLiteDatabase db = this.getWritableDatabase();
	    boolean updateSuccessful = db.update("gastosfijos ", values, where, whereArgs) > 0;
	    db.close();
	    return updateSuccessful;
	}
	public boolean eliminar(int id){
		String where = "id_gastos = "+id;
		SQLiteDatabase db1 = this.getWritableDatabase();
		boolean deleteSuccessful = db1.delete("gastosfijos ", where, null) > 0;
	    db1.close();
		return deleteSuccessful;
	}
	public int Cantidad() {		
		int cantidad=0;
		db= getReadableDatabase();	
		sql="select count(*) from gastosfijos";
		cur=db.rawQuery(sql, null);	
		if (cur.moveToNext()) {
			cantidad=cur.getInt(0);
		}
		cur.close();
		db.close();
		return cantidad;
	}
	public String[][] Gastos(int i){
		String [][] _Gastos=new String[i][2];
		sql="SELECT id_gastos,descripcion FROM gastosfijos";
		db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(sql, null);
	    int cont=0;
	    if (cursor.moveToFirst()) {
	        do {
	        	_Gastos[cont][0]=cursor.getString(0);
	        	_Gastos[cont][1]=cursor.getString(1);
	        	cont++;
	        } while (cursor.moveToNext());
	    }
	    cursor.close();
		db.close();
		return _Gastos;
	}
	public int validarElim(int _id){
		int cantidad=0;
		db= getReadableDatabase();	
		sql="select count(*) from gastosfijos g,datos d "
				+ "where g.id_gastos=d.id_gastos and g.id_gastos="+_id;
		cur=db.rawQuery(sql, null);	
		if (cur.moveToNext()) {
			cantidad=cur.getInt(0);
		}
		cur.close();
		db.close();
		return cantidad;
	}
}
