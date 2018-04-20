package vos;


import org.codehaus.jackson.annotate.JsonProperty;

public class Contrato {
    
	@JsonProperty(value="descripcion")
	private String descripcion;
	
	@JsonProperty(value="fechaInicial")
	private String fechaInicial;

	@JsonProperty(value="fechaFinal")
	private String  fechaFinal;
	
	@JsonProperty(value="idContrato")
	private Long idContrato;
	
	@JsonProperty(value="noches")
	private Long noches;
	
	@JsonProperty(value="costo")
	private Long costo;
	
	@JsonProperty(value="idApartamento")
	private Long idApartamento;
	
	@JsonProperty(value="idHabitacion")
	private Long idHabitacion;

	@JsonProperty(value="idCliente")
	private Long idCliente;
	
	@JsonProperty(value="idOperador")
	private Long idOperador;
        
	@JsonProperty(value="idVivienda")
	private Long idVivienda;
        
	public Contrato(@JsonProperty(value="descripcion")String descripcion,
                @JsonProperty(value="fechaInicial")String fechaInicial,
                @JsonProperty(value="fechaFinal") String fechaFinal,
                @JsonProperty(value="idContrato") Long idContrato,
                @JsonProperty(value="noches") Long noches,
		@JsonProperty(value="costo")Long costo,
                @JsonProperty(value="idApartamento") Long idApartamento,
                @JsonProperty(value="idHabitacion") Long idHabitacion, 
                @JsonProperty(value="idCliente") Long idCliente,
                @JsonProperty(value="idOperador") Long idOperador,
                @JsonProperty(value="idVivienda") Long idVivienda) 
        {
		super();
		this.descripcion = descripcion;
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
		this.idContrato = idContrato;
		this.noches = noches;
		this.costo = costo;
		this.idApartamento = idApartamento;
		this.idHabitacion = idHabitacion;
		this.idCliente = idCliente;
		this.idOperador = idOperador;
		this.idVivienda = idVivienda;
	}
        
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String  getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public String  getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Long getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(Long idContrato) {
		this.idContrato = idContrato;
	}

	public Long getNoches() {
		return noches;
	}

	public void setNoches(Long noches) {
		this.noches = noches;
	}

	public Long getCostoTotal() {
		return costo;
	}

	public void setCostoTotal(Long costoTotal) {
		this.costo = costoTotal;
	}

	public Long getIdApartamento() {
		return idApartamento;
	}

	public void setIdApartamento(Long idApartamento) {
		this.idApartamento = idApartamento;
	}

	public Long getIdHabitacion() {
		return idHabitacion;
	}

	public void setIdHabitacion(Long idHabitacion) {
		this.idHabitacion = idHabitacion;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Long getIdOperador() {
		return idOperador;
	}

	public void setIdOperador(Long idOperador) {
		this.idOperador = idOperador;
	}
        
	public Long getIdVivienda() {
		return idVivienda;
	}
        
	public void setIdVivienda(Long idVivienda) {
		this.idVivienda = idVivienda;
	}
}
