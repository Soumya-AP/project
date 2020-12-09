package firstEvaluation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AccountManager {
	Scanner input = new Scanner(System.in);
	HashMap<String,BankAccount> map= new HashMap<String,BankAccount>();
	void CreateAccounts(int count) throws IOException {
	
	AccountLink account=new AccountLink();
	
	
	BankAccount[] person = new BankAccount[10]; 
	 
	int i=0;
	while(i<count) {
	System.out.println("Enter the Account type to be created:");
	System.out.println("--> Current Account");
	System.out.println("-->Savings Account");
	System.out.println("-->Fixed Deposit Account");
	System.out.println("-->Reccuring Deposit Account");
	System.out.println("--> Housing Loan Account");
	System.out.println("--> Vehicle Loan Account");
	System.out.println("--> Personal Loan Account");
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
    String accountName=br.readLine();
    long number=(long)Math.floor(Math.random()*9000000000000L)+1000000000000L;
	String accountNumber = Long.toString(number);
	person[i] = account.accountDetails(accountName, accountNumber);
	System.out.println("Enter your name: ");
	br = new BufferedReader(new InputStreamReader(System.in));
	String accountHolderName = br.readLine();
	person[i].setAccountHolderName(accountHolderName);
	System.out.println("Enter the initial amount you want to deposit: ");
	person[i].amount = input.nextInt();
	person[i].addToHistory("Initial amount: " + person[i].amount);
	System.out.println("Do you want to link your emailId and Mobile number(yes/no): ");
	
    String option=br.readLine();
    if(option.equalsIgnoreCase("yes"))
    {
    	System.out.println("Mobile Number:");
    	person[i].mobNumber=br.readLine();
    	System.out.println("EmailId:");
    	person[i].mailId=br.readLine();
    }
    map.put(person[i].getAccountNo(),person[i]);
	i++;
	System.out.println("Account created successfully");
	System.out.println("Your account number: "+accountNumber+"\n");
	}
	}
	
	void OperationOnAccount() throws IOException {
	for(Map.Entry<String,BankAccount> entry:map.entrySet()){  
		int temp=1;

        System.out.println("Enter the account number on which you wish to perform Operation");
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String number=br.readLine();
    	BankAccount b= map.get(number);
        while(temp==1) {
    	    System.out.println("Operations you wish to perform:");
    	    System.out.println("1.Deposit");
    	    System.out.println("2.Withdrawal");
    	    System.out.println("3.Transaction History");
    	    System.out.println("4.Account Details");
    	    System.out.println("5.Interest Amount");
    	    System.out.println("6.Exit");
    	    int type= input.nextInt();
    	    
    	    switch(type)
    	    {
    	    case 1:
    	    	if(b instanceof Current)
    	    	{
    	    		System.out.println("Enter the amount you want to deposit");
    	    		double deposit=input.nextDouble();
    	    		((Current)b).depositAmount(deposit);
    	    		((Current)b).addToHistory("Deposit: "+deposit);
    	    		
    	    	}
    	    	else if(b instanceof Savings)
    	    	{
    	    		System.out.println("Enter the amount you want to deposit");
    	    		double deposit=input.nextDouble();
    	    		((Savings)b).depositAmount(deposit);
    	    		((Savings)b).addToHistory("Deposit: "+deposit);
    	    	}
    	    	else if(b instanceof Fixed)
    	    	{
    	    		System.out.println("Only initial deposit is allowed");
    	    	}
    	    	else if(b instanceof Reccuring) {
    	    		System.out.println("Enter the amount you want to deposit");
    	    		double deposit=input.nextDouble();
    	    		((Reccuring)b).depositAmount(deposit);
    	    		((Reccuring)b).addToHistory("Deposit: "+deposit);
    	    	}
    	    	else if(b instanceof HouseLoan) {
    	    	System.out.println("Enter the amount you want to deposit");
    	    	double deposit=input.nextDouble();
        		((HouseLoan)b).depositAmount(deposit);
        		((HouseLoan)b).addToHistory("Deposit: "+deposit);
    	    	}
    	    	else if(b instanceof VehicleLoan) {
    		    	System.out.println("Enter the amount you want to deposit");
    		    	double deposit=input.nextDouble();
    	    		((VehicleLoan)b).depositAmount(deposit);
    	    		((VehicleLoan)b).addToHistory("Deposit: "+deposit);
    		    	}
    	    	else if(b instanceof PersonalLoan) {
    		    	System.out.println("Enter the amount you want to deposit");
    		    	double deposit=input.nextDouble();
    	    		((PersonalLoan)b).depositAmount(deposit);
    	    		((PersonalLoan)b).addToHistory("Deposit: "+deposit);
    		    	}
    	    	break;
    	    	
    	    case 2:
    	    	if(b instanceof Current)
    	    	{
    	    		System.out.println("Enter the amount you want to withdraw");
    	    		double withdraw=input.nextDouble();
    	    		((Current)b).withdrawAmount(withdraw);
    	    		((Current)b).addToHistory("Withdrawal: "+withdraw);
    	    		
    	    	}
    	    	else if(b instanceof Savings)
    	    	{
    	    		System.out.println("Enter the amount you want to withdraw");
    	    		double withdraw=input.nextDouble();
    	    		((Savings)b).withdrawAmount(withdraw);
    	    		((Savings)b).addToHistory("Withdrawal: "+withdraw);
    	    	}
    	    	else if(b instanceof Fixed)
    	    	{
    	    		System.out.println("Withdrawal is not allowed");
    	    	}
    	    	else if(b instanceof Reccuring ||b instanceof PersonalLoan ||b instanceof VehicleLoan ||b instanceof HouseLoan) {
    	    		System.out.println("Withdrawal is not allowed");
    	    	}
    	    	
    	    	break;
    	    case 3:
    	    	if(b instanceof Current)
    	    	{
    	    		System.out.println("Name: "+((Current)b).getAccountHolderName());
    	    		System.out.println("Account number: "+((Current)b).getAccountNo());
    	    		((Current)b).printHistory();
    	    		
    	    	}
    	    	else if(b instanceof Savings)
    	    	{
    	    		System.out.println("Name: "+((Savings)b).getAccountHolderName());
    	    		System.out.println("Account number: "+((Savings)b).getAccountNo());
    	    		((Savings)b).printHistory();
    	    	}
    	    	else if(b instanceof Fixed)
    	    	{
    	    		System.out.println("Name: "+((Fixed)b).getAccountHolderName());
    	    		System.out.println("Account number: "+((Fixed)b).getAccountNo());
    	    		((Fixed)b).printHistory();
    	    	}
    	    	else if(b instanceof Reccuring) {
    	    		System.out.println("Name: "+((Reccuring)b).getAccountHolderName());
    	    		System.out.println("Account number: "+((Reccuring)b).getAccountNo());
    	    		((Reccuring)b).printHistory();
    	    	}
    	    	else if(b instanceof HouseLoan) {
    	    		System.out.println("Name: "+((HouseLoan)b).getAccountHolderName());
    	    		System.out.println("Account number: "+((HouseLoan)b).getAccountNo());
    	    		((HouseLoan)b).printHistory();
    	    	}
    	    	else if(b instanceof VehicleLoan) {
    	    		System.out.println("Name: "+((VehicleLoan)b).getAccountHolderName());
    	    		System.out.println("Account number: "+((VehicleLoan)b).getAccountNo());
    	    		((VehicleLoan)b).printHistory();
    	    	}
    	    	else if(b instanceof PersonalLoan) {
    	    		System.out.println("Name: "+((PersonalLoan)b).getAccountHolderName());
    	    		System.out.println("Account number: "+((PersonalLoan)b).getAccountNo());
    	    		((PersonalLoan)b).printHistory();
    	    	}
    	    	break;
    	    case 4:
    	    	if(b instanceof Current)
    	    	{
    	    		System.out.println("Name: "+((Current)b).getAccountHolderName());
    	    		System.out.println("Account number: "+((Current)b).getAccountNo());
    	    		System.out.println("Account Type: "+((Current)b).getClass().getSimpleName());
    	    		System.out.println("Mobile number: "+((Current)b).getMobNumber());
    	    		System.out.println("EmailId: "+((Current)b).getMailId());
    	    		System.out.println("Account number: "+((Current)b).getCurrentBalance());
    	    	}
    	    	else if(b instanceof Savings)
    	    	{
    	    		System.out.println("Name: "+((Savings)b).getAccountHolderName());
    	    		System.out.println("Account number: "+((Savings)b).getAccountNo());
    	    		System.out.println("Account Type: "+((Savings)b).getClass().getSimpleName());
    	    		System.out.println("Mobile number: "+((Savings)b).getMobNumber());
    	    		System.out.println("EmailId: "+((Savings)b).getMailId());
    	    		System.out.println("Account number: "+((Savings)b).getCurrentBalance());
    	    	}
    	    	else if(b instanceof Fixed)
    	    	{
    	    		System.out.println("Name: "+((Fixed)b).getAccountHolderName());
    	    		System.out.println("Account number: "+((Fixed)b).getAccountNo());
    	    		System.out.println("Account Type: "+((Fixed)b).getClass().getSimpleName());
    	    		System.out.println("Mobile number: "+((Fixed)b).getMobNumber());
    	    		System.out.println("EmailId: "+((Fixed)b).getMailId());
    	    		System.out.println("Account number: "+((Fixed)b).getCurrentBalance());
    	    	}
    	    	else if(b instanceof Reccuring) {
    	    		System.out.println("Name: "+((Reccuring)b).getAccountHolderName());
    	    		System.out.println("Account number: "+((Reccuring)b).getAccountNo());
    	    		System.out.println("Account Type: "+((Reccuring)b).getClass().getSimpleName());
    	    		System.out.println("Mobile number: "+((Reccuring)b).getMobNumber());
    	    		System.out.println("EmailId: "+((Reccuring)b).getMailId());
    	    		System.out.println("Account number: "+((Reccuring)b).getCurrentBalance());
    	    	}
    	    	else if(b instanceof HouseLoan) {
    	    		System.out.println("Name: "+((HouseLoan)b).getAccountHolderName());
    	    		System.out.println("Account number: "+((HouseLoan)b).getAccountNo());
    	    		System.out.println("Account Type: "+((HouseLoan)b).getClass().getSimpleName());
    	    		System.out.println("Mobile number: "+((HouseLoan)b).getMobNumber());
    	    		System.out.println("EmailId: "+((HouseLoan)b).getMailId());
    	    		System.out.println("Account number: "+((HouseLoan)b).getCurrentBalance());
    	    	}
    	    	else if(b instanceof VehicleLoan) {
    	    		System.out.println("Name: "+((VehicleLoan)b).getAccountHolderName());
    	    		System.out.println("Account number: "+((VehicleLoan)b).getAccountNo());
    	    		System.out.println("Account Type: "+((VehicleLoan)b).getClass().getSimpleName());
    	    		System.out.println("Mobile number: "+((VehicleLoan)b).getMobNumber());
    	    		System.out.println("EmailId: "+((VehicleLoan)b).getMailId());
    	    		System.out.println("Account number: "+((VehicleLoan)b).getCurrentBalance());
    	    	}
    	    	else if(b instanceof PersonalLoan) {
    	    		System.out.println("Name: "+((PersonalLoan)b).getAccountHolderName());
    	    		System.out.println("Account number: "+((PersonalLoan)b).getAccountNo());
    	    		System.out.println("Account Type: "+((PersonalLoan)b).getClass().getSimpleName());
    	    		System.out.println("Mobile number: "+((PersonalLoan)b).getMobNumber());
    	    		System.out.println("EmailId: "+((PersonalLoan)b).getMailId());
    	    		System.out.println("Account number: "+((PersonalLoan)b).getCurrentBalance());
    	    	}
    	    	break;
    	    case 5:
    	    	if(b instanceof Current)
    	    	{
    	    		((Current)b).getRate(5);
    	    		System.out.println(((Current)b).calculateInterest());	
    	    	}
    	    	else if(b instanceof Savings)
    	    	{
    	    		((Savings)b).getRate(3.5);
    	    		System.out.println(((Savings)b).calculateInterest());	
    	    	}
    	    	else if(b instanceof Fixed)
    	    	{
    	    		((Fixed)b).getRate(9);
    	    		System.out.println("Enter the total term of deposit:");
    	    		((Fixed)b).termOfDeposit=input.nextDouble();
    	    		System.out.println(((Fixed)b).CalculateInterest());	
    	    	}
    	    	else if(b instanceof Reccuring) {
    	    		((Reccuring)b).getRate(5);
    	    		System.out.println("Enter the total term of deposit:");
    	    		((Reccuring)b).termOfDeposit=input.nextDouble();
    	    		System.out.println(((Reccuring)b).CalculateInterest());	
    	    	}
    	    	else if(b instanceof HouseLoan) {
    	    		((HouseLoan)b).getInterestRate(5.7);
    	    		System.out.println(((HouseLoan)b).calculateInterest());	
    	    	}
    	    	else if(b instanceof VehicleLoan) {
    	    		((VehicleLoan)b).getInterestRate(5);
    	    		System.out.println(((VehicleLoan)b).calculateInterest());	
    	    	}
    	    	else if(b instanceof PersonalLoan) {
    	    		((PersonalLoan)b).getInterestRate(5);
    	    		System.out.println(((PersonalLoan)b).calculateInterest());	
    	    	}
    	    	break;
    	    	
    	    	
    	    case 6:System.out.println("Exiting... Thank you for using the application");
    	    		System.exit(0);
    	    	 break;
    	    default: System.out.println("Wrong Choice!!");
    	    }
    	   
    	   }
        input.close();
	}
}
}  
	
	


