import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class BrowseFrame extends JPanel {
	
	static JTextField txtBrowse;
	public static JPanel previousBrowseContent;
	
	/**
	 * Create the panel.
	 */
	public BrowseFrame(Title myTitle) {
		
		setLayout(null);
		
		txtBrowse = new JTextField();
		txtBrowse.setText("Browse...");
		txtBrowse.setBounds(455, 303, 200, 20);
		add(txtBrowse);
		txtBrowse.setColumns(10);
		
		JLabel label = new JLabel("Upload train schedule");
		label.setBounds(455, 285, 141, 14);
		add(label);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setVisible(true);
				if(fc.showOpenDialog(fc) != JFileChooser.CANCEL_OPTION) {
					txtBrowse.setText(fc.getSelectedFile().getName());
				}
			}
		});
		btnBrowse.setBounds(665, 302, 89, 23);
		add(btnBrowse);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				TrainListFrame trainListFrame;
				try {
					trainListFrame = new TrainListFrame(myTitle);
					previousBrowseContent = (JPanel) myTitle.getContentPane();
					myTitle.setContentPane(trainListFrame);
					myTitle.invalidate();
					myTitle.validate();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSubmit.setBounds(1053, 622, 141, 64);
		add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				myTitle.setContentPane(myTitle.previousTitleContent);
			}
		});
		btnCancel.setBounds(902, 622, 141, 64);
		add(btnCancel);
		
		JButton btnHome = new JButton("Home");
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				myTitle.setContentPane(myTitle.previousTitleContent);
			}
		});
		btnHome.setBounds(86, 622, 141, 64);
		add(btnHome);
	}

}
