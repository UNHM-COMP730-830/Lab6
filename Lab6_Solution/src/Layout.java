import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;

public class Layout {
	
	private static List<JLabel> layout1 = new ArrayList<>();
	private static List<JLabel> layout2 = new ArrayList<>();
	
	public Layout() {
	}
	
	public static void addLabel1(JLabel label) {
		layout1.add(label);
	}
	
	public static void addLabel2(JLabel label) {
		layout2.add(label);
	}
	
	public static JLabel getLabel1(int n) {
		return layout1.get(n);
	}
	
	public static JLabel getLabel2(int n) {
		return layout2.get(n);
	}

}
