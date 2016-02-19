package com.selfcode.Until;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.selfcode.To.DatosTo;
import com.selfcode.contabilidad18.R;
import com.selfcode.contabilidad18.datos_report;

public class datos_adapter extends ArrayAdapter<DatosTo> {
	private static LayoutInflater inflater = null;
	private datos_report context;

	public datos_adapter(datos_report context, List<DatosTo> objects) {
		super(context, R.layout.datos_adapter, objects);
		this.context = context;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	public static class ViewHolder {
		public TextView tituloNombre;
		public TextView cantidad;
		public TextView costo;
		public TextView descripcion;
		public TextView fecha;
		public ImageView SelectButton;
	}
	public View getView(final int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ViewHolder holder;
		
		if (row == null) {
			row = inflater.inflate(R.layout.datos_adapter, null);
			holder = new ViewHolder();
			holder.tituloNombre = (TextView) row.findViewById(R.id.lbl_Descripcion_gastos);
			holder.cantidad = (TextView) row.findViewById(R.id.lbl_Cantidad);
			holder.costo = (TextView) row.findViewById(R.id.lbl_Costo);
			holder.descripcion = (TextView) row.findViewById(R.id.lblDescripcion_dato);
			holder.fecha = (TextView) row.findViewById(R.id.lbl_Fecha);
			holder.SelectButton = (ImageView) row.findViewById(R.id.btnOption);
			row.setTag(holder);
		} else {
			holder = (ViewHolder) row.getTag();
		}
		final DatosTo entry = (DatosTo) super.getItem(position);

		holder.tituloNombre.setText(entry.getDescripcion_gastos());
		holder.cantidad.setText(""+entry.getCantidad());
		holder.costo.setText("s/. "+entry.getCosto());
		holder.descripcion.setText(entry.getDescripcion());
		holder.fecha.setText(""+entry.getFecha_dia()+"-"+entry.getFecha_mes()+"-"+entry.getFecha_año());
		row.setBackgroundColor(Color.parseColor(entry.getColor()));
		holder.SelectButton
				.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						context.selectTitulo(entry);
					}
				});
		return row;
	}

}
