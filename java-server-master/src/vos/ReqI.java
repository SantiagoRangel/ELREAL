package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ReqI {

    
    
	@JsonProperty(value="proveedor")
	private String proveedor;
	
	@JsonProperty(value="ingresos")
	private Double ingresos;
	
	public ReqI(@JsonProperty(value="proveedor")String proveedor,
                @JsonProperty(value="ingresos") Double ingresos)
        {
		super();
		this.proveedor = proveedor;
		this.ingresos = ingresos;
	}
/**
     * @return the proveedor
     */
    public String getProveedor() {
        return proveedor;
    }

    /**
     * @param proveedor the proveedor to set
     */
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    /**
     * @return the ingresos
     */
    public Double getIngresos() {
        return ingresos;
    }

    /**
     * @param ingresos the ingresos to set
     */
    public void setIngresos(Double ingresos) {
        this.ingresos = ingresos;
    }
	
}
