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
import javax.swing.JTextField;


public class TrainListFrame extends JPanel {
	
	private JTextField txtSearch;

	static ImageIcon table = new ImageIcon("http://puu.sh/cWZ3a/0c068dbab2.png");
	static JPanel previousTrainListContent;

	/**
	 * Create the panel.
	 * @param myTitle 
	 * @throws IOException 
	 */
	
	public TrainListFrame(Title myTitle) throws IOException {
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
		
		JButton btnHome = new JButton("Home");
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				myTitle.setContentPane(myTitle.previousTitleContent);
			}
		});
		btnHome.setBounds(81, 597, 141, 64);
		add(btnHome);
		
		BufferedImage tabel1 = ImageIO.read(new File("C:/Users/Vladimir/Desktop/Tabel1.png"));
		JLabel lblTableOfTrains = new JLabel(new ImageIcon(tabel1));
		lblTableOfTrains.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					ClickedTrainListFrame lblTrainClicked = new ClickedTrainListFrame(myTitle);
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
		lblTableOfTrains.setBounds(81, 73, 1120, 513);
		add(lblTableOfTrains);
		
		txtSearch = new JTextField();
		txtSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					SearchedTrainListFrame txtSearched = new SearchedTrainListFrame(myTitle);
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

	}
}
