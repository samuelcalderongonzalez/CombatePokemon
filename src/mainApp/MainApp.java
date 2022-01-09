package mainApp;

import java.util.Scanner;

import models.Combate;
import models.Entrenador;
import models.Estado;
import models.Movimiento;
import models.Pokemon;
import models.TipoPokemon;

public class MainApp {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		// Creación de pokemons y movimientos por defecto
		// Bulbasaur
		Pokemon bulbasaur = new Pokemon(1, "Bulbasaur", new TipoPokemon("Planta"), new Estado("none"), 49, 49, 65, 65,
				45, 45, 50);
		Movimiento lcepa = new Movimiento("Látigo Cepa", new TipoPokemon("Planta"), 40, 45, new Estado("none"),
				"Fisico");
		Movimiento placaje = new Movimiento("Placaje", new TipoPokemon("Normal"), 30, 35, new Estado("none"), "Fisico");
		bulbasaur.addMove(lcepa);
		bulbasaur.addMove(placaje);
		// Charmander
		Pokemon charmander = new Pokemon(2, "Charmander", new TipoPokemon("Fuego"), new Estado("none"), 52, 43, 60, 50,
				65, 39, 50);
		Movimiento ascuas = new Movimiento("Ascuas", new TipoPokemon("Fuego"), 40, 40, new Estado("none"), "Especial");
		charmander.addMove(ascuas);
		charmander.addMove(placaje);
		// Squirtle
		Pokemon squirtle = new Pokemon(3, "Squirtle", new TipoPokemon("Agua"), new Estado("none"), 48, 65, 50, 64, 43,
				44, 50);
		Movimiento pagua = new Movimiento("Pistola Agua", new TipoPokemon("Agua"), 40, 40, new Estado("none"),
				"Especial");
		squirtle.addMove(pagua);
		squirtle.addMove(placaje);
		// Pequeño menu
		System.out.println("¿Cómo se llama?");
		String trainerName = sc.nextLine();
		Entrenador entrenador1 = new Entrenador(trainerName);
		System.out.println("Bienvenido, " + entrenador1.getNombre()
				+ ". Elija un pokemon entre estos tres:\n1. Bulbasaur   2. Charmander   3. Squirtle");
		int opc = Integer.parseInt(sc.nextLine());
		switch (opc) {
		case 1:
			entrenador1.getEquipo().addPokemon(bulbasaur);
			break;
		case 2:
			entrenador1.getEquipo().addPokemon(charmander);
			break;
		case 3:
			entrenador1.getEquipo().addPokemon(squirtle);
			break;
		default:
			System.out.println("No se ha reconocido la opción");
			break;
		}
		System.out.println("¡Empieza el combate!");
		// Tu rival coge el pokemon en desventaja frente al tuyo
		Entrenador cpu;
		if (opc == 1) {
			cpu = new Entrenador("Pescador Pedro");
			cpu.getEquipo().addPokemon(squirtle);
		} else if (opc == 2) {
			cpu = new Entrenador("Caza Bichos Carmen");
			cpu.getEquipo().addPokemon(bulbasaur);
		} else if (opc == 3) {
			cpu = new Entrenador("Motorista Kevin");
			cpu.getEquipo().addPokemon(charmander);
		} else {
			cpu = new Entrenador("Motorista Kevin");
		}
		// Empieza el combate
		Combate combate = new Combate(entrenador1, cpu, entrenador1.getEquipo().getFirstPokemon(),
				cpu.getEquipo().getFirstPokemon());
		// Función explicada abajo
		uiCombate(entrenador1, cpu);
		// Guardo tanto el movimiento elegido por la persona como por la máquina
		do {
			Movimiento mov = null;
			try {
				mov = elegirMov(entrenador1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Movimiento cpuMov = null;
			try {
				cpuMov = elegirMovCPU(cpu);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// Quien empieza según la velocidad
			//Si el pokemon más rápido es igual al pokemon de la cpu, empieza cpu
			if (combate.quienEmpiezaTurno().equals(cpu.getEquipo().getFirstPokemon())) {
				combate.aplicarMovimiento(cpu.getEquipo().getFirstPokemon(), cpuMov,
						entrenador1.getEquipo().getFirstPokemon());
				System.out.println(cpu.getEquipo().getFirstPokemon().getNombre() + " ha usado " + cpuMov.getNombre());
				// Muestro la ui después de cada ataque
				uiCombate(entrenador1, cpu);
				if (!combate.isFinished()) {
					combate.aplicarMovimiento(entrenador1.getEquipo().getFirstPokemon(), mov,
							cpu.getEquipo().getFirstPokemon());
					System.out.println(
							entrenador1.getEquipo().getFirstPokemon().getNombre() + " ha usado " + mov.getNombre());
					uiCombate(entrenador1, cpu);
				}
			//Si el pokemon más rapido es igual a mi pokemon, empieza mi pokemon
			} else if (combate.quienEmpiezaTurno().equals(entrenador1.getEquipo().getFirstPokemon())) {
				combate.aplicarMovimiento(entrenador1.getEquipo().getFirstPokemon(), mov,
						cpu.getEquipo().getFirstPokemon());
				System.out.println(
						entrenador1.getEquipo().getFirstPokemon().getNombre() + " ha usado " + mov.getNombre());
				uiCombate(entrenador1, cpu);
				if (!combate.isFinished()) {
					combate.aplicarMovimiento(cpu.getEquipo().getFirstPokemon(), cpuMov,
							entrenador1.getEquipo().getFirstPokemon());
					System.out
							.println(cpu.getEquipo().getFirstPokemon().getNombre() + " ha usado " + cpuMov.getNombre());
					uiCombate(entrenador1, cpu);
				}
			}
			// Todo esto se repite mientras no haya terminado el combate
			// El combate termina cuando la vida de alguno de los dos pokemons alcanza 0
		} while (!combate.isFinished());
		//Mensaje típico de victoria/derrota
		if (entrenador1.getEquipo().getFirstPokemon().getCurrentHP() == 0) {
			System.out.println("Has perdido... Le das 1080 Pokemonedas a " + cpu.getNombre());
		} else {
			System.out.println("¡Has ganado! Recibes 9999 Pokemonedas de " + cpu.getNombre());
		}

	}

	/**
	 * Función que provee una interfaz con datos sobre el nombre, nivel, y vida
	 * máxima y actual de ambos pokemons
	 * 
	 * @param e1  entrenador 1
	 * @param cpu entrenador 2, en este caso una ia
	 */
	public static void uiCombate(Entrenador e1, Entrenador cpu) {
		System.out.println(e1.getEquipo().getFirstPokemon().getNombre() + " LvL: "
				+ e1.getEquipo().getFirstPokemon().getLevel() + "\n" + e1.getEquipo().getFirstPokemon().getCurrentHP()
				+ "/" + e1.getEquipo().getFirstPokemon().getMaxHP() + " HP\n");
		System.out.println(cpu.getEquipo().getFirstPokemon().getNombre() + " LvL: "
				+ cpu.getEquipo().getFirstPokemon().getLevel() + "\n" + cpu.getEquipo().getFirstPokemon().getCurrentHP()
				+ "/" + cpu.getEquipo().getFirstPokemon().getMaxHP() + " HP\n");
	}

	/**
	 * Menú para elegir movimiento, devuelve un movimiento
	 * 
	 * @param e1 entrenador 1 (tú)
	 * @return movimiento elegido
	 * @throws Exception Lanza una excepción en caso de que no queden PP
	 */
	public static Movimiento elegirMov(Entrenador e1) throws Exception {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("¿Qué desea utilizar?");
		e1.getEquipo().getFirstPokemon().getListaMovimientos();
		int opc = Integer.parseInt(sc.nextLine());
		if (e1.getEquipo().getFirstPokemon().getMovimientoId(opc).getCurrentPP() > 0) {
			e1.getEquipo().getFirstPokemon().getMovimientoId(opc).reducePP();
			return e1.getEquipo().getFirstPokemon().getMovimientoId(opc);
		} else {
			throw new Exception("No te quedan PP...");
		}
	}

	/**
	 * Menu para elegir movimiento de la cpu. Elige un movimiento aleatorio
	 * 
	 * @param e1 cpu
	 * @return movimiento aleatorio
	 * @throws Exception Lanza una excepción en caso de que no queden PP
	 */
	public static Movimiento elegirMovCPU(Entrenador e1) throws Exception {
		int random = (int) ((Math.random() * 2) + 1);
		if (e1.getEquipo().getFirstPokemon().getMovimientoId(random).getCurrentPP() > 0) {
			e1.getEquipo().getFirstPokemon().getMovimientoId(random).reducePP();
			return e1.getEquipo().getFirstPokemon().getMovimientoId(random);
		} else {
			throw new Exception();
		}
	}

}
