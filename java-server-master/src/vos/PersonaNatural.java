package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class PersonaNatural extends Operador {
	
	@JsonProperty(value="uniandino")
	private Long uniandino;	
	
	public PersonaNatural(@JsonProperty(value="usuario") String usuario,
			@JsonProperty(value="contrasena")String contrasena,
                        @JsonProperty(value="idUsuario") Long idUsuario,
                        @JsonProperty(value="nombre")String nombre,
			@JsonProperty(value="uniandino") Long uniandino) 
        {
		super(usuario, contrasena, idUsuario, nombre);
		this.uniandino = uniandino;
	}

	public Long getUniandino() {
		return uniandino;
	}

	public void setUniandino(long uniandino) {
		this.uniandino = uniandino;
	}	
}
