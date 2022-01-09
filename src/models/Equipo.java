package models;

import java.util.ArrayList;

public class Equipo {
	private ArrayList<Pokemon> equipo;

	public Equipo() {
		this.equipo = new ArrayList<Pokemon>();
	}
	/**
	 * No lo llego a usar, pero mostrar�a el equipo
	 */
	public void getEquipo() {
		int c = 0;
		for (Pokemon p : equipo) {
			System.out.println(c + ". " + p);
			c++;
		}
	}
	/**
	 * M�todo para a�adir un pokemon al equipo
	 * @param p Pokemon a a�adir
	 */
	public void addPokemon(Pokemon p) {
		this.equipo.add(p);
	}
	/**
	 * M�todo que me devuelve el primer pokemon del equipo
	 * @return primer pokemons
	 */
	public Pokemon getFirstPokemon() {
		return this.equipo.get(0);
	}
}
