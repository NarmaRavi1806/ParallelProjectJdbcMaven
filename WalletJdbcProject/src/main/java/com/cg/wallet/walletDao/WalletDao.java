package com.cg.wallet.walletDao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import com.cg.wallet.Db.WalletDataBase;
import com.cg.wallet.bean.Wallet;
import com.cg.wallet.walletException.WalletException;

/**
 * 
 * Class name : WalletDao
 * Interface : IWalletDao
 * No. of Methods : 6
 * Purpose :To access the database and retrieve values from database
 * 
 * Author : Narmatha
 * Date of Creation : 04-August-2018
 * 
 * Last Modification Date : 04-August-2018
 *
 */

public class WalletDao implements IWalletDao {

	//HashMap<String,Wallet> wallMap=WalletDataBase.getWalletMap();


	public String createAccount(Wallet wallet) throws WalletException {
		Connection conn=WalletDataBase.getConn();
		PreparedStatement stat;
		try{
			conn.setAutoCommit(false);
			stat = conn.prepareStatement(IQueryMapper.insert);
			stat.setString(1, wallet.getName());
			stat.setString(2, wallet.getMobileNo());
			stat.setString(3, wallet.getEmailId());
			stat.setDouble(4, wallet.getBalance());
			int res=stat.executeUpdate();
			if(res==1){
				conn.commit();
				return wallet.getMobileNo();
			}else{
				throw new WalletException("Could not create account");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new WalletException(e.getMessage());
		}



	}
	public double showBalance(String mobileNo)
			throws WalletException {
		Connection conn=WalletDataBase.getConn();
		Wallet wallet=new Wallet();
		PreparedStatement stat;
		try{
			conn.setAutoCommit(false);
			stat=conn.prepareStatement(IQueryMapper.getBal);
			stat.setString(1, mobileNo);
			ResultSet rs=stat.executeQuery();
			conn.commit();
			if(rs!=null){
				rs.next();
				return rs.getDouble("balance");
			}else{
				throw new WalletException("mobile no does not exists");
			}

		}catch (SQLException e) {

			throw new WalletException(e.getMessage());
		}
	}

	public Wallet deposit(String mobileNo,double dep)
			throws WalletException {
		Connection con=WalletDataBase.getConn();
		PreparedStatement stat;
		PreparedStatement stat1;
		try{

			/*con.setAutoCommit(false);
			stat=con.prepareStatement(IQueryMapper.getBal);*/
			Wallet wallet=printTransaction(mobileNo);
			
			double bal=wallet.getBalance()+dep;
			wallet.setBalance(bal);;
			wallet.setDate(Date.valueOf(LocalDate.now()));
			
			stat=con.prepareStatement(IQueryMapper.update);
	
			stat.setDouble(1, wallet.getBalance());
			stat.setDate(2, wallet.getDate());
			stat.setString(3, mobileNo);
			
			stat.executeQuery();
			
			return wallet;
			/*stat.setString(1, mobileNo);
			ResultSet rs=stat.executeQuery();
			if(rs!=null){
				rs.next();
				Wallet wallet=new Wallet();
				System.out.println(rs.getDouble("balance"));
				double balance=rs.getDouble("balance")+dep;
				System.out.println(balance);
				//System.out.println(rs.getString(1));
				wallet.setName(rs.getString("name"));
				wallet.setMobileNo(rs.getString("mobileNo"));
				wallet.setBalance(balance);
				wallet.setEmailId(rs.getString("emailId"));
				wallet.setDate(Date.valueOf(LocalDate.now()));
				stat1=con.prepareStatement(IQueryMapper.depo);
				stat1.setDouble(1, wallet.getBalance());
				stat1.setDate(2, wallet.getDate());
				stat1.setString(3, wallet.getMobileNo());
				int rs1=stat1.executeUpdate();

				if(rs1==1){

					con.commit();

					return wallet.getBalance();
				}else{
					throw new WalletException("balance is not updated");
				}
			}
			else{
				throw new WalletException("mobile no does not exists");
			}


		*/}catch (SQLException e) {

			throw new WalletException(e.getMessage());
		}
		
	}

	public Wallet withdrawAmount(String mobileNo,double withdraw)
			throws WalletException {
		Connection conn=WalletDataBase.getConn();
		Wallet wallet=new Wallet();
		PreparedStatement stat;
		PreparedStatement stat1;
		try{

			conn.setAutoCommit(false);
			stat=conn.prepareStatement(IQueryMapper.getacc);
			stat.setString(1, mobileNo);
			ResultSet rs=stat.executeQuery();
			if(rs!=null){
				rs.next();

				double balance=rs.getDouble("balance")-withdraw;
				wallet.setName(rs.getString("name"));
				wallet.setMobileNo(rs.getString("mobileNo"));
				wallet.setBalance(balance);
				wallet.setEmailId(rs.getString("emailId"));
				wallet.setDate(Date.valueOf(LocalDate.now()));

				stat1=conn.prepareStatement(IQueryMapper.update);
				stat1.setDouble(1, wallet.getBalance());
				stat1.setDate(2, wallet.getDate());
				stat1.setString(3, wallet.getMobileNo());
				int rs1=stat1.executeUpdate();


				if(rs1==1){
					conn.commit();
					return wallet;
				}else{
					throw new WalletException("Invalid Mobile Number");
				}

			}
			else{
				throw new WalletException("Mobile number does not exists");
			}


		}catch (SQLException e) {

			throw new WalletException(e.getMessage());
		}

	}

	/*public double fundTransfer(String srcMobile, String destMobile,
			double amount) throws WalletException {
		Connection conn=WalletDataBase.getConn();
		Wallet wallet=new Wallet();
		Wallet wallet2=new Wallet();
		deposit(srcMobile, amount);
		withdrawAmount(destMobile, amount);

		return wallet.getBalance()+wallet2.getBalance();
	}*/

	public Wallet printTransaction(String mobile) throws WalletException {
		Connection conn=WalletDataBase.getConn();
		PreparedStatement stat;
		try{
			conn.setAutoCommit(false);
			stat=conn.prepareStatement(IQueryMapper.getacc);
			stat.setString(1, mobile);
			ResultSet rs=stat.executeQuery();
			conn.commit();
			if(rs!=null){
				rs.next();
				Wallet wallet=new Wallet();
				wallet.setName(rs.getString("name"));
				wallet.setMobileNo(rs.getString("mobileNo"));
				wallet.setEmailId(rs.getString("emailId"));
				wallet.setBalance(rs.getDouble("balance"));
				wallet.setDate(rs.getDate("Trans_date"));
				return wallet;
			}else{
				throw new WalletException("Mobile number does not exists");
			}


		}catch (SQLException e) {

			throw new WalletException(e.getMessage());
		}
	}




}
