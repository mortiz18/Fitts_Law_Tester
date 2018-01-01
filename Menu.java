/* Author: Michael Ortiz
 * Date: 4/12/2017
 * Course: CS 67
 * Description: Dialog interface for fitt's law tester
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;


public class Menu {
	
	private JFrame f;
	private JPanel p;
	private JButton okButton;
	private JButton cancelButton;
	private JTextField subID;
	private JTextField trials;
	private JLabel widthLabel;
	private JLabel IDLabel;
	private JTextField amplitude;
	private JTextField width;
	private Button removeAmp;
	private Button addAmp;
	private Button addWidth;
	private Button removeWidth;
	private List widths;
	private JLabel totalTrials;
	private ArrayList<Trial> trialList;
	private int trialsPerC;
	
	
	 public Menu(){
	
			inputMenu();
	}
	
	public void inputMenu (){
		
		f = new JFrame("Fitt's Law Tester");

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		p = new JPanel();
		p.setBackground(Color.LIGHT_GRAY);
		

		
		/********** Subject ID *********/
		JLabel subIDLabel = new JLabel("Subject ID: ");
		subIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		subIDLabel.setBounds(10, 37, 74, 14);
		p.add(subIDLabel);
		
		
		subID = new JTextField();
		subID.setBounds(94, 36, 32, 20);
		p.add(subID);
		subID.setColumns(10);
		
		/********** Trials per Condition *********/
		JLabel TrialPC = new JLabel("Trials per condition:");
		TrialPC.setFont(new Font("Tahoma", Font.PLAIN, 14));
		TrialPC.setBounds(10, 73, 127, 14);
		p.add(TrialPC);
		
		
		
		trials = new JTextField();
		trials.setBounds(147, 72, 32, 20);
		p.add(trials);
		trials.setColumns(10);
		
		
		
		/********** Other labels *********/
		JLabel ampLabel = new JLabel("Amplitudes: (pixels)\r\n");
		ampLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ampLabel.setBounds(10, 137, 127, 14);
		p.add(ampLabel);
		
		widthLabel = new JLabel("Widths: (pixels)");
		widthLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		widthLabel.setBounds(184, 137, 101, 14);
		p.add(widthLabel);
		
		IDLabel = new JLabel("Indices of Difficulty:");
		IDLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		IDLabel.setBounds(335, 127, 127, 34);
		p.add(IDLabel);
		
		totalTrials = new JLabel("Total Trials: ");
		totalTrials.setBounds(10, 357, 97, 23);
		p.add(totalTrials);
		
		
		/********** Indices of Difficulty *********/
		List list = new List();
		list.setBackground(Color.GRAY);
		list.setBounds(335, 206, 110, 121);
		p.add(list);
		
		
		/*********** Amplitudes Configuration **********/
		amplitude = new JTextField();
		amplitude.setBounds(10, 162, 45, 20);
		p.add(amplitude);
		amplitude.setColumns(10);
		
		
		
		List amplitudes = new List();
		amplitudes.setBounds(10, 206, 110, 121);
		p.add(amplitudes);
		
		
		addAmp = new Button("+");
		addAmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if (!amplitude.getText().isEmpty()){
					boolean unique = true;
					for (int i= 0 ; i < amplitudes.getItemCount(); i++){
						if (amplitudes.getItem(i).equals(amplitude.getText())){
							unique = false;
						}
						
					}
					
					if (unique){
						amplitudes.add(amplitude.getText());
					}
					
				}
				
				
				int tTrials = calcTrials(amplitudes.getItemCount(), widths.getItemCount(), trials.getText());
				totalTrials.setText("Total trials: " + tTrials);
				
				
				if (widths.getItemCount() != 0){
					list.removeAll();
					calcID(amplitudes, widths, list);
					
				}
				
				
			}
		});
		
		addAmp.setBounds(61, 160, 20, 23);
		p.add(addAmp);
		
		removeAmp = new Button("-");
		removeAmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					
					amplitudes.remove(amplitudes.getSelectedIndex());
					
					
				} catch (Exception x){
					
				}
				
				int tTrials = calcTrials(amplitudes.getItemCount(), widths.getItemCount(), trials.getText());
				totalTrials.setText("Total trials: " + tTrials);
				
				if (amplitudes.getItemCount() != 0){
					list.removeAll();
					calcID(amplitudes, widths, list);
				}
			}
		});
		removeAmp.setBounds(87, 160, 20, 23);
		p.add(removeAmp);
		
		/*********** Width Configuration ********/
		width = new JTextField();
		width.setBounds(184, 162, 45, 20);
		p.add(width);
		width.setColumns(10);
		
		//Creating list
		widths = new List();
		widths.setBounds(184, 206, 110, 121);
		p.add(widths);
		
		
		//Add button
		addWidth = new Button("+");
		addWidth.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (!width.getText().isEmpty()){
				
					
					boolean unique = true;
					for (int i= 0 ; i < widths.getItemCount(); i++){
						if (widths.getItem(i).equals(width.getText())){
							unique = false;
						}
						
					}
					
					if (unique){
						widths.add(width.getText());
					}
				}
				
				int tTrials = calcTrials(amplitudes.getItemCount(), widths.getItemCount(), trials.getText());
						totalTrials.setText("Total trials: " + tTrials);
				
				if (amplitudes.getItemCount() != 0){
					list.removeAll();
					calcID(amplitudes, widths, list);
				}
			
				
			}
			
		});
		
		addWidth.setBounds(235, 160, 20, 22);
		p.add(addWidth);
		
		removeWidth = new Button("-");
		removeWidth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try{
						
						widths.remove(widths.getSelectedIndex());
						
					} catch (Exception x){
					
				}
					int tTrials = calcTrials(amplitudes.getItemCount(), widths.getItemCount(), trials.getText());
					totalTrials.setText("Total trials: " + tTrials);
					
					if (amplitudes.getItemCount() != 0){
						list.removeAll();
						calcID(amplitudes, widths, list);
					}
			}
		});
		removeWidth.setBounds(265, 160, 20, 22);
		p.add(removeWidth);
		
		
		
		
		
		
		
		
		
		
		/********** OK Button *********/
		okButton = new JButton("Ok");
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
				trialsPerC = Integer.parseInt(trials.getText());
				}catch (Exception x){
			}
				
				trialList = new ArrayList<Trial>();
				
				
				for(int i = 0;  i < amplitudes.getItemCount(); i++){
					int A = Integer.parseInt(amplitudes.getItem(i));
					
					
					for (int j = 0; j < widths.getItemCount(); j++){
						int W = Integer.parseInt(widths.getItem(j));

						
						for (int t = 0; t < trialsPerC; t++){

							
							trialList.add(new Trial(A, W));
						}
					}
				}
				p.setVisible(false);
				f.dispose();
				
				try{				
					new Main(trialList, Integer.parseInt(subID.getText()));
				}catch(Exception x){
					
				}
				
				
			}
		});
		
		okButton.setBounds(192, 371, 63, 23);
		
		/********** CANCEL Button *********/
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				f.dispose();
			}
		});
		cancelButton.setBounds(276, 371, 80, 23);
		p.setLayout(null);
		p.add(okButton);
		p.add(cancelButton);
		
		f.getContentPane().add(p);
		
		f.setVisible(true);
		f.setSize(500, 500);
	
		
		
		
		
	}
	
	public List calcID(List a, List w, List outList){
		for (int i = 0; i < a.getItemCount(); i++){
			for (int j = 0; j < w.getItemCount(); j++){
				float ID;
				float currA = Integer.parseInt(a.getItem(i));
				float currW = Integer.parseInt(w.getItem(j));
				
				ID = (float)(2.0*currA)/currW;
				float log = (float) (Math.log(ID)/Math.log(2));
				ID = log;
				
				
				boolean unique = true;
				for (int k = 0 ; k < outList.getItemCount(); k++){
					if (outList.getItem(k).equals(String.valueOf(ID))){
						unique = false;
					}
					
				}
				
				if (unique){
					outList.add(String.valueOf(ID));
				}
				
			}
		}
		
		return outList;
	}
	
	public int calcTrials(int a, int w, String t){
		int trialsPerC = 0;
		try {
			trialsPerC = Integer.parseInt(t);
			
		} catch(Exception x){
			
		}
	
		return a * w * trialsPerC;
	}
	
	public static void main(String[] args){
		new Menu();
	}
}