package work.niaz.example.GuitarString;

public class WindowState {
	
	private String note;
	
	public WindowState(boolean noteCheck, String note) {
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
}
