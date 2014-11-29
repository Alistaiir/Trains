import java.io.IOException;
import java.util.List;
import java.util.Vector;


public abstract class Schedule {
	
	public abstract void upload() throws IOException;
	public abstract void display();
	public abstract void search(String keyword);
	public abstract void select(String selected);
	public abstract void highlight();
	public abstract void save();
	//public abstract String[][] count();
	
	public static void main(String[] args) throws IOException {
		TrainSchedule TS = new TrainSchedule();
		TS.upload();
		//TS.display();
		//TS.search("185S33MB26");
		TS.select("185S33MB26");
	}

}
