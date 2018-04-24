package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ReqIII {    
    
	@JsonProperty(value="indice")
	private Double indice;
	
	@JsonProperty(value="idOferta")
	private Long idOferta;
	
	public ReqIII(@JsonProperty(value="indice") Double indice,
                @JsonProperty(value="idOferta") Long idOferta)
        {
		this.indice = indice;
		this.idOferta = idOferta;
	}
/**
     * @return the proveedor
     */
    public Double getIndice() {
        return indice;
    }

    /**
     * @param proveedor the proveedor to set
     */
    public void setIndice(Double indice) {
        this.indice = indice;
    }

    /**
     * @return the ingresos
     */
    public Long getIdOferta() {
        return idOferta;
    }

    /**
     * @param ingresos the ingresos to set
     */
    public void setIdOferta(Long idOferta) {
        this.idOferta = idOferta;
    }
	
}
