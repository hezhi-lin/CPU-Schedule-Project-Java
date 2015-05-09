import java.util.ArrayList;
import java.util.Collections;


public class RRSchedule {
	private ArrayList<Process> queue;
	private ArrayList<Integer> timeSheet;
	private int quantum_time;
	
	public RRSchedule (int num)
	{
		this.queue = new ArrayList<Process>();
		this.timeSheet = new ArrayList<Integer>();
		this.quantum_time = num;
	}
	
	public void addProcess ( Process p)
	{
		this.queue.add(p);
	}
	
	public void run()
	{	
		while(true)
		{
			for (Process p : this.queue)
			{
				for(int i = 0; i < quantum_time; i ++)
					{

						if (p.getBurstTime() == 0) { break;}
						this.timeSheet.add(p.getPID());
						p.setBurstTime( p.getBurstTime()-1);

					}
				
			}
			
			int sum = 0;
			for (Process p : this.queue)
			{
				sum = sum + p.getBurstTime();
			}
			
			if(sum == 0) break;
			
		}
	}
	
	public ArrayList<Integer> getTimeSheet() 
	{
		
		return this.timeSheet;
	}
}
