package test.unlam;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import unlam.dominio.Equipo;
import unlam.dominio.EquipoDuplicadoException;
import unlam.dominio.PartidoJugadoException;
import unlam.dominio.ResultadoEliminatoria;
import unlam.dominio.ResultadoFinal;
import unlam.dominio.Torneo;

public class MundialTest {

	@Test
	public void queSePuedaCrearUnPartidoDeGruposConDosEquiposDelMismoGrupo()
			throws EquipoDuplicadoException, PartidoJugadoException {
		Torneo torneo = new Torneo("aa");

		Equipo uno = new Equipo("Catar");
		Equipo dos = new Equipo("Ecuador");
		Equipo tres = new Equipo("Argentina");
		Equipo cuatro = new Equipo("Francia");

		torneo.crearTorneo(uno, dos, tres, cuatro);
		Equipo local = torneo.obtenerEquipo("Catar");
		Equipo Visitante = torneo.obtenerEquipo("Ecuador");

		uno = new Equipo("Brasil");
		dos = new Equipo("Alemania");
		tres = new Equipo("Suecia");
		cuatro = new Equipo("Inglaterra");

		torneo.crearTorneo(uno, dos, tres, cuatro);

		Boolean partido = torneo.registrarPartido(12, local, Visitante);

		assertTrue(partido);
	}

	@Test
	public void queSePuedaCrearUnPartidoDeEliminatoriasConDosEquiposPertenecientesALaListaDeEquiposEnEliminatorias()
			throws EquipoDuplicadoException, PartidoJugadoException {

		Torneo torneo = new Torneo("aa");

		Equipo uno = new Equipo("Catar");
		Equipo dos = new Equipo("Ecuador");
		Equipo tres = new Equipo("Argentina");
		Equipo cuatro = new Equipo("Francia");

		torneo.crearTorneo(uno, dos, tres, cuatro);
		Equipo local = torneo.obtenerEquipo("Catar");
		Equipo Visitante = torneo.obtenerEquipo("Ecuador");

		Equipo local2 = torneo.obtenerEquipo("Catar");
		Equipo Visitant2 = torneo.obtenerEquipo("Argentina");

		Boolean partido = torneo.registrarPartido(12, uno, dos);
		Boolean partido2 = torneo.registrarPartido(22, uno, tres);

		torneo.registrarPartido(14, tres, cuatro);
		torneo.registrarPartido(11, uno, cuatro);

		torneo.registrarResultadoGrupo(12, 2, 4);
		torneo.registrarResultadoGrupo(22, 1, 2);
		torneo.registrarResultadoGrupo(11, 2, 3);

		torneo.genererEliminatoria();

		Boolean a = torneo.registrarPartido(43, dos, cuatro);

		assertTrue(a);

	}

	@Test(expected = PartidoJugadoException.class)
	public void queAlCrearUnPartidoDondeLosEquiposYaJugaronSeLanceUnaPartidoJugadoException()
			throws EquipoDuplicadoException, PartidoJugadoException {

		Torneo torneo = new Torneo("aa");

		Equipo uno = new Equipo("Catar");
		Equipo dos = new Equipo("Ecuador");
		Equipo tres = new Equipo("Argentina");
		Equipo cuatro = new Equipo("Francia");

		torneo.crearTorneo(uno, dos, tres, cuatro);

		torneo.registrarPartido(12, uno, dos);
		torneo.registrarPartido(22, uno, dos);

	}

	@Test(expected = EquipoDuplicadoException.class)
	public void queAlCrearUnPartidoDeGruposDondeElEquipoLocalEsElMismoQueElEquipoRivalSeLanceUnaEquipoDuplicadoException()
			throws EquipoDuplicadoException, PartidoJugadoException {
		Torneo torneo = new Torneo("aa");

		Equipo uno = new Equipo("Catar");
		Equipo dos = new Equipo("Ecuador");
		Equipo tres = new Equipo("Argentina");
		Equipo cuatro = new Equipo("Francia");

		torneo.crearTorneo(uno, dos, tres, cuatro);

		torneo.registrarPartido(12, uno, uno);
	}

	@Test
	public void queAlRegistrarElResultadoDeUnPartidoDeGruposSeAcumulenLosPuntosCorrespondientesACadaEquipo()
			throws EquipoDuplicadoException, PartidoJugadoException {

		Torneo torneo = new Torneo("aa");

		Equipo uno = new Equipo("Catar");
		Equipo dos = new Equipo("Ecuador");
		Equipo tres = new Equipo("Argentina");
		Equipo cuatro = new Equipo("Francia");

		torneo.crearTorneo(uno, dos, tres, cuatro);

		torneo.registrarPartido(12, uno, dos);
		torneo.registrarPartido(22, dos, tres);
		torneo.registrarPartido(21, dos, cuatro);

		torneo.registrarResultadoGrupo(12, 3, 2);
		torneo.registrarResultadoGrupo(22, 5, 1);
		torneo.registrarResultadoGrupo(21, 1, 1);

		Integer puntosEquipoUno = uno.getPuntos();
		Integer puntosEquipoDos = dos.getPuntos();

		Integer puntosEsperado = 3;

		assertEquals(puntosEsperado, puntosEquipoUno);

		puntosEsperado = 4;

		assertEquals(puntosEsperado, puntosEquipoDos);

	}

