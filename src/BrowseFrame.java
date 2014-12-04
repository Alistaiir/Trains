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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import java.awt.Font;


public class BrowseFrame extends JPanel {
	
	public String filePath = null; 
	String extension;
	
	static JTextField txtBrowse;
	public static JPanel previousBrowseContent;
	
	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public BrowseFrame(Title myTitle, TrainSchedule TS) throws IOException {
		setBackground(new Color(173, 216, 230));
		
		setLayout(null);
		
		txtBrowse = new JTextField();
		txtBrowse.setText("Browse...");
		txtBrowse.setBounds(495, 340, 200, 20);
		add(txtBrowse);
		txtBrowse.setColumns(10);
		
		JLabel label = new JLabel("Upload train schedule");
		label.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		label.setBounds(500, 310, 210, 14);
		add(label);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel files", "csv");
				JFileChooser fc = new JFileChooser();
				fc.setFileFilter(filter);
				fc.setVisible(true);
				if(fc.showOpenDialog(fc) != JFileChooser.CANCEL_OPTION) {
					txtBrowse.setText(fc.getSelectedFile().getName());
					String filename = fc.getSelectedFile().getName();
					filePath = fc.getSelectedFile().getPath();
					extension = filename.substring(filename.lastIndexOf("."),filename.length());
				}
			}
		});
		btnBrowse.setBounds(800, 340, 89, 23);
		add(btnBrowse);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				TrainListFrame trainListFrame;
				if(filePath == null){
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame,
					        "Please upload a csv file.",
					        "Upload problem",
					        JOptionPane.WARNING_MESSAGE);
				}
				if(!extension.equals("csv")){
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame,
					        "Please upload a csv file.",
					        "Upload problem",
					        JOptionPane.WARNING_MESSAGE);
				}
				
				try {
					trainListFrame = new TrainListFrame(myTitle, TS, filePath);
					previousBrowseContent = (JPanel) myTitle.getContentPane();
					myTitle.setContentPane(trainListFrame);
					myTitle.invalidate();
					myTitle.validate();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					//System.out.println("error");
				}
			}
		});
		btnSubmit.setBounds(1053, 597, 141, 64);
		add(btnSubmit);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.exit(0);
			}
		});
		btnQuit.setBounds(81, 597, 141, 64);
		add(btnQuit);
	
		BufferedImage trainLogo = ImageIO.read(new File("/Users/mereltheisen/Desktop/speedtrainlogo.png"));
		JLabel lblNewLabel = new JLabel(new ImageIcon(trainLogo));
		lblNewLabel.setBounds(21, -15, 500, 400);
		add(lblNewLabel);
	}
}
