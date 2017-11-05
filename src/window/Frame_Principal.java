package window;

import javax.swing.JFrame;

public class Frame_Principal extends JFrame {
	private static final long serialVersionUID = 1L;
	private static int h = 600;
	private static int w = 400;
	private Panel_Principal pp = null;

	public Frame_Principal() {
		this.setSize(Frame_Principal.w, Frame_Principal.h);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Generador de /CIV/ version 1.0");

		this.pp = new Panel_Principal(this);
		this.add(this.pp);

		this.setVisible(true);
	}

}
