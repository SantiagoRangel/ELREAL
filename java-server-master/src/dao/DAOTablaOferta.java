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
		
//		StringBuilder sql1= new StringBuilder();
//		sql1.append (String.format ("SET TRANSACTION ISOLATION LEVEL SERIALIZABLE"));
//		System.out.println(sql1);
//
//		StringBuilder sql2= new StringBuilder();
//		sql2.append (String.format ("SET autocommit 0"));
//		System.out.println(sql2);
//
//		
		
		StringBuilder sql = new StringBuilder();
		sql.append (String.format (
									"UPDATE %s.OFERTA ", USUARIO));
		sql.append (String.format (
				"SET DISPONIBLE = %1$s",
				1));
		sql.append ("WHERE IDOFERTA = " + oferta.getIdOferta());
		System.out.println(sql);


//		StringBuilder sql3= new StringBuilder();
//		sql3.append (String.format ("commit"));
//		System.out.println(sql3);

		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		prepStmt.close();
//		PreparedStatement prepStmt2 = conn.prepareStatement(sql2.toString());
//		recursos.add(prepStmt2);
//		prepStmt2.executeQuery();
//		prepStmt2.close();

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
		
		String sql = String.format("SELECT IDHABITACION, IDAPARTAMENTO, IDVIVIENDA, DISPONIBLE, DESCRIPCION, FECHAINICIAL, FECHAFINAL, IDOPERADOR, IDOFERTA FROM (SELECT * FROM OFERTA INNER JOIN (SELECT IDOFERTA AS IDO FROM (SELECT NVL(INDIC,0) AS INDICE, IDOFERTA FROM (SELECT SUM(NOCHES/TIEMPO) AS INDIC, IDOFERTA FROM (SELECT TIEMPO, IDOFERTA, NOCHES FROM ((SELECT (TO_DATE(OFERTA.FECHAFINAL,  'YYYY-MM-DD HH24:MI:SS') - TO_DATE(OFERTA.FECHAINICIAL,  'YYYY-MM-DD HH24:MI:SS')) AS TIEMPO, IDOFERTA, IDHABITACION AS HAB, IDAPARTAMENTO AS APT, IDVIVIENDA AS VIV FROM OFERTA)LEFT OUTER JOIN CONTRATO ON NVL(HAB,0) = NVL(CONTRATO.IDHABITACION,0) AND NVL(APT,0) = NVL(CONTRATO.IDAPARTAMENTO,0) AND NVL(VIV,0) = NVL(CONTRATO.IDVIVIENDA,0))) GROUP BY IDOFERTA ORDER BY INDIC DESC)) WHERE ROWNUM <= 20 ORDER BY INDICE DESC) ON IDO = OFERTA.IDOFERTA)");

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
		
		String sql = String.format("SELECT * FROM OFERTA WHERE OFERTA.IDOFERTA IN (SELECT IDOFERTA FROM (SELECT (TIEMPOO-LASUMA) AS DESO, IDOFERTA FROM (SELECT SUM(TIEMPOC) AS LASUMA, TIEMPOO, IDOFERTA FROM (SELECT (FIOM-FFOM) AS TIEMPOO, (FICM-FFCM) AS TIEMPOC, IDOFERTA FROM (SELECT TRUNC(SYSDATE - TO_DATE(FIO, 'YYYY-MM-DD'),0) AS FIOM, TRUNC(SYSDATE - TO_DATE(FFO, 'YYYY-MM-DD'),0) AS FFOM, TRUNC(SYSDATE - TO_DATE(FFC, 'YYYY-MM-DD'),0) AS FFCM, TRUNC(SYSDATE - TO_DATE(FIC, 'YYYY-MM-DD'),0) AS FICM, IDOFERTA FROM (SELECT FIO, FFO, IDOFERTA, FFC, FIC FROM (SELECT IDHABITACION AS IDHO, IDAPARTAMENTO AS IDAO, IDVIVIENDA AS IDVO, FECHAINICIAL AS FIO, FECHAFINAL AS FFO, IDOFERTA FROM OFERTA) INNER JOIN (SELECT FECHAFINAL AS FFC, FECHAINIC AS FIC, IDHABITACION AS IDHC, IDAPARTAMENTO AS IDAC, IDVIVIENDA AS IDVC FROM CONTRATO) ON NVL(IDHO,0) = NVL(IDHC,0) AND NVL(IDAO,0) = NVL(IDAC,0) AND NVL(IDVO,0) = NVL(IDVC,0) AND TO_DATE(FIC, 'YYYY-MM-DD') - TO_DATE(FIO, 'YYYY-MM-DD') >= 0 AND TO_DATE(FFC, 'YYYY-MM-DD') - TO_DATE(FFO, 'YYYY-MM-DD') <= 0))) GROUP BY IDOFERTA, TIEMPOO)) WHERE DESO > 30)"
				,USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			ofertas.add(convertResultSetToOferta(rs));
		}
		return ofertas;
	}
	
	public List getHabitacionesHabilitadas()
	{
		List<Habitacion> habitaciones= new ArrayList<Habitacion>();		
		
		return habitaciones;		
	}
}
