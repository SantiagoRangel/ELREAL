package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.ReqI;

/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la 
 * aplicación
 * @author Grupo A - 16*/
public class DAOReqI{

	
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
	public DAOReqI() {
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
        
	public ArrayList<ReqI> getReqI() throws SQLException 
        {
                ArrayList<ReqI> resp = new ArrayList<ReqI>();
                
		String sql = String.format("SELECT SUM(CONTRATO.COSTO) AS INGRESO, OPERADOR.IDOPERADOR AS PROVEEDOR FROM %1$s.CONTRATO INNER JOIN OPERADOR ON CONTRATO.IDOPERADOR = OPERADOR.IDOPERADOR WHERE TO_DATE(CONTRATO.FECHAINIC,'YYYY-MM-DD') BETWEEN ADD_MONTHS(TRUNC(SYSDATE),-12) AND SYSDATE GROUP BY OPERADOR.IDOPERADOR;"
				, USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
                while (rs.next()) {
			resp.add(convertResultSetToReqI(rs));
		}
		return resp;
                
	}

    public ReqI convertResultSetToReqI(ResultSet resultSet) throws SQLException {		
		
		String proveedor = resultSet.getString("PROVEEDOR");
		Double ingresos = resultSet.getDouble("INGRESOS");
	
	
		ReqI op = new ReqI(proveedor, ingresos);
		return op;
	}
}

