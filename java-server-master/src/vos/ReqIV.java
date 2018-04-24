package vos;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class ReqIV {    
    
	@JsonProperty(value="idHab")
	private Long idHab;
	
	@JsonProperty(value="idApt")
	private Long idApt;
	
	@JsonProperty(value="idViv")
	private Long idViv;
	
	public ReqIV(@JsonProperty(value="idHab") Long idHab,
                  @JsonProperty(value="idApt") Long idApt,
                  @JsonProperty(value="idViv") Long idViv)
        {
		super();
		this.idHab = idHab;
		this.idApt = idApt;
		this.idViv = idViv;
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

