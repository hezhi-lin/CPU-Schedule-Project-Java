import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

public class CPUSchedule {
	 static Scanner scan=new Scanner(System.in);	
	 
	 static int process_burst_time[]= new int [5];   //process burst time array
	 static String spbt;
	 static int process_priority[]= new int [5];     //process priority array
	 static String sp;
	 static int quantum_time;    //use this variable for further quantum time calculations 
	 static String sqt;
	
	

	public static void main(String[] args) throws Exception {
		
		String line;
		FileReader fileReader = new FileReader("heading.txt");
		BufferedReader bufferedReader = new BufferedReader(fileReader);


		while ((line = bufferedReader.readLine()) != null) {
			System.out.println(line);// Deal with the line
		}
		bufferedReader.close();
		
		CPUSchedule.inputValidationMethod();
		CPUSchedule.printingUserInputs();
		
		Process p0 = new Process(process_burst_time[0], process_priority[0], 0);
		Process p1 = new Process(process_burst_time[1], process_priority[1], 1);
		Process p2 = new Process(process_burst_time[2], process_priority[2], 2);
		Process p3 = new Process(process_burst_time[3], process_priority[3], 3);
		Process p4 = new Process(process_burst_time[4], process_priority[4], 4);
		FCFSSchedule fcfs = new FCFSSchedule();
		Printer printer = new Printer();
		
		System.out.println();		
		System.out.println("First Come First Served <FCFS>:");
		System.out.println();
		fcfs.addProcess(p0);
		fcfs.addProcess(p1);
		fcfs.addProcess(p2);
		fcfs.addProcess(p3);
		fcfs.addProcess(p4);
		fcfs.run();
		printer.setTimeSheet(fcfs.getTimeSheet());
		printer.print();
		
		System.out.println();		
		System.out.println("Shortest Job First <SJF>:");
		System.out.println();
		SJFSchedule sjf = new SJFSchedule();
		sjf.addProcess(p0);
		sjf.addProcess(p1);
		sjf.addProcess(p2);
		sjf.addProcess(p3);
		sjf.addProcess(p4);
		sjf.run();
		printer.setTimeSheet(sjf.getTimeSheet());
		printer.print();
		
		System.out.println();		
		System.out.println("Priority <PriOne> with a lower number being a higher priority");
		System.out.println();
		PriSchedule pri = new PriSchedule();
		pri.addProcess(p0);
		pri.addProcess(p1);
		pri.addProcess(p2);
		pri.addProcess(p3);
		pri.addProcess(p4);
		pri.run();
		printer.setTimeSheet(pri.getTimeSheet());
		printer.print();
		
		System.out.println();		
		System.out.println("Priority <PriTwo>  with a higher number being a higher priority");
		System.out.println();
		PriSchedule2 pri2 = new PriSchedule2();
		pri2.addProcess(p0);
		pri2.addProcess(p1);
		pri2.addProcess(p2);
		pri2.addProcess(p3);
		pri2.addProcess(p4);
		pri2.run();
		printer.setTimeSheet(pri2.getTimeSheet());
		printer.print();
		
		System.out.println();		
		System.out.println("Round Robin <RR>:");
		System.out.println();
		RRSchedule rr = new RRSchedule(quantum_time);
		rr.addProcess(p0);
		rr.addProcess(p1);
		rr.addProcess(p2);
		rr.addProcess(p3);
		rr.addProcess(p4);
		rr.run();
		printer.setTimeSheet(rr.getTimeSheet());
		printer.print();
		
		System.out.println();
		System.out.println("*************************************************************************");
		System.out.println();
		System.out.println("Turnaround Time for each Process and Average Turnaround");
		System.out.println("Time for each Algorithm");
		System.out.println();
		System.out.println("Process      FCFS      SJF      PriOne      PriTwo      RR");
		System.out.println("-------      ----      ---      ------      ------      --");		
		System.out.println("P0           "+calcTurnaround(0,fcfs.getTimeSheet())+"         "+calcTurnaround(0,sjf.getTimeSheet())+"          "
										  +calcTurnaround(0,pri.getTimeSheet())+"         "+calcTurnaround(0,pri2.getTimeSheet())+"           "
										  +calcTurnaround(0,rr.getTimeSheet()));
		System.out.println("P1           "+calcTurnaround(1,fcfs.getTimeSheet())+"         "+calcTurnaround(1,sjf.getTimeSheet())+"          "
				  +calcTurnaround(1,pri.getTimeSheet())+"         "+calcTurnaround(1,pri2.getTimeSheet())+"           "
				  +calcTurnaround(1,rr.getTimeSheet()));
		System.out.println("P2           "+calcTurnaround(2,fcfs.getTimeSheet())+"         "+calcTurnaround(2,sjf.getTimeSheet())+"          "
				  +calcTurnaround(2,pri.getTimeSheet())+"         "+calcTurnaround(2,pri2.getTimeSheet())+"           "
				  +calcTurnaround(2,rr.getTimeSheet()));
		System.out.println("P3           "+calcTurnaround(3,fcfs.getTimeSheet())+"         "+calcTurnaround(3,sjf.getTimeSheet())+"          "
				  +calcTurnaround(3,pri.getTimeSheet())+"         "+calcTurnaround(3,pri2.getTimeSheet())+"           "
				  +calcTurnaround(3,rr.getTimeSheet()));
		System.out.println("P4           "+calcTurnaround(4,fcfs.getTimeSheet())+"         "+calcTurnaround(4,sjf.getTimeSheet())+"          "
				  +calcTurnaround(4,pri.getTimeSheet())+"         "+calcTurnaround(4,pri2.getTimeSheet())+"           "
				  +calcTurnaround(4,rr.getTimeSheet()));
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("Average:    "+calcAverageTime(fcfs.getTimeSheet())+"         "+calcAverageTime(sjf.getTimeSheet())+"        "+calcAverageTime(pri.getTimeSheet())+"         "+calcAverageTime(pri2.getTimeSheet())+"          "+calcAverageTime(rr.getTimeSheet())+"  ");
		System.out.println();
		System.out.println("The algorithm(s) which performs better in terms of");
		System.out.println("average turnaround time is (are)");
		
		double shortAve = calcAverageTime(fcfs.getTimeSheet());

		if(shortAve > calcAverageTime(sjf.getTimeSheet())) shortAve = calcAverageTime(sjf.getTimeSheet());
		if(shortAve > calcAverageTime(pri.getTimeSheet())) shortAve = calcAverageTime(pri.getTimeSheet());
		if(shortAve > calcAverageTime(pri2.getTimeSheet())) shortAve = calcAverageTime(pri2.getTimeSheet());
		if(shortAve > calcAverageTime(rr.getTimeSheet())) shortAve = calcAverageTime(rr.getTimeSheet());

		System.out.println();
		if(shortAve == calcAverageTime(fcfs.getTimeSheet())) System.out.println("  First Come First Served Scheduling Algorithm.");		
		if(shortAve == calcAverageTime(sjf.getTimeSheet())) System.out.println("  Shortest Job First Scheduling Algorithm. ");
		if(shortAve == calcAverageTime(pri.getTimeSheet())) System.out.println("  Priority Scheduling Algorithm 1.");
		if(shortAve == calcAverageTime(pri2.getTimeSheet())) System.out.println("  Priority Scheduling Algorithm 2.");
		if(shortAve == calcAverageTime(rr.getTimeSheet())) System.out.println("  Round Robin Algorithm.");	
		
		
		
		
		System.out.println();
		System.out.println("*************************************************************************");
		System.out.println();
		System.out.println("Waiting Time for each Process and Average Waiting Time");
		System.out.println("for each Algorithm");
		System.out.println();
		System.out.println("Process      FCFS      SJF      PriOne      PriTwo      RR");
		System.out.println("-------      ----      ---      ------      ------      --");		
		System.out.println("P0           "+calcWaitingTime(0,fcfs.getTimeSheet())+"         "+calcWaitingTime(0,sjf.getTimeSheet())+"          "
										  +calcWaitingTime(0,pri.getTimeSheet())+"         "+calcWaitingTime(0,pri2.getTimeSheet())+"           "
										  +calcRRWaitingTime(0,rr.getTimeSheet()));
		System.out.println("P1           "+calcWaitingTime(1,fcfs.getTimeSheet())+"         "+calcWaitingTime(1,sjf.getTimeSheet())+"          "
				  +calcWaitingTime(1,pri.getTimeSheet())+"         "+calcWaitingTime(1,pri2.getTimeSheet())+"           "
				  +calcRRWaitingTime(1,rr.getTimeSheet()));
		System.out.println("P2           "+calcWaitingTime(2,fcfs.getTimeSheet())+"         "+calcWaitingTime(2,sjf.getTimeSheet())+"          "
				  +calcWaitingTime(2,pri.getTimeSheet())+"         "+calcWaitingTime(2,pri2.getTimeSheet())+"           "
				  +calcRRWaitingTime(2,rr.getTimeSheet()));
		System.out.println("P3           "+calcWaitingTime(3,fcfs.getTimeSheet())+"         "+calcWaitingTime(3,sjf.getTimeSheet())+"          "
				  +calcWaitingTime(3,pri.getTimeSheet())+"         "+calcWaitingTime(3,pri2.getTimeSheet())+"           "
				  +calcRRWaitingTime(3,rr.getTimeSheet()));
		System.out.println("P4           "+calcWaitingTime(4,fcfs.getTimeSheet())+"         "+calcWaitingTime(4,sjf.getTimeSheet())+"          "
				  +calcWaitingTime(4,pri.getTimeSheet())+"         "+calcWaitingTime(4,pri2.getTimeSheet())+"           "
				  +calcRRWaitingTime(4,rr.getTimeSheet()));
		System.out.println("-------------------------------------------------------------------------");
		System.out.println();
		System.out.println("Average:    "+calcAverageWaitingTime(fcfs.getTimeSheet())+"         "+calcAverageWaitingTime(sjf.getTimeSheet())+"        "+calcAverageWaitingTime(pri.getTimeSheet())+"         "+calcAverageWaitingTime(pri2.getTimeSheet())+"          "+calcAverageRRWaitingTime(rr.getTimeSheet())+"  ");
		
		System.out.println("The algorithm(s) which performs better in terms of");
		System.out.println("average waiting time is (are)");
		System.out.println();
		
		double waitAve = calcAverageWaitingTime(fcfs.getTimeSheet());

		if(waitAve > calcAverageWaitingTime(sjf.getTimeSheet())) waitAve = calcAverageWaitingTime(sjf.getTimeSheet());
		if(waitAve > calcAverageWaitingTime(pri.getTimeSheet())) waitAve = calcAverageWaitingTime(pri.getTimeSheet());
		if(waitAve > calcAverageWaitingTime(pri2.getTimeSheet())) waitAve = calcAverageWaitingTime(pri2.getTimeSheet());
		if(waitAve > calcAverageWaitingTime(rr.getTimeSheet())) waitAve = calcAverageWaitingTime(rr.getTimeSheet());

		if(waitAve == calcAverageWaitingTime(fcfs.getTimeSheet())) System.out.println("  First Come First Served Scheduling Algorithm.");		
		if(waitAve == calcAverageWaitingTime(sjf.getTimeSheet())) System.out.println("  Shortest Job First Scheduling Algorithm. ");
		if(waitAve == calcAverageWaitingTime(pri.getTimeSheet())) System.out.println("  Priority Scheduling Algorithm 1.");
		if(waitAve == calcAverageWaitingTime(pri2.getTimeSheet())) System.out.println("  Priority Scheduling Algorithm 2.");
		if(waitAve == calcAverageWaitingTime(rr.getTimeSheet())) System.out.println("  Round Robin Algorithm.");	
		
		
		
		System.in.read();
		System.exit(0);
	}
	
