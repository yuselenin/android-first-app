package com.selfcode.To;

public class GastosfijosTo {
	private int id_gastos;
	private String descripcion;
	private int id_categoria;
	private String color;
	public GastosfijosTo(int id_gastos, String descripcion, int id_categoria,
			String color) {
		super();
		this.id_gastos = id_gastos;
		this.descripcion = descripcion;
		this.id_categoria = id_categoria;
		this.color = color;
	}
	public GastosfijosTo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId_gastos() {
		return id_gastos;
	}
	public void setId_gastos(int id_gastos) {
		this.id_gastos = id_gastos;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	
}
