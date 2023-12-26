import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class ImageIconScale {
	
	public static ImageIcon getScaledImageIcon(int w, int h, String img) {
		ImageIcon icon1 = new ImageIcon("../Lab6_Images/" + img + ".png");
		Image img1 = icon1.getImage();
		BufferedImage img2 = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = img2.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(img1, 0, 0, w, h, null);
		g2.dispose();
		return new ImageIcon(img2);
	}

}
