package tm;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import dao.DAOTablaApartamento;
import dao.DAOTablaHabitacion;
import dao.DAOTablaOperador;
import vos.Apartamento;
import vos.Habitacion;
import vos.Operador;

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
		return DriverManager.getConnection(url, user, password);
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
	public Operador getOperadortoById(Long id) throws Exception {
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
	
	
	
	
}
