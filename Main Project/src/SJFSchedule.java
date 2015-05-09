import java.util.ArrayList;
import java.util.Collections;


public class SJFSchedule {
	private ArrayList<Process> queue;
	private ArrayList<Integer> timeSheet;
	
	public SJFSchedule ()
	{
		this.queue = new ArrayList<Process>();
		this.timeSheet = new ArrayList<Integer>();
	}
	
	public void addProcess ( Process p)
	{
		this.queue.add(p);
	}
	
	public void run()
	{	
		Collections.sort(this.queue);
		for (Process p : this.queue)
		{
			for(int i = 0; i < p.getBurstTime(); i ++)
				this.timeSheet.add(p.getPID());
		}
	}
	
	public ArrayList<Integer> getTimeSheet() 
	{
		
		return this.timeSheet;
	}
}
