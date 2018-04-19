package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.AlohaTM;
import vos.Oferta;
import vos.Cliente;
import vos.ReqI;
import vos.ReqV;
import vos.ReqVII;

@Path("consultas")
public class ConsultasService {

	@Context
	private ServletContext context;
	
	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}
	
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}
	
	private String doBuenMessaje(){
		return "{ \"Bien\": \" borro la oferta \"}";
	}
        
	@GET
	@Path( "requerimientoI" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getRequerimientoI()
	{
		try{
			AlohaTM tm = new AlohaTM( getPath( ) );
			
			List<ReqI> apto = tm.getRequerimientoI();
			return Response.status( 200 ).entity( apto ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	@GET
	@Path( "requerimientoIX" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getRequerimientoIX()
	{
		try{
			AlohaTM tm = new AlohaTM( getPath( ) );
			
			List<Oferta> apto = tm.getRequerimientoIX();
			return Response.status( 200 ).entity( apto ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	@GET
	@Path( "requerimientoII" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getRequerimientoII()
	{
		try{
			AlohaTM tm = new AlohaTM( getPath( ) );
			
			List<Oferta> apto = tm.getRequerimientoII();
			return Response.status( 200 ).entity( apto ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	@GET
	@Path( "requerimientoV" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getRequerimientoV()
	{
		try{
			AlohaTM tm = new AlohaTM( getPath( ) );
			
			List<ReqV> apto = tm.getRequerimientoV();
			return Response.status( 200 ).entity( apto ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Path( "requerimientoVII" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getRequerimientoVII()
	{
		try{
			AlohaTM tm = new AlohaTM( getPath( ) );
			
			List<ReqVII> apto = tm.getRequerimientoVII();
			return Response.status( 200 ).entity( apto ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Path( "requerimientoVIII" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getRequerimientoVIII()
	{
		try{
			AlohaTM tm = new AlohaTM( getPath( ) );
			
			List<Cliente> apto = tm.getRequerimientoVIII();
			return Response.status( 200 ).entity( apto ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
}