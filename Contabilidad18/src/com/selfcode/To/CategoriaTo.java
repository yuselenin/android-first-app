package com.selfcode.To;

public class CategoriaTo {
	private int id_categoria;
	private String descripcion;
	private String color;
	public CategoriaTo(int id_categoria, String descripcion, String color) {
		super();
		this.id_categoria = id_categoria;
		this.descripcion = descripcion;
		this.color = color;
	}
	public CategoriaTo() {
		super();
	}
	public int getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
