package jogotecprog;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class Soco extends Poderes {

    public Soco(int x, int y) {//recebe como parametros o x e y do jogador
        //super(x2, y2);
        this.x = x;
        this.y = y;
        this.alcance = 80;
        this.velocidade = 10;
        isVisivel = true;
    }

    @Override
    public void load() {
        ImageIcon referencia = new ImageIcon("imagens\\soco.png");
        referencia.setImage(referencia.getImage().getScaledInstance(130, 130, 100));
        imagem = referencia.getImage();

        this.largura = 5;//imagem.getWidth(null);
        this.altura = 1;//imagem.getHeight(null);
    }

    @Override
    public void update() {
        this.x += velocidade;
        if (this.x > alcance) {
            //quando chega no alcance limite o soco desaparece
            isVisivel = false;
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, largura + 30, altura);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setIsVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
    }

    @Override
    public boolean getisVisivel() {
        return isVisivel;
    }

    @Override
    public Image getImagem() {
        return imagem;
    }

    @Override
    public void setVelocidade(int vel) {
        velocidade = vel;
    }

    @Override
    public int getDano() {
        return 10;
    }

}
