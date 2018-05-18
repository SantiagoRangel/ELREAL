package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.ReqV;
import vos.ReqVI;

/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la 
 * aplicación
 * @author Grupo A - 16*/
public class DAOReqVI{

	
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
	public DAOReqVI() {
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
        
	public ArrayList<ReqVI> getReqVI(Long id) throws SQLException 
        {
                ArrayList<ReqVI> resp = new ArrayList<ReqVI>();
                
		String sql = String.format("SELECT NOCHES, TOTAL, HABITA, APTO, VIVIEN FROM((SELECT SUM (TIEMPO) AS NOCHES, SUM (MONTO) AS TOTAL, CLI AS USUARIOI, COUNT(HAB) AS HABITA, COUNT(APT) APTO, COUNT(VIV) VIVIEN FROM (SELECT MONTO, TIEMPO, CONTRA, CLI, HAB, VIV, APT FROM ((SELECT IDCLIENTE AS CRI FROM CLIENTE) INNER JOIN (SELECT COSTO AS MONTO, ((TO_DATE(FECHAFINAL,  'YYYY-MM-DD HH24:MI:SS'))-(TO_DATE(FECHAINIC,  'YYYY-MM-DD HH24:MI:SS'))) AS TIEMPO, NOCHES AS NAKT, IDCONTRATO AS CONTRA, IDCLIENTE AS CLI, IDHABITACION AS HAB, IDVIVIENDA AS VIV, IDAPARTAMENTO AS APT FROM CONTRATO) ON CRI = CLI)) GROUP BY CLI) UNION ALL (SELECT SUM (TIEMPO) AS NOCHES, SUM (MONTO), OPE AS USUARIOII, COUNT(HAB), COUNT(APT), COUNT(VIV) FROM (SELECT MONTO, TIEMPO, CONTRA, OPE, HAB, VIV, APT FROM ((SELECT IDOPERADOR AS OP FROM OPERADOR) INNER JOIN (SELECT COSTO AS MONTO, ((TO_DATE(FECHAFINAL,  'YYYY-MM-DD HH24:MI:SS'))-(TO_DATE(FECHAINIC,  'YYYY-MM-DD HH24:MI:SS'))) AS TIEMPO, NOCHES AS NAKT, IDCONTRATO AS CONTRA, IDOPERADOR AS OPE, IDHABITACION AS HAB, IDVIVIENDA AS VIV, IDAPARTAMENTO AS APT FROM CONTRATO) ON OPE = OP)) GROUP BY OPE)) WHERE USUARIOI = %2$d"
				,USUARIO,id);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
                while (rs.next()) {
			resp.add(convertResultSetToReqVI(rs));
		}
		return resp;
                
	}

    public ReqVI convertResultSetToReqVI(ResultSet resultSet) throws SQLException {		
		
    	Integer apto = resultSet.getInt("APTO");
    	Integer vivien = resultSet.getInt("VIVIEN");
    	Integer habita = resultSet.getInt("HABITA");                       
		Double total = resultSet.getDouble("TOTAL");
		Integer noches = resultSet.getInt("NOCHES");
	
	
		ReqVI op = new ReqVI(habita, apto, vivien, total, noches);
		return op;
	}
}



