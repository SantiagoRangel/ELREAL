package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ReqXII {
	
	@JsonProperty(value="semana")
	private Integer semana;
	
	@JsonProperty(value="peorOperador")
	private Long peorOperador;
	
	@JsonProperty(value="mejorOperador")
	private Long mejorOperador;
	
	@JsonProperty(value="peorOferta")
	private Long peorOferta;
	
	@JsonProperty(value="mejorOferta")
	private Long mejorOferta;
	
	public ReqXII(@JsonProperty(value="semana") Integer semana,
			@JsonProperty(value="peorOperador")Long peorOperador,
                        @JsonProperty(value="mejorOperador") Long mejorOperador,
                        @JsonProperty(value="peorOferta") Long peorOferta,
                        @JsonProperty(value="mejorOferta") Long mejorOferta) 
        {
		super();
		this.semana = semana;
		this.peorOperador = peorOperador;
		this.mejorOperador = mejorOperador;
		this.peorOferta = peorOferta;
		this.mejorOferta = mejorOferta;
	}

	public Integer getSemana() {
		return semana;
	}

	public void setSemana(Integer semana) {
		this.semana = semana;
	}

	public Long getPeorOperador() {
		return peorOperador;
	}

	public void setPeorOperador(Long peorOperador) {
		this.peorOperador = peorOperador;
	}

	public Long getMejorOperador() {
		return mejorOperador;
	}

	public void setMejorOperador(Long mejorOperador) {
		this.mejorOperador = mejorOperador;
	}

	public Long getPeorOferta() {
		return peorOferta;
	}

	public void setPeorOferta(Long peorOferta) {
		this.peorOferta = peorOferta;
	}

	public Long getMejorOferta() {
		return mejorOferta;
	}

	public void setMejorOferta(Long mejorOferta) {
		this.mejorOferta = mejorOferta;
	}
}
