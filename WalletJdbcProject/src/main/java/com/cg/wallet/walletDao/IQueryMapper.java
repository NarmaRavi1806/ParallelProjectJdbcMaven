package com.cg.wallet.walletDao;

public interface IQueryMapper {
	
	public String insert="insert into Wallet(name,emailId,mobileNo,balance) values(?,?,?,?)";
	public String getBal="select balance from Wallet where mobileNo=?";
	public String getacc="select * from Wallet where mobileNo=?";
	public String update="update Wallet set balance=?,Transdate=? where mobileNo=?";

}
