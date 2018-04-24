package vos;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ReservaColectiva {

	
	@JsonProperty(value="descripcion")
	private String descripcion;
	
	@JsonProperty(value="fechaInicial")
	private String fechaInicial;

	@JsonProperty(value="fechaFinal")
	private String  fechaFinal;
	
	@JsonProperty(value="idReservaColectiva")
	private Long idReservaColectiva;
	
	@JsonProperty(value="noches")
	private Long noches;
	
	@JsonProperty(value="costo")
	private Long costo;
	
	
	@JsonProperty(value="idClientes")
	private List<Long> idClientes;
	

	@JsonProperty(value="contratos")
	private List<Long> contratos;

	public ReservaColectiva(@JsonProperty(value="descripcion")String descripcion,
			@JsonProperty(value="fechaInicial")String fechaInicial,
			@JsonProperty(value="fechaFinal") String fechaFinal,
			@JsonProperty(value="idReservaColectiva") Long idReservaColectiva,
			@JsonProperty(value="noches")Long noches,
			@JsonProperty(value="costo") Long costo,
			@JsonProperty(value="idClientes") List<Long> idClientes, 
			@JsonProperty(value="contratos")List<Long> contratos) {
		super();
		this.descripcion = descripcion;
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
		this.idReservaColectiva = idReservaColectiva;
		this.noches = noches;
		this.costo = costo;
		this.idClientes = idClientes;
		this.contratos = contratos;
	}

	

	public List<Long> getContratos() {
		return contratos;
	}

	public void setContratos(ArrayList<Long> contratos) {
		this.contratos = contratos;
	}
	
	public void addContrato(Long contrato)
	{
		contratos.add(contrato);
	}
	public void addCliente(Long cliente)
	{
		idClientes.add(cliente);
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public String getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Long getIdReservaColectiva() {
		return idReservaColectiva;
	}

	public void setIdReservaColectiva(Long idReservaColectiva) {
		this.idReservaColectiva = idReservaColectiva;
	}

	public Long getNoches() {
		return noches;
	}

	public void setNoches(Long noches) {
		this.noches = noches;
	}

	public Long getCosto() {
		return costo;
	}

	public void setCosto(Long costo) {
		this.costo = costo;
	}

	public List<Long> getIdClientes() {
		return idClientes;
	}

	public void setIdClientes(ArrayList<Long> idClientes) {
		this.idClientes = idClientes;
	}


	
}
