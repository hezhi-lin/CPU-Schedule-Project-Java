import java.util.ArrayList;
import java.util.Collections;
public class PriSchedule {




		private ArrayList<Process> queue;
		private ArrayList<Integer> timeSheet;
		
		public PriSchedule ()
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
			Collections.sort(this.queue,Process.ProcessPriorityComparator);
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
		
//		public void print()
//		{
//			System.out.println(this.timeSheet);
//		}
}
