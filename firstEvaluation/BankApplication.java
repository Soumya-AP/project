package firstEvaluation;

import java.io.IOException;
import java.util.Scanner;



public class BankApplication {
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		AccountManager obj=new AccountManager();
		int temp=1;
		while(temp==1)
		{
		System.out.println("Welcome!! ");
		System.out.println("1.Create Account");
		System.out.println("2.Perform other operations");
		System.out.println("3.Exit");
		System.out.println("Enter your choice:");
		int choice=sc.nextInt();
		
		switch(choice)
		{
		case 1: System.out.println("Enter the number of accounts to be created:");
		int count=sc.nextInt();
		obj.CreateAccounts(count);
		break;
		case 2:
		obj.OperationOnAccount();
		break;
		case 3:
			System.exit(0);
		default:
			System.out.println("Enter a valid choice");
		}
	}
		sc.close();
	}
		
}
