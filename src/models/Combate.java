package models;

public class Combate {
	// Propiedades
	@SuppressWarnings("unused")
	private Entrenador entrenador1;
	@SuppressWarnings("unused")
	private Entrenador entrenador2;
	private Pokemon pokemon1;
	private Pokemon pokemon2;
	private boolean finished;

	public Combate(Entrenador entrenador1, Entrenador entrenador2, Pokemon pokemon1, Pokemon pokemon2) {
		super();
		this.entrenador1 = entrenador1;
		this.entrenador2 = entrenador2;
		this.pokemon1 = pokemon1;
		this.pokemon2 = pokemon2;
		this.finished = false;
	}

	/**
	 * Método que determina quien empieza atacando en función de la velocidad de
	 * cada pokemon
	 * 
	 * @return pokemon que empieza
	 */
	public Pokemon quienEmpiezaTurno() {
		if (this.pokemon1.getSpeed() > this.pokemon2.getSpeed())
			return this.pokemon1;
		else if (this.pokemon2.getSpeed() > this.pokemon1.getSpeed())
			return this.pokemon2;
		else {
			int random = (int) (Math.random() * 2);
			if (random == 1)
				return this.pokemon1;
			else
				return this.pokemon2;
		}

	}

	/**
	 * Método donde se aplica la fórmula del daño, que se convierte directamente en
	 * la cantidad de vida que pierde el pokemon atacado
	 * 
	 * @param p1 pokemon atacante
	 * @param m  movimiento
	 * @param p2 pokemon atacado
	 */
	public void aplicarMovimiento(Pokemon p1, Movimiento m, Pokemon p2) {
		double ad = 1;
		// Probabilidad de crítico del 10%
		double critico = (Math.random() * 10);
		double stab = 1;
		// El pokemon pilla "stab" cuando el tipo del movimiento corresponde con el tipo
		// del pokemon
		if (p1.getTipo1().equals(m.getTipoPokemon()))
			stab = 1.5;
		if (critico == 2)
			critico = 2;
		else
			critico = 1;
		// Ad es un parámetro que cambia de valor en función de la categoría del ataque;
		// físico o especial (No he tenido en cuenta los ataques de estado), escalando
		// con las respectivas estadísticas del pokemon
		if (m.getCategoria().equals("Fisico"))
			ad = p1.getAttack() / p2.getDefense();
		else if (m.getCategoria().equals("Especial"))
			ad = p1.getSpecialAtk() / p2.getSpecialDef();
		//El tocho de la fórmula del daño
		int damage = (int) (((((((2 * p1.getLevel()) / 5) + 2) * m.getDamage() * ad) / 50) + 2) * critico * stab
				* m.getEfectividad(p2.getTipo1()));
		//Si la vida baja de 0, la pongo en 0
		p2.setCurrentHP(p2.getCurrentHP() - damage);
		if (p2.getCurrentHP() <= 0) {
			p2.setCurrentHP(0);
		}
		//Si la vida es 0, el combate acaba
		if (p2.getCurrentHP() == 0) {
			this.finished = true;
		}
		//Si das un crítico salta un mensaje
		if (critico == 2) {
			System.out.println("¡Golpe crítico!");
		}
	}

	public boolean isFinished() {
		return finished;
	}

}
