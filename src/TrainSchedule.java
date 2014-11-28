import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;


public class TrainSchedule extends Schedule{		
	String[][] result;
	String[][] display;
	String[][] displaysearch;
	
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
	
	/* Make a method that counts occurences of train. Return as List: Train1: 12, Train2: 100 etc.. */
	
	/*
	public String[][] count(){
		 int n = result.length-1;
		 String[][] newArray = new String[n][];
		 System.arraycopy(result,1,newArray,0,n);
		
		 String[][] counting;
		 int count = 0;
		 for(int i = 0; i< newArray.length; i++){
			 for(int j = 0; j < newArray[j].length; j++){
				 if(newArray[i][0].equals(newArray[i+1][0])){
					 count++;
				 }
				 counting[i][j] = new
				 
			 }
		 }
		 return counting;
		
		
	}
	
	*/
	
	@Override
	public void display2(){
		JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    int n = result.length-1;
	    String[][] newArray = new String[n][];
	    System.arraycopy(result,1,newArray,0,n);
	    display = new String[newArray.length + 1][newArray.length + 1];
	    
	    display[0][0] = newArray[0][0];
	    display[0][1] = newArray[0][2];
	    display[0][3] = newArray[0][4];
	    int count = 1;
	    int j = 1;
	    Integer[] counter = new Integer[newArray.length];
	    for(int i = 1; i < newArray.length; i++){
	    	counter[i] = 0;
	    		if(newArray[i][0].equals(newArray[i-1][0])){
	    			count++;
	    		}
	    		else{
	    			counter[i] = count;
	    			
	    			System.out.println(counter[i]);
	    			
	    			//display[0][2] = newArray[12][2]; 
		    		display[j][0] = newArray[counter[i]][0];
		    		display[j][1] = newArray[counter[i]][2];
		    		display[j - 1][2] = newArray[counter[i] - 1][2];
		    		System.out.println(display[j-1][2]);
		    		display[j][3] = newArray[counter[i]][4];
		    		count = counter[i] + 1;
		    		j++;
	    		}	
	    }
	    System.out.println(count);
	    System.out.println(j);
	    display[j - 1][2] = newArray[count -1][2];
	    
	    
	    String[][] small_display = new String[8][4];
	    for(int i = 0; i < small_display.length; i++){
	    	for(int k = 0; k< small_display[i].length; k++){
	    		if(display[i][k] != null){
	    			small_display[i][k] = display[i][k];
	    			
	    		}
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
		JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    displaysearch = new String[result.length][result.length];
		for(int i = 0; i < result.length; i++){
			for(int j = 0; j < result[i].length; j++){
				if(result[i][j].equals(keyword)){
					displaysearch[i][0] = result[i][0];
					displaysearch[i][j] = result[i][j];
				}
			}
		}
		Object rowSchedule[][] = displaysearch;
	    Object columnTitle[] = {"Train", "Start location", "Final destination", "Start time"};
	    JTable table = new JTable(rowSchedule, columnTitle);

	    JScrollPane scrollPane = new JScrollPane(table);
	    frame.add(scrollPane, BorderLayout.CENTER);
	    frame.setSize(1500, 500);
	    frame.setVisible(true);
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
