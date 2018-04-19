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
import vos.Cliente;

@Path("clientes")
public class ClientesService {

	
	
	@Context
	private ServletContext context;

	
	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}

	private String doBuenMessaje(){
		return "{ \"Bien\": \" borró el cliente \"}";
	}
	
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}

	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getClientes() {
		
		try {
			AlohaTM tm = new AlohaTM(getPath());
			
			List<Cliente> habitaciones;
			//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			habitaciones = tm.getAllClientes();
			return Response.status(200).entity(habitaciones).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrarClientes(Cliente habitacion) {
		AlohaTM tm = new AlohaTM(getPath());
		try {
			tm.registrarCliente(habitacion);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(habitacion).build();
	}
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getClienteById( @PathParam( "id" ) Long id )
	{
		try{
			AlohaTM tm = new AlohaTM( getPath( ) );
			
			Cliente apto = tm.getClienteById( id );
			return Response.status( 200 ).entity( apto ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	@DELETE
	@Path( "{id: \\d+}" )
	public Response borrarCliente( @PathParam( "id" ) Long id ) {
		AlohaTM tm = new AlohaTM(getPath());
		try {
			tm.borrarCliente(id);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(doBuenMessaje()).build();
	}
}
