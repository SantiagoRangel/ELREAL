package vos;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class ReqVII {    
    
	@JsonProperty(value="empieza")
	private Date empieza;
	
	@JsonProperty(value="termina")
	private Date termina;
	
	public ReqVII(@JsonProperty(value="empieza") Date empieza,
                  @JsonProperty(value="termina") Date termina)
        {
		super();
		this.empieza = empieza;
		this.termina = termina;
	}

/**
     * @return the empieza
     */
    public Date getEmpieza() {
        return empieza;
    }

    /**
     * @param proveedor the empieza to set
     */
    public void setEmpieza(Date empieza) {
        this.empieza = empieza;
    }

    /**
     * @return the termina
     */
    public Date getTermina() {
        return termina;
    }

    /**
     * @param ingresos the termina to set
     */
    public void setTermina(Date termina) {
        this.termina = termina;
    }
	
}