	public static void inputValidationMethod(){
		
		System.out.println("            Collecting  Data                ");
		System.out.println("     ");
		System.out.println("Inputting each process burst time");
		System.out.println("     ");
		
		    
		    
		    for(int i=0;i<5;i++){
		      
		      System.out.print("Enter process P" + i +" "+ "burst time in milli seconds :");
		      spbt=scan.next();  
		     
		        
		      //checking for alphabets and alphabetical strings
		        try{process_burst_time[i]=Integer.parseInt(spbt);}
		        catch (Exception exp ){
		        	  System.out.println("\n");
			          System.out.println("The burst time you have entered is not a valid number");
			          System.out.println("\n");
		              i=i-1; continue;
		        }
		      
		      //checking for zero
		      if(process_burst_time[i]==0){ 
		          System.out.println("\n");
		          System.out.println("The burst time must be atleast 1 millisecond");
		          System.out.println("\n");
		          i=i-1; continue;   
		        }
		      
		      //checking for negative values and characters
		      if(process_burst_time[i]<0){ 
		          System.out.println("\n");
		          System.out.println("The burst time you have entered is not a valid number");
		          System.out.println("\n");
		          i=i-1; continue;   
		        }

	      } // end of for loop
		    
		    System.out.println("\n");
		    
			 System.out.println("Inputting each process priority." + "\n"+ "\n"+ 
					 "There are two implementations:"+"\n"+
			   "    Version 1: a lower priority number implies a higher priority."+"\n"+
			   "    Version 2: a higher priority number implies a higher priority.");
			 
			 
			 System.out.println("\n");
			 
			 
			 //Taking process priority values from user
			 
			 for(int i=0;i<5;i++){
			      
			      System.out.print("Enter process P" + i +" "+ "priority :");
			      sp=scan.next();  
			     
			        
			      //checking for alphabets and alphabetical strings
			        try{process_priority[i]=Integer.parseInt(sp);}
			        catch (Exception exp ){
			        	  System.out.println("\n");
				          System.out.println("The priority you have entered is not a valid number");
				          System.out.println("\n");
			              i=i-1; continue;
			        }

			      
			      //checking for negative values 
			      if(process_priority[i]<0){ 
			          System.out.println("\n");
			          System.out.println("The priority you have entered is not a valid number");
			          System.out.println("\n");
			          i=i-1; continue;   
			        } 
			 } // end of for loop[
			 
			 
			   

	for(int k=0 ;k<1;k++)
	{
		 System.out.println("\n");
		 System.out.println("Enter the Quantum Time in Milli Seconds");
		     sqt=scan.next();  
		 System.out.println("\n");
		 
		 //checking for alphabets and alphabetical strings
	        try{quantum_time=Integer.parseInt(sqt);}
	        catch (Exception exp ){
	        	  //System.out.println("\n");
	        	  System.out.println("The quantum value you have entered is not a valid number");
	 	          System.out.println("The Quantum time must be at least 1 millisecond");
		          //System.out.println("\n");
	              k=k-1; continue;
	        }
	      
	      //checking for zero
	      if(quantum_time==0){ 
	         // System.out.println("\n");
	          System.out.println("The Quantum time must be at least 1 millisecond");
	          //System.out.println("\n");
	          k=k-1; continue;   
	        }
	      
	      //checking for negative values and characters
	      if(quantum_time<0){ 
	         // System.out.println("\n");
	          System.out.println("The quantum value you have entered is not a valid number");
	          System.out.println("The Quantum time must be at least 1 millisecond");
	          //System.out.println("\n");
	          k=k-1; continue;   
	        }

	   
	}
	}
	public static void printingUserInputs(){
		System.out.println("\n");
		System.out.println("               Collected Data Set \n");
		System.out.println("Process"+"\t"+"BurstTime" + "\t" + "Priority" + "\t" + "Quantum Time");
	  
		  for (int ii = 0; ii < 5; ii++)
	  {

		  // printing valid user inputs
		  
	    System.out.printf("P%d \t\t", ii);
	  
	    for (int jj = 0; jj < 1; jj++){
	      System.out.print(process_burst_time[ii] + "\t\t");
	    }
	    for (int k1 = 0; k1 < 1; k1++){
	      System.out.print(process_priority[ii] + "\t\t");
	    }
	    if (ii == 0){
	      for (int l = 0; l < 1; l++){
	        if(l < 1-1)
	        System.out.print(quantum_time + "\t\t");
	        else
	        System.out.print(quantum_time);
	      }
	    }
	    System.out.println("\n");
	  }
		  
	  System.out.println("*************************************************************************");
	  System.out.println();
	  System.out.println("       Execution Sequence of each Algorithm");
	  
	}
	public static int calcTurnaround(int pNum, ArrayList<Integer> timesheet){
		int result = 0;
		for (int i = 0 ; i < timesheet.size(); i++)
		{
			if (pNum == timesheet.get(i))
				result = i;
		}
		return result+1;
	}
	
