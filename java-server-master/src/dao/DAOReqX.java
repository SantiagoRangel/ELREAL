package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.ReqI;
import vos.ReqVII;
import vos.ReqX;

/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la 
 * aplicación
 * @author Grupo A - 16*/
public class DAOReqX{

	
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
	public DAOReqX() {
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
        
	public ArrayList<ReqX> getReqX(Long idOperador, Long idOferta, String fechaInicio, String fechaFinal , String[] order) throws SQLException 
        {
       ArrayList<ReqX> resp = new ArrayList<ReqX>(); 
                
       String sql2 = "";
       String añadido = "";
       
       if (order.length>0)
       {
    	   añadido = "ORDER BY" + order[0];
    	   for (int i = 1; i< order.length; i++)
    	   {
    		   añadido = añadido + "," + order[i];
    	   }
       }
       
       if (idOperador == 666)
       {
    		   sql2 = String.format("SELECT IDCLIENTE, USUARIO, NOMBRE, SUM(COSTO) AS MONTO, SUM(NOCHES) AS NIGHTS, COUNT(HAB) AS HABItACIONES, COUNT(APT) AS APARTAMENTOS, COUNT(VIV) AS VIVIENDAS FROM (SELECT IDCLIENTE, USUARIO, NOMBRE, COSTO, NOCHES, IDHABITACION AS HAB, IDVIVIENDA AS VIV, IDAPARTAMENTO AS APT FROM (SELECT IDCLIENTE AS CLI, USUARIO, CONTRASENA, NOMBRE FROM CLIENTE WHERE CLIENTE.IDCLIENTE IN (SELECT IDCLIENTE FROM (SELECT IDHABITACION AS IDHO, IDAPARTAMENTO AS IDAO, IDVIVIENDA AS IDVO, FECHAINICIAL AS FIO, FECHAFINAL AS FFO, IDOFERTA FROM OFERTA WHERE IDOFERTA = %2$d) INNER JOIN CONTRATO ON NVL(IDHO,0) = NVL(CONTRATO.IDHABITACION,0) AND NVL(IDAO,0) = NVL(CONTRATO.IDAPARTAMENTO,0) AND NVL(IDVO,0) = NVL(CONTRATO.IDVIVIENDA,0) AND TO_DATE(CONTRATO.FECHAINIC, 'YYYY-MM-DD')-TO_DATE(FIO, 'YYYY-MM-DD')>=0 AND TO_DATE(CONTRATO.FECHAFINAL, 'YYYY-MM-DD')-TO_DATE(FFO, 'YYYY-MM-DD')<=0 AND TO_DATE(CONTRATO.FECHAINIC, 'YYYY-MM-DD')-TO_DATE('%3$$s', 'YYYY-MM-DD')>=0 AND TO_DATE(CONTRATO.FECHAFINAL, 'YYYY-MM-DD')-TO_DATE('%4$$s', 'YYYY-MM-DD')<=0)) INNER JOIN CONTRATO ON CONTRATO.IDCLIENTE = CLI) GROUP BY IDCLIENTE, USUARIO, NOMBRE %6$s"
              	    ,USUARIO, idOferta, fechaInicio, fechaFinal, idOperador, añadido);    	   
       }
       else
       {  
    		   sql2 = String.format("SELECT IDCLIENTE, USUARIO, NOMBRE, SUM(COSTO) AS MONTO, SUM(NOCHES) AS NIGHTS, COUNT(HAB) AS HABItACIONES, COUNT(APT) AS APARTAMENTOS, COUNT(VIV) AS VIVIENDAS FROM (SELECT IDCLIENTE, USUARIO, NOMBRE, COSTO, NOCHES, IDHABITACION AS HAB, IDVIVIENDA AS VIV, IDAPARTAMENTO AS APT FROM (SELECT IDCLIENTE AS CLI, USUARIO, CONTRASENA, NOMBRE FROM CLIENTE WHERE CLIENTE.IDCLIENTE IN (SELECT IDCLIENTE FROM (SELECT IDHABITACION AS IDHO, IDAPARTAMENTO AS IDAO, IDVIVIENDA AS IDVO, FECHAINICIAL AS FIO, FECHAFINAL AS FFO, IDOFERTA FROM OFERTA WHERE IDOFERTA = %2$d) INNER JOIN CONTRATO ON NVL(IDHO,0) = NVL(CONTRATO.IDHABITACION,0) AND NVL(IDAO,0) = NVL(CONTRATO.IDAPARTAMENTO,0) AND NVL(IDVO,0) = NVL(CONTRATO.IDVIVIENDA,0) AND TO_DATE(CONTRATO.FECHAINIC, 'YYYY-MM-DD')-TO_DATE(FIO, 'YYYY-MM-DD')>=0 AND TO_DATE(CONTRATO.FECHAFINAL, 'YYYY-MM-DD')-TO_DATE(FFO, 'YYYY-MM-DD')<=0 AND TO_DATE(CONTRATO.FECHAINIC, 'YYYY-MM-DD')-TO_DATE('%3$$s', 'YYYY-MM-DD')>=0 AND TO_DATE(CONTRATO.FECHAFINAL, 'YYYY-MM-DD')-TO_DATE('%4$$s', 'YYYY-MM-DD')<=0)) INNER JOIN CONTRATO ON CONTRATO.IDCLIENTE = CLI WHERE IDOPERADOR = %5$d) GROUP BY IDCLIENTE, USUARIO, NOMBRE %6$s"
           			,USUARIO, idOferta, fechaInicio, fechaFinal, idOperador, añadido);    	   
       }

	    PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
		recursos.add(prepStmt2);
		ResultSet rs2 = prepStmt2.executeQuery();
        
		while (rs2.next()) {
			resp.add(convertResultSetToReqX(rs2));
		}
		
		return resp;                
	}	
	
	public ArrayList<ReqX> getReqXI(Long idOperador, Long idOferta, String fechaInicio, String fechaFinal , String[] order) throws SQLException 
    {
   ArrayList<ReqX> resp = new ArrayList<ReqX>(); 
            
   String sql2 = "";
   String añadido = "";
   
   if (order.length>0)
   {
	   añadido = "ORDER BY" + order[0];
	   for (int i = 1; i< order.length; i++)
	   {
		   añadido = añadido + "," + order[i];
	   }
   }
   
   if (idOperador == 666)
   {
		   sql2 = String.format("SELECT IDCLIENTE, USUARIO, NOMBRE, SUM(COSTO) AS MONTO, SUM(NOCHES) AS NIGHTS, COUNT(HAB) AS HABItACIONES, COUNT(APT) AS APARTAMENTOS, COUNT(VIV) AS VIVIENDAS FROM (SELECT IDCLIENTE, USUARIO, NOMBRE, COSTO, NOCHES, IDHABITACION AS HAB, IDVIVIENDA AS VIV, IDAPARTAMENTO AS APT FROM (SELECT IDCLIENTE AS CLI, USUARIO, CONTRASENA, NOMBRE FROM CLIENTE WHERE CLIENTE.IDCLIENTE NOT IN (SELECT IDCLIENTE FROM (SELECT IDHABITACION AS IDHO, IDAPARTAMENTO AS IDAO, IDVIVIENDA AS IDVO, FECHAINICIAL AS FIO, FECHAFINAL AS FFO, IDOFERTA FROM OFERTA WHERE IDOFERTA = %2$d) INNER JOIN CONTRATO ON NVL(IDHO,0) = NVL(CONTRATO.IDHABITACION,0) AND NVL(IDAO,0) = NVL(CONTRATO.IDAPARTAMENTO,0) AND NVL(IDVO,0) = NVL(CONTRATO.IDVIVIENDA,0) AND TO_DATE(CONTRATO.FECHAINIC, 'YYYY-MM-DD')-TO_DATE(FIO, 'YYYY-MM-DD')>=0 AND TO_DATE(CONTRATO.FECHAFINAL, 'YYYY-MM-DD')-TO_DATE(FFO, 'YYYY-MM-DD')<=0 AND TO_DATE(CONTRATO.FECHAINIC, 'YYYY-MM-DD')-TO_DATE('%3$$s', 'YYYY-MM-DD')>=0 AND TO_DATE(CONTRATO.FECHAFINAL, 'YYYY-MM-DD')-TO_DATE('%4$$s', 'YYYY-MM-DD')<=0)) INNER JOIN CONTRATO ON CONTRATO.IDCLIENTE = CLI) GROUP BY IDCLIENTE, USUARIO, NOMBRE %6$s"
          	    ,USUARIO, idOferta, fechaInicio, fechaFinal, idOperador, añadido);    	   
   }
   else
   {  
		   sql2 = String.format("SELECT IDCLIENTE, USUARIO, NOMBRE, SUM(COSTO) AS MONTO, SUM(NOCHES) AS NIGHTS, COUNT(HAB) AS HABItACIONES, COUNT(APT) AS APARTAMENTOS, COUNT(VIV) AS VIVIENDAS FROM (SELECT IDCLIENTE, USUARIO, NOMBRE, COSTO, NOCHES, IDHABITACION AS HAB, IDVIVIENDA AS VIV, IDAPARTAMENTO AS APT FROM (SELECT IDCLIENTE AS CLI, USUARIO, CONTRASENA, NOMBRE FROM CLIENTE WHERE CLIENTE.IDCLIENTE NOT IN (SELECT IDCLIENTE FROM (SELECT IDHABITACION AS IDHO, IDAPARTAMENTO AS IDAO, IDVIVIENDA AS IDVO, FECHAINICIAL AS FIO, FECHAFINAL AS FFO, IDOFERTA FROM OFERTA WHERE IDOFERTA = %2$d) INNER JOIN CONTRATO ON NVL(IDHO,0) = NVL(CONTRATO.IDHABITACION,0) AND NVL(IDAO,0) = NVL(CONTRATO.IDAPARTAMENTO,0) AND NVL(IDVO,0) = NVL(CONTRATO.IDVIVIENDA,0) AND TO_DATE(CONTRATO.FECHAINIC, 'YYYY-MM-DD')-TO_DATE(FIO, 'YYYY-MM-DD')>=0 AND TO_DATE(CONTRATO.FECHAFINAL, 'YYYY-MM-DD')-TO_DATE(FFO, 'YYYY-MM-DD')<=0 AND TO_DATE(CONTRATO.FECHAINIC, 'YYYY-MM-DD')-TO_DATE('%3$$s', 'YYYY-MM-DD')>=0 AND TO_DATE(CONTRATO.FECHAFINAL, 'YYYY-MM-DD')-TO_DATE('%4$$s', 'YYYY-MM-DD')<=0)) INNER JOIN CONTRATO ON CONTRATO.IDCLIENTE = CLI WHERE IDOPERADOR = %5$d) GROUP BY IDCLIENTE, USUARIO, NOMBRE %6$s"
       			,USUARIO, idOferta, fechaInicio, fechaFinal, idOperador, añadido);    	   
   }

    PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
	recursos.add(prepStmt2);
	ResultSet rs2 = prepStmt2.executeQuery();
    
	while (rs2.next()) {
		resp.add(convertResultSetToReqX(rs2));
	}
	
	return resp;                
}	
	
	
    public ReqX convertResultSetToReqX(ResultSet resultSet) throws SQLException {		
		
		Double monto = resultSet.getDouble("MONTO");
		Integer nights= resultSet.getInt("NIGHTS");	
		Integer habitaciones= resultSet.getInt("HABITACIONES");
		Integer apartamentos= resultSet.getInt("APARTAMENTOS");	
		Integer viviendas= resultSet.getInt("VIVIENDAS");	
		String usuario= resultSet.getString("USUARIO");			
		String nombre= resultSet.getString("NOMBRE");
	
		ReqX op = new ReqX(monto, nights, habitaciones, apartamentos, viviendas, usuario, nombre);
		return op;
	}
}