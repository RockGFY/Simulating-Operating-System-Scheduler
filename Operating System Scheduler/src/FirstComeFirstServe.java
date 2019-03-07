import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class FirstComeFirstServe {
	
	private static final String FILE_HEADER = "CpuTime, PID, StartingBurstTime, EndingBurstTime, CompletionTime";
	private int cost = 0;
	
	public FirstComeFirstServe(int cost) {
		this.cost = cost;
	}
	
	public void getResult(LinkedList<Process> process, String filename) {
		int n = process.size();
		int cputime[] = new int[n+1];
		cputime[0] = 0;
		int endTime[] = new int[n];
		int compleationTime[] = new int[n];		
		
		try {
			PrintWriter writer = new PrintWriter(new File(filename));
			StringBuilder sb = new StringBuilder();
			
			sb.append(FILE_HEADER);		
			sb.append("\n");			
			//calculate  
			for(int i = 0; i < n; i++) {
				compleationTime[i] = process.get(i).getStartTime() + cputime[i];
				cputime[i+1] = compleationTime[i] + cost;	
				endTime[i] = 0;	
				sb.append(Integer.toString(cputime[i]));
				sb.append(",");
				sb.append(Integer.toString(process.get(i).getPid()));
				sb.append(",");
				sb.append(Integer.toString(process.get(i).getStartTime()));
				sb.append(",");
				sb.append(Integer.toString(endTime[i]));
				sb.append(",");
				sb.append(Integer.toString(compleationTime[i]));
				sb.append("\n");
			}	
			
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
		
		//check 
//		for(int i = 0; i < n; i++) {
//			System.out.println(cputime[i] + " " + process.get(i).getPid() + " " + process.get(i).getStartTime() + " " + endTime[i] + " " + compleationTime[i]);
//		}
		
	}
		
	

}
