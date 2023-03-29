package game;

import javax.swing.JFrame;

public class Program {

	public static void main(String[] args) {
		JFrame janela = new JFrame("Personagem");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setSize(1600,900);
		
		
		Canvas canvas = new Canvas();
		janela.add(canvas);
		
		janela.addKeyListener(canvas);
		
		janela.setVisible(true);
	}

}
