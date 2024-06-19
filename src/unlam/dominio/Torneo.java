package unlam.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Torneo {
	private String nombre;
	private Set<Equipo> equipoTorneo;
	private Map<String, List<Equipo>> grupo;
	private Set<Partido> partido;
	private List<Resultado> resultado;
	private Set<Equipo> equipoEliminatorias;
	private static int contador = 1;

	public Torneo(String nombre) {
		super();
		this.nombre = nombre;
		this.equipoTorneo = new HashSet<>();
		this.grupo = new TreeMap<>(new GrupoNombres());
		this.partido = new HashSet<>();
		this.resultado = new ArrayList<>();
		this.equipoEliminatorias = new HashSet<>();
		// inicializarEquipos();

	}

	public Boolean crearTorneo(Equipo uno, Equipo dos, Equipo tres, Equipo cuatro) {
		this.equipoTorneo.add(uno);
		this.equipoTorneo.add(dos);
		this.equipoTorneo.add(tres);
		this.equipoTorneo.add(cuatro);
		List<Equipo> listaEquipos = new ArrayList<>();

		listaEquipos.add(uno);
		listaEquipos.add(dos);
		listaEquipos.add(tres);
		listaEquipos.add(cuatro);
		int numGrupo = this.grupo.size() + 1;
		if (listaEquipos.size() == 4) {
			String group = "Grupo" + numGrupo;
			this.grupo.put(group, listaEquipos);
			listaEquipos = null;
			

		}

		return false;
	}

//
//	public Boolean crearTorneo() {
//
//		List<Equipo> listaEquipos = new ArrayList<>(this.equipoTorneo);
//		List<Equipo> equipos = null;
//
//		Integer contadorGrupo = 1;
//		
//
//		for (Equipo e : listaEquipos) {
//
//			if (equipos == null) {
//				equipos = new ArrayList<>();
//			}
//
//			equipos.add(e);
//
//			if (equipos.size() == 4) {
//				String group = "Grupo" + contadorGrupo;
//				this.grupo.put(group, equipos);
//				equipos = null;
//				contadorGrupo++;
//
//			}
//
//		}
//
//		return false;
//	}

	public Boolean registrarPartido(Integer idPartido, Equipo local, Equipo visitante)
			throws EquipoDuplicadoException, PartidoJugadoException {
		// Verificar si los equipos son iguales
		if (local.equals(visitante)) {
			throw new EquipoDuplicadoException("El equipo local y el visitante son el mismo.");
		}

		Partido partido = new Partido(idPartido, local, visitante);

		// Verificar si el partido ya ha sido jugado
		if (verificarQueElPartidoNoseHayaJugado(partido)) {
			throw new PartidoJugadoException("El partido ya ha sido jugado.");
		}

		// Verificar si los equipos son del mismo grupo
		boolean mismosGrupo = verificarQueLosEquiposSeanDelMismoGrupo(local, visitante);

		// Verificar si los equipos son de eliminatorias
		boolean deEliminatorias = verificarQueLosEquiposSeanDeEliminatorias(local, visitante);

		// Agregar el partido si es válido
		if (mismosGrupo || deEliminatorias) {
			return this.partido.add(partido);
		}

		return false;
	}

	public void genererEliminatoria() {

		for (Map.Entry<String, List<Equipo>> entry : grupo.entrySet()) {
			List<Equipo> equipos = entry.getValue();

		
			Collections.sort(equipos);

			this.equipoEliminatorias.add(equipos.get(0));
			this.equipoEliminatorias.add(equipos.get(1));
		}

	}

	private boolean verificarQueElPartidoNoseHayaJugado(Partido partido) {
		// TODO Auto-generated method stub
		return this.partido.contains(partido);
	}

	private Boolean verificarQueLosEquiposSeanDelMismoGrupo(Equipo local, Equipo visitante) {
		// TODO Auto-generated method stub
		for (Map.Entry<String, List<Equipo>> entry : grupo.entrySet()) {
			List<Equipo> equipos = entry.getValue();
			if (equipos.contains(local) && equipos.contains(visitante)
					&& !(this.equipoEliminatorias.contains(local) && this.equipoEliminatorias.contains(visitante))) {
				return true;
			}
		}
		return false;

	}

	private Boolean verificarQueLosEquiposSeanDeEliminatorias(Equipo local, Equipo visitante) {
		// TODO Auto-generated method stub
		if (this.equipoEliminatorias.contains(local) && this.equipoEliminatorias.contains(visitante)) {
			return true;
		}

		return false;

	}

	public Boolean registrarResultadoGrupo(Integer id, Integer golesLocal, Integer golesVisitante) {

		Resultado resultado = new Resultado(id, golesLocal, golesVisitante);
		Boolean seRegistro = this.resultado.add(resultado);
		registrarPuntos(id, golesLocal, golesVisitante);
		return seRegistro;

	}

	public Boolean registrarResultadoEliminatoria(Integer id, Integer golesLocal, Integer golesVisitante,
			Integer penalLocal, Integer penalVisitante) {

		ResultadoEliminatoria resultado = new ResultadoEliminatoria(id, golesLocal, golesVisitante, penalLocal,
				penalVisitante);

		Boolean seRegistro = this.resultado.add(resultado);

		return seRegistro;

	}

	public Equipo obtenerEquipo(String nombre) {

		for (Equipo e : this.equipoTorneo) {

			if (e.getNombre().equals(nombre)) {
				return e;
			}
		}

		return null;
	}

	public ResultadoEliminatoria buscarResultadoEliminatoria(Integer id) {

		for (Resultado r : this.resultado) {
			if (r.getIdPartido().equals(id) && r instanceof ResultadoEliminatoria) {
				return (ResultadoEliminatoria) r;
			}
		}
		return null;
	}

	public Set<Equipo> consultrarGrupo(String grupo) {
		
		Set<Equipo> equipo = new TreeSet<>();
		equipo.addAll(this.grupo.get(grupo));
		return equipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Equipo> getEquipoTorneo() {
		return equipoTorneo;
	}

	public void setEquipoTorneo(Set<Equipo> equipoTorneo) {
		this.equipoTorneo = equipoTorneo;
	}

	public Map<String, List<Equipo>> getGrupo() {
		return grupo;
	}

	public void setGrupo(Map<String, List<Equipo>> grupo) {
		this.grupo = grupo;
	}

	public Set<Partido> getPartido() {
		return partido;
	}

	public void setPartido(Set<Partido> partido) {
		this.partido = partido;
	}

	public List<Resultado> getResultado() {
		return resultado;
	}

	public void setResultado(List<Resultado> resultado) {
		this.resultado = resultado;
	}

	public Set<Equipo> getEquipoEliminatorias() {
		return equipoEliminatorias;
	}

	public void setEquipoEliminatorias(Set<Equipo> equipoEliminatorias) {
		this.equipoEliminatorias = equipoEliminatorias;
	}

	private void registrarPuntos(Integer id, Integer golesLocal, Integer golesVisitante) {
		// TODO Auto-generated method stub

		for (Partido e : this.partido) {

			if (e.getIdPartido().equals(id)) {

				ResultadoFinal resultado = obtenerResultado(id);

				if (resultado == ResultadoFinal.GANA_LOCAL) {

					e.getLocal().golesAfavor(golesLocal);
					e.getLocal().golesContra(golesVisitante);
					e.getLocal().puntosObtenidos(3);

					e.getVisitante().golesAfavor(golesVisitante);
					e.getVisitante().golesContra(golesLocal);
					e.getVisitante().puntosObtenidos(0);
				} else if (resultado == ResultadoFinal.GANA_VISITANTE) {
					e.getVisitante().golesAfavor(golesVisitante);
					e.getVisitante().golesContra(golesLocal);
					e.getVisitante().puntosObtenidos(3);

					e.getLocal().golesAfavor(golesLocal);
					e.getLocal().golesContra(golesVisitante);
					e.getLocal().puntosObtenidos(0);
				} else {

					e.getVisitante().golesAfavor(golesVisitante);
					e.getVisitante().golesContra(golesLocal);
					e.getVisitante().puntosObtenidos(1);

					e.getLocal().golesAfavor(golesLocal);
					e.getLocal().golesContra(golesVisitante);
					e.getLocal().puntosObtenidos(1);
				}

			}

		}

	}

	public ResultadoFinal obtenerResultado(Integer idPartdio) {

		for (Resultado e : this.resultado) {
			if (e.getIdPartido().equals(idPartdio)) {

				return e.resultado();
			}
		}
		return null;
	}

	private void inicializarEquipos() {

		String[] selecciones = { "Catar", "Ecuador", "Senegal", "Países Bajos", "Inglaterra", "Irán", "Estados Unidos",
				"Gales", "Argentina", "Arabia Saudita", "México", "Polonia", "Francia", "Australia", "Dinamarca",
				"Túnez", "España", "Costa Rica", "Alemania", "Japón", "Bélgica", "Canadá", "Marruecos", "Croacia",
				"Brasil", "Serbia", "Suiza", "Camerún", "Portugal", "Ghana", "Uruguay", "Corea del Sur" };

		for (int i = 0; i < selecciones.length; i++) {

			Equipo equipo = new Equipo(selecciones[i]);

			this.equipoTorneo.add(equipo);

		}
	}

}
