package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ReqV {

   
    
	@JsonProperty(value="hotel")
	private Integer hotel;
	
	@JsonProperty(value="hostal")
	private Integer hostal;
        @JsonProperty(value="persona")
	private Integer persona;
        @JsonProperty(value="vivienda")
	private Integer vivienda;
	
	public ReqV(@JsonProperty(value="persona")Integer persona,
                @JsonProperty(value="vivienda")Integer vivienda,
                @JsonProperty(value="hotel")Integer hotel,
                @JsonProperty(value="hostal") Integer hostal)
        {
		super();
		this.hotel = hotel;
		this.hostal = hostal;
                this.vivienda = vivienda;
                this.persona = persona;
	}
         /**
     * @return the hotel
     */
    public Integer getHotel() {
        return hotel;
    }

    /**
     * @param hotel the hotel to set
     */
    public void setHotel(Integer hotel) {
        this.hotel = hotel;
    }

    /**
     * @return the hostal
     */
    public Integer getHostal() {
        return hostal;
    }

    /**
     * @param hostal the hostal to set
     */
    public void setHostal(Integer hostal) {
        this.hostal = hostal;
    }

    /**
     * @return the persona
     */
    public Integer getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(Integer persona) {
        this.persona = persona;
    }

    /**
     * @return the vivienda
     */
    public Integer getVivienda() {
        return vivienda;
    }

    /**
     * @param vivienda the vivienda to set
     */
    public void setVivienda(Integer vivienda) {
        this.vivienda = vivienda;
    }

    
}