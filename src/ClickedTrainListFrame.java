import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.Color;


public class ClickedTrainListFrame extends JPanel {
	
	private JTextField txtSearch;
	static JPanel previousTrainListContent;
	String searchTerm;

	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public ClickedTrainListFrame(Title myTitle, TrainSchedule TS, String selectedTrain) throws IOException {
		setBackground(new Color(173, 216, 230));
		setLayout(null);
		
		//This takes the output of the select method. 
		JTable label = TS.select(selectedTrain);
		JScrollPane scrollPane = new JScrollPane(label);
		add(scrollPane, BorderLayout.CENTER);
		scrollPane.setBounds(81, 73, 1120, 513);
		
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
		
		//===================================== THIS SHOULD RUN THE SAVE METHOD ================================================================
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
		
		//This label takes which train is selected. 
		JLabel lblSelectedTrain = new JLabel(selectedTrain);
		lblSelectedTrain.setBounds(81, 16, 300, 64);
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
