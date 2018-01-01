/* Author: Michael Ortiz
 * Date: 4/12/2017
 * Course: CS 67
 * Description: Main testing interface
 */

import java.util.ArrayList;
import java.util.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.MouseMotionAdapter;



public class Main {
	int index = 0;
	int clickNum = 0;
	ArrayList<Trial> updatedList;
	boolean hasClicked;
	private JFrame frame;


	/**
	 * Create the application.
	 */
	public Main(ArrayList<Trial> trialList, int subID) {
		Collections.shuffle(trialList);
		initialize(trialList, subID);
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(ArrayList<Trial> trialList, int subID) {
		
		frame = new JFrame();

		frame.getContentPane().setForeground(Color.WHITE);
		
		frame.setBounds(100, 100, 700, 700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		/****** Start Button ******/
		Button startButton = new Button("Start");
		startButton.setBackground(Color.BLACK);
		startButton.setActionCommand("Start");
		startButton.setBounds(350, 350, 60, 60);
		frame.getContentPane().add(startButton);
		
		/****** Goal Button ******/
		int currW = trialList.get(index).getWidth();
		Random rand = new Random();
		int r = rand.nextInt(trialList.get(index).getAmp()/2);
		int newX = startButton.getX() + r;
		int newY = startButton.getY() + r;
		
		Button goalButton = new Button("");
		goalButton.setBackground(Color.GREEN);
		System.out.println("Inserting Button with width: " + currW);
		
		goalButton.setBounds(newX, newY, currW, currW);
		frame.getContentPane().add(goalButton);
		goalButton.setEnabled(false);
		
		
		/***** End Labels ****/
		JLabel goodbye = new JLabel("Thank you for your time :)");
		goodbye.setForeground(Color.DARK_GRAY);
		goodbye.setBackground(Color.BLUE);
		goodbye.setFont(new Font("Arial", Font.BOLD, 16));
		goodbye.setBounds(238, 96, 218, 40);
		frame.getContentPane().add(goodbye);
		goodbye.setVisible(false);
		
		JLabel complete = new JLabel("Study Complete");
		complete.setForeground(Color.DARK_GRAY);
		complete.setFont(new Font("Arial", Font.BOLD, 16));
		complete.setBounds(267, 60, 140, 19);
		frame.getContentPane().add(complete);
		complete.setVisible(false);
		
		JLabel trialsLeftLabel = new JLabel("Trial(s) Left: " + trialList.size());
		trialsLeftLabel.setFont(new Font("Arial", Font.BOLD, 12));
		trialsLeftLabel.setBounds(10, 11, 101, 14);
		frame.getContentPane().add(trialsLeftLabel);
		
		JButton reviewButton = new JButton("Review Data");
		reviewButton.setFont(new Font("Arial", Font.BOLD, 13));
		reviewButton.setBounds(275, 174, 120, 40);
		frame.getContentPane().add(reviewButton);
		reviewButton.setVisible(false);
		
		

		startButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
		
				System.out.println("Start Button Clicked");
				System.out.println("Goal Peek Width: " + goalButton.getWidth());
				startButton.setBackground(Color.LIGHT_GRAY);
				startButton.setForeground(Color.BLACK);
				goalButton.setEnabled(true);
				trialList.get(index).setSubID(subID);
				
				// Start timer
				trialList.get(index).setStartTime(System.currentTimeMillis());
				trialList.get(index).setStartPoint(new Point(startButton.getX(), startButton.getY()));
				
				startButton.setEnabled(false);
				clickNum = 0;
				hasClicked = false;
				
				
					
			}	
			
		});
		
			
			
			goalButton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					trialList.get(index).setEndTime(System.currentTimeMillis());
					
					System.out.println("Button with width " + goalButton.getWidth() +  " removed");
					
					
					long difference = trialList.get(index).getEndTime() - trialList.get(index).getStartTime();
					System.out.println("Time Spent: " + difference);
					
					Point p = new Point(goalButton.getX(), goalButton.getY());
					
					//Record info
					trialList.get(index).setTimeTaken(difference);
					trialList.get(index).setTargetPoint(p);
					trialList.get(index).setTrialNum(index);
					
					goalButton.setVisible(false);
					
					if (hasClicked){
						trialList.get(index).setSuccess(0);
					}
					
					else{
						trialList.get(index).setSuccess(1);
					}
					
