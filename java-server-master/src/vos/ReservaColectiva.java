package vos;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

public class ReservaColectiva {

	
	@JsonProperty(value="contratos")
	private ArrayList<Long> contratos;

	public ReservaColectiva(@JsonProperty(value="contratos")ArrayList<Long> contratos) {
		super();
		this.contratos = contratos;
	}

	public ArrayList<Long> getContratos() {
		return contratos;
	}

	public void setContratos(ArrayList<Long> contratos) {
		this.contratos = contratos;
	}
	
	public void addContrato(Long contrato)
	{
		contratos.add(contrato);
	}
}
