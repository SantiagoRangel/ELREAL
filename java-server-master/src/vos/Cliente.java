package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Cliente extends Usuario{
	
	public Cliente(@JsonProperty(value="usuario") String usuario,
			@JsonProperty(value="contrasena")String contrasena,
                        @JsonProperty(value="idCliente") Long idUsuario,
                        @JsonProperty(value="nombre") String nombre) 
        {
		super(usuario, contrasena, nombre, idUsuario);
	}
}
