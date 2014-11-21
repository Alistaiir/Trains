import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class TrainSchedule extends Schedule{	
	
	@Override
	public void upload() {
		String csvFile = "/Users/mereltheisen/Documents/UCL/GC02 - Apps Design/Scheds.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
	 
		try {
	 
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
	 
			        // use comma as separator
				String[] column = line.split(cvsSplitBy);
	 
				System.out.println("Train code: " + column[0] + " " +
								   "Location: " + column[2] + " " +
								   "Planned arrival: " + column[3] +" " +
								   "Planned departure: " + column[4]+" " +
								   "Planned passing: " + column[5]+" " +
								   "Actual arrival: " + column[6]+" " +
								   "Actual departure: " + column[7]);
	 
			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	 
		System.out.println("Done");
	  }
		
	@Override
	public void display() {
		JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    Object rowData[][] = { { "185S33MB26" ,"27","CRFTDEP","01-JAN-01 00.00.00","26-OCT-14 07.56.00","01-JAN-01 00.00.00","01-JAN-01 00.00.00","01-JAN-01 00.00.00" },
	    					   { "185S33MB26","27","CRFTESP","26-OCT-14 08.03.00","26-OCT-14 08.08.00","01-JAN-01 00.00.00","01-JAN-01 00.00.00","01-JAN-01 00.00.00" } };
	    Object columnNames[] = { "UWTTID","SECTOR","LOCATION","PLANNED ARRIVAL","PLANNED DEPARTURE","PLANNED PASS","ACTUAL ARRIVAL","ACTUAL DEPARTURE"};
	    JTable table = new JTable(rowData, columnNames);

	    JScrollPane scrollPane = new JScrollPane(table);
	    frame.add(scrollPane, BorderLayout.CENTER);
	    frame.setSize(1500, 500);
	    frame.setVisible(true);
		
	}



	@Override
	public void search() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void select() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void highlight() {
		// TODO Auto-generated method stub
		
	}
	
	

}
