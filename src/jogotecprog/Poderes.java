package jogotecprog;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public abstract class Poderes {

    protected Image imagem;
    protected int x, y;
    protected int largura, altura;
    protected boolean isVisivel;

    protected int alcance = 1300;
    protected int velocidade = 10;

    abstract public void load();

    abstract public void update();

    abstract public int getX();

    abstract public int getY();

    abstract public void setIsVisivel(boolean isVisivel);

    abstract public boolean getisVisivel();

    abstract public Rectangle getBounds();

    abstract public Image getImagem();

    abstract public void setVelocidade(int vel);

    abstract public int getDano();

}
