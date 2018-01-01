/* Author: Michael Ortiz
 * Date: 4/12/2017
 * Course: CS 67
 * Description: Data Review interface
 */

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Button;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Data {

	private JFrame frame;
	int index = 0;

	

	/**
	 * Create the application.
	 */
	public Data(ArrayList<Trial> trialList) {
	initialize(trialList);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(ArrayList<Trial> trialList) {
	frame = new JFrame();
	frame.setBounds(100, 100, 700, 700);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	frame.setVisible(true);
	
	JButton prevButton = new JButton("<");
	prevButton.setBounds(10, 347, 41, 43);
	frame.getContentPane().add(prevButton);
	prevButton.setEnabled(false);
	
	JButton nextButton = new JButton(">");
	
	nextButton.setBounds(633, 347, 41, 43);
	frame.getContentPane().add(nextButton);
	
	JLabel trialLabel = new JLabel("Trial #:");
	trialLabel.setFont(new Font("Arial", Font.BOLD, 16));
	trialLabel.setBounds(10, 11, 108, 14);
	frame.getContentPane().add(trialLabel);
	
	JLabel timeLabel = new JLabel("Time: ");
	timeLabel.setFont(new Font("Arial", Font.BOLD, 16));
	timeLabel.setBounds(10, 36, 108, 14);
	frame.getContentPane().add(timeLabel);
	
	/****** Start Button ******/
	Button startButton = new Button("Start");
	startButton.setForeground(Color.WHITE);
	startButton.setBackground(Color.BLACK);
	startButton.setActionCommand("Start");
	startButton.setBounds(350, 350, 60, 60);
	frame.getContentPane().add(startButton);
	startButton.setEnabled(false);
	
	/****** Goal Button ******/
	int currW = trialList.get(index).getWidth();
	int goalX = (int) trialList.get(index).getTargetPoint().getX();
	int goalY = (int) trialList.get(index).getTargetPoint().getY();
	Button goalButton = new Button("");
	goalButton.setBackground(Color.GREEN);
	goalButton.setBounds(goalX, goalY, currW, currW);
	frame.getContentPane().add(goalButton);
	goalButton.setVisible(true);
	goalButton.setEnabled(false);
	
	trialLabel.setText("Trial #: " + trialList.get(index).getTrialNum());
	timeLabel.setText("Time: " + trialList.get(index).getTimeTaken());
	

	prevButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			nextButton.setEnabled(true);
			goalButton.setVisible(false);
			index--;
			int currW = trialList.get(index).getWidth();
			int goalX = (int) trialList.get(index).getTargetPoint().getX();
			int goalY = (int) trialList.get(index).getTargetPoint().getY();
			goalButton.setBounds(goalX, goalY, currW, currW);
			goalButton.setVisible(true);
			
			trialLabel.setText("Trial #: " + trialList.get(index).getTrialNum());
			timeLabel.setText("Time: " + trialList.get(index).getTimeTaken());
			
			if (index == 0){
				prevButton.setEnabled(false);
			}
		}
	});
	
	
	nextButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			prevButton.setEnabled(true);
			goalButton.setVisible(false);
			index++;
			int currW = trialList.get(index).getWidth();
			int goalX = (int) trialList.get(index).getTargetPoint().getX();
			int goalY = (int) trialList.get(index).getTargetPoint().getY();
			goalButton.setBounds(goalX, goalY, currW, currW);
			goalButton.setVisible(true);
			
			trialLabel.setText("Trial #: " + trialList.get(index).getTrialNum());
			timeLabel.setText("Time: " + trialList.get(index).getTimeTaken());
			
			if (index == trialList.size() - 1){
				nextButton.setEnabled(false);
			}
		}
	});
	
	
	
	}
	

	
}
