package unlam.dominio;

import java.util.Objects;

public class Partido {
	private Integer idPartido;
	private Equipo local;
	private Equipo visitante;
	
	public Partido(Integer idPartido, Equipo local, Equipo visitante) {
		super();
		this.idPartido = idPartido;
		this.local = local;
		this.visitante = visitante;
	}
	public Integer getIdPartido() {
		return idPartido;
	}
	public void setIdPartido(Integer idPartido) {
		this.idPartido = idPartido;
	}
	public Equipo getLocal() {
		return local;
	}
	public void setLocal(Equipo local) {
		this.local = local;
	}
	public Equipo getVisitante() {
		return visitante;
	}
	public void setVisitante(Equipo visitante) {
		this.visitante = visitante;
	}
	@Override
	public int hashCode() {
		return Objects.hash(local, visitante);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partido other = (Partido) obj;
		return Objects.equals(local, other.local) && Objects.equals(visitante, other.visitante);
	}
	
	
	   	
}
