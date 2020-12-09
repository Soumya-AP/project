package firstEvaluation;

public abstract class Deposit extends BankAccount {

	double termOfDeposit;
	protected double rateOfInterest;  
    abstract void getRate(double rateOfInterest);  

	Deposit(String newAccountNo) {
		super(newAccountNo);
		// TODO Auto-generated constructor stub
	}
	public double getRateOfInterest() {
		return rateOfInterest;
	}
	public void setRateOfInterest(double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}
	public double getTermOfDeposit() {
		return termOfDeposit;
	}
	public void setTermOfDeposit(double termOfDeposit) {
		this.termOfDeposit = termOfDeposit;
	}
	
	public double CalculateInterest() {
		amount=amount+(amount* getRateOfInterest()*getTermOfDeposit());
		return amount;
	}

	public double getCurrentBalance()
	  {
	    return amount;
	  }
}

class Fixed extends Deposit{

	Fixed(String newAccountNo) {
		super(newAccountNo);
		// TODO Auto-generated constructor stub
	}
	@Override
	void getRate(double r) {
		// TODO Auto-generated method stub
		rateOfInterest=r;
	}

}

class Reccuring extends Deposit{

	Reccuring(String newAccountNo) {
		super(newAccountNo);
		// TODO Auto-generated constructor stub
	}
	public void depositAmount(double depositAmount)
	  {
	    amount += depositAmount;
	    
	  }
	@Override
	void getRate(double r) {
		// TODO Auto-generated method stub
		rateOfInterest=r;
	}
	
}
