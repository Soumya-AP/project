
public class VideoStore extends Video {
	int i=0;
	int j,index; 

	VideoStore(String name) 
	{
		super(name);
	}
	void addVideo(String name)
	{
		setName(i,name);
		i++;
	}
	void doCheckout(String name)
	{
		
		index=0;
		for(j=0;j<i;j++)
		{
			if(videoName[j].equals(name))
			{
				index=j;
				break;
			}
		}
		if(j<i)
		{
			doCheckout(j);
			System.out.println("\nVideo '"+ name +"' checked out successfuly.\n");
		}
		else
			System.out.println("\nSorry!! Video '"+name+"' is not found \n");
		
	}
	void doReturn(String name)
	{
		index=0;
		for(j=0;j<i;j++)
		{
			if(videoName[j].equals(name))
			{
				index=j;
				break;
			}
		}
		if(j<i)
		{
			doReturn(j);
			System.out.println("\nVideo '"+ name +"' returned successfuly.\n");
		}
		else
			System.out.println("\nSorry!! Video '"+name+"' is not found \n");
		
		
	}
	void receiveRating(String name,int rating)
	{
		index=0;
		for(j=0;j<i;j++)
		{
			if(videoName[j].equals(name))
			{
				index=j;
				break;
			}
		}
		if(j<i)
		{
			receiveRating(j,rating);
			System.out.println("\nRating '" + rating + "' has been mapped to the Video '" + name + "'.\n");
		}
		else
			System.out.println("\nSorry!! Video "+name+" is not found \n");
		
	}
	void listInventory() 
	{
		for(j=0;j<i;j++)
		{
		System.out.print(" "+videoName[j]+"\t\t|\t"+getCheckout(j)+"\t\t|\t"+getRating(j));
		System.out.println();
		}
		
	}
	

}
