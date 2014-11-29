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
	private static final int n = 0;
	String[][] result;
	String[][] display;
	String[][] displaysearch;
	String[][] selection;
	
	@Override
	public void upload() throws IOException {
		String csvFile = "/Users/mereltheisen/Documents/UCL/GC02 - Apps Design/Scheds.csv";
		CSVReader reader = null;
		try	{ 
			reader = new CSVReader(new FileReader(csvFile));
			List<String[]> list = reader.readAll();
			
			//This is an array with all the data from the CSV file in it. 
			String[][] data = new String[list.size()][];
			data = list.toArray(data);
			
			//This array will be the data from the CSV file split at each point where there is a comma. 
			result = new String[list.size()][list.size()];
			for(int i = 0; i < data.length; i++){
				for(int j = 0 ; j < data[i].length; j++){
					result[i] = data[i][j].split(",");			
				}
			}
			
			//This for loop replaces every null space and every occurrence of "01-JAN-01 00.00.00" with "-". 
			for(int i = 0; i < result.length; i++){
				for (int j = 0; j < result[i].length; j++){
					if(result[i][j].equals("01-JAN-01 00.00.00") || result[i][j].equals(null)){
						result[i][j] = "-";
					}
					//This only prints the result in the console, can be deleted in final project. 
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
	public void display(){
		JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    //This creates a new array which is the same as result, apart from the title line, the first one in result. 
	    int n = result.length-1;
	    String[][] newArray = new String[n][];
	    System.arraycopy(result,1,newArray,0,n);
	    
	    //This array will be filled with the data we want to display, but is still the same size as our original data array. 
	    display = new String[newArray.length][newArray.length];
	    
	    //The first edge cases are initialised. 
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
		    		display[j][0] = newArray[counter[i]][0];
		    		display[j][1] = newArray[counter[i]][2];
		    		display[j - 1][2] = newArray[counter[i] - 1][2];
		    		display[j][3] = newArray[counter[i]][4];
		    		count = counter[i] + 1;
		    		j++;
	    		}	
	    }
	    //The last edge case is initialised as well. 
	    display[j - 1][2] = newArray[count -1][2];
	    
	    //This small_display array contains the data of display[][], but is exactly the size of the numbers of rows that aren't null. 
	    String[][] small_display = new String[j][4];
	    for(int i = 0; i < small_display.length; i++){
	    	for(int k = 0; k< small_display[i].length; k++){
	    		if(display[i][k] != null){
	    			small_display[i][k] = display[i][k];
	    		}
	    	}
	    }
	    
	    Object rowSchedule[][] = small_display;
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
	   
	    int n = 0;
		for(int i = 0; i < result.length; i++){
			for(int j = 0; j < result[i].length; j++){
				if(result[i][j].equals(keyword)){
					displaysearch[n]= result[i];
					n++;
				}
			}
		}
		
		String[][] small_displaysearch = new String[n][8];
		for(int i = 0; i < small_displaysearch.length; i++){
	    	for(int k = 0; k< small_displaysearch[i].length; k++){
	    			small_displaysearch[i][k] = displaysearch[i][k];
	    	}
		}
		
		System.out.println(small_displaysearch.length);
		
		Object rowSchedule[][] = small_displaysearch;
	    Object columnTitle[] = result[0];
	    JTable table = new JTable(rowSchedule, columnTitle);

	    JScrollPane scrollPane = new JScrollPane(table);
	    frame.add(scrollPane, BorderLayout.CENTER);
	    frame.setSize(1500, 500);
	    frame.setVisible(true);
	}

	@Override
	public void select(String selected) {
		JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    selection = new String[result.length][result.length];
	   
	    int n = 0;
		for(int i = 0; i < result.length; i++){
				if(result[i][0].equals(selected)){
					selection[n] = result[i];
					n++;
				}
		}

		String[][] small_selection = new String[n][8];
		for(int i = 0; i < small_selection.length; i++){
	    	for(int k = 0; k< small_selection[i].length; k++){
	    		small_selection[i][k] = selection[i][k];
	    	}
		}
		
		Object rowSchedule[][] = small_selection;
	    Object columnTitle[] = result[0];
	    JTable table = new JTable(rowSchedule, columnTitle);

	    JScrollPane scrollPane = new JScrollPane(table);
	    frame.add(scrollPane, BorderLayout.CENTER);
	    frame.setSize(1500, 500);
	    frame.setVisible(true);
	}

	@Override
	public void highlight() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void save(){
		
	}
	
	
	

}
