package unlam.dominio;

public class Resultado {

	private Integer idPartido;
	private Integer golesLocal;
	private Integer golesVisitante;

	public Resultado(Integer idPartido, Integer golesLocal, Integer golesvisitante) {
		super();
		this.idPartido = idPartido;
		this.golesLocal = golesLocal;
		this.golesVisitante = golesvisitante;
	}

	public Integer getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(Integer idPartido) {
		this.idPartido = idPartido;
	}

	public Integer getGolesLocal() {
		return golesLocal;
	}

	public void setGolesLocal(Integer golesLocal) {
		this.golesLocal = golesLocal;
	}

	public Integer getGolesVisitante() {
		return golesVisitante;
	}

	public void setGolesVisitante(Integer golesVisitante) {
		this.golesVisitante = golesVisitante;
	}

	public ResultadoFinal resultado() {
		if (this.golesLocal > this.golesVisitante) {
			return ResultadoFinal.GANA_LOCAL;
		} else if (this.golesVisitante > this.golesLocal) {
			return ResultadoFinal.GANA_VISITANTE;
		} else {
			return ResultadoFinal.EMPATE;
		}
	}

}
