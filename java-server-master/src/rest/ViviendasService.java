package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.AlohaTM;
import vos.Vivienda;

@Path("viviendas")
public class ViviendasService {

	@Context
	private ServletContext context;

	
	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}

	private String doBuenMessaje(){
		return "{ \"Bien\": \" borró la vivienda \"}";
	}
	
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}

	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getViviendas() {
		
		try {
			AlohaTM tm = new AlohaTM(getPath());
			
			List<Vivienda> habitaciones;
			//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			habitaciones = tm.getAllViviendas();
			return Response.status(200).entity(habitaciones).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrarVivienda(Vivienda vivienda) {
		AlohaTM tm = new AlohaTM(getPath());
		try {
			tm.registrarVivienda(vivienda);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(vivienda).build();
	}
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getViviendaById( @PathParam( "id" ) Long id )
	{
		try{
			AlohaTM tm = new AlohaTM( getPath( ) );
			
			Vivienda apto = tm.getViviendaById( id );
			return Response.status( 200 ).entity( apto ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	@DELETE
	@Path( "{id: \\d+}" )
	public Response borrarVivienda( @PathParam( "id" ) Long id ) {
		AlohaTM tm = new AlohaTM(getPath());
		try {
			tm.borrarVivienda(id);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(doBuenMessaje()).build();
	}
}
