package com.cg.wallet.testApp;
import static org.junit.Assert.*;

//import static org.junit.Assert.assertNotEquals;
/*import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;*/

import org.junit.Before;
import org.junit.Test;

import com.cg.wallet.bean.Wallet;
import com.cg.wallet.walletException.WalletException;
import com.cg.wallet.walletService.IWalletService;
import com.cg.wallet.walletService.WalletServiceImpl;




public class SampleTest {

		private IWalletService service;

		@Before

		public void init() {
			service = new WalletServiceImpl();
		}

	 
		@Test
		public void testCreateAccountForValidMobile() {
			Wallet ac = new Wallet();
			ac.setMobileNo("1234897565");
			ac.setName("Marria");
			ac.setEmailId("marria@cg.com");
			ac.setBalance(200.0);
			try {
				service.createAccount(ac);
			} catch (WalletException e) {
				assertEquals("Mobile number should contain 10 digits", e.getMessage());
			}
		}
		
		
		/*@Test
		public void testCreateAccountForInvalidName() {
			Wallet ac = new Wallet();
			ac.setMobileNo("8185800166");
			ac.setName("Miya12");
			ac.setEmailId("miya@cg.com");
			ac.setBalance(500.0);
			try {
				service.createAccount(ac);
			} catch (WalletException e) {
				assertEquals("Name should start with capital letter and should contain only Alphabets", e.getMessage());
			}
		}*/
		
		/*
		@Test
		public void testCreateAccountForNameIsEmpty() {
			Wallet ac = new Wallet();
			ac.setMobileNo("8185800167");
			ac.setName("");
			ac.setEmailId("Keira@cg.com");
			ac.setBalance(200.0);
			try {
				service.createAccount(ac);
			} catch (WalletException e) {
				assertEquals("Name can't be empty", e.getMessage());
			}
		}	*/
		
		@Test
		public void testCreateAccountForValidEmailId() {
			Wallet ac = new Wallet();
			ac.setMobileNo("9948032465");
			ac.setName("Benji");
			ac.setEmailId("benji@cg.com");
			ac.setBalance(200.0);
			try {
				service.createAccount(ac);
			} catch (WalletException e) {
				assertEquals("Enter valid emailid", e.getMessage());
			}
		}
		
		@Test
		public void testCreateAccount() {
			Wallet ac = new Wallet();
			ac.setMobileNo("5555555555");
			ac.setName("Judy");
			ac.setEmailId("judy@gmail.com");
			ac.setBalance(200.0);
				try {
					String s=service.createAccount(ac);
					assertNotNull(s);
				} catch (WalletException e) {
							

				}
		}	
		
		@Test
		public void testShowBalanceForInvalidMobileNo() {
			
			try {
				service.showBalance("818580");
			} catch (WalletException e) {
				assertEquals("Mobile number should contain 10 digits",e.getMessage());
			}
		}	

		
		@Test
		public void testShowBalanceForMobileNoDoesNotExist() {
			
			try {
				service.showBalance("818580166");
			} catch (WalletException e) {			
				assertEquals("Mobile number should contain 10 digits",e.getMessage());
			}
		}
		
		@Test
		public void testDepositForMobileNo() {
			Wallet ac=new Wallet();
			ac.setMobileNo("818580016");
			try {
				service.deposit(ac.getMobileNo(), 230);
			} catch (WalletException e) {			
				assertEquals("Mobile number should contain 10 digits",e.getMessage());
			}
		}
		
		/*@Test
		public void testDepositForMobileNoDoesNotExist() {
			Wallet ac=new Wallet();
			ac.setMobileNo("7995522030");
			try {
				service.deposit(ac.getMobileNo(), 230);
			} catch (WalletException e) {
				assertEquals("Mobile Number should contain 10 digits",e.getMessage());
			}
		}*/
		
		@Test
		public void testDepositForDepositAmt1() {
			Wallet ac=new Wallet();
			ac.setMobileNo("8185800166");
			try {
				service.deposit(ac.getMobileNo(), -230);
			} catch (WalletException e) {
				assertEquals("Deposit amount must be greater than zero",e.getMessage());
			}
		}	
		
		@Test
		public void testDeposit() {
			Wallet ac=new Wallet();
			ac.setMobileNo("7777777777");
			try {
				Wallet ac1=service.deposit(ac.getMobileNo(), 230);
				assertNotNull(ac1);
			} catch (WalletException e) {
				System.out.println(e.getMessage());
				}
		}	
		
		@Test
		public void testWithDrawForMobileNo() {
			Wallet ac=new Wallet();
			ac.setMobileNo("7777777777");
			try {
				service.withdrawAmount(ac.getMobileNo(), 230);
			} catch (WalletException e) {
				assertEquals("Mobile number should contain 10 digits",e.getMessage());
			}
		}
		
		@Test
		public void testWithdrawForMobileNoDoesNotExist() {
			Wallet ac=new Wallet();
			ac.setMobileNo("8185800166");
			try {
				service.withdrawAmount(ac.getMobileNo(), 230);
			} catch (WalletException e) {
				//assertNotEquals("The mobile number does not exist",e.getMessage());
			}
		}
		
		/*@Test
		public void testWithdrawForMobileNoDoesNotExist1() {
			Wallet ac=new Wallet();
			ac.setMobileNo("8185800167");
			try {
				service.withdrawAmount(ac.getMobileNo(), 230);
			} catch (WalletException e) {
				assertEquals("The mobile number does not exist",e.getMessage());
			}
		}*/
		
