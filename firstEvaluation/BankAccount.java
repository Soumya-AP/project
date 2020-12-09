package firstEvaluation;

import java.util.ArrayList;
import java.util.Date;

public class BankAccount  {
	private String accountNo="0000000000000";
    private String accountHolderName;
    protected double amount;
    
    String mailId=null;
	String mobNumber=null;
   // protected Date joinDate;
    
/*
	public boolean accountExist(Object o) {
        if (this.accountNo == o) {
            return true;
        }
        BankAccount obj=(BankAccount)o;
		return accountNo==obj.accountNo;
        
    }*/
    BankAccount(String newAccountNo){
    	if(newAccountNo.length()==13) {
    		//if(accountExist(newAccountNo)==(false))
    		{
    			accountNo=newAccountNo;
    		}
    	}
    }
 
		public void setAmount(double initialAmount) {
			this.amount = initialAmount;
		}
	
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getMobNumber() {
		return mobNumber;
	}
	public void setMobNumber(String mobNumber) {
		this.mobNumber = mobNumber;
	}
	
	
	public String getAccountNo() {
		return accountNo;
	}
	public String getAccountHolderName() {
		return accountHolderName;
	}
	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}
	ArrayList<String> transactionHistory = new ArrayList<String>();

	  void addToHistory(String transaction) {
		  transactionHistory.add(transaction);
	  }

	  void printHistory() {
	      for(String s : transactionHistory) {
	          System.out.println(s);
	      }
	  }
    
}
