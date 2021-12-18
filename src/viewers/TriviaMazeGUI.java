package viewers;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import controllers.*;
import models.*;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JProgressBar;

/**
 * GUI for Trivia Maze (currently WIP) Built using windowbuilder
 * 
 * @author Roland Hanson
 * @version 1.0
 */
public class TriviaMazeGUI extends JFrame {

	/**
	 * Generated serialUID
	 */
	private static final long serialVersionUID = 5085020715814436080L;

	public static JPanel myContentPane;
	public static JButton myBtnN;
	public static JButton myBtnE;
	public static JButton myBtnS;
	public static JButton myBtnW;
	private JMenuBar myMenuBar;
	private static JMenu myMnFile;
	public static JMenuItem myMntmSave;
	public static JMenuItem myMntmLoad;
	private static JMenuItem myMntmExit;
	private static JMenu myMnHelp;
	private static JMenuItem myMntmAbout;
	private static JMenuItem myMntmInstructions;
	private static JMenuItem myMntmCheats;
	public static JPanel myMazePanel;
	public static JButton myBtnStart;

	public static Maze myMaze = new Maze();
	public static MazeControl myMazeCon = new MazeControl();;
	public static String myLastDirection;
	public static int myHealth;
	public static Door myDoorChk;
	public static Player myPlayer = new Player();
	public static Room[][] myRoomChk;
	public static JRadioButton myRdbtnChoiceA;
	public static JRadioButton myRdbtnChoiceB;
	public static JRadioButton myRdbtnChoiceC;
	public static JRadioButton myRdbtnChoiceD;
	public static JLabel myLblQuestion;
	public static JLabel myLblResult;
	public static JTextField myTxtFieldRow;
	public static JTextField myTxtFieldCol;
	public static JLabel myLblPlayerControls;
	public static JLabel myLblInstructions;
	public static JLabel myLblOfRows;
	public static JLabel myLblOfColumns;
	public static JProgressBar myHealthBar;
	public static JLabel lblPlayerHealth;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TriviaMazeGUI frame = new TriviaMazeGUI();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
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
		setBounds(100, 100, 716, 395);
		myContentPane = new JPanel();
		myContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(myContentPane);
		myContentPane.setLayout(null);

		myBtnN = new JButton("N");
		myBtnN.addActionListener(myMazeCon.new GoNorth());
		myBtnN.setBounds(556, 67, 50, 23);
		myContentPane.add(myBtnN);

		myBtnE = new JButton("E");
		myBtnE.addActionListener(myMazeCon.new GoEast());
		myBtnE.setBounds(586, 101, 50, 23);
		myContentPane.add(myBtnE);

		myBtnS = new JButton("S");
		myBtnS.addActionListener(myMazeCon.new GoSouth());
		myBtnS.setBounds(556, 135, 50, 23);
		myContentPane.add(myBtnS);

		myBtnW = new JButton("W");
		myBtnW.addActionListener(myMazeCon.new GoWest());
		myBtnW.setBounds(526, 101, 50, 23);
		myContentPane.add(myBtnW);

		myMenuBar = new JMenuBar();
		myMenuBar.setBounds(0, 0, 700, 22);
		myContentPane.add(myMenuBar);

		myMnFile = new JMenu("File");
		myMenuBar.add(myMnFile);

		myMntmSave = new JMenuItem("Save");
		myMntmSave.addActionListener(myMazeCon.new SaveMaze());
		myMnFile.add(myMntmSave);

		myMntmLoad = new JMenuItem("Load");
		myMntmLoad.addActionListener(myMazeCon.new LoadMaze());
		myMnFile.add(myMntmLoad);

		myMntmExit = new JMenuItem("Exit");
		myMntmExit.addActionListener(myMazeCon.new ExitGame());
		myMnFile.add(myMntmExit);

		myMnHelp = new JMenu("Help");
		myMenuBar.add(myMnHelp);

		myMntmAbout = new JMenuItem("About");
		myMntmAbout.addActionListener(myMazeCon.new AboutMenu());
		myMnHelp.add(myMntmAbout);

		myMntmInstructions = new JMenuItem("Instructions");
		myMntmInstructions.addActionListener(myMazeCon.new InstructionsMenu());
		myMnHelp.add(myMntmInstructions);

		myMntmCheats = new JMenuItem("Cheats");
		myMntmCheats.addActionListener(myMazeCon.new CheatsMenu());
		myMnHelp.add(myMntmCheats);

		myMazePanel = new MazePanel();
		myMazePanel.setBounds(10, 33, 416, 195);
		myContentPane.add(myMazePanel);
		myMazePanel.setVisible(false);