	public static int calcWaitingTime(int pNum, ArrayList<Integer> timesheet){
		int i = 0;
		for ( ; i < timesheet.size(); i++)
		{
			if (pNum == timesheet.get(i))
			break;
		}
		return i ;
	}
	
	public static int calcRRWaitingTime(int pNum, ArrayList<Integer> timesheet)
	{
		int i =0;
		int endTime = 0;
		int lastArrive = 0;
		int sumExeuTime = 0;
		int result = 0;
		for (i = timesheet.size() -1 ; i >= 0; i--)
		{
			if (pNum == timesheet.get(i))
			{
				endTime = i;
				break;
			}
		}
		
		for (i = endTime ; i >= 0; i--)
		{
			if (pNum != timesheet.get(i))
			{
				lastArrive = i+1;
				break;
			}
		}
		
		for (i = 0  ; i < timesheet.size(); i++)
		{
			if (pNum == timesheet.get(i))
			{
				sumExeuTime ++;
			}
		}
		result = lastArrive -(sumExeuTime -(endTime-lastArrive +1) );
		return result;
	}
	
	
	
	public static double calcAverageRRWaitingTime(ArrayList<Integer> timesheet)
	{
		double average = 0;
		for (int i = 0 ; i < 5 ; i ++)
		{
			average = average + calcRRWaitingTime(i,timesheet);
		}
		return average/5;
	}
	
	public static double calcAverageWaitingTime(ArrayList<Integer> timesheet)
	{
		double average = 0;
		for (int i = 0 ; i < 5 ; i ++)
		{
			average = average + calcWaitingTime(i,timesheet);
		}
		return average/5;
	}
	
	public static double calcAverageTime(ArrayList<Integer> timesheet)
	{
		double average = 0;
		for (int i = 0 ; i < 5 ; i ++)
		{
			average = average + calcTurnaround(i,timesheet);
		}
		return average/5;
	}
	
}
