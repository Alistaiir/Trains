import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;


public class Title extends JFrame {

	public JPanel contentPane;
	public JPanel previousTitleContent;
	

	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	/*
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Title frame = new Title();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public Title() throws IOException {
		TrainSchedule TS = new TrainSchedule();
		
		setTitle("Train Schedule App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 760);
		
		Title title = this;
		
		BrowseFrame browseFrame = new BrowseFrame(title, TS);
		previousTitleContent = (JPanel) getContentPane();
		setContentPane(browseFrame);
		invalidate();
		validate();
		
		/*contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Title title = this;
		
		JButton btnStart = new JButton("Start");
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				BrowseFrame browseFrame = new BrowseFrame(title);
				previousTitleContent = (JPanel) getContentPane();
				setContentPane(browseFrame);
				invalidate();
				validate();
			}
		});
		btnStart.setBounds(999, 597, 141, 64);
		contentPane.add(btnStart);
		
		JLabel lblLogo = new JLabel("Logo");
		lblLogo.setBounds(417, 174, 380, 237);
		contentPane.add(lblLogo);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.exit(0);
			}
		});
		btnQuit.setBounds(81, 597, 141, 64);
		getContentPane().add(btnQuit);*/
	}

}
