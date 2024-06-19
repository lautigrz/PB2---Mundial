package unlam.dominio;

public class ResultadoEliminatoria extends Resultado {

	private Integer penalesConvertidosLocal;
	private Integer penalesConvertidosVisitante;

	public ResultadoEliminatoria(Integer idPartido, Integer golesLocal, Integer golesvisitante,
			Integer penalesConvertidosLocal, Integer penalesConvertidosVisitante) {
		super(idPartido, golesLocal, golesvisitante);
		// TODO Auto-generated constructor stub
		this.penalesConvertidosLocal = penalesConvertidosLocal;
		this.penalesConvertidosVisitante = penalesConvertidosVisitante;
	}

	public Integer getPenalesConvertidosLocal() {
		return penalesConvertidosLocal;
	}

	public void setPenalesConvertidosLocal(Integer penalesConvertidosLocal) {
		this.penalesConvertidosLocal = penalesConvertidosLocal;
	}

	public Integer getPenalesConvertidosVisitante() {
		return penalesConvertidosVisitante;
	}

	public void setPenalesConvertidosVisitante(Integer penalesConvertidosVisitante) {
		this.penalesConvertidosVisitante = penalesConvertidosVisitante;
	}

	@Override
	public ResultadoFinal resultado() {
		if (super.getGolesLocal() == super.getGolesVisitante()) {

			if (this.penalesConvertidosLocal > this.penalesConvertidosVisitante) {

				return ResultadoFinal.GANA_LOCAL_PENALES;

			} else if (this.penalesConvertidosVisitante > this.penalesConvertidosLocal) {
				return ResultadoFinal.GANA_VISITANTE_PENALES;
			}
		}
		return null;
	}

}
