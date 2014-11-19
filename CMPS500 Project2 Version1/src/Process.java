import java.util.Comparator;




public class Process implements Comparable<Process>{
	private int burstTime;
	private int priority;
	private int pID;
	
	public Process (int burstTime, int priority, int pID)
	{
		this.burstTime = burstTime;
		this.priority = priority;
		this.pID = pID;
	}
	
	public void setBurstTime (int n){ this.burstTime = n;}
	public void setPriority (int n) {this.priority = n;}
	public void setpID (int n) {this.pID = n;}
	
	public int getBurstTime (){return this.burstTime;}
	public int getPriority () {return this.priority;}
	public int getPID() {return this.pID;}
	
	public String toString()
	{
		return this.pID + "   " + this.priority + "    " + this.burstTime;
	}
	
	@Override
	public int compareTo(Process p) {
		// TODO Auto-generated method stub
		return this.burstTime - p.burstTime;
	}
	
	public static Comparator<Process> ProcessPriorityComparator = new Comparator <Process>() {
	
			@Override
			public int compare(Process o1, Process o2) {
				// TODO Auto-generated method stub
				return o1.priority - o2.priority;
			}
			
	};
	
}
