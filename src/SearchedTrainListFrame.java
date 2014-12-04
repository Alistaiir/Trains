import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.Color;


public class SearchedTrainListFrame extends JPanel {
	
	String selectedTrain;
	String searchTerm;
	static JPanel previousTrainListContent;
	private JTextField txtSearch;
	
	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public SearchedTrainListFrame(Title myTitle, TrainSchedule TS, String searchTerm) throws IOException {
		setBackground(new Color(173, 216, 230));
		setLayout(null);
		
		//===================================== THIS SHOULD TAKE THE OUTPUT FROM THE SEARCH METHOD INSTEAD OF AN IMAGE ==========================
		
		JTable label = TS.search(searchTerm);
		JScrollPane scrollPane = new JScrollPane(label);
		label.addMouseListener(new MouseAdapter() {
			@Override
			//===================================== TAKE WHICH TRAIN IS SELECTED, "SEND" THIS TO THE SELECT METHOD =============================
			public void mouseClicked(MouseEvent e) {
				int[] selected = label.getSelectedRows();
				for(int i = 0; i < selected.length; i++){
					selectedTrain = (String) label.getValueAt(selected[i], i);
				}
				
				try {
					ClickedTrainListFrame lblTrainClicked = new ClickedTrainListFrame(myTitle, TS, selectedTrain);
					previousTrainListContent = (JPanel) myTitle.getContentPane();
					myTitle.setContentPane(lblTrainClicked);
					myTitle.invalidate();
					myTitle.validate();
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		scrollPane.setBounds(81, 73, 1120, 513);
		add(scrollPane, BorderLayout.CENTER);
		
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
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				myTitle.setContentPane(TrainListFrame.previousTrainListContent);
			}
		});
		btnBack.setBounds(987, 672, 89, 23);
		add(btnBack);
		
		//===================================== THIS SHOULD RUN THE SAVE METHOD =================================================================
		JButton btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setVisible(true);
				fc.showSaveDialog(fc);
			}
		});
		btnSave.setBounds(1112, 672, 89, 23);
		add(btnSave);
		
		txtSearch = new JTextField();
		txtSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//searchTerm = txtSearch.getText();
				try {
					SearchedTrainListFrame txtSearched = new SearchedTrainListFrame(myTitle, TS, txtSearch.getText());
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
		
		
		//This takes the search term entered by the user.
		JLabel lblSearchedTerm = new JLabel("Searched for: " + searchTerm);
		lblSearchedTerm.setBounds(81, 16, 300, 64);
		add(lblSearchedTerm);
		
		//fit text to label
		Font labelFont = lblSearchedTerm.getFont();
		String labelText = lblSearchedTerm.getText();

		int stringWidth = lblSearchedTerm.getFontMetrics(labelFont).stringWidth(labelText);
		int componentWidth = lblSearchedTerm.getWidth();

		// Find out how much the font can grow in width.
		double widthRatio = (double)componentWidth / (double)stringWidth;

		int newFontSize = (int)(labelFont.getSize() * widthRatio);
		int componentHeight = lblSearchedTerm.getHeight();

		// Pick a new font size so it will not be larger than the height of label.
		int fontSizeToUse = Math.min(newFontSize, componentHeight);

		// Set the label's font size to the newly determined size.
		lblSearchedTerm.setFont(new Font("Lucida Grande", lblSearchedTerm.getFont().getStyle(), lblSearchedTerm.getFont().getSize() + 10));
	}

}
