package jogotecprog;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class Inimigo3 extends Inimigos {
   
    
    public Inimigo3(int x, int y){
        vida = 40;
        dano = 15;  
        this.x = x;
        this.y = y;
        altura = 100;
        largura = 100;
        isVisivel = true;
        dx = 1;
        dy = 1;   
        this.load();
    }
    
    public void load(){
        
        ImageIcon referencia = new ImageIcon("imagens\\Troll_03_1_WALK_000.png"); 
        referencia.setImage(referencia.getImage().getScaledInstance(200, 200, 100));
        imagem = referencia.getImage();
        
        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
                 
    }
    
    
}

