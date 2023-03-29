package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Canvas extends JPanel implements Runnable, KeyListener{
	
	private Robot javaBot;
	
	public Canvas() {
		javaBot = new Robot();
		
		Thread gameLoop = new Thread(this);
		gameLoop.start();
	}
	
	@Override 
	public void paintComponent(Graphics g2) {
		super.paintComponent(g2);
		
		Graphics2D g = (Graphics2D) g2.create();
		
		//desenhar um fundo branco
		g.setColor(Color.white);
		g.fillRect(0, 0, 1600, 800);
		
		g.setColor(new Color(0, 160, 0));
		g.fillRect(0, 500, 1600, 800);
		
		//desenhar o robo
		javaBot.pintar(g);
	}

	@Override
	public void run() {
		
		while(true) {
			javaBot.atualizar();
			
			repaint(); //o Java sabe chamar o paintComponent
			
			dorme();
		}
	}
	
	//atualiza o estado do meu jogo
	private void atualizar() {
		
	}
	
	private void dorme() {
		try {
			Thread.sleep(16);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()== KeyEvent.VK_D) {
			javaBot.setDirecao(1);
			javaBot.setUltimaDirecao(0);
		
		}else if(e.getKeyCode() == KeyEvent.VK_A) {
			javaBot.setDirecao(-1);
			javaBot.setUltimaDirecao(0);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_D ) {
			javaBot.setDirecao(0);
			javaBot.setUltimaDirecao(1);
		}else if(e.getKeyCode() == KeyEvent.VK_A) {
			javaBot.setDirecao(0);
			javaBot.setUltimaDirecao(-1);
		}
	}
}
