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
import javax.swing.JTextField;


public class ClickedTrainListFrame extends JPanel {
	
	private JTextField txtSearch;

	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public ClickedTrainListFrame(Title myTitle) throws IOException {
		setLayout(null);
		
		BufferedImage table2 =  ImageIO.read(new File("C:/Users/Vladimir/Desktop/Tabel2.png"));
		JLabel label = new JLabel(new ImageIcon(table2));
		label.setBounds(81, 73, 1120, 513);
		add(label);
		
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
		
		JButton btnHome = new JButton("Home");
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				myTitle.setContentPane(myTitle.previousTitleContent);
			}
		});
		btnHome.setBounds(81, 597, 141, 64);
		add(btnHome);
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				myTitle.setContentPane(TrainListFrame.previousTrainListContent);
			}
		});
		btnBack.setBounds(987, 672, 89, 23);
		add(btnBack);
		
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
		txtSearch.setText("Search...");
		txtSearch.setBounds(1115, 42, 86, 20);
		add(txtSearch);
		txtSearch.setColumns(10);


	}
}
