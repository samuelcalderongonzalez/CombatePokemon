package models;

public class Movimiento {
	//Propiedades
	private String nombre;
	private TipoPokemon tipoPokemon;
	private int maxPP;
	private int currentPP;
	private int damage;
	private Estado aplicaEstado;
	private String categoria;
	//Getters
	public String getNombre() {
		return nombre;
	}

	public TipoPokemon getTipoPokemon() {
		return tipoPokemon;
	}

	public int getMaxPP() {
		return maxPP;
	}

	public int getCurrentPP() {
		return currentPP;
	}

	public int getDamage() {
		return damage;
	}

	public Estado getAplicaEstado() {
		return aplicaEstado;
	}

	public String getCategoria() {
		return categoria;
	}

	public Movimiento(String nombre, TipoPokemon tipoPokemon, int maxPP, int damage, Estado aplicaEstado,
			String categoria) {
		super();
		this.nombre = nombre;
		this.tipoPokemon = tipoPokemon;
		this.maxPP = maxPP;
		this.currentPP = maxPP;
		this.damage = damage;
		this.aplicaEstado = aplicaEstado;
		this.categoria = categoria;
	}
	/**
	 * Para no complicarme, solo he creado los tipos Planta, fuego, agua, normal y sus respectivas interacciones
	 * @param t Tipo del movimiento
	 * @return 1 o 2, según sea neutro o superefectivo (No he tenido en cuenta las resistencias 0.5 para no complicarlo tanto)
	 */
	public double getEfectividad(TipoPokemon t) {
		if (this.tipoPokemon.getNombre().equals("Planta") && t.getNombre().equals("Agua")) {
			return 2;
		} else if (this.tipoPokemon.getNombre().equals("Fuego") && t.getNombre().equals("Planta")) {
			return 2;
		} else if (this.tipoPokemon.getNombre().equals("Agua") && t.getNombre().equals("Fuego")) {
			return 2;
		} else {
			return 1;
		}
	}
	/**
	 * Método para reducir los PP
	 */
	public void reducePP() {
		this.currentPP--;
	}
	
}
