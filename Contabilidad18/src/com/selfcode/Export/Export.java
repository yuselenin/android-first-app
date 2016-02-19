package com.selfcode.Export;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;

import com.selfcode.Dao.DatosDao;
import com.selfcode.Dao.GastosfijosDao;
import com.selfcode.To.DatosTo;
import com.selfcode.To.GastosfijosTo;
import com.selfcode.contabilidad18.R;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

public class Export extends Activity implements OnClickListener{
	private DatePicker date_picker;
    private int year;
    private int month;
    private int day;
    static final int DATE_DIALOG_ID = 100;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.export);
        View writeExcelButton = findViewById(R.id.writeExcel);
        writeExcelButton.setOnClickListener(this);
    }
    public void onClick(View v) {
        switch (v.getId()) {
        
        case R.id.writeExcel:
//            try {
//				saveExcelFile(this,"myExcel.xls");
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
        }
    }
//    private static boolean saveExcelFile(Context context, String fileName) throws  ParseException { 
//        // check if available and not read only 
//        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) { 
//            Log.w("FileUtils", "Storage not available or read only"); 
//            return false; 
//        } 
//
//        boolean success = false; 
//
//        //New Workbook
//        Workbook wb = new HSSFWorkbook();
//
//        Cell c = null;
//
//        //Cell style for header row
//        CellStyle cs = wb.createCellStyle();
//        cs.setFillForegroundColor(HSSFColor.LIME.index);
//        cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//       
//        //New Sheet
//        Sheet sheet1 = null;
//        sheet1 = wb.createSheet("myOrder");
//        GastosfijosDao DAO=new GastosfijosDao(context);
//        DatosDao datosdao=new DatosDao(context);
//        final int mes=11;
//        int []id_active=datosdao.idActive(mes);//preguntar mes
//        int numColumnas=DAO.Cantidad();
//        for (int i = 0; i < 31; i++) {
//        	try {
//				DatosTo datoActual=datosdao.listar_Id(i);
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//        	if(i==0){
//        		Row row = sheet1.createRow(0);
//        		for (int j = 0; j <=numColumnas; j++) {
//        			if(j==0){
//        				c = row.createCell(j);
//        				c.setCellValue("Dia");
//                        c.setCellStyle(cs);
//                        sheet1.setColumnWidth(j, (15 * 30));
//        			}else{
//        				c = row.createCell(j);
//                		GastosfijosTo dts;
//						try {
//							dts = DAO.listar_Id(j);
//							c.setCellValue(""+dts.getDescripcion());
//	                        c.setCellStyle(cs);
//	                        sheet1.setColumnWidth(j, (15 * 300));
//						} catch (ParseException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//                        
//        			}
//				}
//        	}else{
//        		Row row1 = sheet1.createRow(i);
//        		for (int j = 0; j <=numColumnas; j++) {
//        			if(j==0){
//            				c = row1.createCell(j);
//            				c.setCellValue(i);
//                            c.setCellStyle(cs);
//                            sheet1.setColumnWidth(j, (15 * 30));
//               		}
//        		}
//        	}
//		}
//        Double datos;
//        for (int m = 0; m <id_active.length; m++) {
//			for (int f = 1; f < 32; f++) {
//				if(id_active[m]==f){
//					Row row3 = sheet1.createRow(f);
//					for (int d = 0; d < numColumnas; d++) {
//						if(d==0){
//							c = row3.createCell(d);
//            				c.setCellValue(f);
//                            c.setCellStyle(cs);
//                            sheet1.setColumnWidth(d, (15 * 30));
//						}else{
//							c = row3.createCell(d);
//							datos = datosdao.listar_IdGastos(d,f,11);
//							c.setCellValue(datos);
//	                        c.setCellStyle(cs);
//	                        sheet1.setColumnWidth(d, (15 * 300));
//						}
//					}
//				}
//			}
//		}
//        File file = new File(context.getExternalFilesDir("conta18"), fileName);
//        
//        FileOutputStream os = null; 
//
//        try { 
//            os = new FileOutputStream(file);
//            wb.write(os);
//            Log.w("FileUtils", "Writing file" + file); 
//            success = true; 
//        } catch (IOException e) { 
//            Log.w("FileUtils", "Error writing " + file, e); 
//        } catch (Exception e) { 
//            Log.w("FileUtils", "Failed to save file", e); 
//        } finally { 
//            try { 
//                if (null != os) 
//                    os.close(); 
//            } catch (Exception ex) { 
//            } 
//        } 
//
//        return success; 
//    } 
//    public static boolean isExternalStorageReadOnly() { 
//        String extStorageState = Environment.getExternalStorageState(); 
//        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) { 
//            return true; 
//        } 
//        return false; 
//    } 
//    public static boolean isExternalStorageAvailable() { 
//        String extStorageState = Environment.getExternalStorageState(); 
//        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) { 
//            return true; 
//        } 
//        return false; 
//    } 
//    @Override
//	 public boolean onCreateOptionsMenu(Menu menu) {
//	 	// Inflate the menu; this adds items to the action bar if it is present.
//	 	getMenuInflater().inflate(R.menu.datos, menu);
//	 	return true;
//	 }
//	 public boolean onOptionsItemSelected(MenuItem item) {
//	     switch (item.getItemId()) {
//	         case R.id.action_nuevo:
//	        	 showDialog(DATE_DIALOG_ID);
//	             return true;
//	         default:
//	             return super.onOptionsItemSelected(item);
//	     }
//	 }
//	 public void setCurrentDate() {
//			//text_date = (TextView) findViewById(R.id.text_date);
//			final Calendar calendar = Calendar.getInstance();
//			year = calendar.get(Calendar.YEAR);
//			month = calendar.get(Calendar.MONTH);
//			day = calendar.get(Calendar.DAY_OF_MONTH);
//			        date_picker.init(year, month, day, null);
//			        month=month+1;
//			        setTitle(day+"-"+month+"-"+year);
//			    }
//			    @Override
//		protected Dialog onCreateDialog(int id) {
//			        switch (id) {
//			        case DATE_DIALOG_ID:
//			           return new DatePickerDialog(this, datePickerListener, year, (month-1),day);
//			        }
//			        return null;
//			    }
//		public DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
//			        public void onDateSet(DatePicker view, int selectedYear,int selectedMonth, int selectedDay) {
//			            year = selectedYear;
//			            month = selectedMonth;
//			            day = selectedDay;
//			            date_picker.init(year, month, day, null);
//			            month=month+1;
//			            setTitle(day+"-"+month+"-"+year);
//			        }
//			 };
}