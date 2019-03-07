import java.io.*;
import java.util.LinkedList;

public class Main {
	final static int COST = 3;
	final static int QUANTUM1 = 20;
	final static int QUANTUM2 = 40;
	public static void main(String[] args) throws Exception {
		
		LinkedList<Process> testdata1 = new LinkedList<Process>();
		LinkedList<Process> testdata2 = new LinkedList<Process>();
		LinkedList<Process> testdata3 = new LinkedList<Process>();
		LinkedList<Process> testdata4 = new LinkedList<Process>();
		
		testdata1 = readData("testdata1");
		testdata2 = readData("testdata2");
		testdata3 = readData("testdata3");
		testdata4 = readData("testdata4");
		
		//for FirstComeFirstServe scheduling;
		System.out.println("**********First Come First Serve scheduling***********");
		FirstComeFirstServe fcfs = new FirstComeFirstServe(COST);
		fcfs.getResult(testdata1, "FCFS-testdata_1.csv");
		fcfs.getResult(testdata2, "FCFS-testdata_2.csv");
		fcfs.getResult(testdata3, "FCFS-testdata_3.csv");
		fcfs.getResult(testdata4, "FCFS-testdata_4.csv");
		System.out.println("******************************************************");

		//for ShortestJobFirst scheduling		
		System.out.println("************Shortest Job First scheduling*************");
		ShortestJobFirst sjf = new ShortestJobFirst(COST);
		sjf.getResult(testdata1, "SJF-testdata_1.csv");
		sjf.getResult(testdata2, "SJF-testdata_2.csv");
		sjf.getResult(testdata3, "SJF-testdata_3.csv");
		sjf.getResult(testdata4, "SJF-testdata_4.csv");
		System.out.println("******************************************************");
		
		//for Round Robin scheduling		
		System.out.println("**************Round Robin 20 scheduling***************");
		RoundRobin rr20 = new RoundRobin(QUANTUM1, COST);
		rr20.getResult(testdata1, "RR20-testdata_1.csv");
		rr20.getResult(testdata2, "RR20-testdata_2.csv");
		rr20.getResult(testdata3, "RR20-testdata_3.csv");
		rr20.getResult(testdata4, "RR20-testdata_4.csv");
		System.out.println("******************************************************");
		
		System.out.println("**************Round Robin 40 scheduling***************");
		RoundRobin rr40 = new RoundRobin(QUANTUM2, COST);
		rr40.getResult(testdata1, "RR40-testdata_1.csv");
		rr40.getResult(testdata2, "RR40-testdata_2.csv");
		rr40.getResult(testdata3, "RR40-testdata_3.csv");
		rr40.getResult(testdata4, "RR40-testdata_4.csv");
		System.out.println("******************************************************");
		
		//for lottery scheduling
		System.out.println("****************Lottery 40 scheduling****************");
		Lottery lottery40 = new Lottery(QUANTUM2, COST);
		lottery40.getResult(testdata1, "Lottery40-testdata1.csv");
		lottery40.getResult(testdata2, "Lottery40-testdata2.csv");
		lottery40.getResult(testdata3, "Lottery40-testdata3.csv");
		lottery40.getResult(testdata4, "Lottery40-testdata4.csv");
	}
	
	public static LinkedList<Process> readData(String filename) throws Exception {
		File file = new File(filename);
		BufferedReader br = new BufferedReader(new FileReader(file));		
		LinkedList<Process> processList = new LinkedList<Process>();
		String str; 		
		int pid = 0, st = 0, pri = 0;	
		int counter = 1;
		while ((str = br.readLine()) != null) {			
			if(counter == 1) {
				pid = Integer.parseInt(str);		
				counter++;
			}
			else if(counter == 2) {
				st = Integer.parseInt(str);
				counter++;			
			}
			else if(counter == 3) {
				pri = Integer.parseInt(str);
				Process pro = new Process(pid, st, pri);
				processList.add(pro);
				counter = 1;
			}				
		}
		br.close();
//		for (Process p : process) {
//			System.out.println(p.getPid() + " " + p.getStartTime() + " " + p.getPriority());
//	    }
		return processList;		
	}
	
	
}
