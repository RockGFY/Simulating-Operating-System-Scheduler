import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class RoundRobin {
	
	private static final String FILE_HEADER = "CpuTime, PID, StartingBurstTime, EndingBurstTime, CompletionTime";
	private int quantum = 0;
	private int cost = 0;
	
	public RoundRobin(int quantum, int cost) {		
		this.quantum = quantum;	
		this.cost = cost;	
	}
	
	public void getResult(LinkedList<Process> process, String filename) {
		LinkedList<Process> processCopy = new LinkedList<Process>();
		for(int i = 0; i < process.size(); i++) {
			Process pro = new Process(process.get(i).pid, process.get(i).st, process.get(i).priority);
			processCopy.add(pro);
		}
		
		int n = processCopy.size();
		LinkedList<Integer> cputime = new LinkedList<Integer>();
		cputime.add(0, 0);
		LinkedList<Integer> endTime = new LinkedList<Integer>();
		LinkedList<Integer> compleationTime = new LinkedList<Integer>();
			
		try {
			PrintWriter writer = new PrintWriter(new File(filename));
			StringBuilder sb = new StringBuilder();
			
			sb.append(FILE_HEADER);		
			sb.append("\n");		
			int index = 0;
			//calculate  
			while(isdone(processCopy) == false) {							
				for(int i = 0; i < n; i++) {
					if(processCopy.get(i).getStartTime() != 0) {
						sb.append(Integer.toString(cputime.get(index)));
						sb.append(",");
						sb.append(Integer.toString(processCopy.get(i).getPid()));
						sb.append(",");
						sb.append(Integer.toString(processCopy.get(i).getStartTime()));
						sb.append(",");
						if(processCopy.get(i).getStartTime() > quantum) {
							endTime.add(index, processCopy.get(i).getStartTime() - quantum);
							cputime.add(index+1, cputime.get(index)+quantum+cost);
							processCopy.get(i).setStartTime(endTime.get(index));
							compleationTime.add(index, 0);							

							sb.append(Integer.toString(endTime.get(index)));
							sb.append(",");
							//sb.append(Integer.toString(compleationTime.get(index)));
							sb.append("\n");
						}
						else {
							endTime.add(index, 0);
							compleationTime.add(index, processCopy.get(i).getStartTime() + cputime.get(index));
							cputime.add(index+1, compleationTime.get(index)+cost);	
							processCopy.get(i).setStartTime(endTime.get(index));
							
							sb.append(Integer.toString(endTime.get(index)));
							sb.append(",");
							sb.append(Integer.toString(compleationTime.get(index)));
							sb.append("\n");						
						}
						index++;
					}
				}//end for
				
			}//end while			
			double avgTurnaround = 0;
			for(double temp : compleationTime) {
				avgTurnaround += temp;
			}			
			avgTurnaround = avgTurnaround/ Double.valueOf(n);
			sb.append("AverageTurnaroundTime: ");
			sb.append(",");
			sb.append(String.format("%.1f", avgTurnaround));
			
			writer.write(sb.toString());
			writer.close();

			System.out.println("The result has been printed into the file: " + filename);
			System.out.println("The average turnaround is: " + String.format("%.1f", avgTurnaround));	
		
		}catch (FileNotFoundException e) { 
			System.out.println(e.getMessage()); 
	    } 
		
	}
	
	public boolean isdone(LinkedList<Process> process) {
		for(int i = 0; i < process.size(); i++) {
			if(process.get(i).getStartTime() != 0) {
				return false;
			}
		}		
		return true;
	}

}
