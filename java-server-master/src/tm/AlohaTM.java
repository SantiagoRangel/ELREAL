package tm;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import dao.DAOReqI;
import dao.DAOReqIII;
import dao.DAOReqV;
import dao.DAOReqVI;
import dao.DAOReqVII;
import dao.DAOReqX;
import dao.DAOReqXII;
import dao.DAOReqXIII;
import dao.DAOReqIV;
import dao.DAOTablaApartamento;
import dao.DAOTablaCliente;
import dao.DAOTablaContrato;
import dao.DAOTablaHabitacion;
import dao.DAOTablaHostal;
import dao.DAOTablaHotel;
import dao.DAOTablaOferta;
import dao.DAOTablaOperador;
import dao.DAOTablaPersonaNatural;
import dao.DAOTablaVivienda;
import dao.DAOTablaViviendaUniveristaria;
import vos.Apartamento;
import vos.Cliente;
import vos.Contrato;
import vos.Habitacion;
import vos.Hostal;
import vos.Hotel;
import vos.Oferta;
import vos.Operador;
import vos.PersonaNatural;
import vos.ReqI;
import vos.ReqIII;
import vos.ReqV;
import vos.ReqVI;
import vos.ReqVII;
import vos.ReqX;
import vos.ReqXII;
import vos.ReqXIII;
import vos.ReqIV;
import vos.ReservaColectiva;
import vos.Vivienda;
import vos.ViviendaUniversitaria;

public class AlohaTM {
	 
	private static final String CONNECTION_DATA_FILE_NAME_REMOTE = "/conexion.properties";

	/**
	 * Atributo estatico que contiene el path absoluto del archivo que tiene los datos de la conexion
	 */
	private  String connectionDataPath;

	/**
	 * Atributo que guarda el usuario que se va a usar para conectarse a la base de datos.
	 */
	private String user;
	/**
	 * Atributo que guarda la clave que se va a usar para conectarse a la base de datos.
	 */
	private String password;

	/**
	 * Atributo que guarda el URL que se va a usar para conectarse a la base de datos.
	 */
	private String url;

	/**
	 * Atributo que guarda el driver que se va a usar para conectarse a la base de datos.
	 */
	private String driver;
	
	/**
	 * conexion a la base de datos
	 */
	private Connection conn;
	
		
	
	
	public AlohaTM(String contextPathP) {
		connectionDataPath = contextPathP + CONNECTION_DATA_FILE_NAME_REMOTE;
		initConnectionData();
	}
	private Connection darConexion() throws SQLException {
		System.out.println(" Attempting Connection to: " + url + " - By User: " + user);
		Connection coni = DriverManager.getConnection(url, user, password);
		coni.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
		coni.setAutoCommit(false);
		return coni;
	}
	
