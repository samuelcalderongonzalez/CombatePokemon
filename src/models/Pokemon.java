package models;

import java.util.ArrayList;

public class Pokemon {
	//Propiedades
	private int numero;
	private String nombre;
	private TipoPokemon tipo1;
	private Estado estado;
	private double attack;
	private double defense;
	private double specialAtk;
	private double specialDef;
	private int speed;
	private int maxHP;
	private int currentHP;
	private int level;
	private ArrayList<Movimiento> movimientos;

	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
	}
	/**
	 * Método para añadir un movimiento
	 * @param m Movimiento a añadir
	 */
	public void addMove(Movimiento m) {
		this.movimientos.add(m);
	}



	public Pokemon(int numero, String nombre, TipoPokemon tipo1, Estado estado, int attack, int defense, int specialAtk,
			int specialDef, int speed, int maxHP, int level) {
		super();
		this.numero = numero;
		this.nombre = nombre;
		this.tipo1 = tipo1;
		this.estado = estado;
		this.attack = attack;
		this.defense = defense;
		this.specialAtk = specialAtk;
		this.specialDef = specialDef;
		this.speed = speed;
		this.maxHP = maxHP;
		this.currentHP = maxHP;
		this.movimientos = new ArrayList<Movimiento>();
		this.level = level;
	}
	//Getters
	public int getNumero() {
		return numero;
	}

	public String getNombre() {
		return nombre;
	}

	public TipoPokemon getTipo1() {
		return tipo1;
	}

	public Estado getEstado() {
		return estado;
	}

	public double getAttack() {
		return attack;
	}

	public double getDefense() {
		return defense;
	}

	public double getSpecialAtk() {
		return specialAtk;
	}

	public double getSpecialDef() {
		return specialDef;
	}

	public int getSpeed() {
		return speed;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public int getLevel() {
		return level;
	}
	/**
	 * Método para listar movimientos y sus PP
	 */
	public void getListaMovimientos() {
		int c = 1;
		for (Movimiento m : movimientos) {
			System.out.println(c + ". " + m.getNombre() + " " + m.getCurrentPP() + "/" + m.getMaxPP() + " PP");
			c++;
		}
	}
	/**
	 * Te devuelve un movimiento a partir de una id
	 * @param id en cuestión
	 * @return movimiento
	 */
	public Movimiento getMovimientoId(int id) {
		return movimientos.get(id - 1);
	}
}
