import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;



public abstract class Schedule {
	
	public abstract void upload(String filePath) throws IOException;
	public abstract JTable display();
	public abstract JTable search(String searchTerm);
	public abstract JTable select(String selectedTrain);
	public abstract void highlight();
	public abstract void save();
	
	public static void main(String[] args) throws IOException {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Title frame = new Title();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
