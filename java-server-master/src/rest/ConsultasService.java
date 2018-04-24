package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.AlohaTM;
import vos.Oferta;
import vos.Cliente;
import vos.ReqI;
import vos.ReqIII;
import vos.ReqIV;
import vos.ReqV;
import vos.ReqVI;
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
		System.out.println("se mete al metodo");
		try{
			AlohaTM tm = new AlohaTM( getPath( ) );
			
			System.out.println("llama al req1 en el tm");

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
	@Path( "requerimientoIII" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getRequerimientoIII()
	{
		try{
			AlohaTM tm = new AlohaTM( getPath( ) );
			
			List<ReqIII> apto = tm.getRequerimientoIII();
			return Response.status( 200 ).entity( apto ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	@GET
	@Path( "requerimientoIV" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getRequerimientoVI()
	{
		try{
			AlohaTM tm = new AlohaTM( getPath( ) );
			
			List<ReqVI> apto = tm.getRequerimientoVI();
			return Response.status( 200 ).entity( apto ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	@GET
	@Path( "requerimientoIV" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getRequerimientoIV()
	{
		try{
			AlohaTM tm = new AlohaTM( getPath( ) );
			
			List<ReqIV> apto = tm.getRequerimientoIV();
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
	@Path( "requerimientoVII/"+"{id: \\d+}")
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getRequerimientoVII( @PathParam( "id" ) Long id)
	{
		try{
			AlohaTM tm = new AlohaTM( getPath( ) );
			
			//List<ReqVII> apto = tm.getRequerimientoVII(id, "hola");
			tm.transaccion();
			return Response.status( 200 ).entity( id ).build( );			
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
