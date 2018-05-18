package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.ReqI;
import vos.ReqVII;

/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la 
 * aplicación
 * @author Grupo A - 16*/
public class DAOReqVII{

	
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
	public DAOReqVII() {
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
        
	public ArrayList<ReqVII> getReqVII(Long dias, String tipo) throws SQLException 
        {
       ArrayList<ReqVII> resp = new ArrayList<ReqVII>();    
                
	   String sql2 = String.format("SELECT SYSDATE-(FECHA*%2$d) AS TERMINA, SYSDATE-((FECHA*%2$d)+%2$d) AS EMPIEZA FROM (SELECT * FROM ((SELECT SEGMENTO AS FECHA FROM (SELECT SUM(COSTO) AS CUENTAIII, SEGMENTO FROM (SELECT SEGMENTO, IDCONTRATO, COSTO FROM (SELECT ROWNUM AS SEGMENTO FROM DUAL CONNECT BY ROWNUM <= (SELECT TRUNC(ANTIGUEDAD/%2$d) FROM (SELECT TRUNC(SYSDATE-TO_DATE(FECHAINIC, 'YYYY-MM-DD'),0) AS ANTIGUEDAD FROM CONTRATO ORDER BY ANTIGUEDAD DESC) WHERE ROWNUM = 1)) INNER JOIN (SELECT TRUNC(((SYSDATE-TO_DATE(FECHAINIC, 'YYYY-MM-DD'))/%2$d),0) AS FIC, TRUNC(((SYSDATE-TO_DATE(FECHAFINAL, 'YYYY-MM-DD'))/%2$d),0) AS FFC, IDCONTRATO, COSTO FROM CONTRATO WHERE ID%3$s IS NOT NULL) ON FIC>= SEGMENTO AND FFC <= SEGMENTO) GROUP BY SEGMENTO ORDER BY CUENTAIII DESC) WHERE ROWNUM = 1) UNION ALL (SELECT SEGMENTO AS SEGMENTOMEJOR FROM (SELECT COUNT(IDCONTRATO) AS CUENTAI, SEGMENTO FROM (SELECT SEGMENTO, IDCONTRATO FROM (SELECT ROWNUM AS SEGMENTO FROM DUAL CONNECT BY ROWNUM <= (SELECT TRUNC(ANTIGUEDAD/%2$d) FROM (SELECT TRUNC(SYSDATE-TO_DATE(FECHAINIC, 'YYYY-MM-DD'),0) AS ANTIGUEDAD FROM CONTRATO ORDER BY ANTIGUEDAD DESC) WHERE ROWNUM = 1)) INNER JOIN (SELECT TRUNC(((SYSDATE-TO_DATE(FECHAINIC, 'YYYY-MM-DD'))/%2$d),0) AS FIC, TRUNC(((SYSDATE-TO_DATE(FECHAFINAL, 'YYYY-MM-DD'))/%2$d),0) AS FFC, IDCONTRATO FROM CONTRATO WHERE ID%3$s IS NOT NULL) ON FIC>= SEGMENTO AND FFC <= SEGMENTO) GROUP BY SEGMENTO ORDER BY CUENTAI DESC) WHERE ROWNUM = 1)) UNION ALL (SELECT SEGMENTO AS SEGMENTOPEOR FROM (SELECT COUNT(IDCONTRATO) AS CUENTAII, SEGMENTO FROM (SELECT SEGMENTO, IDCONTRATO FROM (SELECT ROWNUM AS SEGMENTO FROM DUAL CONNECT BY ROWNUM <= (SELECT TRUNC(ANTIGUEDAD/%2$d) FROM (SELECT TRUNC(SYSDATE-TO_DATE(FECHAINIC, 'YYYY-MM-DD'),0) AS ANTIGUEDAD FROM CONTRATO ORDER BY ANTIGUEDAD DESC) WHERE ROWNUM = 1)) INNER JOIN (SELECT TRUNC(((SYSDATE-TO_DATE(FECHAINIC, 'YYYY-MM-DD'))/%2$d),0) AS FIC, TRUNC(((SYSDATE-TO_DATE(FECHAFINAL, 'YYYY-MM-DD'))/%2$d),0) AS FFC, IDCONTRATO FROM CONTRATO WHERE ID%3$s IS NOT NULL) ON FIC>= SEGMENTO AND FFC <= SEGMENTO) GROUP BY SEGMENTO ORDER BY CUENTAII ASC) WHERE ROWNUM = 1))"
	    ,USUARIO, dias, tipo);

	   PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
		recursos.add(prepStmt2);
		ResultSet rs2 = prepStmt2.executeQuery();
        
		while (rs2.next()) {
			resp.add(convertResultSetToReqVII(rs2));
		}
		return resp; 
		
	}
	public void transaccion() throws SQLException
	{
		 String sql = String.format("SET AUTOCOMMIT 0 SET TRANSACTION ISOLATION LEVEL SERIALIZABLE",USUARIO);
			
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();      
	                
	}

    public ReqVII convertResultSetToReqVII(ResultSet resultSet) throws SQLException {		
		
		Date empieza = resultSet.getDate("EMPIEZA");
		Date termina= resultSet.getDate("TERMINA");	
	
		ReqVII op = new ReqVII(empieza, termina);
		return op;
	}
}