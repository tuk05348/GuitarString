package work.niaz.example.GuitarString;

public class WindowState {
	
	private String note;
	private double xScale;
	private double yScale;
	
	public WindowState(boolean noteCheck, String note, double xScale, double yScale) {
		this.xScale = xScale;
		this.yScale = yScale;
		if(noteCheck) {
			this.note = note;
		}
		else {
			this.note = "";
		}
	}
	
	public String getNote() {
		return note;
	}
	
	public double getXScale() {
		return xScale;
	}
	
	public double getYScale() {
		return yScale;
	}
}
