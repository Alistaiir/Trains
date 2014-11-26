import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;


public class TrainSchedule extends Schedule{		
	String[][] result;
	String[][] display;
	
	@Override
	public void upload() throws IOException {
		String csvFile = "/Users/mereltheisen/Documents/UCL/GC02 - Apps Design/Scheds.csv";
		CSVReader reader = null;
		try	{ 
			reader = new CSVReader(new FileReader(csvFile));
			List<String[]> list = reader.readAll();
			
			String[][] data = new String[list.size()][];
			data = list.toArray(data);
			
			result = new String[list.size()][list.size()];
				
			for(int i = 0; i < data.length; i++){
				for(int j = 0 ; j < data[i].length; j++){
					result[i] = data[i][j].split(",");			
				}
			}
			
			for(int i = 0; i < result.length; i++){
				for (int j = 0; j < result[i].length; j++){
					if(result[i][j].equals("01-JAN-01 00.00.00") || result[i][j].equals(null)){
						result[i][j] = "-";
					}
					System.out.print(result[i][j] + " ");
					if(j == result[i].length-1){
						System.out.println(" ");
					}
				}
			}
		}
   
		catch (FileNotFoundException e) {
				e.printStackTrace();
	    }
		catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	  }
		
	@Override
	public void display() {
		JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    int n = result.length-1;
	    String[][] newArray = new String[n][];
	    System.arraycopy(result,1,newArray,0,n);
	    
	    Object rowSchedule[][] = newArray;
	    Object columnTitle[] = result[0];
	    JTable table = new JTable(rowSchedule, columnTitle);

	    JScrollPane scrollPane = new JScrollPane(table);
	    frame.add(scrollPane, BorderLayout.CENTER);
	    frame.setSize(1500, 500);
	    frame.setVisible(true);
	}

	@Override
	public void display2(){
		JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    int n = result.length-1;
	    String[][] newArray = new String[n][];
	    System.arraycopy(result,1,newArray,0,n);
	    display = new String[newArray.length][newArray.length];
	    
	    display[0][0] = newArray[0][0];
	    display[0][1] = newArray[0][2];
	    display[0][3] = newArray[0][4];
	    for(int i = 1; i < newArray.length; i++){
	    		if(newArray[i][0].equals(newArray[i-1][0])){
	    		}
	    		else{
	    			display[i][0] = newArray[i][0];
	    			display[i][1] = newArray[i][2];
	    			display[i-1][2] = newArray[i-1][2];
	    			display[i][3] = newArray[i][4];
	    			System.out.println(display[i][0]);
	    		}
	    }
	      
	    Object rowSchedule[][] = display;
	    Object columnTitle[] = {"Train", "Start location", "Final destination", "Start time"};
	    JTable table = new JTable(rowSchedule, columnTitle);

	    JScrollPane scrollPane = new JScrollPane(table);
	    frame.add(scrollPane, BorderLayout.CENTER);
	    frame.setSize(1500, 500);
	    frame.setVisible(true);
	    
		
	}
	@Override
	public void search(String keyword) {
		for(int i = 0; i < result.length; i++){
			for(int j = 0; j < result[i].length; j++){
				//System.out.println(result[i][j]);
				if(result[i][j].equals(keyword)){
					System.out.println("Train code: " + result[i][0] + " Location: " + result[i][2]);
				}
			}
		}
	}


	@Override
	public void select() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void highlight() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void save(){
		
	}
	
	
	

}
