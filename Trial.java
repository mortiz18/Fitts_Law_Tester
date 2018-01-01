import java.awt.Point;
import java.util.ArrayList;

class Trial {
	private int amp;
	private int  w;
	private int trialNum;
	private int subID;
	private int success;
	private long s;
	private long e;
	private long timeTaken;
	private Point startPoint;
	private Point targetPoint;
	
	private ArrayList<Point> movements;
	private ArrayList<Long>  timeList;

	public Trial (int A, int W) {
		this.amp = A;
		this.w = W;
		movements = new ArrayList<Point>();
		timeList = new ArrayList<Long>();
	}

	public int getAmp(){
		return amp;
	}
	
	public int getWidth(){
		return w;
	}
	
	public int getTrialNum(){
		return trialNum;
	}
	
	
	public long getStartTime(){
		return s;
	}
	
	public long getEndTime(){
		return e;
	}
	
	public long getTimeTaken (){
		return timeTaken;
	}
	
	public int getSubID(){
		return subID;
	}
	

	
	public Point getTargetPoint (){
		return targetPoint;
	}
	
	public void addMovement(Point p){
		movements.add(p);
	}
	
	public void addTime(Long l){
		timeList.add(l);
	}
	
	public void setTrialNum(int num){
		trialNum = num;
	}
	
	public void setStartTime(long num){
		s = num;
	}
	
	public void setEndTime(long num){
		e = num;
	}
	
	public void setTimeTaken (long num){
		timeTaken = num;
	}
	
	public void setSubID (int s){
		subID = s;
	}
	
	public void setStartPoint (Point p){
		startPoint = p;
	}
	
	public void setTargetPoint (Point p){
		targetPoint = p;
	}
	
	public void setSuccess(int num){
		success = num;
	}
	
	public ArrayList<Point> getTrack(){
		return movements;
	}
	
	public ArrayList<Long> getTimeList(){
		return timeList;
	}
	
	public int getSuccess (){
		return success;
	}
	
	public String trialInfo (){
		int startX = (int) startPoint.getX();
		int startY = (int) startPoint.getY();
		
		int targetX = (int) targetPoint.getX();
		int targetY = (int) targetPoint.getY();
		
		
		String startPos = " (" + startX + ", " + startY + ") ";
		String targetPos = "(" + targetX + ", " + targetY + ") ";
		
		String output;
		output = "\n"+ subID + " "+ trialNum + " " + amp + " " + w  + startPos + targetPos;
		System.out.println(output);
		return output;
		
	}
	
	
	
	
	
	
}