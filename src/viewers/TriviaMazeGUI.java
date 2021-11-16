package viewers;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import controllers.Maze;
import models.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * GUI for Trivia Maze (currently WIP)
 * @author Roland Hanson
 * @version 1.2
 */
public class TriviaMazeGUI extends JFrame {

	/**
	 * Generated serialUID
	 */
	private static final long serialVersionUID = 5085020715814436080L;
	
	private JPanel contentPane;
	JButton btnN;
	JButton btnE;
	JButton btnS;
	JButton btnW;
	JMenuBar menuBar;
	JMenu mnFile;
	JMenuItem mntmSave;
	JMenuItem mntmLoad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TriviaMazeGUI frame = new TriviaMazeGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TriviaMazeGUI() {
		setTitle("Trivia Maze");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnN = new JButton("N");
		btnN.addActionListener(new goNorth());
		btnN.setBounds(459, 137, 43, 23);
		contentPane.add(btnN);
		
		btnE = new JButton("E");
		btnE.addActionListener(new goEast());
		btnE.setBounds(484, 171, 43, 23);
		contentPane.add(btnE);
		
		btnS = new JButton("S");
		btnS.addActionListener(new goSouth());
		btnS.setBounds(459, 205, 43, 23);
		contentPane.add(btnS);
		
		btnW = new JButton("W");
		btnW.addActionListener(new goWest());
		btnW.setBounds(436, 171, 43, 23);
		contentPane.add(btnW);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 553, 22);
		contentPane.add(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new saveMaze());
		mntmSave.setHorizontalAlignment(SwingConstants.LEFT);
		mnFile.add(mntmSave);
		
		mntmLoad = new JMenuItem("Load");
		mnFile.add(mntmLoad);
	}
	
	/**
	 * Makes the player go north
	 * @author Roland Hanson
	 *
	 */
	private class goNorth implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			
		}
	}
	
	/**
	 * Makes the player go east
	 * @author Roland Hanson
	 *
	 */
	private class goEast implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
	/**
	 * Makes the player go south
	 * @author Roland Hanson
	 *
	 */
	private class goSouth implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
	/**
	 * Makes the player go west
	 * @author Roland Hanson
	 *
	 */
	private class goWest implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
	/**
	 * Saves the game (would call the SaveLoad class once it's complete)
	 * @author Roland Hanson
	 *
	 */
	private class saveMaze implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
}
