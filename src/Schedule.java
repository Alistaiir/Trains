import java.io.IOException;


public abstract class Schedule {
	
	public abstract void upload() throws IOException;
	public abstract void display();
	public abstract void search(String keyword);
	public abstract void select();
	public abstract void highlight();
	public abstract void save();
	
	public static void main(String[] args) throws IOException {
		TrainSchedule TS = new TrainSchedule();
		TS.upload();
		//TS.display();
		TS.search("\"NEVLWJN\"");
	}

}
