package unlam.dominio;

import java.util.Objects;

public class Equipo implements Comparable<Equipo>{
	 
	private String nombre;
	private Integer puntos = 0;
	private Integer golesContra = 0;;
	private Integer golesFavor = 0;
	
	public Equipo(String nombre) {
		super();
		this.nombre = nombre;
		
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getPuntos() {
		return puntos;
	}
	public void puntosObtenidos(Integer puntos) {
		this.puntos += puntos;
	}
	
	public void golesAfavor(Integer goles) {
		this.golesFavor += goles;
	}
	
	public void golesContra(Integer goles) {
		this.golesContra += goles;
	}
	
	public Integer getGolesContra() {
		return golesContra;
	}
	
	
	public Integer getGolesFavor() {
		return golesFavor;
	}
	public void setGolesContra(Integer golesContra) {
		this.golesContra = golesContra;
	}
	public void setGolesFavor(Integer golesFavor) {
		this.golesFavor = golesFavor;
	}
	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}
	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipo other = (Equipo) obj;
		return Objects.equals(nombre, other.nombre);
	}
	@Override
	public String toString() {
		return "Equipo [nombre=" + nombre + ", puntos=" + puntos + "]";
	}
	@Override
	public int compareTo(Equipo otro) {
		if (this.puntos != otro.puntos) {
            return Integer.compare(otro.puntos, this.puntos); // Orden descendente por puntos
        } else if (this.golesFavor != otro.golesFavor) {
            return Integer.compare(otro.golesFavor, this.golesFavor); // Orden descendente por goles a favor
        } else if(this.golesContra != otro.golesContra){
            return Integer.compare(this.golesContra, otro.golesContra); // Orden ascendente por goles en contra
        }else {
         return this.nombre.compareTo(otro.nombre);
        }
		
		
	}
	
	
	
	
}
