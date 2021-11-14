package jogotecprog;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class PoderBoss3 extends Poderes {

    public PoderBoss3(int x, int y) {//recebe como parametros o x e y do jogador   
        //super(x2, y2);
        this.x = x;
        this.y = y + 20;
        this.alcance = 0;
        this.velocidade = 10;
        isVisivel = true;
    }

    @Override
    public void load() {
        ImageIcon referencia = new ImageIcon("imagens\\poderThanos.png");
        //imagem = referencia.getImage();

        //Para mudar o tamanho da imagem
        Image img = referencia.getImage();
        BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();

        //  g.drawImage(img, jogador1.getX(), jogador1.getY()-100, 30, 30, null, null);
        g.drawImage(img, 0, 0, 100, 100, null, null);

        ImageIcon ref2 = new ImageIcon(bi);
        imagem = ref2.getImage();

        this.largura = 1;//imagem.getWidth(null);
        this.altura = 1;//imagem.getHeight(null);
    }

    @Override
    public void update() {
        this.x -= velocidade;
        if (this.x < alcance) {
            isVisivel = false;
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y + 100, largura, altura);
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
