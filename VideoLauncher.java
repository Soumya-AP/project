import java.util.Scanner;

public class VideoLauncher {
	public static void main(String[] args) {
		VideoStore ob=new VideoStore("");
		int temp=1;
		int choice;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		while(temp==1)
		{
			System.out.println("MAIN MENU");
			System.out.println("==========");
			System.out.println("1.Add Videos:");
			System.out.println("2.Check Out Video:");
			System.out.println("3.Return Video:");
			System.out.println("4.Receive Rating:");
			System.out.println("5.List Inventory:");
			System.out.println("6.Exit:");
			System.out.println("Enter your choice (1..6):");
			choice = sc.nextInt();
			sc.nextLine();
			switch(choice)
			{
			case 1: System.out.println("Enter the name of video you want to add: ");   
					String str = sc.nextLine();
					ob.addVideo(str);
					System.out.println("\nVideo '"+ str +"' added successfuly.\n");
					break;		
			case 2: System.out.println("Enter the name of the video you want to check out: ");
					String str2 = sc.nextLine();
					ob.doCheckout(str2);
			break;		
			case 3: System.out.println("Enter the name of the video you want to return: ");
					String str3 = sc.nextLine();
					ob.doReturn(str3);
					break;		
			case 4: System.out.println("Enter the name of the video you want to Rate: ");
					String str4 = sc.nextLine();
					System.out.print("Enter the rating for this video: ");
					int rating = sc.nextInt();
					sc.nextLine();
					ob.receiveRating(str4, rating);
					break;
			case 5: System.out.println("\n----------------------------------------------------------"); 
					System.out.print(" Video Name\t|");
					System.out.print("\tCheckout Status\t|");
					System.out.println("\tRating");
					ob.listInventory();
					System.out.println("\n----------------------------------------------------------");
					break;		
			case 6: System.out.println("Exiting...!! Thanks for using the application.");
					System.exit(0);
					break;		
			default: System.out.println("Wrong choice");
	
			}	
		}
	}
		
}


