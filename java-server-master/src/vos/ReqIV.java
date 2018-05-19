package vos;


import org.codehaus.jackson.annotate.JsonProperty;

public class ReqIV {    
    
	@JsonProperty(value="habitaciones")
	private Long habitaciones;
	
	@JsonProperty(value="apartamentos")
	private Long apartamentos;
	
	@JsonProperty(value="viviendas")
	private Long viviendas;
	
	public ReqIV(@JsonProperty(value="habitaciones") Long habitaciones,
                  @JsonProperty(value="apartamentos") Long apartamentos,
                  @JsonProperty(value="viviendas") Long viviendas)
        {
		super();
		this.habitaciones = habitaciones;
		this.apartamentos = apartamentos;
		this.viviendas = viviendas;
	}

	public Long getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(Long habitaciones) {
		this.habitaciones = habitaciones;
	}

	public Long getApartamentos() {
		return apartamentos;
	}

	public void setApartamentos(Long apartamentos) {
		this.apartamentos = apartamentos;
	}

	public Long getViviendas() {
		return viviendas;
	}

	public void setViviendas(Long viviendas) {
		this.viviendas = viviendas;
	}
}

