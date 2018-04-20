package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Hostal extends Operador {
	
	@JsonProperty(value="horaAtencion")
	private String horaAtencion;
	
	@JsonProperty(value="ubicacion")
	private String ubicacion;
	
	@JsonProperty(value="descripcion")
	private String descripcion;

	public Hostal(@JsonProperty(value="usuario") String usuario,
			@JsonProperty(value="contrasena")String contrasena,
                        @JsonProperty(value="idHostal") Long idUsuario,
			@JsonProperty(value="horaAtencion")String horaAtencion,
                        @JsonProperty(value="nombre") String nombre,
			@JsonProperty(value="ubicacion")String ubicacion, 
                        @JsonProperty(value="descripcion")String descripcion)
	{
		super(usuario, contrasena, idUsuario, nombre);
		this.descripcion = descripcion;
		this.horaAtencion = horaAtencion;
		this.ubicacion = ubicacion;
	}
        
	public String getDescripcion() {
		return descripcion;
	}
        
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
        
	public String getHoraAtencion() {
		return horaAtencion;
	}

	public void setHoraAtencion(String horaAtencion) {
		this.horaAtencion = horaAtencion;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
}
