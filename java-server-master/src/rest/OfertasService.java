package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
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
import vos.Oferta;

@Path( "ofertas" )
public class OfertasService {
	
	@Context
	private ServletContext context;

	
	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}

	
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}
	
	private String doBuenMessaje(){
		return "{ \"Bien\": \" borró la oferta \"}";
	}
	

	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getOfertas() {
		
		try {
			AlohaTM tm = new AlohaTM(getPath());
			
			List<Oferta> apartamentos;
			//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			apartamentos = tm.getAllOfertas();
			return Response.status(200).entity(apartamentos).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrarOferta(Oferta Oferta) {
		AlohaTM tm = new AlohaTM(getPath());
		try {
			tm.registrarOferta(Oferta);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(Oferta).build();
	}
	
	
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getOfertaById( @PathParam( "id" ) Long id )
	{
		try{
			AlohaTM tm = new AlohaTM( getPath( ) );
			
			Oferta apto = tm.getOfertaById( id );
			return Response.status( 200 ).entity( apto ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@DELETE
	@Path( "{id: \\d+}" )
	public Response borrarOferta( @PathParam( "id" ) Long id ) {
		AlohaTM tm = new AlohaTM(getPath());
		try {
			tm.borrarOferta(id);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(doBuenMessaje()).build();
	}
	@PUT
	@Path( "{id: \\d+}"+"/hab" )
	public Response habilitarOferta( @PathParam( "id" ) Long id ) throws Exception
	{
		AlohaTM tm = new AlohaTM(getPath());
		Oferta oferta= tm.getOfertaById(id);
		try {
			tm.habilitarOferta(oferta);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity("Habilito la oferta "+id).build();
	}
	@PUT
	@Path( "{id: \\d+}"+"/deshab")
	public Response deshabilitarOferta( @PathParam( "id" ) Long id ) throws Exception
	{
		AlohaTM tm = new AlohaTM(getPath());
		Oferta oferta= tm.getOfertaById(id);
		try {
			tm.deshabilitarOferta(oferta);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity("desHabilito la oferta "+id).build();
	}

}
