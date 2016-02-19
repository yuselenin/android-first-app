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
import com.selfcode.To.GastosfijosTo;
import com.selfcode.contabilidad18.R;
import com.selfcode.contabilidad18.gastosfijos_main;

public class gastosfijos_adapter extends ArrayAdapter<GastosfijosTo> {
	private static LayoutInflater inflater = null;
	private gastosfijos_main context;

	public gastosfijos_adapter(gastosfijos_main context, List<GastosfijosTo> objects) {
		super(context, R.layout.main_adapter, objects);
		this.context = context;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	public static class ViewHolder {
		public TextView tituloNombre;
		public ImageView SelectButton;
	}
	public View getView(final int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ViewHolder holder;
		
		if (row == null) {
			row = inflater.inflate(R.layout.main_adapter, null);
			holder = new ViewHolder();
			holder.tituloNombre = (TextView) row.findViewById(R.id.lblTitles);
			holder.SelectButton = (ImageView) row.findViewById(R.id.btnOptionTitle);
			row.setTag(holder);
		} else {
			holder = (ViewHolder) row.getTag();
		}
		final GastosfijosTo entry = (GastosfijosTo) super.getItem(position);

		holder.tituloNombre.setText(entry.getDescripcion());
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
