package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Oferta{   
	
<<<<<<< HEAD
	@JsonProperty(value="fechaFinal")
=======
	    @JsonProperty(value="fechaFinal")
>>>>>>> 472bb3c06a9b673ffa2c260c57ee0aa8548619d4
	private String fechaFinal;	
        
        @JsonProperty(value="fechaInicial")
	private String fechaInicial;
<<<<<<< HEAD
=======
        
        @JsonProperty(value="disponible")
    private Long disponible;
        
        @JsonProperty(value="descripcion")
        private String descripcion;
>>>>>>> 472bb3c06a9b673ffa2c260c57ee0aa8548619d4
        
        @JsonProperty(value="idOferta")
	private Long idOferta;
        
        @JsonProperty(value="idHabitacion")
	private Long idHabitacion;
         
        @JsonProperty(value="idApartamento")
	private Long idApartamento;
	
        @JsonProperty(value="idVivienda")
	private Long idVivienda;
<<<<<<< HEAD
        @JsonProperty(value="idOperador")
    	private Long idOperador;
=======
        
        @JsonProperty(value="idOperador")
    private Long idOperador;
>>>>>>> 472bb3c06a9b673ffa2c260c57ee0aa8548619d4
         
	public Oferta(@JsonProperty(value="idVivienda") Long idVivienda,
			@JsonProperty(value="idApartamento")Long idApartamento,
                        @JsonProperty(value="idHabitacion") Long idHabitacion,
                        @JsonProperty(value="idOferta") Long idOferta,
                        @JsonProperty(value="fechaInicial") String fechaInicial,
                        @JsonProperty(value="fechaFinal") String fechaFinal,
<<<<<<< HEAD
=======
                        @JsonProperty(value="disponible") Long disponible,
                        @JsonProperty(value="disponible") String descripcion,
>>>>>>> 472bb3c06a9b673ffa2c260c57ee0aa8548619d4
                        @JsonProperty(value="idOperador") Long idOperador) 
        {
		super();
		this.idVivienda=idVivienda;
                this.idOferta=idOferta;
                this.idHabitacion=idHabitacion;
                this.idApartamento=idApartamento;
                this.fechaInicial=fechaInicial;
                this.fechaFinal=fechaFinal;
<<<<<<< HEAD
=======
                this.disponible=disponible;
                this.descripcion=descripcion;
>>>>>>> 472bb3c06a9b673ffa2c260c57ee0aa8548619d4
                this.idOperador= idOperador;
	}
        
         /**
     * @return the fechaFinal
     */
    public String getFechaFinal() {
        return fechaFinal;
    }

    /**
     * @param fechaFinal the fechaFinal to set
     */
    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
    
    /**
     * @return the disponibe
     */
    public Long getDisponible() {
        return disponible;
    }

    /**
     * @param fechaFinal the fechaFinal to set
     */
    public void setDisponible(Long disponible) {
        this.disponible = disponible;
    }
    
    /**
     * @return the disponibe
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param fechaFinal the fechaFinal to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
   
    /**
     * @return the fechaInicial
     */
    public String getFechaInicial() {
        return fechaInicial;
    }

    /**
     * @param fechaInicial the fechaInicial to set
     */
    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    /**
     * @return the idOferta
     */
    public Long getIdOferta() {
        return idOferta;
    }

    /**
     * @param idOferta the idOferta to set
     */
    public void setIdOferta(Long idOferta) {
        this.idOferta = idOferta;
    }

    /**
     * @return the idHabitacion
     */
    public Long getIdHabitacion() {
        return idHabitacion;
    }

    /**
     * @param idHabitacion the idHabitacion to set
     */
    public void setIdHabitacion(Long idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    /**
     * @return the idApartamento
     */
    public Long getIdApartamento() {
        return idApartamento;
    }

    /**
     * @param idApartamento the idApartamento to set
     */
    public void setIdApartamento(Long idApartamento) {
        this.idApartamento = idApartamento;
    }

    /**
     * @return the idVivienda
     */
    public Long getIdVivienda() {
        return idVivienda;
    }

    /**
     * @param idVivienda the idVivienda to set
     */
    public void setIdVivienda(Long idVivienda) {
        this.idVivienda = idVivienda;
    }

	public Long getIdOperador() {
		return idOperador;
	}

	public void setIdOperador(Long idOperador) {
		this.idOperador = idOperador;
	}
    
}