import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;

public class ForecastAdapter implements ListViewAdapter {
	
	private List<Forecast> data = new ArrayList<>();
	
	public void setData(List<Forecast> data) {
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
		views.add(new View(Layout.getLabel1(0), WeatherAPI.getCity()));
		int n = 1;
		for (int i = 0; i < data.size(); i++) {
			Forecast fc = data.get(i);
			if (i == 0) {
				for (int j = 0; j < 5; j++) {
					JLabel label = Layout.getLabel1(n);
					String str = null;
					ImageIcon icon = null;
					switch (j) {
					case 0: str = fc.getDateString("'Today', MMM dd"); break;
					case 1: icon = ImageIconScale.getScaledImageIcon(label.getWidth(), label.getHeight(), fc.getIcon()); break;
					case 2: str = fc.getDescription(); break;
					case 3: str = String.valueOf(fc.getHighTemp()) + "\u00b0"; break;
					case 4: str = String.valueOf(fc.getLowTemp()) + "\u00b0"; break;
					}
					if (str != null) views.add(new View(label, str));
					else views.add(new View(label, icon));	
					n += 1;
				}
			}
			else {
				for (int j = 0; j < 5; j++) {
					JLabel label = Layout.getLabel1(n);
					String str = null;
					ImageIcon icon = null;
					switch (j) {
					case 0: icon = ImageIconScale.getScaledImageIcon(label.getWidth(), label.getHeight(), fc.getIcon()); break;
					case 1: str = fc.getDateString("EE, MMM dd"); break;
					case 2: str = fc.getDescription(); break;
					case 3: str = String.valueOf(fc.getHighTemp()) + "\u00b0"; break;
					case 4: str = String.valueOf(fc.getLowTemp()) + "\u00b0"; break;
					}
					if (str != null) views.add(new View(label, str));
					else views.add(new View(label, icon));
					n += 1;
				}
			}
		}
		return views;
	}
	


}