	private void initConnectionData() {
		try {
			File arch = new File(this.connectionDataPath);
			Properties prop = new Properties();
			FileInputStream in = new FileInputStream(arch);
			prop.load(in);
			in.close();
			this.url = prop.getProperty("url");
			this.user = prop.getProperty("usuario");
			this.password = prop.getProperty("clave");
			this.driver = prop.getProperty("driver");
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Habitacion> getAllHabitaciones() throws Exception {
		DAOTablaHabitacion daoHabitacion = new DAOTablaHabitacion();
		List<Habitacion> habitaciones;
		try 
		{
			this.conn = darConexion();
			daoHabitacion.setConn(conn);
			
			//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			habitaciones = daoHabitacion.getHabitaciones();
		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoHabitacion.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return habitaciones;
	}
	
	public List<Apartamento> getAllApartamentos() throws Exception {
		DAOTablaApartamento daoApartamento = new DAOTablaApartamento();
		List<Apartamento> apartamentos;
		try 
		{
			this.conn = darConexion();
			daoApartamento.setConn(conn);
			
			//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			apartamentos = daoApartamento.getApartamentos();
		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoApartamento.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return apartamentos;
	}
	
	public List<Operador> getAllOperadores() throws Exception {
		DAOTablaOperador daoOperador = new DAOTablaOperador();
		List<Operador> operadores;
		try 
		{
			this.conn = darConexion();
			daoOperador.setConn(conn);
			
			//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			operadores = daoOperador.getOperadores();
		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoOperador.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return operadores;
	}
	public void registrarHabitacion(Habitacion habitacion) throws Exception {
		DAOTablaHabitacion daoHabitaciones = new DAOTablaHabitacion();
		try 
		{
			//////transaccion
			
			this.conn = darConexion();
			daoHabitaciones.setConn(conn);
			daoHabitaciones.registrarHabitacion(habitacion);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoHabitaciones.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public void registrarApartamento(Apartamento apartamento) throws Exception {
		DAOTablaApartamento daoBebedor = new DAOTablaApartamento( );
		try
		{
			//TODO Requerimiento 3D: Obtenga la conexion a la Base de Datos (revise los metodos de la clase)
			
				this.conn = darConexion();
			
			//TODO Requerimiento 3E: Establezca la conexion en el objeto DAOBebedor (revise los metodos de la clase DAOBebedor)
				daoBebedor.setConn(conn);

			daoBebedor.registrarApartamento(apartamento);
			conn.commit();


		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoBebedor.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}
	public Apartamento getApartamentoById(Long id) throws Exception {
		DAOTablaApartamento daoBebedor = new DAOTablaApartamento();
		Apartamento bebedor = null;
		try 
		{
			this.conn = darConexion();
			daoBebedor.setConn(conn);
			bebedor = daoBebedor.findApartamentoById(id);
			if(bebedor == null)
			{
				throw new Exception("El bebedor con el id = " + id + " no se encuentra persistido en la base de datos.");				
			}
		} 
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoBebedor.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return bebedor;
	}
	public void borrarApartamento(Long id) throws Exception
	{
		DAOTablaApartamento darPreferencia = new DAOTablaApartamento();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			darPreferencia.setConn(conn);
			darPreferencia.deleteApartamento(id);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				darPreferencia.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public Habitacion getHabitacionById(Long id) throws Exception {
		DAOTablaHabitacion daoBebedor = new DAOTablaHabitacion();
		Habitacion bebedor = null;
		try 
		{
			this.conn = darConexion();
			daoBebedor.setConn(conn);
			bebedor = daoBebedor.findHabitacionById(id);
			if(bebedor == null)
			{
				throw new Exception("La Habitacion con el id = " + id + " no se encuentra persistido en la base de datos.");				
			}
		} 
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoBebedor.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return bebedor;
	}
	public void borrarHabitacion(Long id) throws Exception {
		DAOTablaHabitacion darPreferencia = new DAOTablaHabitacion();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			darPreferencia.setConn(conn);
			darPreferencia.deleteHabitacion(id);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				darPreferencia.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	public void registrarOperador(Operador operador) throws Exception {
		DAOTablaOperador daoHabitaciones = new DAOTablaOperador();
		try 
		{
			//////transaccion
			
			this.conn = darConexion();
			daoHabitaciones.setConn(conn);
			daoHabitaciones.registrarOperador(operador);;
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoHabitaciones.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public Operador getOperadorById(Long id) throws Exception {
		DAOTablaOperador daoBebedor = new DAOTablaOperador();
		Operador bebedor = null;
		try 
		{
			this.conn = darConexion();
			daoBebedor.setConn(conn);
			bebedor = daoBebedor.findOperadorById(id);
			if(bebedor == null)
			{
				throw new Exception("La Habitacion con el id = " + id + " no se encuentra persistido en la base de datos.");				
			}
		} 
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoBebedor.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return bebedor;
	}
	public void borrarOperador(Long id) throws Exception {
		DAOTablaOperador darPreferencia = new DAOTablaOperador();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			darPreferencia.setConn(conn);
			darPreferencia.deleteOperador(id);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				darPreferencia.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}
	
	public void registrarHotel(Hotel hotel) throws Exception {
		DAOTablaHotel daoHabitaciones = new DAOTablaHotel();
		try 
		{
			//////transaccion
			
			this.conn = darConexion();
			daoHabitaciones.setConn(conn);
			daoHabitaciones.registrarHotel(hotel);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoHabitaciones.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public void borrarHostal(Long id) throws Exception {
		DAOTablaHostal darPreferencia = new DAOTablaHostal();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			darPreferencia.setConn(conn);
			darPreferencia.deleteHostal(id);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				darPreferencia.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public Hostal getHostalById(Long id) throws Exception {
		DAOTablaHostal daoBebedor = new DAOTablaHostal();
		Hostal bebedor = null;
		try 
		{
			this.conn = darConexion();
			daoBebedor.setConn(conn);
			bebedor = daoBebedor.findHostalById(id);
			if(bebedor == null)
			{
				throw new Exception("El hostal con el id = " + id + " no se encuentra persistido en la base de datos.");				
			}
		} 
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoBebedor.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return bebedor;
	}
	public void registrarHostal(Hostal hostal) throws Exception {
		DAOTablaHostal daoHabitaciones = new DAOTablaHostal();
		try 
		{
			//////transaccion
			
			this.conn = darConexion();
			daoHabitaciones.setConn(conn);
			daoHabitaciones.registrarHostal(hostal);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoHabitaciones.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public List<Hostal> getAllHostales() throws Exception {
		DAOTablaHostal daoOperador = new DAOTablaHostal();
		List<Hostal> operadores;
		try 
		{
			this.conn = darConexion();
			daoOperador.setConn(conn);
			
			//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			operadores = daoOperador.getHostales();
		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoOperador.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return operadores;
	}
	public List<Hotel> getAllHoteles() throws Exception {
		DAOTablaHotel daoOperador = new DAOTablaHotel();
		List<Hotel> operadores;
		try 
		{
			this.conn = darConexion();
			daoOperador.setConn(conn);
			
			//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			operadores = daoOperador.getHoteles();
		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoOperador.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return operadores;
	}
	public Hotel getHotelById(Long id) throws Exception {
		DAOTablaHotel daoBebedor = new DAOTablaHotel();
		Hotel bebedor = null;
		try 
		{
			this.conn = darConexion();
			daoBebedor.setConn(conn);
			bebedor = daoBebedor.findHotelById(id);
			if(bebedor == null)
			{
				throw new Exception("El hotel con el id = " + id + " no se encuentra persistido en la base de datos.");				
			}
		} 
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoBebedor.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return bebedor;
	}
	public void borrarHotel(Long id) throws Exception {
		DAOTablaHotel darPreferencia = new DAOTablaHotel();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			darPreferencia.setConn(conn);
			darPreferencia.deleteHotel(id);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				darPreferencia.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public void borrarPersonaNatural(Long id) throws Exception {
		DAOTablaPersonaNatural darPreferencia = new DAOTablaPersonaNatural();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			darPreferencia.setConn(conn);
			darPreferencia.deletePersonaNatural(id);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				darPreferencia.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public PersonaNatural getPersonaNaturalById(Long id) throws Exception {
		DAOTablaPersonaNatural daoBebedor = new DAOTablaPersonaNatural();
		PersonaNatural bebedor = null;
		try 
		{
			this.conn = darConexion();
			daoBebedor.setConn(conn);
			bebedor = daoBebedor.findPersonaNaturalById(id);
			if(bebedor == null)
			{
				throw new Exception("La persona natural con el id  = " + id + " no se encuentra persistido en la base de datos.");				
			}
		} 
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoBebedor.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return bebedor;
	}
	public void registrarPersonaNatural(PersonaNatural personasNaturales) throws Exception {
		DAOTablaPersonaNatural daoHabitaciones = new DAOTablaPersonaNatural();
		try 
		{
			//////transaccion
			
			this.conn = darConexion();
			daoHabitaciones.setConn(conn);
			daoHabitaciones.registrarPersonaNatural(personasNaturales);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoHabitaciones.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public List<PersonaNatural> getAllPersonasNaturales() throws Exception {
		DAOTablaPersonaNatural daoOperador = new DAOTablaPersonaNatural();
		List<PersonaNatural> operadores;
		try 
		{
			this.conn = darConexion();
			daoOperador.setConn(conn);
			
			//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			operadores = daoOperador.getPersonaNaturals();
		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoOperador.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return operadores;
	}
	public void borrarViviendaUniversitaria(Long id) throws Exception {
		DAOTablaViviendaUniveristaria darPreferencia = new DAOTablaViviendaUniveristaria();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			darPreferencia.setConn(conn);
			darPreferencia.deleteViviendaUniversitaria(id);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				darPreferencia.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public ViviendaUniversitaria getViviendaUniversitariaById(Long id) throws Exception {
		DAOTablaViviendaUniveristaria daoBebedor = new DAOTablaViviendaUniveristaria();
		ViviendaUniversitaria bebedor = null;
		try 
		{
			this.conn = darConexion();
			daoBebedor.setConn(conn);
			bebedor = daoBebedor.findViviendaUniversitariaById(id);
			if(bebedor == null)
			{
				throw new Exception("La vivienda universitaria con el id = " + id + " no se encuentra persistido en la base de datos.");				
			}
		} 
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoBebedor.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return bebedor;
	}
	public void registrarViviendaUniversitaria(ViviendaUniversitaria viviendaUniversitaria) throws Exception {
		DAOTablaViviendaUniveristaria daoHabitaciones = new DAOTablaViviendaUniveristaria();
		try 
		{
			//////transaccion
			
			this.conn = darConexion();
			daoHabitaciones.setConn(conn);
			daoHabitaciones.registrarViviendaUniversitaria(viviendaUniversitaria);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoHabitaciones.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public List<ViviendaUniversitaria> getAllViviendaUniversitarias() throws Exception {
		DAOTablaViviendaUniveristaria daoOperador = new DAOTablaViviendaUniveristaria();
		List<ViviendaUniversitaria> operadores;
		try 
		{
			this.conn = darConexion();
			daoOperador.setConn(conn);
			
			//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			operadores = daoOperador.getViviendaUniversitarias();
		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoOperador.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return operadores;
	}
	public List<Vivienda> getAllViviendas() throws Exception {
		DAOTablaVivienda daoOperador = new DAOTablaVivienda();
		List<Vivienda> operadores;
		try 
		{
			this.conn = darConexion();
			daoOperador.setConn(conn);
			
			//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			operadores = daoOperador.getViviendas();
		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoOperador.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return operadores;
	}
	public void registrarVivienda(Vivienda vivienda) throws Exception {
		DAOTablaVivienda daoHabitaciones = new DAOTablaVivienda();
		try 
		{
			//////transaccion
			
			this.conn = darConexion();
			daoHabitaciones.setConn(conn);
			daoHabitaciones.registrarVivienda(vivienda);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoHabitaciones.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public void borrarVivienda(Long id) throws Exception {
		DAOTablaVivienda darPreferencia = new DAOTablaVivienda();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			darPreferencia.setConn(conn);
			darPreferencia.deleteVivienda(id);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				darPreferencia.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public Vivienda getViviendaById(Long id) throws Exception {
		DAOTablaVivienda daoBebedor = new DAOTablaVivienda();
		Vivienda bebedor = null;
		try 
		{
			this.conn = darConexion();
			daoBebedor.setConn(conn);
			bebedor = daoBebedor.findViviendaById(id);
			if(bebedor == null)
			{
				throw new Exception("La vivienda con el id = " + id + " no se encuentra persistido en la base de datos.");				
			}
		} 
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoBebedor.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return bebedor;
	}
	public void borrarCliente(Long id) throws Exception {
		DAOTablaCliente darPreferencia = new DAOTablaCliente();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			darPreferencia.setConn(conn);
			darPreferencia.deleteCliente(id);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				darPreferencia.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public Cliente getClienteById(Long id) throws Exception {
		DAOTablaCliente daoBebedor = new DAOTablaCliente();
		Cliente bebedor = null;
		try 
		{
			this.conn = darConexion();
			daoBebedor.setConn(conn);
			bebedor = daoBebedor.findClienteById(id);
			if(bebedor == null)
			{
				throw new Exception("La vivienda con el id = " + id + " no se encuentra persistido en la base de datos.");				
			}
		} 
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoBebedor.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return bebedor;
	}
	public void registrarCliente(Cliente cliente) throws Exception {
		DAOTablaCliente daoHabitaciones = new DAOTablaCliente();
		try 
		{
			//////transaccion
			
			this.conn = darConexion();
			daoHabitaciones.setConn(conn);
			daoHabitaciones.registrarCliente(cliente);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoHabitaciones.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public List<Cliente> getAllClientes() throws Exception {
		DAOTablaCliente daoOperador = new DAOTablaCliente();
		List<Cliente> operadores;
		try 
		{
			this.conn = darConexion();
			daoOperador.setConn(conn);
			
			//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			operadores = daoOperador.getClientes();

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoOperador.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return operadores;
	}
	public void borrarContrato(Long id) throws Exception {
		DAOTablaContrato darPreferencia = new DAOTablaContrato();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			darPreferencia.setConn(conn);
			darPreferencia.deleteContrato(id);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				darPreferencia.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public Contrato getContratoById(Long id) throws Exception {
		DAOTablaContrato daoBebedor = new DAOTablaContrato();
		Contrato bebedor = null;
		try 
		{
			this.conn = darConexion();
			daoBebedor.setConn(conn);
			bebedor = daoBebedor.findContratoById(id);
			if(bebedor == null)
			{
				throw new Exception("El contrato con el id = " + id + " no se encuentra persistido en la base de datos.");				
			}
		} 
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoBebedor.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return bebedor;
	}
	public void registrarContrato(Contrato contrato) throws Exception {
		DAOTablaContrato daoHabitaciones = new DAOTablaContrato();
		try 
		{
			//////transaccion
			
			this.conn = darConexion();
			daoHabitaciones.setConn(conn);
			daoHabitaciones.registrarContrato(contrato);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoHabitaciones.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public List<Contrato> getAllContratos() throws Exception {
		DAOTablaContrato daoOperador = new DAOTablaContrato();
		List<Contrato> operadores;
		try 
		{
			this.conn = darConexion();
			daoOperador.setConn(conn);
			
			//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			operadores = daoOperador.getContratos();

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoOperador.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return operadores;
	}
	
	public List<ReqI> getRequerimientoI() throws Exception {
		DAOReqI dao = new DAOReqI();
		System.out.println("se mete al metodo del tm");

		List<ReqI> req;
		try 
		{
			this.conn = darConexion();
			dao.setConn(conn);
			
			System.out.println("llama al req1 del dao");

			req = dao.getReqI();
		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				dao.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return req;
	}
	
	public List<ReqVII> getRequerimientoVII(Long num, String tipo) throws Exception {
		DAOReqVII dao = new DAOReqVII();
		List<ReqVII> req;
		try 
		{
			this.conn = darConexion();
			dao.setConn(conn);
			
			
			req = dao.getReqVII( num, tipo);
		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				dao.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return req;
	}
	public List<Oferta> getAllOfertas() throws Exception {
		DAOTablaOferta daoOperador = new DAOTablaOferta();
		List<Oferta> operadores;
		try 
		{
			this.conn = darConexion();
			daoOperador.setConn(conn);
			
			//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			operadores = daoOperador.getOfertas();
		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoOperador.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return operadores;
	}
	public void registrarOferta(Oferta oferta) throws Exception {
		DAOTablaOferta daoHabitaciones = new DAOTablaOferta();
		try 
		{
			//////transaccion
			
			this.conn = darConexion();
			daoHabitaciones.setConn(conn);
			daoHabitaciones.registrarOferta(oferta);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoHabitaciones.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public Oferta getOfertaById(Long id) throws Exception {
		DAOTablaOferta daoBebedor = new DAOTablaOferta();
		Oferta bebedor = null;
		try 
		{
			this.conn = darConexion();
			daoBebedor.setConn(conn);
			bebedor = daoBebedor.findOfertaById(id);
			if(bebedor == null)
			{
				throw new Exception("La oferta con el id = " + id + " no se encuentra persistido en la base de datos.");				
			}
		} 
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoBebedor.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return bebedor;
	}
	public void borrarOferta(Long id) throws Exception {
		DAOTablaOferta darPreferencia = new DAOTablaOferta();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			darPreferencia.setConn(conn);
			darPreferencia.deleteOferta(id);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				darPreferencia.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	public List<Oferta> getRequerimientoII() throws Exception {
		DAOTablaOferta dao = new DAOTablaOferta();
		List<Oferta> req;
		try 
		{
			this.conn = darConexion();
			dao.setConn(conn);
			
			
			req = dao.getReqII();
		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				dao.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return req;
	}
	public List<Oferta> getRequerimientoIX() throws Exception {
		DAOTablaOferta dao = new DAOTablaOferta();
		List<Oferta> req;
		try 
		{
			this.conn = darConexion();
			dao.setConn(conn);
			
			
			req = dao.getReqIX();
		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				dao.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return req;
	}
	public List<Cliente> getRequerimientoVIII(Long id, String tipo) throws Exception {
		DAOTablaCliente dao = new DAOTablaCliente();
		List<Cliente> req;
		try 
		{
			this.conn = darConexion();
			dao.setConn(conn);
			
			
			req = dao.getReqVIII(id, tipo);
		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				dao.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return req;
	}
	 public List<ReqV> getRequerimientoV() throws Exception {
			DAOReqV dao = new DAOReqV();
			List<ReqV> req;
			try 
			{
				this.conn = darConexion();
				dao.setConn(conn);
				
				
				req = dao.getReqV();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					dao.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return req;
		}
	 public List<ReqIII> getRequerimientoIII() throws Exception {
			DAOReqIII dao = new DAOReqIII();
			List<ReqIII> req;
			try 
			{
				this.conn = darConexion();
				dao.setConn(conn);
				
				
				req = dao.getReqIII();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					dao.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return req;
		}
	 public List<ReqXIII> getRequerimientoXIII() throws Exception {
			DAOReqXIII dao = new DAOReqXIII();
			List<ReqXIII> req;
			try 
			{
				this.conn = darConexion();
				dao.setConn(conn);				
				
				req = dao.getReqXIII();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					dao.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return req;
		}	 
	 public List<ReqXII> getRequerimientoXII() throws Exception {
			DAOReqXII dao = new DAOReqXII();
			List<ReqXII> req;
			try 
			{
				this.conn = darConexion();
				dao.setConn(conn);				
				
				req = dao.getReqXII();
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					dao.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return req;
		}
	 public List<ReqIV> getRequerimientoIV(Long [] id, String fi, String ff) throws Exception {
			DAOReqIV dao = new DAOReqIV();
			List<ReqIV> req;
			try 
			{
				this.conn = darConexion();
				dao.setConn(conn);
				
				
				req = dao.getReqIV(ff,fi,id);
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					dao.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return req;
		}
	 
	 public List<ReqX> getRequerimientoX(Long idOperador, Long idOferta, String fechaInicio, String fechaFinal , String[] order) throws Exception {
			DAOReqX dao = new DAOReqX();
			List<ReqX> req;
			try 
			{
				this.conn = darConexion();
				dao.setConn(conn);
				
				
				req = dao.getReqX(idOperador, idOferta, fechaInicio, fechaFinal,order);
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					dao.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return req;
		}
	 public List<ReqX> getRequerimientoXI(Long idOperador, Long idOferta, String fechaInicio, String fechaFinal , String[] order) throws Exception {
			DAOReqX dao = new DAOReqX();
			List<ReqX> req;
			try 
			{
				this.conn = darConexion();
				dao.setConn(conn);
				
				
				req = dao.getReqXI(idOperador, idOferta, fechaInicio, fechaFinal, order);
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					dao.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return req;
		}
	 
	 public List<ReqVI> getRequerimientoVI(Long id) throws Exception {
			DAOReqVI dao = new DAOReqVI();
			List<ReqVI> req;
			try 
			{
				this.conn = darConexion();
				dao.setConn(conn);
				
				
				req = dao.getReqVI(id);
			}
			catch (SQLException sqlException) {
				System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
				sqlException.printStackTrace();
				throw sqlException;
			} 
			catch (Exception exception) {
				System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			} 
			finally {
				try {
					dao.cerrarRecursos();
					if(this.conn!=null){
						this.conn.close();					
					}
				}
				catch (SQLException exception) {
					System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return req;
		}
	public void habilitarOferta(Oferta oferta) throws SQLException {
	
		DAOTablaOferta daoOferta = new DAOTablaOferta();
		try 
		{
			//////transaccion
			
			this.conn = darConexion();
			daoOferta.setConn(conn);
			daoOferta.habilitarOferta(oferta);;
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoOferta.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public void deshabilitarOferta(Oferta oferta) throws Exception {
		DAOTablaOferta daoOferta = new DAOTablaOferta();
		DAOTablaContrato daoTablaContrato= new DAOTablaContrato();
		try 
		{
			//////transaccion
			
			this.conn = darConexion();
			daoTablaContrato.setConn(conn);
			if (daoTablaContrato.existeContratoConOferta(oferta)) {
				daoTablaContrato.reAsignar(oferta);
			}
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoOferta.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		try 
		{
			//////transaccion
			
			this.conn = darConexion();
			daoOferta.setConn(conn);
			daoOferta.deshabilitarOferta(oferta);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoOferta.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}
	public ReservaColectiva hacerReservaColectivaHabitaciones(int j, ReservaColectiva reservaColectiva) throws Exception {
		
		List<Habitacion> habitaciones = this.getHabitacionesHabilitadas1();
		Long costo = reservaColectiva.getCosto();
		String descripcion = reservaColectiva.getDescripcion();
		String fechaFinal = reservaColectiva.getFechaFinal();
		String fechaInicial =reservaColectiva.getFechaInicial();
		System.out.println("llegue aca");
		List<Long> idClientes = reservaColectiva.getIdClientes();
		System.out.println("meti los clientes");
		Long idReservaColectiva = reservaColectiva.getIdReservaColectiva();
		Long noches = reservaColectiva.getNoches();
		
	
		DAOTablaContrato daoHabitaciones = new DAOTablaContrato();
		try 
		{
			//////transaccion
			
			this.conn = darConexion();
			daoHabitaciones.setConn(conn);
			for (int i = 1; (i < j)&&(i<idClientes.size()); i++) {
				Long idHabitacioni =habitaciones.get(i-1).getIdHabitacion();
				Long idCliente =  idClientes.get(i-1);
				Long idOperador = habitaciones.get(i-1).getIdOperador();
				Long variable = Long.parseLong(""+i);
				
				Contrato contrato = new Contrato(descripcion, fechaInicial, fechaFinal, idReservaColectiva + variable, noches, costo, null, idHabitacioni, idCliente, idOperador, null);
				daoHabitaciones.registrarContrato(contrato);
				System.out.println("se registro el contrato"+i);
			}
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoHabitaciones.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
		return reservaColectiva;
	}
	
	
	public ReservaColectiva hacerReservaColectivaApartamento(int var, ReservaColectiva reservaColectiva) throws Exception {
		// TODO Auto-generated method stub
		List<Apartamento> habitaciones = this.getApartamentosHabilitados();
		Long costo = reservaColectiva.getCosto();
		String descripcion = reservaColectiva.getDescripcion();
		String fechaFinal = reservaColectiva.getFechaFinal();
		String fechaInicial =reservaColectiva.getFechaInicial();
		System.out.println("llegue aca");
		List<Long> idClientes = reservaColectiva.getIdClientes();
		System.out.println("meti los clientes");
		Long idReservaColectiva = reservaColectiva.getIdReservaColectiva();
		Long noches = reservaColectiva.getNoches();
		
	
		DAOTablaContrato daoHabitaciones = new DAOTablaContrato();
		try 
		{
			//////transaccion
			
			this.conn = darConexion();
			daoHabitaciones.setConn(conn);
			for (int i = 1; (i < var)&&(i<idClientes.size()); i++) {
				Long idApto =habitaciones.get(i-1).getIdApartamento();
				Long idCliente =  idClientes.get(i-1);
				Long idOperador = habitaciones.get(i-1).getIdPersonaNatural();
				Long variable = Long.parseLong(""+i);
				
				Contrato contrato = new Contrato(descripcion, fechaInicial, fechaFinal, idReservaColectiva + variable, noches, costo, idApto, null, idCliente, idOperador, null);
				daoHabitaciones.registrarContrato(contrato);
				System.out.println("se registro el contrato"+i);
			}
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoHabitaciones.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
		return reservaColectiva;
	}
	public ReservaColectiva hacerReservaColectivaVivienda(int var, ReservaColectiva reservaColectiva) throws Exception {

		List<Vivienda> habitaciones = this.getViviendasHabilitadas();
		Long costo = reservaColectiva.getCosto();
		String descripcion = reservaColectiva.getDescripcion();
		String fechaFinal = reservaColectiva.getFechaFinal();
		String fechaInicial =reservaColectiva.getFechaInicial();
		System.out.println("llegue aca");
		List<Long> idClientes = reservaColectiva.getIdClientes();
		System.out.println("meti los clientes");
		Long idReservaColectiva = reservaColectiva.getIdReservaColectiva();
		Long noches = reservaColectiva.getNoches();
		
	
		DAOTablaContrato daoHabitaciones = new DAOTablaContrato();
		try 
		{
			//////transaccion
			
			this.conn = darConexion();
			daoHabitaciones.setConn(conn);
			for (int i = 1; (i < var)&&(i<idClientes.size()); i++) {
				Long idVivienda =habitaciones.get(i-1).getIdVivienda();
				Long idCliente =  idClientes.get(i-1);
				Long idOperador = habitaciones.get(i-1).getIdPersonaNatural();
				Long variable = Long.parseLong(""+i);
				Contrato contrato = new Contrato(descripcion, fechaInicial, fechaFinal, idReservaColectiva + variable, noches, costo, null, null, idCliente, idOperador, idVivienda);
				daoHabitaciones.registrarContrato(contrato);
				System.out.println("se registro el contrato"+i);
			}
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoHabitaciones.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
		return reservaColectiva;
	}
	
	public void borrarContratratosDesc(String descripcion) throws Exception {
		DAOTablaContrato darPreferencia = new DAOTablaContrato();
		try 
		{
			//////transaccion
			this.conn = darConexion();
			darPreferencia.setConn(conn);
			darPreferencia.deleteContratoDesc(descripcion);;
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				darPreferencia.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}
	public List<Habitacion> getHabitacionesHabilitadas() throws Exception {
		DAOTablaHabitacion daoHabitacion = new DAOTablaHabitacion();
		List<Habitacion> habitaciones;
		try 
		{
			this.conn = darConexion();
			daoHabitacion.setConn(conn);
			
			//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
			habitaciones = daoHabitacion.getHabitaciones();
		}
		
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoHabitacion.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		List<Habitacion> rta = new ArrayList();
		List<Oferta> ofertas = this.getAllOfertas();
		for (int i = 0; i < ofertas.size(); i++) {
			if (ofertas.get(i).getIdHabitacion()!=null && ofertas.get(i).getDisponible()== 1) {
				Long idHab = ofertas.get(i).getIdHabitacion();
				for (int j = 0; j < habitaciones.size(); j++) {
					if (idHab == habitaciones.get(j).getIdHabitacion()) {
						rta.add(habitaciones.get(j));
					}
				}
			}
			
		}
		return rta;
	}
	public List<Habitacion> getHabitacionesHabilitadas1() throws Exception {
		return this.getAllHabitaciones();
	}
	public List<Apartamento> getApartamentosHabilitados() throws Exception {
		return this.getAllApartamentos();
	}
	public List<Vivienda> getViviendasHabilitadas() throws Exception {
		return this.getAllViviendas();
	}
}
