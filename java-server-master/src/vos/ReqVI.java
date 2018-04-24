package vos;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class ReqVI {    
    
	@JsonProperty(value="noches")
	private Integer noches;
	
	@JsonProperty(value="total")
	private Double total;
	
	@JsonProperty(value="habita")
	private Integer habita;
	
	@JsonProperty(value="apto")
	private Integer apto;
	
	@JsonProperty(value="vivien")
	private Integer vivien;
	
	public ReqVI(@JsonProperty(value="habita") Integer habita,
                  @JsonProperty(value="apto") Integer apto,
                  @JsonProperty(value="vivien") Integer vivien,
                  @JsonProperty(value="total") Double total,
                  @JsonProperty(value="noches") Integer noches)
        {
		super();
		this.habita = habita;
		this.vivien = vivien;
		this.apto = apto;
		this.total = total;
		this.noches = noches;
	}

	public Integer getNoches() {
		return noches;
	}

	public void setNoches(Integer noches) {
		this.noches = noches;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getHabita() {
		return habita;
	}

	public void setHabita(Integer habita) {
		this.habita = habita;
	}

	public Integer getApto() {
		return apto;
	}

	public void setApto(Integer apto) {
		this.apto = apto;
	}

	public Integer getVivien() {
		return vivien;
	}

	public void setVivien(Integer vivien) {
		this.vivien = vivien;
	}
}

