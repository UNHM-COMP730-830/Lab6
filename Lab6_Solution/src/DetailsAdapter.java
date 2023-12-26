
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;

import javax.swing.ImageIcon;

public class DetailsAdapter implements ListViewAdapter {
	
	private List<ForecastDetails> data = new ArrayList<>();
	
	public void setData(List<ForecastDetails> data) {
		this.data = data;
	}
	
//	private ImageIcon getScaledImageIcon(int w, int h, String img) {
//		ImageIcon icon1 = new ImageIcon("img/" + img + ".png");
//		Image img1 = icon1.getImage();
//		BufferedImage img2 = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
//		Graphics2D g2 = img2.createGraphics();
//		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//		g2.drawImage(img1, 0, 0, w, h, null);
//		g2.dispose();
//		return new ImageIcon(img2);
//	}
	
	@Override
	public List<View> getViews() {
		List<View> views = new ArrayList<>();
		views.add(new View(Layout.getLabel2(0), "\u2190     " + data.get(0).getValue())); // date
		JLabel label = Layout.getLabel2(1);
		views.add(new View(label, ImageIconScale.getScaledImageIcon(label.getWidth(), label.getHeight(), data.get(1).getValue()))); // icon
		views.add(new View(Layout.getLabel2(2), data.get(2).getValue())); // description
		int n = 3;
		for (int i = 3; i < data.size(); i++) { // for each information
			views.add(new View(Layout.getLabel2(n), data.get(i).getValue()));
			n += 1;
		}
		return views;
	}

}
