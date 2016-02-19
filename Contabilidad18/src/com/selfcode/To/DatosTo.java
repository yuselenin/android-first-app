package com.selfcode.To;
public class DatosTo {
	private int id_dato;
	private int id_gastos;
	private int cantidad;
	private Double costo;
	private String Descripcion;
	private int fecha_dia;
	private int fecha_mes;
	private int fecha_año;
	private String Descripcion_gastos;
	private String Color;
	public DatosTo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DatosTo(int id_dato, int id_gastos, int cantidad, Double costo,
			String descripcion, int fecha_dia, int fecha_mes, int fecha_año,
			String descripcion_gastos, String color) {
		super();
		this.id_dato = id_dato;
		this.id_gastos = id_gastos;
		this.cantidad = cantidad;
		this.costo = costo;
		Descripcion = descripcion;
		this.fecha_dia = fecha_dia;
		this.fecha_mes = fecha_mes;
		this.fecha_año = fecha_año;
		Descripcion_gastos = descripcion_gastos;
		Color = color;
	}
	public int getId_dato() {
		return id_dato;
	}
	public void setId_dato(int id_dato) {
		this.id_dato = id_dato;
	}
	public int getId_gastos() {
		return id_gastos;
	}
	public void setId_gastos(int id_gastos) {
		this.id_gastos = id_gastos;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Double getCosto() {
		return costo;
	}
	public void setCosto(Double costo) {
		this.costo = costo;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public int getFecha_dia() {
		return fecha_dia;
	}
	public void setFecha_dia(int fecha_dia) {
		this.fecha_dia = fecha_dia;
	}
	public int getFecha_mes() {
		return fecha_mes;
	}
	public void setFecha_mes(int fecha_mes) {
		this.fecha_mes = fecha_mes;
	}
	public int getFecha_año() {
		return fecha_año;
	}
	public void setFecha_año(int fecha_año) {
		this.fecha_año = fecha_año;
	}
	public String getDescripcion_gastos() {
		return Descripcion_gastos;
	}
	public void setDescripcion_gastos(String descripcion_gastos) {
		Descripcion_gastos = descripcion_gastos;
	}
	public String getColor() {
		return Color;
	}
	public void setColor(String color) {
		Color = color;
	}
	
}
