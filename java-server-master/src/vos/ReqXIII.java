package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ReqXIII extends Usuario{
	
	@JsonProperty(value="descripcion")
	private String descripcion;
	
	public ReqXIII(@JsonProperty(value="usuario") String usuario,
			@JsonProperty(value="contrasena")String contrasena,
                        @JsonProperty(value="idCliente") Long idUsuario,
                        @JsonProperty(value="nombre") String nombre,
                        @JsonProperty(value="descripcion") String descripcion) 
        {
		super(usuario, contrasena, nombre, idUsuario);
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
