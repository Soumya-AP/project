
public class Video {
	String[] videoName=new String[10];
	boolean[]checkout=new boolean[10];
	int[] rating=new int[10];
	
	Video(String name) {
		;// TODO Auto-generated constructor stub
	}
	void setName(int i, String name )
	{  
		videoName[i] = name;
	}
	
	String getName(int i)
	{
		return videoName[i];
	}
	void doCheckout(int i)
	{
		if(checkout[i]==false)
			checkout[i]=true;
	}
	void doReturn(int i)
	{
		if(checkout[i]==true)
			checkout[i]=false;
	}
	void receiveRating(int i,int rating)
	{
		this.rating[i]=rating;
	}
	int getRating(int i)
	{
		return rating[i];
	}
	boolean getCheckout(int i)
	{
		return checkout[i];
	}

}
