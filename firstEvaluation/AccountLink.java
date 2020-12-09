package firstEvaluation;


import java.io.IOException;


public class AccountLink {


	public BankAccount accountDetails(String accountType, String accountNumber) throws IOException {

		if (accountType.equalsIgnoreCase("Current Account")) {
			return new Current(accountNumber);
		} else if (accountType.equalsIgnoreCase("Savings Account")) {
			return new Savings(accountNumber);
		} else if (accountType.equalsIgnoreCase("Fixed Deposit Account")) {
			return new Fixed(accountNumber);
		} else if (accountType.equalsIgnoreCase("Reccuring Deposit Account")) {
			return new Reccuring(accountNumber);
		} else if (accountType.equalsIgnoreCase("Housing Loan Account")) {
			return new HouseLoan(accountNumber);
		} else if (accountType.equalsIgnoreCase("Vehicle Loan Account")) {
			return new VehicleLoan(accountNumber);
		} else if (accountType.equalsIgnoreCase("Personal Loan Account")) {
			return new PersonalLoan(accountNumber);
		}
		return null;
	}

}