		@Test
		public void testWithdrawForMobileNoDoesNotExist2() {
			Wallet ac=new Wallet();
			ac.setMobileNo("8888888888");
			try {
				service.withdrawAmount(ac.getMobileNo(), 230);
			} catch (WalletException e) {
				//assertNotEquals("The mobile number does not exist",e.getMessage());
			}
		}
		
		@Test
		public void testWithdrawForMobileNoDoesNotExist3() {
			Wallet ac=new Wallet();
			ac.setMobileNo("8888888888");
			try {
				service.withdrawAmount(ac.getMobileNo(), 230);
			} catch (WalletException e) {
				//assertNotEquals("The mobile number does not exist",e.getMessage());
			}
		}
		
		@Test
		public void testWithdrawForAmt() {
			Wallet ac=new Wallet();
			ac.setMobileNo("7777777777");
			try {
				service.withdrawAmount(ac.getMobileNo(), -230);
			} catch (WalletException e) {
				assertEquals("The amount to be withdrawn should be greater than available balance and greater than zero",e.getMessage());
			}
		}
		
		@Test
		public void testWithdrawForAmt1() {
			Wallet ac=new Wallet();
			ac.setMobileNo("7777777777");
			try {
				service.withdrawAmount(ac.getMobileNo(), 230);
			} catch (WalletException e) {
				assertEquals("The amount to be withdrawn should be greater than available balance and greater than zero",e.getMessage());
			}
		}
		
		@Test
		public void testWithdrawForAmt2() {
			Wallet ac=new Wallet();
			ac.setMobileNo("8888888888");
			try {
				service.withdrawAmount(ac.getMobileNo(), -230);
			} catch (WalletException e) {
				assertEquals("The amount to be withdrawn should be greater than available balance and greater than zero",e.getMessage());
			}
		}	
		
		@Test
		public void testWithdrawForInvalidAmt() {
			Wallet ac=new Wallet();
			ac.setMobileNo("8185800167");
			try {
				service.withdrawAmount(ac.getMobileNo(), -230);
			} catch (WalletException e) {
				assertEquals("The amount to be withdrawn should be greater than available balance and greater than zero",e.getMessage());
			}
		}
		
		@Test
		public void testFundTransferForMobileNo() {
			Wallet ac=new Wallet();
			Wallet ac2=new Wallet();
			ac.setMobileNo("818580");
			ac2.setMobileNo("1234");
			try {
				service.fundTransfer(ac.getMobileNo(),ac2.getMobileNo(), 230);
			} catch (WalletException e) {
				assertEquals("Mobile number should contain 10 digits",e.getMessage());
			}
		}
		
		@Test

		public void testFundTransferForMobileNoDoesNotExist() {
			Wallet ac=new Wallet();
			Wallet ac2=new Wallet();
			ac.setMobileNo("7777777777");
			ac2.setMobileNo("8185800165");
			try {
				service.fundTransfer(ac.getMobileNo(), ac2.getMobileNo(),  230);
			} catch (WalletException e) {
				//assertNotEquals("The mobile number does not exist",e.getMessage());
			}
		}
		
		@Test
		public void testFundTransferForAmt() {
			Wallet ac=new Wallet();
			Wallet ac2=new Wallet();
			ac.setMobileNo("7777777777");
			ac2.setMobileNo("8185800165");
			try {
				service.fundTransfer(ac.getMobileNo(), ac2.getMobileNo(),  -230);
			} catch (WalletException e) {
			assertEquals("The amount to be withdrawn should be greater than available balance and greater than zero",e.getMessage());
			}
		}
		
		@Test
		public void testFundTransfer() {
			Wallet ac=new Wallet();
			Wallet ac2=new Wallet();
			ac.setMobileNo("7777777777");
			ac2.setMobileNo("9948032465");
			try {
				assertTrue(service.fundTransfer(ac.getMobileNo(), ac2.getMobileNo(),  230));
			} catch (WalletException e) {
				System.out.println(e.getMessage());
			}
		}
		
		@Test
		public void testPrinttransactionDetails() {
			Wallet ac=new Wallet();
			ac.setMobileNo("9948032465");
			try {
				Wallet acc=service.printTransaction(ac.getMobileNo());
				assertNotNull(acc);
			} catch (WalletException e) {
				System.out.println(e.getMessage());
			}
		}
		
		@Test
		public void testPrinttransactionDetails1() {
			Wallet ac=new Wallet();
			ac.setMobileNo("8185800166");
			try {
				Wallet acc=service.printTransaction(ac.getMobileNo());
				assertNotNull(acc);
			} catch (WalletException e) {
				System.out.println(e.getMessage());
			}
		}
		
		@Test
		public void testPrinttransactionDetails2() {
			Wallet ac=new Wallet();
			ac.setMobileNo("8185800165");
			try {
				Wallet acc=service.printTransaction(ac.getMobileNo());
				assertNotNull(acc);
			} catch (WalletException e) {
				System.out.println(e.getMessage());
			}
		}
		
		@Test
		public void testPrinttransactionDetails3() {
			Wallet ac=new Wallet();
			ac.setMobileNo("8185800167");
			try {
				Wallet acc=service.printTransaction(ac.getMobileNo());
				assertNotNull(acc);
			} catch (WalletException e) {
				System.out.println(e.getMessage());

			}
		}

	}



