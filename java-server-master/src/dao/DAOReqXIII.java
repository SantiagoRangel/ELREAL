package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Cliente;
import vos.Oferta;
import vos.ReqXIII;

public class DAOReqXIII {
	
	public final static String USUARIO = "ISIS2304A241810";
	/**
	 * Arraylist de recursos que se usan para la ejecución de sentencias SQL
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
	public DAOReqXIII() {
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
	
	public ReqXIII convertResultSetToReqXIII(ResultSet resultSet) throws SQLException {		
		
		String contrasena = resultSet.getString("CONTRASENA");
		Long idUsuario = resultSet.getLong("IDCLIENTE");
		String usuario = resultSet.getString("USUARIO");
		String nombre = resultSet.getString("NOMBRE");	
		String descripcion = resultSet.getString("DESCRIPCION");
	
		ReqXIII cliente = new ReqXIII(usuario, contrasena, idUsuario, nombre, descripcion);
		return cliente;
	}	
	public ArrayList<ReqXIII> getReqXIII() throws SQLException, Exception 
	{
		ArrayList<ReqXIII> ofertas = new ArrayList<ReqXIII>();
		
		String sql = String.format("SELECT * FROM (SELECT IDCLIENTE AS CLI, DESCRIPCION FROM (SELECT * FROM ((SELECT IDCLIENTE, 'Siempre gasta la oligarca!' AS DESCRIPCION FROM (SELECT CONTRATO.IDCLIENTE FROM CLIENTE INNER JOIN CONTRATO ON CLIENTE.IDCLIENTE = CONTRATO.IDCLIENTE WHERE COSTO >= 150) WHERE IDCLIENTE NOT IN (SELECT CLIENTE.IDCLIENTE FROM CLIENTE INNER JOIN CONTRATO ON CLIENTE.IDCLIENTE = CONTRATO.IDCLIENTE WHERE COSTO < 150)) UNION ALL (SELECT IDCLIENTE, 'Siempre Reserva Suites!' AS DESCRIPCION FROM(SELECT CONTRATO.IDCLIENTE FROM CLIENTE INNER JOIN CONTRATO ON CLIENTE.IDCLIENTE = CONTRATO.IDCLIENTE WHERE CONTRATO.DESCRIPCION >= 'SUITE') WHERE IDCLIENTE NOT IN (SELECT CLIENTE.IDCLIENTE FROM CLIENTE INNER JOIN CONTRATO ON CLIENTE.IDCLIENTE = CONTRATO.IDCLIENTE WHERE CONTRATO.DESCRIPCION != 'SUITE'))) UNION ALL (SELECT IDCLIENTE, 'Reservo una vez al mes!' AS DESCRIPCION FROM (SELECT COUNT(DISTINCT RESERVA) AS CUENTAR, IDCLIENTE FROM (SELECT IDCLIENTE, TRUNC((SYSDATE - TO_DATE(FECHAINIC, 'YYYY-MM-DD'))/30,0) AS RESERVA FROM CONTRATO) GROUP BY IDCLIENTE) WHERE CUENTAR >= (SELECT COUNT(*) FROM (SELECT SEGMENTO FROM (SELECT TRUNC (((SYSDATE-DIA)/30),0) AS SEGMENTO FROM (SELECT TRUNC (SYSDATE - ROWNUM) AS DIA FROM DUAL CONNECT BY ROWNUM <= (SELECT * FROM (SELECT TRUNC((SYSDATE - TO_DATE(FECHAINIC, 'YYYY-MM-DD')),0) AS ANTIGUEDAD FROM CONTRATO ORDER BY ANTIGUEDAD DESC) WHERE ROWNUM = 1))) GROUP BY SEGMENTO ORDER BY SEGMENTO ASC))))) INNER JOIN CLIENTE ON CLIENTE.IDCLIENTE = CLI"
				,USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			ofertas.add(convertResultSetToReqXIII(rs));
		}
				
		return ofertas;
	}
}
