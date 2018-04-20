package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

import vos.Contrato;

public class DAOTablaContrato {

	
	

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
	public DAOTablaContrato() {
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
	 * Metodo que inicializa la connection del DAO a la base de datos con la conexión que entra como parametro.
	 * @param con - connection a la base de datos
	 */
	public void setConn(Connection con){
		this.conn = con;
	}


	public void registrarContrato(Contrato contrato) throws SQLException, Exception {

//		if( contrato.getDescripcion()== null || contrato.getCostoTotal() == null || contrato.getFechaFinal()== null
//				|| contrato.getFechaInicial()== null|| contrato.getIdContrato()== null|| contrato.getIdOperador()== null|| contrato.getIdCliente()== null)
//		{
//			throw new Exception("hay campos en null que no pueden ser null");
//		}
	
//		 DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//	        String dateInString = contrato.getFechaInicial();
//	        String dateFinString = contrato.getFechaFinal();
//	            Date dateInicial = formatter.parse(dateInString);
//	            Date dateFinal = formatter.parse(dateFinString);
//	            String fechainic = dateInicial.toString();
		String fechaInic= contrato.getFechaInicial();
		String fechaFinal= contrato.getFechaFinal();

		String[] arregloInicial = fechaInic.split("-");
		String[] arregloFinal = fechaFinal.split("-");
		System.out.println(fechaInic);
		@SuppressWarnings("deprecation")
		Date dateFinal = new Date(Integer.parseInt(arregloFinal[0]),Integer.parseInt(arregloFinal[1]), Integer.parseInt(arregloFinal[2]));
		@SuppressWarnings("deprecation")
		Date dateInicial = new Date(Integer.parseInt(arregloInicial[0]),Integer.parseInt(arregloInicial[1]), Integer.parseInt(arregloInicial[2]));
		String sql = String.format("INSERT INTO %1$s.CONTRATO (NOCHES,"
				+ " COSTO, DESCRIPCION, FECHAINIC, FECHAFINAL, IDCONTRATO, "
				+ "IDCLIENTE ,IDOPERADOR, IDAPARTAMENTO, IDHABITACION, IDVIVIENDA) VALUES (%2$s, %3$s, '%4$s','%5$s', '%6$s', %7$s, %8$s, %9$s, %10$s, %11$s, %12$s)", 
				USUARIO, 
				contrato.getNoches(),
				contrato.getCostoTotal(),
				contrato.getDescripcion(),
				contrato.getFechaInicial(),
				contrato.getFechaFinal(),
				contrato.getIdContrato(),
				contrato.getIdCliente(),
				contrato.getIdOperador(),
				contrato.getIdApartamento(),
				contrato.getIdHabitacion(),
				contrato.getIdVivienda()
				);

		if (findContratoById(contrato.getIdContrato())!=null) {
			throw new Exception("Ya existe el contrato");
		}
		else {
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		}
		
System.out.println(sql);


	}
	
	
	
	public ArrayList<Contrato> getContratos() throws SQLException, Exception {
		ArrayList<Contrato> contratos = new ArrayList<Contrato>();

		//Aclaracion: Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
		String sql = String.format("SELECT * FROM %1$s.CONTRATO WHERE ROWNUM <= 50", USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			contratos.add(convertResultSetToContrato(rs));
		}
		return contratos;
	}
	
	
	public Contrato findContratoById(Long id) throws SQLException, Exception 
	{
		Contrato contrato = null;

		String sql = String.format("SELECT * FROM %1$s.CONTRATO WHERE IDCONTRATO = %2$d", USUARIO, id); 
 
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			contrato = convertResultSetToContrato(rs);
		}

		return contrato;
	}	
	
	
//	public void updateContrato(Usuario usuario) throws SQLException, Exception {
//
//		StringBuilder sql = new StringBuilder();
//		sql.append(String.format("UPDATE %s.HABITACION SET ", USUARIO));
//		sql.append(String.format("UBICACION = '%1$s' AND DESCRIPCION = '%2$s' AND PRECIO = '%3$s'"
//				+ " AND IDHABITACION = '%4$s' AND TAMANO = '%5$s' AND IDOPERADOR = '%6$s' ", habitacion.getUbicacion(),
//		habitacion.getDescripcion(), habitacion.getPrecio(),habitacion.getIdHabitacion(),habitacion.getTamano(), habitacion.getIdOperador() ));
//		
//		System.out.println(sql);
//		
//		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
//		recursos.add(prepStmt);
//		prepStmt.executeQuery();
//	}

	public void deleteContrato(Long id) throws SQLException, Exception {

		String sql = String.format("DELETE FROM %1$s.CONTRATO WHERE IDCONTRATO = %2$d", USUARIO, id);
		
		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public Contrato convertResultSetToContrato(ResultSet resultSet) throws SQLException {
		
		
		Long noches = resultSet.getLong("NOCHES");
		Long idContrato = resultSet.getLong("IDCONTRATO");
		Long costo = resultSet.getLong("COSTO");
		Long idApartamento = resultSet.getLong("IDAPARTAMENTO");
		String descripcion = resultSet.getString("DESCRIPCION");
		Date fechaInicialDate = resultSet.getDate("FECHAINIC");
		String fechaInicial = fechaInicialDate.toString();
		Date fechaFinalDate = resultSet.getDate("FECHAFINAL");
		String fechaFinal = fechaFinalDate.toString();
		Long idHabitacion = resultSet.getLong("IDHABITACION");
		Long idCliente = resultSet.getLong("IDCLIENTE");
		Long idOperador = resultSet.getLong("IDOPERADOR");
		Long idVivienda = resultSet.getLong("IDVIVIENDA");
	
		Contrato contrato = new Contrato(descripcion, fechaInicial, fechaFinal, idContrato, noches, costo, idApartamento, idHabitacion, idCliente, idOperador,idVivienda);
		return contrato;
	}
	
	
}
