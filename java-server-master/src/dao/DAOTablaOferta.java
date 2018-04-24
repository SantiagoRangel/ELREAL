package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vos.Habitacion;
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
                        + "VALUES (%2$s, %3$s, %4$s, %5$s, '%6$s', '%7$s', %8$s, '%9$s', %10$s)", 
				USUARIO, 
				oferta.getIdVivienda(),
				oferta.getIdApartamento(),
				oferta.getIdHabitacion(),
				oferta.getIdOferta(),
				oferta.getFechaInicial(),
				oferta.getFechaFinal(),
				1,
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
	
	
	public void updateOferta(Oferta oferta) throws SQLException, Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(String.format("UPDATE %s.OFERTA SET ", USUARIO));
		sql.append(String.format("IDVIVIENDA = %1$s AND IDAPARTAMENTO = %2$s AND IDHABITACION = %3$s"
				+ " AND IDOFERTA = %4$s AND IDOPERADOR = %5$s AND FECHAINICIAL = '%6$s'AND FECHAFINAL = '%7$s' AND DISPONIBLE = %8$s AND DESCRIPCION = '%9$s' ", oferta.getIdVivienda(),
		oferta.getIdApartamento(), oferta.getIdHabitacion(),oferta.getIdOferta(),oferta.getFechaFinal(), oferta.getFechaInicial(), oferta.getDisponible(), oferta.getDescripcion() ));
		sql.append ("WHERE IDOFERTA = " + oferta.getIdOferta());
		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
	}

	public void habilitarOferta(Oferta oferta) throws SQLException
	{
//        String sqli = (String.format ("SET AUTOCOMMIT 0 SET TRANSACTION ISOLATION LEVEL SERIALIZABLE", USUARIO));
//		
//		PreparedStatement prepStmt = conn.prepareStatement(sqli.toString());
//		recursos.add(prepStmt);
//		prepStmt.executeQuery();
		
		StringBuilder sql1= new StringBuilder();
		sql1.append (String.format ("SET TRANSACTION ISOLATION LEVEL SERIALIZABLE"));
		System.out.println(sql1);

		StringBuilder sql2= new StringBuilder();
		sql2.append (String.format ("SET autocommit 0"));
		System.out.println(sql2);

		
		
		StringBuilder sql = new StringBuilder();
		sql.append (String.format ("SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;" +
									"SET autocommit 0;"+
									"UPDATE %s.OFERTA ", USUARIO));
		sql.append (String.format (
				"SET DISPONIBLE = %1$s",
				1));
		sql.append ("WHERE IDOFERTA = " + oferta.getIdOferta());
		System.out.println(sql);


		StringBuilder sql3= new StringBuilder();
		sql3.append (String.format ("commit"));
		System.out.println(sql3);

		PreparedStatement prepStmt1 = conn.prepareStatement(sql1.toString());
		recursos.add(prepStmt1);
		prepStmt1.executeQuery();
		prepStmt1.close();
//		PreparedStatement prepStmt2 = conn.prepareStatement(sql2.toString());
//		recursos.add(prepStmt2);
//		prepStmt2.executeQuery();
//		prepStmt2.close();
		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		prepStmt.close();
//
//		PreparedStatement prepStmt3 = conn.prepareStatement(sql3.toString());
//		recursos.add(prepStmt3);
//		prepStmt3.executeQuery();
//		prepStmt3.close();
//		
//		sqli = String.format("COMMIT"
//				,USUARIO);
//		
//		prepStmt = conn.prepareStatement(sqli);
//		recursos.add(prepStmt);
//		prepStmt.executeQuery();
	}
	
	public void deshabilitarOferta(Oferta oferta) throws SQLException
	{		
//		String sqli = (String.format ("SET AUTOCOMMIT 0 SET TRANSACTION ISOLATION LEVEL SERIALIZABLE", USUARIO));
//		
//		PreparedStatement prepStmt = conn.prepareStatement(sqli.toString());
//		recursos.add(prepStmt);
//		prepStmt.executeQuery();
		
		StringBuilder sql = new StringBuilder();
		sql.append (String.format ("UPDATE %s.OFERTA ", USUARIO));
		sql.append (String.format (
				"SET DISPONIBLE = %1$s",
				0));
		sql.append ("WHERE IDOFERTA = " + oferta.getIdOferta());
		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		prepStmt.close();
		
//		sqli = String.format("COMMIT"
//				,USUARIO);
//		
//		prepStmt = conn.prepareStatement(sqli);
//		recursos.add(prepStmt);
//		prepStmt.executeQuery();
		
	}
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
		String fechaFinal  = resultSet.getString("FECHAFINAL");
		Long disponible  = resultSet.getLong("DISPONIBLE");
		String descripcion  = resultSet.getString("DESCRIPCION");


		Oferta oferta = new Oferta(idVivienda, idApartamento, idHabitacion, idOferta, fechaInicial, fechaFinal, disponible, descripcion, idOperador);
		return oferta;
	}	
	
	
	public ArrayList<Oferta> getReqII() throws SQLException, Exception {
		ArrayList<Oferta> ofertas = new ArrayList<Oferta>();
		
		String sql = String.format("SELECT * FROM(SELECT IDA, COUNT(IDA) AS CUENTA FROM (OPERADOR INNER JOIN (SELECT NVL (IDHABITACION,0) + NVL (IDAPARTAMENTO, 0) + NVL (IDVIVIENDA,0) AS IDA, IDCONTRATO, COSTO, FECHAFINAL, FECHAINIC, NOCHES, DESCRIPCION, IDOPERADOR, IDCLIENTE  FROM CONTRATO) ON IDA = OPERADOR.IDOPERADOR) INNER JOIN (SELECT NVL (IDHABITACION,0) + NVL (IDAPARTAMENTO, 0) + NVL (IDVIVIENDA,0) AS IDB, IDOFERTA, FECHAINICIAL, FECHAFINAL, IDOPERADOR FROM OFERTA) ON IDB = OPERADOR.IDOPERADOR AND IDA = IDB GROUP BY IDA ORDER BY CUENTA) WHERE ROWNUM <= 20");

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

        /*String sql = (String.format ("SET AUTOCOMMIT 0 SET TRANSACTION ISOLATION LEVEL SERIALIZABLE", USUARIO));
		
		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		prepStmt.executeQuery();*/
		
		String sql = String.format("SELECT * FROM OFERTA WHERE IDOFERTA NOT IN (SELECT IDOFERTA FROM (SELECT NVL(IDVIVIENDA, 0) AS IDVII, NVL(IDHABITACION, 0) AS IDHII, NVL(IDAPARTAMENTO, 0) AS IDAII, IDOFERTA FROM OFERTA WHERE TO_DATE(FECHAFINAL, 'YYYY-MM-DD\"T\"HH24:MI:SS\"Z\"')>= SYSDATE AND TO_DATE(FECHAINICIAL, 'YYYY-MM-DD\"T\"HH24:MI:SS\"Z\"')< ADD_MONTHS(SYSDATE, -1)) INNER JOIN (SELECT NVL(IDVIVIENDA, 0) AS IDVI, NVL(IDHABITACION, 0) AS IDHI, NVL(IDAPARTAMENTO, 0) AS IDAI FROM CONTRATO WHERE TO_DATE(FECHAFINAL, 'YYYY-MM-DD\"T\"HH24:MI:SS\"Z\"')>= ADD_MONTHS(SYSDATE, -1)) ON IDVII = IDVI AND IDHII = IDHI AND IDAII = IDAI) AND IDOFERTA IN (SELECT IDOFERTA FROM OFERTA WHERE TO_DATE(FECHAFINAL, 'YYYY-MM-DD\"T\"HH24:MI:SS\"Z\"')>= SYSDATE AND TO_DATE(FECHAINICIAL, 'YYYY-MM-DD\"T\"HH24:MI:SS\"Z\"')< ADD_MONTHS(SYSDATE, -1))"
				,USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			ofertas.add(convertResultSetToOferta(rs));
		}
		
		/*sql = String.format("COMMIT"
				,USUARIO);
		
		prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();*/
		
		return ofertas;
	}
	
	public List getHabitacionesHabilitadas()
	{
		List<Habitacion> habitaciones= new ArrayList<Habitacion>();		
		
		return habitaciones;		
	}
}
