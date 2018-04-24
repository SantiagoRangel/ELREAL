package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.ReqI;
import vos.ReqIII;

/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la 
 * aplicación
 * @author Grupo A - 16*/
public class DAOReqIII{

	
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
	public DAOReqIII() {
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
        
	public ArrayList<ReqIII> getReqIII() throws SQLException 
        {
                ArrayList<ReqIII> resp = new ArrayList<ReqIII>();
                
		String sql = String.format("SELECT SUM(NOCHES/TIEMPO) AS INDICE, IDOFERTA FROM (SELECT TIEMPO, IDOFERTA, NOCHES FROM ((SELECT (TO_DATE(OFERTA.FECHAFINAL,  'YYYY-MM-DD HH24:MI:SS') - TO_DATE(OFERTA.FECHAINICIAL,  'YYYY-MM-DD HH24:MI:SS')) AS TIEMPO, IDOFERTA, IDHABITACION AS HAB, IDAPARTAMENTO AS APT, IDVIVIENDA AS VIV FROM OFERTA)INNER JOIN CONTRATO ON NVL(HAB,0) = NVL(CONTRATO.IDHABITACION,0) AND NVL(APT,0) = NVL(CONTRATO.IDAPARTAMENTO,0) AND NVL(VIV,0) = NVL(CONTRATO.IDVIVIENDA,0))) GROUP BY IDOFERTA"
				, USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
                while (rs.next()) {
			resp.add(convertResultSetToReqIII(rs));
		}
		return resp;
                
	}

    public ReqIII convertResultSetToReqIII(ResultSet resultSet) throws SQLException {		
		
		Long idOferta = resultSet.getLong("IDOFERTA");
		Double indice = resultSet.getDouble("INDICE");
	
	
		ReqIII op = new ReqIII(indice, idOferta);
		return op;
	}
}

