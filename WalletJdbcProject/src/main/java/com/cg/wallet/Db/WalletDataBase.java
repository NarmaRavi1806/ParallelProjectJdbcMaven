package com.cg.wallet.Db;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import com.cg.wallet.walletException.WalletException;


public class WalletDataBase{
	
	public static Connection getConn() throws WalletException{
		String url="jdbc:mysql://localhost:3306/myjdbc";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url,"root","root");
		}catch(ClassNotFoundException e){
			throw new WalletException(e.getMessage());
		}catch(SQLException e1){
			throw new WalletException(e1.getMessage());
		}
	}
	

}

