package rest;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.AlohaTM;
import vos.Cliente;
import vos.Contrato;
import vos.ReservaColectiva;

@Path( "reservaColectiva" )
public class ReservaColectivaService {

	
	@Context
	private ServletContext context;

	
	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}

	
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}
	
	private String doBuenMessaje(){
		return "{ \"Bien\": \" se realizo la reserva colectiva \"}";
	}
	
//	@POST
//	@Path( "{tipo: \\d+}" +"/"+"{num: \\d+}")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response registrarContrato( @PathParam( "tipo" ) String tipo, @PathParam( "num" ) Long num, ReservaColectiva reserva) {
//		AlohaTM tm = new AlohaTM(getPath());
//		if (tipo.equals("habitacion")) {
//			try {
//				ReservaColectiva reservaColectiva = tm.hacerReservaColectivaHabitaciones(num, reserva);
//			} catch (Exception e) {
//				return Response.status(500).entity(doErrorMessage(e)).build();
//			}
//		}
//		return Response.status(200).entity(reserva).build();
//
//	}
//	@POST
//	@Path( "{num: \\d+}")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response registrarContrato3( @PathParam( "num" ) Long num, ReservaColectiva reserva) {
//		AlohaTM tm = new AlohaTM(getPath());
//		 
//			try {
//				tm.hacerReservaColectivaHabitaciones(num, reserva);
//			} catch (Exception e) {
//				return Response.status(500).entity(doErrorMessage(e)).build();
//			}
//		
//		return Response.status(200).entity(reserva).build();
//
//	}
//	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrarReservaColectiva(ReservaColectiva reserva) {
		AlohaTM tm = new AlohaTM(getPath());
		try {
			tm.hacerReservaColectivaHabitaciones(2, reserva);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(reserva).build();
	}
	
}
