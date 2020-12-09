package firstEvaluation;

public abstract class Loan extends BankAccount {

	Loan(String newAccountNo) {
		super(newAccountNo); 
		// TODO Auto-generated constructor stub
	}
	protected double rateOfInterest;  
	   abstract void getInterestRate(double rateOfInterest);  
	   
	   public double getCurrentBalance()
		  {
		    return amount;
		  }
	   public void depositAmount(double depositAmount)
		  {
		    amount += depositAmount;
		  }
	   public double calculateInterest(){  
	    	double interestAmount=amount*(amount* rateOfInterest/365);
			return interestAmount;  
	     }  
}

class HouseLoan extends Loan{
	HouseLoan(String newAccountNo) {
		super(newAccountNo);
		// TODO Auto-generated constructor stub
	}

	public void getInterestRate(double r){  
        rateOfInterest=r;  
   }  

}

class PersonalLoan extends Loan{

	PersonalLoan(String newAccountNo) {
		super(newAccountNo);
		// TODO Auto-generated constructor stub
	}

	@Override
	void getInterestRate(double r) {
		// TODO Auto-generated method stub
		rateOfInterest=r;
	}

}

class VehicleLoan extends Loan{

	VehicleLoan(String newAccountNo) {
		super(newAccountNo);
		// TODO Auto-generated constructor stub
	}

	@Override
	void getInterestRate(double r) {
		// TODO Auto-generated method stub
		rateOfInterest=r;
	}

}
