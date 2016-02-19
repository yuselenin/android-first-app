package com.selfcode.Dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.selfcode.To.CategoriaTo;
import com.selfcode.Until.ConexionData;

public class CategoriaDao extends ConexionData{
	SQLiteDatabase db;
	Cursor cur;
	String sql;
	public CategoriaDao(Context context) {
		super(context);
	}
	public List<CategoriaTo> listar() throws ParseException {
		CategoriaTo to;
	    List<CategoriaTo> recordsList = new ArrayList<CategoriaTo>();
	    String sql = "SELECT * FROM categorias";
	    db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(sql, null);
	    if (cursor.moveToFirst()) {
	        do {
	        	to=new CategoriaTo();
	        	to.setId_categoria(cursor.getInt(0));
	        	to.setDescripcion(cursor.getString(1));
	        	to.setColor(cursor.getString(2));
	            recordsList.add(to);
	        } while (cursor.moveToNext());
	    }
	    cursor.close();
	    db.close();
	    return recordsList;
	}
	public CategoriaTo listar_Id(int id) throws ParseException {
		CategoriaTo to = null;
	    String sql = "SELECT * FROM categorias WHERE id_categoria = " + id;
	    db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(sql, null);
	    if (cursor.moveToFirst()) {
	    	to=new CategoriaTo();
	    	to.setId_categoria(cursor.getInt(0));
        	to.setDescripcion(cursor.getString(1));
        	to.setColor(cursor.getString(2));
	    }
	    cursor.close();
	    db.close();
	    return to;
	}
	public boolean  insertar(CategoriaTo object){
		ContentValues values = new ContentValues();
		values.put("color",object.getColor());
		values.put("descripcion", object.getDescripcion());
//		values.put("_id", object.get_id());
		db = getWritableDatabase();
		boolean createSuccessful = db.insert("categorias ", null, values) > 0;
		db.close();
		return createSuccessful;		
	}
	public boolean actualizar(CategoriaTo to) {
	    ContentValues values = new ContentValues();
	    values.put("descripcion", to.getDescripcion());
	    values.put("color",to.getColor());
	    String where = "id_categoria = ?";
	    String[] whereArgs = {Integer.toString(to.getId_categoria()) };
	    SQLiteDatabase db = this.getWritableDatabase();
	    boolean updateSuccessful = db.update("categorias ", values, where, whereArgs) > 0;
	    db.close();
	    return updateSuccessful;
	}
	public boolean eliminar(int id){
		String where = "id_categoria = "+id;
		SQLiteDatabase db1 = this.getWritableDatabase();
		boolean deleteSuccessful = db1.delete("categorias ", where, null) > 0;
	    db1.close();
		return deleteSuccessful;
	}
	public int Cantidad() {		
		int cantidad=0;
		db= getReadableDatabase();	
		sql="select count(*) from categorias";
		cur=db.rawQuery(sql, null);	
		if (cur.moveToNext()) {
			cantidad=cur.getInt(0);
		}
		cur.close();
		db.close();
		return cantidad;
	}
	public int validarElim(int _id){
		int cantidad=0;
		db= getReadableDatabase();	
		sql="select count(*) from categorias c,gastosfijos "
				+ "g where c.id_categoria=g.id_categoria and c.id_categoria="+_id;
		cur=db.rawQuery(sql, null);	
		if (cur.moveToNext()) {
			cantidad=cur.getInt(0);
		}
		cur.close();
		db.close();
		return cantidad;
	}
	//func auxiliar en vez de spinner
	public String[][] Categorias(int i){
		String [][] _Gastos=new String[i][2];
		sql="SELECT id_categoria,descripcion FROM categorias";
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
}
