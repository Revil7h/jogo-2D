package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Robot {
	
	private int timer;
	
	private int indiceImagemAtual;
	private int indiceImagemAtualParado;
	
	private BufferedImage imagemParado[];
	private BufferedImage imagemCorrendo[];
	
	private int altura;
	private int largura;
	
	private int posiX;
	private int posiY;
	
	private int direcao;
	private int ultimaDirecao;
	
	private int velocidade = 4;
	
	public Robot() {
		ultimaDirecao = 1; direcao = 0;
		timer = 0;
		indiceImagemAtual = indiceImagemAtualParado = 0;
		
		imagemParado = new BufferedImage[10];
		imagemCorrendo = new BufferedImage[8];
		
		largura = 150;
		altura = 150;
		
		posiX = 400;
		posiY = 400;
		
		direcao = 0;
		
		for(int i=0; i<8 ; i++) {
			try {
				imagemCorrendo[i] = ImageIO.read(new File("imagensRobos/Run ("+(i+1)+").png"));
			} catch (IOException e) {
				System.out.println("Não foi possivel carregar a imagem");
				e.printStackTrace();
			}
		}
		
		for(int i=0; i<10 ; i++) {
			try {
				imagemParado[i] = ImageIO.read(new File("imagensRobos/Idle ("+(i+1)+").png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void atualizar() {
		//responsavel por atualizar a animação
		timer++;
		
		if(timer >= 5) {
			indiceImagemAtual++;
			indiceImagemAtualParado++;
			
			if(indiceImagemAtual == 8) 
				indiceImagemAtual=0;			
			
			if(indiceImagemAtualParado == 10)
				indiceImagemAtualParado=0;
				
			timer = 0;
		}
		
		if(direcao == 1) {
			posiX += velocidade;
		}else if(direcao == -1) {
			posiX -= velocidade;
		}
	}

	public void pintar(Graphics g) {
		
		if(ultimaDirecao == 1) {
			g.drawImage(imagemParado[indiceImagemAtual],
			posiX, posiY, 
			posiX + largura, posiY + altura,
			0, 0,
			imagemCorrendo[indiceImagemAtual].getWidth(),imagemCorrendo[indiceImagemAtual].getHeight(),
			null);
		}
		else if(ultimaDirecao == -1) {
			g.drawImage(imagemParado[indiceImagemAtual],
			posiX, posiY, 
			posiX + largura, posiY + altura,
			imagemCorrendo[indiceImagemAtual].getWidth(), 0,
			0,imagemCorrendo[indiceImagemAtual].getHeight(),
			null);
		}
		
		//imagem do robo correndo
		if(direcao == 1) {
			
			g.drawImage(imagemCorrendo[indiceImagemAtual], //a imagem a ser desenhada
			posiX, posiY,                           //posição x e y da imagem
			posiX + largura, posiY + altura,		//posição + tamanho da imagem
			0, 0,          						    //Canto da imagem original
			imagemCorrendo[indiceImagemAtual].getWidth(),imagemCorrendo[indiceImagemAtual].getHeight(), null);
		}
		else if(direcao == -1) {
			
			g.drawImage(imagemCorrendo[indiceImagemAtual],
			posiX, posiY,                           
			posiX + largura, posiY + altura,		
			imagemCorrendo[indiceImagemAtual].getWidth(), 0,          						   
			0,imagemCorrendo[indiceImagemAtual].getHeight(), null);
		}
	}
	
	public void setDirecao(int dir) {
		direcao = dir;
	}
	
	public void setUltimaDirecao(int dir) {
		ultimaDirecao = dir;
	}
}
