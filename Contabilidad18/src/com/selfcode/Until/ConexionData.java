package com.selfcode.Until;

import pe.upeu.util.SQLiteAssetHelper;
import android.content.Context;

public class ConexionData extends SQLiteAssetHelper {
	private static final String DATABASE_NAME="contabilidad18.sqlite";
	private static final int DATABASE_VERSION=3;
		
	public ConexionData(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

}