	@Test
	public void queAlObtenerElResultadoDeUnPartidoDeGruposSeaElElementoEmpateDelEnum()
			throws EquipoDuplicadoException, PartidoJugadoException {

		Torneo torneo = new Torneo("aa");

		Equipo uno = new Equipo("Catar");
		Equipo dos = new Equipo("Ecuador");
		Equipo tres = new Equipo("Argentina");
		Equipo cuatro = new Equipo("Francia");

		torneo.crearTorneo(uno, dos, tres, cuatro);

		torneo.registrarPartido(12, uno, dos);

		torneo.registrarResultadoGrupo(12, 2, 2);

		ResultadoFinal resultado = torneo.obtenerResultado(12);

		ResultadoFinal esperado = ResultadoFinal.EMPATE;

		assertEquals(esperado, resultado);

	}

	@Test
	public void queAlObtenerElResultadoDeUnPartidoDeEliminatoriasEnCasoDeEmpateSeObtengaElEnumDelGanadorPorPenales()
			throws EquipoDuplicadoException, PartidoJugadoException {
		Torneo torneo = new Torneo("aa");

		Equipo uno = new Equipo("Catar");
		Equipo dos = new Equipo("Ecuador");
		Equipo tres = new Equipo("Argentina");
		Equipo cuatro = new Equipo("Francia");

		torneo.crearTorneo(uno, dos, tres, cuatro);

		torneo.registrarPartido(12, uno, dos);
		torneo.registrarPartido(22, uno, tres);

		torneo.registrarPartido(14, tres, cuatro);
		torneo.registrarPartido(11, uno, cuatro);

		torneo.registrarResultadoGrupo(12, 2, 4);
		torneo.registrarResultadoGrupo(22, 1, 2);
		torneo.registrarResultadoGrupo(11, 2, 3);

		torneo.genererEliminatoria();

		torneo.registrarPartido(43, dos, cuatro);

		torneo.registrarResultadoEliminatoria(43, 2, 2, 4, 5);

		ResultadoEliminatoria resultadoPartido = torneo.buscarResultadoEliminatoria(43);

		ResultadoFinal resultado = resultadoPartido.resultado();
		ResultadoFinal esperado = ResultadoFinal.GANA_VISITANTE_PENALES;

		assertEquals(esperado, resultado);

	}

	@Test
	public void queAlConsultarPuntajeDeEquiposDeLosGrupoSeObtenganLosEquiposOrdenadosPorGrupoAscendenteYPorPuntosDescendentemente()
			throws EquipoDuplicadoException, PartidoJugadoException {

		Torneo torneo = new Torneo("aa");

		Equipo uno = new Equipo("Catar");
		Equipo dos = new Equipo("Ecuador");
		Equipo tres = new Equipo("Argentina");
		Equipo cuatro = new Equipo("Francia");

		torneo.crearTorneo(uno, dos, tres, cuatro);

		Equipo grupo1 = new Equipo("Brasil");
		Equipo grupo2 = new Equipo("Alemania");
		Equipo grupo3 = new Equipo("Suecia");
		Equipo grupo4 = new Equipo("Inglaterra");

		torneo.crearTorneo(grupo1, grupo2, grupo3, grupo4);

		Equipo grupoQ = new Equipo("España");
		Equipo grupoW = new Equipo("Croaca");
		Equipo grupoE = new Equipo("Suiza");
		Equipo grupoR = new Equipo("Belgica");

		torneo.crearTorneo(grupoQ, grupoW, grupoE, grupoR);

		torneo.registrarPartido(10, uno, dos);
		torneo.registrarPartido(11, cuatro, tres);

		torneo.registrarResultadoGrupo(10, 2, 3);
		torneo.registrarResultadoGrupo(11, 4, 1);

		torneo.registrarPartido(12, grupo1, grupo2);
		torneo.registrarPartido(13, grupo3, grupo4);

		torneo.registrarResultadoGrupo(12, 4, 3);
		torneo.registrarResultadoGrupo(13, 2, 1);

		Set<String> keys = torneo.getGrupo().keySet();
	
		
		Set<String> esperado = new TreeSet<>();
		esperado.add("Grupo1");
		esperado.add("Grupo2");
		esperado.add("Grupo3");
		
		
	

		Set<Equipo> hash = new TreeSet<>(torneo.consultrarGrupo("Grupo1"));

		Set<Equipo> esperadoDos = new TreeSet<>();
		//(cuatro, dos, uno, tres);
		esperadoDos.add(cuatro);
		esperadoDos.add(dos);
		esperadoDos.add(uno);
		esperadoDos.add(tres);
	
		assertEquals(esperadoDos, hash);
	}


}