					index++;
					int trialsLeft;
					trialsLeft = trialList.size() - index;
					trialsLeftLabel.setText("Trial(s) Left: " + trialsLeft);
					System.out.println("Curr Indx: " + index);
					System.out.println("Trial Size: " + trialList.size());
					
					if(index == trialList.size()){
						startButton.setVisible(false);
						goalButton.setVisible(false);
						System.out.println("END!");
						goodbye.setVisible(true);
						complete.setVisible(true);
						updatedList = trialList;
						outputData(updatedList);
						reviewButton.setVisible(true);

						
					}
					
					else{
						
						Random rand = new Random();
						int currAmp = trialList.get(index).getAmp();
						int r = rand.nextInt(currAmp/2);
						int newX;
						int newY;
						currAmp = currAmp - r;
						
						int c = rand.nextInt(4);
						
						if ( c == 0){
							newX = startButton.getX() + r;
							newY = startButton.getY() - currAmp;
						}
						
						else if (c == 1){
							newX = startButton.getX() - r;
							newY = startButton.getY() + currAmp;
						}
					
						else if (c == 2){
							newX = startButton.getX() + r;
							newY = startButton.getY() + currAmp;
						}
						
						else {
							newX = startButton.getX() - r;
							newY = startButton.getY() - currAmp;
						}
						
						System.out.println("New coordinates: " + newX + " and " + newY);
						
						trialList.get(index).setTargetPoint(new Point(newX, newY));
						goalButton.setBounds (newX, newY, trialList.get(index).getWidth(), trialList.get(index).getWidth());
						goalButton.setBackground(Color.GREEN);
						goalButton.setVisible(true);
						startButton.setBackground(Color.BLACK);
						startButton.setForeground(Color.LIGHT_GRAY);
						goalButton.setEnabled(false);
						
						
						
					}
	
					startButton.setEnabled(true);
					
					
				}
				
			});
	
	
			frame.getContentPane().addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent p) {
					clickNum++;
					if (clickNum >= 1 && !goalButton.contains(p.getPoint())){
						
						System.out.println("Current number of clicks:" + clickNum);
						hasClicked = true;
					}
					
					else{
						hasClicked = false;
					}
				}
			});
			
			frame.getContentPane().addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseMoved(MouseEvent p) {
					if (!startButton.isEnabled() && goalButton.isEnabled()){
						trialList.get(index).addMovement(p.getPoint());
						trialList.get(index).addTime(System.currentTimeMillis());
					}
				}
			});
			reviewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new Data(updatedList);
					frame.dispose();
					
				}
			});
		
	
			
	

		}
	
	private void outputData (ArrayList<Trial> trialList){
		/********* Trial Info **********/
		try {
			File file = new File("studyData.txt");
			FileWriter fileWriter = new FileWriter(file);
			
			fileWriter.write("SubjectID TrialNum Amplitude Width StartPos TargetPos Time Success");
			fileWriter.write(System.lineSeparator());
			for (int i = 0; i < trialList.size(); i++){
				fileWriter.write(trialList.get(i).trialInfo());
				fileWriter.write(" " + trialList.get(i).getTimeTaken()); //Time Taken
				fileWriter.write(" " + trialList.get(i).getSuccess()); // Success
				
				fileWriter.write(System.lineSeparator());
				fileWriter.flush();

				
			}
			
			
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/********* Advanced Trial Info **********/
		
		try {
			File file2 = new File("AdvancedData.txt");
			FileWriter fileWriter = new FileWriter(file2);
			
			fileWriter.write("SubjectID TrialNum Amplitude Width StartPos TargetPos Time Success Cursor Pos (x,y)");
			fileWriter.write(System.lineSeparator());
			for (int i = 0; i < trialList.size(); i++){
				for (int j = 0; j < trialList.get(i).getTrack().size(); j++){
					Point currP = trialList.get(i).getTrack().get(j);
					fileWriter.write(trialList.get(i).trialInfo());
					fileWriter.write(" " + trialList.get(i).getTimeTaken()); //Time
					fileWriter.write(" " + trialList.get(i).getSuccess()); // Success
					
					
					String output = " (" + currP.getX()+", " + currP.getY()+")"; 
					fileWriter.write(output);
					fileWriter.write(System.lineSeparator());
				}
				fileWriter.write(System.lineSeparator());
				fileWriter.flush();

				
			}
			
			
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
