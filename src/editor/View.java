package editor;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

public class View extends JFrame {
	private static final long serialVersionUID = -2325156572708621182L;

	JFrame window = new JFrame("Pacman - Level Editor");
	JPanel view = new JPanel();
	JPanel items = new JPanel();
	GridLayout grid = new GridLayout(1, 2);
	MigLayout left = new MigLayout(new LC().wrapAfter(9).gridGap("0px", "0px"));
	MigLayout right = new MigLayout();
	
	LinkedList<JLabel> labels = new LinkedList<JLabel>();

	public View() {
		setTitle("Pacman - Level Editor");
		setSize(1280, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(grid);
		getContentPane().add(view);
		getContentPane().add(items);
		
		view.setLayout(left);
		items.setLayout(right);
				
		view.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		items.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
		JLabel header = new JLabel("Level:");
		header.setFont(new Font("Dialog Bold", 1, 14));
		view.add(header, new CC().wrap().gapBottom("20px"));
				
		ImageIcon image = new ImageIcon("/Users/Thomas/Workspace/Eclipse/Pacman/pacman/images/gray.png");
		
		for (int i = 0; i < 81; i++) {	
			JLabel label = new JLabel(image);
			label.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			labels.add(label);
		}
		
		for (JLabel label : labels) {
			view.add(label);
		}

		setVisible(true);
	}
}
