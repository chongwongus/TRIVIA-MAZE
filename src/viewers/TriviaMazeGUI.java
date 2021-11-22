package viewers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import controllers.Maze;
import controllers.Player;
import models.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * GUI for Trivia Maze (currently WIP) Built using windowbuilder
 * Note: Direction movements still need to check the boundaries of the maze
 * @author Roland Hanson
 * @version 1.5
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
	JMenuItem mntmExit;
	JMenu mnHelp;
	JMenuItem mntmAbout;
	JMenuItem mntmInstructions;
	JMenuItem mntmCheats;
	JPanel mazePanel;
	JButton btnStart;
	
	Maze maze = new Maze();
	Player player = new Player();

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
		btnN.setBounds(459, 67, 43, 23);
		contentPane.add(btnN);
		
		btnE = new JButton("E");
		btnE.addActionListener(new goEast());
		btnE.setBounds(484, 101, 43, 23);
		contentPane.add(btnE);
		
		btnS = new JButton("S");
		btnS.addActionListener(new goSouth());
		btnS.setBounds(459, 135, 43, 23);
		contentPane.add(btnS);
		
		btnW = new JButton("W");
		btnW.addActionListener(new goWest());
		btnW.setBounds(436, 101, 43, 23);
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
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new exitGame());
		mnFile.add(mntmExit);
		
		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		
		mntmInstructions = new JMenuItem("Instructions");
		mnHelp.add(mntmInstructions);
		
		mntmCheats = new JMenuItem("Cheats");
		mnHelp.add(mntmCheats);
		
		mazePanel = new mazePanel();
		mazePanel.setBounds(10, 33, 416, 195);
		contentPane.add(mazePanel);
		
		btnStart = new JButton("Start!");
		btnStart.addActionListener(new startGame());
		btnStart.setBounds(229, 181, 89, 23);
		contentPane.add(btnStart);
	}
	
	
	/**
	 * Displays the maze...hopefully
	 * Currently testing different ways to do this
	 * @author Roland Hanson
	 *
	 */
	private class mazePanel extends JPanel{
		
		private mazePanel() {
			
		}
		
		public void paintComponent(Graphics g) {
			int bxWidth = 30;
			int bxHeight = 30;
			super.paintComponent(g);
			// Draws the maze (currently only makes a 3x3 grid)
			for (int i = 0; i < maze.getMyRow() + 2 * bxWidth; i += bxWidth) {
				for (int j = 0; j < maze.getMyCol() + 2 * bxHeight; j += bxHeight) {
					g.drawRect(i, j, bxWidth, bxHeight);
				}
			}
			g.drawString("<O>", player.getLocationX(), player.getLocationY());
		}
		
	}
	
	/**
	 * Makes the player go north
	 * @author Roland Hanson
	 *
	 */
	private class goNorth implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (player.getLocationY() > 1) {
				player.setLocation(player.getLocationX(), player.getLocationY() - 1);
			}
		}
	}
	
	/**
	 * Makes the player go east
	 * @author Roland Hanson
	 *
	 */
	private class goEast implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (player.getLocationX() < maze.getMyRow() + 2) {
				player.setLocation(player.getLocationX() + 1, player.getLocationY());
			}
		}
	}
	
	/**
	 * Makes the player go south
	 * @author Roland Hanson
	 *
	 */
	private class goSouth implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (player.getLocationY() < player.getLocationY() + 2) {
				player.setLocation(player.getLocationX(), player.getLocationY() + 1);
			}
		}
	}
	
	/**
	 * Makes the player go west
	 * @author Roland Hanson
	 *
	 */
	private class goWest implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (player.getLocationX() > 1) {
				player.setLocation(player.getLocationX() - 1, player.getLocationY());
			}
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
	
	/**
	 * Exits the program
	 * @author Roland Hanson
	 *
	 */
	private class exitGame implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			System.exit(EXIT_ON_CLOSE);
		}
	}
	
	/**
	 * Initializes the maze once the start button is pressed
	 * @author Roland Hanson
	 *
	 */
	private class startGame implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Initialize maze & player
			maze.initializeRooms();
			maze.initializeRoomQuestions();
			player.setLocation(1, 1);
			btnStart.setVisible(false);
			mazePanel.repaint();
		}
	}
}