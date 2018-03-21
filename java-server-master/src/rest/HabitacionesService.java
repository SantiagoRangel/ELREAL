package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.AlohaTM;
import tm.VideoAndesTM;
import vos.Cliente;
import vos.Habitacion;


@Path("habitaciones")
public class HabitacionesService {

	
	@Context
	private ServletContext context;

	
	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}

	
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}

	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getBebedores() {
		
		try {
			AlohaTM tm = new AlohaTM(getPath());
			
			List<Habitacion> habitaciones;
			//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			habitaciones = tm.getAllHabitaciones();
			return Response.status(200).entity(habitaciones).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	//xascdaca
	@POST

	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrarHabitacion(Habitacion habitacion) {
		AlohaTM tm = new AlohaTM(getPath());
		try {
			tm.registrarHabitacion(habitacion);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(habitacion).build();
	}
	
//	@GET
//	@Path( "{id: \\d+}" )
//	@Produces( { MediaType.APPLICATION_JSON } )
//	public Response getBebedorById( @PathParam( "id" ) Long id )
//	{
//		try{
//			AlohaTM tm = new AlohaTM( getPath( ) );
//			
//			Bebedor bebedor = tm.getBebedorById( id );
//			return Response.status( 200 ).entity( bebedor ).build( );			
//		}
//		catch( Exception e )
//		{
//			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
//		}
//	}
}
