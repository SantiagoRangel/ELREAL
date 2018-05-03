package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ReqX {    
    
	@JsonProperty(value="monto")
	private Double monto;
	
	@JsonProperty(value="nights")
	private Integer nights;
	
	@JsonProperty(value="habitaciones")
	private Integer habitaciones;
	
	@JsonProperty(value="apartamentos")
	private Integer apartamentos;
	
	@JsonProperty(value="viviendas")
	private Integer viviendas;
	
	@JsonProperty(value="usuario")
	private String usuario;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	public ReqX(@JsonProperty(value="monto") Double monto,
                @JsonProperty(value="nights") Integer nights,
                @JsonProperty(value="habitaciones") Integer habitaciones,
                @JsonProperty(value="apartamentos") Integer apartamentos,
                @JsonProperty(value="viviendas") Integer viviendas,
                @JsonProperty(value="usuario") String usuario,
                @JsonProperty(value="nombre") String nombre)
        {
		this.monto = monto;
		this.nights = nights;
		this.habitaciones = habitaciones;
		this.apartamentos = apartamentos;
		this.viviendas = viviendas;
		this.usuario = usuario;
		this.nombre = nombre;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Integer getNights() {
		return nights;
	}

	public void setNights(Integer nights) {
		this.nights = nights;
	}

	public Integer getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(Integer habitaciones) {
		this.habitaciones = habitaciones;
	}

	public Integer getApartamentos() {
		return apartamentos;
	}

	public void setApartamentos(Integer apartamentos) {
		this.apartamentos = apartamentos;
	}

	public Integer getViviendas() {
		return viviendas;
	}

	public void setViviendas(Integer viviendas) {
		this.viviendas = viviendas;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
