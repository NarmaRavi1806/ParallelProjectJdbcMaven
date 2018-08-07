package com.cg.wallet.walletService;


import java.time.LocalDateTime;

import com.cg.wallet.bean.Wallet;
import com.cg.wallet.walletDao.IWalletDao;
import com.cg.wallet.walletDao.WalletDao;
import com.cg.wallet.walletException.WalletException;

/**
 * 
 * Class name : WalletServiceImpl
 * No. of Methods : 10
 * Purpose :To implement the Service of Wallet
 * 
 * Author : Narmatha
 * Date of Creation : 04-August-2018
 * 
 * Last Modification Date : 04-August-2018
 *
 */

public class WalletServiceImpl implements IWalletService {
	IWalletDao walletDao=new WalletDao();
	

	public String createAccount(Wallet wallet) throws WalletException {
		if (!wallet.getMobileNo().matches("\\d{10}")) {
			throw new WalletException("Mobile number should contain 10 digits");
			}
			if (wallet.getName().isEmpty() || wallet.getName() == null) {
			throw new WalletException("Name cannot be empty");
			} else {
			if (!wallet.getName().matches("[A-Z][A-Za-z]{3,}")) {
			throw new WalletException(
			"Name should start with capital letter and should contain only alphabets");
			}
			}
			if (wallet.getBalance() < 0) {
			throw new WalletException("Balance should be greater than zero");
			}
			if (!wallet.getEmailId().matches("[a-z]+@[a-z]+\\.com")) {
			throw new WalletException("Enter valid emailid");
			}

			return walletDao.createAccount(wallet);
			}

	public double showBalance(String mobileNo) throws WalletException {
		if (!mobileNo.matches("\\d{10}")) {
			throw new WalletException("Mobile number should contain 10 digits");
		}
		return walletDao.showBalance(mobileNo);
	}
	public Wallet deposit(String mobileNo,double dep) throws WalletException {
		if (!mobileNo.matches("\\d{10}")) {
			throw new WalletException("Mobile number should contain 10 digits");
		}
	
		if (dep <= 0) {
			throw new WalletException(
					"Deposit amount must be greater than zero");
		}
		
		return walletDao.deposit(mobileNo,dep);
		 
	}
	
	public Wallet withdrawAmount(String mobileNo, double withdraw)
			throws WalletException {
		if (!mobileNo.matches("\\d{10}")) {
			throw new WalletException("Mobile number should contain 10 digits");
		}
		
		if ( withdraw <= 0) {
			throw new WalletException(
					"The amount to be withdrawn should be greater than available balance and greater than zero");
		}
	
		Wallet acc1 = walletDao.withdrawAmount(mobileNo,withdraw);
		return acc1;
	}
	
	public boolean fundTransfer(String srcMobile, String DestMobile,
			double amount) throws WalletException {
		if (!srcMobile.matches("\\d{10}")) {
			throw new WalletException("Mobile number should contain 10 digits");
		}
		if (!DestMobile.matches("\\d{10}")) {
			throw new WalletException("Mobile number should contain 10 digits");
		}
		IWalletService service = new WalletServiceImpl();
		Wallet acc1 = service.withdrawAmount(srcMobile, amount);
		Wallet d2 = service.deposit(DestMobile, amount);
		return true;
	}

	public Wallet printTransactionDetails(String mobileNo)
			throws WalletException {
		return walletDao.printTransaction(mobileNo);
	}
	
	
	private boolean validateCustomerName(String name) throws WalletException{
		
			if(!name.matches("[A-Z][A-Za-z]{3,}")){
				throw new WalletException("Name should start with a "
						+ "capital letter and should be only of alphabets");
			}else {
		
		return true;
			}
	}
	
	
	private boolean validateMoblieNo(String mobileNo) throws WalletException{
		if(!mobileNo.matches("\\d{10}")){
			throw new WalletException("Mobile number should contain 10 digits");
		}
		return true;
	}
	
	private boolean validateEmailId(String emailId)throws WalletException{
		
		if(!emailId.matches("[a-z0-9]{8,}[@][a-z]{5}\\.{1}[com]")){
			throw new WalletException("Invalid Email Id");
		}
		
		return true;
	}

	public boolean validateAccount(Wallet wallet) throws WalletException {
		if(validateCustomerName(wallet.getName())&& validateMoblieNo(wallet.getMobileNo())
				&& validateEmailId(wallet.getEmailId())){
			return true;
		}else{
			throw new WalletException("Invalid Data");
			
		}
		
	}

	public Wallet printTransaction(String mobile) throws WalletException {
		
		return walletDao.printTransaction(mobile);
	}

	

	

}
