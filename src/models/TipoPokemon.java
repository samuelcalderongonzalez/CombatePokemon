package models;

public class TipoPokemon {
	//Tiene un string con el tipo y poco más. Lo utilizo tanto en pokemons como en movimientos
	private String nombre;
	
	public TipoPokemon(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
	
	
}
