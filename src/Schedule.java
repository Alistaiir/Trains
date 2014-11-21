
public abstract class Schedule {
	
	public abstract void upload();
	public abstract void display();
	public abstract void search();
	public abstract void select();
	public abstract void highlight();
	
	public static void main(String[] args) {
		TrainSchedule TS = new TrainSchedule();
		TS.upload();
		TS.display();
	}

}
