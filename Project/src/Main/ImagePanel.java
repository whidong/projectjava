package Main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class ImagePanel extends JPanel{ //주석 2 : 클래스 상속
	private Image img;
	public ImagePanel(Image img) {
		this.img = img;
		setSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setLayout(null);
	}
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null );
	}
	
	public Dimension getDim() {
		return new Dimension(img.getWidth(null), img.getHeight(null));
	}
}
