package rest;

import java.util.List;

import javax.servlet.ServletContext;
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
import vos.Contrato;

@Path("contratos")
public class ContratosService {

	
	@Context
	private ServletContext context;

	
	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}

	
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}
	
	private String doBuenMessaje(){
		return "{ \"Bien\": \" borró el contrato \"}";
	}
	

	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getContratos() {
		
		try {
			AlohaTM tm = new AlohaTM(getPath());
			
			List<Contrato> apartamentos;
			//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			apartamentos = tm.getAllContratos();
			return Response.status(200).entity(apartamentos).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrarContraro(Contrato contrato) {
		AlohaTM tm = new AlohaTM(getPath());
		try {
			tm.registrarContrato(contrato);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(contrato).build();
	}
	
	
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getContratoById( @PathParam( "id" ) Long id )
	{
		try{
			AlohaTM tm = new AlohaTM( getPath( ) );
			
			Contrato apto = tm.getContratoById( id );
			return Response.status( 200 ).entity( apto ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@DELETE
	@Path( "{id: \\d+}" )
	public Response borrarContrato( @PathParam( "id" ) Long id ) {
		AlohaTM tm = new AlohaTM(getPath());
		try {
			tm.borrarContrato(id);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(doBuenMessaje()).build();
	}

}
