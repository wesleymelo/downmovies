package br.ucb.fimes.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FactoryConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/filmes";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	
	public static Connection init() {
		Connection conn = null;
		
		try {
			Class.forName(getDriver());
			conn = DriverManager.getConnection(getUrl(), getUser(), getPassword());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void closeConnection(Connection con, Statement stm, ResultSet rs){
        if(con != null && stm != null && rs != null){
                try {
                        con.close();
                        stm.close();
                        rs.close();
                } catch (SQLException e) {
                        e.printStackTrace();
                }
                
        }
	}
	
	public static void closeConnection(Connection con, Statement stm){
        if(con != null && stm != null){
                try {
                        con.close();
                        stm.close();
                    
                } catch (SQLException e) {
                        e.printStackTrace();
                }
                
        }
	}

	public static String getUrl() {
		return URL;
	}

	public static String getDriver() {
		return DRIVER;
	}

	public static String getUser() {
		return USER;
	}

	public static String getPassword() {
		return PASSWORD;
	}
}
