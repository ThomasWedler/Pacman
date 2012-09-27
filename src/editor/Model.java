package editor;

import java.util.LinkedList;
import javax.swing.Icon;
import javax.swing.JLabel;

public class Model {

	private Icon actualIcon;
	private LinkedList<JLabel> fixedDND;

	public Icon getActualIcon() {
		return actualIcon;
	}

	public void setActualIcon(Icon actualIcon) {
		this.actualIcon = actualIcon;
	}

	public LinkedList<JLabel> getFixedDND() {
		return fixedDND;
	}

	public void setFixedDND(LinkedList<JLabel> fixedDND) {
		this.fixedDND = fixedDND;
	}

}
