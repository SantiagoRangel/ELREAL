package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

import vos.Contrato;
import vos.Hostal;
import vos.ReservaMasiva;

/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la 
 * aplicación
 * @author Grupo A - 16*/
public class DAOReservaMasiva{

	
	public final static String USUARIO = "ISIS2304A241810";
	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;
	
	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Metodo constructor que crea DAOIngrediente
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOReservaMasiva() {
		recursos = new ArrayList<Object>();
	}

	/**
	 * Metodo que cierra todos los recursos que estan en el arreglo de recursos
	 * <b>post: </b> Todos los recurso del arreglo de recursos han sido cerrados
	 */
	public void cerrarRecursos() {
		for(Object ob : recursos){
			if(ob instanceof PreparedStatement)
				try {
					((PreparedStatement) ob).close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
	}

	/**
	 * Metodo que inicializa la connection del DAO a la base de datos con la conexión que 
         * entra como parametro.
	 * @param con - connection a la base de datos
	 */
	public void setConn(Connection con){
		this.conn = con;
	}
        
	public void reservaMasiva(int gente, String tipo, String fechaInicial, String fechaFinal, Long noches, Long costo, Long idApartamento, Long idHabitacion, Long idCliente, Long idOperador, Long idVivienda, String descripcion) throws SQLException, Exception {
	
		DAOTablaContrato contratos = new DAOTablaContrato();
		ReservaMasiva resp = new ResrvaMasiva();
		
		if ( tipo.equals("HABITACION"))
		{
			DAOTablaHabitacion habitaciones = new DAOTablaHabitacion();
			if (habitaciones.getHabitaciones().size()>= gente)
			{
				ArrayList<Contrato> boleo = new ArrayList<>();
				for (int i = 0; i<gente; i++)
				{
					Long x = (long) (i+100000000);
					Contrato reserva = new Contrato (descripcion, fechaInicial, fechaFinal, x ,noches, costo, idApartamento, idHabitacion, idCliente, idOperador, idVivienda);
					contratos.registrarContrato(reserva);
					resp.addReserva(reserva);
					
				}
				return resp;
			}
			else
			{
				
			}
		}
		else if (tipo.equals("APARTAMENTO"))
		{
			DAOTablaApartamento apartamentos = new DAOTablaApartamento();
			if (apartamentos.getApartamentos().size()>= gente)
			{
				
				for (int i = 0; i<gente; i++)
				{
					Contrato reserva = new Contrato ();
					contratos.add(reserva);
					
				}
				return new ReservaMasiva(contratos);
			}
			else
			{
				
			}
		}
		else if (tipo.equals("VIVIENDA"))
	    {
			DAOTablaVivienda viviendas = new DAOTablaVivienda();
			if (viviendas.getViviendas().size()>= gente)
			{
				ArrayList<Contrato> contratos = new Arraylist<>();
				for (int i = 0; i<gente; i++)
				{
					Contrato reserva = new Contrato ();
					contratos.add(reserva);
					
				}
				return new ReservaMasiva(contratos);
			}
			else
			{
				
			}
	    }
		ArrayList<ReservaMasiva> reservaMasiva = new ArrayList<ReservaMasiva>();
		
		String sql = String.format("SELECT * FROM %1$s.HOSTAL WHERE ROWNUM <= 50", USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			reservaMasiva.add(convertResultSetToReservaMasiva(rs));
		}
		return reservaMasiva;
	}

    public ReservaMasiva convertResultSetToReservaMasiva(ResultSet resultSet) throws SQLException {		
		
		Long idContrato = resultSet.getLong("IDCONTRATO");	
	
		ReservaMasiva op = new ReservaMasiva(idContrato);
		return op;
	}
}


