import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class View {
	
	JLabel label = null;
	String text = null;
	ImageIcon icon = null;
	
	public View(JLabel label, String text) {
		this.label = label;
		this.text = text;
	}
	
	public View(JLabel label, ImageIcon icon) {
		this.label = label;
		this.icon = icon;
	}
	
	public void update() {
		if (text != null) label.setText(text);
		else label.setIcon(icon);
	}
	
}
