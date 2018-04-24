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

	public Long getIdHab() {
		return idHab;
	}

	public void setIdHab(Long idHab) {
		this.idHab = idHab;
	}

	public Long getIdApt() {
		return idApt;
	}

	public void setIdApt(Long idApt) {
		this.idApt = idApt;
	}

	public Long getIdViv() {
		return idViv;
	}

	public void setIdViv(Long idViv) {
		this.idViv = idViv;
	}
	
}

