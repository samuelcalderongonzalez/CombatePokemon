package models;

public class Entrenador {
	//Propiedades
	private String nombre;
	private Equipo equipo;
	
	public Entrenador(String nombre) {
		super();
		this.nombre = nombre;
		this.equipo = new Equipo();
	}
	//Getters
	public String getNombre() {
		return nombre;
	}

	public Equipo getEquipo() {
		return equipo;
	}
	
	
}
