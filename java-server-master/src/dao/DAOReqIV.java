package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.ReqI;
import vos.ReqIII;
import vos.ReqIV;

/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la 
 * aplicación
 * @author Grupo A - 16*/
public class DAOReqIV{

	
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
	public DAOReqIV() {
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
        
	public ArrayList<ReqIV> getReqIV(String fechaFinal, String fechaInicial, Long servicio) throws SQLException 
        {
                ArrayList<ReqIV> resp = new ArrayList<ReqIV>();
                
		String sql = String.format("SELECT HAB, APT, VIV FROM (SELECT HAB, APT, VIV, ((TO_DATE('%2$s',  'YYYY-MM-DD HH24:MI:SS'))-(TO_DATE(FF,  'YYYY-MM-DD HH24:MI:SS')))AS FFF, ((TO_DATE(FI,  'YYYY-MM-DD HH24:MI:SS'))-(TO_DATE('%3$s',  'YYYY-MM-DD HH24:MI:SS'))) AS FIF FROM (SELECT HAB, APT, VIV, FF, FI FROM (SELECT IDHAB FROM (SELECT IDSH, IDHAB FROM (SELECT IDSH, IDHAB FROM (SELECT IDSERVICIOHABITACION AS IDSH, IDHABITACION AS IDHAB FROM HABITACIONOFRECE) INNER JOIN (SELECT IDHABITACION AS IDSHI FROM HABITACION) ON IDSHI = IDHAB) INNER JOIN (SELECT IDSERVICIOHABITACION AS LOL FROM SERVICIOHABITACION) ON LOL = IDSH) WHERE IDSH = %4$d) INNER JOIN (SELECT FF, FI, HAB, APT, VIV FROM (SELECT FECHAFINAL AS FF, FECHAINICIAL AS FI, IDHABITACION AS HAB, IDAPARTAMENTO AS APT, IDVIVIENDA AS VIV, DISPONIBLE FROM OFERTA WHERE DISPONIBLE = 1)) ON HAB = IDHAB)) WHERE FFF <= 0 AND FIF <= 0"
				, USUARIO, fechaFinal, fechaInicial, servicio);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
                while (rs.next()) {
			resp.add(convertResultSetToReqIV(rs));
		}
		return resp;
                
	}

    public ReqIV convertResultSetToReqIV(ResultSet resultSet) throws SQLException {		
		
		Long idHabitacion = resultSet.getLong("IDHAB");
		Long idApartamento = resultSet.getLong("IDAPT");
		Long idVivienda = resultSet.getLong("IDVIV");
	
		ReqIV op = new ReqIV(idHabitacion, idApartamento, idVivienda);
		return op;
	}
}

