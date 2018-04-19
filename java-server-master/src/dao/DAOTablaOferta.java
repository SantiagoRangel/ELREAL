package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import vos.Oferta;

public class DAOTablaOferta {

	
	

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
	public DAOTablaOferta() {
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


	public void registrarOferta(Oferta oferta) throws SQLException, Exception {

//		if(oferta.getFechaFinal()== null|| oferta.getFechaInicial()== null|| 
//                        oferta.getIdOferta()== null)
//		{
//			throw new Exception("hay campos en null que no pueden ser null");
//		}
//		
		String sql = String.format("INSERT INTO %1$s.OFERTA (IDVIVIENDA, IDAPARTAMENTO, IDHABITACION, IDOFERTA, FECHAINICIAL, FECHAFINAL, DISPONIBLE, DESCRIPCION, IDOPERADOR) "
                        + "VALUES (%2$s, %3$s, %4$s, %5$s, '%6$s', '%7$s', %8$s, %9$s, %10$s)", 
				USUARIO, 
				oferta.getIdVivienda(),
				oferta.getIdApartamento(),
				oferta.getIdHabitacion(),
				oferta.getIdOferta(),
				oferta.getFechaInicial(),
				oferta.getFechaFinal(),
				oferta.getDisponible(),
				oferta.getDescripcion(),
				oferta.getIdOperador()
				);

		if (findOfertaById(oferta.getIdOferta())!=null) {
			throw new Exception("Ya existe el oferta");
		}
		else {
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		}
		
System.out.println(sql);


	}
	
	
	
	public ArrayList<Oferta> getOfertas() throws SQLException, Exception {
		ArrayList<Oferta> ofertas = new ArrayList<Oferta>();

		//Aclaracion: Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
		String sql = String.format("SELECT * FROM %1$s.OFERTA WHERE ROWNUM <= 50", USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			ofertas.add(convertResultSetToOferta(rs));
		}
		return ofertas;
	}
	
	
	public Oferta findOfertaById(Long id) throws SQLException, Exception 
	{
		Oferta oferta = null;

		String sql = String.format("SELECT * FROM %1$s.OFERTA WHERE IDOFERTA = %2$d", USUARIO, id); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			oferta = convertResultSetToOferta(rs);
		}

		return oferta;
	}	
	
	
//	public void updateOferta(Usuario usuario) throws SQLException, Exception {
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

	public void deleteOferta(Long id) throws SQLException, Exception {

		String sql = String.format("DELETE FROM %1$s.OFERTA WHERE IDOFERTA = %2$d", USUARIO, id);

		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public Oferta convertResultSetToOferta(ResultSet resultSet) throws SQLException {		
		
		Long idVivienda = resultSet.getLong("IDVIVIENDA");
		Long idApartamento = resultSet.getLong("IDAPARTAMENTO");
		Long idHabitacion = resultSet.getLong("IDHABITACION");
		Long idOferta = resultSet.getLong("IDOFERTA");
		Long idOperador = resultSet.getLong("IDOPERADOR");
		String fechaInicial  = resultSet.getString("FECHAINICIAL");
		Long disponible  = resultSet.getLong("DISPONIBLE");
		String descripcion  = resultSet.getString("DESCRIPCION");
		String fechaFinal  = resultSet.getString("FECHAFINAL");


		Oferta oferta = new Oferta(idVivienda, idApartamento, idHabitacion, idOferta, fechaInicial, fechaFinal, disponible, descripcion, idOperador);
		return oferta;
	}	
	
	
	public ArrayList<Oferta> getReqII() throws SQLException, Exception {
		ArrayList<Oferta> ofertas = new ArrayList<Oferta>();
		
		String sql = String.format("SELECT *" + 
				"FROM(SELECT IDA, COUNT(IDA) AS CUENTA" + 
				"FROM (OPERADOR INNER JOIN" + 
				"(SELECT NVL (IDHABITACION,0) + NVL (IDAPARTAMENTO, 0) + NVL (IDVIVIENDA,0) AS IDA, IDCONTRATO, COSTO, FECHAFINAL, FECHAINIC, NOCHES, DESCRIPCION, IDOPERADOR, IDCLIENTE" + 
				"FROM CONTRATO) ON IDA = OPERADOR.IDOPERADOR)" + 
				"INNER JOIN (SELECT NVL (IDHABITACION,0) + NVL (IDAPARTAMENTO, 0) + NVL (IDVIVIENDA,0) AS IDB, IDOFERTA, FECHAINICIAL, FECHAFINAL, IDOPERADOR" + 
				"FROM OFERTA) ON IDB = OPERADOR.IDOPERADOR AND IDA = IDB " + 
				"GROUP BY IDA" + 
				"ORDER BY CUENTA) " + 
				"WHERE ROWNUM <= 20");

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			ofertas.add(convertResultSetToOferta(rs));
		}
		return ofertas;
	}
	
	public ArrayList<Oferta> getReqIX() throws SQLException, Exception {
		ArrayList<Oferta> ofertas = new ArrayList<Oferta>();
		
		String sql = String.format("SELECT * FROM OFERTA WHERE IDOFERTA NOT IN (SELECT IDOFERTA FROM (SELECT NVL(IDVIVIENDA, 0) AS IDVII, NVL(IDHABITACION, 0) AS IDHII, NVL(IDAPARTAMENTO, 0) AS IDAII, IDOFERTA FROM OFERTA WHERE TO_DATE(FECHAFINAL, 'YYYY-MM-DD\"T\"HH24:MI:SS\"Z\"')>= SYSDATE AND TO_DATE(FECHAINICIAL, 'YYYY-MM-DD\"T\"HH24:MI:SS\"Z\"')< ADD_MONTHS(SYSDATE, -1)) INNER JOIN (SELECT NVL(IDVIVIENDA, 0) AS IDVI, NVL(IDHABITACION, 0) AS IDHI, NVL(IDAPARTAMENTO, 0) AS IDAI FROM CONTRATO WHERE TO_DATE(FECHAFINAL, 'YYYY-MM-DD\"T\"HH24:MI:SS\"Z\"')>= ADD_MONTHS(SYSDATE, -1)) ON IDVII = IDVI AND IDHII = IDHI AND IDAII = IDAI) AND IDOFERTA IN (SELECT IDOFERTA FROM OFERTA WHERE TO_DATE(FECHAFINAL, 'YYYY-MM-DD\"T\"HH24:MI:SS\"Z\"')>= SYSDATE AND TO_DATE(FECHAINICIAL, 'YYYY-MM-DD\"T\"HH24:MI:SS\"Z\"')< ADD_MONTHS(SYSDATE, -1))"
				,USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			ofertas.add(convertResultSetToOferta(rs));
		}
		return ofertas;
	}
}
