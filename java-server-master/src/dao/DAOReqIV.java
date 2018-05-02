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
        
	public ArrayList<ReqIV> getReqIV(String fechaFinal, String fechaInicial, Long [] servicios) throws SQLException 
        {
                ArrayList<ReqIV> resp = new ArrayList<ReqIV>();
                String concat = "";
                
                if (!(servicios.length==0))
                {
                	concat = "WHERE IDSH =" + servicios[0];
                	for (int i = 1; i< servicios.length; i++)
                	{
                		concat = concat + "OR IDSH =" + servicios[i];
                	}
                }
                
		String sql = String.format("SELECT HABITACIONES, APARTAMENTOS, VIVIENDAS FROM (SELECT IDH AS HABITACIONES, IDA AS APARTAMENTOS, IDV AS VIVIENDAS, IDSERVICIOHABITACION AS  IDSH FROM (SELECT * FROM (SELECT IDHABITACION AS IDH, IDAPARTAMENTO AS IDA, IDAPARTAMENTO AS IDV, IDOFERTA FROM OFERTA WHERE (TO_DATE(FECHAINICIAL,  'YYYY-MM-DD'))-(TO_DATE('%2$s',  'YYYY-MM-DD'))>=0 AND (TO_DATE(FECHAFINAL,  'YYYY-MM-DD'))-(TO_DATE('%3$s',  'YYYY-MM-DD'))<=0 AND NOT (IDHABITACION IS NULL AND IDAPARTAMENTO IS NULL)) INNER JOIN HABITACIONOFRECE ON (IDH = IDHABITACION OR IDA = IDHABITACION OR IDV = IDHABITACION))) %4$s"
				, USUARIO, fechaFinal, fechaInicial, concat);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
                while (rs.next()) {
			resp.add(convertResultSetToReqIV(rs));
		}
		return resp;
                
	}

    public ReqIV convertResultSetToReqIV(ResultSet resultSet) throws SQLException {		
		
		Long habitaciones = resultSet.getLong("HABITACIONES");
		Long apartamentos = resultSet.getLong("APARTAMENTOS");
		Long viviendas = resultSet.getLong("VIVIENDAS");
	
		ReqIV op = new ReqIV(habitaciones, apartamentos, viviendas);
		return op;
	}
}

