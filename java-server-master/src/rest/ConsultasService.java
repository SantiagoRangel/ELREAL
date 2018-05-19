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
import vos.ReqX;
import vos.ReqXII;
import vos.ReqXIII;

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
	@Path( "requerimientoXIII" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getRequerimientoXIII()
	{
		try{
			AlohaTM tm = new AlohaTM( getPath( ) );
			
			List<ReqXIII> apto = tm.getRequerimientoXIII();
			return Response.status( 200 ).entity( apto ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	@GET
	@Path( "requerimientoXII" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getRequerimientoXII()
	{
		try{
			AlohaTM tm = new AlohaTM( getPath( ) );
			
			List<ReqXII> apto = tm.getRequerimientoXII();
			return Response.status( 200 ).entity( apto ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	@GET
	@Path( "requerimientoVI/" + "{id}")
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getRequerimientoVI(@PathParam( "id" ) Long id)
	{
		try{
			AlohaTM tm = new AlohaTM( getPath( ) );
			
			List<ReqVI> apto = tm.getRequerimientoVI(id);
			return Response.status( 200 ).entity( apto ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	@GET
	@Path( "requerimientoX/" + "{id: \\d+}"+"{group}")
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getRequerimientoX(@PathParam( "id" ) Long id, @PathParam( "group" ) String group)
	{
		try{
			AlohaTM tm = new AlohaTM( getPath( ) );
			
			List<ReqX> apto = tm.getRequerimientoX(id,id,group, group, null);
			return Response.status( 200 ).entity( apto ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	@GET
	@Path( "requerimientoVI/" + "{id}")
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getRequerimientoXI(@PathParam( "id" ) Long id)
	{
		try{
			AlohaTM tm = new AlohaTM( getPath( ) );
			
			List<ReqVI> apto = tm.getRequerimientoVI(id);
			return Response.status( 200 ).entity( apto ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	@GET
	@Path( "requerimientoIV/"+"{servicios}"+"/"+"{ff}"+"/"+"{fi}")
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getRequerimientoIV( @PathParam( "servicios" ) String servicios, @PathParam( "fi" ) String fi,  @PathParam( "ff" ) String ff)
	{
		try{
			AlohaTM tm = new AlohaTM( getPath( ) );
			String[] servicios2 = servicios.split(",");
			Long[] serviciosLong = new Long[servicios2.length];
			for (int i = 0; i < servicios2.length; i++) {
				serviciosLong[i] = Long.parseLong(servicios2[i]);
			}
			List<ReqIV> apto = tm.getRequerimientoIV(serviciosLong, ff, fi);
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
	@Path( "requerimientoVII/"+"{id: \\d+}"+"/"+"{lol}")
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getRequerimientoVII( @PathParam( "id" ) Long id, @PathParam( "lol" ) String tipo)
	{
		try{
			AlohaTM tm = new AlohaTM( getPath( ) );
			
		    List<ReqVII> apto = tm.getRequerimientoVII(id,tipo);
			return Response.status( 200 ).entity( apto ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Path( "requerimientoVIII/" +"{id: \\d+}"+"/"+"{lol}")
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getRequerimientoVIII(@PathParam( "id" ) Long id,@PathParam( "lol" ) String tipo)
	{
		try{
			AlohaTM tm = new AlohaTM( getPath( ) );
			
			List<Cliente> apto = tm.getRequerimientoVIII(id, tipo);
			return Response.status( 200 ).entity( apto ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
}
