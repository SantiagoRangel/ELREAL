package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.ReqV;

/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la 
 * aplicación
 * @author Grupo A - 16*/
public class DAOReqV{

	
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
	public DAOReqV() {
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
        
	public ArrayList<ReqV> getReqV() throws SQLException 
        {
                ArrayList<ReqV> resp = new ArrayList<ReqV>();
                
		String sql = String.format("SELECT HOTEL, HOSTAL, PERSONA, VIVIENDA, (HOTEL+HOSTAL+PERSONA+VIVIENDA) AS CLIENTE FROM (SELECT COUNT (IDHOTEL) AS HOTEL, COUNT (IDHOSTAL) AS HOSTAL, COUNT (IDPERSONANATURAL) AS PERSONA, COUNT (IDVIVIENDAUNIVERSITARIA) AS VIVIENDA FROM ((((CONTRATO LEFT OUTER JOIN HOTEL ON HOTEL.IDHOTEL = CONTRATO.IDOPERADOR) LEFT OUTER JOIN HOSTAL ON HOSTAL.IDHOSTAL = CONTRATO.IDOPERADOR) LEFT OUTER JOIN PERSONANATURAL ON PERSONANATURAL.IDPERSONANATURAL = CONTRATO.IDOPERADOR) LEFT OUTER JOIN VIVIENDAUNIVERSITARIA ON VIVIENDAUNIVERSITARIA.IDVIVIENDAUNIVERSITARIA = CONTRATO.IDOPERADOR));");

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
                while (rs.next()) {
			resp.add(convertResultSetToReqV(rs));
		}
		return resp;
                
	}

    public ReqV convertResultSetToReqV(ResultSet resultSet) throws SQLException {		
		
		Integer hotel = resultSet.getInt("HOTEL");
                Integer hostal = resultSet.getInt("HOSTAL");
                Integer vivienda = resultSet.getInt("VIVIENDA");                
		Integer persona = resultSet.getInt("PERSONA");
		Integer cliente = resultSet.getInt("CLIENTE");
	
	
		ReqV op = new ReqV(persona, vivienda, hotel, hostal, cliente);
		return op;
	}
}



