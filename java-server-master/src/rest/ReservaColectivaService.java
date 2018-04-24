package rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.AlohaTM;
import vos.Apartamento;
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
	private String doBuenBorradoMessaje(){
		return "{ \"Bien\": \" se cancelo la reserva Colectiva \"}";
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
//	@POST
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response registrarReservaColectiva(ReservaColectiva reserva) {
//		System.out.println("Entro al metodo");
//		AlohaTM tm = new AlohaTM(getPath());
//		try {
//			tm.hacerReservaColectivaHabitaciones(2, reserva);
//		} catch (Exception e) {
//			return Response.status(500).entity(doErrorMessage(e)).build();
//		}
//		return Response.status(200).entity(reserva).build();
//	}
	@POST
	@Produces(MediaType.APPLICATION_JSON)
    @Path( "{num: \\d+}"+"/habitacion")
	public Response registrarReservaColectivaHabitacion( @PathParam( "num" ) int var) {
		System.out.println("Entro al metodo");
	    List<Long> idClientes= new ArrayList<Long>();
	    idClientes.add((long) 10021);
	    idClientes.add((long) 10022);
	    idClientes.add((long) 10023);
	    idClientes.add((long) 10024);
	    idClientes.add((long) 10025);
	    List<Long> contratos = null;
	    Long siete= Long.parseLong(""+7);
	    Long num= Long.parseLong(""+25);
	    Long idReserva =Long.parseLong(""+1000);
		ReservaColectiva reserva= new ReservaColectiva("reservaPrimera", "2000-1-1", "2000-1-5", idReserva, siete, num, idClientes, contratos);
		AlohaTM tm = new AlohaTM(getPath());
			try {
				tm.hacerReservaColectivaHabitaciones(var, reserva);
			} catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
		
		
		return Response.status(200).entity(doBuenMessaje()).build();
	}
	@POST
	@Produces(MediaType.APPLICATION_JSON)
    @Path( "{num: \\d+}"+"/apartamento")
	public Response registrarReservaColectivaAparatamento( @PathParam( "num" ) int var) {
		System.out.println("Entro al metodo");
	    List<Long> idClientes= new ArrayList<Long>();
	    idClientes.add((long) 10021);
	    idClientes.add((long) 10022);
	    idClientes.add((long) 10023);
	    idClientes.add((long) 10024);
	    idClientes.add((long) 10025);
	    List<Long> contratos = null;
	    Long siete= Long.parseLong(""+7);
	    Long num= Long.parseLong(""+25);
	    Long idReserva =Long.parseLong(""+2000);
		ReservaColectiva reserva= new ReservaColectiva("reservaApartamentoPrueba", "2000-1-1", "2000-1-5", idReserva, siete, num, idClientes, contratos);
		AlohaTM tm = new AlohaTM(getPath());
			try {
				tm.hacerReservaColectivaApartamento(var, reserva);
			} catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
		
		
		return Response.status(200).entity(doBuenMessaje()).build();
	}
	@POST
	@Produces(MediaType.APPLICATION_JSON)
    @Path( "{num: \\d+}"+"/vivienda")
	public Response registrarReservaColectivaVivienda( @PathParam( "num" ) int var) {
		System.out.println("Entro al metodo");
	    List<Long> idClientes= new ArrayList<Long>();
	    idClientes.add((long) 10021);
	    idClientes.add((long) 10022);
	    idClientes.add((long) 10023);
	    idClientes.add((long) 10024);
	    idClientes.add((long) 10025);
	    List<Long> contratos = null;
	    Long siete= Long.parseLong(""+7);
	    Long num= Long.parseLong(""+25);
	    Long idReserva =Long.parseLong(""+3000);
		ReservaColectiva reserva= new ReservaColectiva("reservaVivienda1", "2000-1-1", "2000-1-5", idReserva, siete, num, idClientes, contratos);
		AlohaTM tm = new AlohaTM(getPath());
			try {
				tm.hacerReservaColectivaVivienda(var, reserva);
			} catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
		
		
		return Response.status(200).entity(doBuenMessaje()).build();
	}
	@DELETE
	@Path( "{descripcion}" )
	public Response cancelarReservaColectiva( @PathParam( "descripcion" ) String descripcion ) {
		AlohaTM tm = new AlohaTM(getPath());
		try {
			tm.borrarContratratosDesc(descripcion);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(doBuenBorradoMessaje()).build();
	}
	
}
