package vos;

import java.sql.Array;
import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

public class ReservaMasiva{
	
	@JsonProperty(value="reservas")
	private Array reservas;

	public ReservaMasiva()
	{		
		
	}
        
	public void addReserva(Contrato reserva) {
		((Object) reservas).add(reserva);
	}
}
