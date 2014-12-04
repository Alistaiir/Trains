import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class TrainListFrame extends JPanel {
	
	String selectedTrain;
	String searchTerm;
	private JTextField txtSearch;
	static JPanel previousTrainListContent;

	/**
	 * Create the panel.
	 * @param myTitle 
	 * @throws IOException 
	 */
	
	public TrainListFrame(Title myTitle, TrainSchedule TS, String filePath) throws IOException {
		setBackground(new Color(173, 216, 230));
		setLayout(null);
		
		JButton btnUpload = new JButton("Upload another schedule");
		btnUpload.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				myTitle.setContentPane(BrowseFrame.previousBrowseContent);
				myTitle.invalidate();
				myTitle.validate();
				BrowseFrame.txtBrowse.setText("Browse...");
			}
		});
		btnUpload.setBounds(987, 597, 214, 64);
		add(btnUpload);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.exit(0);
			}
		});
		btnQuit.setBounds(81, 597, 141, 64);
		add(btnQuit);
		
		//===================================== THIS TAKES THE OUTPUT FROM THE DISPLAY =========================================================
		TS.upload(filePath);
		JTable lblTableOfTrains = TS.display();
		JScrollPane scrollPane = new JScrollPane(lblTableOfTrains);
		
		lblTableOfTrains.addMouseListener(new MouseAdapter() {
			@Override
			//===================================== TAKE WHICH TRAIN IS SELECTED, "SEND" THIS TO THE SELECT METHOD =============================
			public void mouseClicked(MouseEvent e) {
				int[] selected = lblTableOfTrains.getSelectedRows();
				for(int i = 0; i < selected.length; i++){
					selectedTrain = (String) lblTableOfTrains.getValueAt(selected[i], i);
				}
				
				try {
					ClickedTrainListFrame lblTrainClicked = new ClickedTrainListFrame(myTitle, TS, selectedTrain);
					previousTrainListContent = (JPanel) myTitle.getContentPane();
					myTitle.setContentPane(lblTrainClicked);
					myTitle.invalidate();
					myTitle.validate();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		scrollPane.setBounds(81, 73, 1120, 513);
		add(scrollPane, BorderLayout.CENTER);
		
		txtSearch = new JTextField();
		txtSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchTerm = txtSearch.getText();
				try {
					SearchedTrainListFrame txtSearched = new SearchedTrainListFrame(myTitle, TS, searchTerm);
					previousTrainListContent = (JPanel) myTitle.getContentPane();
					myTitle.setContentPane(txtSearched);
					myTitle.invalidate();
					myTitle.validate();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		txtSearch.setText("Search...");
		txtSearch.setBounds(1115, 42, 86, 20);
		add(txtSearch);
		txtSearch.setColumns(10);
		
		//This label takes which schedule was uploaded. 
		JLabel lblSelectedTrain = new JLabel(filePath);
		lblSelectedTrain.setBounds(81, 16, 400, 64);
		add(lblSelectedTrain);
		
		//fit text to label
		Font labelFont = lblSelectedTrain.getFont();
		String labelText = lblSelectedTrain.getText();

		int stringWidth = lblSelectedTrain.getFontMetrics(labelFont).stringWidth(labelText);
		int componentWidth = lblSelectedTrain.getWidth();

		// Find out how much the font can grow in width.
		double widthRatio = (double)componentWidth / (double)stringWidth;

		int newFontSize = (int)(labelFont.getSize() * widthRatio);
		int componentHeight = lblSelectedTrain.getHeight();

		// Pick a new font size so it will not be larger than the height of label.
		int fontSizeToUse = Math.min(newFontSize, componentHeight);

		// Set the label's font size to the newly determined size.
		lblSelectedTrain.setFont(new Font("Lucida Grande", lblSelectedTrain.getFont().getStyle(), lblSelectedTrain.getFont().getSize() + 10));


	}
}
