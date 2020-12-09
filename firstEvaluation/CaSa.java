package firstEvaluation;

import java.util.ArrayList;

public abstract class CaSa extends BankAccount{
	protected double rateOfInterest;  
    abstract void getRate(double rateOfInterest);  

    public double calculateInterest(){  
    	double interestAmount=amount*(amount* rateOfInterest/365);
		return interestAmount;  
     }  
	 
	
	CaSa(String newAccountNo) {
		super(newAccountNo);
		// TODO Auto-generated constructor stub
	}
	
	public void depositAmount(double depositAmount)
	  {
	    amount += depositAmount;
	  }

	  public boolean withdrawAmount(double withdrawAmount)
	  {
	    if (withdrawAmount > amount){    
	      System.out.println("Insufficient balance!!!");
	      return false;
	    } else {
	      amount -= withdrawAmount;
	      return true;
	    }
	  }
	  public double getCurrentBalance()
	  {
	    return amount;
	  }	
}

class Current extends CaSa {
	Current(String newAccountNo) {
		super(newAccountNo);
		// TODO Auto-generated constructor stub
	}

	@Override
	void getRate(double r) {
		// TODO Auto-generated method stub
		rateOfInterest=r;
	}
}

class Savings extends CaSa{

	Savings(String newAccountNo) {
		super(newAccountNo);
		// TODO Auto-generated constructor stub
	}
	@Override
	void getRate(double r) {
		// TODO Auto-generated method stub
		rateOfInterest=r;
	}
	
}

