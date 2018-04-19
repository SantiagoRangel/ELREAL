package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ViviendaUniversitaria extends Operador {
    
	@JsonProperty(value="horaAtencion")
	private String horaAtencion;
	
	@JsonProperty(value="descripcion")
	private String descripcion;

	public ViviendaUniversitaria(@JsonProperty(value="usuario") String usuario,
			@JsonProperty(value="contrasena")String contrasena,
                        @JsonProperty(value="idUsuario") Long idUsuario
			,@JsonProperty(value="horaAtencion")String horaAtencion,
                        @JsonProperty(value="nombre") String nombre,
			 @JsonProperty(value="descripcion")String descripcion)
	{
		super(usuario, contrasena, idUsuario, nombre);
		this.descripcion = descripcion;
		this.horaAtencion = horaAtencion;
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
}
