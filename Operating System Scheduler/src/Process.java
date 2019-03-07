

public class Process  {
	
	int pid; // process id
	int st; // start time
	int priority; // priority number
	
	public Process(int pid, int st) {
		this.pid = pid;
		this.st = st;		
	}
	
	public Process(int pid, int st, int priority) {
		this.pid = pid;
		this.st = st;	
		this.priority = priority;		
	}
	
	public int getPid() {
		return pid;
	}

	public int getStartTime() {
		return st;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public void setPid(int pid) {
		this.pid = pid;
	}
	
	public void setStartTime(int st) {
		this.st = st;	
	}
	
	public void setPriority(int priority) {
		this.priority = priority;	
	}
	
}
