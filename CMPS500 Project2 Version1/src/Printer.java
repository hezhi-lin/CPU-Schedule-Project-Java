import java.util.ArrayList;


public class Printer {
	private ArrayList <Integer> timeSheet;
	
	public void setTimeSheet(ArrayList <Integer> list)
	{
		this.timeSheet = list;
	}
	
	public void print()
	{
		for (int i = 0 ; i < 5 ; i ++)
		{
			String str = "P" + i;
			for(Integer pID : this.timeSheet)
			{
				if(pID == i)
				{
					str = str + "*";
				}else
				{
					str = str + " ";
				}
			}
			System.out.println(str);
		}
	}
	
}