		myBtnStart = new JButton("Start!");
		myBtnStart.addActionListener(myMazeCon.new StartGame());
		myBtnStart.setBounds(269, 181, 89, 23);
		myContentPane.add(myBtnStart);

		myRdbtnChoiceA = new JRadioButton("Choice A");
		myRdbtnChoiceA.addActionListener(myMazeCon.new AnswerChk());
		myRdbtnChoiceA.setBounds(429, 222, 271, 23);
		myContentPane.add(myRdbtnChoiceA);
		myRdbtnChoiceA.setVisible(false);

		myRdbtnChoiceB = new JRadioButton("Choice B");
		myRdbtnChoiceB.addActionListener(myMazeCon.new AnswerChk());
		myRdbtnChoiceB.setBounds(429, 248, 271, 23);
		myContentPane.add(myRdbtnChoiceB);
		myRdbtnChoiceB.setVisible(false);

		myRdbtnChoiceC = new JRadioButton("Choice C");
		myRdbtnChoiceC.addActionListener(myMazeCon.new AnswerChk());
		myRdbtnChoiceC.setBounds(429, 274, 271, 23);
		myContentPane.add(myRdbtnChoiceC);
		myRdbtnChoiceC.setVisible(false);

		myRdbtnChoiceD = new JRadioButton("Choice D");
		myRdbtnChoiceD.addActionListener(myMazeCon.new AnswerChk());
		myRdbtnChoiceD.setBounds(429, 300, 271, 23);
		myContentPane.add(myRdbtnChoiceD);
		myRdbtnChoiceD.setVisible(false);

		myLblQuestion = new JLabel("Question");
		myLblQuestion.setBounds(10, 274, 413, 23);
		myContentPane.add(myLblQuestion);

		myLblResult = new JLabel("Correct!");
		myLblResult.setHorizontalAlignment(SwingConstants.CENTER);
		myLblResult.setBounds(236, 263, 159, 14);
		myLblResult.setVisible(false);
		myContentPane.add(myLblResult);

		myTxtFieldRow = new JTextField();
		myTxtFieldRow.setBounds(210, 249, 86, 20);
		myContentPane.add(myTxtFieldRow);
		myTxtFieldRow.setColumns(10);

		myTxtFieldCol = new JTextField();
		myTxtFieldCol.setBounds(337, 249, 86, 20);
		myContentPane.add(myTxtFieldCol);
		myTxtFieldCol.setColumns(10);

		myLblPlayerControls = new JLabel("Player Controls");
		myLblPlayerControls.setBounds(537, 42, 89, 14);
		myContentPane.add(myLblPlayerControls);

		myLblInstructions = new JLabel("Please enter your desired size for the maze (Default is 4x4)");
		myLblInstructions.setHorizontalAlignment(SwingConstants.CENTER);
		myLblInstructions.setBounds(143, 131, 351, 31);
		myContentPane.add(myLblInstructions);

		myLblOfRows = new JLabel("# of Rows");
		myLblOfRows.setHorizontalAlignment(SwingConstants.CENTER);
		myLblOfRows.setBounds(210, 278, 86, 14);
		myContentPane.add(myLblOfRows);

		myLblOfColumns = new JLabel("# of Columns");
		myLblOfColumns.setHorizontalAlignment(SwingConstants.CENTER);
		myLblOfColumns.setBounds(337, 278, 86, 14);
		myContentPane.add(myLblOfColumns);

		myHealthBar = new JProgressBar();
		myHealthBar.setValue(100);
		myHealthBar.setBounds(511, 201, 146, 14);
		myContentPane.add(myHealthBar);

		lblPlayerHealth = new JLabel("Player Health");
		lblPlayerHealth.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayerHealth.setBounds(537, 185, 89, 14);
		myContentPane.add(lblPlayerHealth);
		myLblQuestion.setVisible(false);
	}

	/**
	 * Displays the maze
	 * 
	 * @author Roland Hanson
	 * 
	 */
	private class MazePanel extends JPanel {

		/**
		 * Default constructor
		 */
		public MazePanel() {

		}

		/**
		 * Draws the maze grid and player location
		 */
		public void paintComponent(Graphics g) {
			int bxWidth = 30;
			int bxHeight = 30;
			super.paintComponent(g);
			// Draws the maze
			for (int i = 27; i < myMaze.getMyRow() * bxWidth; i += bxWidth) {
				for (int j = 0; j < myMaze.getMyCol() * bxHeight; j += bxHeight) {
					g.drawRect(i, j, bxWidth, bxHeight);
				}
			}
			g.drawString("<O>", myPlayer.getLocationX() * bxWidth, myPlayer.getLocationY() * bxHeight);

		}

	}

}
