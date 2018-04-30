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
 * aplicaci�n
 * @author Grupo A - 16*/
public class DAOReqX{

	
	public final static String USUARIO = "ISIS2304A241810";
	/**
	 * Arraylits de recursos que se usan para la ejecuci�n de sentencias SQL
	 */
	private ArrayList<Object> recursos;
	
	/**
	 * Atributo que genera la conexi�n a la base de datos
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
	 * Metodo que inicializa la connection del DAO a la base de datos con la conexi�n que 
         * entra como parametro.
	 * @param con - connection a la base de datos
	 */
	public void setConn(Connection con){
		this.conn = con;
	}
        
	public ArrayList<ReqX> getReqX(Long idOp, String group, String[] order) throws SQLException 
        {
       ArrayList<ReqX> resp = new ArrayList<ReqX>(); 
                
       String sql2 = "";
       String a�adido = "";
       if (order.length>0)
       {
    	   a�adido = "ORDER BY" + order[0];
    	   for (int i = 1; i< order.length; i++)
    	   {
    		   a�adido = a�adido + "," + order[i];
    	   }
       }
       
       if (idOp == 666)
       {
    	   if (group.equals("CLIENTE"))
    	   {
    		   sql2 = String.format("SELECT  MONTO, NIGHTS, HABITACIONES, APARTAMENTOS, VIVIENDAS, USUARIO, NOMBRE, CUENTA FROM (SELECT SUM(COSTO) AS MONTO, SUM(NOCHES) AS NIGHTS, IDCLIENTE, COUNT(DISTINCT IDOFERTA) AS CUENTA, COUNT (DISTINCT HAB) AS HABITACIONES, COUNT(DISTINCT APT) AS APARTAMENTOS, COUNT(DISTINCT VIV) AS VIVIENDAS, USUARIO, NOMBRE FROM (SELECT * FROM (SELECT COSTO, NOCHES, IDCLIENTE, HAB, APT, VIV, IDOFERTA, USUARIO, NOMBRE, IDOPERADOR FROM (SELECT * FROM (SELECT COSTO, FFC, FIC, NOCHES, IDCLIENTE AS BOLEADOR, IDHC AS HAB, IDAC AS APT, IDVC AS VIV, IDOFERTA, IDOPERADOR FROM (SELECT COSTO, FECHAFINAL AS FFC, FECHAINIC AS FIC, NOCHES, IDCLIENTE, NVL(IDHABITACION,0) AS IDHC, NVL(IDAPARTAMENTO,0) AS IDAC, NVL(IDVIVIENDA,0) AS IDVC FROM CONTRATO WHERE (TO_DATE('2900-01-01',  'YYYY-MM-DD') - TO_DATE(FECHAINIC,  'YYYY-MM-DD')) > 0 AND (TO_DATE(FECHAFINAL,  'YYYY-MM-DD') - TO_DATE('1900-01-01',  'YYYY-MM-DD')) > 0) INNER JOIN (SELECT NVL(IDHABITACION,0) AS IDHO, NVL(IDAPARTAMENTO,0) AS IDAO, NVL(IDVIVIENDA,0) AS IDVO, FECHAINICIAL AS FIO, FECHAFINAL AS FFO, IDOFERTA, IDOPERADOR FROM OFERTA WHERE (TO_DATE('2900-01-01',  'YYYY-MM-DD') - TO_DATE(FECHAINICIAL,  'YYYY-MM-DD')) > 0 AND (TO_DATE(FECHAFINAL,  'YYYY-MM-DD') - TO_DATE('1900-01-01',  'YYYY-MM-DD')) > 0 AND DISPONIBLE = 1) ON TO_DATE(FFC,  'YYYY-MM-DD') - TO_DATE(FFO,  'YYYY-MM-DD')<=0 AND TO_DATE(FIC,  'YYYY-MM-DD') - TO_DATE(FIO,  'YYYY-MM-DD')>=0 AND IDHO=IDHC AND IDAO=IDAC AND IDVC= IDVO) INNER JOIN CLIENTE ON BOLEADOR = CLIENTE.IDCLIENTE)) UNION (SELECT COSTO, NOCHES, IDCLIENTE, HAB, APT, VIV, IDOFERTA, USUARIO, NOMBRE, IDOPERADOR FROM (SELECT * FROM (SELECT COSTO, FFC, FIC, NOCHES, IDCLIENTE AS BOLEADOR, IDHC AS HAB, IDAC AS APT, IDVC AS VIV, IDOFERTA, IDOPERADOR FROM (SELECT COSTO, FECHAFINAL AS FFC, FECHAINIC AS FIC, NOCHES, IDCLIENTE, NVL(IDHABITACION,0) AS IDHC, NVL(IDAPARTAMENTO,0) AS IDAC, NVL(IDVIVIENDA,0) AS IDVC FROM CONTRATO WHERE (TO_DATE('2900-01-01',  'YYYY-MM-DD') - TO_DATE(FECHAINIC,  'YYYY-MM-DD')) > 0 AND (TO_DATE(FECHAFINAL,  'YYYY-MM-DD') - TO_DATE('1900-01-01',  'YYYY-MM-DD')) > 0) INNER JOIN (SELECT NVL(IDHABITACION,0) AS IDHO, NVL(IDAPARTAMENTO,0) AS IDAO, NVL(IDVIVIENDA,0) AS IDVO, FECHAINICIAL AS FIO, FECHAFINAL AS FFO, IDOFERTA, IDOPERADOR FROM OFERTA WHERE (TO_DATE('2900-01-01',  'YYYY-MM-DD') - TO_DATE(FECHAINICIAL,  'YYYY-MM-DD')) > 0 AND (TO_DATE(FECHAFINAL,  'YYYY-MM-DD') - TO_DATE('1900-01-01',  'YYYY-MM-DD')) > 0 AND DISPONIBLE = 1) ON TO_DATE(FFC,  'YYYY-MM-DD') - TO_DATE(FFO,  'YYYY-MM-DD')<=0 AND TO_DATE(FIC,  'YYYY-MM-DD') - TO_DATE(FIO,  'YYYY-MM-DD')>=0 AND IDHO=IDHC AND IDAO=IDAC AND IDVC= IDVO) INNER JOIN CLIENTE ON BOLEADOR = CLIENTE.IDCLIENTE))) GROUP BY IDClIENTE, USUARIO, NOMBRE)" + a�adido
           			,USUARIO, idOp);
    	   }
    	   else
    	   {
    		   sql2 = String.format("SELECT  MONTO, NIGHTS, HABITACIONES, APARTAMENTOS, VIVIENDAS, USUARIO, NOMBRE, CUENTA FROM (SELECT SUM(COSTO) AS MONTO, SUM(NOCHES) AS NIGHTS, IDOFERTA, COUNT(DISTINCT IDCLIENTE) AS CUENTA, COUNT (DISTINCT HAB) AS HABITACIONES, COUNT(DISTINCT APT) AS APARTAMENTOS, COUNT(DISTINCT VIV) AS VIVIENDAS, USUARIO, NOMBRE FROM (SELECT * FROM (SELECT COSTO, NOCHES, IDCLIENTE, HAB, APT, VIV, IDOFERTA, USUARIO, NOMBRE, IDOPERADOR FROM (SELECT * FROM (SELECT COSTO, FFC, FIC, NOCHES, IDCLIENTE AS BOLEADOR, IDHC AS HAB, IDAC AS APT, IDVC AS VIV, IDOFERTA, IDOPERADOR FROM (SELECT COSTO, FECHAFINAL AS FFC, FECHAINIC AS FIC, NOCHES, IDCLIENTE, NVL(IDHABITACION,0) AS IDHC, NVL(IDAPARTAMENTO,0) AS IDAC, NVL(IDVIVIENDA,0) AS IDVC FROM CONTRATO WHERE (TO_DATE('2900-01-01',  'YYYY-MM-DD') - TO_DATE(FECHAINIC,  'YYYY-MM-DD')) > 0 AND (TO_DATE(FECHAFINAL,  'YYYY-MM-DD') - TO_DATE('1900-01-01',  'YYYY-MM-DD')) > 0) INNER JOIN (SELECT NVL(IDHABITACION,0) AS IDHO, NVL(IDAPARTAMENTO,0) AS IDAO, NVL(IDVIVIENDA,0) AS IDVO, FECHAINICIAL AS FIO, FECHAFINAL AS FFO, IDOFERTA, IDOPERADOR FROM OFERTA WHERE (TO_DATE('2900-01-01',  'YYYY-MM-DD') - TO_DATE(FECHAINICIAL,  'YYYY-MM-DD')) > 0 AND (TO_DATE(FECHAFINAL,  'YYYY-MM-DD') - TO_DATE('1900-01-01',  'YYYY-MM-DD')) > 0 AND DISPONIBLE = 1) ON TO_DATE(FFC,  'YYYY-MM-DD') - TO_DATE(FFO,  'YYYY-MM-DD')<=0 AND TO_DATE(FIC,  'YYYY-MM-DD') - TO_DATE(FIO,  'YYYY-MM-DD')>=0 AND IDHO=IDHC AND IDAO=IDAC AND IDVC= IDVO) INNER JOIN CLIENTE ON BOLEADOR = CLIENTE.IDCLIENTE)) UNION (SELECT COSTO, NOCHES, IDCLIENTE, HAB, APT, VIV, IDOFERTA, USUARIO, NOMBRE, IDOPERADOR FROM (SELECT * FROM (SELECT COSTO, FFC, FIC, NOCHES, IDCLIENTE AS BOLEADOR, IDHC AS HAB, IDAC AS APT, IDVC AS VIV, IDOFERTA, IDOPERADOR FROM (SELECT COSTO, FECHAFINAL AS FFC, FECHAINIC AS FIC, NOCHES, IDCLIENTE, NVL(IDHABITACION,0) AS IDHC, NVL(IDAPARTAMENTO,0) AS IDAC, NVL(IDVIVIENDA,0) AS IDVC FROM CONTRATO WHERE (TO_DATE('2900-01-01',  'YYYY-MM-DD') - TO_DATE(FECHAINIC,  'YYYY-MM-DD')) > 0 AND (TO_DATE(FECHAFINAL,  'YYYY-MM-DD') - TO_DATE('1900-01-01',  'YYYY-MM-DD')) > 0) INNER JOIN (SELECT NVL(IDHABITACION,0) AS IDHO, NVL(IDAPARTAMENTO,0) AS IDAO, NVL(IDVIVIENDA,0) AS IDVO, FECHAINICIAL AS FIO, FECHAFINAL AS FFO, IDOFERTA, IDOPERADOR FROM OFERTA WHERE (TO_DATE('2900-01-01',  'YYYY-MM-DD') - TO_DATE(FECHAINICIAL,  'YYYY-MM-DD')) > 0 AND (TO_DATE(FECHAFINAL,  'YYYY-MM-DD') - TO_DATE('1900-01-01',  'YYYY-MM-DD')) > 0 AND DISPONIBLE = 1) ON TO_DATE(FFC,  'YYYY-MM-DD') - TO_DATE(FFO,  'YYYY-MM-DD')<=0 AND TO_DATE(FIC,  'YYYY-MM-DD') - TO_DATE(FIO,  'YYYY-MM-DD')>=0 AND IDHO=IDHC AND IDAO=IDAC AND IDVC= IDVO) INNER JOIN CLIENTE ON BOLEADOR = CLIENTE.IDCLIENTE))) GROUP BY IDOFERTA, USUARIO, NOMBRE)" + a�adido
              	    ,USUARIO, idOp);
    	   }     	
       }
       else
       {    	   
    	   if (group.equals("CLIENTE"))
    	   {
    		   sql2 = String.format("SELECT  MONTO, NIGHTS, HABITACIONES, APARTAMENTOS, VIVIENDAS, USUARIO, NOMBRE, CUENTA FROM (SELECT SUM(COSTO) AS MONTO, SUM(NOCHES) AS NIGHTS, IDCLIENTE, COUNT(DISTINCT IDOFERTA) AS CUENTA, COUNT (DISTINCT HAB) AS HABITACIONES, COUNT(DISTINCT APT) AS APARTAMENTOS, COUNT(DISTINCT VIV) AS VIVIENDAS, USUARIO, NOMBRE FROM (SELECT * FROM (SELECT COSTO, NOCHES, IDCLIENTE, HAB, APT, VIV, IDOFERTA, USUARIO, NOMBRE, IDOPERADOR FROM (SELECT * FROM (SELECT COSTO, FFC, FIC, NOCHES, IDCLIENTE AS BOLEADOR, IDHC AS HAB, IDAC AS APT, IDVC AS VIV, IDOFERTA, IDOPERADOR FROM (SELECT COSTO, FECHAFINAL AS FFC, FECHAINIC AS FIC, NOCHES, IDCLIENTE, NVL(IDHABITACION,0) AS IDHC, NVL(IDAPARTAMENTO,0) AS IDAC, NVL(IDVIVIENDA,0) AS IDVC FROM CONTRATO WHERE (TO_DATE('2900-01-01',  'YYYY-MM-DD') - TO_DATE(FECHAINIC,  'YYYY-MM-DD')) > 0 AND (TO_DATE(FECHAFINAL,  'YYYY-MM-DD') - TO_DATE('1900-01-01',  'YYYY-MM-DD')) > 0) INNER JOIN (SELECT NVL(IDHABITACION,0) AS IDHO, NVL(IDAPARTAMENTO,0) AS IDAO, NVL(IDVIVIENDA,0) AS IDVO, FECHAINICIAL AS FIO, FECHAFINAL AS FFO, IDOFERTA, IDOPERADOR FROM OFERTA WHERE (TO_DATE('2900-01-01',  'YYYY-MM-DD') - TO_DATE(FECHAINICIAL,  'YYYY-MM-DD')) > 0 AND (TO_DATE(FECHAFINAL,  'YYYY-MM-DD') - TO_DATE('1900-01-01',  'YYYY-MM-DD')) > 0 AND DISPONIBLE = 1) ON TO_DATE(FFC,  'YYYY-MM-DD') - TO_DATE(FFO,  'YYYY-MM-DD')<=0 AND TO_DATE(FIC,  'YYYY-MM-DD') - TO_DATE(FIO,  'YYYY-MM-DD')>=0 AND IDHO=IDHC AND IDAO=IDAC AND IDVC= IDVO) INNER JOIN CLIENTE ON BOLEADOR = CLIENTE.IDCLIENTE)) UNION (SELECT COSTO, NOCHES, IDCLIENTE, HAB, APT, VIV, IDOFERTA, USUARIO, NOMBRE, IDOPERADOR FROM (SELECT * FROM (SELECT COSTO, FFC, FIC, NOCHES, IDCLIENTE AS BOLEADOR, IDHC AS HAB, IDAC AS APT, IDVC AS VIV, IDOFERTA, IDOPERADOR FROM (SELECT COSTO, FECHAFINAL AS FFC, FECHAINIC AS FIC, NOCHES, IDCLIENTE, NVL(IDHABITACION,0) AS IDHC, NVL(IDAPARTAMENTO,0) AS IDAC, NVL(IDVIVIENDA,0) AS IDVC FROM CONTRATO WHERE (TO_DATE('2900-01-01',  'YYYY-MM-DD') - TO_DATE(FECHAINIC,  'YYYY-MM-DD')) > 0 AND (TO_DATE(FECHAFINAL,  'YYYY-MM-DD') - TO_DATE('1900-01-01',  'YYYY-MM-DD')) > 0) INNER JOIN (SELECT NVL(IDHABITACION,0) AS IDHO, NVL(IDAPARTAMENTO,0) AS IDAO, NVL(IDVIVIENDA,0) AS IDVO, FECHAINICIAL AS FIO, FECHAFINAL AS FFO, IDOFERTA, IDOPERADOR FROM OFERTA WHERE (TO_DATE('2900-01-01',  'YYYY-MM-DD') - TO_DATE(FECHAINICIAL,  'YYYY-MM-DD')) > 0 AND (TO_DATE(FECHAFINAL,  'YYYY-MM-DD') - TO_DATE('1900-01-01',  'YYYY-MM-DD')) > 0 AND DISPONIBLE = 1) ON TO_DATE(FFC,  'YYYY-MM-DD') - TO_DATE(FFO,  'YYYY-MM-DD')<=0 AND TO_DATE(FIC,  'YYYY-MM-DD') - TO_DATE(FIO,  'YYYY-MM-DD')>=0 AND IDHO=IDHC AND IDAO=IDAC AND IDVC= IDVO) INNER JOIN CLIENTE ON BOLEADOR = CLIENTE.IDCLIENTE))) WHERE IDOPERADOR = %2%d GROUP BY IDClIENTE, USUARIO, NOMBRE)" + a�adido
           			,USUARIO);
    	   }
    	   else
    	   {
    		   sql2 = String.format("SELECT  MONTO, NIGHTS, HABITACIONES, APARTAMENTOS, VIVIENDAS, USUARIO, NOMBRE, CUENTA FROM (SELECT SUM(COSTO) AS MONTO, SUM(NOCHES) AS NIGHTS, IDOFERTA, COUNT(DISTINCT IDCLIENTE) AS CUENTA, COUNT (DISTINCT HAB) AS HABITACIONES, COUNT(DISTINCT APT) AS APARTAMENTOS, COUNT(DISTINCT VIV) AS VIVIENDAS, USUARIO, NOMBRE FROM (SELECT * FROM (SELECT COSTO, NOCHES, IDCLIENTE, HAB, APT, VIV, IDOFERTA, USUARIO, NOMBRE, IDOPERADOR FROM (SELECT * FROM (SELECT COSTO, FFC, FIC, NOCHES, IDCLIENTE AS BOLEADOR, IDHC AS HAB, IDAC AS APT, IDVC AS VIV, IDOFERTA, IDOPERADOR FROM (SELECT COSTO, FECHAFINAL AS FFC, FECHAINIC AS FIC, NOCHES, IDCLIENTE, NVL(IDHABITACION,0) AS IDHC, NVL(IDAPARTAMENTO,0) AS IDAC, NVL(IDVIVIENDA,0) AS IDVC FROM CONTRATO WHERE (TO_DATE('2900-01-01',  'YYYY-MM-DD') - TO_DATE(FECHAINIC,  'YYYY-MM-DD')) > 0 AND (TO_DATE(FECHAFINAL,  'YYYY-MM-DD') - TO_DATE('1900-01-01',  'YYYY-MM-DD')) > 0) INNER JOIN (SELECT NVL(IDHABITACION,0) AS IDHO, NVL(IDAPARTAMENTO,0) AS IDAO, NVL(IDVIVIENDA,0) AS IDVO, FECHAINICIAL AS FIO, FECHAFINAL AS FFO, IDOFERTA, IDOPERADOR FROM OFERTA WHERE (TO_DATE('2900-01-01',  'YYYY-MM-DD') - TO_DATE(FECHAINICIAL,  'YYYY-MM-DD')) > 0 AND (TO_DATE(FECHAFINAL,  'YYYY-MM-DD') - TO_DATE('1900-01-01',  'YYYY-MM-DD')) > 0 AND DISPONIBLE = 1) ON TO_DATE(FFC,  'YYYY-MM-DD') - TO_DATE(FFO,  'YYYY-MM-DD')<=0 AND TO_DATE(FIC,  'YYYY-MM-DD') - TO_DATE(FIO,  'YYYY-MM-DD')>=0 AND IDHO=IDHC AND IDAO=IDAC AND IDVC= IDVO) INNER JOIN CLIENTE ON BOLEADOR = CLIENTE.IDCLIENTE)) UNION (SELECT COSTO, NOCHES, IDCLIENTE, HAB, APT, VIV, IDOFERTA, USUARIO, NOMBRE, IDOPERADOR FROM (SELECT * FROM (SELECT COSTO, FFC, FIC, NOCHES, IDCLIENTE AS BOLEADOR, IDHC AS HAB, IDAC AS APT, IDVC AS VIV, IDOFERTA, IDOPERADOR FROM (SELECT COSTO, FECHAFINAL AS FFC, FECHAINIC AS FIC, NOCHES, IDCLIENTE, NVL(IDHABITACION,0) AS IDHC, NVL(IDAPARTAMENTO,0) AS IDAC, NVL(IDVIVIENDA,0) AS IDVC FROM CONTRATO WHERE (TO_DATE('2900-01-01',  'YYYY-MM-DD') - TO_DATE(FECHAINIC,  'YYYY-MM-DD')) > 0 AND (TO_DATE(FECHAFINAL,  'YYYY-MM-DD') - TO_DATE('1900-01-01',  'YYYY-MM-DD')) > 0) INNER JOIN (SELECT NVL(IDHABITACION,0) AS IDHO, NVL(IDAPARTAMENTO,0) AS IDAO, NVL(IDVIVIENDA,0) AS IDVO, FECHAINICIAL AS FIO, FECHAFINAL AS FFO, IDOFERTA, IDOPERADOR FROM OFERTA WHERE (TO_DATE('2900-01-01',  'YYYY-MM-DD') - TO_DATE(FECHAINICIAL,  'YYYY-MM-DD')) > 0 AND (TO_DATE(FECHAFINAL,  'YYYY-MM-DD') - TO_DATE('1900-01-01',  'YYYY-MM-DD')) > 0 AND DISPONIBLE = 1) ON TO_DATE(FFC,  'YYYY-MM-DD') - TO_DATE(FFO,  'YYYY-MM-DD')<=0 AND TO_DATE(FIC,  'YYYY-MM-DD') - TO_DATE(FIO,  'YYYY-MM-DD')>=0 AND IDHO=IDHC AND IDAO=IDAC AND IDVC= IDVO) INNER JOIN CLIENTE ON BOLEADOR = CLIENTE.IDCLIENTE))) WHERE IDOPERADOR = %2$d GROUP BY IDOFERTA, USUARIO, NOMBRE)" + a�adido
              	    ,USUARIO);
    	   }         	  
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
		Integer cuenta= resultSet.getInt("CUENTA");
	
		ReqX op = new ReqX(monto, nights, habitaciones, apartamentos, viviendas, usuario, nombre, cuenta);
		return op;
	}
}