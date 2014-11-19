import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
//sds
//test
//test2
public class CPUSchedule {

	public static void main(String[] args) throws Exception {
		
		String line;
		FileReader fileReader = new FileReader("heading.txt");
		BufferedReader bufferedReader = new BufferedReader(fileReader);


		while ((line = bufferedReader.readLine()) != null) {
			System.out.println(line);// Deal with the line
		}
		bufferedReader.close();
		
		Process p0 = new Process(4, 3, 0);		
		Process p1 = new Process(1, 1, 1);
		Process p2 = new Process(2, 2, 2);
		Process p3 = new Process(3, 4, 3);
		Process p4 = new Process(5, 5, 4);
		FCFSSchedule fcfs = new FCFSSchedule();
		Printer printer = new Printer();
		fcfs.addProcess(p0);
		fcfs.addProcess(p1);
		fcfs.addProcess(p2);
		fcfs.addProcess(p3);
		fcfs.addProcess(p4);
		fcfs.run();
		printer.setTimeSheet(fcfs.getTimeSheet());
		printer.print();
		
		SJFSchedule sjf = new SJFSchedule();
		sjf.addProcess(p0);
		sjf.addProcess(p1);
		sjf.addProcess(p2);
		sjf.addProcess(p3);
		sjf.addProcess(p4);
		sjf.run();
		printer.setTimeSheet(sjf.getTimeSheet());
		printer.print();
		
		PriSchedule pri = new PriSchedule();
		pri.addProcess(p0);
		pri.addProcess(p1);
		pri.addProcess(p2);
		pri.addProcess(p3);
		pri.addProcess(p4);
		pri.run();
		printer.setTimeSheet(pri.getTimeSheet());
		printer.print();
		
		System.in.read();
		System.exit(0);
	}
}
