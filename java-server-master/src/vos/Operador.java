package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Operador extends Usuario {

	@JsonProperty(value="nombre")
	private String nombre;
	
	public Operador(@JsonProperty(value="usuario") String usuario,
			@JsonProperty(value="contrasena")String contrasena,
                        @JsonProperty(value="idOperador") Long idOperador,
                        @JsonProperty(value="nombre") String nombre) 
        {
		super(usuario, contrasena, idOperador);
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
        
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
