package jogotecprog;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public abstract class Personagem {

    protected int x, y;
    protected int dx, dy;
    protected Image imagem;
    protected int altura, largura;// para as colisoes
    protected int vida;
    protected int dano;
    protected int direcao, direcaoAnterior;
    protected boolean movendo;/*flag para ver se o personagem esta se movendo e para 
    trocar os sprites*/
    protected int faseMovimento;/*determina qual o sprite do momento, para trocar 
    os sprites*/
    protected int velocidade;
    protected boolean isVisivel;
    protected boolean atacando;
    protected int contaAtaque;

    public int getX() {

        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return imagem;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vid) {
        vida = vid;
    }

    public void setVelocidade(int velocidade) {
        dx = velocidade;
        dy = velocidade;
    }

    abstract void setVisivel(boolean b);

    abstract void load();

}
